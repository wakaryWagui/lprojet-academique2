package playStory;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tools.ImagePanel;

public class PlayBookPage {
    public JPanel  home; // le panel qui represente la page playStoryPage "la page qui permet de choisir une histoire pour la jouer"
	
	// le contenu de la page playStoryPage
	public DefaultListModel model_stories;
	public JList jlist_stories;
	JScrollPane scrollPane_stories;
    public JButton play;
	public JButton remove;
    public JButton back;
	
	public PlayBookPage(){
		
		// créer le panel avec comme arriere plan une image + créer le contenu de la page playStoryPage
		
        home = new ImagePanel(new ImageIcon("./images/playStoryPage.jpg").getImage()){
			@Override
			public void paintComponent(Graphics g) {
               g.drawImage(img,0,0,900,650,null);
			   repaint();
            }
		};
		
        model_stories = new DefaultListModel();
		jlist_stories = new JList(model_stories);
		scrollPane_stories = new JScrollPane(jlist_stories);
        play = new JButton("Play");
		remove = new JButton("Remove");
        back = new JButton("Back");
		
 
        // ajouter les buttons et la jlist_stories dans le panel home
        home.add(scrollPane_stories);
        home.add(play);
		home.add(remove);
        home.add(back);
        
		// modifier la taille et l'emplacement des buttons et de la jlist_stories
        scrollPane_stories.setBounds(280, 96, 301, 288);
        play.setBounds(670, 550, 180, 40);
		remove.setBounds(350, 550,180, 40);
        back.setBounds(30, 550, 180, 40);
		
		// modifier la couleur des buttons 
		play.setBackground(Color.cyan);
		remove.setBackground(Color.cyan);
		back.setBackground(Color.cyan);
		
		
		jlist_stories.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            { // les instructions à exécuter quand un element est selectionné dans jlist_stories
                
				// on active les button play(pour jouer l'histoire) , remove(pour supprimer l'histoire)
				play.setEnabled(true);
		        remove.setEnabled(true);

            }
        });
		
		
		
	}
}
