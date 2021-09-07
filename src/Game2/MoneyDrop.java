package Game2;

import Utilities.Vector2D;

import java.awt.*;
import java.util.Random;

import static Game2.Constants.DT;

public class MoneyDrop extends Object {
    //Holds position of target
    Vector2D targetdirection;

    //Constructor taking position and velocity
    public MoneyDrop(Vector2D postion,Vector2D velocity) {
        this.position = postion;
        this.velocity=velocity;
        double vx = new Random().nextInt(10);
        double vy = new Random().nextInt(10);
        velocity.x+=vx;
        velocity.y+=vy;
        COLOR = Color.green;
    }

    //Function to add money object to game based on object velocity,position and money
    public static void dropmoney(Object o){
        for (int i = 0; i<o.money;i++){
            Game.alive.add(new MoneyDrop(o.position,o.velocity));
        }
    }
    //Finds objects position and gets a direction for it
    public void findobject(Object target) {

        Vector2D a = new Vector2D(target.getpos());
        Vector2D b = new Vector2D(this.position);

        Vector2D vectDirection = b.subtract(a).normalise();

        targetdirection = vectDirection;

    }


    @Override
    void hit(Object o) {
        //if player hits money their money increases
        if (o instanceof PlayerShip){
            o.money+=1;
            IsDead = true;
        }

    }

    @Override
    void update() {
        //Sensds money flying towards player
        position.wrap(1000,1000);
        findobject(Game.ship);
        velocity.addScaled(targetdirection, -10);
        position.addScaled(velocity , DT);
        velocity.mult(0.99);
    }

    @Override
    void draw(Graphics2D g) {
        g.setColor(COLOR);
        g.drawString("$",(int)position.x,(int)position.y);

    }

    @Override
    int radius() {
        return 25;
    }
}
