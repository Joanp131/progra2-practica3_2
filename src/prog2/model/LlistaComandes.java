package prog2.model;

import prog2.vista.MercatException;

import java.io.Serializable;
import java.util.ArrayList;

public class LlistaComandes extends Llista<Comanda> implements Serializable {

    @Override
    public void afegir(Comanda comanda) throws MercatException {
        for (Comanda c : llista)
            if(c.equals(comanda))
                throw new MercatException("La llista de comandes ja conté una comanda igual");

        llista.add(comanda);
    }

    /**
     * Esborra una comanda donada de la llista
     * @param comanda Comanda a afegir
     * @throws MercatException Si la comanda no es pot esborrar de la llista perquè ja ha estat enviada
     */
    @Override
    public void esborrar(Comanda comanda) throws MercatException {
        if (comanda.comandaEnviada())
            throw new MercatException("La comanda no es pot cancel·lar, doncs ja ha estat enviada");

        super.esborrar(comanda);
    }

    @Override
    public Comanda getAt(int position) throws MercatException {
        try {
            return super.getAt(position);
        } catch (MercatException e) {
            throw new MercatException("L'índex introduït per la llista de comandes no és correcte");
        }
    }

    /**
     * Crea i retorna una subllista amb totes les comandes urgents de la llista de comandes
     * @return ArrayList
     */
    public ArrayList<Comanda> recuperaComandesUrgents() {
        ArrayList<Comanda> comandesUrgents = new ArrayList<>();

        for (Comanda comanda : llista)
            if (comanda instanceof ComandaUrgent)
                comandesUrgents.add(comanda);

        return comandesUrgents;
    }

}
