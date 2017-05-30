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
    private ActionListener musicSwitch1;
	private ActionListener musicSwitch2;
    private ItemListener music01;
    private ItemListener music02;
    private static Sequencer midiPlayer1;
	private static Sequencer midiPlayer2;
    boolean play1= true;
    boolean play2 = false;
    boolean stop = false;
    
    public ControllerSettings(ModelInterface mi, ViewSettings vs) {
        
        // reference the model to allow the controller to alter settings
        this.mi = mi;
        // reference the view to allow the controller to change things in the view
        this.vs = vs;
        
    }
    
    public void setupController () {
        Sound("src/music01.mid", 1);
        // creates the action when the back to menu button is pressed
        backToMenu = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // when the menu button is pressed, sets the current screen being viewed to the main menu screen
                mi.setCurrScreen("Menu");
            }
        };
        // adds a listener to the menu button so that the action is performed when the menu button is pressed
        vs.getBackButton().addActionListener(backToMenu);
        /**
         * Which control the music on/off. If the button is display Off, that means once you pressed it, music would be off
         * vice versa
         */
        musicSwitch1 = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            //performance after music on
				if(vs.getbtnSwitch1().getText().equals("On")){
					vs.getbtnSwitch1().setText("Off");

					Sound("src/music01.mid", 1);

				}else if(vs.getbtnSwitch1().getText().equals("Off")){
					vs.getbtnSwitch1().setText("On");

					musicstop("src/music01.mid", 1);

				}  
			}
		};

        vs.getbtnSwitch1().addActionListener(musicSwitch1);

		musicSwitch1 = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//performance after music on
				if(vs.getbtnSwitch2().getText().equals("On")){
					vs.getbtnSwitch2().setText("Off");

					Sound("src/music02.mid", 2);

				}else if(vs.getbtnSwitch2().getText().equals("Off")){
					vs.getbtnSwitch2().setText("On");

					musicstop("src/music02.mid", 2);

				}
			}
		};
		vs.getbtnSwitch2().addActionListener(musicSwitch1);

    }
    
    /**
     * Read the music file and play the music, set it repeat 100 times.
     * @param midFilename
     * @return play
     */
     public static boolean Sound(String midFilename, int num) {
    	boolean play = false;
        try {
           File midiFile = new File(midFilename);
           Sequence song = MidiSystem.getSequence(midiFile);
           if(num == 1){
			   midiPlayer1 = MidiSystem.getSequencer();
			   midiPlayer1.open();
			   midiPlayer1.setSequence(song);
			   midiPlayer1.setLoopCount(100); // repeat 0 times (play once)
			   midiPlayer1.start();
		   }else{
			   midiPlayer2 = MidiSystem.getSequencer();
			   midiPlayer2.open();
			   midiPlayer2.setSequence(song);
			   midiPlayer2.setLoopCount(100); // repeat 0 times (play once)
			   midiPlayer2.start();
		   }
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
    
     boolean musicstop(String str, int num){
		 boolean stop = false;
			if(num == 1) {
				midiPlayer1.stop();
				midiPlayer1.close();
			}else{
				midiPlayer2.stop();
				midiPlayer2.close();
			}
			stop = true;
			return stop;
    }
     

}

