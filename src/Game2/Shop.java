package Game2;



import java.awt.*;

public class Shop extends Menu{

    private Controller ctrl;

    //holds values for contents of shop
    public String[] options = {"Buy extra life","buy double shot","Supergun","exit shop","Win Game"};
    public int[] cost = {30,100,500,0,1000};
    public int selected =0;
    public boolean win = false;






    //constructor taking a controller
    public Shop(Controller ctrl) {
        width = Constants.FRAME_WIDTH;
        height = Constants.FRAME_HEIGHT;
        this.ctrl = ctrl;
        selected=0;


    }

    public void update() {
        if (win==true){
            menuwait(10000);
            System.exit(1);
        }

        Action action = ctrl.action();

        //If Right key pressed
        if (action.turn==1){

            if (selected!=options.length-1){
                selected+=1;
            }
            else {
                selected=0;
            }
            menuwait(100);



        }


        //If left key pressed
        if (action.turn==-1){


            if (selected!=0){
                selected-=1;

            }
            else {
                selected=options.length-1;
            }
            menuwait(100);



        }


        //IF spacebar pressed
        //Gets selected item and performs the appropriate action
        if (action.shoot){
            if (selected==0){
                if (Game.ship.getMoney()>=cost[selected]) {
                    Game.ship.inclives();
                    menuwait(100);
                    Game.ship.decreasemoney(cost[selected]);
                }
            }
            else if(selected==1){
                if (Game.ship.getMoney()>=cost[selected]) {
                    Game.doubleshot = true;
                    menuwait(100);
                    Game.ship.decreasemoney(cost[selected]);
                    cost[selected] = 0;
                }
            }
            else if ((selected==2)){
                if (Game.ship.getMoney()>=cost[selected]) {
                    Game.supergun = true;
                    menuwait(100);
                    Game.ship.decreasemoney(cost[selected]);
                    cost[selected] = 0;
                }
            }
            else if (selected==3){
                Game.pause=false;
                close();
            }
            else if (selected==4){
                if (Game.ship.getMoney()>=cost[selected]) {
                    win=true;
                    menuwait(100);
                    Game.ship.decreasemoney(cost[selected]);
                    cost[selected] = 0;
                }
            }
        }




    }
    //Function to wait thread.sleep does not cause issues as when a menu is active it is the only thing active
    public void menuwait(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





    public void draw(Graphics2D g) {


        g.setColor(Color.black);
        g.fillRect(0,0,width,height);
        g.setColor(Color.WHITE);

        g.drawRect(width/2-75,height/2-50,150,100);
        g.drawString(options[selected],275,height/2);
        g.drawString("Cost: "+cost[selected],275,height/3);
        g.drawString("Money: "+ Game.ship.getMoney(),550,150);
        g.drawString("Use left and right arrow keys to navigate and space to buy",width/4,100);
        g.drawString("------>",width/4*3,height/2);
        g.drawString("<------",width/4,height/2);
        if (win==true){
            g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g.setColor(Color.RED);
            g.drawString("CONGRATULATIONS YOU WIN",100,50);
        }


    }


}
