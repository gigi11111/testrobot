import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;

public class DrawMyImgs extends JPanel {
    public void paint(Graphics g) {
        Image img1 = Toolkit.getDefaultToolkit().getImage("src\\p11.png");
        g.drawImage(img1, 0, 0, this);
        Image img2 = Toolkit.getDefaultToolkit().getImage("src\\p22.png");
        g.drawImage(img2, 321, 0, this);
        Image img3 = Toolkit.getDefaultToolkit().getImage("src\\p33.png");
        g.drawImage(img3, 0, 321, this);
        Image img4 = Toolkit.getDefaultToolkit().getImage("src\\p44.png");
        g.drawImage(img4, 321, 321, this);
        //g.drawString("guerrier épée", 630, 250);
    }
}