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
        
        musicSwitch = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            //performance after music on
				if(vs.getbtnSwitch().getText().equals("Off")){
					vs.getbtnSwitch().setText("On");
					if(play1){
						musicstop("src/music01.mid");
						play1 = false;
						play2 = false;
						stop = true;
						vs.getbtnMusic1().setSelected(false);
					}else if(play2){
						musicstop("src/music02.mid");
						play2 = false;
						stop = true;
						play1 = false;
						vs.getbtnMusic2().setSelected(false);
					}
		 
				}else if(vs.getbtnSwitch().getText().equals("On")){
					vs.getbtnSwitch().setText("Off");
					if(stop){
						play1 = true;
						play2 = false;
						stop = false;
						vs.getbtnMusic1().setSelected(true);
						vs.getbtnMusic2().setSelected(false);
					}
				}  
			}
		};
        vs.getbtnSwitch().addActionListener(musicSwitch);
        
       //There is only one background music can be selected each time, if all of musics are not be selected, then off the sound.
       music01 = new ItemListener(){

			@Override
			/**
			 * Check any change made by checkbox, if the box is selected, then first music would be played
			 * if the box is not selected, then no music would be played
			 */
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				Object source = e.getItemSelectable();
				 if ( e.getStateChange() == ItemEvent.DESELECTED ) {
					 //...make a note of it...
					 vs.getbtnMusic2().setSelected( false );
					 vs.getbtnMusic1().setSelected( false );
					 vs.getbtnSwitch().setText("On");
					 musicstop("src/music01.mid");
					 stop = true;
					 play1 = false;
				  }
				 else if (e.getStateChange() != ItemEvent.DESELECTED) {
					 //...make a note of it...
					 vs.getbtnMusic2().setSelected(false);
					 vs.getbtnMusic1().setSelected(true);
					 vs.getbtnSwitch().setText("Off");
					 Sound("src/music01.mid");
					 play1 = true;
					 stop = false;
				}    
			}
		};     	
		vs.getbtnMusic1().addItemListener(music01);
        //Same as music01 	
		music02 = new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				  Object source = e.getItemSelectable();
				  if (e.getStateChange() == ItemEvent.DESELECTED){
					 //...make a note of it...
					 stop = true;
					 play2 = false;
					 vs.getbtnMusic2().setSelected( false );
					 vs.getbtnMusic1().setSelected( false );
					 vs.getbtnSwitch().setText("On");
					 musicstop("src/music02.mid");

				 }
				 else if (e.getStateChange() != ItemEvent.DESELECTED) {
					 //...make a note of it...
					vs.getbtnMusic2().setSelected(true);
					vs.getbtnMusic1().setSelected(false);
					vs.getbtnSwitch().setText("Off");
					Sound("src/music02.mid");
					play2 = true;
					stop = false;
				 }
			 }
		};     	
		vs.getbtnMusic2().addItemListener(music02);   
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

