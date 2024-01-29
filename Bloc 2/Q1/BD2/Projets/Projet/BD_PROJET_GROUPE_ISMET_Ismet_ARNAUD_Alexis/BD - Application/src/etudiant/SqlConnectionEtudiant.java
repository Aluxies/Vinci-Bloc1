package etudiant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnectionEtudiant {

    public static Connection create() {

        try {

            Class.forName( "org.postgresql.Driver" );

        } catch ( ClassNotFoundException e ) {

            System.out.println( "Driver PostgreSQL manquant !" );
            System.exit( 1 );

        }

        // Modifier valeurs pour la démo

        String user = "postgres";
        String password = "ismet4589";
        String host = "localhost:5432";
        String dbName = "projetDB";
        String url = "jdbc:postgresql://" + host + "/" + dbName;

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(url, user, password);

            System.out.println( "Connecté à la base de données en tant qu'utilisateur \"étudiant\" !" );

        } catch (SQLException e) {

            System.out.println("Impossible de joindre le server !");
            System.exit(1);

        }

        return connection;
    }

}
