import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by 14007427 on 18/11/14.
 */
public class Train extends Thread {

    private final static int VITESSE_TRAIN = 100;
    private final static int TEMPS_ARRET_TRAIN = 5;
    private final static int CAPACITE_TRAIN = 25;

    private int placeDisponible;
    private String nomTrain;
    private EspaceQuai quai;
    private Boolean venteOuverte;
    private Collection<Voyageurs> listeVoyageurs;

    public Train(String nom, EspaceQuai quai) {
        this.nomTrain = nom;
        this.quai = quai;
        venteOuverte = false;
        placeDisponible = CAPACITE_TRAIN;
        listeVoyageurs = new ArrayList<Voyageurs>();
    }

    synchronized public void majNbPlaceDispo() {
        placeDisponible--;
    }

    synchronized void embarquer(Voyageurs voyageur){
        listeVoyageurs.remove(voyageur);
    }

    synchronized public int getPlaceDisponible() {
        return placeDisponible;
    }

    synchronized public String getNomTrain() {
        return nomTrain;
    }

    synchronized public Boolean venteIsOuverte(){
        return venteOuverte;
    }

    synchronized public Boolean listeVoyageurIsEmpty(){
        return listeVoyageurs.isEmpty();
    }

    synchronized public void setVenteOuverte(Boolean bool){
        venteOuverte = bool;
    }

    synchronized public void addVoyageur(Voyageurs voyageur){
        listeVoyageurs.add(voyageur);
    }




    @Override
    public void run() {
        quai.entrerVoie(this);
        System.out.println(""+getNomTrain()+" : arrive en gare.");
        try {
            Thread.sleep(TEMPS_ARRET_TRAIN*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        quai.quitterVoie(this);
        System.out.println(""+getNomTrain()+" a quitté la gare.");

    }
}