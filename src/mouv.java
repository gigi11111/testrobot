import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class mouv {
    public static int couleurrobot;
    public static int direction;
    public static boolean boucle;
    public static boolean collision = false;
    public static int mur;
    public static void deplacement() throws InterruptedException {
        System.out.println(Game.x1+" "+Game.y1);
        choixdurobot();
        choixdirection();
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
        int joueurencour = Game.joueur +1;
        JFrame frame = new JFrame("joueur "+ joueurencour);
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
        int joueurencour = Game.joueur +1;
        JFrame frame = new JFrame("joueur "+ joueurencour);
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
                        int mur =0;
                        for(int i=0;i<23;i++){
                            if((Game.x1)==Game.murhori[i][1]){
                                if((Game.y1)==Game.murhori[i][0]){
                                mur =1;
                                }
                            }
                        }
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
                if(Game.y1!=15) {
                    if (!robot(Game.x1, Game.y1 + 1)) {
                        int mur = 0;
                        for(int i=0;i<23;i++){
                            if((Game.x1)==Game.murhori[i][1]){
                                if((Game.y1+1)==Game.murhori[i][0]){
                                    mur =1;
                                }
                            }
                        }
                        if (mur == 0) {
                            Game.y1 += 1;
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
            case 3:
                if(Game.x1!=0) {
                    if (!robot(Game.x1-1, Game.y1)) {
                        int mur =0;
                        for(int i=0;i<23;i++){
                            if((Game.x1)==Game.murvert[i][1]){
                                if((Game.y1)==Game.murvert[i][0]){
                                    mur =1;
                                }
                            }
                        }
                        if (mur == 0) {
                            Game.x1 -= 1;
                        } else {
                            boucle = false;
                        }
                    } else {
                        boucle = false;
                    }
                }else {
                    boucle = false;
                }
                break;
            case 4:
                if(Game.x1!=15) {
                    if (!robot(Game.x1+1, Game.y1)) {
                        int mur =0;
                        for(int i=0;i<23;i++){
                            if((Game.x1+1)==Game.murvert[i][1]){
                                if((Game.y1)==Game.murvert[i][0]){
                                    mur =1;
                                }
                            }
                        }
                        if (mur == 0) {
                            Game.x1 += 1;
                        } else {
                            boucle = false;
                        }
                    } else {
                        boucle = false;
                    }
                }else {
                    boucle = false;
                }
                break;
        }
    }
    public static void directionB(int direction){
        switch (direction){
        case 1:
                if(Game.y2!=0) {
                    if (!robot(Game.x2, Game.y2 - 1)) {
                        int mur =0;
                        for(int i=0;i<23;i++){
                            if((Game.x2)==Game.murhori[i][1]){
                                if((Game.y2)==Game.murhori[i][0]){
                                    mur =1;
                                }
                            }
                        }
                        if (mur == 0) {
                            Game.y2 -= 1;
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
                if(Game.y2!=15) {
                    if (!robot(Game.x2, Game.y2 + 1)) {
                        int mur = 0;
                        for(int i=0;i<23;i++){
                            if((Game.x2)==Game.murhori[i][1]){
                                if((Game.y2+1)==Game.murhori[i][0]){
                                    mur =1;
                                }
                            }
                        }
                        if (mur == 0) {
                            Game.y2 += 1;
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
            case 3:
                if(Game.x2!=0) {
                    if (!robot(Game.x2-1, Game.y2)) {
                        int mur =0;
                        for(int i=0;i<23;i++){
                            if((Game.x2)==Game.murvert[i][1]){
                                if((Game.y2)==Game.murvert[i][0]){
                                    mur =1;
                                }
                            }
                        }
                        if (mur == 0) {
                            Game.x2 -= 1;
                        } else {
                            boucle = false;
                        }
                    } else {
                        boucle = false;
                    }
                }else {
                    boucle = false;
                }
                break;
            case 4:
                if(Game.x2!=15) {
                    if (!robot(Game.x2+1, Game.y2)) {
                        int mur =0;
                        for(int i=0;i<23;i++){
                            if((Game.x2+1)==Game.murvert[i][1]){
                                if((Game.y2)==Game.murvert[i][0]){
                                    mur =1;
                                }
                            }
                        }
                        if (mur == 0) {
                            Game.x2 += 1;
                        } else {
                            boucle = false;
                        }
                    } else {
                        boucle = false;
                    }
                }else {
                    boucle = false;
                }
                break;
        }
    }

    public static void directionJ(int direction){
        switch (direction){
            case 1:
                if(Game.y3!=0) {
                    if (!robot(Game.x3, Game.y3 - 1)) {
                        int mur =0;
                        for(int i=0;i<23;i++){
                            if((Game.x3)==Game.murhori[i][1]){
                                if((Game.y3)==Game.murhori[i][0]){
                                    mur =1;
                                }
                            }
                        }
                        if (mur == 0) {
                            Game.y3 -= 1;
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
                if(Game.y3!=15) {
                    if (!robot(Game.x3, Game.y3 + 1)) {
                        int mur = 0;
                        for(int i=0;i<23;i++){
                            if((Game.x3)==Game.murhori[i][1]){
                                if((Game.y3+1)==Game.murhori[i][0]){
                                    mur =1;
                                }
                            }
                        }
                        if (mur == 0) {
                            Game.y3 += 1;
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
            case 3:
                if(Game.x3!=0) {
                    if (!robot(Game.x3-1, Game.y3)) {
                        int mur =0;
                        for(int i=0;i<23;i++){
                            if((Game.x3)==Game.murvert[i][1]){
                                if((Game.y3)==Game.murvert[i][0]){
                                    mur =1;
                                }
                            }
                        }
                        if (mur == 0) {
                            Game.x3 -= 1;
                        } else {
                            boucle = false;
                        }
                    } else {
                        boucle = false;
                    }
                }else {
                    boucle = false;
                }
                break;
            case 4:
                if(Game.x3!=15) {
                    if (!robot(Game.x3+1, Game.y3)) {
                        int mur =0;
                        for(int i=0;i<23;i++){
                            if((Game.x3+1)==Game.murvert[i][1]){
                                if((Game.y3)==Game.murvert[i][0]){
                                    mur =1;
                                }
                            }
                        }
                        if (mur == 0) {
                            Game.x3 += 1;
                        } else {
                            boucle = false;
                        }
                    } else {
                        boucle = false;
                    }
                }else {
                    boucle = false;
                }
                break;
        }
    }
    public static void directionV(int direction) {
        switch (direction) {
            case 1:
                if(Game.y4!=0) {
                    if (!robot(Game.x4, Game.y4 - 1)) {
                        int mur =0;
                        for(int i=0;i<23;i++){
                            if((Game.x4)==Game.murhori[i][1]){
                                if((Game.y4)==Game.murhori[i][0]){
                                    mur =1;
                                }
                            }
                        }
                        if (mur == 0) {
                            Game.y4 -= 1;
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
                if(Game.y4!=15) {
                    if (!robot(Game.x4, Game.y4 + 1)) {
                        int mur = 0;
                        for(int i=0;i<23;i++){
                            if((Game.x4)==Game.murhori[i][1]){
                                if((Game.y4+1)==Game.murhori[i][0]){
                                    mur =1;
                                }
                            }
                        }
                        if (mur == 0) {
                            Game.y4 += 1;
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
            case 3:
                if(Game.x4!=0) {
                    if (!robot(Game.x4-1, Game.y4)) {
                        int mur =0;
                        for(int i=0;i<23;i++){
                            if((Game.x4)==Game.murvert[i][1]){
                                if((Game.y4)==Game.murvert[i][0]){
                                    mur =1;
                                }
                            }
                        }
                        if (mur == 0) {
                            Game.x4 -= 1;
                        } else {
                            boucle = false;
                        }
                    } else {
                        boucle = false;
                    }
                }else {
                    boucle = false;
                }
                break;
            case 4:
                if(Game.x4!=15) {
                    if (!robot(Game.x4+1, Game.y4)) {
                        int mur =0;
                        for(int i=0;i<23;i++){
                            if((Game.x4+1)==Game.murvert[i][1]){
                                if((Game.y4)==Game.murvert[i][0]){
                                    mur =1;
                                }
                            }
                        }
                        if (mur == 0) {
                            Game.x4 += 1;
                        } else {
                            boucle = false;
                        }
                    } else {
                        boucle = false;
                    }
                }else {
                    boucle = false;
                }
                break;
        }
    }
    public static boolean robot(int x, int y){
        boolean bool = false;
        if((x==Game.x1&& y ==Game.y1)||(x==Game.x2&& y ==Game.y2)||(x==Game.x3&& y ==Game.y3)||(x==Game.x4&& y ==Game.y4)){
            bool = true;
        }
        return bool;
    }
}
