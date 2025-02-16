package mvc.Karten;

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
        if(karten[i] != null) {
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
    public String getCardQuestion(int i) {
        if(karten[i]!=null) {
            return karten[i].getFrage();
        }
        else{
            return null;
        }
    }
    public KarteiKarte[] shuffle() {
        KarteiKarte[] ausgabe = new KarteiKarte[karten.length];
        int currentIndex = karten.length;
        while (currentIndex != 0) {
            int randomIndex = (int) Math.round( Math.floor(Math.random() * currentIndex));
            currentIndex--;
            KarteiKarte karteTemp = karten[currentIndex];
            ausgabe[currentIndex] = karten[randomIndex];
            ausgabe[randomIndex] = karteTemp;

        } return karten;
    }


}
