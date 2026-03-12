import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
public class jocprincipal {

    Scanner sc = new Scanner(System.in);
    ArrayList<personatge> personatges = new ArrayList<personatge>();
   public static void main(String[] args) {
    jocprincipal joc = new jocprincipal();
    joc.iniciarJoc();
   }

   public void iniciarJoc(){

    int opcions;

    do {
        System.out.println("--- Menú Principal ---");
        System.out.println("1. Crear personatge");
        System.out.println("2. Mostrar personatges");
        System.out.println("3. Jugar Combat");
        System.out.println("0. Sortir");
        opcions = sc.nextInt();
        sc.nextLine();

        switch (opcions) {
            case 1:
                crearPersonatge();
                break;
            case 2:
                mostrarPersonatges();
                break;
            case 3:
                //jugarcombat();
                break;
            case 0:
                System.out.println("Fins aviat!");
                break;
            default:
                System.out.println("Opció no vàlida, torna-ho a intentar.");
        }
    } while (opcions != 0);

   }


   public void crearPersonatge(){
    System.out.println("Introdueix nom del personatge:");
    String nom = sc.nextLine();
    System.out.println("Introdueix edat del personatge:");
    int edat = sc.nextInt();
    System.out.println("Introdueix la raça del personatge:");
    String raca = sc.nextLine();

    System.out.println("1) Crear manual");
    System.out.println("2) Crear aleatori");
    int tipus = llegirInt();

    personatge p;

    if (tipus == 1) {
        p = crearPersonatgeManual(nom, edat);
        
    //}else{
     //   p = crearPersonatgeAleatori(nom, edat);
    }

    afegirArmesAlPersonatge(p);
    personatges.add(p);

    System.out.println("Personatge creat amb èxit!");

    System.out.println(p);
   }

   public personatge crearPersonatgeManual(String nom,int edat){
    int[] stats = new int[6];

    for(int i = 0; i < 6;i++){
        stats[i] = 5;
    }

    int puntsRestants = 30;

    String[] nomsStats = {"Força", "Destresa", "Constitució", "Intel·ligència", "Sàvia", "Carisma"};

    while(puntsRestants > 0){
        System.out.println("Punts restants:" + puntsRestants);
        for(int i = 0; i < 6;i++){
            System.out.println((i+1) + ". " + nomsStats[i] + ": " + stats[i]);
        }
    }
    System.out.println("Introdueix el número de la estadística que vols augmentar:")
    int opcio = llegirInt();

    if (opcio >= 1 && opcio <=6) {
        System.out.println("opcio incorrecte, torna-ho a intentar");
    }else{
        int index = opcio - 1;
        int maxAugment = 20 - stats[index];
        
        if (maxAugment == 0) {
            System.out.println("Ja esta al maxim, tria una altra estadística");
        }else{
            System.out.println("Quants punts vols afegir? ");
            int punts = llegirInt();
        }
    }

    String raca;
    return new personatge(nom, edat, stats[0], stats[1], stats[2], stats[3], stats[4], stats[5], nom,raca);
   

    }

    public void mostrarPersonatges(){
        if (personatges.isEmpty()) {
            System.out.println("No hi ha personatges creats.");
        }else{
            for (personatge p : personatges) {
                System.out.println(p);
                System.out.println("-------------------");
            }
        }
    }

   public void afegirArmesAlPersonatge(personatge p) {
    System.out.println("Maxim d'armes 3, quantes armes vols afegir al personatge?");
    int numArmes = llegirInt();

    while (numArmes < 0 || numArmes > 3) {
        System.out.println("Nombre invalid. Introdueix un valor entre 0 i 3:");
        numArmes = llegirInt();
    }

    for (int i = 0; i < numArmes; i++) {
        System.out.println("\nArma " + (i + 1));

        System.out.print("Tipus d'arma: ");
        String tipus = sc.nextLine();

        System.out.print("Dany (1-100): ");
        int dany = llegirInt();

        while (dany < 1 || dany > 100) {
            System.out.print("Dany invalid. Torna a introduir-lo (1-100): ");
            dany = llegirInt();
        }

        System.out.println("L'arma es magica? (1 = true / 2 = false)");
        int magicaInt = llegirInt();

        boolean magica;

        if (magicaInt == 1) {
            magica = true;
        } else {
            magica = false;
        }

        arma novaArma = new arma(tipus, dany, magica);
        p.afegirArma(novaArma);
    }
}

   public int llegirInt(){
    int num;
    while(true){
        try{
            num = sc.nextInt();
            sc.nextLine();
            return num;
        }catch(InputMismatchException e){
            System.out.println("Entrada no vàlida, torna-ho a intentar:");
            sc.nextLine();
        }
    }
   }




}