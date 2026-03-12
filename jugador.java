public class jugador {
    
    private String nom;
    private Personatge personatge;

    public jugador(String nom, Personatge personatge) {
        this.nom = nom;
        this.personatge = personatge;

    }

    public String getNom() {
        return nom;
    }

    public Personatge getPersonatge() {
        return personatge;
    }

    public void setPersonatge(Personatge personatge) {
        this.personatge = personatge;
    }

     @Override
     public String toString() {
         return "Jugador: " + nom + "\n" + personatge.toString();
     }
}
