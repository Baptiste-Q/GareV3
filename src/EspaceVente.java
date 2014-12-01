import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by 14007427 on 18/11/14.
 */
public class EspaceVente {


    private final int TEMPS_IMPRESSION_TICKET = 5;
    private int nbGuichet = 4;
    private int nbGuichetDispo;
    private EspaceQuai espaceQuai;


    public EspaceVente() {
        nbGuichetDispo = nbGuichet;
    }

    synchronized public EspaceQuai getEspaceQuai() {
        return espaceQuai;
    }

    synchronized public void setEspaceQuai(EspaceQuai espaceQuai) {
        this.espaceQuai = espaceQuai;
    }

    synchronized void getGuichet(Voyageurs voyageur) {

        while (nbGuichetDispo == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nbGuichetDispo--;
        voyageur.setAUnGuichet(true);
    }

    synchronized public Train acheterTicket(Voyageurs voyageur){

        getGuichet(voyageur);
        ArrayList<Train> listeTrain = espaceQuai.getListeTrainQuai();

        while(true){
            Collections.shuffle(listeTrain);
            Iterator<Train> iteratrain = listeTrain.iterator();

            while(iteratrain.hasNext()){
                Train train = iteratrain.next();

                if (train.getPlaceDisponible() > 0 && train.venteIsOuverte()) {

                    train.majNbPlaceDispo(); // place --
                    train.addVoyageur(voyageur);

                    voyageur.setTrain(train);
                    voyageur.setTicketValide(true);

                    System.out.println(train.getNomTrain() + train.getPlaceDisponible());

                    try {
                        Thread.sleep(TEMPS_IMPRESSION_TICKET);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    nbGuichetDispo++;
                    notifyAll();
                    return train;
                }
            }

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
