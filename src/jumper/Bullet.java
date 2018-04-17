
package jumper;

import java.util.Random;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import static jumper.Jumper.app;
import static jumper.Jumper.verso;

public class Bullet 
{
    private int x;
    private int y;
    private int width;
    private int height;
    private Image img;
    public int vel=4;
    private Sound sound;

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }
    
   public Bullet(int vel) throws SlickException
    {
        x = 0;
        y = app.getHeight()-50-25;
        this.width = 20;
        this.height = 10;
        img = new Image("bulletR.png");
        this.vel = vel;
        sound = new Sound("bullet.wav");
    }
   
    public void bulletMove() throws SlickException
        {
           
            if(verso)//da sinistra a destra
            {
                setX(getX()+vel);
                    if(!Jumper.soundb&&!Jumper.gameOver)
                    {
                        if(Jumper.countdown<=0)
                        {
                            setSound(new Sound("bullet.wav"));
                            getSound().play();
                            Jumper.soundb=true;
                        }
                            
                        
                    }   
                if(getX()>=app.getWidth())
                {
                    Jumper.soundb=false;
                    if(!Jumper.gameOver)Jumper.punteggio++;
                    Jumper.start = System.nanoTime();
                    verso = Jumper.rnd.nextBoolean();
                    if(verso)
                    {
                        setX(-30);
                       setImg(new Image("bulletR.png"));
                    }
                    else
                    {
                       setX(app.getWidth());
                        setImg(new Image("bulletL.png"));
                        
                    }
                }
            }
            else//da destra a sinistra
            {
                setX(getX()-vel);
                
                 if(!Jumper.soundb&&!Jumper.gameOver)
                    {
                        if(Jumper.countdown<=0)
                        {
                            setSound(new Sound("bullet.wav"));
                            getSound().play();
                            Jumper.soundb=true;
                        }
                    }   
                     
                if(getX()<=0)
                {
                    Jumper.soundb=false;
                    if(!Jumper.gameOver) Jumper.punteggio++;
                    Jumper.start = System.nanoTime();
                    verso = Jumper.rnd.nextBoolean();
                    if(verso)
                    {
                        setX(-30);
                       setImg(new Image("bulletR.png"));
                    }
                    else
                    {
                        setX(app.getWidth());
                       setImg(new Image("bulletL.png"));
                    }
                }
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
    
    
}
