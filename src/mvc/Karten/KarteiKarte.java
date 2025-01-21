package mvc.Karten;

public class KarteiKarte {
    private String frage;
    private String antwort;



    public String getFrage() {
        return frage;
    }
    public String getAntwort() {
        return antwort;
    }
    public int[] containsCharacter(char character) {
        int[] charsat = new int[0];
        int[] temp;
        for(int i = 0; i < antwort.length();i++) {
            if(character == antwort.charAt(i)) {
                temp = new int[charsat.length+1];
                for(int j = 0; j < charsat.length; j++) {
                    temp[j] = charsat[j];
                }
                temp[charsat.length] = i;
                charsat = temp;
            }
        }
        return charsat;
    }

    public boolean isAntwort(String antw) {
        return antw.equalsIgnoreCase(this.antwort);
    }
}
