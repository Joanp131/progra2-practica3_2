package prog2.model;

import java.io.Serializable;

/**
 * Classe que conté tota la informació sobre un client:
 * <ul>
 *     <li>Correu electrònic</li>
 *     <li>Nom del client</li>
 *     <li>Adreça de correu ordinari</li>
 * </ul>
 *
 * @author Dídac Díaz i Joan Pau Condal
 */
public abstract class Client implements Serializable{

    private String correuElectronic, nom, correuOrdinari;

    /**
     * Constructor de la classe
     * @param correuElectronic Correu electronic del client
     * @param nom Nom del client
     * @param correuOrdinari Adreça del client
     */
    public Client(String correuElectronic, String nom, String correuOrdinari) {
        this.correuElectronic = correuElectronic;
        this.nom = nom;
        this.correuOrdinari = correuOrdinari;
    }

    public String getCorreuElectronic() {
        return correuElectronic;
    }

    public void setCorreuElectronic(String correuElectronic) {
        this.correuElectronic = correuElectronic;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCorreuOrdinari() {
        return correuOrdinari;
    }

    public void setCorreuOrdinari(String correuOrdinari) {
        this.correuOrdinari = correuOrdinari;
    }

    /**
     * Retorna el tipus de client. Pot ser: Estàndard / Premium
     * @return
     */
    public abstract String tipusClient();

    /**
     * Calcula la quota mensual d'un client. En el cas dels clients estàndard,
     * aquesta és 0. Pels clients premium és 4€/mes
     * @return float
     */
    public abstract float calcMensual();

    /**
     * Calcula el descompte d'enviament del que disposa el client
     * @return float entre 0 i 1, ambdós inclosos
     */
    public abstract float descompteEnv();

    @Override
    public String toString() {
        return "Tipus: " + this.tipusClient() +
                ", Email: " + this.correuElectronic +
                ", Nom: " + this.nom +
                ", Adreça: " + this.correuOrdinari +
                ", Descompte Enviament: " + this.descompteEnv() + "%"+
                ", Mensualitat: " + this.calcMensual();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Client)
            return ((Client) obj).getCorreuElectronic().equals(correuElectronic);

        return false;
    }
}
