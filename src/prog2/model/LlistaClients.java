package prog2.model;

import prog2.vista.MercatException;

import java.io.Serializable;

public class LlistaClients extends Llista<Client> implements Serializable {

    @Override
    public void afegir(Client client) throws MercatException {
        for (Client c : llista)
            if(c.equals(client))
                throw new MercatException("La llista de clients ja conté un client igual");

        llista.add(client);
    }

    @Override
    public Client getAt(int position) throws MercatException {
        try {
            return super.getAt(position);
        } catch (MercatException e) {
            throw new MercatException("L'índex introduït per la llista de clients no és correcte");
        }
    }
}
