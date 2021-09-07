package Game2;

import Utilities.Vector2D;

import java.awt.*;


//Abstract class used for creating game objects
public abstract  class Object {
    public Vector2D velocity;
    public Vector2D position;
    public boolean IsDead;
    public double radius;
    //Needed in order to allow the user to buy lives from shop
    public static int lives;
    //How much money an object contains
    public int money;
    public  Color COLOR;


    public  Object(){
        Vector2D velocity, position;
    }

    public Vector2D getVelocity(){return velocity;}

    public Vector2D getpos(){ return position; }

    public Color getCOLOR(){
        return COLOR;
    }


    //Needed in order to allow the user to buy lives from shop
    public void inclives(){
        lives+=3;
    }
    public int getMoney(){
        return money;
    }
    public void decreasemoney(int amount){
        money-=amount;
    }







    abstract void hit(Object o);


    abstract void update();

    abstract void draw(Graphics2D g);

    abstract int radius();









    public boolean overlap(Object other){
        return this.position.dist(other.position) <= this.radius() + other.radius();
    }
    public void collisionHandling(Object other) {
        if (this.getClass() != other.getClass() && this.overlap(other)) {

            this.hit(other);
            other.hit(this);
        }
    }




}

