/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoecodingame;

import java.util.ArrayList;

/**
 *
 * @author thomas.bélair
 */
public class AlphaBetaPrio extends AlgoRecherche{
	
	Joueur joueur1;         // Joueur qui commence la partie
    Joueur joueur2;         // Adversaire
    Joueur currentJoueur;   // C'est à son tour de jouer

    public AlphaBetaPrio(GrilleTicTacToe9x9 _plateau, Joueur _joueur1, Joueur _joueur2) {
        joueur1 = _joueur1;
        joueur2 = _joueur2;
        currentJoueur = joueur1;     // Le joueur1 commence la partie.
    }
    
    public int eval (Plateau  _plateau, int depth, int alpha, int beta, Joueur _joueur) {
    	int score = 0;
    	//Cas d'arrêt : La récursivité s'arrête à la profondeur demandé ou la partie est terminée
    	if (depth <= 0 || _plateau.partieTerminee() == true) {
    		Joueur resultat = _plateau.vainqueur();
    		//On accorde un grand score positif en cas de victoire
			if (resultat == joueur1 ) {
				score = 1000*(depth + 1);//*(depth + 1) permet de valoriser les victoires rapides
			}
			//On donne un score fortement négatif en cas de défaite.
			if (resultat == joueur2 ) {
				score = -1000*(depth + 1);
			}
			//Score nul en cas d'égalité
		return score;
    	}
    	
    	//*********************************TRIGGER***********************************//
    	//Cette condition déclenche la partie PRIO uniquement quand une sous-grille est susceptible
    	//d'être remportée
    	//issue : Cette condition doit être incomplète car il arrive qu'elle ne soit pas 
    	//true pour tous les cas où elle devrait se déclencher
    	
    	
    	//if (((GrilleTicTacToe9x9)_plateau).grilleGagneeSav[((GrilleTicTacToe9x9)_plateau).nbCoupJoue] == true) {
    	
    	//*********************************TRIGGER***********************************//
    	
    	//*********************************PRIO***********************************//
    	//Cette partie attribue un score positif ou négatif à la prise d'une sous-grille
    	//par un des joueurs. L'ordre d'importance est le suivant : sous-grille centrale puis 
    	//sous-grilles des diagonales et enfin sous-grilles des côtés (points cardinaux).
    	
    		//Sous-grille centrale
    	
    		if(((GrilleTicTacToe9x9)_plateau).grille3x3[1][1] != null) {
    			if(cetteSousGrilleNATElleJamaisEtePrise(_plateau, 1,1)) {
	    			if (((GrilleTicTacToe9x9)_plateau).grille3x3[1][1].getJoueur().getIdJoueur() == 1 ) {
	    				score = -100*(depth + 1);
	    				return score;
	    			}
	    			/*if (((GrilleTicTacToe9x9)_plateau).grille3x3[1][1].getJoueur().getIdJoueur() == 0) {
	    				score = 100*(depth + 1);
	    				return score;
	    			}*/
    			}
    		}
    		
    		//Sous-grilles des diagonales
    		
    		if(((GrilleTicTacToe9x9)_plateau).grille3x3[0][0] != null) {
    			if(cetteSousGrilleNATElleJamaisEtePrise(_plateau, 0,0)) {
	    			if (((GrilleTicTacToe9x9)_plateau).grille3x3[0][0].getJoueur().getIdJoueur() == 1 ) {
	    				score = -10*(depth + 1);
	    				return score;
	    			}
	    			/*if (((GrilleTicTacToe9x9)_plateau).grille3x3[0][0].getJoueur().getIdJoueur() == 0) {
	    				score = 10*(depth + 1);
	    				return score;
	    			}*/
    			}
    		}
    		if(((GrilleTicTacToe9x9)_plateau).grille3x3[0][2] != null) {
    			if(cetteSousGrilleNATElleJamaisEtePrise(_plateau, 0,2)) {
	    			if (((GrilleTicTacToe9x9)_plateau).grille3x3[0][2].getJoueur().getIdJoueur() == 1 ) {
	    				score = -10*(depth + 1);
	    				return score;
	    			}
	    			/*if (((GrilleTicTacToe9x9)_plateau).grille3x3[0][2].getJoueur().getIdJoueur() == 0) {
	    				score = 10*(depth + 1);
	    				return score;
	    			}*/
    			}
    		}
    		if(((GrilleTicTacToe9x9)_plateau).grille3x3[2][0] != null) {
    			if(cetteSousGrilleNATElleJamaisEtePrise(_plateau, 2,0)) {
	    			if (((GrilleTicTacToe9x9)_plateau).grille3x3[2][0].getJoueur().getIdJoueur() == 1 ) {
	    				score = -10*(depth + 1);
	    				return score;
	    			}
	    			/*if (((GrilleTicTacToe9x9)_plateau).grille3x3[2][0].getJoueur().getIdJoueur() == 0) {
	    				score = 10*(depth + 1);
	    				return score;
	    			}*/
    			}
    		}
    		if(((GrilleTicTacToe9x9)_plateau).grille3x3[2][2] != null) {
    			if(cetteSousGrilleNATElleJamaisEtePrise(_plateau, 2,2)) {
	    			if (((GrilleTicTacToe9x9)_plateau).grille3x3[2][2].getJoueur().getIdJoueur() == 1) {
	    				score = -10*(depth + 1);
	    				return score;
	    			}
	    			/*if (((GrilleTicTacToe9x9)_plateau).grille3x3[2][2].getJoueur().getIdJoueur() == 0) {
	    				score = 10*(depth + 1);
	    				return score;
	    			}*/
    			}
    		}
    		
    		//Sous-grilles des côtés (points cardinaux)
    		
    		if(((GrilleTicTacToe9x9)_plateau).grille3x3[1][0] != null) {
    			if(cetteSousGrilleNATElleJamaisEtePrise(_plateau, 1,0)) {
	    			if (((GrilleTicTacToe9x9)_plateau).grille3x3[1][0].getJoueur().getIdJoueur() == 1 ) {
	    				score = -1*(depth + 1);
	    				return score;
	    			}
	    			/*if (((GrilleTicTacToe9x9)_plateau).grille3x3[1][0].getJoueur().getIdJoueur() == 0) {
	    				score = 1*(depth + 1);
	    				return score;
	    			}*/
    			}
    		}
    		if(((GrilleTicTacToe9x9)_plateau).grille3x3[0][1] != null) {
    			if(cetteSousGrilleNATElleJamaisEtePrise(_plateau, 0,1)) {
	    			if (((GrilleTicTacToe9x9)_plateau).grille3x3[0][1].getJoueur().getIdJoueur() == 1 ) {
	    				score = -1*(depth + 1);
	    				return score;
	    			}
	    			/*if (((GrilleTicTacToe9x9)_plateau).grille3x3[0][1].getJoueur().getIdJoueur() == 0) {
	    				score = 1*(depth + 1);
	    				return score;
	    			}*/
    			}
    		}
    		if(((GrilleTicTacToe9x9)_plateau).grille3x3[2][1] != null) {
    			if(cetteSousGrilleNATElleJamaisEtePrise(_plateau, 2,1)) {
	    			if (((GrilleTicTacToe9x9)_plateau).grille3x3[2][1].getJoueur().getIdJoueur() == 1 ) {
	    				score = -1*(depth + 1);
	    				return score;
	    			}
	    			/*if (((GrilleTicTacToe9x9)_plateau).grille3x3[2][1].getJoueur().getIdJoueur() == 0) {
	    				score = 1*(depth + 1);
	    				return score;
	    			}*/
    			}
    		}
    		if(((GrilleTicTacToe9x9)_plateau).grille3x3[1][2] != null) {
    			if(cetteSousGrilleNATElleJamaisEtePrise(_plateau, 1,2)) {
	    			if (((GrilleTicTacToe9x9)_plateau).grille3x3[1][2].getJoueur().getIdJoueur() == 1) {
	    				score = -1*(depth + 1);
	    				return score;
	    			}
	    			/*if (((GrilleTicTacToe9x9)_plateau).grille3x3[1][2].getJoueur().getIdJoueur() == 0) {
	    				score = 1*(depth + 1);
	    				return score;
	    			}*/
    			}
    		}
    		//*********************************PRIO***********************************/
    	//}
    	
    	//Simulation d'un coup "ami" (cherche à maximiser le score)
    	
    	if (_joueur == joueur1) {
    		int score1 = 0;
    		int bestScore = Integer.MIN_VALUE;
    		ArrayList<Coup> coupsPossibles = _plateau.getListeCoups(_joueur);
            int i;
            for(i = 0 ; i < coupsPossibles.size(); i++) {
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
    	
    	//Simulation d'un coup "ennemi" (cherche à minimiser le score)
    	
    	else {
    		int score1 = 0;
    		int bestScore = Integer.MAX_VALUE;
    		ArrayList<Coup> coupsPossibles = _plateau.getListeCoups(_joueur);
            int i;
            for(i = 0 ; i < coupsPossibles.size(); i++) {
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
        
        //Cette partie force notre algorithme à jouer dans la case centrale s'il joue en 1er
        
        if(_plateau.getDernierCoup() == null){//si on joue en premier, on joue au centre du centre
            return coups.get(40);
        }
        
        //*********************************FIRST***********************************//
        
        int i;
        for(i = 0 ; i < coups.size(); i++) {
        	_plateau.joueCoup(coups.get(i));
        	//Nous choisissons ici à quelle profondeur nous souhaitons faire évoluer
        	//notre fonction d'évaluation
        	score = eval(_plateau, 3, Integer.MIN_VALUE, Integer.MAX_VALUE, joueur2);
        	_plateau.annuleDernierCoup();
        	if (score > bestScore) {
        		bestScore = score;
				bestMove = coups.get(i);
        	}
        }  
		return bestMove;  
    }
    
    public boolean cetteSousGrilleNATElleJamaisEtePrise (Plateau  _plateau, int colonne, int ligne) {
    	//Nous remontons la partie jusqu'au 5ème coup car il faut dans un situation
    	//optimale au moins pour prendre une sous-grille
    	for(int i = ((GrilleTicTacToe9x9)_plateau).nbCoupJoue-1; i > 5; i--) {
    		//La sous-grille a déjà été prise
    		if (((GrilleTicTacToe9x9)_plateau).grille3x3Sav[i][colonne][ligne] != null) {
    			return false;
    		}
    	}
    //renvoie true s'il la sous-grille n'a pas été prise par le passé	
    return true;
    }
}
        
    