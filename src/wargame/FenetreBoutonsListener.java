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

/**
 * @author Franck Vandewiele
 *
 */
public class FenetreBoutonsListener extends JFrame implements ActionListener{
	final int width = 8;
    final int height = 5;
    private JFrame frame;

	private JButton bouton;
	private JButton bouton2;
		
	public FenetreBoutonsListener(){
		super();
		
		build();//On initialise notre fenêtre
	}
	
	private void build(){
		setTitle("Selection type "); //On donne un titre à l'application
		setSize(320,240); //On donne une taille à notre fenêtre
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
		panel.add(bouton);
				
		bouton2 = new JButton("Square !");
		bouton2.addActionListener(this);
		panel.add(bouton2);
		
		return panel;
	}
	
	public static void main(String[] args) {
		//On crée une nouvelle instance de notre FenetreBoutons
		FenetreBoutonsListener fenetre = new FenetreBoutonsListener();
		fenetre.setVisible(true);//On la rend visible
	}

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == bouton){
            TileLayout hexLayout = new HexLayout(height, width);
            JPanel hexPanel = new JPanel(hexLayout);
            hexPanel.setPreferredSize(hexLayout.getPreferredDimension(600));
            BufferedImage image = null;
            try {
                image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/grass.png"));
            } catch (IOException e2) {
                System.out.println("ERROR: file not found");
            } 

            if (image == null) {
                System.exit(-1);
            }
            for (int row = 0 ; row < height ; row++) {
                for (int column = 0 ; column < width ; column++) {
                    Tile hexTile = new HexTile(row + " ; " + column, image);
                    hexPanel.add(hexTile);
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
            BufferedImage image = null;
            try {
                image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("assets/grass.png"));
            } catch (IOException e2) {
                System.out.println("ERROR: file not found");
            }

            if (image == null) {
                // si on n'a pas réussi à charger l'image, on quitte le programme
                System.exit(-1);
            }
            for (int row = 0 ; row < height ; row++) {
                for (int column = 0 ; column < width ; column++) {
                    // construction et ajout d'une SquareTile au JPanel agencé en grille carrée
                    Tile squareTile = new SquareTile(row + " ; " + column, image);
                    squarePanel.add(squareTile);
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
