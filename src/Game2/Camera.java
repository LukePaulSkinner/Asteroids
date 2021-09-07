package Game2;

public class Camera {



    private double x,y;
    //Creates camera with x and y coordinate
    public Camera(double x,double y){
        this.x=x;
        this.y=y;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setX(double x){
        this.x=x;
    }
    public void setY(double y){
        this.y=y;
    }

    public void update(Object a){
        //Centers camera on supplied object
        this.x= -a.position.x+Constants.FRAME_WIDTH/2;
        this.y= -a.position.y+Constants.FRAME_HEIGHT/2;






    }



}
