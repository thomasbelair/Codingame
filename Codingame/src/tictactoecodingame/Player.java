package tictactoecodingame;

/**
 *
 * @author franck
 */

    /*--------------------------------------------------------*/
    /*                 Version jeu en local                   */
    /*--------------------------------------------------------*/
public class Player {

    public static void main(String args[]) {
        
        JoueurHumain humain = new JoueurHumain("Humain");
    	
        JoueurOrdi joueurAlpha = new JoueurOrdi("Alpha");
        
        JoueurOrdi joueurAlea2 = new JoueurOrdi("ALEA");
       
        GrilleTicTacToe9x9 grille = new GrilleTicTacToe9x9();
        
        // Remplacer ici l'algorithme aléatoire par votre algorithme. 
        // Créer une nouvelle classe qui hérite de la class AlgoRecherche
        //AlgoRechercheAleatoire alea  = new AlgoRechercheAleatoire( );   // L'ordinateur joue au hasard
        AlgoRechercheAleatoire alea2  = new AlgoRechercheAleatoire( );
        AlphaBeta alpha = new AlphaBeta(grille, joueurAlpha, joueurAlea2);
        
        joueurAlpha.setAlgoRecherche(alpha);                      
        joueurAlea2.setAlgoRecherche(alea2);
         
        Arbitre a = new Arbitre(grille, joueurAlpha , joueurAlea2);
       
        //a.startNewGame(true);    // Demarre une partie en affichant la grille du jeu
       
       // Pour lancer un tournooi de 100 parties en affichant la grille du jeu
       a.startTournament(300 , true);
        
    }
}

    /*--------------------------------------------------------*/
    /*                 Version Codin game                     */
    /*--------------------------------------------------------*/

    /*
    import java.util.Scanner;
    class Player {
       public static void main(String args[]) {
            Scanner in = new Scanner(System.in);
            CoupTicTacToe3x3 coup;
            JoueurHumain adversaire = new JoueurHumain("Adversaire");
            JoueurOrdi joueurOrdi = new JoueurOrdi("Ordi");
            AlgoRechercheAleatoire alea  = new AlgoRechercheAleatoire( );   // L'ordinateur joue au hasard
            joueurOrdi.setAlgoRecherche(alea);
            GrilleTicTacToe3x3 grille = new GrilleTicTacToe3x3();
            grille.init();
            while (true) {
                int opponentRow = in.nextInt();
                int opponentCol = in.nextInt();
                int validActionCount = in.nextInt();
                for (int i = 0; i < validActionCount; i++) {
                    int row = in.nextInt();
                    int col = in.nextInt();
                }
                if ( opponentCol != -1  ) {
                    coup = new CoupTicTacToe3x3(opponentCol, opponentRow, new Jeton(adversaire));
                    grille.joueCoup(coup);
                }
                coup = (CoupTicTacToe3x3) joueurOrdi.joue(grille);
                grille.joueCoup(coup);
                System.out.println(coup.getLigne() + " " + coup.getColonne() ); 
                System.out.flush();
            }
       }
    
}
*/