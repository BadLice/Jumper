
package jumper;


import java.util.Random;
import static jumper.Jumper.app;
import static jumper.Jumper.gameOver;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Block 
{
    private int x;
    private int y;
    private int width;
    private int height;
    private Image img;
    static float coinX =0;
    static int coinY=390;        

   

    public Block(int width,int height) throws SlickException
    {
        Random rnd = new Random();
        x = rnd.nextInt(width);
        //coinX+=x;
        y = rnd.nextInt((height/3))*-2;
        this.width = 10;
        this.height = 30;
        img = new Image(/*"pg.png"*/"rocket.png");
    }
    
    public void move()
    {
        setY(getY() + 4);
                if (getY() >= app.getHeight()-50 - getHeight()) 
                {
                   setY(0);
                   setX(Jumper.rnd.nextInt(app.getWidth()));
                    if(!gameOver) Jumper.punteggio++;
                }
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
     public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
    public static float getCoinX()
    {
        return coinX/4;
    }
    
}
