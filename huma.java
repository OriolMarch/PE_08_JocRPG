public class huma extends personatge {

    public huma(String nom, int edat, int forca, int destresa, int constitucio,
                int inteligencia, int saviesa, int carisma) {
        super(nom, edat, forca, destresa, constitucio, inteligencia, saviesa, carisma);
        aplicarModificadorsRacials();
        recalcularValorsMaxims();
    }

    @Override
    public void aplicarModificadorsRacials() {
        sumarAmbMaxim(1, "forca");
        sumarAmbMaxim(1, "destresa");
        sumarAmbMaxim(1, "constitucio");
        sumarAmbMaxim(1, "inteligencia");
        sumarAmbMaxim(1, "saviesa");
        sumarAmbMaxim(1, "carisma");
    }
}