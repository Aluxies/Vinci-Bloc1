import java.util.Objects;

public class Task {

    private String title;
    private String description;
    private String status;

    public Task( String title, String description ) {

        if ( title == null ) throw new IllegalArgumentException( "Title must not be null." );
        if ( title.isBlank() ) throw new IllegalArgumentException( "Title must not be blank." );
        if ( description == null ) throw new IllegalArgumentException( "Description must not be null." );

        this.title = title;
        this.description = description;
        this.status = "non terminée";

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {

        return this.status;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }

    public boolean end() {

        if ( this.status == "non terminée" ) {

            this.status = "terminée";

            return true;

        } else return false;

    }

    public boolean editTitle(String newTitle) {

        if ( this.status == "terminée" ) return false;
        if ( newTitle == null ) return false;
        if ( newTitle.isBlank() ) return false;

        this.title = newTitle;
        return true;

    }

    public boolean editDescription(String newDescription) {

        if ( this.status == "terminée" ) return false;

        this.description = newDescription;
        return true;

    }
}
