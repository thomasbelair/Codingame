package tictactoe;

public enum CaracteristiqueJoueur {
	MOI(joueur,1),
	ADV(joueur,-1),
	TIE(null,0);

	private Joueur joueur = null;
	private int score = (Integer) null ;
	   
	//Constructeur
	CaracteristiqueJoueur(Joueur joueur, int score){
		this.joueur = joueur;
		this.score = score;
	}
	   
	   
	  /*public static void main(String args[]){
	    Langage l1 = Langage.JAVA;
	    Langage l2 = Langage.PHP;
	      
	    l1.getEditor();
	    l2.getEditor();
	  }*/
}