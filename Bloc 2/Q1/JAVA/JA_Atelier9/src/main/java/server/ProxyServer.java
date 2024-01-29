package server;

import domaine.Query;
import domaine.QueryFactory;
import domaine.QueryFactoryImpl;
import domaine.QueryImpl;

import java.util.Scanner;

public class ProxyServer {

    private QueryFactory queryFactory;
    public ProxyServer( QueryFactory queryFactory ) {

        this.queryFactory = queryFactory;

    }

    public void startServer() {
        try ( Scanner scanner = new Scanner(System.in) ) {

            while ( true ) {

                System.out.print("Entrez l'adresse de la page : ");
                String url = scanner.nextLine();

                Query query = queryFactory.getQuery();

                query.setMethod( Query.QueryMethod.GET );
                query.setUrl( url );

                QueryHandler queryHandler = new QueryHandler(query);
                queryHandler.start();

            }
        }

    }

}