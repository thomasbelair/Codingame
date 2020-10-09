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

    public AlphaBeta() {
    	
    	//float score = 0;
    	
    	/*if(_plateau.partieTerminee() || depth == 0) {
    		return score;
    	}*/
    }
    
    public float alphabeta (Plateau  _plateau, int depth, Joueur _joueur) {
		float score = 0;    
    	Joueur resultat = _plateau.vainqueur();
    	if (depth == 0) {
    		if (resultat != null) {
				if (resultat.getNom() == "Ordi" ) {
					score = 1;
				}
				if (resultat.getNom() == "Humain" ) {
					score = -1;
				}
    		}
		return score;
    	}
    	if (_joueur.getNom() == "Ordi") {
    		float score1 = 0;
    		float bestScore = -10000;
    		ArrayList<Coup> coupsPossibles = _plateau.getListeCoups(_joueur);
            int i;
            for(i = 0 ; i < coupsPossibles.size(); i++) {
            	_plateau.joueCoup(coupsPossibles.get(i));
            	score1 = alphabeta(_plateau, depth - 1, _joueur);
            	_plateau.annuleDernierCoup();
            	bestScore = Math.max(score1, bestScore);
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
            	score1 = alphabeta(_plateau, depth - 1, _joueur);
            	_plateau.annuleDernierCoup();
            	bestScore = Math.min(score1, bestScore);
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
        	score = alphabeta(_plateau, 2, _joueur);
        	_plateau.annuleDernierCoup();

        	if (score > bestScore) {
        		bestScore = score;
				bestMove = coups.get(i);
        	}
        }
		return bestMove;  
    }  
}
        
    