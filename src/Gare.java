import java.util.List;

/**
 * Created by Baptiste on 02/12/2014.
 */
public class Gare {

    private EspaceQuai espaceQuai;
    private EspaceVente espaceVente;
    private String nomGare;
    private ServeurBillet serveurBillet;
    private List<Gare> listeGare;


    public Gare(String nomGare, ServeurBillet serveurBillet){
        this.espaceQuai = new EspaceQuai();
        this.espaceVente = new EspaceVente();
        this.nomGare = nomGare;
        this.serveurBillet = serveurBillet;
        espaceVente.setGareAssocie(this);

        listeGare = TestGare.getListeGare();
    }

    synchronized public void entrerGare(Train train){
        espaceQuai.entrerVoie(train);
    }

    synchronized public void quitterGare (Train train){
        espaceQuai.quitterVoie(train);
    }

    public EspaceVente getEspaceVente(){
        return espaceVente;
    }

    public EspaceQuai getEspaceQuai(){
        return espaceQuai;
    }

    public ServeurBillet getServeurBillet(){
        return serveurBillet;
    }

    public String getNomGare(){
        return nomGare;
    }

}
