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
       // garePresentationVoyageur =
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

    @Override
    public void run() {/*
        Train trainReserve = espaceQuai.getEspaceVente().acheterTicket(this);
        System.out.println(""+getNom()+" j'ai acheté un billet");

        if(espaceQuai.accederAuTrain(this, trainReserve))
            System.out.println(""+getNom()+" je suis monté dans le train.");
        else
            System.out.println("Le train est déjà parti");*/
    }
}

