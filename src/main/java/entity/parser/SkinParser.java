package entity.parser;

import entity.enums.SkinType;

public class SkinParser {
    public static SkinType skinTypeParser (String skinTypeStr){
        String skinType=skinTypeStr.toUpperCase();
        if(skinType.equals("NATURAL")){
            return SkinType.NATURAL;
        }
        if(skinType.equals("ARTIFICIAL")){
            return SkinType.ARTIFICIAL;
        }
        return SkinType.ARTIFICIAL;
    }
}
