package prog2.model;

import prog2.vista.MercatException;

import java.util.Date;

/**
 * Subclasse de Comanda
 *
 * @author Dídac Díaz i Joan Pau Condal
 */
public class ComandaUrgent extends Comanda {

    public ComandaUrgent(Client client, Article article, int nombreArticles) throws MercatException {
        super(client, article, nombreArticles);

        if (!article.isUrgent())
            throw new MercatException("Aquest article no admet comandes urgents");
    }

    @Override
    protected Date calcularDataEnviament(Date dataCreacio) {

        // Data en la qual l'article ja estarà enviat tenint en compte quan va ser creada
        // la comanda i quan tarda l'article a preparar-se
        Date dataJaEnviat = new Date(
                this.getDataCreacio().getTime() +
                this.getArticle().getTempsEnviament() * 30000L
        );

        return dataJaEnviat;
    }

    @Override
    protected Date calcularDataRebut(Date dataCreacio) {
        Date dataJaRebut = new Date(
                this.getDataEnviat().getTime() + 86400000L
        );

        return dataJaRebut;
    }

    @Override
    public String tipusComanda() {
        return "Urgent";
    }

    // La comanda està enviada quan passa el temps d'enviament de l'article / 2
    @Override
    public boolean comandaEnviada() {
        Date dataInstant = new Date();

        return this.getDataEnviat().before(dataInstant);
    }

    @Override
    public boolean comandaRebuda() {
        Date dataInstant = new Date();

        return this.getDataRebut().before(dataInstant);
    }

    // El preu d'enviament d'una comanda urgent és de 4€,
    // aplicant els descomptes depenent del tipus de soci que faci la comanda
    @Override
    public float preuEnviament() {
        return (1 - client.descompteEnv()) * 4f;
    }
}
