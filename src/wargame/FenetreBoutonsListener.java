package wargame;

import wargame.gui.Tile;
import wargame.gui.TileLayout;
import wargame.gui.hex.HexLayout;
import wargame.gui.hex.HexTile;
import wargame.gui.square.SquareLayout;
import wargame.gui.square.SquareTile;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.FlowLayout;
import wargame.generator.*;
import wargame.generator.factories.*;
import java.util.Random;

/**
 * @author Franck Vandewiele
 *
 */
public class FenetreBoutonsListener extends JFrame implements ActionListener{
	private int width = 8;
    private int height = 5;
    private JFrame frame;
	private JButton bouton;
	private JButton bouton2;
    private JTextField textField;
    private JLabel label;
		
	public FenetreBoutonsListener(){
		super();
		build();//On initialise notre fenêtre
	}
	
	private void build(){
		setTitle("Selection type "); //On donne un titre à l'application
		setSize(250,140); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setResizable(true); //On permet le redimensionnement
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		setContentPane(buildContentPane());
	}
	
	private JPanel buildContentPane(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		bouton = new JButton("Hex !");
		bouton.addActionListener(this);
				
		bouton2 = new JButton("Square !");
		bouton2.addActionListener(this);

        label = new JLabel("Nom du joueur:");
		textField = new JTextField();
		textField.setColumns(10); //On lui donne un nombre de colonnes à afficher
        textField.addActionListener(this);
        panel.add(label);
        panel.add(textField);
        panel.add(bouton);
        panel.add(bouton2);
		return panel;
	}
	
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        TileFactory gFact = TileGenerator.getFactory("GRASS");
        TileFactory wFact = TileGenerator.getFactory("RIVER");
        Tile tile;
        Random r = new Random();
        if(source == bouton){
            TileLayout hexLayout = new HexLayout(height, width);
            JPanel hexPanel = new JPanel(hexLayout);
            hexPanel.setPreferredSize(hexLayout.getPreferredDimension(600));
            for (int row = 0 ; row < height ; row++) {
                for (int column = 0 ; column < width ; column++) {
                switch(r.nextInt(2)){
                    case(0):
                        tile = gFact.getTile("HEX",column, row);
                        hexPanel.add(tile);
                        break;

                    case(1):
                        tile = wFact.getTile("HEX",column, row);
                        hexPanel.add(tile);
                        break;
                }
            }
         }
            JPanel contentPane = new JPanel();
            contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
            contentPane.add(hexPanel);
            frame = new JFrame("Deux grilles de tuiles graphiques");
            frame.setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
            frame.setContentPane(contentPane);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            this.setVisible(false);
		} else if(source == bouton2){
            TileLayout squareLayout = new SquareLayout(height, width);
            JPanel squarePanel = new JPanel(squareLayout);
            squarePanel.setPreferredSize(squareLayout.getPreferredDimension(600));

            for (int row = 0 ; row < height ; row++) {
                for (int column = 0 ; column < width ; column++) {
                    switch(r.nextInt(2)){
                        case(0):
                            tile = gFact.getTile("SQUARE",column, row);
                            squarePanel.add(tile);
                            break;

                        case(1):
                            tile = wFact.getTile("SQUARE",column, row);
                            squarePanel.add(tile);
                            break;
                        }
                }
            }
            JPanel contentPane = new JPanel();
            contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
            contentPane.add(squarePanel);
            frame.setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
            frame.setContentPane(contentPane);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            this.setVisible(false);
		}
	}

}
