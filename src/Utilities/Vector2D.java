package Utilities;


// mutable 2D vectors
public final class Vector2D {


    public double x, y;

    // constructor for zero vector
    public Vector2D() {
        this.x=0;
        this.y=0;
    }

    // constructor for vector with given coordinates
    public Vector2D(double x, double y) {
        this.x =x;
        this.y = y;
    }

    // constructor that copies the argument vector
    public Vector2D(Vector2D v) {
        this.x=v.x;
        this.y=v.y;
    }

    // set coordinates
    public Vector2D set(double x, double y) {
        Vector2D a = new Vector2D(x, y);
        this.x= x;
        this.y=y;
        return a;
    }

    // set coordinates based on argument vector
    public Vector2D set(Vector2D v) {

        this.x = v.x;
        this.y=v.y;
        Vector2D a = new Vector2D(x,y);
        return a;
    }

    // compare for equality (note Object type argument)
    public boolean equals(Object o) {
        if (this==o){
            return true;
        }
        else return false;
    }

    // String for displaying vector as text
    public String toString() {
        return "X coordinate is: "+x+" Y coordinate is: "+y;
    }

    //  magnitude (= "length") of this vector
    public double mag() {
        double resl = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
        return resl;
    }

    // angle between vector and horizontal axis in radians in range [-PI,PI]
// can be calculated using Math.atan2
    public double angle() {
        return Math.atan2(this.y,this.x);
    }

    // angle between this vector and another vector in range [-PI,PI]
    public double angle(Vector2D other) {
        return (Math.atan2(this.y,this.x) - Math.atan2(other.y,other.x));

    }

    // add argument vector
    public Vector2D add(Vector2D v) {

        Vector2D a = new Vector2D(this.x+v.x, this.y+v.y);
        this.x= this.x+v.x;
        this.y= this.y+v.y;
        return a;
    }

    // add values to coordinates
    public Vector2D add(double x, double y) {
        Vector2D a = new Vector2D(this.x+x, this.y+y);
        this.x= this.x+x;
        this.y= this.y+y;
        return a;
    }

    // weighted add - surprisingly useful
    public Vector2D addScaled(Vector2D v, double fac) {

        this.x=this.x + fac * v.x;
        this.y=this.y + fac * v.y;





        return this;
    }

    // subtract argument vector
    public Vector2D subtract(Vector2D v) {
        Vector2D a = new Vector2D(this.x-v.x, this.y-v.y);
        this.x= this.x-v.x;
        this.y= this.y-v.y;
        return a;
    }

    // subtract values from coordinates
    public Vector2D subtract(double x, double y) {
        Vector2D a = new Vector2D(this.x-x, this.y-y);
        this.x=this.x-x;
        this.y=this.y-y;
        return a;
    }

    // multiply with factor
    public Vector2D mult(double fac) {
        Vector2D a = new Vector2D(this.x*fac, this.y*fac);
        this.x=this.x*fac;
        this.y=this.y*fac;
        return a;
    }

    // rotate by angle given in radians
    public Vector2D rotate(double angle) {



        double a = this.x;
        double b = this.y;
        this.x = a*Math.cos(angle) - b*Math.sin(angle);
        this.y = a*Math.sin(angle) + b*Math.cos(angle);
        Vector2D x = new Vector2D(this.x, this.y);
        return x;
    }

    // "dot product" ("scalar product") with argument vector
    public double dot(Vector2D v) {


        double sum = 0;

        sum+= v.x *this.x;
        sum+= v.y *this.y;
        return sum;


    }

    // distance to argument vector
    public double dist(Vector2D v) {
        double diff_square_sum = 0.0;

        diff_square_sum += (v.x - this.x) * (v.x - this.x);
        diff_square_sum += (v.y - this.y) * (v.y - this.y);
        return Math.sqrt(diff_square_sum);

    }

    // normalise vector so that magnitude becomes 1
    public Vector2D normalise() {
        this.mult(1/this.mag());
        return this;
    }

    // wrap-around operation, assumes w> 0 and h>0
// remember to manage negative values of the coordinates
    public Vector2D wrap(double w, double h) {
        this.x = (x+w)%w;
        this.y = (y+h)%h;
        Vector2D v = new Vector2D(this.x, this.y);
        return v;
    }





    // construct vector with given polar coordinates
    public static Vector2D polar(double angle, double mag) {

         Vector2D v = new Vector2D(Math.cos(angle), Math.sin(angle));
         return v;
    }



}