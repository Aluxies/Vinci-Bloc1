package main;


import blacklist.BlackListService;
import blacklist.BlackListServiceImpl;
import domaine.QueryFactory;
import domaine.QueryFactoryImpl;
import server.ProxyServer;

public class Main {

	public static void main(String[] args) {
		BlackListService blackListService = new BlackListServiceImpl();
		QueryFactory queryFactory = new QueryFactoryImpl();
		ProxyServer proxyServer = new ProxyServer(queryFactory, blackListService );
		proxyServer.startServer();
	}

}