import java.io.*;
import javax.sound.sampled.*;

public class Sound {
    public static void main(String[] args) {
        File yeet = new File("litty.wav");
        PlaySound(yeet);
    }
    
    public static void PlaySound(File Sound)
    {
    	try {
    		Clip clip = AudioSystem.getClip();
    		clip.open(AudioSystem.getAudioInputStream(Sound));
    	    clip.start();
    	    
    	    Thread.sleep(clip.getMicrosecondLength()/10);
    	}catch(Exception e) {
    		
    	}
    }
   
    
}
//https://www.youtube.com/watch?v=3q4f6I5zi2w
//https://www.youtube.com/watch?v=QVrxiJyLTqU
