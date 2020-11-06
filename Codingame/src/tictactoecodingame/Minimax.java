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
public class Minimax extends AlgoRecherche{
	
	Joueur joueur1;         // Joueur qui commence la partie
    Joueur joueur2;         // Adversaire
    Joueur currentJoueur;   // C'est à son tour de jouer

    public Minimax(GrilleTicTacToe9x9 _plateau, Joueur _joueur1, Joueur _joueur2) {
        joueur1 = _joueur1;
        joueur2 = _joueur2;
        currentJoueur = joueur1;     // Le joueur1 commence la partie.
    }
    
    public int eval (Plateau  _plateau, int depth, Joueur _joueur) {
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
    	
    	//Simulation d'un coup "ami" (cherche à maximiser le score)
    	
    	if (_joueur == joueur1) {
    		int score1 = 0;
    		int bestScore = Integer.MIN_VALUE;
    		ArrayList<Coup> coupsPossibles = _plateau.getListeCoups(_joueur);
            int i;
            for(i = 0 ; i < coupsPossibles.size(); i++) {
            	_plateau.joueCoup(coupsPossibles.get(i));
            	score1 = eval(_plateau, depth - 1, joueur2);
            	_plateau.annuleDernierCoup();
            	bestScore = Math.max(score1, bestScore);
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
            	score1 = eval(_plateau, depth - 1, joueur1);
            	_plateau.annuleDernierCoup();
            	bestScore = Math.min(score1, bestScore);
            }
            return bestScore;
    	}
    } 
    
    @Override
    public Coup meilleurCoup(Plateau  _plateau, Joueur _joueur, boolean _ponder) {
    	
    	int bestScore = Integer.MIN_VALUE;
    	int score = Integer.MIN_VALUE;
    	Coup bestMove = null;
        ArrayList<Coup> coups = _plateau.getListeCoups(_joueur);
        int i;
        for(i = 0 ; i < coups.size(); i++) {
        	_plateau.joueCoup(coups.get(i));
        	//Nous choisissons ici à quelle profondeur nous souhaitons faire évoluer
        	//notre fonction d'évaluation
        	score = eval(_plateau, 3, joueur2);
        	_plateau.annuleDernierCoup();
	    	if (score > bestScore) {
	    		bestScore = score;
				bestMove = coups.get(i);
	    	}
        }
		return bestMove;  
    }
}
        
    