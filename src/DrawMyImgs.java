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
        Image img5 = Toolkit.getDefaultToolkit().getImage("src\\robot rouge.png");
        g.drawImage(img5, 40*Game.x1 + 10, 40*Game.y1+5, this);
        Image img6 = Toolkit.getDefaultToolkit().getImage("src\\robotbleu.png");
        g.drawImage(img6, 40*Game.x2 + 10, 40*Game.y2+5, this);
        Image img7 = Toolkit.getDefaultToolkit().getImage("src\\robotjaune.png");
        g.drawImage(img7, 40*Game.x3 + 10, 40*Game.y3+5, this);
        Image img8 = Toolkit.getDefaultToolkit().getImage("src\\robotvert.png");
        g.drawImage(img8, 40*Game.x4 + 10, 40*Game.y4+5, this);
        //g.drawString("guerrier épée", 630, 250);
    }
}