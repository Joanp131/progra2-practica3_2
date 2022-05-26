package prog2.model;

/**
 * Subclasse de Client
 *
 * @author Dídac Díaz i Joan Pau Condal
 */
public class ClientPremium extends Client {

    public ClientPremium(String correuElectronic, String nom, String correuOrdinari) {
        super(correuElectronic, nom, correuOrdinari);
    }

    @Override
    public String tipusClient() {
        return "Premium";
    }

    @Override
    public float calcMensual() {
        return 4f;
    }

    @Override
    public float descompteEnv() {
        return .2f;
    }
}
