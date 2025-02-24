package mvc.Karten;

import java.lang.reflect.Array;

public class KarteiKarten {
    private KarteiKarte[] karten = new KarteiKarte[0];

    public boolean addKarte(KarteiKarte karte) {
        if(karte != null) {
            KarteiKarte[] temp = karten;
            karten = new KarteiKarte[temp.length+1];
            for(int i = 0; i< temp.length; i++) {
                karten[i] = temp[i];
            }
            karten[temp.length] = karte;
            return true;
        }
        else{
            return false;
        }
    }
    public void removeKarte(int i) {
        if(i >= 0 && i< karten.length) {
            KarteiKarte[] temp = karten;
            karten = new KarteiKarte[temp.length-1];
            for(int j = 0; j < i; j++) {
                karten[j] = temp[j];
            }
            for( int j = i+1;j< temp.length;j++) {
                karten[j-1] = temp[j];
            }
        }
    }
    public KarteiKarte[] getCards(){
        return karten;
    }
    public KarteiKarte getCard(int i) {
        if(karten !=null &&( karten.length !=0 && karten[i] != null)) {
            return karten[i];
        }
        return null;
    }
    public String getCardAnswer(int i) {
        if(karten[i]!=null) {
            return karten[i].getAntwort();
        }
        else{
            return null;
        }
    }
    public int getCardType(int i) {
        if(karten[i]!=null) {
            return karten[i].getFragentyp();
        }
        return 0;
    }
    public String getCardQuestion(int i) {
        if(karten[i]!=null) {
            return karten[i].getFrage();
        }
        else{
            return null;
        }
    }
    public KarteiKarte[] shuffle() {
        KarteiKarte[] ausgabe = karten.clone();
        int currentIndex = karten.length-1;
        while (currentIndex != 0) {
            int randomIndex = (int) (Math.random() * (currentIndex+1));
            currentIndex--;
            KarteiKarte karteTemp = ausgabe[randomIndex];
            ausgabe[randomIndex] = ausgabe[currentIndex];
            ausgabe[currentIndex] = karteTemp;

        }
        for(int i = 0; i < karten.length; i++) {
            System.out.println(karten[i].getFrage());
        }
        return ausgabe;
    }


}
