/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import prog2.vista.MercatException;

public class Llista<T> implements Serializable {

   protected ArrayList<T> llista;

   public Llista() {        
       llista = new ArrayList<>();
    }

    public int getSize() {
          return llista.size();
    }

    /**
     * Afegeix un nou element a la llista
     * @param t Nou element a afegir
     * @throws MercatException Si l'element que volem afegir està repetit a la llista
     */
    public void afegir(T t) throws MercatException {
          for(T element : llista)
              if(t.equals(element))
                  throw new MercatException("La llista ja conté un element igual");

          llista.add(t);
    }

    /**
     * Esborra un element donat de la llista
     * @param t Element de la llista a esborrar
     * @throws MercatException
     */
    public void esborrar(T t) throws MercatException {
          llista.remove(t);
    }

    /**
     * Retorna la classe que esta a la posició <code>position</code> a la llista
     * @param position índex de la classe que volem recuperar
     * @return Element de la llista
     * @throws MercatException Si l'índex no està ben definit
     */
    public T getAt(int position) throws MercatException {
        try {
            return llista.get(position);
        } catch (IndexOutOfBoundsException e) {
            throw new MercatException("L'index introduït no és correcte");
        }
    }

    /**
     * Esborra tots els elements de la llista
     */
    public void clear() {
          llista.clear();
    }

    /**
     * Comprova si la llista és buida o no
     * @return boolean
     */
    public boolean isEmpty() {
          return llista.isEmpty();
    }

    /**
     * Retorna la llista sencera
     * @return ArrayList
     */
    public ArrayList<T> getArrayList() {
        ArrayList<T> arrlist = new ArrayList<>(llista);
        return arrlist;
    }
}
