import acm.graphics.GCanvas;
import acm.graphics.GOval;

public class Ball extends GOval {


    private GCanvas screen;
    public boolean lost = false;





    private double deltaX = 1;
    private double deltaY = -1;



    public Ball(double x, double y, double size, GCanvas screen) {
        super(x, y, size, size);
        setFilled(true);
        this.screen = screen;


    }

    public void handleMove(){
        //move ball
        move(deltaX, -deltaY);
        //is ball too high?

        // check if right or left side
        if (getY() <= 0){
            //start moving down
            deltaY *= -1;
        }
        //is ball to low?
        if (getY() >= screen.getHeight()- getHeight()){
            //lose life
            lost = true;
            deltaY = -1;
            deltaX = 1;

        }
        if (getX() <= 0){
            //start moving right
            deltaX *= -1;
        }

        if (getX() >= screen.getWidth() - getWidth()){

            deltaX *= -1;
        }

        pause(5);
    }

    public void bounce(){
        deltaY *= -1;
    }
    public void bounceRight(){
        deltaX = Math.abs(deltaX);
        deltaY *= -1;
    }
    public void bounceLeft(){
        deltaX = -Math.abs(deltaX);
        deltaY *= -1;
    }
}
