
package jumper;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Menu 
{
    private Voice[] voices;
    private Color color;
    private int selected=-1;
    
    public Menu(String[] names,Color color,AppGameContainer app)
    {
        this.color = color;
        
        voices = new Voice[names.length];
        
        for(int i=0;i<names.length;i++)
        {
           voices[i]=new Voice(names[i],(app.getWidth()/2)-(9*(names[i].length()/2)),((app.getHeight()/names.length)*i)+30);
        }
            
    }
    public void drawMenu(Graphics g,AppGameContainer app)
        {
            resetSelected();
            for(Voice v:voices)
            {
                v.drawVoice(app,g, color);
            }
            
            updateMenu();

        }
    
    public void updateMenu()
    {
        int i=0;
        boolean done = false;
        for(Voice v:voices)
        {
            
            if(v.isSelected())
            {
                selected=i;
                done=true;
            }
            i++;
        }
        if(!done)
            selected = -1;
    }

    public int getSelected()
    {
        return selected;
    }
    public void resetSelected()
    {
        for(Voice v:voices) v.setSelected(false);
        selected = -1;
        
    }
}

