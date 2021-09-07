package Game2;

import Utilities.Vector2D;

import java.awt.*;

public class Spacestation extends Object {

    private int timer= 25;
    //Constructor taking x and y coords
    Spacestation(double x,double y){
        position = new Vector2D((0), (0));
        velocity = new Vector2D((0), (0));
        position.x=x;
        position.y=y;
        radius = 50;

    }

    void hit(Object o) {





    }

    @Override
    void update() {


    }

    @Override
    void draw(Graphics2D g) {
        //Draws grey circle
        g.setColor(Color.gray);
        g.fillOval((int) position.x -(int) radius, (int) position.y - (int)radius, 2 * (int)radius, 2 * (int)radius);
        g.setColor(Color.black);
        g.drawString("Shop",(int)position.x-15,(int)position.y);



    }

    @Override
    int radius() {
        return (int)radius;
    }
}
