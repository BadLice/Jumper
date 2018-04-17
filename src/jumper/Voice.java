package jumper;
        
import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Voice
{
    private String name;
    private float x;
    private float y;
    private boolean highlight=false;
    private boolean selected=false;
    
    public Voice(String name,float x,float y)
    {
        this.name=name;  
        this.x=x;
        this.y=y;
    }
    
    public void drawVoice(AppGameContainer app,Graphics g,Color color)
    {
        if(highlight)
        g.setColor(color);        
        g.drawString(name,x ,y);
        g.setColor(Color.white);
        
        updateVoice(app);
    }
    
    public void updateVoice(AppGameContainer app)
    {
        if(Mouse.getX()>=x&&Mouse.getX()<=x+(10*name.length())&&(app.getHeight()-Mouse.getY())>=y&&(app.getHeight()-Mouse.getY())<=y+25)
            {
               highlight=true;
               if(Mouse.isButtonDown(0))
               {
                   selected = true;
                   
               }
               else
                  selected=false;
            }
            else
                highlight=false;
    }
        

    public boolean isHighlight() 
    {
        return highlight;
    }


    public boolean isSelected() 
    {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    

}
