package blacklist;

import domaine.Query;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class BlackListServiceImpl implements BlackListService {

    private static Set<String> blacklistedDomains;

    static {

        Properties props = new Properties();

        try ( FileInputStream input = new FileInputStream("blacklist.properties") ) {

            props.load(input);

        } catch (IOException e ) {

            e.printStackTrace();
            System.exit(2);

        }

        String propertyValue = props.getProperty( "blackListedDomains" );

        String[] valuesArray = propertyValue.split( ";" );

        HashSet<String> valuesSet = new HashSet<String>();

        valuesSet.addAll(Arrays.asList(valuesArray));

        blacklistedDomains = valuesSet;

    }

    public boolean checkQuery( Query query ) {

        for ( String blackListedDomain : blacklistedDomains ) {

            if ( query.getUrl().contains( blackListedDomain ) ) return false;

        }

        return true;

    }

}
