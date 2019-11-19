/**
 * TileFactory est la classe qui permet de définir les autres classes "Factory" ( GrassTileFactory par exemple).
 * Elle peut potentiellement permettre d'ajouter de nouveaux types de terrain.
 * 
 * @author Tom Maillard
 * @version 1.0
 */
 
package wargame.generator.factories;

import wargame.gui.Tile;


public abstract class TileFactory {
    public abstract Tile getTile(String tileType, int col, int row);
}
