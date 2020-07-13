package entity.parser;

import entity.enums.Color;

public class ColorParser {
        public static Color colorParser(String colorStr){
            String color=colorStr.toUpperCase();
            if(color.equalsIgnoreCase("RED")){
                return Color.RED;
            }
            if(color.equalsIgnoreCase("GREEN")){
                return Color.GREEN;
            }
            if(color.equalsIgnoreCase("BLUE")){
                return Color.BLUE;
            }
            if(color.equalsIgnoreCase("BLACK")){
                return Color.BLACK;
            }
            if(color.equalsIgnoreCase("YELLOW")){
                return Color.YELLOW;
            }
            if(color.equalsIgnoreCase("WHITE")){
                return Color.WHITE;
            }
            return Color.WHITE;
        }
}
