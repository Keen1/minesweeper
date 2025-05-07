package models;

//cell model
//a cell has two attributes -- whether it contains a bomb and whether a user has flagged it or not
public class Cell {

    //bomb and flag attributes
    private boolean bomb;
    private boolean userFlag;

    //constructor
    public Cell(){

    }

    //constructor
    public Cell(boolean bomb, boolean userFlag){
        this.bomb = bomb;
        this.userFlag = userFlag;
    }

    //bomb status getter
    public boolean hasBomb(){
        return this.bomb;
    }

    //flag status getter
    public boolean hasUserFlag(){
        return this.userFlag;
    }

    //flag status setter
    public void setUserFlag(boolean userFlag){
        this.userFlag = userFlag;
    }

    //bomb status setter
    public void setBomb(boolean bomb){
        this.bomb = bomb;
    }
}
