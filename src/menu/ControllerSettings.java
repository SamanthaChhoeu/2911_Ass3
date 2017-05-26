package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.midi.*;

public class ControllerSettings {

    private ModelInterface mi;
    private ViewSettings vs;
    private ActionListener backToMenu;
    private ActionListener musicSwitch;
    private ItemListener music01;
    private ItemListener music02;
    private static Sequencer midiPlayer;
    boolean play = true;
    boolean stop = false;
    
    public ControllerSettings(ModelInterface mi, ViewSettings vs) {
        
        // reference the model to allow the controller to alter settings
        this.mi = mi;
        // reference the view to allow the controller to change things in the view
        this.vs = vs;
        
    }
    
    public void setupController () {
        mi.setRankList();
        //Sound("filename.mid");
        // creates the action when the back to menu button is pressed
        backToMenu = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // when the menu button is pressed, sets the current screen being viewed to the main menu screen
                mi.setCurrScreen("Menu");
            }
        };
        // adds a listener to the menu button so that the action is performed when the menu button is pressed
        vs.getBackButton().addActionListener(backToMenu);


        //There is only one background music can be selected each time, if all of musics are not be selected, then off the sound.
    }
    
    /**
     * Read the music file and play the music, set it repeat 100 times.
     * @param midFilename
     * @return play
     */
     public static boolean Sound(String midFilename) {
    	boolean play = false;
        try {
           File midiFile = new File(midFilename);
           Sequence song = MidiSystem.getSequence(midiFile);
           midiPlayer = MidiSystem.getSequencer();
           midiPlayer.open();
           midiPlayer.setSequence(song);
           midiPlayer.setLoopCount(100); // repeat 0 times (play once)
           midiPlayer.start();
           play = true;
        } catch (MidiUnavailableException e) {
           e.printStackTrace();
        } catch (InvalidMidiDataException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }
       return play;
     }
    
     boolean musicstop(String str){
       boolean stop = false;
       midiPlayer.stop();
       midiPlayer.close();
       stop = true;
       return stop;
    }

}

