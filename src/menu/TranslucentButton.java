package menu;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *Creates translucent buttons for menus
 */

class TranslucentButton extends JButton{

    private static final long serialVersionUID = -609729690621250930L;

    private static final Color TL = new Color(1f,1f,1f,.2f);
    private static final Color BR = new Color(0f,0f,0f,.4f);
    private static final Color ST = new Color(1f,1f,1f,.2f);
    private static final Color SB = new Color(1f,1f,1f,.1f);
    private Color ssc;
    private Color bgc;
    private int r = 8;
    public TranslucentButton(String text) {
        super(text);
    }

    /**
     *Constructor for TranslucentButton
     *@param text Text to identify button function
     *@param icon The button's icon
     */
    public TranslucentButton(String text, Icon icon) {
        super(text, icon);
    }
    /**
     *Updates the menu UI
     */
    @Override public void updateUI() {
        super.updateUI();
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setForeground(Color.WHITE);
    }
    /**
     *Re-color component
     *@param g The graahical appearance used to customize the component
     */
    @Override protected void paintComponent(Graphics g) {
        int x = 0;
        int y = 0;
        int w = getWidth();
        int h = getHeight();
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Shape area = new RoundRectangle2D.Float(x, y, w-1, h-1, r, r);
        ssc = TL;
        bgc = BR;
        ButtonModel m = getModel();
        if(m.isPressed()) {
            ssc = SB;
            bgc = ST;
        }else if(m.isRollover()) {
            ssc = ST;
            bgc = SB;
        }
        g2.setPaint(bgc);
        g2.fill(area);
        g2.setPaint(BR);
        g2.draw(area);
        g2.dispose();
        super.paintComponent(g);
    }
}