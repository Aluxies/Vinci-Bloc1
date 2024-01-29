package blacklist;

import domaine.Query;

import java.util.Set;

public interface BlackListService {

    boolean checkQuery( Query query );

}
