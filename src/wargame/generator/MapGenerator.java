package wargame.generator;

import wargame.gameplay.Map;
import wargame.generator.factories.TileFactory;

import java.util.Random;

/**
 * @author MAILLARD Tom
 * @version 1.0
 * La classe MapGenerator est une classe client qui retourne des cartes de jeu grace aux factories de cases
 */
public class MapGenerator {

    /**
     * Cette methode statique permet de creer une carte en fonction d'une hauteur, d'une largeur, et du type de carte (Square ou Hex actuellement)
     *
     * @param mapType Le type de map a creer
     * @param height  La hauteur de la map
     * @param width   La largeur de la map
     * @return map La map generee
     */
    public static Map getMap(String mapType, int height, int width){

        Map map = new Map(width, height);
        Random r = new Random();
        TileFactory gFact = TileGenerator.getFactory("GRASS");
        TileFactory rFact = TileGenerator.getFactory("RIVER");
        TileFactory mFact = TileGenerator.getFactory("MOUNTAIN");

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                switch (r.nextInt(3)) {
                    case (0):
                        map.setMapTile(gFact.getTile(mapType, column, row), row, column);
                        break;

                    case (1):
                        map.setMapTile(rFact.getTile(mapType, column, row), row, column);
                        break;

                    case (2):
                        map.setMapTile(mFact.getTile(mapType, column, row), row, column);
                        break;
                }
            }
        }
        return map;
    }
}