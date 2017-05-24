package menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Extension of JPanel used for every interface panel<br>
 * Gives JPanel methods for setting background image, setting an icon, and painting itself.
 *
 */
@SuppressWarnings("serial")
public class PicturePanel extends JPanel {

    private int refresher1;
    private int refresher2;
    private BufferedImage image;

    public PicturePanel() {
        try {
            image = ImageIO.read(new File("0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public JLabel getPicture(String type) {

        BufferedImage wPic = null;
        try {
            wPic = ImageIO.read(this.getClass().getResource("/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel wIcon = new JLabel(new ImageIcon(wPic));

        return wIcon;

    }

    public int getRefresher1() {
        return refresher1;
    }

    public void setRefresher1(int refresher1) {
        this.refresher1 = refresher1;
    }

    public int getRefresher2() {
        return refresher2;
    }

    public void setRefresher2(int refresher2) {
        this.refresher2 = refresher2;
    }

}