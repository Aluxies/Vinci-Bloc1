package domaine;

public interface Query {
    String getUrl();

    QueryMethod getMethod();

    void setUrl( String url );
    void setMethod( QueryMethod method );

    public enum QueryMethod {

        GET, POST;

    }
}
