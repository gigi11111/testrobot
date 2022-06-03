import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main
{
    public static Game game;
    public static void main(String[] args) throws InterruptedException, IOException {
        //test();
        game = new Game();
        Game.playGame();
    }
    public static void test() throws InterruptedException {
        //fonction test pour tester l'environnement de  l'utilisateur : une fenetre dois s'afficher pour ensuite disparaître
        JFrame frame = new JFrame();
        JButton btn = new JButton("Bienvenu dans ce RobotRicochet, cliquer si vous devez nous quitter");
        frame.setContentPane(btn);
        btn.addActionListener(e -> {
            frame.dispose();
            System.out.println(" au revoir et a une prochaine");
            System.exit(1);
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(30000, 3000));
        frame.pack();
        frame.setVisible(true);
        Thread.sleep(5000);
        frame.setVisible(false);
    }

    public static void fin(){
        //permet de sortir
        System.exit(1);
    }
    public static void victoire(){
        //permettra de verrifier la conditon de victoire de sortir
        System.out.println("bien joué");
        fin();
    }
}
