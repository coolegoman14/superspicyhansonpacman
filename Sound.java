import java.io.*;
import javax.sound.sampled.*;

public class Sound {
	private File yeet;
	private Clip clip;
//    public static void main(String[] args) {
//        File yeet = new File("litty.wav");
//        PlaySound(yeet);
//    }
    public Sound(String s) {
    	yeet = new File(s);
    	try {
			clip = AudioSystem.getClip();
			try {
				AudioInputStream yayeet = AudioSystem.getAudioInputStream(yeet);
				clip.open(yayeet);
			} catch (UnsupportedAudioFileException e) {} 
			catch (IOException e) {}
		} catch (LineUnavailableException e) {
			
		}
    }
    public void PlaySound()
    {
    	try {
    		Clip clip = AudioSystem.getClip();
    		clip.open(AudioSystem.getAudioInputStream(yeet));
    	    clip.start();
    	    
    	    Thread.sleep(clip.getMicrosecondLength()/10);
    	}catch(Exception e) {
    		
    	}
    }
    public void loop() {
    	clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
   
    
}
//https://www.youtube.com/watch?v=3q4f6I5zi2w
//https://www.youtube.com/watch?v=QVrxiJyLTqU
//https://stackoverflow.com/questions/11919009/using-javax-sound-sampled-clip-to-play-loop-and-stop-mutiple-sounds-in-a-game
