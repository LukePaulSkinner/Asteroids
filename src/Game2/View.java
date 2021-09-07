package Game2;



import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;


public class View extends JComponent {
    // background colour
    public static final Color BG_COLOR = Color.black;

    Image im = Constants.MILKYWAY1;
    AffineTransform bgTransf;





    private Game game;

    public View(Game game) {
        this.game = game;

        //Setting background image
        double imWidth = im.getWidth(null);
        double imHeight = im.getHeight(null);
        double stretchx = (imWidth > Constants.FRAME_WIDTH? 1 :
                Constants.FRAME_WIDTH/imWidth);
        double stretchy = (imHeight > Constants.FRAME_HEIGHT? 1 :
                Constants.FRAME_HEIGHT/imHeight);
        bgTransf = new AffineTransform();
        bgTransf.scale(stretchx, stretchy);



    }

    @Override
    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;

        //Checking if the game is paused and if it is then the game draws menu items and if it is not paused then the game draws game items
        if (Game.pause==false) {
            g.drawImage(im, bgTransf, null);
            //Everything below the line below is affected by the camera class
            g.translate(Game.cam.getX(), Game.cam.getY());
            synchronized (Game.class) {
                for (Object object : game.objects)
                    object.draw(g);
            }
            //Everything above the line below is affected by the camera class
            g.translate(-Game.cam.getX(), -Game.cam.getY());

            g.setColor(Color.WHITE);
            //Drawing values to screen
            g.drawString("Score: "+Game.score, 150, 50);
            g.drawString("Lives: " + PlayerShip.lives / 3, 250, 50);
            g.drawString("Level: " + Game.level, 350, 50);
            g.drawString("Remaining enemies: " + (Game.enemycount ), 450, 50);
            g.drawString("Money: "+ Game.ship.getMoney(),550,150);


            Game.map.draw(g);
        }
        else {
            //If game paused then menus are shown
            synchronized (Game.class) {
                for (Menu menu: game.menus)
                    menu.draw(g);
            }
        }




    }

    @Override
    public Dimension getPreferredSize() {
        return Constants.FRAME_SIZE;
    }
}