import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task("task 1", "description 1");
    }

    @Test
    void createTask() {

        String title = "task 1";
        String description = "description 1";
        String defaultStatus = "non terminée";

        assertAll(
                () -> assertEquals(task.getTitle(), title),
                () -> assertEquals(task.getDescription(), description),
                () -> assertEquals(task.getStatus(), defaultStatus)
        );

    }

    @Test
    void createNullTitleTask() {

        String title = null;
        String description = "description 1";

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new Task( title, description );
                })
        );

    }

    @Test
    void createNullDescriptionTask() {

        String title = "task 1";
        String description = null;

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new Task( title, description );
                })
        );

    }

    @Test
    void createEmptyTitleTask() {

        String title = "";
        String description = "description 1";

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new Task( title, description );
                })
        );

    }

    @Test
    void createEmptyDescriptionTask() {

        String title = "task 1";
        String description = "";

        assertAll(
                () -> assertDoesNotThrow( () -> {
                    new Task( title, description );
                })
        );

    }

    @Test
    void endTask() {

        assertAll(
            () -> assertTrue(task.end())
        );

    }

    @Test
    void endEndedTask() {

        task.end();

        assertAll(
                () -> assertFalse(task.end())
        );

    }

    @Test
    void editTitleTask() {

        String newTitle = "task 2";

        assertAll(
                () -> assertTrue(task.editTitle(newTitle)),
                () -> assertEquals(task.getTitle(), newTitle)
        );

    }

    @Test
    void editEndedTitleTask() {

        task.end();

        String oldTitle = task.getTitle();
        String newTitle = "task 2";

        assertAll(
                () -> assertFalse(task.editTitle(newTitle)),
                () -> assertEquals(task.getTitle(), oldTitle)
        );

    }

    @Test
    void editNullTitleTask() {

        String oldTitle = task.getTitle();
        String newTitle = null;

        assertAll(
                () -> assertFalse(task.editTitle(newTitle)),
                () -> assertEquals(task.getTitle(), oldTitle)
        );

    }

    @Test
    void editEmptyTitleTask() {

        String oldTitle = task.getTitle();
        String newTitle = "";

        assertAll(
                () -> assertFalse(task.editTitle(newTitle)),
                () -> assertEquals(task.getTitle(), oldTitle)
        );

    }

    @Test
    void editDescriptionTask() {

        String newDescription = "Description 2";

        assertAll(
                () -> assertTrue(task.editDescription(newDescription)),
                () -> assertEquals(task.getDescription(), newDescription)
        );

    }

    @Test
    void editEndedDescriptionTask() {

        task.end();

        String oldDescription = task.getDescription();
        String newDescription = "Description 2";

        assertAll(
                () -> assertFalse(task.editDescription(newDescription)),
                () -> assertEquals(task.getDescription(), oldDescription)
        );

    }

    @Test
    void editNullDescriptionTask() {

        task.end();

        String oldDescription = task.getDescription();
        String newDescription = "Description 2";

        assertAll(
                () -> assertFalse(task.editDescription(newDescription)),
                () -> assertEquals(task.getDescription(), oldDescription)
        );

    }

}
