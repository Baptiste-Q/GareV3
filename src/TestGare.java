import java.util.ArrayList;
import java.util.List;

/**
 * Created by 14007427 on 18/11/14.
 */


public class TestGare {

    private static final int NB_MAX_TRAIN = 2;
    private static final int NB_MAX_VOYAGEURS = 20;
    private static final int NB_GARES = 3;

    public static void main(String[] args){

        ServeurBilleterie monServeurBilleterie = new ServeurBilleterie();

        for (int i=0; i<NB_GARES; i++){
            EspaceVente espaceVente = new EspaceVente();
            EspaceQuai espaceQuai = new EspaceQuai(espaceVente, monServeurBilleterie);
            espaceVente.setEspaceQuai(espaceQuai);
            new Gare("Gare n°"+ i,espaceQuai, monServeurBilleterie);
        }

        for (int i=0; i<NB_MAX_VOYAGEURS; i++){
            new Voyageurs("Voyageur n°"+(i+1), monServeurBilleterie).start();
        }

        for (int i=0; i<NB_MAX_TRAIN; i++){
            new Train("Train n°"+(i+1),monServeurBilleterie).start();
        }
    }

}
