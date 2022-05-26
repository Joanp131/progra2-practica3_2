package prog2.model;

import prog2.vista.MercatException;

import java.io.Serializable;

public class LlistaArticles extends Llista<Article> implements Serializable {

    public LlistaArticles() {
        super();
    }

    @Override
    public void afegir(Article article) throws MercatException {

        for (Article a : llista)
            if(a.equals(article))
                throw new MercatException("La llista d'articles ja conté un article igual");

        llista.add(article);
    }

    @Override
    public Article getAt(int position) throws MercatException {
        try {
            return super.getAt(position);
        } catch (MercatException e) {
            throw new MercatException("L'índex introduït per la llista d'articles no és correcte");
        }
    }
}
