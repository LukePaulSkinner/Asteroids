package Game2;

import Utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class AiShip extends Ship {







    public static int delay =50;


    // constant speed loss factor



    //Used for holding the direction of targeted item
    public static Vector2D targetdirection;










    //Constructer to create aiship
    public AiShip(int x,int y) {

        position = new Vector2D((x), (y));
        velocity = new Vector2D((0), (0));
        direction = new Vector2D((2), (2));
        radius = 8;
        COLOR=Color.magenta;
        money = 10;
     }

     //Creates a aiship in a random position that is at least 40 pixels away from player ship

    public static Object makeRandomShip() {
        int x = new Random().nextInt(1000);
        while ( x >= Game.ship.position.x-80 && x <= Game.ship.position.x+80 ) { x = new Random().nextInt(1000);}
        int y = new Random().nextInt(1000);
        while ( y >= Game.ship.position.y-80 && y <= Game.ship.position.y+80 ) { y = new Random().nextInt(1000);}

        Object a = new AiShip(x, y);
        return a;

    }


    //Gets direction of targeted item

    public void findobject(Object target) {

        Vector2D a = new Vector2D(target.getpos());
        Vector2D b = new Vector2D(this.position);

        Vector2D vectDirection = b.subtract(a).normalise();

        targetdirection = vectDirection;

    }







    void hit(Object o) {

        //if hit by bullet dies and drops money
        if (o instanceof Bullet){
            IsDead = true;
            o.IsDead = true;
            MoneyDrop.dropmoney(this);
            Game.incScore(20);

        }

    }

    //Calculates which direction ship needs to turn to look at ship returns 0 if looking at ship
    public int turn(){
        int a = (int) (Math.atan2(targetdirection.x, targetdirection.y)*180/Math.PI);
        int b = (int) (Math.atan2(direction.x, direction.y)*180/Math.PI);

        if (a<0){
            a+=180;
        }
        else {a-=180;}


        if (a>b+10){
            return -1;
        }
        else if (a<b-10){
            return 1;
        }
        else return 0;

    }

    public void update() {


        //Finds direction of player ship
        findobject(Game.ship);


        //Rotates ship based on results from turn()
        direction.rotate(turn()*STEER_RATE*Constants.DT); // rotate the ship if the action says to do so

        //changing velocity based on direction
        velocity.addScaled(direction, 1*MAG_ACC*Constants.DT); // move the ship forward
        //simulate drag on the ship
        velocity.mult(0.9);
        //Moving ship based on velocity
        position.addScaled(velocity, Constants.DT);

        //Wrap ship around the stage
        position.wrap(1000,1000);



        //If ship is looking at player fire shots after a random delay
        if (turn()==0) {

            int y = new Random().nextInt(delay+1);
            if (y == delay){
                mkBullet();
            }

        }



    }

    public void draw(Graphics2D g) {
        //Draw ship
        g.setColor(COLOR);
        AffineTransform at = g.getTransform();
        g.translate(position.x, position.y);
        double rot = direction.angle() + Math.PI / 2;
        g.rotate(rot);
        g.scale(DRAWING_SCALE, DRAWING_SCALE);

        g.fillPolygon(XP, YP, XP.length);

        g.setTransform(at);

    }


    int radius() {
        return (int) radius;
    }





}