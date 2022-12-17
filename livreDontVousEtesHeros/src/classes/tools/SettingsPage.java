package tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import createStory.CreateBookPage;
import playStory.GamePage;

public class SettingsPage {
    public JPanel home;// le panel qui represente la page settingsPage
	
	// le contenu de la page settingsPage
    JLabel fontTypeLabel;
    public JComboBox fontTypeBox;
	JLabel fontSizeLabel;
    public JComboBox fontSizeBox;
    JLabel fontColorLabel;
    public JComboBox fontColorBox;
	public JButton reset;
    public JButton back;
	
	
	// ces attributs permettent de stocker les caracteristiques de la police des paragraphs (taille , type , coleur)
	String fontType,fontColor;
	int fontSize;
	
	
	public SettingsPage(){
		
		
		// créer le panel avec comme arriere plan une image + créer le contenu de la page settingsPage
		
        home = new ImagePanel(new ImageIcon("./images/settingsPage.jpg").getImage()){
			@Override
			public void paintComponent(Graphics g) {
               g.drawImage(img,0,0,1600,1000,null); // réglage de la taille et l'emplacement de l'image
			   repaint();
            }
		};
		
        fontTypeLabel = new JLabel("Font Type");
        fontTypeBox = new JComboBox(new String[]{"  Arial", "  Calibri", "  Dotum"});
		
		fontSizeLabel = new JLabel("Font Size");
        fontSizeBox = new JComboBox(new String[]{"  Small", "  Meduim", "  Large"});
		
		fontColorLabel = new JLabel("Font Color");
        fontColorBox = new JComboBox(new String[]{"  Black", "  Blue", "  Red"});
		
		reset = new JButton("Reset");
		back = new JButton("Back");
        
		
	    // ajouter le contenu dans le panel home "dans la page settingsPage"
        home.add(fontTypeLabel);
		home.add(fontTypeBox);
		home.add(fontSizeLabel);
		home.add(fontSizeBox);
		home.add(fontColorLabel);
		home.add(fontColorBox);
		home.add(reset);
		home.add(back);
		
        // modifier la taille et l'emplacement des element du panel home
        
        fontTypeLabel.setBounds(220, 50, 180, 40);
        fontTypeBox.setBounds(300, 50, 180, 40);
		fontSizeLabel.setBounds(220, 110, 180, 40);
        fontSizeBox.setBounds(300, 110, 180, 40);
		fontColorLabel.setBounds(220, 170, 180, 40);
        fontColorBox.setBounds(300, 170, 180, 40);
		reset.setBounds(300, 250, 180, 40);
		back.setBounds(300, 310, 180, 40);
		
		// modifier la couleur des buttons 
		reset.setBackground(Color.cyan);
		back.setBackground(Color.cyan);
		
		
		// intialiser les attributs avec la police par default
		fontType = "Arial";
		fontSize = 14;
		fontColor = "Black";
		
		
		fontTypeBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
 
 
                if (e.getStateChange() == ItemEvent.SELECTED) { // les actions à executer quand l'utilisateur choisit un type de police 
                    String selectedItem = e.getItem().toString();
 
                    switch (selectedItem) {
                        case "  Arial":
                            fontType = "Arial";
                            break;
                        case "  Calibri":
                            fontType = "Calibri";
                            break;
                        case "  Dotum":
                            fontType = "Dotum";
                            break;
                    }
					
                    // l'appel de ces deux methode static qui se trouvent dans les classe CreateStoryPage et GamePage permet de modifier la police des paragraphs  
                    CreateBookPage.setFontParagraph(fontType,fontSize,fontColor);
					GamePage.setFontParagraph(fontType,fontSize,fontColor);
                }
            }
        });
		
		fontSizeBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
 
 
                if (e.getStateChange() == ItemEvent.SELECTED) {// les actions à executer quand l'utilisateur choisit une taille de police 
                    String selectedItem = e.getItem().toString();
 
                    switch (selectedItem) {
                        case "  Small":
                            fontSize = 14;
                            break;
                        case "  Meduim":
                            fontSize = 17;
                            break;
                        case "  Large":
                            fontSize = 20;
                            break;
                    }
                    // l'appel de ces deux methode static qui se trouvent dans les classe CreateStoryPage et GamePage permet de modifier la police des paragraphs  
                    CreateBookPage.setFontParagraph(fontType,fontSize,fontColor);
					GamePage.setFontParagraph(fontType,fontSize,fontColor);
                }
            }
        });
		
		fontColorBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) { 
 
 
                if (e.getStateChange() == ItemEvent.SELECTED) { // les actions à executer quand l'utilisateur choisit une couleur de police 
                    String selectedItem = e.getItem().toString();
 
                    switch (selectedItem) {
                        case "  Black":
                            fontColor = "Black";
                            break;
                        case "  Blue":
                            fontColor = "Blue";
                            break;
                        case "  Red":
                            fontColor = "Red";
                            break;
                    }
                    // l'appel de ces deux methode static qui se trouvent dans les classe CreateStoryPage et GamePage permet de modifier la police des paragraphs 
                    CreateBookPage.setFontParagraph(fontType,fontSize,fontColor);
					GamePage.setFontParagraph(fontType,fontSize,fontColor);
                }
            }
        });
		
		
		
		
	}
	
	
}
