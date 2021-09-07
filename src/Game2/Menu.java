package Game2;

import java.awt.*;

//Abstract menu class to hold menus
public abstract class Menu {

    public static int width;
    public static int height;
    public static boolean IsClosed = false;




    void close(){
        IsClosed = true;
    }

    abstract void update();

    abstract void draw(Graphics2D g);
}
