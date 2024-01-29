package domaine;

public class QueryImpl implements Query {

    private String url;
    private QueryMethod method;

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public QueryMethod getMethod() {
        return method;
    }

    public void setUrl( String url ) {

        this.url = url;

    };
    public void setMethod( QueryMethod method ) {

        this.method = method;

    };
}
