import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;


public class Game {
    private static int cas;
    public static int nbdejoueur=0;
    static JFrame f = new JFrame("carte de jeu");
    protected static int x1;
    protected static int y1;// x1 et y1 sont les coordonnées du robot rouge
    protected static int x2;
    protected static int y2;// x2 et y2 sont les coordonnées du robot bleu
    protected static int x3;
    protected static int y3;// x3 et y3 sont les coordonnées du robot jaune
    protected static int x4;
    protected static int y4;// x4 et y4 sont les coordonnées du robot vert
    protected static int x,y; //coordonnées de la cible
    public static boolean bool = false;
    public static boolean fin = true;
    public static void playGame() throws InterruptedException, IOException {
        /*nombredejoueur();
        while(nbdejoueur==0){
            Thread.sleep(500);
        }*/
        do{
            x1 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            y1 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            if((x1 == 8 || x1==7) && (y1 == 8 || y1==7)){
                bool = true;
            }else{
                bool = false;
            }
        }while (bool);
        do{
            x2 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            y2 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            if((x2 == 8 || x2==7) && (y2 == 8 || y2==7)){
                bool = true;
            }else{
                if(x2==x1 && y2==y1){
                    bool = true;
                }else {
                    bool = false;
                }
            }
        }while (bool);
        do{
            x3 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            y3 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            if((x3 == 8 || x3==7) && (y3 == 8 || y3==7)){
                bool = true;
            }else {
                if (x3 == x1 && y3 == y1) {
                    bool = true;
                } else if (x3 == x2 && y3 == y2) {
                    bool = true;
                } else {
                    bool = false;
                }
            }
        }while (bool);
        do{
            x4 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            y4 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            if((x4 == 7 || x4==8) && (y4 == 7 || y4==8)){
                bool = true;
            }else {
                if (x4 == x1 && y4 == y1) {
                    bool = true;
                } else if (x4 == x2 && y4 == y2) {
                    bool = true;
                }else if (x4 == x3 && y4 == y3) {
                bool = true;
            } else {
                    bool = false;
                }
            }
        }while (bool);
        System.out.println(" "+ x1 +" "+ y1 +" "+ x2 +" "+ y2 + " "+ x3 + " "+y3 +" "+ x4 + " "+y4);
        init(true);
        while(fin) {
            init(false);
            affichejeu();
            couleur();
            Thread.sleep(30000);
        }
        Main.fin();//Victoire
    }
    public static void nombredejoueur(){
        JFrame frame = new JFrame("frame");
        frame.setLayout(new FlowLayout());
        JLabel label = new JLabel("Choisissez le nombre de joueurs");
        //Ajouter l'étiquette au frame
        frame.add(label);

        String[] items = { "1", "2", "3", "4" };
        JComboBox cb = new JComboBox(items);
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Valeur: " + cb.getSelectedItem().toString());
                switch (cb.getSelectedItem().toString()){
                    case "1":
                        cas = 1;
                        break;
                    case "2" :
                        cas = 2;
                        break;
                    case "3" :
                        cas = 3;
                        break;
                    case "4" :
                        cas = 4;
                        break;
                }
            }
        });
        frame.add(cb);
        JButton btn = new JButton("Cliquer pour envoyer votre choix!");
        frame.add(btn);
        btn.addActionListener(e -> {
            frame.dispose();
            nbdejoueur = cas;
            if (nbdejoueur == 0){
                nombredejoueur();
            }
            System.out.println(nbdejoueur);
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 250);
        frame.setVisible(true);
    }
    public static void affichejeu(){
        f.getContentPane().add(new DrawMyImgs());
        f.setSize(670, 690);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void init(boolean bool){
       if(bool){
           P.x11 = x1;
           P.y11 = y1;
           P.x22 = x2;
           P.y22 = y2;
           P.x33 = x3;
           P.y33 = y3;
           P.x44 = x4;
           P.y44 = y4;
       }else{
           x1 = P.x11;
           y1 = P.y11;
           x2 = P.x22;
           y2 = P.y22;
           x3 = P.x33;
           y3 = P.y33;
           x4 = P.x44;
           y4 = P.y44;
       }
    }
    public static void couleur() throws IOException {
        int k = ThreadLocalRandom.current().nextInt(1, 15 + 1);
        Frame jfond = new Frame("cible à viser");
        jfond.setSize(150,150);
        JPanel panel = new JPanel();
        panel.setBounds(800, 200, 200, 200);
        BufferedImage img;
        System.out.println(k);
        switch (k){
            case 1:
                x = 1;
                y = 5;
                img = ImageIO.read(new File("src\\cible15.jpg"));
                JLabel pic = new JLabel(new ImageIcon(img));
                panel.add(pic);
                jfond.add(panel);
                break;
            case 2 :
                x = 4;
                y = 7;
                img = ImageIO.read(new File("src\\cible47.jpg"));
                JLabel pic1 = new JLabel(new ImageIcon(img));
                panel.add(pic1);
                jfond.add(panel);
                break;
            case 3 :
                x = 5;
                y = 2;
                img = ImageIO.read(new File("src\\cible52.jpg"));
                JLabel pic2 = new JLabel(new ImageIcon(img));
                panel.add(pic2);
                jfond.add(panel);
                break;
            case 4 :
                x = 9;
                y = 1;
                img = ImageIO.read(new File("src\\cible91.jpg"));
                JLabel pic3 = new JLabel(new ImageIcon(img));
                panel.add(pic3);
                jfond.add(panel);
                break;
            case 5:
                x = 9;
                y = 4;
                img = ImageIO.read(new File("src\\cible94.jpg"));
                JLabel pic5 = new JLabel(new ImageIcon(img));
                panel.add(pic5);
                jfond.add(panel);
                break;
            case 6 :
                x = 10;
                y = 6;
                img = ImageIO.read(new File("src\\cible106.jpg"));
                JLabel pic6 = new JLabel(new ImageIcon(img));
                panel.add(pic6);
                jfond.add(panel);
                break;
            case 7 :
                x = 10;
                y = 9;
                img = ImageIO.read(new File("src\\cible109.jpg"));
                JLabel pic7 = new JLabel(new ImageIcon(img));
                panel.add(pic7);
                jfond.add(panel);
                break;
            case 8:
                x = 11;
                y = 3;
                img = ImageIO.read(new File("src\\cible113.jpg"));
                JLabel pic8 = new JLabel(new ImageIcon(img));
                panel.add(pic8);
                jfond.add(panel);
                break;
            case 9:
                x = 14;
                y = 5;
                img = ImageIO.read(new File("src\\cible145.jpg"));
                JLabel pic9 = new JLabel(new ImageIcon(img));
                panel.add(pic9);
                jfond.add(panel);
                break;
            case 10 :
                x = 3;
                y = 14;
                img = ImageIO.read(new File("src\\cible314.jpg"));
                JLabel pic10 = new JLabel(new ImageIcon(img));
                panel.add(pic10);
                jfond.add(panel);
                break;
            case 11 :
                x = 6;
                y = 12;
                img = ImageIO.read(new File("src\\cible612.jpg"));
                JLabel pic11 = new JLabel(new ImageIcon(img));
                panel.add(pic11);
                jfond.add(panel);
                break;
            case 12:
                x = 6;
                y = 13;
                img = ImageIO.read(new File("src\\cible613.jpg"));
                JLabel pic12 = new JLabel(new ImageIcon(img));
                panel.add(pic12);
                jfond.add(panel);
                break;
            case 13:
                x = 9;
                y = 13;
                img = ImageIO.read(new File("src\\cible913.jpg"));
                JLabel pic13 = new JLabel(new ImageIcon(img));
                panel.add(pic13);
                jfond.add(panel);
                break;
            case 14 :
                x = 12;
                y = 11;
                img = ImageIO.read(new File("src\\cible1211.jpg"));
                JLabel pic14 = new JLabel(new ImageIcon(img));
                panel.add(pic14);
                jfond.add(panel);
                break;
            case 15:
                x = 13;
                y = 11;
                img = ImageIO.read(new File("src\\cible15.jpg"));
                JLabel pic15 = new JLabel(new ImageIcon(img));
                panel.add(pic15);
                jfond.add(panel);
                break;
        }
        jfond.setVisible(true);
    }

}
