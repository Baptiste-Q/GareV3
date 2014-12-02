import java.util.Collections;
import java.util.Iterator;

/**
 * Created by 14007427 on 18/11/14.
 */
public class Voyageurs extends Thread {

    private boolean ticketValide;
    private String nom;
    private Train train;
    private boolean aUnTicket;
    private Gare garePresentationVoyageur;
    private ServeurBilleterie serveurBilleterie;

    public Voyageurs (String nom, ServeurBilleterie serveurBilleterie) {
        this.nom = nom;
        ticketValide = false;
        aUnTicket = false;
        this.serveurBilleterie = serveurBilleterie;
        choixAleatoireGarePresentation();

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

    synchronized public void choixAleatoireGarePresentation(){

        Collections.shuffle(serveurBilleterie.getListeGare());
        Iterator<Gare> iteraGare = serveurBilleterie.getListeGare().iterator();

        while (iteraGare.hasNext()) {
            garePresentationVoyageur = iteraGare.next();
        }
    }



        @Override
    public void run() {

        garePresentationVoyageur.getEspaceQuai().getEspaceVente().getGuichet(this);

        Train trainReserve = serveurBilleterie.acheterTicket(this);
        System.out.println(""+getNom()+" j'ai acheté un billet");

        if(train.getGareDepart().getEspaceQuai().accederAuTrain(this, trainReserve))
            System.out.println(""+getNom()+" je suis monté dans le train.");
        else
            System.out.println("Le train est déjà parti");
        }
    }

