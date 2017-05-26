package menu;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class ViewGuide extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
    private ModelInterface mi;
    private JButton back;
    private String str = "";
	private ArrayList<JLabel> guide;
	private JLabel g;
    
    public ViewGuide(ModelInterface mi) {
    	this.guide = new ArrayList<JLabel>();
        this.mi = mi;
        setupGuide(); 
        
    }
    public void setGuide(){
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader("src/Guide.txt"));
            int i = 0;
            while (sc.hasNext()) {
                str = sc.nextLine();
                guide.add(new JLabel(str, JLabel.CENTER));
                i++;
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Issue with reading guide file");
        } finally {
            if (sc != null) sc.close();
        }
        
    }
  	private void setupGuide() {
  		setGuide();
      // sets the size of the window
      this.setSize(mi.getDimensions());
      // sets what happens when the user closes the window
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // sets the layout to a manual layout to enable full customisation of where the buttons are
      this.setLayout(null);
      // sets the window to show in the middle of the screen
      this.setLocationRelativeTo(null);
      
      int c = 0;
      for (c = 0; c < guide.size(); c++){
    	  guide.get(c).setBounds(125, 25 * (c+1), 1000, 50);
    	  guide.get(c).setFont(new Font("Default", Font.PLAIN, 20));
    	  //c++;
    	  //System.out.println(guide.get(c).getText() + c);
    	  this.add(guide.get(c));
      }
      //g = new JLabel("str");
      //g.setBounds(100, 200, 200, 50);
      //this.add(g);
     
      // creates a new button to go back to the main menu
      back = new JButton("Back");
      back.setBounds(1025, 600, 200, 50);
      this.add(back);
  
  	}
  	
 

	public JButton getBackButton() {
        return back;
    }
	@Override
	public void update(Observable arg0, Object arg) {
		// TODO Auto-generated method stub
		 String command = ((String) arg);
	        
	        if (command.equals("ChangeScreenGuide")) {
	                
	            this.setVisible(true);

	        } else {
	            
	            this.setVisible(false);
	            
	        }
	        
	    }
		

}
