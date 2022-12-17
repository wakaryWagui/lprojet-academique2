package playStory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tools.ImagePanel;

public class GamePage {
    public JPanel  home; // le panel qui represente la page getImage "la page qui permet de jouer une histoire"
	
	// le contenu de la page gamePage
	public JButton next;
    public JButton leave;
	JLabel label_description;
	public static JTextArea textArea_description;
	JScrollPane scrollPane_description;
	JLabel label_question;
	public static JTextArea textArea_question;
	JScrollPane scrollPane_question;
	JLabel label_reponses;
	public DefaultListModel model_reponses;
	public JList jlist_reponses;
	JScrollPane scrollPane_reponses;
	
	
	public GamePage(){
		
		// créer le panel avec comme arriere plan une image + créer le contenu de la page gamePage
        home = new ImagePanel(new ImageIcon("./images/gamePage.jpg").getImage()){
			@Override
			public void paintComponent(Graphics g) {
               g.drawImage(img,0,0,900,650,null);
            }
		};
        next = new JButton("next");
		leave = new JButton("leave");
        label_description = new JLabel("paragraph1");
		textArea_description = new JTextArea();
		scrollPane_description = new JScrollPane(textArea_description);
		label_question = new JLabel("paragraph2");
		textArea_question = new JTextArea();
		scrollPane_question = new JScrollPane(textArea_question);
		label_reponses = new JLabel("reponses");
		model_reponses = new DefaultListModel();
		jlist_reponses = new JList(model_reponses);
		scrollPane_reponses = new JScrollPane(jlist_reponses);
		
	    // ajouter le contenu dans le panel home "le contenu de la page"
        home.add(next);
		home.add(leave);
		home.add(label_description);
		home.add(label_question);
		home.add(label_reponses);
		home.add(scrollPane_description);
		home.add(scrollPane_question);
		home.add(scrollPane_reponses);
		
        // modifier la taille et l'emplacement des element du panel home
        
        next.setBounds(570, 320, 180, 40);
        leave.setBounds(570, 390, 180, 40);
		label_description.setBounds(23, 30, 71, 14);
		scrollPane_description.setBounds(23, 50, 420, 200);
		label_question.setBounds(23, 265, 71, 14);
		scrollPane_question.setBounds(23, 285, 420, 200);
		label_reponses.setBounds(500, 30, 59, 14);
		scrollPane_reponses.setBounds(500, 50, 300, 200);
		
		// modifier la couleur des buttons 
		next.setBackground(Color.cyan);
		leave.setBackground(Color.cyan);
		
		// désactiver le button next
		next.setEnabled(false);
		
		// faire un saut de ligne si on arrive a la fin de la ligne 
		textArea_description.setLineWrap(true);
		textArea_question.setLineWrap(true);
		
		// eviter de partager un mot qui se trouve a la fin sur deux ligne 
		textArea_description.setWrapStyleWord(true);
		textArea_question.setWrapStyleWord(true);
		
		// empecher l'utilisateur d'ecrire sur les champs textArea 
		textArea_description.setEditable(false);
		textArea_question.setEditable(false);
		
		jlist_reponses.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            { // les instructions à exécuter quand un element est selectionné dans jlist_reponses
                next.setEnabled(true);
            }
        });
		
		
		
		
		
		
		
	}
	
	// cette methode permet de modifier la police des deux textArea paragraphs (type,taille,couleur)
	public static void setFontParagraph(String fontType,int fontSize,String fontColor){
		
		textArea_description.setFont(new Font(fontType, Font.PLAIN, fontSize));
		textArea_question.setFont(new Font(fontType, Font.PLAIN, fontSize));
		
		switch (fontColor) {
                        case "Black":
                            textArea_description.setForeground(Color.BLACK);
							textArea_question.setForeground(Color.BLACK);
                            break;
                        case "Blue":
                            textArea_description.setForeground(Color.BLUE);
							textArea_question.setForeground(Color.BLUE);
                            break;
                        case "Red":
                            textArea_description.setForeground(Color.RED);
							textArea_question.setForeground(Color.RED);
                            break;
        
		}
	
	}
	
	// cette methode permet de nettoyer les champs textArea et jlist_reponses
	public void clear(){
			
			textArea_description.setText("");
		    textArea_question.setText("");
			model_reponses.clear();
		
    }
	
}
