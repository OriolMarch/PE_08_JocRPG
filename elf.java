public class elf extends personatge {

    public elf(String nom, int edat, int forca, int destresa, int constitucio,
               int inteligencia, int saviesa, int carisma) {
        super(nom, edat, forca, destresa, constitucio, inteligencia, saviesa, carisma);
        aplicarModificadorsRacials();
        recalcularValorsMaxims();
    }

    @Override
    public void aplicarModificadorsRacials() {
        sumarAmbMaxim(2, "destresa");
        sumarAmbMaxim(2, "inteligencia");
    }

    @Override
    public int getRegeneracioMana() {
        return inteligencia * 3;
    }
}