import java.util.ArrayList;

public abstract class personatge {

    private String nom;
    private int edat;

    private int forca;
    protected int destresa;
    protected int constitucio;
    public int inteligencia;
    private int saviesa;
    private int carisma;

    private int salut;
    private int salutMax;
    private int mana;
    private int manaMax;

    private boolean defensat;

    private ArrayList<arma> armes;
    private arma armaEquipada;

    public personatge(String nom, int edat, int forca, int destresa, int constitucio,
                      int inteligencia, int saviesa, int carisma) {
        this.nom = nom;
        this.edat = edat;
        this.forca = forca;
        this.destresa = destresa;
        this.constitucio = constitucio;
        this.inteligencia = inteligencia;
        this.saviesa = saviesa;
        this.carisma = carisma;

        this.defensat = false;
        this.armes = new ArrayList<arma>();
        this.armaEquipada = null;
    }

    protected void sumarAmbMaxim(int valor, String caracteristica) {
        if (caracteristica.equalsIgnoreCase("forca")) {
            forca = forca + valor;
            if (forca > 20) {
                forca = 20;
            }
        } else if (caracteristica.equalsIgnoreCase("destresa")) {
            destresa = destresa + valor;
            if (destresa > 20) {
                destresa = 20;
            }
        } else if (caracteristica.equalsIgnoreCase("constitucio")) {
            constitucio = constitucio + valor;
            if (constitucio > 20) {
                constitucio = 20;
            }
        } else if (caracteristica.equalsIgnoreCase("inteligencia")) {
            inteligencia = inteligencia + valor;
            if (inteligencia > 20) {
                inteligencia = 20;
            }
        } else if (caracteristica.equalsIgnoreCase("saviesa")) {
            saviesa = saviesa + valor;
            if (saviesa > 20) {
                saviesa = 20;
            }
        } else if (caracteristica.equalsIgnoreCase("carisma")) {
            carisma = carisma + valor;
            if (carisma > 20) {
                carisma = 20;
            }
        }
    }

    protected void recalcularValorsMaxims() {
        salutMax = constitucio * 50;
        manaMax = inteligencia * 30;
        salut = salutMax;
        mana = manaMax;
    }

    public abstract void aplicarModificadorsRacials();

    public String getRaca() {
        return this.getClass().getSimpleName();
    }

    public void afegirArma(arma armaNova) {
        armes.add(armaNova);
    }

    public void mostrarArmes() {
        if (armes.isEmpty()) {
            System.out.println("Aquest personatge no té armes.");
        } else {
            for (int i = 0; i < armes.size(); i++) {
                System.out.println((i + 1) + ". " + armes.get(i));
            }
        }
    }

    public boolean potEquiparArma(arma armaNova) {
        if (armaNova.isMagica() && inteligencia < 10) {
            System.out.println("No tens prou intel·ligència per equipar una arma màgica.");
            return false;
        }
        return true;
    }

    public void equiparArma(int posicio) {
        if (posicio < 1 || posicio > armes.size()) {
            System.out.println("Posició no vàlida.");
        } else {
            arma armaNova = armes.get(posicio - 1);

            if (potEquiparArma(armaNova)) {
                armaEquipada = armaNova;
                System.out.println(nom + " ha equipat " + armaEquipada.getTipus());
            }
        }
    }

    public boolean esquivar() {
        double probabilitat = (destresa - 5) * 3.33;
        double aleatori = Math.random() * 100;
        return aleatori < probabilitat;
    }

    public void defensar() {
        defensat = true;
        System.out.println(nom + " es defensa aquest torn.");
    }

    public double getMultiplicadorAtac() {
        return 1.0;
    }

    public int calcularDanyBase() {
        int dany;

        if (armaEquipada == null) {
            dany = forca;
        } else {
            if (armaEquipada.isMagica()) {
                dany = armaEquipada.getDany() * inteligencia / 100;
            } else {
                dany = forca * (100 + armaEquipada.getDany()) / 100;
            }
        }

        dany = (int) Math.round(dany * getMultiplicadorAtac());

        if (dany < 0) {
            dany = 0;
        }

        return dany;
    }

    public void atacar(personatge enemic) {
        if (enemic.esquivar()) {
            System.out.println(enemic.nom + " ha esquivat l'atac de " + nom + ".");
            return;
        }

        int dany = calcularDanyBase();
        enemic.rebreDany(dany);

        System.out.println(nom + " ha atacat a " + enemic.nom + " i li ha causat " + dany + " de dany.");
    }

    public double getMultiplicadorDefensa() {
        return 0.5;
    }

    public void rebreDany(int dany) {
        if (defensat) {
            dany = (int) Math.round(dany * getMultiplicadorDefensa());
            System.out.println(nom + " s'ha defensat i ara rep " + dany + " de dany.");
            defensat = false;
        }

        salut = salut - dany;

        if (salut < 0) {
            salut = 0;
        }
    }

    public int getRegeneracioVida() {
        return constitucio * 3;
    }

    public int getRegeneracioMana() {
        return inteligencia * 2;
    }

    public void regenerarVida() {
        salut = salut + getRegeneracioVida();

        if (salut > salutMax) {
            salut = salutMax;
        }
    }

    public void regenerarMana() {
        mana = mana + getRegeneracioMana();

        if (mana > manaMax) {
            mana = manaMax;
        }
    }

    public String getNom() {
        return nom;
    }

    public int getSalut() {
        return salut;
    }

    public int getMana() {
        return mana;
    }

    public boolean estaViu() {
        return salut > 0;
    }

    @Override
    public String toString() {
        String armaString;

        if (armaEquipada == null) {
            armaString = "Cap arma equipada";
        } else {
            armaString = armaEquipada.getTipus();
        }

        return "Nom: " + nom +
               "\nRaça: " + getRaca() +
               "\nEdat: " + edat +
               "\nForça: " + forca +
               "\nDestresa: " + destresa +
               "\nConstitució: " + constitucio +
               "\nIntel·ligència: " + inteligencia +
               "\nSaviesa: " + saviesa +
               "\nCarisma: " + carisma +
               "\nSalut: " + salut + "/" + salutMax +
               "\nMana: " + mana + "/" + manaMax +
               "\nArma equipada: " + armaString +
               "\nNombre d'armes: " + armes.size();
    }
}

// edit: He afegit el mètode estaViu() per comprovar si el personatge està viu o no, i també he corregit un error en el mètode rebreDany() on no es restava el dany a la salut.