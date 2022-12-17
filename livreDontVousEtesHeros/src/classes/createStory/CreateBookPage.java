package createStory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import graphs.Noeud;
import graphs.Reponse;
import tools.ImagePanel;

public class CreateBookPage {
    public JPanel home; // ce panel represente la page createStoryPage et il va contenir les elements de la page 
	
	// chaque panel va contenir quelques elements de la page 
    JPanel panel_info;
	JPanel panel_listnoeuds;
	JPanel panel_noeud;
	
	public JButton back;
	public JButton save;
	public JButton reset;
	
	//les élements que nous allons mettre dans panel_info
	JLabel creatorNameLabel;
    JLabel storyNameLabel;
    public JTextField creatorNameText;
	public JTextField storyNameText;
	
	//les élements que nous allons mettre dans panel_listnoeuds
	JLabel lblNewLabel_root;
	public JTextField textField_root;
	DefaultListModel model_noeuds;
	JList jlist_noeuds;
	JScrollPane scrollPane_listnoeuds;
	public JButton btnNewButton_remove;
	public JButton btnNewButton_edit;			
	
	
	
	//les élements que nous allons mettre dans panel_noeud
	public JButton btnNewButton_confirm;
	public JButton btnNewButton_cancel;
	public JButton btnNewButton_add;
	JLabel lblNewLabel_idnoeud;
	JTextField textField_idnoeud;
	JLabel lblNewLabel_description;
	static JTextArea textArea_description;
	JScrollPane scrollPane_description;
	JLabel lblNewLabel_question;
	static JTextArea textArea_question;
	JScrollPane scrollPane_question;
	JLabel lblNewLabel_reponses;
	static DefaultListModel model_reponses;
	static JList jlist_reponses;
	JScrollPane scrollPane_reponses;
	JButton btnNewButton_plusR;
	JButton btnNewButton_moinR;
	JButton btnNewButton_editR;
	
	// ces deux attributs permettent de stocker une fenetre pour ajouter une réponse et l'autre pour modifier une réponse 
	static AddReponse addreponse ;
	static EditReponse editreponse ;
	
	String  lastId ; // on aura besoin de cette variable dans la modification

	
	
