package prog2.adaptador;

import prog2.model.Article;
import prog2.model.Client;
import prog2.model.Comanda;
import prog2.model.Dades;
import prog2.vista.MercatException;

import java.io.*;
import java.util.ArrayList;

public class Adaptador {
    private Dades dades;

    public Adaptador() {
        dades = new Dades();
    }

    public Dades getDades() {
        return dades;
    }

    public void setDades(Dades dades) {
        this.dades = dades;
    }
    
    public void afegirArticle(String id, String nom, float preu, int temps, boolean admetUrgent) throws MercatException {
        dades.afegirArticle(id, nom, preu, temps, admetUrgent);
    }
    
    public ArrayList<String> recuperaArticles() {
        
        ArrayList<String> noms = new ArrayList<>();
        
        for(Article a : dades.recuperaArticles()) {
            noms.add(a.toString());
        }
        
        return noms;
    
    }
    
    public ArrayList<String> recuperarNomClients() {
        
        ArrayList<String> noms = new ArrayList<>();
        
        for(Client c : dades.recuperaClients()) {
            noms.add(c.getNom());
        }
        
        return noms;        
    }
    
    public void afegirClient(String email, String nom, String adreca, boolean isPremium) throws MercatException {
        dades.afegirClient(email, nom, adreca, isPremium);
    }
    
    public ArrayList<String> recuperaClients() {
        
        ArrayList<String> noms = new ArrayList<>();
        
        for(Client c : dades.recuperaClients()) {
            noms.add(c.toString());
        }
        
        return noms;        
    }

    public void afegirComanda(int articlePos, int clientPos, int quantitat, boolean esUrgent) throws MercatException {
        dades.afegirComanda(articlePos, clientPos, quantitat, esUrgent);
    }

    public void esborrarComanda(int comandaPos) throws MercatException {
        dades.esborrarComanda(comandaPos);
    }
    
    public ArrayList<String> recuperaComandes() {
        
        ArrayList<String> comandes = new ArrayList<>();
        
        for(Comanda c : dades.recuperaComandes())
            comandes.add(c.toString());
        
        return comandes;
    }
    
    public ArrayList<String> recuperaComandesUrgents() {
        
        ArrayList<String> comandes = new ArrayList<>();
        
        for(Comanda comandaUrgent : dades.recuperaComandesUrgents()){
            comandes.add(comandaUrgent.toString() + "\n");
        }
        
        return comandes;
    }
    
    public void guardaDades(String fitxerOrigen) throws MercatException {

        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {
            fout = new FileOutputStream(fitxerOrigen);
            oos = new ObjectOutputStream(fout);

            oos.writeObject(dades);

        } catch (FileNotFoundException e) {
            throw new MercatException("No s'ha pogut trobar el fitxer");
        } catch (IOException e) {
            throw new MercatException("Hi ha hagut un problema al guardar al fitxer la classe dades");
        }
        
    }
    
    public void carregaDades(File file) throws MercatException{

        FileInputStream fin = null;
        ObjectInputStream ois = null;

        try {
            fin = new FileInputStream(file);

            ois = new ObjectInputStream(fin);
            dades = (Dades)ois.readObject();
        } catch (FileNotFoundException e) {
            throw new MercatException("Fitxer no trobat. Assegureu-vos que el fitxer existeix i el nom està ben escrit");
        } catch (ClassNotFoundException | ClassCastException e) {
            throw new MercatException("No es pot castejar a un objecte tipus dades");
        } catch (IOException e) {
            throw new MercatException("Hi ha hagut un problema de lectura");
        } catch (NullPointerException e) {
            throw new MercatException(e.getMessage());
        }finally {
            try {
                fin.close();
            } catch (IOException e) {
                throw new MercatException("Hi ha hagut un problema al tancar el fitxer");
            } catch (NullPointerException e) {
                throw new MercatException("Null pointer exception al tancar el fitxer");
            }
        }

    }
}
