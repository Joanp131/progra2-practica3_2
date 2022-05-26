package prog2.model;

import prog2.vista.MercatException;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Classe que conté tota la informació sobre els clients, els articles i les comandes
 *
 * @author Dídac Díaz i Joan Pau Condal
 */
public class Dades implements InDades, Serializable {

    private LlistaArticles articles;
    private LlistaClients clients;
    private LlistaComandes comandes;

    /**
     * Constructor per defecte. Inicialitza totes les llistes que conté dades
     */
    public Dades() {
        articles = new LlistaArticles();
        clients = new LlistaClients();
        comandes = new LlistaComandes();
    }

    //<editor-fold desc="Getters i Setters">
    public LlistaArticles getArticles() {
        return articles;
    }

    public void setArticles(LlistaArticles articles) {
        this.articles = articles;
    }

    public LlistaClients getClients() {
        return clients;
    }

    public void setClients(LlistaClients clients) {
        this.clients = clients;
    }

    public LlistaComandes getComandes() {
        return comandes;
    }

    public void setComandes(LlistaComandes comandes) {
        this.comandes = comandes;
    }
    //</editor-fold>

    /**
     * Afegeix un article a la llista, creant una nova instància
     * @param id id de l'article
     * @param nom nom de l'article
     * @param preu preu de l'article
     * @param temps temps d'enviament de l'article
     * @param admetUrgent si l'article admet comanda urgent o no
     * @throws MercatException Si l'article està repetit a la llista
     */
    @Override
    public void afegirArticle(String id, String nom, float preu, int temps, boolean admetUrgent) throws MercatException {
        articles.afegir(new Article(id, nom, preu, temps, admetUrgent));
    }

    /**
     * Retorna una llista amb tots els articles
     * @return ArrayList
     */
    @Override
    public ArrayList<Article> recuperaArticles() {
        return articles.getArrayList();
    }

    /**
     * Afegeix un nou client a la llista de clients
     * @param email Correu del nou client
     * @param nom nom del client
     * @param adreca Adreça postal del client
     * @param esPremium si el client és premium o no
     * @throws MercatException Si la llista ja conté un client igual
     */
    @Override
    public void afegirClient(String email, String nom, String adreca, boolean esPremium) throws MercatException {
        clients.afegir(esPremium ?
                new ClientPremium(email, nom, adreca)
                : new ClientEstandard(email, nom, adreca)
        );
    }

    /**
     * Retorna tots els clients de la llista de clients
     * @return ArrayList
     */
    @Override
    public ArrayList<Client> recuperaClients() {
        return clients.getArrayList();
    }

    /**
     * Afegeix una nova comanda a la lista de comandes
     * @param articlePos Posició de l'article a la llista d'articles
     * @param clientPos Posició del client a la llista de clients
     * @param quantitat Quantitat d'articles que conté la comanda
     * @param esUrgent Si la comanda és urgent o no
     * @throws MercatException En el cas que l'article no permeti comanda urgent i s'intenti crear-ne una
     */
    @Override
    public void afegirComanda(int articlePos, int clientPos, int quantitat, boolean esUrgent) throws MercatException {
        comandes.afegir(esUrgent ?
                        new ComandaUrgent(clients.getAt(clientPos), articles.getAt(articlePos), quantitat)
                        : new ComandaNormal(clients.getAt(clientPos), articles.getAt(articlePos), quantitat)
                );
    }

    /**
     * Esborra una comanda de la llista de comandes
     * @param position Posició a la llista de la comanda que volem enviar
     * @throws MercatException Si la comanda ja ha estat enviada i, per tant, no és pot esborrar
     */
    @Override
    public void esborrarComanda(int position) throws MercatException {

        comandes.esborrar(comandes.getAt(position));

    }

    /**
     * Retorna totes les comandes de la llista
     * @return ArrayList
     */
    @Override
    public ArrayList<Comanda> recuperaComandes() {
        return comandes.getArrayList();
    }

    /**
     * Recupera totes les comandes urgents de la llista de comandes
     * @return ArrayList
     */
    @Override
    public ArrayList<Comanda> recuperaComandesUrgents() {
        return comandes.recuperaComandesUrgents();
    }
}
