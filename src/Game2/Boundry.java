package Game2;



import Utilities.Vector2D;

import java.awt.*;

public class Boundry extends Object {
    //Boundry class shows where the screen wraps

    public static int width =1000;
    public static int height = 1000;
    public static final Color COLOR = Color.white;





    public Boundry() {

        position = new Vector2D((0), (0));
        velocity = new Vector2D((0), (0));

    }






    void hit(Object o){
    }

    public void update() {


    }

    public void draw(Graphics2D g) {
        g.setColor(COLOR);


        //Draws square showing playing area
        g.drawRect((int)position.x,(int)position.y,1,1000);
        g.drawRect((int)position.x+width,(int)position.y,1,1000);
        g.drawRect((int)position.x,(int)position.y,1000,1);
        g.drawRect((int)position.x,(int)position.y+height,1000,1);





    }



    int radius() {
        return (int) radius;
    }





}