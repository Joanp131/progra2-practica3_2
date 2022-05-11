package prog2.model;

import java.util.Date;

/**
 * Subclasse de Comanda
 *
 * @author Dídac Díaz i Joan Pau Condal
 */
public class ComandaNormal extends Comanda {

    public ComandaNormal(Client client, Article article, int nombreArticles) {
        super(client, article, nombreArticles);
    }

    @Override
    protected Date calcularDataEnviament(Date dataCreacio) {
        // Data en la qual l'article ja estarà enviat tenint en compte quan va ser creada
        // la comanda i quan tarda l'article a preparar-se
        Date dataJaEnviat = new Date(
                this.getDataCreacio().getTime() +
                article.getTempsEnviament() * 60000L
        );

        return dataJaEnviat;
    }

    // La comanda normal tarda 2 dies després d'haver estat preparada
    // 1 dia = 86 400 000 mil·lisegons
    @Override
    protected Date calcularDataRebut(Date dataCreacio) {
        Date dataJaRebut = new Date(
                this.getDataEnviat().getTime() + 86400000L*2
        );

        return dataJaRebut;
    }

    @Override
    public String tipusComanda() {
        return "Normal";
    }

    // La comanda esta enviada quan passa el temps d'enviament de l'article
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

    // En el cas de comandes normals, el preu d'enviament és d'un euro,
    // aplicant els descomptes depenent del tipus de soci que faci la comanda
    @Override
    public float preuEnviament() {
        return (1 - client.descompteEnv()) * 1f;
    }
}
