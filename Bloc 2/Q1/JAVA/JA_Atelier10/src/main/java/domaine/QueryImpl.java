package domaine;

class QueryImpl implements Query {
	
	private String url;
	private QueryMethod method;

	public QueryImpl() {
	}
	
	public QueryImpl(String url, QueryMethod method) {
		this.url = url;
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public QueryMethod getMethod() {
		return method;
	}

	public void setMethod(QueryMethod method) {
		this.method = method;
	}

	public void setMethod( String method ) {

		QueryMethod queryMethod = QueryMethod.valueOf( method );

		if ( queryMethod == null ) {
			throw new IllegalArgumentException( "Parameter 'method' invalid, must be equals to 'GET' or 'POST'." );
		}

		this.method = queryMethod;

	}

}
