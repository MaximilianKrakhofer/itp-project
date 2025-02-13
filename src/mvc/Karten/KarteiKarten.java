package mvc.Karten;

public class KarteiKarten {
    KarteiKarte[] karten = new KarteiKarte[0];
    int aktuelleKarte = 0;

    public boolean addKarte(KarteiKarte karte) {
        if(karte != null) {
            KarteiKarte[] temp = karten;
            karten = new KarteiKarte[temp.length+1];
            for(int i = 0; i< temp.length; i++) {
                karten[i] = temp[i];
            }
            karten[temp.length+1] = karte;
            return true;
        }
        else{
            return false;
        }
    }
    public void removeKarte(int i) {
        if(i >= 0 & i< karten.length) {
            KarteiKarte[] temp = karten;
            karten = new KarteiKarte[temp.length-1];
            for(int j = 0; j < i; j++) {
                karten[j] = temp[j];
            }
            for( int j = i+1;j< karten.length;j++) {
                karten[j] = temp[j];
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
    public void shuffle() { // zu implementieren
        int currentIndex = karten.length;
        // While there remain elements to shuffle...
        while (currentIndex != 0) {
            // Pick a remaining element...
            int randomIndex = (int) Math.round( Math.floor(Math.random() * currentIndex));
            currentIndex--;
            // And swap it with the current element.
            KarteiKarte karteTemp = karten[currentIndex];
            karten[currentIndex] = karten[randomIndex];
            karten[randomIndex] = karteTemp;
        }
    }


}
