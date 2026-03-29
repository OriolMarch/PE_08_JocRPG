import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class jocprincipal {

    Scanner sc = new Scanner(System.in);
    ArrayList<personatge> personatges = new ArrayList<personatge>();

    public static void main(String[] args) {
        jocprincipal joc = new jocprincipal();
        joc.menu();
    }

    public void menu() {
        int opcio = -1;

        while (opcio != 0) {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Crear personatge");
            System.out.println("2. Mostrar personatges");
            System.out.println("3. Combat");
            System.out.println("0. Sortir");
            System.out.print("Opcio: ");

            opcio = llegirInt();

            if (opcio == 1) {
                crearPersonatge();
            } else if (opcio == 2) {
                mostrarPersonatges();
            } else if (opcio == 3) {
                combat();
            } else if (opcio == 0) {
                System.out.println("Sortint del joc...");
            } else {
                System.out.println("Opcio incorrecta.");
            }
        }
    }

    public void crearPersonatge() {
        String nom;
        int edat;
        int raca;
        int tipusCreacio;

        System.out.print("Nom del personatge: ");
        nom = sc.nextLine();

        System.out.print("Edat del personatge: ");
        edat = llegirInt();

        System.out.println("Tria la raca:");
        System.out.println("1. Huma");
        System.out.println("2. Elf");
        System.out.println("3. Orc");
        System.out.println("4. Nan");
        System.out.print("Opcio: ");
        raca = llegirInt();

        System.out.println("Com vols crear el personatge?");
        System.out.println("1. Manual");
        System.out.println("2. Automatic");
        System.out.print("Opcio: ");
        tipusCreacio = llegirInt();

        int[] stats = new int[6];

        for (int i = 0; i < 6; i++) {
            stats[i] = 5;
        }

        if (tipusCreacio == 1) {
            int punts = 30;

            while (punts > 0) {
                System.out.println("\nPunts restants: " + punts);
                System.out.println("1. Forca: " + stats[0]);
                System.out.println("2. Destresa: " + stats[1]);
                System.out.println("3. Constitucio: " + stats[2]);
                System.out.println("4. Inteligencia: " + stats[3]);
                System.out.println("5. Saviesa: " + stats[4]);
                System.out.println("6. Carisma: " + stats[5]);
                System.out.print("Quina caracteristica vols pujar? ");

                int opcio = llegirInt();

                if (opcio >= 1 && opcio <= 6) {
                    if (stats[opcio - 1] < 20) {
                        stats[opcio - 1]++;
                        punts--;
                    } else {
                        System.out.println("Aquesta caracteristica ja esta al maxim.");
                    }
                } else {
                    System.out.println("Opcio incorrecta.");
                }
            }

        } else {
            Random r = new Random();
            int punts = 30;

            while (punts > 0) {
                int pos = r.nextInt(6);

                if (stats[pos] < 20) {
                    stats[pos]++;
                    punts--;
                }
            }
        }

        personatge p;

        if (raca == 1) {
            p = new huma(nom, edat, stats[0], stats[1], stats[2], stats[3], stats[4], stats[5]);
        } else if (raca == 2) {
            p = new elf(nom, edat, stats[0], stats[1], stats[2], stats[3], stats[4], stats[5]);
        } else if (raca == 3) {
            p = new orc(nom, edat, stats[0], stats[1], stats[2], stats[3], stats[4], stats[5]);
        } else if (raca == 4) {
            p = new nan(nom, edat, stats[0], stats[1], stats[2], stats[3], stats[4], stats[5]);
        } else {
            System.out.println("Raca incorrecta. Es creara un huma.");
            p = new huma(nom, edat, stats[0], stats[1], stats[2], stats[3], stats[4], stats[5]);
        }

        System.out.print("Quantes armes vols afegir? (maxim 3): ");
        int numArmes = llegirInt();

        while (numArmes < 0 || numArmes > 3) {
            System.out.print("Numero incorrecte. Torna a posar-lo: ");
            numArmes = llegirInt();
        }

        for (int i = 0; i < numArmes; i++) {
            System.out.println("\nArma " + (i + 1));

            System.out.print("Tipus: ");
            String tipus = sc.nextLine();

            System.out.print("Dany: ");
            int dany = llegirInt();

            while (dany < 1 || dany > 100) {
                System.out.print("El dany ha d'estar entre 1 i 100. Torna a posar-lo: ");
                dany = llegirInt();
            }

            System.out.print("Es magica? (1 = si / 2 = no): ");
            int opcioMagica = llegirInt();

            boolean magica = false;
            if (opcioMagica == 1) {
                magica = true;
            }

            arma a = new arma(tipus, dany, magica);
            p.afegirArma(a);
        }

        personatges.add(p);

        System.out.println("\nPersonatge creat.");
        System.out.println(p);
    }

    public void mostrarPersonatges() {
        if (personatges.size() == 0) {
            System.out.println("No hi ha personatges creats.");
        } else {
            for (int i = 0; i < personatges.size(); i++) {
                System.out.println("\nPersonatge " + (i + 1));
                System.out.println(personatges.get(i));
                System.out.println("-------------------------");
            }
        }
    }

    public void combat() {
        if (personatges.size() < 2) {
            System.out.println("Has de tenir almenys 2 personatges.");
            return;
        }

        mostrarPersonatges();

        System.out.print("Tria el personatge 1: ");
        int p1 = llegirInt() - 1;

        System.out.print("Tria el personatge 2: ");
        int p2 = llegirInt() - 1;

        if (p1 < 0 || p1 >= personatges.size() || p2 < 0 || p2 >= personatges.size() || p1 == p2) {
            System.out.println("Seleccio incorrecta.");
            return;
        }

        jugador j1 = new jugador("Jugador 1", personatges.get(p1));
        jugador j2 = new jugador("Jugador 2", personatges.get(p2));

        jugador actual = j1;
        jugador rival = j2;

        while (actual.getPersonatge().estaViu() && rival.getPersonatge().estaViu()) {
            System.out.println("\n----- TORN DE " + actual.getNom() + " -----");
            System.out.println(actual.getPersonatge().getNom() + " - Vida: " + actual.getPersonatge().getSalut() + " Mana: " + actual.getPersonatge().getMana());
            System.out.println(rival.getPersonatge().getNom() + " - Vida: " + rival.getPersonatge().getSalut() + " Mana: " + rival.getPersonatge().getMana());

            System.out.print("Vols equipar arma? (1 = si / 2 = no): ");
            int canviar = llegirInt();

            if (canviar == 1) {
                actual.getPersonatge().mostrarArmes();
                System.out.print("Quina arma vols equipar? ");
                int armaTriada = llegirInt();
                actual.getPersonatge().equiparArma(armaTriada);
            }

            System.out.println("1. Atacar");
            System.out.println("2. Defensar");
            System.out.print("Accio: ");
            int accio = llegirInt();

            if (accio == 1) {
                actual.getPersonatge().atacar(rival.getPersonatge());
            } else if (accio == 2) {
                actual.getPersonatge().defensar();
            } else {
                System.out.println("Accio incorrecta. Perds el torn.");
            }

            actual.getPersonatge().regenerarVida();
            actual.getPersonatge().regenerarMana();

            if (!rival.getPersonatge().estaViu()) {
                System.out.println("\nHa guanyat " + actual.getNom() + " amb " + actual.getPersonatge().getNom());
                break;
            }

            jugador aux = actual;
            actual = rival;
            rival = aux;
        }
    }

    public int llegirInt() {
        int num = 0;
        boolean correcte = false;

        while (!correcte) {
            try {
                num = sc.nextInt();
                sc.nextLine();
                correcte = true;
            } catch (InputMismatchException e) {
                System.out.print("Introdueix un numero valid: ");
                sc.nextLine();
            }
        }

        return num;
    }
}