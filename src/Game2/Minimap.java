package Game2;

import Utilities.Vector2D;

import java.awt.*;

public class Minimap {







    public void draw(Graphics2D g) {
        //draws white box in upper left corner
        g.setColor(Color.white);

        g.drawRect(0, 0, 100, 100);

        //For item in map items gets their position and divides it by 10 and draws it

        for (Object o : Game.mapitems){

            g.setColor(o.getCOLOR());

            Vector2D opos = new Vector2D(o.getpos());
            if (!(o instanceof Boundry)) {
                g.drawOval((int) opos.x / 10, (int) opos.y / 10, 5, 5);
            }


        }
    }
}
