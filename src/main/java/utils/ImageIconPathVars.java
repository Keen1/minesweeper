package utils;

public enum ImageIconPathVars {
    FLAG("flag", "/images/red_flag_24.png"),
    MINE("mine", "/images/mine.png");
    private String code;
    private String path;

    ImageIconPathVars(String code, String path){
        this.code = code;
        this.path = path;
    }

    public static String getPathFromCode(String code){
        for(ImageIconPathVars path : ImageIconPathVars.values()){
            if(path.code.compareToIgnoreCase(code) == 0){
                return path.path;
            }
        }
        return "";
    }
}
