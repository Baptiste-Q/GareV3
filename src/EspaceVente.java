import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by 14007427 on 18/11/14.
 */
public class EspaceVente {

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

    synchronized void quitteGuichet() {

        nbGuichetDispo++;
        notifyAll();
    }


}
