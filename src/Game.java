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
        xf = ThreadLocalRandom.current().nextInt(0, 15 + 1);
        yf = ThreadLocalRandom.current().nextInt(0, 15 + 1);
        System.out.println(xf+ " "+ yf);
        if((xf == 8 || xf==9) && (yf == 8 || yf==9)){
            bool = true;
        }else{
            bool = false;
        }
        }while (bool);

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
