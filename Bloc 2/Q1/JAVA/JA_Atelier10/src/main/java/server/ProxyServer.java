package server;

import java.util.Scanner;

import blacklist.BlackListService;
import domaine.Query;
import domaine.Query.QueryMethod;
import domaine.QueryFactory;

public class ProxyServer {
	
	QueryFactory queryFactory;
	BlackListService blackListService;

	public ProxyServer(QueryFactory queryFactory, BlackListService blackListService ) {
		this.queryFactory = queryFactory;
		this.blackListService = blackListService;
	}
	
	public void startServer() {
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				String url = scanner.nextLine();
				Query query = this.queryFactory.getQuery();
				query.setMethod(QueryMethod.GET);
				query.setUrl(url);
				if ( this.blackListService.checkQuery( query ) ) {
					QueryHandler queryHandler = new QueryHandler(query);
					queryHandler.start();
				} else {

					System.out.println( "This domain is blackListed" );

				}

			}
		}
	}

}
