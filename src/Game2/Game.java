package Game2;

import Utilities.JEasyFrame;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int N_INITIAL_ASTEROIDS = 5;
    public static List<Object> asteroids;
    public static Object ship,arena,station, boss;
    public static Keys ctrl;
    public static List<Object> objects, objects2;
    public static List<Menu> menus;
    public static List<Object> alive = new ArrayList<>();
    public static List<Object> mapitems = new ArrayList<>();
    public static int score= 0;
    public static int level= 0;
    public static Camera cam;
    public static int enemycount = 0;
    public static Minimap map;

    //Used for shop upgrade
    public static boolean supergun = false;
    public static boolean doubleshot =false;

    //used for shop
    public static boolean pause= false;

    public Game() {
        //Creating instance of map
        map = new Minimap();
        //Creating instance of camera
        cam = new Camera(0,0);
        //Adding instance of Keys
        ctrl=new Keys();
        //Creating an array to store all objects
        objects = new ArrayList<Object>();
        //Creating array to store menus
        menus = new ArrayList<Menu>();



        //Adding essential objects
        objects.add(station = new Spacestation(500,500));
        objects.add(ship= new PlayerShip(ctrl));
        objects.add(arena = new Boundry());





    }



    public static void main(String[] args) throws Exception {
        Game game = new Game();
        View view = new View(game);
        new JEasyFrame(view, "Asteroids").addKeyListener(game.ctrl);
        // run the game
        while (true) {
            game.update();
            view.repaint();
            Thread.sleep(Constants.DELAY);
        }
    }

    //Function to increase score
    public static void incScore(int points){
        score+=points;
    }




    public void update() {
        //If game is paused only menus will update
        if (pause==true){

            for (Menu m : menus){
                m.update();
            }

        }
        //If not paused plays game
        else {
            objects2 = objects;

            alive.clear();
            //Using array indexes to solve n^2 issue
            for(int i=0;i<objects.size();i++){
                for(int x=0;x<objects2.size();x++){
                    if (i!=x){
                        objects2.get(x).collisionHandling(objects.get(i));
                    }
                }
                objects.get(i).update();
                if (!(objects.get(i).IsDead)){
                    alive.add(objects.get(i));
                }
            }
            //Centering camera on ship
            cam.update(ship);

            synchronized (Game.class) {
                objects.clear();
                objects.addAll(alive);
            }
            NextLevel();

            //updating minimap

            mapitems=alive;
        }


    }



    //Enters next level if no enemies
    public void NextLevel(){
        enemycount = 0;
        for (Object a : alive){
            if ((a instanceof Asteroid)||(a instanceof AiShip)||(a instanceof Boss)){
                enemycount+=1;
            }
        }


        if (enemycount==0){
            level+=1;
            level();
        }
    }
    //Holds information for which level holds which enemies
    public void level(){

        switch (level){
            case 0:


            case 1:
                spawnasteroid(5);
                break;
            case 2:
                spawnenemyship(3);
                break;
            case 3:
                spawnasteroid(10);
                spawnenemyship(3);
                break;
            case 4:
                spawnasteroid(15);
                spawnenemyship(5);
                break;
            case 5:
                objects.add(boss = new Boss(250,100));
                objects.add(boss = new Boss(750,100));
                break;
             case 6:
                 spawnasteroid(20);
                 spawnenemyship(10);
            case 7:
                objects.add(boss = new Boss(250,100));
                objects.add(boss = new Boss(750,100));
                spawnasteroid(20);
                spawnenemyship(10);
            case 8:
                objects.add(boss = new Boss(250,100));
                objects.add(boss = new Boss(750,100));
                objects.add(boss = new Boss(500,100));
                spawnasteroid(20);
                spawnenemyship(10);
            case 9:
                objects.add(boss = new Boss(250,100));
                objects.add(boss = new Boss(750,100));
                objects.add(boss = new Boss(500,100));
                spawnasteroid(30);
                spawnenemyship(15);
            case 10:
                objects.add(boss = new Boss(200,100));
                objects.add(boss = new Boss(400,100));
                objects.add(boss = new Boss(600,100));
                objects.add(boss = new Boss(800,100));
                spawnasteroid(100);
                spawnenemyship(20);


        }
    }
    //Spawns an amount of asteroids
    public void spawnasteroid(int amount){
        for (int i = 0;i<amount;i++){
            objects.add(Asteroid.makeRandomAsteroid());
        }
    }
    //spawns an amount of enemy ships
    public void spawnenemyship(int amount){
        for (int i = 0;i<amount;i++){
            objects.add(AiShip.makeRandomShip());
        }
    }




}
