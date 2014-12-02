import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

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
    private Gare gareDepart;
    private Gare gareArrivee;
    private ServeurBilleterie serveurBilleterie;

    public Train(String nom, ServeurBilleterie serveurBilleterie) {
        this.nomTrain = nom;
        venteOuverte = false;
        placeDisponible = CAPACITE_TRAIN;
        listeVoyageurs = new ArrayList<Voyageurs>();
        this.serveurBilleterie = serveurBilleterie;
        choixAleatoireGares();
        this.quai = gareDepart.getEspaceQuai();
        System.out.println(this.getNomTrain()+" : Ma gare de départ est la " + gareDepart.getNomGare());
        System.out.println(this.getNomTrain()+" : Ma gare d'arrivée est la " + gareArrivee.getNomGare());
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

    synchronized public ServeurBilleterie getServeurBilleterie(){
        return serveurBilleterie;
    }

    synchronized public void choixAleatoireGares(){

        Collections.shuffle(serveurBilleterie.getListeGare());
        Iterator<Gare> iteraGare1 = serveurBilleterie.getListeGare().iterator();

        while (iteraGare1.hasNext()) {
            gareDepart = iteraGare1.next();
        }

        Collections.shuffle(serveurBilleterie.getListeGare());
        Iterator<Gare> iteraGare2 = serveurBilleterie.getListeGare().iterator();
        while (iteraGare2.hasNext()) {
            gareArrivee = iteraGare2.next();
        }

        if (gareDepart.equals(gareArrivee)){
            while (iteraGare2.hasNext()) {
                gareArrivee = iteraGare2.next();
            }
        }
    }

    public Gare getGareDepart(){
        return gareDepart;
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