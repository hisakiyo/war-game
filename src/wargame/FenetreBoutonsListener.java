package wargame;

import wargame.generator.LayoutGenerator;
import wargame.generator.MapGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author Franck Vandewiele
 *
 */
public class FenetreBoutonsListener extends JFrame implements ActionListener{

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
        JPanel tilePanel = new JPanel();

        if(source == bouton) {
            Main.gameMap = MapGenerator.getMap("HEX", Main.height, Main.width);
            tilePanel = LayoutGenerator.getPanelLayout(Main.gameMap, "HEX", Main.height, Main.width);
        }
        else if(source == bouton2) {
            Main.gameMap = MapGenerator.getMap("SQUARE", Main.height, Main.width);
            tilePanel = LayoutGenerator.getPanelLayout(Main.gameMap, "SQUARE", Main.height, Main.width);
        }

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.add(tilePanel);
        frame = new JFrame("Deux grilles de tuiles graphiques");
        frame.setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        this.setVisible(false);

	}

}
