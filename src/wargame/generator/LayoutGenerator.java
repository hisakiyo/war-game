package wargame.generator;

import wargame.gameplay.Map;
import wargame.gui.TileLayout;
import wargame.gui.hex.HexLayout;
import wargame.gui.square.SquareLayout;
import wargame.Main;
import wargame.gameplay.*;

import javax.swing.*;
import java.awt.*;

public class LayoutGenerator {
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
