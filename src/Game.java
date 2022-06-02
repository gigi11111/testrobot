import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;


public class Game {
    private static int cas;
    public static int nbdejoueur=0;
    static JFrame f = new JFrame("carte de jeu");
    protected static int x1;
    protected static int y1;// xf et yf sont les coordonnées du robot 1 protected static int x1;
    protected static int x2;
    protected static int y2;// xf et yf sont les coordonnées du robot 1 protected static int x1;
    protected static int x3;
    protected static int y3;// xf et yf sont les coordonnées du robot 1 protected static int x1;
    protected static int x4;
    protected static int y4;// xf et yf sont les coordonnées du robot 1
    public static boolean bool = false;
    public static void playGame() throws InterruptedException {
        /*nombredejoueur();
        while(nbdejoueur==0){
            Thread.sleep(500);
        }*/
        do{
            x1 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            y1 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            if((x1 == 8 || x1==9) && (y1 == 8 || y1==9)){
                bool = true;
            }else{
                bool = false;
            }
        }while (bool);
        do{
            x2 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            y2 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            if((x2 == 8 || x2==9) && (y2 == 8 || y2==9)){
                bool = true;
            }else{
                bool = false;
            }
        }while (bool);
        do{
            x3 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            y3 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            if((x3 == 8 || x3==9) && (y3 == 8 || y3==9)){
                bool = true;
            }else{
                bool = false;
            }
        }while (bool);
        do{
            x4 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            y4 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            if((x4 == 8 || x4==9) && (y4 == 8 || y4==9)){
                bool = true;
            }else{
                bool = false;
            }
        }while (bool);
        System.out.println(x1+" "+y1);
        System.out.println(x2+" "+y2);
        affichejeu();
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

}
