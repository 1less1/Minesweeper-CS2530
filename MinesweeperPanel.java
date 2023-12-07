package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MinesweeperPanel extends JPanel {

    public Board board;

    private Boolean firstClick = true;

    private ArrayList<GridSquare> tiles = new ArrayList<>();

    public MinesweeperPanel(Board b) {
        board = b;

        setLayout(new GridLayout(board.getNumColumn(), board.getNumColumn()));

        for (int i = 0; i < (Math.pow(board.getNumColumn(), 2)); i++) {
            GridSquare tile = new GridSquare(i);
            tiles.add(tile);
            add(tile);

        }


    }

    public void highlightMines() {
        ArrayList<Space> mineLocations = board.getMineLocations();
        for (Space m: mineLocations) {
            Integer colNum = m.getColNum();
            Integer rowNum = m.getRowNum();
            for (int i = 0; i < (Math.pow(board.getNumColumn(), 2)); i++) {
               if ((i% board.getNumColumn()==colNum) && (i/board.getNumColumn()==rowNum)) {
                   GridSquare tile = tiles.get(i);
                   tile.setBackground(Color.RED);
                   // set text to be a bomb
                   tile.coordinateLabel.setText("\uD83D"+"\uDCA3");

               }
            }
        }
    }

    public void createStartingArea(Space cs) {
        Space spaceSelected = cs;
        //Random generator = new Random();
        while (true) {
            Integer colNum = spaceSelected.getColNum();
            Integer rowNum = spaceSelected.getRowNum();

            int x =(int)Math.floor(Math.random() * (1-(-1)+1) + (-1));
            int y = (int)Math.floor(Math.random() * (1-(-1)+1) + (-1));
            colNum = colNum + x;
            rowNum = rowNum + y;


            if (board.validCoordinate(colNum, rowNum)) {
                spaceSelected = board.getBoard().get(colNum).get(rowNum);
                if (!spaceSelected.getMine()) {
                    for (int i = 0; i < (Math.pow(board.getNumColumn(), 2)); i++) {
                        if ((i % board.getNumColumn() == colNum) && (i / board.getNumColumn() == rowNum)) {
                            GridSquare tile = tiles.get(i);
                            tile.setBackground(Color.WHITE);
                            Integer mineCount = board.scanMine(spaceSelected);
                            tile.coordinateLabel.setText("" + mineCount);
                            break;
                        }
                    }

                }
                else {
                    break;
                }

            }
        }

    }


    private class GridSquare extends JPanel implements MouseListener {
        public Integer rowNum, colNum;

        public JLabel coordinateLabel = new JLabel();;

        public GridSquare(Integer i) {
            setBackground(Color.DARK_GRAY);

            setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            rowNum = i / board.getNumColumn();
            colNum = i % board.getNumColumn();

            setLayout(new GridLayout());
            coordinateLabel.setText("");
            coordinateLabel.setFont(new Font("Roboto", Font.PLAIN, 50));
            coordinateLabel.setHorizontalAlignment(SwingConstants.CENTER);
            coordinateLabel.setVerticalAlignment(SwingConstants.CENTER);
            add(coordinateLabel);

            addMouseListener(this);

        }


        @Override
        public void mouseClicked(MouseEvent e) {

            Space spaceSelected = board.getBoard().get(colNum).get(rowNum);

            if (e.getButton()==MouseEvent.BUTTON1) {
                if (spaceSelected.getMine()) {
                    System.out.println("GAME OVER!!!!");
                    highlightMines();

                }
                else {
                    setBackground(Color.WHITE);
                    repaint();
                    Integer mineCount=board.scanMine(spaceSelected);
                    coordinateLabel.setText(""+mineCount);

                    if (firstClick) {
                        createStartingArea(spaceSelected);
                        firstClick=false;
                    }
                }
            }
            else if (e.getButton()==MouseEvent.BUTTON3) {

                setBackground(Color.RED);
                repaint();
                coordinateLabel.setText("\u2691");
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (!getBackground().equals(Color.RED) && !getBackground().equals(Color.WHITE)){
                setBackground(Color.LIGHT_GRAY);
                //repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!getBackground().equals(Color.RED) && !getBackground().equals(Color.WHITE)){
                setBackground(Color.DARK_GRAY);
                //repaint();
            }
        }
    }
}