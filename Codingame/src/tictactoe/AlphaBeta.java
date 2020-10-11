/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.ArrayList;
//import java.util.Random;

/**
 *
 * @author thomas.bélair
 */
public class AlphaBeta extends AlgoRecherche{
	
	Joueur joueur1;         // Joueur qui commence la partie
    Joueur joueur2;         // Adversaire
    Joueur currentJoueur;   // C'est à son tour de jouer

    public AlphaBeta(Joueur _joueur1, Joueur _joueur2) {
        joueur1 = _joueur1;
        joueur2 = _joueur2;
        currentJoueur = joueur1;     // Le joueur1 commence la partie.
    }
    
    public float alphabeta (Plateau  _plateau, int depth, float alpha, float beta, Joueur _joueur) {
		float score = 0;
		_plateau.partieGagnee();
    	Joueur resultat = _plateau.vainqueur();
    	if (depth == 0 || resultat != null) {
			if (resultat == joueur1 ) {
				score = 1;
			}
			if (resultat == joueur2 ) {
				score = -1;
			}
		resultat = null;	
		return score;
    	}
    	if (_joueur == joueur1) {
    		float score1 = 0;
    		float bestScore = -10000;
    		ArrayList<Coup> coupsPossibles = _plateau.getListeCoups(_joueur);
            int i;
            for(i = 0 ; i < coupsPossibles.size(); i++) {
            	_plateau.joueCoup(coupsPossibles.get(i));
            	score1 = alphabeta(_plateau, depth - 1, alpha, beta, joueur2);
            	_plateau.annuleDernierCoup();
            	bestScore = Math.max(score1, bestScore);
            	alpha = Math.max(alpha, score1);
            	if (beta <= alpha) break;
            }
            return bestScore;
    	}
    	else {
    		float score1 = 0;
    		float bestScore = 10000;
    		ArrayList<Coup> coupsPossibles = _plateau.getListeCoups(_joueur);
            int i;
            for(i = 0 ; i < coupsPossibles.size(); i++) {
            	_plateau.joueCoup(coupsPossibles.get(i));
            	score1 = alphabeta(_plateau, depth - 1, alpha, beta, joueur1);
            	_plateau.annuleDernierCoup();
            	bestScore = Math.min(score1, bestScore);
            	beta = Math.min(beta, score1);
            	if (beta <= alpha) break;
            }
            return bestScore;
    	}
    	
    }
    
    
    
    @Override
    public Coup meilleurCoup(Plateau  _plateau, Joueur _joueur, boolean _ponder) {
        
    	float bestScore =-10000;
    	float score = -10000;
    	Coup bestMove = null;
    	
        ArrayList<Coup> coups = _plateau.getListeCoups(_joueur);
        int i;
        for(i = 0 ; i < coups.size(); i++) {
        	_plateau.joueCoup(coups.get(i));
        	score = alphabeta(_plateau, 3, -10000, 10000, joueur2);
        	_plateau.annuleDernierCoup();

        	if (score > bestScore) {
        		bestScore = score;
				bestMove = coups.get(i);
        	}
        }
		return bestMove;  
    }  
}
        
    