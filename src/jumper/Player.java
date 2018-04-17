
package jumper;

import static jumper.Jumper.app;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player 
{
    static public Image pgL, pgR;
    private int pgX;
    private int pgY;
    private int pgWidth;
    private int pgHeight;
    private Input in;
    private boolean jump = false;
    private int dist = -3;
    private boolean direzione=false;
    private Sound sound;
    private boolean soundb=false;
    static public String stringImgL,stringImgR;
    
    public Player(String imgL, String imgR, Input in) throws SlickException
    {
        stringImgL = imgL;
        stringImgR = imgR;
        pgL = new Image(imgL);
        pgR = new Image(imgR);
        pgX =app.getWidth()/2;
        pgY =app.getHeight()-100;
        pgWidth = 45;
        pgHeight = 50;
        this.in = in;
        sound = new Sound("jump.wav");
    }
    
    public void draw()
    {
        move();
        if(direzione) 
            pgL.draw(pgX,pgY,pgWidth,pgHeight);
        else
            pgR.draw(pgX,pgY,pgWidth,pgHeight);
    }
    
    public void move()
        {
            if((in.isKeyDown(Input.KEY_RIGHT)||in.isKeyDown(Input.KEY_D))&&pgX<app.getWidth()-pgWidth)
            {
                pgX += 5;
                direzione=false;
            }
            if((in.isKeyDown(Input.KEY_LEFT)|| in.isKeyDown(Input.KEY_A))&&pgX>=0)
            {
                pgX -=5;
                direzione=true;
            }
            if(in.isKeyDown(Input.KEY_ESCAPE))
                System.exit(0);
            jump();
        }
        
    public void jump()
    {
             if(in.isKeyDown(Input.KEY_UP) || in.isKeyDown(Input.KEY_W))
            {
                jump = true;
                if(!soundb)
                {
                    sound = new Sound("jump.wav");
                    sound.play();
                    soundb=true;
                }
                
            }
            if (jump)
            {
                pgY+=dist;
                if(pgY <= app.getHeight()-150) 
                {
                    dist *=-1;
                }
                if(pgY>=app.getHeight()-100)
                {
                    pgY =app.getHeight()-100;
                    jump=false;
                    soundb=false;
                    dist*=-1;
                }
            }
        }


    public int getPgX() {
        return pgX;
    }

    public void setPgX(int pgX) {
        this.pgX = pgX;
    }

    public int getPgY() {
        return pgY;
    }

    public void setPgY(int pgY) {
        this.pgY = pgY;
    }

    public int getPgWidth() {
        return pgWidth;
    }

    public void setPgWidth(int pgWidth) {
        this.pgWidth = pgWidth;
    }

    public int getPgHeight() {
        return pgHeight;
    }

    public void setPgHeight(int pgHeight) {
        this.pgHeight = pgHeight;
    }

    public Input getIn() {
        return in;
    }

    public void setIn(Input in) {
        this.in = in;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }
        
        
}
