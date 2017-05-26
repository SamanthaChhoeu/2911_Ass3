package menu;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
    private String str;
	 private JLabel labelx;
    
    public ViewGuide(ModelInterface mi) {    
        this.mi = mi;
        setupGuide(); 
        
    }
    public void setGuide(){
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader("guide.txt"));
            int i = 0;
            while (sc.hasNext()) {
                i++;
                String line = sc.nextLine();
                //String[] l = line.split("//");
                str = str + line;
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
      
  //    JTextField(text);
      String Str="abcdefg\nhijklmn";
      labelx = new JLabel(str,SwingConstants.CENTER);
   //  labelx.setFont(str);
      this.add(labelx);
      labelx.setBounds(50,50,500,500);
      
      // creates a new button to go back to the main menu
      back = new JButton("Back");
      back.setBounds(1025, 600, 200, 50);
      this.add(back);
      
      
      
      
  	}
  	
  /*	JTextField(Document doc, String text, int columns) 
	}*/

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