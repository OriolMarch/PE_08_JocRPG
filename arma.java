public class arma {
    
    private String tipus;
    private int dany;
    private boolean magica;

    public arma(String tipus, int dany, boolean magica) {
        this.tipus = tipus;
        this.dany = dany;
        this.magica = magica;

        if (this.dany < 1) {
            this.dany = 1;
        }

        if (this.dany > 100) {
            this.dany = 100;
        }
    }

     public String getTipus() {
        return tipus;
    }

    public int getDany() {
        return dany;
    }

    public boolean isMagica() {
        return magica;
    }

     @Override
    public String toString() {
        String tipusArma;

        if (magica) {
            tipusArma = "magica";
        } else {
            tipusArma = "fisica";
        }

        return tipus + " | dany: " + dany + " | " + tipusArma;
    }

     public String getNom() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNom'");
     }
}




