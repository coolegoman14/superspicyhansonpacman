import java.awt.event.*;
import javax.swing.*;

import sun.audio.*;

import java.io.*;

public class Sound {
    public static String filename;

    public Sound(String file) {
        filename = file;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame;
        frame.setSize(300, 300);
        JButton button = new JButton("Yeet");
        frame.add(button);
        button.addActionListener(new AL());
    }

    class AL implements ActionListener {
        public final void actionPerformed(ActionEvent e) {
            play();
        }
    }

    public static void play() {
        //Plays Audio
        AudioPlayer MGP = AudioPlayer.player;
        //Gives file name of .wav files (  no mp3 :( "audionamehere.wav"  )
        AudioStream BGM;
        //Receives data from BGM in order to create new ContinuousAudioDataStream
        AudioData MD;
        //provides necessary data to audio player (MGP) to play the sound
        ContinuousAudioDataStream loop = null;

        try {
            BGM = new AudioStream(new FileInputStream(filename));
            MD = BGM.getData();
            loop = new ContinuousAudioDataStream(MD);
        } catch (IOException error) {
        }

        MGP.start(loop);
    }
}
}
//https://www.youtube.com/watch?v=VMSTTg5EEnY

/* Sound Guide
Music that plays in the beginning before the game starts: https://www.youtube.com/watch?v=HwyAwPLHqnM
Music that plays in the background when pacman does not have a power pellet: https://www.youtube.com/watch?v=lIx8TrlSV7I
Music that plays when pacman eats the normie pellets (little pellets): https://www.youtube.com/watch?v=5DE2AtHVzBU
Music that plays in the background when pacman has eaten a power pellet: https://www.youtube.com/watch?v=XgTaPJRa4PE
Sound that plays when pacman dies: https://www.youtube.com/watch?v=mD4nMp90MYQ
Sound that plays when pacman eats a ghost: https://www.youtube.com/watch?v=CwZPo2xx4iY
Sound that plays when pacman eats fruit: https://www.youtube.com/watch?v=33FKY1uIrG0
 */