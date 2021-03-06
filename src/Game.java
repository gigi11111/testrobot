import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class Game {
    public static Cases[][] cases = new Cases[16][16];

    protected static int[][] murvert = new int[23][2];
    protected static int[][] murhori = new int[23][2];
    private static int cas;
    public static int nbdejoueur = 0;
    protected static int x1;
    protected static int y1;// x1 et y1 sont les coordonnées du robot rouge
    protected static int x2;
    protected static int y2;// x2 et y2 sont les coordonnées du robot bleu
    protected static int x3;
    protected static int y3;// x3 et y3 sont les coordonnées du robot jaune
    protected static int x4;
    protected static int y4;// x4 et y4 sont les coordonnées du robot vert
    protected static int x, y; //coordonnées de la cible
    public static int xi, yi;
    public static boolean bool = false;
    public static boolean fin = true;
    public static int couleur;//stocke la couleur de la cible
    public static int[] nbcoup = new int[4];
    protected static int deplacement;// stoke le nbre de déplacement accorder au joueur en cours
    protected static int[][] mur = new int[8][8];
    protected static int[][] murs = new int[16][16];
    public static int joueur;// addresse joueur en cours dans la liste

    public static void playGame() throws InterruptedException, IOException {
        nombredejoueur();// permet de donner le nombre de joueur et de rappeler les regles
        while (nbdejoueur == 0) {//permet de mettre le code en pause tant que les joueur n'ont pas répondu à ce qui précède
            Thread.sleep(500);
        }
        // les do{}while() qui suivent donnent un emplacement aléatoire aux 4 robots
        do {
            x1 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            y1 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            if ((x1 == 8 || x1 == 7) && (y1 == 8 || y1 == 7)) {
                bool = true;
            } else {
                bool = false;
            }
        } while (bool);
        do {
            x2 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            y2 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            if ((x2 == 8 || x2 == 7) && (y2 == 8 || y2 == 7)) {
                bool = true;
            } else {
                if (x2 == x1 && y2 == y1) {
                    bool = true;
                } else {
                    bool = false;
                }
            }
        } while (bool);
        do {
            x3 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            y3 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            if ((x3 == 8 || x3 == 7) && (y3 == 8 || y3 == 7)) {
                bool = true;
            } else {
                if (x3 == x1 && y3 == y1) {
                    bool = true;
                } else if (x3 == x2 && y3 == y2) {
                    bool = true;
                } else {
                    bool = false;
                }
            }
        } while (bool);
        do {
            x4 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            y4 = ThreadLocalRandom.current().nextInt(0, 15 + 1);
            if ((x4 == 7 || x4 == 8) && (y4 == 7 || y4 == 8)) {
                bool = true;
            } else {
                if (x4 == x1 && y4 == y1) {
                    bool = true;
                } else if (x4 == x2 && y4 == y2) {
                    bool = true;
                } else if (x4 == x3 && y4 == y3) {
                    bool = true;
                } else {
                    bool = false;
                }
            }
        } while (bool);
        System.out.println(" " + x1 + " " + y1 + " " + x2 + " " + y2 + " " + x3 + " " + y3 + " " + x4 + " " + y4);
        init(true);//enregistre la position pour relancer le jeu à l'identique
        while (fin) {
            init(false);//reinitialise le jeu selon la configuration précédente
            affichejeu();// affiche la carte du jeu
            creationmap();//création des murs
            String i3 = "";
            for(int k2 = 0; k2 <16;k2++){
                for(int k = 0; k <16;k++) {
                i3 += Game.murs[k2][k] + " ";
                }
                System.out.println(i3);
                i3="";
            }
            System.out.println(i3);
            couleur();//création de la cible
            Thread.sleep(30000);
            for (int i = 1; i <= nbdejoueur; i++) {
                int k = nombredecoups(i);
                nbcoup[i-1] = k;
            }
            joueur = 0;
            for (int i = 1; i <= nbdejoueur; i++) {
                if (nbcoup[joueur] > nbcoup[i]) {
                    joueur = i-1;
                }
            }
            deplacement = nbcoup[joueur];
            for (int m = 0; m < deplacement; m++) {
                mouv.deplacement();
            }
        }
        Main.fin();//Victoire
    }

    public static void nombredejoueur() {
        // permet de donner le nombre de joueur et de rappeler les regles
        JFrame frame = new JFrame("Annoncer le nombre de joueur");
        frame.getContentPane().setBackground(Color.orange);
        JLabel rules = new JLabel ("Une Image avec une couleur et un symbole vont apparaître \n" +
                "Cela correspond à une case sur le plateau. Vous devez mener le robot de la couleur correspondante sur cette case\n" );
        JLabel rules2 = new JLabel ("Vous pouvez utilisez les autres robots à tour de rôle. Celui qui réussit avec le nombre minimum de coups gagne!!\n" +
                "En évitant les murs de préférence");
        frame.add(rules);
        frame.add(rules2);
        frame.setLayout(new FlowLayout());
        JLabel label = new JLabel("Choisissez le nombre de joueurs");
        //Ajouter l'étiquette au frame
        frame.add(label);

        String[] items = {"1", "2", "3", "4"};
        JComboBox cb = new JComboBox(items);
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Valeur: " + cb.getSelectedItem().toString());
                switch (cb.getSelectedItem().toString()) {
                    case "1":
                        cas = 1;
                        break;
                    case "2":
                        cas = 2;
                        break;
                    case "3":
                        cas = 3;
                        break;
                    case "4":
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
            if (nbdejoueur == 0) {
                nombredejoueur();
            }
            System.out.println(nbdejoueur);
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1050, 250);
        frame.setVisible(true);
    }

    public static void affichejeu() {
        JFrame f = new JFrame("carte de jeu");
        f.getContentPane().add(new DrawMyImgs());
        f.setSize(670, 690);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void init(boolean bool) {
        //true : enregistre la position pour relancer le jeu à l'identique
        //false :reinitialise le jeu selon la configuration précédente
        if (bool) {
            P.x11 = x1;
            P.y11 = y1;
            P.x22 = x2;
            P.y22 = y2;
            P.x33 = x3;
            P.y33 = y3;
            P.x44 = x4;
            P.y44 = y4;
        } else {
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
        // permet de definir une cible de manière aléatoire
        int k = ThreadLocalRandom.current().nextInt(1, 15 + 1);
        Frame jfond = new Frame("cible à viser");
        jfond.setSize(150, 150);
        JPanel panel = new JPanel();
        panel.setBounds(800, 200, 200, 200);
        BufferedImage img;
        System.out.println(k);
        switch (k) {
            case 1:
                x = 1;
                y = 5;
                img = ImageIO.read(new File("src\\cible15.jpg"));
                JLabel pic = new JLabel(new ImageIcon(img));
                panel.add(pic);
                jfond.add(panel);
                couleur = 1;
                break;
            case 2:
                x = 4;
                y = 7;
                img = ImageIO.read(new File("src\\cible47.jpg"));
                JLabel pic1 = new JLabel(new ImageIcon(img));
                panel.add(pic1);
                jfond.add(panel);
                couleur = 2;
                break;
            case 3:
                x = 5;
                y = 2;
                img = ImageIO.read(new File("src\\cible52.jpg"));
                JLabel pic2 = new JLabel(new ImageIcon(img));
                panel.add(pic2);
                jfond.add(panel);
                couleur = 4;
                break;
            case 4:
                x = 9;
                y = 1;
                img = ImageIO.read(new File("src\\cible91.jpg"));
                JLabel pic3 = new JLabel(new ImageIcon(img));
                panel.add(pic3);
                jfond.add(panel);
                couleur = 3;
                break;
            case 5:
                x = 9;
                y = 4;
                img = ImageIO.read(new File("src\\cible94.jpg"));
                JLabel pic5 = new JLabel(new ImageIcon(img));
                panel.add(pic5);
                jfond.add(panel);
                couleur = 3;
                break;
            case 6:
                x = 10;
                y = 6;
                img = ImageIO.read(new File("src\\cible106.jpg"));
                JLabel pic6 = new JLabel(new ImageIcon(img));
                panel.add(pic6);
                jfond.add(panel);
                couleur = 4;
                break;
            case 7:
                x = 10;
                y = 9;
                img = ImageIO.read(new File("src\\cible109.jpg"));
                JLabel pic7 = new JLabel(new ImageIcon(img));
                panel.add(pic7);
                jfond.add(panel);
                couleur = 3;
                break;
            case 8:
                x = 11;
                y = 3;
                img = ImageIO.read(new File("src\\cible113.jpg"));
                JLabel pic8 = new JLabel(new ImageIcon(img));
                panel.add(pic8);
                jfond.add(panel);
                couleur = 1;
                break;
            case 9:
                x = 14;
                y = 5;
                img = ImageIO.read(new File("src\\cible145.jpg"));
                JLabel pic9 = new JLabel(new ImageIcon(img));
                panel.add(pic9);
                jfond.add(panel);
                couleur = 2;
                break;
            case 10:
                x = 3;
                y = 14;
                img = ImageIO.read(new File("src\\cible314.jpg"));
                JLabel pic10 = new JLabel(new ImageIcon(img));
                panel.add(pic10);
                jfond.add(panel);
                couleur = 1;
                break;
            case 11:
                x = 6;
                y = 12;
                img = ImageIO.read(new File("src\\cible612.jpg"));
                JLabel pic11 = new JLabel(new ImageIcon(img));
                panel.add(pic11);
                jfond.add(panel);
                couleur = 2;
                break;
            case 12:
                x = 6;
                y = 13;
                img = ImageIO.read(new File("src\\cible613.jpg"));
                JLabel pic12 = new JLabel(new ImageIcon(img));
                panel.add(pic12);
                jfond.add(panel);
                couleur = 4;
                break;
            case 13:
                x = 9;
                y = 13;
                img = ImageIO.read(new File("src\\cible913.jpg"));
                JLabel pic13 = new JLabel(new ImageIcon(img));
                panel.add(pic13);
                jfond.add(panel);
                couleur = 2;
                break;
            case 14:
                x = 12;
                y = 11;
                img = ImageIO.read(new File("src\\cible1211.jpg"));
                JLabel pic14 = new JLabel(new ImageIcon(img));
                panel.add(pic14);
                jfond.add(panel);
                couleur = 1;
                break;
            case 15:
                x = 13;
                y = 11;
                img = ImageIO.read(new File("src\\cible15.jpg"));
                JLabel pic15 = new JLabel(new ImageIcon(img));
                panel.add(pic15);
                jfond.add(panel);
                couleur = 4;
                break;
        }
        jfond.setVisible(true);
    }

    public static int nombredecoups(int i) throws InterruptedException {
        // premet de récupérer le nombre de coup annoncer par le joueur
        bool = true;
        cas = 0;
        JFrame frame = new JFrame("joueur"+i);
        frame.getContentPane().setBackground(Color.orange);
        frame.setLayout(new FlowLayout());
        JLabel label = new JLabel("Choisissez le nombre de coups que vous souhaiter faire");
        //Ajouter l'étiquette au frame
        frame.add(label);

        String[] items = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
        JComboBox cb = new JComboBox(items);
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Valeur: " + cb.getSelectedItem().toString());
                switch (cb.getSelectedItem().toString()) {
                    case "1":
                        cas = 1;
                        break;
                    case "2":
                        cas = 2;
                        break;
                    case "3":
                        cas = 3;
                        break;
                    case "4":
                        cas = 4;
                        break;
                    case "5":
                        cas = 5;
                        break;
                    case "6":
                        cas = 6;
                        break;
                    case "7":
                        cas = 7;
                        break;
                    case "8":
                        cas = 8;
                        break;
                    case "9":
                        cas = 9;
                        break;
                    case "10":
                        cas = 10;
                        break;
                    case "11":
                        cas = 11;
                        break;
                }
            }
        });
        frame.add(cb);
        JButton btn = new JButton("Choisissez le nombre de coup que vous pensez pouvoir faire");
        frame.add(btn);
        btn.addActionListener(e -> {
            frame.dispose();
            if (cas == 0) {
                try {
                    nombredecoups(i);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                frame.setVisible(false);
                bool = false;
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 200);
        frame.setVisible(true);
        do {
            Thread.sleep(500);
        } while (bool);
        return cas;
    }

    public static void creationmap() {
        for (int k = 1; k <= 4; k++) {
            planche(k);
        }
        murvert = new int [][]{{0,3},{0,12},{1,10},{2,6},{3,11},{5,1},{5,14},{6,11},{7,13},{7,9},{7,7},{7,5},{8,7},{8,9},{9,5},{9,11},{11,13},{12,7},{13,6},{13,9},{14,3},{15,11},{15,6}};
        murhori = new int [][]{{2,6},{2,9},{3,5},{3,15},{4,11},{5,14},{6,1},{6,10},{7,0},{7,4},{7,7},{7,8},{8,13},{9,4},{9,7},{9,8},{10,0},{10,10},{11,12},{12,13},{13,15},{13,6},{13,9},{15,3}};

    }

    public static void planche(int k) {
        switch (k) {
            case 1:
                planche1(); //à terme, on peux ici appliquer un random pour jongler avec d'autres planches
                break;
            case 2:
                planche2(); //à terme, on peux ici appliquer un random pour jongler avec d'autres planches
                break;
            case 3:
                planche3(); //à terme, on peux ici appliquer un random pour jongler avec d'autres planches
                break;
            case 4:
                planche4(); //à terme, on peux ici appliquer un random pour jongler avec d'autres planches
                break;
        }
    }

    public static void planche1() {
        mur = new int[][]{{0, 0, 0, 10, 0, 0, 0, 0},
                {0, 0, 200, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 11, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 100},
                {0, 10, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 10, 0, 111}};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                xi = i;
                yi = j;
                //Cases cell;
                //cell = new Cases();
                //cases[i][j] = cell;
                murs[i][j] = mur[i][j];
            }
        }
        //afficher la fenetre planche ricochet robot 1 spe verso
    }

    public static void planche2() {
        mur = new int[][]{{0, 0, 0, 0, 10, 0, 0, 0},
                {0, 0, 10, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 10, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 11, 0},
                {0, 0, 10, 1, 0, 0, 0, 0},
                {111, 0, 0, 0, 0, 10, 0, 0}};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                xi = i;
                yi = j + 8;
                //Cases cell;
                //cell = new Cases();
                //cases[i][j] = cell;
                murs[xi][yi] = mur[i][j];
            }
        }//afficher planche ricochet robt 2 spé
    }

    public static void planche3() {
        mur = new int[][]{{0, 0, 0, 0, 0, 0, 0, 111},
                {0, 0, 0, 0, 1, 10, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 200},
                {0, 100, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 10},
                {0, 0, 0, 0, 0, 0, 11, 0},
                {0, 0, 0, 10, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 10, 0}};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                xi = i + 8;
                yi = j;
                //Cases cell;
                //cell = new Cases();
                //cases[i][j] = cell;
                murs[xi][yi] = mur[i][j];
            }
        }
    }

    public static void planche4() {
        mur = new int[][]{{111, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 10, 0, 0},
                {0, 100, 0, 0, 0, 1, 0, 0},
                {0, 11, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 200, 0, 0, 0, 0},
                {0, 0, 0, 10, 0, 0, 0, 0}};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                xi = i + 8;
                yi = j + 8;
                //Cases cell;
                //cell = new Cases();
                //cases[i][j] = cell;
                murs[xi][yi] = mur[i][j];
            }
        }
    }
}
