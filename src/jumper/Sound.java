/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumper;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author quartai
 */
public class Sound 
{
    private Clip clip;
    private AudioInputStream ais;
    private int loop=0;

    public Sound(String sound)
    {
        try {
            clip = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(new File(sound));
        } catch (Exception ex) 
        {
            ex.printStackTrace();
        }       
    }
    public void play() 
    {
      try{
       clip.open(ais);
       clip.loop(loop);
      }
      catch(Exception e)
      {}
    }
    
    public void stop()
    {
        clip.stop();
        clip.close();
    }
    
    public void setLoop(int loop) {
        this.loop = loop;
    }
}
