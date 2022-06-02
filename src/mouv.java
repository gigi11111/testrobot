import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mouv {
    public static int couleurrobot;
    public static int direction;
    public static boolean boucle;
    public static boolean collision = false;
    public static void deplacement() throws InterruptedException {
        choixdurobot();
        choixdirection();
        System.out.println(Game.x1+" "+Game.y1);
        boucle = true;
        do {
            mouvt(couleurrobot, direction);
        }while (boucle);
        System.out.println(Game.x1+" "+Game.y1);
        Game.affichejeu();
    }
    public static void choixdurobot() throws InterruptedException {
        boucle = true;
        couleurrobot = 0;
        JFrame frame = new JFrame("frame");
        frame.setLayout(new FlowLayout());
        JLabel label = new JLabel("Choisissez le robot que vous voulez bouger");
        //Ajouter l'étiquette au frame
        frame.add(label);

        String[] items = { "rouge", "bleu", "jaune", "vert" };
        JComboBox cb = new JComboBox(items);
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Valeur: " + cb.getSelectedItem().toString());
                switch (cb.getSelectedItem().toString()){
                    case "rouge":
                        couleurrobot = 1;
                        break;
                    case "bleu" :
                        couleurrobot = 2;
                        break;
                    case "jaune" :
                        couleurrobot = 3;
                        break;
                    case "vert" :
                        couleurrobot = 4;
                        break;
                }
            }
        });
        frame.add(cb);
        JButton btn = new JButton("Cliquer pour envoyer votre choix!");
        frame.add(btn);
        btn.addActionListener(e -> {
            frame.dispose();
            if (couleurrobot == 0){
                try {
                    choixdurobot();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            boucle = false;
            System.out.println(couleurrobot);
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 250);
        frame.setVisible(true);
        while (boucle){
            Thread.sleep(500);
        }
    }
    public static void choixdirection() throws InterruptedException {
        boucle = true;
        direction = 0;
        JFrame frame = new JFrame("frame");
        frame.setLayout(new FlowLayout());
        JLabel label = new JLabel("Choisissez le robot que vous voulez bouger");
        //Ajouter l'étiquette au frame
        frame.add(label);

        String[] items = { "Nord", "Sud", "Ouest", "Est" };
        JComboBox cb = new JComboBox(items);
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Valeur: " + cb.getSelectedItem().toString());
                switch (cb.getSelectedItem().toString()){
                    case "Nord":
                        direction = 1;
                        break;
                    case "Sud" :
                        direction = 2;
                        break;
                    case "Ouest" :
                        direction = 3;
                        break;
                    case "Est" :
                        direction = 4;
                        break;
                }
            }
        });
        frame.add(cb);
        JButton btn = new JButton("Cliquer pour envoyer votre choix!");
        frame.add(btn);
        btn.addActionListener(e -> {
            frame.dispose();
            if (direction == 0){
                try {
                    choixdirection();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            boucle = false;
            System.out.println(direction);
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 250);
        frame.setVisible(true);
        while (boucle){
            Thread.sleep(500);
        }
    }
    public static void mouvt(int couleurrobot, int direction){
        switch (couleurrobot){
            case 1 :
                directionR(direction);
                break;
            case 2:
                directionB(direction);
                break;
            case 3:
                directionJ(direction);
                break;
            case 4:
                directionV(direction);
                break;
        }
    }
    public static void directionR(int direction){
        collision = false;
        switch (direction){
            case 1:
                if(Game.y1!=0) {
                    if (!robot(Game.x1, Game.y1 - 1)) {
                        int mur = Game.murs[Game.x1][Game.y1] % 2;
                        if (mur == 0) {
                            Game.y1 -= 1;
                        } else {
                            boucle = false;
                        }
                    } else {
                        boucle = false;
                    }
                }else{
                    boucle = false;
                }
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
        }
    }
    public static void directionB(int direction){

    }
    public static void directionJ(int direction){}
    public static void directionV(int direction){}
    public static boolean robot(int x, int y){
        boolean bool = false;
        if((x==Game.x1&& y ==Game.y1)||(x==Game.x2&& y ==Game.y2)||(x==Game.x3&& y ==Game.y3)||(x==Game.x4&& y ==Game.y4)){
            bool = true;
        }
        return bool;
    }
}
