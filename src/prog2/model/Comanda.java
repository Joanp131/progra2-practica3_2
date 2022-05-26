package prog2.model;

import prog2.vista.MercatException;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe que conté tota la informació d'una comanda, els articles de la comanda i el
 * client que la realitza
 *
 * @author Dídac Díaz i Joan Pau Condal
 */
public abstract class Comanda implements Serializable{

    protected Client client;
    protected Article article;
    private int nombreArticles;
    private Date dataCreacio, dataEnviat, dataRebut;

    /**
     * Constructor de la classe Comanda
     * @param client Client que crea la comanda
     * @param article Article que es demana mitjançant la comanda
     * @param nombreArticles Nombre d'articles que conté la comanda
     */
    public Comanda(Client client, Article article, int nombreArticles) throws MercatException {
        this.client = client;
        this.article = article;
        this.nombreArticles = nombreArticles;

        dataCreacio = new Date();
        dataEnviat = calcularDataEnviament(dataCreacio);
        dataRebut = calcularDataRebut(dataCreacio);
        
        if (nombreArticles <= 0) {
            throw new MercatException("El nombre d'articles ha de ser més gran que 0");
        }
        
    }

    //<editor-fold desc="Getters i Setters">
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getNombreArticles() {
        return nombreArticles;
    }

    public void setNombreArticles(int nombreArticles) {
        this.nombreArticles = nombreArticles;
    }

    public Date getDataCreacio() {
        return dataCreacio;
    }

    public void setDataCreacio(Date dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public Date getDataEnviat() {
        return dataEnviat;
    }

    public void setDataEnviat(Date dataEnviat) {
        this.dataEnviat = dataEnviat;
    }

    public Date getDataRebut() {
        return dataRebut;
    }

    public void setDataRebut(Date dataRebut) {
        this.dataRebut = dataRebut;
    }
    //</editor-fold>

    /**
     * Calcula el preu de la comanda sense tenir en compte els costos d'enviament
     * @return Preu de la comanda
     */
    public float calcPreu() {
        return nombreArticles * article.getPreu();
    }

    /**
     * Retorna el tipus de comanda: Normal / Urgent
     * @return String
     */
    public abstract String tipusComanda();

    /**
     * Ens indica si la comanda ha estat enviada
     * @return boolean
     */
    public abstract boolean comandaEnviada();

    /**
     * Ens indica si la comanda ha estat rebuda pel client
     * @return boolean
     */
    public abstract boolean comandaRebuda();

    /**
     * Calcula el preu d'enviament de la comanda, que depèn del tipus de comanda
     * @return float
     */
    public abstract float preuEnviament();

    /**
     * Calcula la data en la qual la comanda ja haurà estat enviada. Aquesta data correspon al temps d'enviament de l'article
     * @param dataCreacio Data en la qual va ser creada la comanda
     * @return Date
     */
    protected abstract Date calcularDataEnviament(Date dataCreacio);

    /**
     * Calcula la data en la qual el client rebrà la comanda. Aquesta data correspon a dos dies a partir
     * de l'enviament en el cas de Comandes Normals i un dia a partir de l'enviament per Comandes Urgents
     * @param dataCreacio Data en la qual va ser creada la comanda
     * @return Date
     */
    protected abstract Date calcularDataRebut(Date dataCreacio);

    @Override
    public String toString() {
        return "Tipus: " + this.tipusComanda() +
                ", Article: " + article.getNom() +
                ", Client: " + client.getNom() +
                ", Quantitat: " + this.nombreArticles +
                ", Data de creació: " + this.dataCreacio +
                ", Enviat: " +  this.comandaEnviada() +
                ", Rebut: " + this.comandaRebuda() +
                ", Preu Articles: " + this.calcPreu() +
                ", Preu Enviament: " + this.preuEnviament();
    }
}
