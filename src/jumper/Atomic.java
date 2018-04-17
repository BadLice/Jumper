package jumper;


import java.util.Random;
import static jumper.Jumper.app;
import static jumper.Jumper.gameOver;
import static jumper.Jumper.startAtomic;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Atomic 
{
    public int dx;
    public int dy;
    private int x;
    private int y;
    private int width;
    private int height;
    private Image img;
    private boolean draw = true;
    public Image boom;
    public long k=0;
    public boolean sound = false;
    public Sound bombSound,explosion,shot;
    public long delay=System.nanoTime();
    public boolean sparo=false;//FARE LO SPARO DEL PERSONAGGIO
   

    public Atomic() throws SlickException
    {
        Random rnd = new Random();
        x = rnd.nextInt(app.getWidth());
        y = 0;
        this.width = 20;
        this.height = 32;
        img = new Image("nuclear.png");
        boom = new Image("explosion.png");
        bombSound = new Sound("bombDrop.wav");
        explosion = new Sound("explosion.wav");
        shot = new Sound("shot.wav");
    }
    
    public void move() throws SlickException
    {
        setY(getY() + 2);
                if (getY() >= app.getHeight()-50 - getHeight()) 
                {
                   setY(0);
                   setX(Jumper.rnd.nextInt(app.getWidth()));
                   bombSound.stop();
                    if(!gameOver) gameOver=true;
                }
                
        if(Mouse.isButtonDown(0))
        {
            if(System.nanoTime()-delay>=Math.pow(10,9)*1)
            {
                shot = new Sound("shot.wav");
                shot.play();
                delay=System.nanoTime();
                
                if(Mouse.getX()>=x && Mouse.getX()<=x+width && (app.getHeight()-Mouse.getY())>=y&&(app.getHeight()-Mouse.getY())<=y+height)
                {
                    sparo = true;
                    draw = false;
                    startAtomic=System.nanoTime();
                    k= System.nanoTime();
                    dx=x;
                    dy=y;
                    y=0;
                    x = Jumper.rnd.nextInt(app.getWidth());
                    sound = false;
                    explosion = new Sound("explosion.wav");
                    explosion.play();
                    bombSound.stop();
                }
            }
            
        }
    }
    
    public void draw() throws SlickException
    {
        if(System.nanoTime()-startAtomic > 3*Math.pow(10,9))
        {
            draw=true;
        }
        if (draw)
        {
            move();
            if(draw)
            {
                if(!sound)
                {
                    try 
                    { 
                        bombSound=new Sound("bombDrop.wav");
                        bombSound.play();
                    } catch (Exception e){}
                    sound = true;
                }
                img.draw(x,y,width,height);
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
