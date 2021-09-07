package Game2;

import Utilities.Vector2D;

import java.awt.*;


public class Bullet extends Object {
    private int life;

    //Creates bullet based on position velocity and direction
    public Bullet(Vector2D pos, Vector2D vel,Vector2D dir) {
        position = pos;
        velocity = vel;
        radius = 5;
        life = 40;
        velocity.addScaled(dir, 200);
        position.addScaled(this.velocity, Constants.DT);
        COLOR = Color.yellow;


    }


    void hit(Object o) {

    }

    void update() {

        //Moves the bullet
        position.addScaled(this.velocity, Constants.DT);
        position.wrap(1000,1000);

        //Bullet life decays, if it reaches 0 then the bullet expires
        life-=1;
        if (life==0){
            IsDead=true;
        }




    }


    void draw(Graphics2D g) {
        //Draw bullet
        g.setColor(COLOR);
        g.drawOval((int) ((int) position.x - radius), (int) ((int) position.y- radius), (int) (radius*2), (int) (radius*2));
    }


    int radius() {
        return (int) radius;
    }
}
