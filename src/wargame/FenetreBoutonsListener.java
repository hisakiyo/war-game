package wargame;

import wargame.gameplay.Player;
import wargame.generator.LayoutGenerator;
import wargame.generator.MapGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cette classe permet de gerer nos interfaces graphiques, dont le choix du type de carte sur laquelle jouer, les scores des joueurs et l'ecran de fin de partie
 * @author Gauthier MARETTE
 *
 */
public class FenetreBoutonsListener extends JFrame implements ActionListener{

    private static JFrame frame;
    private JButton bouton;
    private JButton bouton2;
    private JTextField textField;
    private JLabel label;
    public static JLabel[] listscore = new JLabel[20];
    public static JPanel score;
    public static JPanel apanel;
    public static JLabel alabel;
    public static JLabel blabel;

	public FenetreBoutonsListener(){
		super();
		build();//On initialise notre fenêtre
	}

    /**
     * Cette methode permet de mettre a jour l'interface des scores et d'afficher l'ecran de fin de partie lorsque plus aucun joueur de peut placer d'armees
     *
     * @param vscore Score du joueur a mettre a jour
     * @param joueur Identifiant du joueur dont il faut mettre le score a jour
     * @param end    Boolean permettant de verifier si il faut afficher l'ecran de fin
     */
    static public void update_score(int vscore, int joueur, boolean end) {
        if (!end) {
            //Mise a jour du scoreboard
            score.remove(listscore[joueur]);
            listscore[joueur] = new JLabel("Joueur " + (joueur + 1) + " : " + vscore + "pts    ");
            score.add(listscore[joueur]);
            score.updateUI();

            //Affichage de la prochaine personne a jouer + la taille de son armee
            apanel.remove(blabel);
            apanel.remove(alabel);
            blabel = new JLabel("Tour du joueur " + Main.currentArmy.getOwner().getPlayerId());
            alabel = new JLabel("Taille de l'armee a placer: " + Main.currentArmy.getSize());
            apanel.add(blabel);
            apanel.add(alabel);
            apanel.updateUI();
        } else {
            frame.getContentPane().removeAll();
            JPanel contentPane = new JPanel();
            contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
            Player pmax = new Player(0, null);
            for (Player p : Main.playerQueue) {
                if (pmax.getScore() < p.getScore()) {
                    pmax = p;
                }
            }
            JLabel thend = new JLabel("Partie terminee: Joueur " + pmax.getPlayerId() + " gagnant, score = " + pmax.getScore());
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

    /**
     * Construction de la fenetre
     */
    private void build() {
        setTitle("Choix grille"); //On donne un titre a l'application
        setSize(250, 210); //On donne une taille a notre fenêtre
        setLocationRelativeTo(null); //On centre la fenêtre sur l'ecran
        setResizable(false); //On permet le redimensionnement
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit a l'application de se fermer lors du clic sur la croix
        setContentPane(buildContentPane());
    }

    /**
     * Construction du menu de selection du type de map
     * @return panel
     */
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

    /**
     * Cette methode permet d'afficher une map de jeu et le scoreboard lorsqu'on clique sur le type de map choisie
     */
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        JPanel tilePanel = new JPanel();
        score = new JPanel();
        apanel = new JPanel();
        score.setLayout(new FlowLayout());
        apanel.setLayout(new FlowLayout());
        if(source == bouton) {
            Main.gameMap = MapGenerator.getMap("HEX", Main.height, Main.width);
            tilePanel = LayoutGenerator.getPanelLayout(Main.gameMap, "HEX", Main.height, Main.width);
        }
        else if(source == bouton2) {
            Main.gameMap = MapGenerator.getMap("SQUARE", Main.height, Main.width);
            tilePanel = LayoutGenerator.getPanelLayout(Main.gameMap, "SQUARE", Main.height, Main.width);
        }

        for (Player p : Main.playerQueue) {
            listscore[p.getPlayerId() - 1] = new JLabel("Joueur " + p.getPlayerId() + " : 0pts");
            score.add(listscore[p.getPlayerId() - 1]);
        }
        blabel = new JLabel("Tour du joueur " + Main.currentArmy.getOwner().getPlayerId());
        alabel = new JLabel("Taille de l'armee a placer: " + Main.currentArmy.getSize());
        apanel.add(blabel);
        apanel.add(alabel);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.add(tilePanel);
        contentPane.add(score);
        contentPane.add(apanel);
        frame = new JFrame("Wargame");
        frame.setLocationRelativeTo(null); //On centre la fenêtre sur l'ecran
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        this.setVisible(false);
	}
}
