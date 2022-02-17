import acm.graphics.GRect;

import java.awt.*;

public class Brick extends GRect {

    public static final int Width = 44;
    public static final int Height = 20;
    public int bLives = 1;


    public Brick(double x, double y, Color color, int row, int bLives){
        super(x, y, Width, Height);
        this.setFillColor(color);
        this.setFilled(true);
        if(row == 10){
            this.bLives = 1;

        }else if (row == 9){
            this.bLives = 2;
        }else if (row == 8){
            this.bLives = 3;
        }else if (row == 7){
            this.bLives = 4;
        }else if (row == 6){
            this.bLives = 5;
        }else if (row == 5){
            this.bLives = 6;
        }else if (row == 4){
            this.bLives = 7;
        }else if (row == 3){
            this.bLives = 8;
        }else if (row == 2){
            this.bLives = 9;
        }else if (row == 1){
            this.bLives = 10;
        }
    }

    public void removeLife(){
        bLives -= 1;
    }





}
