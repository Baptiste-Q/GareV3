import java.util.ArrayList;
import java.util.List;

/**
 * Created by Baptiste on 02/12/2014.
 */
public class Gare {

    private EspaceQuai espaceQuai;
    private EspaceVente espaceVente;
    private String nomGare;
    private ServeurBilleterie serveurBilleterie;
    private List<Gare> listeGare;


    public Gare(String nomGare, ServeurBilleterie serveurBilleterie){
        this.espaceQuai = new EspaceQuai();
        this.espaceVente = new EspaceVente();
        this.nomGare = nomGare;
        this.serveurBilleterie = serveurBilleterie;

        listeGare = TestGare.getListeGare();
    }

    synchronized public void entrerGare(Train train){
        espaceQuai.entrerVoie(train);
    }

    synchronized public void quitterGare (Train train){
        espaceQuai.quitterVoie(train);
    }

    public String getNomGare(){
        return nomGare;
    }

}
