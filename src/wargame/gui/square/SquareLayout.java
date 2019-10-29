package wargame.gui.square;

import wargame.gui.TileLayout;

import java.awt.*;

public class SquareLayout extends GridLayout implements TileLayout {

    private int rows;
    private int columns;
    double preferredAspectRatio;

    public SquareLayout(int rows, int columns) {
        super(rows, columns);
        this.rows = rows;
        this.columns = columns;
        this.preferredAspectRatio = ((double)columns) / rows;
    }

    @Override
    public Dimension getPreferredDimension(int width) {
        return new Dimension(width, (int) (width/preferredAspectRatio));
    }
}
