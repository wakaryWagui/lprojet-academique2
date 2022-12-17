package welcome;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import tools.ImagePanel;

public class StartPage {
JPanel  home; // le panel qui represente la page startPage
	
	// le contenu de la page startPage
	JButton playStory;
    JButton createStory;
    JButton settings;
    JButton about;
    JButton exit;
	
	public StartPage(){
		
		// créer le panel avec comme arriere plan une image + créer le contenu de la page startPage
		
        home = new ImagePanel(new ImageIcon("./images/startPage.jpg").getImage()){
			@Override
			public void paintComponent(Graphics g) {
               g.drawImage(img,0,0,900,650,null); // réglage de la taille et l'emplacement de l'image
			   repaint();
            }
		};
        playStory = new JButton("Play Story");
        createStory = new JButton("Create Story");
        settings = new JButton("Settings");
        about = new JButton("About");
        exit = new JButton("Exit");
		
		
 
        // ajouter les buttons dans le panel home "dans la page startPage"
        home.add(playStory);
        home.add(createStory);
        home.add(settings);
        home.add(about);
        home.add(exit);
 
        // modifier la taille et l'emplacement des buttons
        playStory.setBounds(670, 30, 180, 40);
        createStory.setBounds(30, 30, 180, 40);
        settings.setBounds(670, 550, 180, 40);
        about.setBounds(350, 550,180, 40);
        exit.setBounds(30, 550, 180, 40);
		
		// modifier la couleur des buttons 
		playStory.setBackground(Color.cyan);
		createStory.setBackground(Color.cyan);
		settings.setBackground(Color.cyan);
		about.setBackground(Color.cyan);
		exit.setBackground(Color.cyan);
		
	}
	
}
