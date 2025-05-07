package models;

public class Cell {
    private boolean bomb;
    private boolean userFlag;
    public Cell(){

    }
    public Cell(boolean bomb, boolean userFlag){
        this.bomb = bomb;
        this.userFlag = userFlag;
    }

    public boolean hasBomb(){
        return this.bomb;
    }

    public boolean hasUserFlag(){
        return this.userFlag;
    }

    public void setUserFlag(boolean userFlag){
        this.userFlag = userFlag;
    }
    public void setBomb(boolean bomb){
        this.bomb = bomb;
    }
}
