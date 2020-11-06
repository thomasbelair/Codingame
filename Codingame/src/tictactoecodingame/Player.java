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
    	
    	long tempsDebut, tempsFin;
        double seconds;
    	
        JoueurOrdi joueurAlphaBetaTouteCoupe = new JoueurOrdi("AlphaBetaTouteCoupe");
        
        JoueurOrdi joueurAlgoRechercheAleatoire = new JoueurOrdi("Alea");
       
        GrilleTicTacToe9x9 grille = new GrilleTicTacToe9x9();
        
        // Remplacer ici l'algorithme aléatoire par votre algorithme. 
        // Créer une nouvelle classe qui hérite de la class AlgoRecherche
        //AlgoRechercheAleatoire alea  = new AlgoRechercheAleatoire( );   // L'ordinateur joue au hasard
        AlgoRechercheAleatoire alea  = new AlgoRechercheAleatoire();
        
        AlphaBetaTouteCoupe alphaBetaTouteCoupe = new AlphaBetaTouteCoupe(grille, joueurAlphaBetaTouteCoupe, joueurAlgoRechercheAleatoire);
        
        joueurAlphaBetaTouteCoupe.setAlgoRecherche(alphaBetaTouteCoupe);
        
        joueurAlgoRechercheAleatoire.setAlgoRecherche(alea); 
         
        Arbitre a = new Arbitre(grille, joueurAlphaBetaTouteCoupe , joueurAlgoRechercheAleatoire);
        
        //a.startNewGame(true);    // Demarre une partie en affichant la grille du jeu
        
        //On lance un chronomètre pour mesurer les performances sur 2000 parties
        //2000 parties pour réduire au maximum la variance des résultats 
        tempsDebut = System.nanoTime();
        // Pour lancer un tournoi de X parties en affichant la grille du jeu
        a.startTournament(2000 , true);
        
        tempsFin = System.nanoTime();
        seconds = (tempsFin - tempsDebut) / 1000000000F;
        System.out.println("Opération effectuée en: "+ Double.toString(seconds) + "secondes.");
        
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