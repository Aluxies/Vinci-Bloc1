import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoListTest {
    private TodoList todoList;
    @BeforeEach
    void setUp() {
        todoList = new TodoList();
    }

    @Test
    void addTask() {
        Task task = new Task("Task", "desc");
        assertAll(
            () -> assertTrue(todoList.addTask(task)),
            () -> assertTrue(todoList.containsTask(task))
        );
    }

    @Test
    void addNullTask() {
        Task task = null;
        assertAll(
            () -> assertFalse(todoList.addTask(task)),
            () -> assertFalse(todoList.containsTask(task))
        );
    }

    @Test
    void addExistingTask() {
        Task task = new Task("task 1", "desc 1");
        todoList.addTask(task);
        assertAll(
            () -> assertFalse(todoList.addTask(task))
        );
    }

    @Test
    void removeTask() {
        Task task = new Task("task 2", "desc 2");
        todoList.addTask(task);
        assertAll(
            () -> assertTrue(todoList.removeTask(task)),
            () -> assertFalse(todoList.containsTask(task))
        );
    }

    @Test
    void removeUnexistingTask() {
        Task task = new Task("task 3", "desc 3");
        assertAll(
            () -> assertFalse(todoList.removeTask(task)),
            () -> assertFalse(todoList.containsTask(task))
        );
    }

    @Test
    void returnToDoListTask() {

        Task task1 = new Task("task 1", "desc 1");

        todoList.addTask(task1);

        Task task1Clone = new Task( "task 1", "desc 1" );

        Task taskReturned = todoList.returnTask(task1Clone);

        assertAll(
            () -> assertEquals(taskReturned, task1),
            () -> assertEquals(taskReturned.getTitle(), task1.getTitle()),
            () -> assertEquals(taskReturned.getDescription(), task1.getDescription())
        );
    }

    @Test
    void returnUnexistingToDoListTask() {

        Task task1 = new Task("task 1", "desc 1");

        todoList.addTask(task1);

        Task taskUnexisting = new Task( "task 2", "desc 1" );

        Task taskReturned = todoList.returnTask(taskUnexisting);

        assertAll(
                () -> assertNull(taskReturned)
        );
    }

    @Test
    void editToDoListTask() {

        Task taskToModify = new Task("task 1", "desc 1");

        todoList.addTask(taskToModify);

        Task taskReturnedModified = todoList.returnTask(taskToModify);

        Task taskToUse = new Task( "task 2", "desc 2");

        todoList.editTask(taskToModify, taskToUse);

        assertAll(
                () -> assertNotNull( taskReturnedModified ),
                () -> assertEquals(taskReturnedModified, taskToUse)
        );
    }

    @Test
    void editToDoListEndedTask() {

        Task taskToModify = new Task("task 1", "desc 1");

        todoList.addTask(taskToModify);

        Task taskReturnedModified = todoList.returnTask(taskToModify);

        taskReturnedModified.end();

        Task taskToUse = new Task( "task 2", "desc 2");

        todoList.editTask(taskToModify, taskToUse);

        assertAll(
                () -> assertNotNull( taskReturnedModified ),
                () -> assertNotEquals(taskReturnedModified, taskToUse)
        );
    }

    @Test
    void editToDoListUnexistingTask() {

        Task taskToModify = new Task("task 1", "desc 1");

        Task taskReturnedModified = todoList.returnTask(taskToModify);

        Task taskToUse = new Task( "task 2", "desc 2");

        assertAll(
                () -> assertNull(taskReturnedModified),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    todoList.editTask(taskToModify, taskToUse);
                }),
                () -> assertNotEquals(taskToModify.getTitle(), taskToUse.getTitle()),
                () -> assertNotEquals(taskToModify.getDescription(), taskToUse.getDescription())
        );
    }

}
