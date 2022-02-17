import acm.graphics.GObject;
import acm.program.GraphicsProgram;
import svu.csc213.Dialog;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Breakout extends GraphicsProgram {
    /*
    dont have lives
    all bricks only take one hit

    what if runs out of lives?
    how many lives do i have left?
    how many bricks have i broken?

    how could i make power ups?
    how could i make new levels?

     */


    public int Lives = 3;
    private Color[] color = {Color.RED,Color.RED, Color.ORANGE,Color.ORANGE, Color.YELLOW,Color.YELLOW, Color.green,Color.green, Color.blue, Color.blue,};
    Ball ball;
    Paddle paddle;
    private int numBricksInRow;

    @Override
    public void init(){

        Lives = 3;
        ball = new Ball(getWidth()/2, 350, 10, this.getGCanvas());
        add(ball);

        paddle = new Paddle(230, 430, 50, 10);
        add(paddle);



        numBricksInRow = (int) (getWidth() / (Brick.Width + 5.0));

        for (int row = 0; row < 10; row++) {

            for (int col = 0; col < numBricksInRow; col++) {

                Brick brick = new Brick(10 + col * (Brick.Width + 5), 4 * Brick.Height + row * (Brick.Height + 5), color[row], row, row);
                System.out.println("adding brick");
               
                add(brick);
            }

        }
    }

    @Override
    public void run(){
        addMouseListeners();
        waitForClick();
        gameLoop();

    }

    @Override
    public void mouseMoved(MouseEvent ME){

        //make sure that the paddle doesn't go off-screen
        if ((ME.getX() < getWidth() - paddle.getWidth())&&(ME.getX() > paddle.getWidth()/2)) {
            paddle.setLocation(ME.getX()- paddle.getWidth()/2, paddle.getY() );
        }

    }

    private void gameLoop(){
        while (true){
            ball.handleMove();
            handleCollisions();
            if (ball.lost){
                handleLost();

            }
        }
    }

    private void handleCollisions(){
        GObject obj = null;

        //check if abt to hit something

        if (obj == null){
            //check top right corner
            obj = this.getElementAt(ball.getX() + ball.getWidth(), ball.getY());
        }
        if (obj == null){
            //check top left
            obj = this.getElementAt(ball.getX(), ball.getY());
        }
        if (obj == null){
            //check bottom right
            obj = this.getElementAt(ball.getX() + ball.getWidth(), ball.getY() + ball.getHeight());
        }
        if (obj == null){
            //check top left
            obj = this.getElementAt(ball.getX(), ball.getY() + ball.getHeight());
        }
        //what did we hit?
        if (obj != null){

            if ( obj instanceof Paddle){

                if (ball.getX() < (paddle.getX() + (paddle.getWidth() * .2))){
                    //did i hit the left?
                    ball.bounceLeft();
                }else if (ball.getX() > (paddle.getX() + (paddle.getWidth() * .80))){
                    //did i hit right side?
                    ball.bounceRight();
                }else
                    ball.bounce();
                    //did i hit the middle?


            }
        }


        if (obj instanceof Brick ){
            ball.bounce();
            ((Brick) obj).bLives += -1;
            //destroy brick
            if(((Brick) obj).bLives == 0){
                this.remove(obj);
            }
        }



        //if nothing is hit, we hit nothing
    }

    private void handleLost(){
       ball.lost = false;
        reset();

    }

    private void reset(){
        ball.setLocation(getWidth()/2, 350);
        Lives -= 1;



        waitForClick();


    }



    public static void main(String[] args) {
        new Breakout().start();
    }
}
