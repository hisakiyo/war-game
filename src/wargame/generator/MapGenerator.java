package wargame.generator;

import wargame.gameplay.Map;
import wargame.generator.factories.TileFactory;
import wargame.gui.TileLayout;
import wargame.gui.hex.HexLayout;
import wargame.gui.square.SquareLayout;

import javax.swing.*;
import java.util.Random;

public class MapGenerator {

    public static Map getMap(String mapType, int height, int width){

        Map map = new Map(width, height);
        Random r = new Random();
        TileFactory gFact = TileGenerator.getFactory("GRASS");
        TileFactory rFact = TileGenerator.getFactory("RIVER");

        for (int row = 0 ; row < height; row++) {
            for (int column = 0; column < width; column++) {
                switch (r.nextInt(2)) {
                    case (0):
                        map.setMapTile(gFact.getTile(mapType, column, row), row, column);
                        break;

                    case (1):
                        map.setMapTile(rFact.getTile(mapType, column, row), row, column);
                        break;
                }
            }
        }
        return map;
    }

    public static JPanel getPanelLayout(Map map, String mapType, int height, int width){
        JPanel panel;
        if( mapType.equals("HEX")){
            TileLayout hexLayout = new HexLayout(height, width);
            panel = new JPanel(hexLayout);
            panel.setPreferredSize(hexLayout.getPreferredDimension(600));
        }
        else if (mapType.equals("SQUARE")){
            TileLayout squareLayout = new SquareLayout(height, width);
            panel = new JPanel(squareLayout);
            panel.setPreferredSize(squareLayout.getPreferredDimension(600));

        }

        else return null;

        for(int i=0; i<height;i++){
            for(int j=0; j<width;j++){
                panel.add(map.getMapTile(i,j));
            }
        }
        return panel;
    }


}