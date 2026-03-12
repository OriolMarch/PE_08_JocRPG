import java.util.ArrayList;

public class personatge {
    private String nom;
    private int edat;

    private int forca;
    private int destresa;
    private int constitucio;
    private int inteligencia;
    private int saviesa;
    private int carisma;
    private String especialitat;

    private int salut;
    private int salutMax;
    private int mana;
    private int manaMax;

    private boolean defensat;

    private ArrayList<arma> armes;
    private arma armaEquipada;

    public personatge(String nom,int edat, int forca,int destresa,int constitucio, int inteligencia, int saviesa, int carisma, String especialitat){
        this.nom = nom;
        this.edat = edat;
        this.forca = forca;
        this.destresa = destresa;
        this.constitucio = constitucio;
        this.inteligencia = inteligencia;
        this.saviesa = saviesa;
        this.carisma = carisma;
        this.especialitat = especialitat;
        this.salutMax = 10 * constitucio;
        this.salut = salutMax;
        this.manaMax = 10 * inteligencia;
        this.mana = manaMax;
        this.defensat = false;
        this.armes = new ArrayList<>();
        this.armaEquipada = null;

    }
}
