package minesweeper;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private ArrayList<ArrayList<Space>> board = new ArrayList<>();
    private ArrayList<Space> mineLocations = new ArrayList<>();
    private Integer numColumn;
    private final Integer numberOfMines;
    private Random Generator = new Random();
    public Board(Integer n, Integer numMines) {
        numColumn = n;
        numberOfMines = numMines;
        for(int i = 0; i < numColumn; i++) {
            ArrayList<Space> column = new ArrayList<>();
            board.add(column);
            addRow(i);
        }

        addMines();

    }

    private void addRow(Integer numRow) {
        for(int i = 0; i < numColumn; i++) {
            Integer rowNum = i;
            Integer colNum = numRow;

            Space emptySpace = new Space(colNum,rowNum,false);

            board.get(numRow).add(emptySpace);
        } }

    private void addMines() {
        Integer mineCount = numberOfMines;
        while (mineCount>0) {
            Integer columnCoordinate = Generator.nextInt(numColumn);
            Integer rowCoordinate = Generator.nextInt(numColumn);
            Space currentSpace = board.get(columnCoordinate).get(rowCoordinate);

            if(currentSpace.getMine()==false) {
                currentSpace.setMine(true);
                mineLocations.add(currentSpace);
                mineCount-=1;

            }

        }
    }

    public Integer getNumColumn() {
        return numColumn;
    }

    public ArrayList<ArrayList<Space>> getBoard() {
        return board;
    }

    public Integer scanMine(Space cp) {
        Space currentSpace = cp;
        Integer colNum = currentSpace.getColNum();
        Integer rowNum = currentSpace.getRowNum();


        Integer mineCount = 0;

        for (int i = -1; i <= 1; i++) {
            //colNum+=i;
            for (int j = -1; j <= 1; j++) {
                //rowNum+=j;

                //loop through surrounding tiles but don't fetch unless a valid position

                if (validCoordinate(colNum + i, rowNum + j)) {
                    if (board.get(colNum + i).get(rowNum + j).getMine()) {
                        mineCount += 1;

                    }
                }

            }
        }
        return mineCount;

    }

    public boolean validCoordinate(Integer cn, Integer rn) {
        Integer colNum = cn;
        Integer rowNum = rn;

        if ((colNum>=0 && colNum<numColumn) && (rowNum>=0 && rowNum<numColumn)) {
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList<Space> getMineLocations() {
        return mineLocations;
    }

}
