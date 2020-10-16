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
    	
        JoueurOrdi joueurOrdi = new JoueurOrdi("Alpha");
        
        JoueurOrdi joueurOrdi2 = new JoueurOrdi("Ordi2");
       
        
        // Remplacer ici l'algorithme al�atoire par votre algorithme. 
        // Cr�er une nouvelle classe qui h�rite de la class AlgoRecherche
        //AlgoRechercheAleatoire alea  = new AlgoRechercheAleatoire( );   // L'ordinateur joue au hasard
        AlgoRechercheAleatoire alea2  = new AlgoRechercheAleatoire( );
        AlphaBeta alpha = new AlphaBeta(joueurOrdi, humain);
        
        joueurOrdi.setAlgoRecherche(alpha);                      
        joueurOrdi2.setAlgoRecherche(alea2);
             
        GrilleTicTacToe3x3 grille = new GrilleTicTacToe3x3();
         
        Arbitre a = new Arbitre(grille, joueurOrdi , humain);
       
        a.startNewGame(true);    // Demarre une partie en affichant la grille du jeu
       
       // Pour lancer un tournooi de 100 parties en affichant la grille du jeu
       //a.startTournament(300 , false);
        
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