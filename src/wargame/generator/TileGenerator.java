/**
 * Cette classe pilote la creation des factories creant les tuiles du jeu
 *
 * @author MAILLARD Tom
 */

package wargame.generator;

import wargame.generator.factories.GrassTileFactory;
import wargame.generator.factories.MountainTileFactory;
import wargame.generator.factories.RiverTileFactory;
import wargame.generator.factories.TileFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class TileGenerator {

    /**
     *
     * @param terrain Le type de terrain dont on veut creer la factory
     * @return Une factory dependant du type de terrain
     */
    public static TileFactory getFactory(String terrain){
        if(terrain.equals("RIVER")){
            BufferedImage image = null;

            try {
                image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/river.png"));
            } catch (IOException e) {
                System.out.println("ERROR: file not found");

            } catch (NullPointerException e) {
                System.err.println("ERROR: an unexpected error occurred");
            }

            if (image == null) {
                // si on n'a pas reussi a charger l'image, on quitte le programme
                System.exit(-1);
            } else return new RiverTileFactory(image);
        }

        if(terrain.equals("GRASS")){
            BufferedImage image = null;

            try {
                image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/grass.png"));
            } catch (IOException e) {
                System.out.println("ERROR: file not found");

            } catch (NullPointerException e) {
                System.err.println("ERROR: an unexpected error occurred");
            }

            if (image == null) {
                // si on n'a pas reussi a charger l'image, on quitte le programme
                System.exit(-1);
            } else return new GrassTileFactory(image);
        }

        if (terrain.equals("MOUNTAIN")) {
            BufferedImage image = null;

            try {
                image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/mountain.png"));
            } catch (IOException e) {
                System.out.println("ERROR: file not found");

            } catch (NullPointerException e) {
                System.err.println("ERROR: an unexpected error occurred");
            }

            if (image == null) {
                // si on n'a pas reussi a charger l'image, on quitte le programme
                System.exit(-1);
            } else return new MountainTileFactory(image);
        }

        return null;
    }
}
