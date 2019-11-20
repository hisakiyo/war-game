/**
 * Interface permettant de mettre en place une "Abstract Factory" responsable de la generation des cases du plateau de jeu
 * @author MAILLARD Tom
 * @version 1.0
 */

package wargame.generator.factories;

import wargame.gui.Tile;


public interface TileFactory {
    Tile getTile(String tileType, int col, int row);
}
