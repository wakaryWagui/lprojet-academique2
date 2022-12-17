package welcome;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import createStory.CreateBookPage;
import createStory.ManageBook;
import graphs.Noeud;
import graphs.Reponse;
import playStory.GamePage;
import playStory.PlayBookPage;
import tools.SettingsPage;
import tools.XmlBackup;

public class Main extends JFrame {

	StartPage startPage; // represente la page d'accueil du jeux
    CreateBookPage CreateBookPage; // represente la page qui permet de cr�er une histoire
    PlayBookPage PlayBookPage; // represente la page qui permet de choisir une histoire � jouer 
	GamePage gamePage; // represente la page qui permet de jouer une histoire
	SettingsPage settingsPage; // represente la page qui permet de faire le r�glage
	
	// cet attribut permet de stocker le noeud racine de l'histoire qu'on veut jouer et permet aussi de parcourir l'histoire selon les choix de l'utilisateur
	Noeud racine; 
	
	// quand on cr�e un objet de la classe main le constructeur va appeler la methode createAndShowGUI
    public Main() {
		
		createAndShowGUI();
    
	}
 
 
    // Cette fonction construit tout dans le jeu et d�termine ce qui se passera lorsque l'utilisateur interagira avec le contenu de la fen�tre
    private void createAndShowGUI() {
		
		// modifier la couleur de tout les JOptionPane
		UIManager.put("OptionPane.background",new ColorUIResource(51,119,255));
        UIManager.put("Panel.background",new ColorUIResource(51,119,255));
		 
		// cr�er toutes les pages du programme 
	    startPage = new StartPage();
        CreateBookPage = new CreateBookPage(); 
        PlayBookPage = new PlayBookPage();
		gamePage = new GamePage();
		settingsPage = new SettingsPage();
		
		// ici nous avons fait en sorte que la fen�tre utilise le CardLayout Pour placer les pages les unes derri�re les autres
        CardLayout card = new CardLayout();
        Container container = getContentPane();
        container.setLayout(card);
 
        // Ici, nous avons ajout� toutes les pages (conteneurs) dans la fen�tre
        add(startPage.home);
		add(CreateBookPage.home);
		add(PlayBookPage.home);
        add(gamePage.home);
		add(settingsPage.home);
		
		// Ici on a mis un nom pour chaque page (conteneur) pour pouvoir sp�cifier la page qui sera affich�e par son nom
        container.getLayout().addLayoutComponent("startPage", startPage.home);
        container.getLayout().addLayoutComponent("CreateBookPage", CreateBookPage.home);
		container.getLayout().addLayoutComponent("PlayBookPage", PlayBookPage.home);
		container.getLayout().addLayoutComponent("gamePage", gamePage.home);
		container.getLayout().addLayoutComponent("settingsPage", settingsPage.home);
		
		
		
		// le button createStory de la page startPage
		startPage.createStory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {// quand l'utilisateur click sur le button createStory de la page startPage alors  on execute les instructions suivantes
                
				CreateBookPage.clear();// nettoyer la page CreateBookPage
				card.show(container, "CreateBookPage"); // aller vers la page CreateBookPage
            }
        
	    });
		
		// le button playStory de la page startPage
		startPage.playStory.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {// quand l'utilisateur click sur le button playStory de la page startPage alors  on execute les instructions suivantes
			    
				//vider la liste des fichier stories pour supprimer les traces
			    PlayBookPage.model_stories.clear();
				//desactiver les buttons play et remove de la page PlayBookPage
				PlayBookPage.play.setEnabled(false);
	 	        PlayBookPage.remove.setEnabled(false);
				
				// on parcourt le dossier stories/ pour lister ces fichiers  dans le jlist_stories
				File stories  = new File("stories");
				File[] liste = stories.listFiles();
				for(File item : liste){
				
					if(item.isFile()){
						  PlayBookPage.model_stories.addElement(item.getName());
						  
					} 
							
		        }		   
				
				
				
			    //aller vers PlayBookPage
				card.show(container, "PlayBookPage");
				
			}
							
            
        });
		
	    // le button settings de la page startPage
	    startPage.settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // si l'utilisateur click sur settings alors
                card.show(container, "settingsPage"); // aller vers la page settingsPage
            }
        
	    });
		
		// le button about de la page startPage
		startPage.about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // quand on click sur le button about alors on affiche une fenetre qui contient le text suivant
                String aboutGame
                        = "<html>"
                        + "<big>livre dont vous etes le heros</big><br><br>"
                        + "<p>Prepared by : <b><br>- Rahmani Faical Sid Ahmed <br>- Michel Kalala<br>- Matej Trpkovski<br>- Wakary Doucoure<br></b><br><br>"
                        + "If you have any comments, ideas.. just let us know<br><br>"
                        + "<p><i>@ Copyright 2021 www.unicaen.fr - All Rights Reserved</i></p>"
                        + "<html>";
				
                				
 
                JOptionPane.showMessageDialog(getContentPane(), aboutGame, "About livre dont vous etes le heros", JOptionPane.PLAIN_MESSAGE);
            }
        });
		
		// quand l'utilisateur click sur le button exit de la page startPage on arrete le programme
		startPage.exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
		
		
		//le buton save de la page CreateBookPage
		CreateBookPage.save.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {// quand l'utilisateur click sur le button save alors  on execute les instructions suivantes
				
				
				// pour verifier si l'utilisateur a entr� le nom de l'histoire et le nom de l'auteur
				if(CreateBookPage.storyNameText.getText().equals("") || CreateBookPage.creatorNameText.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Error : you need to add the name of your story and the author", "Warning", JOptionPane.WARNING_MESSAGE);return;
				}
				// si le nom de l'histoire contient les caractere " " ,":" ,"." on affiche un message d'erreur pour ne pas avoir des erreur pendant la creation du fichier
				if(CreateBookPage.storyNameText.getText().contains(" ") || CreateBookPage.storyNameText.getText().contains(":") || CreateBookPage.storyNameText.getText().contains(".")){
					JOptionPane.showMessageDialog(null, "Error : the name of the story must not contain special characters ", "Warning", JOptionPane.WARNING_MESSAGE);return;
				}
				
				// on parcourt le dossier stories/ pour verifier s'il existe un fichier qui porte le meme nom
				
				File stories  = new File("./stories");
				File[] liste = stories.listFiles();
				for(File item : liste){
				
					if(item.isFile()){
						// si on a un fichier qui porte le meme nom dans le dossier stories alors on affiche un message d'erreur pour indiquer �a et on sort 
						if(item.getName().equals(CreateBookPage.storyNameText.getText()+".XmlBackup")){
						       	  JOptionPane.showMessageDialog(null, "Error : the name of file already exist !! you need to change the name ",
								  "Name file exist", JOptionPane.WARNING_MESSAGE);return;
						} 
						  
					} 
							
		        }
				
				// si la liste des noeuds est vide on affiche un message d'erreur pour dire a l'utilisateur que l'histoire est vide
				if(ManageBook.noeuds.size() == 0){
					JOptionPane.showMessageDialog(null, "Error : you did not create a story ", "Warning", JOptionPane.WARNING_MESSAGE);return;
				}
				
				// on verifie si l'utilisateur a entr� un identifiant entier dans le champ Root Node id
			    try{
						
					Integer.parseInt(CreateBookPage.textField_root.getText());
						
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Error : you forgot to add Root_Node_ID or Root_Node_ID is not integer", "Warning", JOptionPane.WARNING_MESSAGE);return;
				}			          
				// on verifie si le root_id que l'utilisateur � entr� exist 
				boolean exist = false;
				for(Noeud noeud:ManageBook.noeuds){
					if(Integer.parseInt(CreateBookPage.textField_root.getText()) == noeud.getIdentifiant()) {
						exist = true ; break;
					}
				}
				
				if(exist == false){
					JOptionPane.showMessageDialog(null, "Error : Root_Node_ID does not exist", "Warning", JOptionPane.WARNING_MESSAGE);return;
				}
				
				// si on arrive ici alors tout se passe bien donc on peut cr�er notre histoire
				
				XmlBackup XmlBackup = new XmlBackup();// cr�er un objet XmlBackup 
				// l'appel de la methode createXmlBackupFile permet de cr�er un fichier XmlBackup dans lequel on mets l'histoire
				XmlBackup.createXmlFile(ManageBook.noeuds,CreateBookPage.storyNameText.getText(),CreateBookPage.creatorNameText.getText(),CreateBookPage.textField_root.getText());
				
				// afficher un message pour dire que l'histoire � �t� cr�e avec succes
				JOptionPane.showMessageDialog(null, "The story was successfully created", "Congratulation", JOptionPane.PLAIN_MESSAGE);
				
				// retour � l'accueil
				card.show(container, "startPage");
				
				
			
			   
			}
                
            
        });
       
		
		// le button back de la page CreateBookPage
		CreateBookPage.back.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {// quand l'utilisateur click sur le button back alors  on execute les instructions suivantes
				
				int result = JOptionPane.showConfirmDialog( null,
                                                            "You are going to delete your history.\nAre you sure?",
                                                            "Delete History",
                                                            JOptionPane.YES_NO_OPTION,
                                                            JOptionPane.WARNING_MESSAGE );
				switch (result) {
                    case JOptionPane.YES_OPTION:// s'il click sur oui
                        card.show(container, "startPage");//aller vers startPage
						
                        break;
					//sinon on fait rien	
                    case JOptionPane.NO_OPTION:
                        break;
                    case JOptionPane.CLOSED_OPTION:
                        break;
                }											
			
			   
			}
                
            
        });
		
		// le button reset de la page CreateBookPage permet de tout effacer 
		CreateBookPage.reset.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {// quand l'utilisateur click sur le button reset alors  on execute les instructions suivantes
				
				int result = JOptionPane.showConfirmDialog( null,
                                                            "You are going to delete your history.\nAre you sure?",
                                                            "Delete History",
                                                            JOptionPane.YES_NO_OPTION,
                                                            JOptionPane.WARNING_MESSAGE );
															
				if(result != JOptionPane.YES_OPTION) return; // s'il ne click pas sur oui alors on fait rien
				
				CreateBookPage.clear();
				// masquer les button (edit,remove,confirm,cancel) et afficher le button add 
				// dans le cas ou l'utilisateur click sur reset pendant la modification
				CreateBookPage.btnNewButton_edit.setVisible(false);
				CreateBookPage.btnNewButton_remove.setVisible(false);
				CreateBookPage.btnNewButton_confirm.setVisible(false);
				CreateBookPage.btnNewButton_cancel.setVisible(false);
			    CreateBookPage.btnNewButton_add.setVisible(true);
			   
			}
                
            
        });
		
		// le button play de la page PlayBookPage
		PlayBookPage.play.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {// quand l'utilisateur click sur le button play de PlayBookPage alors on execute les instructions suivantes
			    
				if(PlayBookPage.jlist_stories.isSelectionEmpty() == false){// on verifie d'abord si on a selectionn� un element dans jlist_stories de la page PlayBookPage
					
					XmlBackup XmlBackup = new XmlBackup();// cr�er un objet XmlBackup
					
					// lire le fichier histoire selectionn� et r�cuperer la racine de l'histoire
					// remarque : dans la partie cr�ation on donne pas la possibilit� de cr�er une histoire vide donc on a au moin un noeud 
					racine = XmlBackup.readXmlFile((String)PlayBookPage.jlist_stories.getSelectedValue());
					// nettoyer la page gamePage avant de commencer le jeux pour effacer les traces de la derniere partie 
				    gamePage.clear();
					// afficher le contenu de la racine dans la page gamePage
					gamePage.textArea_description.setText(racine.getDescription());
					gamePage.textArea_question.setText(racine.getQuestion());
					for(Reponse reponse : racine.getReponses()){
						gamePage.model_reponses.addElement(reponse.getReponseText());
					}
					//aller vers gamePage
					card.show(container, "gamePage");
				     	
				}
				// d�sactiver le button next
				gamePage.next.setEnabled(false);
				
			}
							
            
        });
		
		// le button remove de la page PlayBookPage
		PlayBookPage.remove.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {// quand l'utilisateur click sur le button remove de PlayBookPage alors on execute les instructions suivantes
			    
				if(PlayBookPage.jlist_stories.isSelectionEmpty() == false){// on verifie d'abord si on a selectionn� un element dans jlist_stories de la page PlayBookPage
					
					File file = new File("stories/"+(String)PlayBookPage.jlist_stories.getSelectedValue());
					file.delete();
				    PlayBookPage.model_stories.remove(PlayBookPage.jlist_stories.getSelectedIndex()); 	
				}
				
				// d�sactiver les buttons play et remove et next
				PlayBookPage.play.setEnabled(false);
		        PlayBookPage.remove.setEnabled(false);
			
			}
							
            
        });
		
		
		// le button back de la page PlayBookPage
		PlayBookPage.back.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {// quand l'utilisateur click sur le button back alors  on execute les instructions suivantes
				//retour � l'accueil
				card.show(container, "startPage");
				//vider la liste des fichier stories
			    PlayBookPage.model_stories.clear();
				
			}
                
            
        });
		
		
		
		
		
		
		// le button next de la page gamePage
		gamePage.next.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {// quand l'utilisateur click sur le button next de gamePage alors on execute les instructions suivantes
				
				if(gamePage.jlist_reponses.isSelectionEmpty() == false){// on verifie d'abord si on a selectionn� un element dans jlist_reponses de gamePage
			            // on part vers la destination de la r�ponse choisie par l'utilisateur 
						racine = racine.getReponseValue(gamePage.jlist_reponses.getSelectedIndex()).getNoeudDestination();
						//r�initialiser la page gamePage et desactiver le button next
						gamePage.clear();	
						gamePage.next.setEnabled(false);
						if(racine == null){//si racine == null alors la reponse n'a pas de destination donc on considere �a comme une defaite
							JOptionPane.showMessageDialog(null, " you lost: there is no destination for this answer", "lost", JOptionPane.WARNING_MESSAGE);
						    card.show(container, "startPage");
							return;
						}
						// on affiche le contenu du noeud courant dans gamePage
						gamePage.textArea_description.setText(racine.getDescription());
						gamePage.textArea_question.setText(racine.getQuestion());
						for(Reponse reponse : racine.getReponses()){
						   gamePage.model_reponses.addElement(reponse.getReponseText());
						}
						
						
						
					   
			    }
			   
			}
                
            
        });
		
		
		// le button leave de la page gamePage
		gamePage.leave.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {// quand l'utilisateur click sur le button leave de gamePage alors on execute les instructions suivantes
			    
				if(racine.noeudTerminal()){ // si on est dans un noeud terminal "qui n'a pas de reponse" "fin de partie"
					JOptionPane.showMessageDialog(null, " We hope to see you in the next time ... good luck ","Bye",JOptionPane.PLAIN_MESSAGE);
				    card.show(container, "startPage");
					return;
				}else{ // l'utilisateur veut quitter la partie avant sa fin
				
					int result = JOptionPane.showConfirmDialog( null,
																"You are going to quit the game.\nAre you sure?",
																"leave the game",
																JOptionPane.YES_NO_OPTION,
																JOptionPane.WARNING_MESSAGE );
					switch (result) {
						case JOptionPane.YES_OPTION:
							// aller vers startPage
							card.show(container, "startPage");
							
					
							break;
						case JOptionPane.NO_OPTION:
							break;
						case JOptionPane.CLOSED_OPTION:
							break;
					}
			    }
			}
							
            
        });
	   
	   // le button reset de la page settingsPage
	   settingsPage.reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // quan on click sur le button reset de la page settingsPage
                // on r�initialise les JComboBox et la police 
				settingsPage.fontTypeBox.setSelectedItem("  Arial");
				settingsPage.fontSizeBox.setSelectedItem("  Small");
				settingsPage.fontColorBox.setSelectedItem("  Black");
				CreateBookPage.setFontParagraph("Arial",14,"Black");
				GamePage.setFontParagraph("Arial",14,"Black");
				
            }
        
	   });
	   
	   // le button back de la page settingsPage
	   settingsPage.back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(container, "startPage"); // aller vers startPage
            }
        
	   });

       
	      
		// Ici nous avons d�fini quelques propri�t�s de la fenetre et l'avons rendues visibles  
        setTitle("livre dont vous etes le heros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650); 
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
 
 
    }
 
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                new Main(); // on cr�e un objet de la classe Main et comme �a nous allons appeler la methode createAndShowGUI et celle ci elle va cr�er la fenetre  
            
			}
        });
    }
 
	

}
