package prog2.vista;

import prog2.adaptador.Adaptador;

import java.util.Scanner;

public class MercatUB {
    
    Adaptador adaptador;

    public MercatUB() {
        adaptador = new Adaptador();
    }

    public void gestioMercat() {

        Scanner sc = new Scanner(System.in);

        gestioMenu(sc);

    }
    
    private static enum OpcionsMenu {
        M_Opcio_1_GestioArticles,
        M_Opcio_2_GestioClients,
        M_Opcio_3_GestioComandes,
        M_Opcio_4_GuardarDades,
        M_Opcio_5_CarregaDades,
        M_Opcio_6_Sortir,
    }
    
    private static enum OpcionsGestioArticles {
        GA_Opcio_1_AfegirArticle,
        GA_Opcio_2_VisualitzarArticles,
        GA_Opcio_3_Sortir,
    }
    
    private static enum OpcionsGestioClients {
        GC_Opcio_1_AfegirClient,
        GC_Opcio_2_VisualitzarClients,
        GC_Opcio_3_Sortir,
    }
    
    private static enum OpcionsGestioComandes {
        GC_Opcio_1_AfegirComanda,
        GC_Opcio_2_EsborrarComanda,
        GC_Opcio_3_VisualitzarComandes,
        GC_Opcio_4_VisualitzarComandesUrgents,
        GC_Opcio_5_Sortir,
    }
    
    private static final String[] descMenu = {
        "Mostra un memú que permet gestionar la informació relacionada amb els articles",
        "Mostra un menú que permet gestionar els clients",
        "Visualitza un menú per gestionar les comandes",
        "Guarda les dades de l'aplicació",
        "Carrega les dades de l'aplicació",
        "Surt de l'aplicació"
    };
    
    private static final String[] descGestioArticles = {
        "Afegeix un nou article al sistema",
        "Mostra tots els articles afegits",
        "Torna al menú principal"
    };
    
    private static final String[] descGestioClients = {
        "Incorpora un nou client a l'aplicació",
        "Visualitza tots els clients afegits",
        "Torna al menú principal"
    };
    
    private static final String[] descGestioComandes = {
        "Crea una nova comanda",
        "Esborra una comanda (que no hagi estat enviada encara)",
        "Visualitza totes les comandes realitzades",
        "Visualitza únicament les comandes urgents",
        "Torna al menú principal",
    };

    /**
     * Menú principal
     * @param sc Scanner
     */
    private void gestioMenu(Scanner sc) {

        Menu<OpcionsMenu> menuMercatUB = new Menu<>("Menú principal", OpcionsMenu.values());
        menuMercatUB.setDescripcions(descMenu);
        
        OpcionsMenu opcioMenu;
        
        do{
            menuMercatUB.mostrarMenu();
            opcioMenu = menuMercatUB.getOpcio(sc);
            
            switch (opcioMenu){
                
                case M_Opcio_1_GestioArticles :
                    // Obre un nou menú per gestionar els articles
                    gestioArticles(sc);
                    break;
                    
                case M_Opcio_2_GestioClients :
                    // Obre un nou menú per gestionar els clients
                    gestioClients(sc);
                    break;
                    
                case M_Opcio_3_GestioComandes : 
                    // Obre un nou menú per gestionar les comandes
                    gestioComandes(sc);
                    break;
                    
                case M_Opcio_4_GuardarDades :
                    // Guarda les dades de l'aplicació
                    try {
                        guardarDades(sc);
                        System.out.println("Dades guardades correctament");
                    } catch (MercatException e) {
                        System.err.println(e);
                    }
                    break;
                
                case M_Opcio_5_CarregaDades :
                    // Carrega les dades de l'aplicació
                    try {
                        carregaDades(sc);
                        System.out.println("Dades recuperades correctament");
                    } catch (MercatException e) {
                        System.err.println(e);
                    }
                    break;
            }
        } while (opcioMenu != OpcionsMenu.M_Opcio_6_Sortir);
    }
    
    private void gestioArticles(Scanner sc) {

        Menu<OpcionsGestioArticles> menuGestioArticles = new Menu<>("Gestionar els articles", OpcionsGestioArticles.values());
        menuGestioArticles.setDescripcions(descGestioArticles);
        
        OpcionsGestioArticles opcioGestioArticles;
        
        do {
            menuGestioArticles.mostrarMenu();
            opcioGestioArticles = menuGestioArticles.getOpcio(sc);

            switch (opcioGestioArticles) {
                case GA_Opcio_1_AfegirArticle :
                    // Afegeix un nou article al sistema
                    try {
                        afegirArticle(sc);
                        System.out.println("Article afegit correctament");
                    } catch (MercatException e) {
                        System.err.println(e);
                    }

                    break;

                case GA_Opcio_2_VisualitzarArticles :
                    // Mostra tots els articles afegits
                    visualitzarArticles();
                    break;
            }
        } while (opcioGestioArticles != OpcionsGestioArticles.GA_Opcio_3_Sortir);
    }
    
    private void gestioClients(Scanner sc){
        Menu<OpcionsGestioClients> menuGestioClients = new Menu<>("Gestionar els Clients", OpcionsGestioClients.values());
        menuGestioClients.setDescripcions(descGestioClients);
        
        OpcionsGestioClients opcioGestioClients;
        
        do {
            menuGestioClients.mostrarMenu();
            opcioGestioClients = menuGestioClients.getOpcio(sc);
            
            switch (opcioGestioClients) {
                case GC_Opcio_1_AfegirClient :
                    // Afegeix un nou client al sistema
                    try{
                        afegirClient(sc);
                        System.out.println("Client afegit correctament");
                    } catch (MercatException e){
                        System.err.println(e);
                    }
                    break;
                
                case GC_Opcio_2_VisualitzarClients :
                    // Mostra tots els clients afegits
                    visualitzarClients();
                    break;
            }
        } while (opcioGestioClients != OpcionsGestioClients.GC_Opcio_3_Sortir);
    }
    
