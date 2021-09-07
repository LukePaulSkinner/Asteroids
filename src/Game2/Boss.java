package Game2;

import Utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;


public class Boss extends Object{
    //points to draw boss
    public int[] XP = {-5, 0, 5, 10,12,15,15,14,10,9,8,6,0,-6,-8,-9,-10,-14,-15,-15,-12,-10};
    public int[] YP = {5, 5, 5, 4,4,0,-10,-12,-12,-10,-5,-4,-5,-4,-5,-10,-12,-12,-10,0,4,4};



    public Vector2D direction;
    public int hp = 300;
    public int delay = 100;




    //Constructer to create boss
    public Boss(int x,int y) {

        position = new Vector2D((x), (y));
        velocity = new Vector2D((0), (0));
        direction = new Vector2D((0), (1));
        radius = 50;
        COLOR = Color.gray;
        money = 100;
    }
    //Function to attack depending on health
    public void attack(){
        if (hp>200){
            spawnasteroid();
        }
        else if (hp>100){
            spawnships();
        }
        else {
            spawnships();
            spawnasteroid();

        }
    }
    //Spawns two ai ships
    public void spawnships(){
        //Adding to alive instead of Objects avoids concurrent modification
        Game.alive.add(new AiShip((int) position.x-65, (int) position.y+65));
        Game.alive.add(new AiShip((int) position.x+65, (int) position.y+65));
    }




    //Spawns one giant asteroid
    public void spawnasteroid(){

        Game.alive.add(new Asteroid(position.x,  position.y+83,0,100,32));
        SoundManager.firebig();

    }





    @Override
    void hit(Object o) {

        if (!(o instanceof PlayerShip)){
            o.IsDead=true;
            hp-=1;
        }

        if (hp==0){
            IsDead=true;
            MoneyDrop.dropmoney(this);
            Game.incScore(100);
        }


    }

    @Override
    void update() {
        //Countdown till nex attack
        delay-=1;
        if (delay==0){
            delay=100;
            attack();
        }

    }

    @Override
    void draw(Graphics2D g) {
        //Draw using points made earlier
        g.setColor(COLOR);
        AffineTransform at = g.getTransform();
        g.translate(position.x, position.y);
        double rot = direction.angle() + Math.PI / 2;
        g.rotate(rot);
        g.scale(5, 5);

        g.fillPolygon(XP, YP, XP.length);

        g.setTransform(at);
        //Draw eye
        g.setColor(new Color(255,hp/2 , 0));
        g.fillOval((int)position.x-25,(int)position.y,50,20);
        g.setColor(Color.black);
        g.drawOval((int)position.x-25,(int)position.y,50,20);

    }

    @Override
    int radius() {
        return (int) radius;
    }
}
