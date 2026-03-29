public class orc extends personatge {

    public orc(String nom, int edat, int forca, int destresa, int constitucio, int inteligencia, int saviesa, int carisma) {
        super(nom, edat, forca, destresa, constitucio, inteligencia, saviesa, carisma);
        aplicarModificadorsRacials();
        recalcularValorsMaxims();
    }

    @Override
    public void aplicarModificadorsRacials() {
        sumarAmbMaxim(3, "forca");
        sumarAmbMaxim(1, "constitucio");
    }

    @Override
    public boolean potEquiparArma(arma armaNova) {
        if (armaNova.isMagica()) {
            System.out.println("Els orcs no poden equipar armes màgiques.");
            return false;
        }
        return true;
    }

    @Override
    public double getMultiplicadorAtac() {
        return 1.10;
    }
}