package createStory;

import java.util.ArrayList;

import graphs.Noeud;
import graphs.Reponse;

public class ManageBook {
	// cette class permet de g�rer la cr�ation d'une histoire
	
	public static ArrayList<Noeud> noeuds = new ArrayList<Noeud>(); // premet de stocker les noeuds cr�e par l'utilisateur
	public static ArrayList<Reponse> reponses = new ArrayList<Reponse>(); // permet de stocker les reponses du noeud qu'on est en train de cr�er ou bien du noeud qu'on veut modifier
	public static Noeud noeud; // permet de stocker un noeud
	
	// on l'utilise pour eviter la modification des reponses d'un noeud si l'utilisateur click sur le button cancel 
	public static ArrayList<Reponse> cancelReponses = new ArrayList<Reponse>(); 

}
