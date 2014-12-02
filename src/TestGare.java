import java.util.ArrayList;
import java.util.List;

/**
 * Created by 14007427 on 18/11/14.
 */

public class TestGare {

    private static final int NB_MAX_TRAIN = 5;
    private static final int NB_MAX_VOYAGEURS = 250;
    private static final int NB_GARES = 3;

    private static ServeurBilletterie monServeurBilletterie;
    private static List<Gare> listeGare;

    public static void main(String[] args){

        listeGare = new ArrayList<Gare>();
        monServeurBilletterie = new ServeurBilletterie();

        //Création gare
        for (int i=0; i<NB_GARES; i++){
            Gare gare = new Gare("Gare n°"+i, monServeurBilletterie);
            listeGare.add(gare);
        }

        for (int i=0; i<NB_MAX_VOYAGEURS; i++){
            new Voyageurs("Voyageur n°"+i).start();
        }

        for (int i=0; i<NB_MAX_TRAIN; i++){
            new Train("Train n°"+i).start();
        }
    }

    synchronized public static List<Gare> getListeGare(){
        return listeGare;
    }
}
