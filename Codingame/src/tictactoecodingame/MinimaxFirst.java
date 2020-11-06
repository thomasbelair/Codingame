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
public class MinimaxFirst extends AlgoRecherche{
	
	Joueur joueur1;         // Joueur qui commence la partie
    Joueur joueur2;         // Adversaire
    Joueur currentJoueur;   // C'est � son tour de jouer

    public MinimaxFirst(GrilleTicTacToe9x9 _plateau, Joueur _joueur1, Joueur _joueur2) {
        joueur1 = _joueur1;
        joueur2 = _joueur2;
        currentJoueur = joueur1;     // Le joueur1 commence la partie.
    }
    
    public int eval (Plateau  _plateau, int depth, Joueur _joueur) {
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
            	_plateau.joueCoup(coupsPossibles.get(i));
            	score1 = eval(_plateau, depth - 1, joueur2);
            	_plateau.annuleDernierCoup();
            	bestScore = Math.max(score1, bestScore);
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
        
        //*********************************FIRST***********************************//
        
        //Cette partie force notre algorithme � jouer dans la case centrale s'il joue en 1er
        
        if(_plateau.getDernierCoup() == null){//si on joue en premier, on joue au centre du centre
            return coups.get(40);
        }
        
        //*********************************FIRST***********************************//
        
        int i;
        for(i = 0 ; i < coups.size(); i++) {
        	_plateau.joueCoup(coups.get(i));
        	//Nous choisissons ici � quelle profondeur nous souhaitons faire �voluer
        	//notre fonction d'�valuation
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
        
    