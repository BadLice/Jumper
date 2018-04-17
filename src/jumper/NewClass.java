
package jumper;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class NewClass 
{
    public static void main(String args[]) throws MalformedURLException, UnsupportedAudioFileException, LineUnavailableException, IOException
    {
        Sound s = new Sound("bullet.wav");
        s.play();
        System.out.print("fdbdf");
        while(true);
    }
}
