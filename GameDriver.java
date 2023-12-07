package minesweeper;

import javax.swing.*;

public class GameDriver {
    public static void main(String args[]) {
        JFrame jf = new JFrame("Minesweeper");
        jf.add(new MinesweeperPanel(new Board(10,7)));
        jf.setSize(600, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
