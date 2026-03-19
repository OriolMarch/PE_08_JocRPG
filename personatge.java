import java.util.ArrayList;


// Link GITHUB: https://github.com/OriolMarch/PE_08_JocRPG

public class personatge {
    private String nom;
    private int edat;

    private int forca;
    private int destresa;
    private int constitucio;
    private int inteligencia;
    private int saviesa;
    private int carisma;
    private String raca;
    private String especialitat;

    private int salut;
    private int salutMax;
    private int mana;
    private int manaMax;

    private boolean defensat;

    private ArrayList<arma> armes;
    private arma armaEquipada;

    public personatge(String nom,int edat, int forca,int destresa,int constitucio, int inteligencia, int saviesa, int carisma, String raca, String especialitat){
        this.nom = nom;
        this.edat = edat;
        this.forca = forca;
        this.destresa = destresa;
        this.constitucio = constitucio;
        this.inteligencia = inteligencia;
        this.saviesa = saviesa;
        this.carisma = carisma;
        this.raca = raca;

        aplicarRaca();

        this.especialitat = especialitat;
        this.salutMax = 10 * constitucio;
        this.salut = salutMax;
        this.manaMax = 10 * inteligencia;
        this.mana = manaMax;
        this.defensat = false;
        this.armes = new ArrayList<>();
        this.armaEquipada = null;
        
    }

    public void afegirArma(arma arma){
        armes.add(arma);
    }

    public void mostrarArmes(){
        if(armes.isEmpty()){
            System.out.println("No tens armes.");
        } else {
            System.out.println("Armes:");
            for(int i = 0; i < armes.size(); i++){
                System.out.println((i+1) + ". " + armes.get(i).getNom());
            }
        }
    }

    public void equiparArma(int posicio){
        if(posicio < 1 || posicio > armes.size()){
            System.out.println("Posició no vàlida.");
        } else {
            armaEquipada = armes.get(posicio - 1);
            System.out.println("Has equipat: " + armaEquipada.getNom());
        }
    }

    public boolean esquivar(){
        double probabilitat = (destresa - 5) * 3.33; 
        double aleatori = Math.random() * 100;
        return aleatori < probabilitat;
    
        
    }

    public void defensar(){
        defensat = true;
        System.out.println(nom + " has defensat aquest torn.");
    }

    public void atacar(personatge enemic){
        if(enemic.esquivar()){
            System.out.println(enemic.nom + "ha esquivat a l'atac de " + nom);
            return;
        }

        int dany;

        if (armaEquipada == null) {
            dany = forca;
        }else {
            if (armaEquipada.isMagica()) {
                dany = armaEquipada.getDany() *  inteligencia / 100;
            }else {
                dany = forca * (100 + armaEquipada.getDany()) / 100;
            }
        }
        enemic.rebreDany(dany);
        System.out.println(nom + " ha atacat a " + enemic.nom + " i li ha causat " + dany + " de dany.");
    }

    public void rebreDany(int dany){
        if (defensat) {
            dany = dany / 2;
            System.out.println(nom + " ha defensat i ha reduït el dany a " + dany);
            defensat = false;
        }
        salut = salut - dany;
        if (salut < 0) {
            salut = 0;
        }
    }

    public void aplicarRaca(){
    if (raca.equalsIgnoreCase("Orc")) {
        forca = forca + 3;
        constitucio = constitucio + 2;
        inteligencia = inteligencia - 2;
        carisma = carisma - 1;
    }
    else if (raca.equalsIgnoreCase("Nan")){
        constitucio = constitucio +3;
        forca = forca -5;
        destresa = destresa - 2;
        carisma = carisma - 1;
        saviesa = saviesa + 2;
    }
    }

    public void regenerarVida(){
        salut = salut + constitucio * 3;
        if (salut > constitucio*50) {
            salut = constitucio*50;
        }
    }

    public void regenerarMana(){
        mana = mana + inteligencia * 2;
        if (mana > inteligencia*30) {
            mana = inteligencia*30;
        }
    }

    public String getRaca() {
        return raca;
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
       if (salut > 0) {
         return true;
       } else {
         return false;
       }
    }

    public String toString(){
        String armaString;

        if(armaEquipada == null){
            armaString = "Cap arma equipada";
        } else {
            armaString = armaEquipada.getTipus();
        }

        return "Nom:" + nom + "\nEdat: " + edat + "\nForça: " + forca + "\nDestresa: " + destresa + "\nConstitució: " + constitucio + "\nIntel·ligència: " + inteligencia + "\nSaviesa: " + saviesa + "\nCarisma: " + carisma + "\nEspecialitat: " + especialitat + "\nSalut: " + salut + "/" + salutMax + "\nMana: " + mana + "/" + manaMax + "\nArma equipada: " + armaString +  "\n Raca: " + raca;  
    }
}
