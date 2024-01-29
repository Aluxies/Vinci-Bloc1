package utils;

import entreprise.MenuEntreprise;
import etudiant.MenuEtudiant;
import professeur.MenuProfesseur;

import java.sql.SQLException;
import java.util.Scanner;

public class Application {

    public static void choose() throws SQLException, InterruptedException {

        Scanner scannerNumero = new Scanner( System.in );

        int select;

        do {

            System.out.println( "Voici les différentes applications disponibles :\n" );

            System.out.println(
                    "\tN°1 - Application professeur\n" +
                    "\tN°2 - Application entreprise\n" +
                    "\tN°3 - Application étudiant\n" +
                    "\tN°4 - Arrêter le programme\n" );

            System.out.print( "Entrez le numéro de l'application : " );

            select = scannerNumero.nextInt();

            switch ( select ) {
                case 1:
                    MenuProfesseur.renderMenu();
                    break;
                case 2:
                    MenuEntreprise.renderMenu();
                    break;
                case 3:
                    MenuEtudiant.renderMenu();
                    break;
                default:
                    System.out.println( "Programme arrêté." );
                    break;
            }

        } while ( select < 1 || select > 4 );

    }
}