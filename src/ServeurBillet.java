import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Baptiste on 02/12/2014.
 */
public class ServeurBillet {

    private final int TEMPS_IMPRESSION_TICKET = 5;
    private List<Gare> listeGare;

    public ServeurBillet(){
        listeGare = TestGare.getListeGare();
    }

    synchronized public Train reserverTicket(Voyageurs voyageur) {

        while (true) {

            Collections.shuffle(listeGare);
            Iterator<Gare> iteraGare = listeGare.iterator();

            while (iteraGare.hasNext()) {
                Gare gare = iteraGare.next();

                ArrayList<Train> listeTrain = gare.getEspaceQuai().getListeTrainQuai();

                Collections.shuffle(listeTrain);
                Iterator<Train> iteratrain = listeTrain.iterator();

                while (iteratrain.hasNext()) {
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
                        gare.getEspaceVente().quitterGuichet();
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
}