	public CreateBookPage() {
		
		// ici on crée le conteneur de base "home" qui a une image comme arriere plan et 3 conteneur , 3 button  
		home = new ImagePanel(new ImageIcon("./images/createStoryPage.png").getImage()){
			@Override
			public void paintComponent(Graphics g) {
               g.drawImage(img,-40,0,950,620,null); // modifier la taille et l'emplacement de l'image
			   repaint();
            }
		};
		
		panel_info = new ImagePanel(new ImageIcon("./images/createStoryPage.png").getImage());
		panel_listnoeuds = new ImagePanel(new ImageIcon("./images/createStoryPage.png").getImage());
		panel_noeud = new ImagePanel(new ImageIcon("./images/createStoryPage.png").getImage());
		
		back = new JButton("Back");
		save = new JButton("Save");
		reset = new JButton("Reset");
		
		// spécifier la taille et l'emplacement des buttons
		back.setBounds(125, 560, 180, 40);
		save.setBounds(590, 560, 180, 40);
		reset.setBounds(365, 560, 180, 40);
		// modifier la couleur des buttons
		back.setBackground(Color.cyan);
		save.setBackground(Color.cyan);
		reset.setBackground(Color.cyan);
		// on mets tout dans le conteneur de base home
		home.add(panel_info);
		home.add(panel_listnoeuds);
		home.add(panel_noeud);
		home.add(back);
		home.add(save);
		home.add(reset);
		
		/** conteneur informations */
		
		panel_info.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "informations ", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_info.setBounds(70, 10, 750, 70);
		panel_info.setLayout(null);
		// créer les élements du conteneur panel_info
		creatorNameLabel = new JLabel("Creator Name");
        storyNameLabel = new JLabel("Story Name");
        creatorNameText = new JTextField();
		storyNameText = new JTextField();
		// ajouter le contenu dans panel_info
		panel_info.add(creatorNameLabel);
        panel_info.add(storyNameLabel);
        panel_info.add(creatorNameText);
		panel_info.add(storyNameText);
		// spécifier la taille et l'emplacement des élements
		creatorNameLabel.setBounds(50,30, 88, 14);
        storyNameLabel.setBounds(450, 30, 77, 14);
        creatorNameText.setBounds(145, 28, 135, 20);
		storyNameText.setBounds(530, 28, 135, 20);
		
		
		/** conteneur liste des noeuds */
		
		panel_listnoeuds.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "liste des noeuds ", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_listnoeuds.setBounds(470, 90, 350, 450);
		panel_listnoeuds.setLayout(null);
		// créer les élements du conteneur panel_listnoeuds
		lblNewLabel_root = new JLabel("Root Node ID");
		textField_root = new JTextField();
		model_noeuds = new DefaultListModel();
		jlist_noeuds = new JList(model_noeuds);
		scrollPane_listnoeuds = new JScrollPane(jlist_noeuds);
		btnNewButton_remove = new JButton("remove");
		btnNewButton_edit = new JButton("edit");			
		// ajouter le contenu dans panel_listnoeuds
		panel_listnoeuds.add(lblNewLabel_root);
		panel_listnoeuds.add(textField_root);
		panel_listnoeuds.add(scrollPane_listnoeuds);
		panel_listnoeuds.add(btnNewButton_edit);
		panel_listnoeuds.add(btnNewButton_remove);
		// spécifier la taille et l'emplacement des élements
		lblNewLabel_root.setBounds(110, 84, 75, 14);
		textField_root.setBounds(190, 80, 64, 20);
		scrollPane_listnoeuds.setBounds(80, 110, 201, 188);
		btnNewButton_remove.setBounds(140, 344, 79, 23);
		btnNewButton_edit.setBounds(140, 310, 79, 23);
		// modifier la couleur des buttons
		btnNewButton_remove.setBackground(Color.cyan);
		btnNewButton_edit.setBackground(Color.cyan);

		/** conteneur noeud */
		
		panel_noeud.setBorder(new TitledBorder(null, "noeud", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_noeud.setBounds(70, 90, 350, 450);
		panel_noeud.setLayout(null);
		// créer les élements du conteneur panel_noeud
		btnNewButton_confirm = new JButton("confirm");
		btnNewButton_cancel = new JButton("cancel");
		btnNewButton_add = new JButton("add");
		lblNewLabel_idnoeud = new JLabel("Node ID");
		textField_idnoeud = new JTextField();
		lblNewLabel_description = new JLabel("description");
		textArea_description = new JTextArea();
		scrollPane_description = new JScrollPane(textArea_description);
		lblNewLabel_question = new JLabel("question");
		textArea_question = new JTextArea();
		scrollPane_question = new JScrollPane(textArea_question);
		lblNewLabel_reponses = new JLabel("reponses");
		model_reponses = new DefaultListModel();
		jlist_reponses = new JList(model_reponses);
		scrollPane_reponses = new JScrollPane(jlist_reponses);
		btnNewButton_plusR = new JButton("+");
		btnNewButton_moinR = new JButton("-");
		btnNewButton_editR = new JButton("edit");
		// ajouter le contenu dans panel_noeud
		panel_noeud.add(btnNewButton_confirm);
		panel_noeud.add(btnNewButton_cancel);
		panel_noeud.add(btnNewButton_add);
		panel_noeud.add(lblNewLabel_idnoeud);
		panel_noeud.add(textField_idnoeud);
		panel_noeud.add(lblNewLabel_description);
		panel_noeud.add(scrollPane_description);
		panel_noeud.add(lblNewLabel_question);
		panel_noeud.add(scrollPane_question);
		panel_noeud.add(lblNewLabel_reponses);
	    panel_noeud.add(scrollPane_reponses);
		panel_noeud.add(btnNewButton_plusR);
		panel_noeud.add(btnNewButton_moinR);
		panel_noeud.add(btnNewButton_editR);
		// spécifier la taille et l'emplacement des élements
		btnNewButton_confirm.setBounds(216, 20, 89, 23);
		btnNewButton_cancel.setBounds(216, 47, 89, 23);
		btnNewButton_add.setBounds(216, 20, 89, 23);
		lblNewLabel_idnoeud.setBounds(23, 24, 59, 14);
		textField_idnoeud.setBounds(79, 21, 64, 20);
		textField_idnoeud.setColumns(10);
		lblNewLabel_description.setBounds(23, 78, 71, 14);
		scrollPane_description.setBounds(23, 95, 280, 83);
		lblNewLabel_question.setBounds(23, 182, 71, 14);
		scrollPane_question.setBounds(23, 200, 280, 83);
		lblNewLabel_reponses.setBounds(23, 287, 59, 14);
		scrollPane_reponses.setBounds(23, 306, 152, 87);
		btnNewButton_plusR.setBounds(196, 310, 59, 23);
		btnNewButton_moinR.setBounds(196, 338, 59, 23);
		btnNewButton_editR.setBounds(196, 365, 59, 23);
		// modifier la couleur des buttons
		btnNewButton_confirm.setBackground(Color.cyan);
		btnNewButton_cancel.setBackground(Color.cyan);
		btnNewButton_add.setBackground(Color.cyan);
		btnNewButton_plusR.setBackground(Color.cyan);
		btnNewButton_moinR.setBackground(Color.cyan);
		btnNewButton_editR.setBackground(Color.cyan);
		
		
		
		
		// masquer les button
		btnNewButton_remove.setVisible(false);
		btnNewButton_edit.setVisible(false);
		btnNewButton_confirm.setVisible(false);
		btnNewButton_cancel.setVisible(false);
		
		//modifier la police des deux paragraphs
		
		textArea_description.setFont(new Font("Arial", Font.PLAIN, 14));
		textArea_question.setFont(new Font("Arial", Font.PLAIN, 14));
		textArea_description.setForeground(Color.BLACK);
		textArea_question.setForeground(Color.BLACK);
		
		// créer les deux fenetre AddReponse et EditReponse
		addreponse = new AddReponse();
		editreponse = new EditReponse();
		
		
			
		
		
		
		
        
		// jlist_noeuds
		
		jlist_noeuds.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            { // les instructions à exécuter quand un element est selectionné dans jlist_noeuds
                
				// on affiche les button edit(permet de modifier un noeud) , remove (permet de supprimer un noeud)
				btnNewButton_edit.setVisible(true);
				btnNewButton_remove.setVisible(true);
				

            }
        });
		
		// button add (noeud)
		
		btnNewButton_add.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) { // quand l'utilisateur click sur le button add alors toutes les instructions de cette methode seront executées
				
				     
				    // on verifie id_noeud 
					
				    try{
						
						Integer.parseInt(textField_idnoeud.getText());
						
					}catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(null, "Error : you forgot to add id_noeud or id_noeud is not integer", "Warning", JOptionPane.WARNING_MESSAGE);return;
					}			          
					
