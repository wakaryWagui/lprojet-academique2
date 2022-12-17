package graphs;

public class Reponse {
	private String reponseText; // permet de stocker le texte de la reponse
	private Noeud NoeudDestination; // permet de stocker la r�ference du prochain noeud
    private String destinationID; // on aura besoin de cette attribut pendant la r�cup�ration de l'histoire � partir d'un fichier xml pour la jouer
	
	// constructeur qui initialise le texte de la reponse et la reference du prochain noeud 
	public Reponse(String reponseText,Noeud NoeudDestination) {
		this.reponseText = reponseText;			
		this.NoeudDestination = NoeudDestination;
	}
	
	// cette methode permet de cr�er une copier de la r�ponse courante
	public Reponse copy(){
		return new Reponse(reponseText,NoeudDestination);
		
	}
	// getters and setters
	
	// pour r�cuperer le texte de la r�ponse
	public String getReponseText() {
		return this.reponseText;
	}
    
	// pour modifier le texte de la r�ponse 
	public void setReponseText(String reponseText) {
		this.reponseText = reponseText;
	}
    
	// pour r�cuperer le noeud destination
	public Noeud getNoeudDestination() {
		return this.NoeudDestination;
	}
	
	// pour modifier le noeud destination
	public void setNoeudDestination(Noeud NoeudDestination) {
		this.NoeudDestination = NoeudDestination;
	}
	
	// pour r�cuperer l'identifiant de la destination
	public String getDestinationID() {
		return this.destinationID;
	}
	
	// pour modifier l'identifiant de la destination
	public void setDestinationID(String destinationID) {
		this.destinationID = destinationID;
	}
	
	
	
}
