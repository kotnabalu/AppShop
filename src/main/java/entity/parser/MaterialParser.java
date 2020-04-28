package entity.parser;

import com.sun.org.apache.regexp.internal.RE;
import entity.enums.Material;

public class MaterialParser {
    public static Material materialParser(String materialStr){
        String material=materialStr.toUpperCase();
        if(material.equals("LEATHER")){
            return Material.LEATHER;
        }
        if(material.equals("FUR")){
            return Material.FUR;
        }
        if(material.equals("COTTON")){
            return Material.COTTON;
        }
        if(material.equals("POLYESTERS")){
            return Material.POLYESTERS;
        }
        return Material.POLYESTERS;
    }
}
