package domaine;

/**
 * The Query interface is an interface where
 * the url of the Query and
 * the queryMethod of the query
 * are stored
 */
public interface Query {

	/**
	 * return the url of the Query
	 * @author Alexis
	 * @return the url of the Query
	 * */

	String getUrl();

	/**
	 * set the url of the Query
	 * @author Alexis
	 * @param url	the url of the QueryMethod to be set
	 * */
	
	void setUrl(String url);

	/**
	 * return the QueryMethod of the Query
	 * @author Alexis
	 * @return the QueryMethod of the Query
	 * */

	QueryMethod getMethod();

	/**
	 * set the QueryMethod of the Query
	 * @author Alexis
	 * @param method	the method of the QueryMethod to be set
	 * */

	void setMethod(QueryMethod method);

	/**
	 * @deprecated
	 * set the method of the Query
	 * @author Alexis
	 * @param method	the method of the QueryMethod to be set
	 * @throws IllegalArgumentException
	 * */

	void setMethod( String method ) throws IllegalArgumentException;

	/**
	 * @author Alexis
	 * QueryMethods that can be selected
	 * */

	public enum QueryMethod {

		/**
		 * For GET methods
		 */
		GET,
		/**
		 * For POST methods
		 */
		POST;
	}

}