package graphs;

import java.util.ArrayList;

public class Noeud {
	private String description; // permet de stocker description du noeud
	private String question; // permet de stocker la question à poser
	private ArrayList<Reponse> reponses; // permet de stocker la liste des reponses 
	private int identifiant;  // permet de stocker l'identifiant du noeud
	
	// constructeur qui initialise la description et la question du noeud + l'identifiant et qui crée la liste de reponses
	public Noeud(String description,String question,int identifiant) {
		this.description = description;
		this.question = question;
		this.reponses = new ArrayList<Reponse>();
		this.identifiant = identifiant;
		
	}
	
	//cette methode permet de savoir si un noeud est terminal "terminal = qui n'a aucune reponse"
	public boolean noeudTerminal() {
		return this.reponses.size() == 0;
	}
	
	//Ajouter une reponse dans la liste de reponses
	public void addReponse(Reponse r) {
		this.reponses.add(r);
	}
	
	// getters and setters 
	
	// pour récuperer le nobre de reponse
	public int getNbreReponse() {
		return this.reponses.size();
	}
	
	// pour récuperer une reponse d'indice i
	public Reponse getReponseValue(int i) {
		return this.reponses.get(i);
	}
	
	// pour recupérer la description du noeud 
	public String getDescription() {
		return this.description;
	}
	
	// pour modifier la description du noeud
	public void setDescription(String description) {
		this.description = description;
	}

	// pour recupérer la question du noeud
	public String getQuestion() {
		return this.question;
	}
	
	// pour modifier la question du noeud
	public void setQuestion(String question) {
		this.question = question;
	}
	
	// pour recupérer l'identifiant du noeud
	public int getIdentifiant() {
		return this.identifiant;
	}
	
	// pour modifier l'identifiant du noeud
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}
	
	// pour recupérer la liste des reponses
	public ArrayList<Reponse> getReponses() {
		return this.reponses;
	}
	
    // pour ajouter la liste des reponses 
	public void setReponses(ArrayList<Reponse> reponses) {
		this.reponses = reponses;
	}
	
	// pour afficher la description et la question du noeud
    @Override
	public String toString() {
		return this.description+"\n"+this.question;
	}

	
}
