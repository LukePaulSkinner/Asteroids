package Game2;

import Utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class PlayerShip extends Ship {




    // constant speed loss factor
    public static final double DRAG = 0.99;
    //shop the player can access
    public static Shop a;
    int delay = 10;









    // controller which provides an Action object in each frame
    private Controller ctrl;
    //Constructor used to create ship
    public PlayerShip(Controller ctrl) {
        this.ctrl = ctrl;
        position = new Vector2D((Constants.FRAME_WIDTH/2), (Constants.FRAME_HEIGHT/2));
        velocity = new Vector2D((0), (0));
        direction = new Vector2D((2), (2));
        radius = 8;
        COLOR=Color.cyan;
        lives = 9;
        a = new Shop(this.ctrl);
        money = 0;


    }




    void hit(Object o) {
        Action action = ctrl.action();

        //if hit lives - 1
        if (o instanceof Asteroid){
            o.IsDead=true;
            lives-=1;

        }
        if (o instanceof Bullet){
            o.IsDead = true;
            lives-=1;
        }
        if (o instanceof  Boss){
            position= new Vector2D(500,500);
            lives-=1;
        }
        //if landing on spacestation game is paused and shop is created
        if (o instanceof Spacestation){
            if (action.land==true){

                if (Game.pause==false){
                    Game.pause=true;
                    Game.menus.add(a);
                }



            }
        }

        if (lives<=3){
            System.exit(1);
        }

    }

    public void update() {
        //counts down fireing delay
        if (delay!=0){
            delay-=1;
        }

        Action action = ctrl.action();


        direction.rotate(action.turn*STEER_RATE*Constants.DT); // rotate the ship if the action says to do so
        velocity.addScaled(direction, action.thrust*MAG_ACC*Constants.DT); // move the ship forward
        velocity.mult(DRAG); //simulate drag on the ship
        position.addScaled(velocity, Constants.DT);


        position.wrap(1000,1000);



        //Checking for upgrades when firing

        if (action.shoot == true&&delay==0) {
            if (Game.supergun==false){
                action.shoot = false;
                delay=10;
            }
            if (Game.doubleshot==true){
                doubleshotfire();
            }
            else {
                mkBullet();

            }
        }


        if (action.thrust>0) {SoundManager.startThrust();}
        else SoundManager.stopThrust();


    }
    //Creates two bullets based on position direction and velocity
    void doubleshotfire() {

        Vector2D bulposl,bulvell,buldirl,bulposr,bulvelr,buldirr;
        bulposl = new Vector2D(position);
        bulvell = new Vector2D(velocity);
        buldirl = new Vector2D(direction);
        buldirl.rotate(-1*STEER_RATE*Constants.DT);
        object = new Bullet(bulposl,bulvell,buldirl);
        Game.alive.add(new Bullet(bulposl,bulvell,buldirl));

        bulposr = new Vector2D(position);
        bulvelr = new Vector2D(velocity);
        buldirr = new Vector2D(direction);
        buldirr.rotate(1*STEER_RATE*Constants.DT);
        object = new Bullet(bulposr,bulvelr,buldirr);
        Game.alive.add(new Bullet(bulposr,bulvelr,buldirr));
        SoundManager.fire();

    }





    public void draw(Graphics2D g) {
        g.setColor(COLOR);
        AffineTransform at = g.getTransform();
        g.translate(position.x, position.y);
        double rot = direction.angle() + Math.PI / 2;
        g.rotate(rot);
        g.scale(DRAWING_SCALE, DRAWING_SCALE);

        g.fillPolygon(XP, YP, XP.length);
        if (SoundManager.thrusting) {
           g.setColor(Color.red);
            g.fillPolygon(XPthrust, YPthrust, XPthrust.length);
        }
        g.setTransform(at);

    }


    int radius() {
        return (int) radius;
    }





}