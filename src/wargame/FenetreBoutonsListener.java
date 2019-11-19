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

    private static JFrame frame;
	private JButton bouton;
	private JButton bouton2;
    private JTextField textField;
    private JLabel label;
    public static JLabel listscore[] = new JLabel[2];
    public static JPanel score;

	public FenetreBoutonsListener(){
		super();
		build();//On initialise notre fenêtre
	}
	
	private void build(){
		setTitle("Choix grille"); //On donne un titre à l'application
		setSize(250,140); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setResizable(false); //On permet le redimensionnement
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		setContentPane(buildContentPane());
	}

	private JPanel buildContentPane(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		bouton = new JButton("Hex !");
		bouton.addActionListener(this);
		bouton.setPreferredSize(new Dimension(150, 50));

		bouton2 = new JButton("Square !");
		bouton2.addActionListener(this);
        bouton2.setPreferredSize(new Dimension(150, 50));

        label = new JLabel("Type de grille");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(label);
        panel.add(bouton);
        panel.add(bouton2);
		return panel;
	}
	
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        JPanel tilePanel = new JPanel();
        score = new JPanel();
        score.setLayout(new FlowLayout());

        if(source == bouton) {
            Main.gameMap = MapGenerator.getMap("HEX", Main.height, Main.width);
            tilePanel = LayoutGenerator.getPanelLayout(Main.gameMap, "HEX", Main.height, Main.width);
        }
        else if(source == bouton2) {
            Main.gameMap = MapGenerator.getMap("SQUARE", Main.height, Main.width);
            tilePanel = LayoutGenerator.getPanelLayout(Main.gameMap, "SQUARE", Main.height, Main.width);
        }

        listscore[0] = new JLabel("Joueur 1 = 0       ");
        listscore[1] = new JLabel("Joueur 2 = 0       ");
        score.add(listscore[0]);
        score.add(listscore[1]);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.add(tilePanel);
        contentPane.add(score);
        frame = new JFrame("Deux grilles de tuiles graphiques");
        frame.setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        this.setVisible(false);

	}

    //Méthode permettant de gérer le score de la partie ainsi que les décisions de victoire d'un joueur.
    static public void update_score(int vscore, int joueur, boolean end){
        if(!end){ 
            score.remove(listscore[joueur]);
            listscore[joueur] = new JLabel("Joueur "+ (joueur+1) +" = "+vscore+"       ");
            score.add(listscore[joueur]);
            score.updateUI();
        } else {
            frame.getContentPane().removeAll();
            JPanel contentPane = new JPanel();
            contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
            JLabel thend = new JLabel("Partie terminée: Joueur "+(joueur+1)+" gagnant");
            thend.setFont(new Font("Arial", Font.PLAIN, 25));
            thend.setPreferredSize(new Dimension(700, 550));
            contentPane.add(thend);
            frame.add(contentPane);
            frame.repaint();
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }
}
