package gametank;

import javax.swing.JFrame;


public class GameTank {


public static void main(String[] args) {

        JFrame Game = new JFrame("Tank");
        Game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Field f = new Field();
        Game.add(f);
        Game.pack();
        Game.setVisible(true);
        f.time.start();
        f.setFocusable(true);

}

}

