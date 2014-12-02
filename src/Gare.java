import java.util.ArrayList;
import java.util.List;

/**
 * Created by Baptiste on 01/12/2014.
 */
public class Gare {

    private EspaceQuai espaceQuai;
    private String nomGare;
    private ServeurBilleterie serveurBilleterie;


    public Gare(String nomGare, EspaceQuai espaceQuai, ServeurBilleterie serveurBilleterie){
        this.espaceQuai = espaceQuai;
        this.nomGare = nomGare;
        this.serveurBilleterie = serveurBilleterie;
        serveurBilleterie.addGare(this);

    }

    public EspaceQuai getEspaceQuai(){
        return espaceQuai;
    }

    public String getNomGare() {
        return nomGare;
    }

}
