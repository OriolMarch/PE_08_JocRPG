public class jugador<personatge> {
    
    private String nom;
    private personatge personatge;

    public jugador(String nom, personatge personatge) {
        this.nom = nom;
        this.personatge = personatge;

    }

    public String getNom() {
        return nom;
    }

    public personatge getPersonatge() {
        return personatge;
    }

    public void setPersonatge(personatge personatge) {
        this.personatge = personatge;
    }

     @Override
     public String toString() {
         return "Jugador: " + nom + "\n" + personatge.toString();
     }
}
