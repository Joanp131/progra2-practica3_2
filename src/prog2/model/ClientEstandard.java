package prog2.model;

/**
 * Subclasse de Client
 *
 * @author Dídac Díaz i Joan Pau Condal
 */
public class ClientEstandard extends Client {

    public ClientEstandard(String correuElectronic, String nom, String correuOrdinari) {
        super(correuElectronic, nom, correuOrdinari);
    }

    @Override
    public String tipusClient() {
        return "Estàndard";
    }

    @Override
    public float calcMensual() {
        return 0f;
    }

    @Override
    public float descompteEnv() {
        return 0.f;
    }
}
