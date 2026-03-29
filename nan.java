public class nan extends personatge {

    public nan(String nom, int edat, int forca, int destresa, int constitucio,
               int inteligencia, int saviesa, int carisma) {
        super(nom, edat, forca, destresa, constitucio, inteligencia, saviesa, carisma);
        aplicarModificadorsRacials();
        recalcularValorsMaxims();
    }

    @Override
    public void aplicarModificadorsRacials() {
        sumarAmbMaxim(4, "constitucio");
        destresa = destresa - 1;
    }

   @Override
    public double getMultiplicadorDefensa() {
    return 0.375;
}

    @Override
    public int getRegeneracioVida() {
        return constitucio * 4;
    }
}