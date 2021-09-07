package Game2;

import Utilities.Vector2D;


//Abstract class
public  abstract class Ship extends Object {


    // rotation velocity in radians per second
    public static final double STEER_RATE = 2* Math.PI;

    // acceleration when thrust is applied
    public static final double MAG_ACC = 200;





    public double DRAWING_SCALE = 1;
    public  Vector2D direction;
    //Holds values for drawing ship
    public int[] XP = {0, 8, 0, -8};
    public int[] YP = {0, 8, -8, 8};

    public int[] XPthrust = {0, 5, 0, -5};
    public int[] YPthrust = {0, 5, 10, 5};

    public Object object = null; //Bullet

    //Function for creating bullet
    void mkBullet() {

        Vector2D bulpos,bulvel,buldir;
        bulpos = new Vector2D(position);
        bulvel = new Vector2D(velocity);
        buldir = new Vector2D(direction);
        object = new Bullet(bulpos,bulvel,buldir);
        Game.alive.add(new Bullet(bulpos,bulvel,buldir));
        SoundManager.fire();

    }



}
