package wargame;

import wargame.gui.Tile;
import wargame.gui.TileLayout;
import wargame.gui.hex.HexLayout;
import wargame.gui.hex.HexTile;
import wargame.gui.square.SquareLayout;
import wargame.gui.square.SquareTile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Franck Vandewiele
 *
 */
public class Main {

    public static void main(String[] args) {

        final int width = 8;
        final int height = 5;

        /*
            Préparation de 2 JPanel, un pour chaque 2 grilles
         */
        TileLayout hexLayout = new HexLayout(height, width);
        TileLayout squareLayout = new SquareLayout(height, width);

        // on fournit un HexLayout pour définir un agencement en grille hexagonale des composants dans ce JPanel
        JPanel hexPanel = new JPanel(hexLayout);

        // on fournit un SquareLayout pour définir un agencement en grille carrée des composants dans ce JPanel
        JPanel squarePanel = new JPanel(squareLayout);

        /*
            on demande aux layouts de fournir la dimension optimale pour chaque JPanel, à partir d'une largeur souhaitée
            (ici : 600 pixels)
         */
        squarePanel.setPreferredSize(squareLayout.getPreferredDimension(600));
        hexPanel.setPreferredSize(hexLayout.getPreferredDimension(600));

        /*
            Chargement du fichier image qui servira de décoration aux tuiles graphiques
         */
        BufferedImage image = null;

        try {
            image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/grass.png"));
        } catch (IOException e) {
            System.out.println("ERROR: file not found");

        } catch (NullPointerException e) {
            System.err.println("ERROR: an unexpected error occurred");
        }

        if (image == null) {
            // si on n'a pas réussi à charger l'image, on quitte le programme
            System.exit(-1);
        }

        /*
            Remplissage des JPanel avec des tuiles graphiques
         */
        for (int row = 0 ; row < height ; row++) {
            for (int column = 0 ; column < width ; column++) {
                // construction et ajout d'une HexTile au JPanel agencé en grille hexagonale
                Tile hexTile = new HexTile(row + " ; " + column, image);
                hexPanel.add(hexTile);

                // construction et ajout d'une SquareTile au JPanel agencé en grille carrée
                Tile squareTile = new SquareTile(row + " ; " + column, image);
                squarePanel.add(squareTile);
            }
        }

        // on construit le contentPane de notre JFrame, et on y ajoute les JPanel contenant nos grilles
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.add(squarePanel);
        contentPane.add(hexPanel);

        // on construit notre JFrame et on définit son contentPane
        JFrame frame = new JFrame("Deux grilles de tuiles graphiques");
        frame.setContentPane(contentPane);

        // on demande à notre JFrame de se dimensionner automatiquement en fonction de la taille de son contenu
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
