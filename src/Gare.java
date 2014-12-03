import java.util.List;

/**
 * Created by Baptiste on 02/12/2014.
 */
public class Gare {

    private EspaceQuai espaceQuai;
    private EspaceVente espaceVente;
    private String nomGare;
    private ServeurBilletterie serveurBilletterie;
    private List<Gare> listeGare;


    public Gare(String nomGare, ServeurBilletterie serveurBilletterie){
        this.espaceQuai = new EspaceQuai();
        this.espaceVente = new EspaceVente();
        this.nomGare = nomGare;
        this.serveurBilletterie = serveurBilletterie;
        espaceVente.setGareAssocie(this);

        listeGare = TestGare.getListeGare();
    }

    public EspaceVente getEspaceVente(){
        return espaceVente;
    }

    public EspaceQuai getEspaceQuai(){
        return espaceQuai;
    }

    public ServeurBilletterie getServeurBilletterie(){
        return serveurBilletterie;
    }

    public String getNomGare(){
        return nomGare;
    }

}
