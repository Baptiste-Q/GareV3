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
    private Gare gareAssocie;


    public EspaceVente() {
        nbGuichetDispo = nbGuichet;
    }

    public void setGareAssocie(Gare gare){
        gareAssocie = gare;
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


    synchronized public Train acheterTicket(Voyageurs voyageur) {

        getGuichet(voyageur);
        Train trainReserve = gareAssocie.getServeurBilleterie().reserverTicket();
        return trainReserve;
    }
}
