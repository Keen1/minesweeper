package utils;
/*
* path vars enum
* holds the image paths associated with their numeric symbol
*/
public enum PathVars {
    ONE(1, "/images/one.png"),
    TWO(2, "/images/two.png"),
    THREE(3, "/images/three.png"),
    FOUR(4, "/images/four.png"),
    FIVE(5, "/images/five.png"),
    SIX(6, "/images/six.png"),
    SEVEN(7, "/images/seven.png"),
    EIGHT(8, "/images/eight.png");

    private final int code;
    private final String path;

    PathVars(int code, String path){
        this.code = code;
        this.path = path;
    }

    public static String getPathFromInt(int code){
        for(PathVars path : PathVars.values()){
            if(path.code == code){
                return path.path;
            }
        }
        return "";
    }

}