    private void gestioComandes(Scanner sc) {
        Menu<OpcionsGestioComandes> menuGestioComandes = new Menu<>("Gestionar les comandes", OpcionsGestioComandes.values());
        menuGestioComandes.setDescripcions(descGestioComandes);
        
        OpcionsGestioComandes opcioGestioComandes;
        
        do {
            menuGestioComandes.mostrarMenu();
           opcioGestioComandes = menuGestioComandes.getOpcio(sc);
           
           switch (opcioGestioComandes) {
               case GC_Opcio_1_AfegirComanda :
                   //Crea una nova comanda
                   try {
                       afegirComanda(sc);
                       System.out.println("Comanda creada correctament");
                   } catch (MercatException e) {
                       System.err.println(e);
                   }

                   break;
                
               case GC_Opcio_2_EsborrarComanda :
                   //Esborra una comanda determinada
                   try {
                       esborrarComanda(sc);
                       System.out.println("Comanda eliminada correctament");
                   } catch (MercatException e) {
                       System.err.println(e);
                   }
                   break;
                
               case GC_Opcio_3_VisualitzarComandes :
                   //Visualitza totes les comandes realitzades
                   visualitzarComandes();
                   break;
               
               case GC_Opcio_4_VisualitzarComandesUrgents :
                   //Visualitza únicament les comandes urgents
                   visualitzarComandesUrgents();
                   break;
           }
        } while(opcioGestioComandes != OpcionsGestioComandes.GC_Opcio_5_Sortir);
    }
    
    private void guardarDades(Scanner sc) throws MercatException {

        System.out.println("Introdueix el nom del fitxer on vols guardar les dades: ");
        String fitxer = sc.nextLine();

        adaptador.guardaDades(fitxer);
    }
    
    private void carregaDades(Scanner sc) throws MercatException {

        System.out.println("Escriu el nom del fitxer del qual vols recuperar les dades: ");
        String fitxer = sc.nextLine();

        adaptador.carregaDades(fitxer);
        
    }
    
    private void afegirArticle(Scanner sc) throws MercatException {

        System.out.println("Id de l'article:");
        String id = sc.nextLine();
        
        System.out.println("Nom de l'article:");
        String nom = sc.nextLine();
        
        System.out.println("Preu de l'article:");
        float preu = sc.nextFloat();
        
        System.out.println("Temps en minuts fins l'enviament");
        int temps = sc.nextInt();

        // Cal això perquè sinó el següent cop que cridem sc.nextLine() no lleigeix cap input
        sc.nextLine();
        
        System.out.println("Admet enviament urgent? (Y/N)");
        String urgentAux = sc.nextLine();

        boolean urgent = false;

        if (urgentAux.equalsIgnoreCase("y")) {
            urgent = true;
        } else if (!urgentAux.equalsIgnoreCase("n")){
            throw new MercatException("La resposta ha de ser Y o N");
        }

        adaptador.afegirArticle(id , nom, preu, temps, urgent);

    }

    private void visualitzarArticles() {
        System.out.println(adaptador.recuperaArticles());
    }
    
    private void afegirClient(Scanner sc) throws MercatException {

        System.out.println("Nom del nou client: ");
        String nom = sc.nextLine();

        System.out.println("Correu electrònic del client: ");
        String correu = sc.nextLine();

        System.out.println("Adreça del client: ");
        String adreca = sc.nextLine();

        System.out.println("És premium el client? (Y/N): ");
        String auxPremium = sc.nextLine();

        boolean premium;
        if (auxPremium.equalsIgnoreCase("y"))
            premium = true;
        else if (auxPremium.equalsIgnoreCase("n"))
            premium = false;
        else
            throw new MercatException("La resposta ha de ser Y o N");

        adaptador.afegirClient(correu, nom, adreca, premium);
    }
    
    private void visualitzarClients() {
        System.out.println(adaptador.recuperaClients());
    }
    
    private void afegirComanda(Scanner sc) throws MercatException {

        // int articlePos, int clientPos, int quantitat, boolean esUrgent
        System.out.println("Posició a la llista de l'article (1 - nº articles): ");
        int articlePos = sc.nextInt();

        System.out.println("Posició a la llista del client que fa la comanda (1 - nº clients): ");
        int clientPos = sc.nextInt();

        System.out.println("Quantitat d'articles a la comanda: ");
        int quantitat = sc.nextInt();
        sc.nextLine();

        System.out.println("És comanda urgent? (Y/N): ");
        String auxUrgent = sc.nextLine();

        boolean urgent;
        if (auxUrgent.equalsIgnoreCase("y"))
            urgent = true;
        else if (auxUrgent.equalsIgnoreCase("n"))
            urgent = false;
        else
            throw new MercatException("La resposta ha de ser Y o N");

        adaptador.afegirComanda(articlePos - 1, clientPos - 1, quantitat, urgent);
        
    }
    
    private void esborrarComanda(Scanner sc) throws MercatException {

        System.out.println("Escriu la posició en la llista de la comanda que vols esborrar (1 - nº comandes): ");
        int comandaPos = sc.nextInt();

        adaptador.esborrarComanda(comandaPos);
        
    }
    
    private void visualitzarComandes() {
        System.out.println(adaptador.recuperaComandes());
    }
    
    private void visualitzarComandesUrgents() {
        System.out.println(adaptador.recuperaComandesUrgents());
    }
}
