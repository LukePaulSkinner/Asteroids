package Game2;

import Utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

import static Game2.Constants.DT;

public class Asteroid extends Object {


    public static int Maxpoints = 7;
    public static int Minpoints = 4;
    public static int MAX_RADIUS = 16;
    public static int MIN_RADIUS= 4;
    int[] py;
    int[] px;
    int nPoints;
    double DRAWING_SCALE;
    private int life;











    public Asteroid(double x, double y, double vx, double vy, int size) {
        position =  new Vector2D((x), (y));
        velocity = new Vector2D((vx), (vy));
        radius=size;
        polygon();
        DRAWING_SCALE = radius/8;
        life = 500;
        money =1;
        COLOR = Color.RED;



    }




    //Set random points to draw asteroids
    public void polygon(){
        nPoints = (int) (Math.random() * (Maxpoints - Minpoints)) + Minpoints;

        px = new int[nPoints];
        py = new int[nPoints];


        for(int i = 0; i<nPoints;i++){

            double theta = Math.PI * 2 * (i + Math.random())/ nPoints;
            double radius = MIN_RADIUS + Math.random() * (MAX_RADIUS - MIN_RADIUS);
            px[i] = (int) (radius * Math.cos(theta));
            py[i] = (int) (radius * Math.sin(theta));

        }
    }


    //Create a asteroid at random position and with random velocity, cannot spawn within 40 pixels of the player
    public static Object makeRandomAsteroid() {
        double x = new Random().nextInt(640);
        while ( x >= Game.ship.position.x-80 && x <= Game.ship.position.x+80 ) { x = new Random().nextInt(1000);}
        double y = new Random().nextInt(480);
        while ( y >= Game.ship.position.y-80 && y <= Game.ship.position.y+80 ) { y = new Random().nextInt(1000);}

        double vx = new Random().nextInt(100);
        double vy = new Random().nextInt(100);

        Object a = new Asteroid(x, y, vx, vy,MAX_RADIUS);
        return (a);
    }



    //method to call when a asteroid is destroyed and new asteroid needs to be created
    public static Object AsteroidShrink(Object a){
        Vector2D newvelocity = new Vector2D(a.velocity);

        Vector2D randvel = new Vector2D(new Random().nextInt(100),new Random().nextInt(100));
        newvelocity.addScaled(randvel,10);
        Object b = new Asteroid(a.position.x, a.position.y, randvel.x, randvel.y,(int)a.radius/2);
        Game.alive.add(b);
        return (b);


    }


    void hit(Object o) {

        //if hit by bullet set to dead,drop money and create a new asteroid if large enough
        if (o instanceof Bullet){

            o.IsDead= true;
            if (this.radius > MIN_RADIUS) {
                AsteroidShrink(this);

            }
            MoneyDrop.dropmoney(this);


            IsDead = true;

            Game.incScore(5);
        }
    }


    public void update() {


        //Adds velocity to position
        position.addScaled(velocity , DT);
        //Wraps object around arena
        position.wrap(1000,1000);
        //if asteroid is large or medium their life will decay
        if (radius>MIN_RADIUS){
            life-=1;
        }
        //If life == 0 then they will be killed and many smaller asteroids will spawn
        if (life == 0&&this.radius > MIN_RADIUS){
            IsDead = true;
            AsteroidShrink(this);
            AsteroidShrink(this);
            AsteroidShrink(this);
            AsteroidShrink(this);


        }


    }

    public void draw(Graphics2D g) {

        //Draws using points set in polygon function
        g.setColor(new Color(255,life/5,0));
        AffineTransform at = g.getTransform();
        g.translate(position.x, position.y);

        g.scale(DRAWING_SCALE, DRAWING_SCALE);

        g.drawPolygon(px, py, nPoints);

        g.setTransform(at);






    }


    int radius() {
        return (int)radius;
    }
}