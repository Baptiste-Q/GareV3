import java.util.Collections;
import java.util.List;

/**
 * Created by 14007427 on 18/11/14.
 */
public class Voyageurs extends Thread {

    private boolean ticketValide;
    private String nom;
    private Train train;
    private Gare garePresentationVoyageur;
    private boolean aUnTicket;

    public Voyageurs (String nom) {
        this.nom = nom;
        ticketValide = false;
        garePresentationVoyageur = generationGarePresentation();
        aUnTicket = false;
    }

    public void setAUnGuichet(Boolean bool){
        aUnTicket = bool;
    }

    synchronized public String getNom() {
        return nom;
    }

    synchronized public void setTrain(Train train){
        this.train = train;
    }

    synchronized public void setTicketValide(Boolean bool){
        ticketValide = bool;
    }

    public Gare generationGarePresentation(){

        List<Gare> listeGareLocale = TestGare.getListeGare();
        Collections.shuffle(listeGareLocale);

        garePresentationVoyageur = listeGareLocale.get(0);

        return garePresentationVoyageur;
    }

    @Override
    public void run() {

        garePresentationVoyageur.getEspaceVente().getGuichet(this);
        Train trainReserve = garePresentationVoyageur.getServeurBilletterie().reserverTicket(this,garePresentationVoyageur);
        System.out.println(""+getNom()+" j'ai acheté un billet");

        if(trainReserve.getGareDepart().getEspaceQuai().accederAuTrain(this, trainReserve))
            System.out.println(""+getNom()+" je suis monté dans le train.");
        else
            System.out.println("Le train est déjà parti");
    }
}

