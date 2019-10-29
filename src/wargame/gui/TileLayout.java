package wargame.gui;

import java.awt.*;

/**
 * Common interface of Tile Layout Managers
 * This software is released under the GNU GPLv3 license
 * @author Franck Vandewiele
 *
 */
public interface TileLayout extends LayoutManager {
    /**
     * @param width desired width for a container using this layout manager
     * @return Aspect-ratio correct corresponding dimension
     */
    public Dimension getPreferredDimension(int width);
}
