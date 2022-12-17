package tools;

import graphs.*;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.util.*;



import graphs.Noeud;
import graphs.Reponse;

public class XmlBackup {
	private DocumentBuilderFactory dbFactory;
	   private DocumentBuilder docBuilder;
	   private Document doc ;
	   
	   private TransformerFactory transformerFactory;
	   private Transformer transformer;
	   private DOMSource source;
	   private StreamResult resultat;    
	   
	   
	   public XmlBackup(){
		   
	   }
	   
	   
	   // cette methode re�oit en parametre une liste de noeuds representent une histoire + le nom de l'histoire + le nom de l'auteur + l'identifiant du noeud racine 
	   // et elle permet de cr�er un fichier xml qui contient l'histoire  
	   public void createXmlFile(ArrayList<Noeud> story,String storyName,String creatorName,String root_id) {
	 
	      try {
			  
			  dbFactory = DocumentBuilderFactory.newInstance();
	          docBuilder = dbFactory.newDocumentBuilder();
	          doc = docBuilder.newDocument();
			  
	          // ajouter un element racine "histoire"
	          Element racine = doc.createElement("histoire");
	          doc.appendChild(racine);
			  
			  // r�cup�rer la racine
			  Node histoire = doc.getFirstChild();
			  
			  // cr�er un element about qui va contenir des information sur l'histoire (nom de l'histoire,auteur,l'dentifiant du noeud racine)
			  Element about = doc.createElement("About");
			  // cr�er un Name element et ajouter le contenu de storyName
			  Element name = doc.createElement("Name");
			  name.appendChild(doc.createTextNode(storyName));
			  // cr�er un Author element et ajouter le contenu de creatorName
			  Element author = doc.createElement("Author");
			  author.appendChild(doc.createTextNode(creatorName));
			  // cr�er un rootID element et ajouter le contenu de root_id
			  Element rootID = doc.createElement("Root");
			  rootID.appendChild(doc.createTextNode(root_id));
			  
			  // ajouter les elements qu'on vient de cr�er � l'�l�ment about
			  about.appendChild(name);
			  about.appendChild(author);
			  about.appendChild(rootID);
			  histoire.appendChild(about);
			  
			  for(Noeud noeud : story){// parcourir tous les noeuds de l'histoire pour les ajouter dans l'histoire		
									
					  
					  // cr�er un element noeud
					  Element noeudelt = doc.createElement("Noeud");
					  // cr�er un description element et ajouter le text du paragraph1
					  Element description = doc.createElement("Paragraph1");
					  description.appendChild(doc.createTextNode(noeud.getDescription()));
					  // cr�er un question element et ajouter le text du paragraph2
					  Element question = doc.createElement("Paragraph2");
					  question.appendChild(doc.createTextNode(noeud.getQuestion()));
					  // ajouter les deux paragraph element � l'element noeud
					  noeudelt.appendChild(description);
					  noeudelt.appendChild(question);
					  
					  
					  // ajouter les elements reponse
					  for(Reponse rep : noeud.getReponses()){// parcourir les r�ponses
						  
						  Element reponse = doc.createElement("Reponse"); // cr�er reponse element
						  reponse.appendChild(doc.createTextNode(rep.getReponseText())); // ajouter le text de la reponse
						  if(rep.getNoeudDestination() != null){
							  // ajouter l'attribut destination � la reponse avec comme valeur l'identifiant de la destination
							  reponse.setAttribute("destination",Integer.toString(rep.getNoeudDestination().getIdentifiant())); 
						  }else{ // sinon on mets la chaine null pour destination
							  reponse.setAttribute("destination","null");
						  }
						  noeudelt.appendChild(reponse); // ajouter l'element reponse � l'�l�ment noeud
							
					  }
					  
					  noeudelt.setAttribute("id",Integer.toString(noeud.getIdentifiant())); // ajouter l'attribut id au noeud avec comme valeur l'identifiant du noeud
					  
					  histoire.appendChild(noeudelt); // ajouter l'element noued � l'element racine "histoire"
				  
			  }		
					
		      // cr�er un fichier xml et ajouter l'histoire dans le fichier qu'on vient de cr�er 		 			  
			  
	          transformerFactory = TransformerFactory.newInstance();
	          transformer = transformerFactory.newTransformer();
	          source = new DOMSource(doc);
	          resultat = new StreamResult(new File("./stories/"+storyName + ".xml"));
			  
			  
	          
	          transformer.transform(source, resultat);
	 
	 
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   
	   }
	   
	   
	   // cette methode permet de lire un fichier xml qui contient une histoire et elle retourne un noeud qui represente la racine de l'histoire
	   // on passe en parametre le chemin relatif du fichier histoire 
	   public Noeud readXmlFile(String file){
		   
		   
		   // cr�er un liste de noueds vide 
		   ArrayList<Noeud> story = new ArrayList<>(); // cette liste stocke les noeuds de l'histoire 
		   Noeud racineStory = null; // �a sert � stocker la racine de l'histoire
		   
		   try {
			  
			  dbFactory = DocumentBuilderFactory.newInstance();
	          docBuilder = dbFactory.newDocumentBuilder();
	          doc = docBuilder.parse("./stories/"+file);
			  
	         
			  // r�cup�rer la racine du fichier xml
			  Node histoire = doc.getFirstChild();
			  
			  // R�cup�rer la liste des noeuds fils de histoire
	          NodeList list = histoire.getChildNodes();
			  
			  //recuperer l'element About
			  Node about = list.item(0);
			  // r�cuperer les fils de About 
			  NodeList aboutList = about.getChildNodes();
			  // R�cup�rer la valeur de l'element Root qui represente l'dentifiant du noeud racine de l'histoire 
			  int id_root = Integer.parseInt(aboutList.item(2).getTextContent());
			  
			  
			  
			  
			  
			  // parcourir les elements noeuds
			  for (int i = 1; i < list.getLength(); i++){
				  NamedNodeMap attr = list.item(i).getAttributes(); //r�cup�rer les attributs du noeud courant
				  NodeList list2 = list.item(i).getChildNodes(); // r�cup�rer les elements fils du noeud courant
				  // cr�er un noued en donnant les deux paragraph et l'identifiant
				  Noeud noeud = new Noeud(list2.item(0).getTextContent(),list2.item(1).getTextContent(),Integer.parseInt(attr.getNamedItem("id").getNodeValue()));
				  // parcourir les elements r�ponses
				  for (int j=2 ; j < list2.getLength() ; j++){ 
						   Reponse reponse = new Reponse(list2.item(j).getTextContent(),null); // cr�er un reponse dont le text est la valeur de l'element reponse
						   NamedNodeMap attr2 = list2.item(j).getAttributes(); //r�cup�rer les attributs de la reponse courante
						   reponse.setDestinationID(attr2.getNamedItem("destination").getNodeValue());// on r�cupere la valeur de destination et on la mets dans la reponse qu'on a cr�er 
						   noeud.addReponse(reponse); // ajouter la reponse au noeud	   
				  }
				  story.add(noeud); // ajouter le noeud dans la liste des noeuds
			  }
			  
			  
			  for(Noeud noeud : story){// on cherche le noeud racine a partir de l'identifiant de la racine qu'on a enregister "id_root" 
				  if(noeud.getIdentifiant() == id_root) {
					  racineStory = noeud ;break;
				  }
			  }	
			  
			  // faire le lien entre les reponses et les noeuds destination
			  
			  for(Noeud noeud : story){// pour chaque noeud
				  for(Reponse reponse : noeud.getReponses()){// on parcourt les reponses
					  if(reponse.getDestinationID().equals("null") == false){ // si la reponse a une destination 
						  for(Noeud noeudDest : story){ // on cherchre la destination pour faire le lien
							  if(noeudDest.getIdentifiant() == Integer.parseInt(reponse.getDestinationID())){
								  reponse.setNoeudDestination(noeudDest);break;
							  }
						  }
					  }
				  }
			  }
			  
		      
	          
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		   
		  
		  return racineStory; 
	   } 
	   
	  
	   



}
