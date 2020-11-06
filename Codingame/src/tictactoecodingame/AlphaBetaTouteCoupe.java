/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoecodingame;

import java.util.ArrayList;

/**
 *
 * @author thomas.b�lair
 */
public class AlphaBetaTouteCoupe extends AlgoRecherche{
	
	Joueur joueur1;         // Joueur qui commence la partie
    Joueur joueur2;         // Adversaire
    Joueur currentJoueur;   // C'est � son tour de jouer

    public AlphaBetaTouteCoupe(GrilleTicTacToe9x9 _plateau, Joueur _joueur1, Joueur _joueur2) {
        joueur1 = _joueur1;
        joueur2 = _joueur2;
        currentJoueur = joueur1;     // Le joueur1 commence la partie.
    }
    
    public int eval (Plateau  _plateau, int depth, int alpha, int beta, Joueur _joueur) {
    	int score = 0;
    	//Cas d'arr�t : La r�cursivit� s'arr�te � la profondeur demand� ou la partie est termin�e
    	if (depth <= 0 || _plateau.partieTerminee() == true) {
    		Joueur resultat = _plateau.vainqueur();
    		//On accorde un grand score positif en cas de victoire
			if (resultat == joueur1 ) {
				score = 1000*(depth + 1);//*(depth + 1) permet de valoriser les victoires rapides
			}
			//On donne un score fortement n�gatif en cas de d�faite.
			if (resultat == joueur2 ) {
				score = -1000*(depth + 1);
			}
			//Score nul en cas d'�galit�
		return score;
    	}
    	
    	//Simulation d'un coup "ami" (cherche � maximiser le score)
    	
    	if (_joueur == joueur1) {
    		int score1 = 0;
    		int bestScore = Integer.MIN_VALUE;
    		ArrayList<Coup> coupsPossibles = _plateau.getListeCoups(_joueur);
            int i;
            for(i = 0 ; i < coupsPossibles.size(); i++) {
            	
            	//*********************************COUPE EN PROFONDEUR***********************************// 
            	
            	//Il s'agit de la m�me d�marche que pour la partie "COUPE" mais en profondeur
            	
            	if(LaTechnique (_plateau, coupsPossibles.get(i))) {            		
            		score = -10*(depth+1);
            	}
            	
            	//*********************************COUPE EN PROFONDEUR***********************************/
            	
            	_plateau.joueCoup(coupsPossibles.get(i));
            	score1 = eval(_plateau, depth - 1, alpha, beta, joueur2);
            	_plateau.annuleDernierCoup();
		        //Coupure Beta	
		    	bestScore = Math.max(score1, bestScore);
		    	alpha = Math.max(alpha, score1);
		    	if (beta <= alpha) break;
            }
            return bestScore;
    	}
    	
    	//Simulation d'un coup "ennemi" (cherche � minimiser le score)
    	
    	else {
    		int score1 = 0;
    		int bestScore = Integer.MAX_VALUE;
    		ArrayList<Coup> coupsPossibles = _plateau.getListeCoups(_joueur);
            int i;
            for(i = 0 ; i < coupsPossibles.size(); i++) {
            	
            	//*********************************COUPE EN PROFONDEUR 2***********************************//            	
            	
            	//La d�marche ici est de pousser notre algorithme � ne pas jouer certaines 
            	//cases. Celles qui permetteraient � l'adversaire de jouer o� il le souhaite.
            	//On oblige donc l'adversaire � les jouer pour pouvoir jouer o� nous le
            	//souhaitons au prochain tour
            	
            	
            	if(LaTechnique (_plateau, coupsPossibles.get(i))) {            		
            		score1 = 10*(depth+1);
            	}
            	
            	//*********************************COUPE EN PROFONDEUR 2***********************************//
            	
            	_plateau.joueCoup(coupsPossibles.get(i));
            	score1 = eval(_plateau, depth - 1, alpha, beta, joueur1);
            	_plateau.annuleDernierCoup();
            	//Coupure alpha
            	bestScore = Math.min(score1, bestScore);
            	beta = Math.min(beta, score1);
            	if (beta <= alpha) break;
            }
            return bestScore;
    	}
    } 
    
    @Override
    public Coup meilleurCoup(Plateau  _plateau, Joueur _joueur, boolean _ponder) {
    	
    	int bestScore = Integer.MIN_VALUE;
    	int score = Integer.MIN_VALUE;
    	Coup bestMove = null;
    	
    	_plateau.sauvegardePosition(((GrilleTicTacToe9x9)_plateau).nbCoupJoue);
    	
        ArrayList<Coup> coups = _plateau.getListeCoups(_joueur);
        
        //*********************************FIRST***********************************//
        
        //Cette partie force notre algorithme � jouer dans la case centrale s'il joue en 1er
        
        if(_plateau.getDernierCoup() == null){//si on joue en premier, on joue au centre du centre
            return coups.get(40);
        }
        
        //*********************************FIRST***********************************//
        
        int i;
        for(i = 0 ; i < coups.size(); i++) {
        	
        	//*********************************COUPE***********************************//
        	
        	//On p�nalise fortement un coup direct qui permetterait � l'adversaire
        	//de jouer o� il le souhaite au tour suivant
        	
        	if(LaTechnique (_plateau, coups.get(i))) {            		
        		score = -900;
        	}
        	
        	//*********************************COUPE***********************************/ 
        	
        	_plateau.joueCoup(coups.get(i));
        	//Nous choisissons ici � quelle profondeur nous souhaitons faire �voluer
        	//notre fonction d'�valuation
        	score = eval(_plateau, 3, Integer.MIN_VALUE, Integer.MAX_VALUE, joueur2);
        	_plateau.annuleDernierCoup();
        	if (score > bestScore) {
        		bestScore = score;
				bestMove = coups.get(i);
        	}
        }  
		return bestMove;  
    }
    
    //fonction permettant de r�cup�rer la colonne et la ligne du dernier coup pour faire
    //des traitement
    public boolean LaTechnique (Plateau  _plateau, Coup coup) {
    			
    			int col = ((CoupTicTacToe)coup).getColonne();
    			int lig = ((CoupTicTacToe)coup).getLigne();
    			
    			//On d�termine la sous-grille dans laquelle le prochain coup devra �tre jou�.
    			
    			int c = col % 3;
    			int l = lig % 3;
    			
    			return ((GrilleTicTacToe9x9)_plateau).isSousGrillePrise(_plateau, c ,l);
    }
}
        
    