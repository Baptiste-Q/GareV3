import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by 14007427 on 18/11/14.
 */
public class EspaceQuai {

    private final int NB_VOIES = 3;
    private int voiesDispo;
    private int trainsEnQuai;
    private ArrayList<Train> listeTrainQuai;

    public EspaceVente espaceVente;

    EspaceQuai(){
        voiesDispo = NB_VOIES;
        trainsEnQuai = 0;
        listeTrainQuai = new ArrayList<Train>();
    }

    synchronized public void entrerVoie(Train train){

        if(voiesDispo == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        voiesDispo--;

        synchronized(TestGare.getMonServeurBilletterie()) {
            TestGare.getMonServeurBilletterie().notifyAll();
        }

        listeTrainQuai.add(train);
        train.setVenteOuverte(true);
        System.out.println(train.getNomTrain() + " en gare");
    }

    synchronized public void quitterVoie(Train train){
        train.setVenteOuverte(false);
        if(train.listeVoyageurIsEmpty()){
            listeTrainQuai.remove(train);
            System.out.println( train.getNomTrain() +" quitte gare");
        }
        voiesDispo++;
        // Pour les trains en attente d'un quai libre.
        notifyAll();
    }

    synchronized public boolean accederAuTrain (Voyageurs voyageur, Train train) {
        Iterator<Train> iteratrain = listeTrainQuai.iterator();

        while (iteratrain.hasNext()) {
            Train trainListe = iteratrain.next();

            if (trainListe.equals(train)) {
                train.embarquer(voyageur);
                // Dire au train qu'un voyageur est monté.
                return true;
            }

        }
        return false;
    }




    synchronized public EspaceVente getEspaceVente() {
        return espaceVente;
    }

    synchronized public ArrayList<Train> getListeTrainQuai(){
        return listeTrainQuai;
    }




}
