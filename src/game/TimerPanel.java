package game;

import javax.swing.*;  
import java.awt.HeadlessException;  
import java.awt.BorderLayout;  
import java.awt.FlowLayout;  
import java.awt.Font;  
import java.awt.event.ActionListener;  
import java.awt.event.ActionEvent;  
 
public class TimerPanel extends JPanel {  
   
	private static final long serialVersionUID = 1L;

	private static final String INITIAL_LABEL_TEXT = "00:00:00";  
   
    private CountingThread thread = new CountingThread();  
   
    private long programStart = System.currentTimeMillis();  
   
    private long pauseStart = programStart;  
   
    private long pauseCount = 0;  
   
    private JLabel label = new JLabel(INITIAL_LABEL_TEXT);  
   
    private JButton startPauseButton = new JButton("Pause");  
   
    private JButton resetButton = new JButton("Reset");  
   
    private ActionListener startPauseButtonListener = new ActionListener() {  
        public void actionPerformed(ActionEvent e) {  
            if (thread.stopped) {  
                pauseCount += (System.currentTimeMillis() - pauseStart);  
                thread.stopped = false;  
                startPauseButton.setText("Pause");  
            } else {  
                pauseStart = System.currentTimeMillis();  
                thread.stopped = true;  
                startPauseButton.setText("Resume");  
            }  
        }  
    };  
   
    private ActionListener resetButtonListener = new ActionListener() {  
        public void actionPerformed(ActionEvent e) {  
            pauseStart = programStart;  
            pauseCount = 0;   
            label.setText(INITIAL_LABEL_TEXT);   
		
	    //When we click "reset", the timer should run again from 0!
            pauseCount += (System.currentTimeMillis() - pauseStart);  
            thread.stopped = false;  
            startPauseButton.setText("Pause");  
        }  
    };  
   
    public TimerPanel() throws HeadlessException {  
   
        setupLabel();  
        setupButtonsPanel();  
        startPauseButton.addActionListener(startPauseButtonListener);  
        resetButton.addActionListener(resetButtonListener);  
        thread.start(); //counting thread runs.
    }  
   
    
    private void setupButtonsPanel() {  
        JPanel panel = new JPanel(new FlowLayout());  
        panel.add(startPauseButton);  
        panel.add(resetButton);  
        add(panel, BorderLayout.SOUTH);  
    }  
   
    private void setupLabel() {  
        label.setHorizontalAlignment(SwingConstants.CENTER);  
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 30));  
        this.add(label, BorderLayout.CENTER);  
    }  
     
   
    private class CountingThread extends Thread {  
   
        public boolean stopped = false;  
   
        private CountingThread() {  
            setDaemon(true);  
        }  
   
        @Override  
        public void run() {  
            while (true) {  
                if (!stopped) {  
                    long elapsed = System.currentTimeMillis() - programStart - pauseCount;  
                    label.setText(format(elapsed));  
                } 
                try {
					sleep(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }  
        }  
   
        private String format(long elapsed) {  
            int hour, minute, second;  
   
            elapsed = elapsed / 1000;  
   
            second = (int) (elapsed % 60);  
            elapsed = elapsed / 60;  
   
            minute = (int) (elapsed % 60);  
            elapsed = elapsed / 60;  
   
            hour = (int) (elapsed % 60);  
   
            return String.format("%02d:%02d:%02d", hour, minute, second);  
        }  
    }  
}  
