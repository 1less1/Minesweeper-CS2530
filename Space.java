package minesweeper;

public class Space {

    private Boolean hasMine;
    private Integer colNum;
    private Integer rowNum;

    public Space (Integer cn, Integer rn, Boolean hm) {
        hasMine=hm;
        colNum=cn;
        rowNum=rn;

    }

    public void setMine(boolean b) {
        hasMine=b;
    }

    public boolean getMine() {
        return hasMine;
    }

    public Integer getColNum() {
        return colNum;
    }
    public Integer getRowNum() {
        return rowNum;
    }

    public String toString() {
        if(hasMine) {
            return "true";
        }
        else {
            return "false";
        }
    }
}
