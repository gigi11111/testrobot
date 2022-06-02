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
        Image img5 = Toolkit.getDefaultToolkit().getImage("src\\coffre.png");
        g.drawImage(img5, 40*Game.x1, 40*Game.y1, this);
        g.drawImage(img5, 40*Game.x2, 40*Game.y2, this);
        g.drawImage(img5, 40*Game.x3, 40*Game.y3, this);
        g.drawImage(img5, 40*Game.x4, 40*Game.y4, this);
        //g.drawString("guerrier épée", 630, 250);
    }
}