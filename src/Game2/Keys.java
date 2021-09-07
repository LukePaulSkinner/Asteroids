package Game2;



import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keys extends KeyAdapter implements Controller {
    Action action;
    public Keys() {
        action = new Action();
    }

    public Action action() {
        // this is defined to interact with the interface
        return action;
    }
    //Detects key pressed
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                action.thrust = 1;
                break;
            case KeyEvent.VK_LEFT:
                action.turn = -1;
                break;
            case KeyEvent.VK_RIGHT:
                action.turn = +1;
                break;
            case KeyEvent.VK_SPACE:
                action.shoot = true;
                break;
            case KeyEvent.VK_B:
                action.land = true;
                break;

        }
    }


    //Detects when key is released
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                action.thrust = 0;
                break;
            case KeyEvent.VK_LEFT:
                action.turn = 0;
                break;
            case KeyEvent.VK_RIGHT:
                action.turn = 0;
                break;
            case KeyEvent.VK_SPACE:
                action.shoot = false;
                break;
            case KeyEvent.VK_B:
                action.land = false;
                break;
        }
    }
}