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
    public Clip getClip() {
    	return clip;
    }
    public boolean isRunning() {
    	return clip.isRunning();
    }
    public boolean isActive() {
    	return clip.isActive();
    }
    public void play()
    {
    	try {
    		Clip clip = AudioSystem.getClip();
    		clip.open(AudioSystem.getAudioInputStream(yeet));
    		clip.stop();
    		clip.setFramePosition(0);
		    clip.start();
		    Thread.sleep(clip.getMicrosecondLength()/10);
    	}catch(Exception e) {
    		
    	}
    	if(clip.isRunning()) {
    		clip.stop();
    	}
    }
    public void loop() {
    	clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
    	clip.stop();
    }
}
//https://www.youtube.com/watch?v=3q4f6I5zi2w
//https://www.youtube.com/watch?v=QVrxiJyLTqU
//https://stackoverflow.com/questions/11919009/using-javax-sound-sampled-clip-to-play-loop-and-stop-mutiple-sounds-in-a-game