					// verifier l'existance de l'identifiant du noeud qu'on est en train de créer
					boolean id_exist = false;
					for(Noeud noeud : ManageBook.noeuds){
						if(Integer.parseInt(textField_idnoeud.getText()) == noeud.getIdentifiant()){
							id_exist = true; break;
						}
					}
			        
					if(id_exist == true){
						JOptionPane.showMessageDialog(null, "Error : id_noeud already exists", "Warning", JOptionPane.WARNING_MESSAGE);return;
					}
					
					
					// création du noeud
					
					ManageBook.noeud = new Noeud(textArea_description.getText(),textArea_question.getText(),Integer.parseInt(textField_idnoeud.getText()));
					
					if(ManageBook.reponses.isEmpty() == false){ // si on a entré des reponses pour le noeud qu on est en train de créer
					
     					ManageBook.noeud.setReponses(ManageBook.reponses); // on ajoute les reponses au noeud qu'on vient de créer
	
                	}
					// pendant la création d'un noeud l'utilisateur par erreur peut supprimer un noeud et puis ajouter une reponse qui fait référence au noeud supprimé
					// dans ce cas on doit verifier que les destinations existent avant d'ajouter le noeud 
					int i = 0;
					for(Reponse reponse : ManageBook.reponses){
						i++;
						if(reponse.getNoeudDestination() != null){
							if(ManageBook.noeuds.contains(reponse.getNoeudDestination()) == false){
								JOptionPane.showMessageDialog(null, "Error : reponse "+i+" refers to node which does not exist", "Warning", JOptionPane.WARNING_MESSAGE);return;
							}
						}
					}
		
					
					ManageBook.noeuds.add(ManageBook.noeud); // l'ajout du noeud qu'on vient de créer dans la liste
				    model_noeuds.addElement("noeud "+ManageBook.noeud.getIdentifiant()); // ajouter un element dans le jlist_noeuds qui est associé au noeud qu'on a créer
					textField_idnoeud.setText("");// vider textField_idnoeud
					textArea_description.setText("");// vider textArea_
				    textArea_question.setText("");// vider textArea_question
				    model_reponses.removeAllElements();// vider jlist_reponses
			        ManageBook.reponses = new ArrayList<Reponse>();// réinitialiser la liste des réponse avec une liste vide
					ManageBook.noeud = null;  			
				            
            }
        });
		
		// button remove (noeud)
		
		
		btnNewButton_remove.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {// quand l'utilisateur click sur le button remove alors toutes les instructions de cette methode seront executées
                
				    if(jlist_noeuds.isSelectionEmpty()) return;// si aucun element est sélectionné on fait rien
					
					Noeud noeud = ManageBook.noeuds.get(jlist_noeuds.getSelectedIndex()); // on récupere le noeud à supprimer en utilisant l'index de l'element selectionné
					
					for(Noeud noeudp : ManageBook.noeuds){ // on parcourt toutes les noeuds de la liste
						for(Reponse reponse : noeudp.getReponses()){ // pour chaque noeud on parcourt les réponses
							if(reponse.getNoeudDestination() == noeud ){// si le suivant de la réponse est le noeud à supprimer
								reponse.setNoeudDestination(null); // alors on mets null dans la destination dans le but de couper le lien
							}
						}
					}
					
					ManageBook.noeuds.remove(jlist_noeuds.getSelectedIndex()); // on supprime le noeud en utilisant l'index de l'element selectionné
					model_noeuds.remove(jlist_noeuds.getSelectedIndex()); // on supprime l'element sélectionné de la jlist_noeuds
					
					// masquer les button edit , remove
				    btnNewButton_edit.setVisible(false);
				    btnNewButton_remove.setVisible(false);
				
            }
        });
		
		
		// button edit
		
		
		btnNewButton_edit.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {// quand l'utilisateur click sur le button edit pour les noeud on execute les instructions suivantes
				
				if(jlist_noeuds.isSelectionEmpty()) return;// si aucun element est sélectionné on fait rien
				
				// on remplace le button add avec le button confirm qui permet de confirmer la modification
				btnNewButton_add.setVisible(false);
				btnNewButton_confirm.setVisible(true);
				// l'ajout du button cancel qui permet à l'utilisateur d'annuler la modification
				btnNewButton_cancel.setVisible(true);
				// desactiver la selection pendant la modification d'un noeud pour ne pas perdre l'indexe de l'element qu'on est en train de modifier
				jlist_noeuds.setEnabled(false);
				// recuperer le noeud corespond à l'element selectionné au niveau de la jlist_noeuds
			    ManageBook.noeud = ManageBook.noeuds.get(jlist_noeuds.getSelectedIndex());
				// remplir textArea_description et textArea_question et textField_idnoeud avec les information du noeud
				textArea_description.setText(ManageBook.noeud.getDescription()); 
				textArea_question.setText(ManageBook.noeud.getQuestion());
				textField_idnoeud.setText(""+ManageBook.noeud.getIdentifiant());
				// sauvegarder le id_noeud pour verifier si on a pas changer l'identifiant
				lastId = textField_idnoeud.getText();
				// recuperer les reponses du noeud (s'il a des reponses sinon on recupere une liste de reponse vide)
				ManageBook.reponses = ManageBook.noeud.getReponses();
				// on doit copier les reponse du noeud qu'on a recuperer car l'utilisateur peut changer son avis et click sur cancel
				// le risque c'est que l'utilisateur peut enlever des reponses ou bien modifier des reponses et puis il click sur cancel
				for(Reponse reponse : ManageBook.reponses){
				    ManageBook.cancelReponses.add(reponse.copy());	
				}
				 
				// vider le jlist_reponses
				model_reponses.removeAllElements();
				// remplir le jlist_reponses avec des element qui corespond aux reponses du noeud à modifier
				int cpt = 0;
				for(Reponse reponse : ManageBook.reponses){
					model_reponses.addElement("reponse "+(++cpt));
				}
				
				// on masque les button edit , remove
				btnNewButton_edit.setVisible(false);
				btnNewButton_remove.setVisible(false);
			    
				
			     
				 
            }
        });
		
		
		// button confirm
		
		
		btnNewButton_confirm.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {
				
				
				// on verifie si l'utilisateur a entré un identifiant entier
			    try{
						
					Integer.parseInt(textField_idnoeud.getText());
						
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Error : you forgot to add id_noeud or id_noeud is not integer", "Warning", JOptionPane.WARNING_MESSAGE);return;
				}			          
				
				
				
				// verifier l'existance de l'identifiant du noeud qu'on est en train de créer(on fait ça si l'utilisateur a changer id_noeud)
				
				if(Integer.parseInt(textField_idnoeud.getText()) != Integer.parseInt(lastId)){
					boolean id_exist = false;
					for(Noeud noeud : ManageBook.noeuds){
						if(Integer.parseInt(textField_idnoeud.getText()) == noeud.getIdentifiant()){
							id_exist = true; break;
						}
					}
						
					if(id_exist == true){
							JOptionPane.showMessageDialog(null, "Error : id_noeud already exists", "Warning", JOptionPane.WARNING_MESSAGE);return;
					}
				}
				// l'utilisateur peut changer l'identifiant donc on mets à jour l'identifiant du noeud et le jlist_noeuds  
				ManageBook.noeud.setIdentifiant(Integer.parseInt(textField_idnoeud.getText()));
				model_noeuds.set(jlist_noeuds.getSelectedIndex(),"noeud "+textField_idnoeud.getText());
				// modifier description et question du noeud
				ManageBook.noeud.setDescription(textArea_description.getText());
				ManageBook.noeud.setQuestion(textArea_question.getText());
				// vider id_noeud , textArea_description , textArea_description
				textField_idnoeud.setText("");
				textArea_description.setText("");
				textArea_question.setText("");
				// vider jlist_reponses et réinitialiser la liste des reponses avec une liste vide 
				// remarque :la modification des reponses du noeud se fait dans AddReponse et EditReponse
				model_reponses.removeAllElements();
				ManageBook.reponses = new ArrayList<Reponse>();
				// on réinitialise cancelReponses 
				ManageBook.cancelReponses = new ArrayList<Reponse>();
				
				// enlever la selection dans jlist_noeuds
				jlist_noeuds.clearSelection();
				// on remplace le button confirm avec le button add et on masque le button cancel 
				btnNewButton_confirm.setVisible(false);
				btnNewButton_add.setVisible(true);
				btnNewButton_cancel.setVisible(false);
				// activer la selection
				 jlist_noeuds.setEnabled(true);
				// on masque les button edit,remove
				btnNewButton_edit.setVisible(false);
				btnNewButton_remove.setVisible(false);
				
				 
            }
        });
		
		// button cancel
		
		
		btnNewButton_cancel.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {
				
				// on remplace la liste des reponses par cancelReponses pour eviter le risque de la modification (effectué par "+" "-" "edit")  
				ManageBook.noeud.setReponses(ManageBook.cancelReponses);
		        // mettre Gestion.noeud à null
				ManageBook.noeud = null;
                // vider id_noeud , textArea_ , textArea_question
				textField_idnoeud.setText("");
				textArea_description.setText("");
				textArea_question.setText("");
				// vider jlist_reponses et réinitialiser la liste des reponses avec une liste vide 
				// remarque :la modification des reponses du noeud se fait dans AddReponse et EditReponse
				model_reponses.removeAllElements();
				ManageBook.reponses = new ArrayList<Reponse>();
				// on réinitialise cancelReponses 
				ManageBook.cancelReponses = new ArrayList<Reponse>();
				// enlever la selection dans jlist_noeuds
				jlist_noeuds.clearSelection();
				// on remplace le button confirm avec le button add et on masque le button cancel 
				btnNewButton_confirm.setVisible(false);
				btnNewButton_add.setVisible(true);
				btnNewButton_cancel.setVisible(false);
				 // activer la selection
				 jlist_noeuds.setEnabled(true);
				// on masque les button edit,remove
				btnNewButton_edit.setVisible(false);
				btnNewButton_remove.setVisible(false); 
				 
            }
        });
		
		
		
		// button "+" (reponse)
		
		
		btnNewButton_plusR.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {// quand l'utilisateur click sur le button "+" alors toutes les instructions de cette methode seront executées
                addreponse.textArea_reponse.setText("");// vider le textArea_reponse
				addreponse.newCheckBox_dest.setSelected(false); // deselectionner le newCheckBox_dest
				// on a fait les deux actions précédentes car l'utilisateur peut ecrire dans la fenetre AddReponse et puis il ferme la fenetre sans ajouter une reponse
                // mais les traces de ce qui a ecrit reste donc comme ça on peut supprimer les traces qu'il a laissées 				
				addreponse.frame.setVisible(true); // on affiche la fenetre AddReponse (voir la classe AddReponse)
                
            }
        });
		
		// button "-" (reponse)
		
		
		btnNewButton_moinR.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {// quand l'utilisateur click sur le button "-" alors toutes les instructions de cette methode seront executées
                if(jlist_reponses.isSelectionEmpty() == false){ // si on a selectionné un element dans jlist_reponses
					ManageBook.reponses.remove(jlist_reponses.getSelectedIndex()); // on supprime la reponse qui corespond à l'element selectionné dans jlist_reponses
					model_reponses.removeAllElements(); // vider jlist_reponses
					// recharger jlist_reponses avec les reponses qu ils restent
					int i=0;
					for(Reponse reponse : ManageBook.reponses){
						model_reponses.addElement("reponse "+(++i));
					}
                }
            }
        });
		
		// "edit" (reponse)
		
		
		btnNewButton_editR.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {// quand l'utilisateur click sur le button edit(reponse) alors  on execute les instructions suivantes
				
				if(jlist_reponses.isSelectionEmpty() == false){// on verifie d'abord si on a selectionné un element dans jlist_reponses
				     // on fait ces deux action pour supprimer les traces que l'utilisateur peut laisser au cas ou l'utilisateur ferme la fentere AddReponse sans modifier
					 editreponse.newCheckBox_dest.setSelected(false);
					 editreponse.textField_destination.setText("");
					 // on mets dans le textArea_reponse de la fenetre AddReponse le text de la reponse qui corespond à l'element selectionné dans jlist_reponses
       				 editreponse.textArea_reponse.setText(ManageBook.reponses.get(jlist_reponses.getSelectedIndex()).getReponseText());
					 if(ManageBook.reponses.get(jlist_reponses.getSelectedIndex()).getNoeudDestination() != null ){ // si la reponse a une destination
						 editreponse.newCheckBox_dest.setSelected(true); // on selectionne le newCheckBox_dest de la fenetre AddReponse 
					     // on mets dans le textField_destination de la fenetre AddReponse l'identifiant du noeud destination de la reponse 
                         editreponse.textField_destination.setText(""+ManageBook.reponses.get(jlist_reponses.getSelectedIndex()).getNoeudDestination().getIdentifiant());						 
					 }
					 
					 editreponse.frame.setVisible(true);// on affiche la fenetre EditReponse (voir la classe EditReponse)
				   
			    }
			   
			}
                
            
        });
		
		
		

		
		
		
		
		
		
		
		
		
	
	}
	
	// cette methode permet de nettoyer tous les champs
	public void clear(){
		
		clearText();
		clearNoeuds();
		clearReponses();
		creatorNameText.setText("");
		storyNameText.setText("");
		textField_root.setText("");
		jlist_noeuds.clearSelection();
		jlist_noeuds.setEnabled(true);
		btnNewButton_cancel.setVisible(false);
		btnNewButton_confirm.setVisible(false);
		btnNewButton_edit.setVisible(false);
		btnNewButton_remove.setVisible(false);
		btnNewButton_add.setVisible(true);
		
		
	}
	
	// cette methode permet de nettoyer les champs textArea_description,textArea_question,textField_idnoeud
	public void clearText(){
		
		textField_idnoeud.setText("");
		textArea_description.setText("");
		textArea_question.setText("");
		
	}
	
	
	// cette methode permet de nettoyer la liste des noeuds crées + le champs jlist_noeuds 
	public void clearNoeuds(){
		
		ManageBook.noeuds.clear();
		model_noeuds.clear();
		
	}
	
	// cette methode permet de nettoyer la liste des reponses + le champs jlist_reponses
	public void clearReponses(){
		
		ManageBook.reponses.clear();
		model_reponses.clear();
	}
	
	// cette methode permet de modifier la police des deux textArea paragraphs (type,taille,couleur) + la fenetre addreponse et editreponse
	public static void setFontParagraph(String fontType,int fontSize,String fontColor){
		
		textArea_description.setFont(new Font(fontType, Font.PLAIN, fontSize));
		textArea_question.setFont(new Font(fontType, Font.PLAIN, fontSize));
		editreponse.textArea_reponse.setFont(new Font(fontType, Font.PLAIN, fontSize));
		addreponse.textArea_reponse.setFont(new Font(fontType, Font.PLAIN, fontSize));
		
		switch (fontColor) {
                        case "Black":
                            textArea_description.setForeground(Color.BLACK);
							textArea_question.setForeground(Color.BLACK);
                            editreponse.textArea_reponse.setForeground(Color.BLACK);
							addreponse.textArea_reponse.setForeground(Color.BLACK);
							break;
							
                        case "Blue":
                            textArea_description.setForeground(Color.BLUE);
							textArea_question.setForeground(Color.BLUE);
                            editreponse.textArea_reponse.setForeground(Color.BLUE);
							addreponse.textArea_reponse.setForeground(Color.BLUE);
							break;
                        case "Red":
                            textArea_description.setForeground(Color.RED);
							textArea_question.setForeground(Color.RED);
							editreponse.textArea_reponse.setForeground(Color.RED);
							addreponse.textArea_reponse.setForeground(Color.RED);
                            break;
        
		}
	
	
	}
	
	

}
