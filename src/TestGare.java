/**
 * Created by 14007427 on 18/11/14.
 */


public class TestGare {

    private static final int NB_MAX_TRAIN = 6;
    private static final int NB_MAX_VOYAGEURS = 250;

    public static void main(String[] args){

        //Création gare / quai / vente
        EspaceVente espaceVente = new EspaceVente();
        EspaceQuai espaceQuai = new EspaceQuai(espaceVente);
        espaceVente.setEspaceQuai(espaceQuai);

        for (int i=0; i<NB_MAX_VOYAGEURS; i++){
            new Voyageurs("voyageur#"+i,espaceQuai).start();
        }

        for (int i=0; i<NB_MAX_TRAIN; i++){
            new Train("train#"+i,espaceQuai).start();
        }
    }

}
