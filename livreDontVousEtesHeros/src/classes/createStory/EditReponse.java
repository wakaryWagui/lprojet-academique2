package createStory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import graphs.Noeud;

public class EditReponse {
	// on définit une fenetre avec ces élements
		JFrame frame;
		JLabel lblNewLabel_reponse;
		JScrollPane scrollPane_reponse;
		JTextArea textArea_reponse;
		JTextField textField_destination;
		JButton btnNewButton_confirm ;
		JLabel lblNewLabel_text;
		JLabel lblNewLabel_dest;
		JCheckBox newCheckBox_dest;
		
		public EditReponse(){
			
			// créer la fenetre et ces élements
			frame = new JFrame("edit reponse");
		    lblNewLabel_reponse = new JLabel("reponse");
	        textArea_reponse = new JTextArea();
		    scrollPane_reponse = new JScrollPane(textArea_reponse);
			textField_destination = new JTextField();
		    btnNewButton_confirm = new JButton("confirm");
		    lblNewLabel_text = new JLabel("<html>* veuillez saisir le text de <br>la reponse</html>");
		    lblNewLabel_dest = new JLabel("<html>* veuillez saisir le numero du noeud<br> destination</html>");
		    newCheckBox_dest = new JCheckBox("Destination");

			
			// spécifier la taille de la fenetre
	        frame.setBounds(100, 100, 450, 300);                            
			frame.getContentPane().setLayout(null);
			
			// spécifier la taille et l'emplacements des élements de la fenetre
			lblNewLabel_reponse.setBounds(28, 36, 55, 14);
			scrollPane_reponse.setBounds(28, 61, 196, 83);
			textField_destination.setBounds(138, 179, 61, 20);
			textField_destination.setColumns(10);
			btnNewButton_confirm.setBounds(310, 215, 89, 23);
			lblNewLabel_text.setBounds(234, 67, 178, 29);
			lblNewLabel_dest.setBounds(210, 182, 200, 30);
			newCheckBox_dest.setBounds(29, 178, 89, 23);
			
			// ajouter les élements dans la fenetre
			frame.getContentPane().add(scrollPane_reponse);
			frame.getContentPane().add(lblNewLabel_reponse);
			frame.getContentPane().add(textField_destination);
			frame.getContentPane().add(btnNewButton_confirm);
			frame.getContentPane().add(lblNewLabel_text);
			frame.getContentPane().add(lblNewLabel_dest);
			frame.getContentPane().add(newCheckBox_dest);
			
			
			// masquer le textField_destination et lblNewLabel_dest	
			textField_destination.setVisible(false);
			lblNewLabel_dest.setVisible(false);
			
			// button confirm
			
			btnNewButton_confirm.addActionListener(new ActionListener() {    
	            @Override
	            public void actionPerformed(ActionEvent e) {// quand l'utilisateur click sur le button confirm alors toutes les instructions de cette methode seront executées
	                
					
					if(newCheckBox_dest.isSelected()){ // si le newCheckBox_dest est sélectionné = l'utilisateur veut associer la reponse à un noeud qui existe  
					     // on verifie si la liste des noeud est vide
						 if(ManageBook.noeuds.size() == 0){
							 JOptionPane.showMessageDialog(null, "Error : list of node is empty", "Warning", JOptionPane.WARNING_MESSAGE);return;
						 }
						 // on verifie si l'utilisateur a entré un identifiant entier
						 try {
						 	
						   Integer.parseInt(textField_destination.getText());
						 	
					     }catch(NumberFormatException ex){
							JOptionPane.showMessageDialog(null, "Error : no destination or destination is not integer", "Warning", JOptionPane.WARNING_MESSAGE);return;
					     }
						 
						 //on verifie si le noeud destination existe 
						 Noeud destination = null;
						 boolean exist = false; // on suppose qu'il n'existe pas
						 for(Noeud noeud : ManageBook.noeuds){ // on parcourt tous les noeuds
							if(noeud.getIdentifiant() == Integer.parseInt(textField_destination.getText())){ // si le noeud destination existe
								exist = true;destination = noeud; break;  // on sauvegarde la destination
							}
						 }
						 
						 if(exist == false){
							 JOptionPane.showMessageDialog(null, "Error : node destination does not exist", "Warning", JOptionPane.WARNING_MESSAGE);return;
						 }
						 
						 // modifier la destination de la reponse qui corespond à l'element sélectionné dans le jlist_reponses
					     ManageBook.reponses.get(CreateBookPage.jlist_reponses.getSelectedIndex()).setNoeudDestination(destination);

	                }
					
					// modifier le text de la reponse qui corespond à l'element sélectionné dans le jlist_reponses
					ManageBook.reponses.get(CreateBookPage.jlist_reponses.getSelectedIndex()).setReponseText(textArea_reponse.getText());
			        				
			        if(newCheckBox_dest.isSelected() == false){ // si le newCheckBox_dest n'est pas sélectionné
						
						// mettre la destination de la reponse qui corespond à l'element sélectionné dans le jlist_reponses à null 
						// dans le cas ou la reponse avait une destination et puis l'utilisateur veut couper le lien
					    
						ManageBook.reponses.get(CreateBookPage.jlist_reponses.getSelectedIndex()).setNoeudDestination(null);
						 
					}
			
			        
			        frame.setVisible(false);
					
					
	                
	            }
	        });
			
			
			
			// newCheckBox_dest
			
			newCheckBox_dest.addItemListener(new ItemListener() {            
	            @Override
	            public void itemStateChanged(ItemEvent e) {// les instructions à executer quand le newCheckBox_dest est sélectionné
	                if(newCheckBox_dest.isSelected()){// si newCheckBox_dest est selectionné
						textField_destination.setVisible(true); // afficher textField_destination
						lblNewLabel_dest.setVisible(true); // afficher lblNewLabel_dest
					}else{// si newCheckBox_dest n'est pas selectionné
						textField_destination.setVisible(false); // masquer textField_destination
						lblNewLabel_dest.setVisible(false); // masquer lblNewLabel_dest
					
					}
	            }
	        });
			
			
		
		}
		
			

}
