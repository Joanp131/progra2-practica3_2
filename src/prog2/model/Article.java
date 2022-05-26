package prog2.model;

import java.io.Serializable;

/**
 * Classe que conté tota la informació sobre un article
 *
 * @author Dídac Díaz i Joan Pau Condal
 */
public class Article implements Serializable{

    private String id, nom;
    private int tempsEnviament;
    private float preu;
    boolean urgent;

    /**
     * Constructor de la classe
     * @param id Identificador de l'article
     * @param nom Nom de l'article
     * @param preu Preu de l'article
     * @param tempsEnviament Temps que tarda l'article en ser enviat
     * @param urgent Si l'article admet comanda urgent o no
     */
    public Article(String id, String nom, float preu, int tempsEnviament, boolean urgent) {
        this.id = id;
        this.nom = nom;
        this.preu = preu;
        this.tempsEnviament = tempsEnviament;
        this.urgent = urgent;
    }

    //<editor-fold desc="Getters i Setters">
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public int getTempsEnviament() {
        return tempsEnviament;
    }

    public void setTempsEnviament(int tempsEnviament) {
        this.tempsEnviament = tempsEnviament;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "Id = " + this.id +
                ", Nom = " + this.nom +
                ", Preu = " + this.preu +
                ", Temps fins a enviament = " + this.tempsEnviament +
                ", Enviament urgent = " + this.urgent;
    }

    /**
     * Dos articles es consideren iguals si tenen el mateix identificador
     * @param obj Objecte a comparar
     * @return True si els dos articles tenen el mateix identificador, False altrament
     */
    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof Article))
            return ((Article) obj).getId().equals(this.id);

        return false;
    }
}
