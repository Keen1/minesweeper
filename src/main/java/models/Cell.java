package models;

//cell model
//a cell has two attributes -- whether it contains a bomb and whether a user has flagged it or not
public class Cell {

    //bomb and flag attributes
    private boolean mine;
    private boolean userFlag;

    //constructor
    public Cell(){
        this.mine = false;
        this.userFlag = false;

    }

    //constructor
    public Cell(boolean mine, boolean userFlag){
        this.mine = mine;
        this.userFlag = userFlag;
    }

    //bomb status getter
    public boolean hasMine(){
        return this.mine;
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
    public void setMine(boolean mine){
        this.mine = mine;
    }
}
