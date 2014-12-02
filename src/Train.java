import java.util.*;

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

        List<Gare> maListeGare = serveurBilleterie.getListeGare();

        Collections.shuffle(maListeGare);

        gareDepart = maListeGare.get(0);
        gareArrivee = maListeGare.get(1);
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