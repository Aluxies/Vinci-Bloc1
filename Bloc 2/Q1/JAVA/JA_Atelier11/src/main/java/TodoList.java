import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private List<Task> tasks = new ArrayList<Task>();

    public boolean addTask(Task task) {
        if (task == null) return false;
        if (task.getTitle().isBlank()) return false;
        if (containsTask(task)) return false;
        return tasks.add(task);
    }

    public boolean containsTask(Task task) {
        return tasks.contains(task);
    }

    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    public Task returnTask(Task taskClone) {

        if ( !tasks.contains(taskClone) ) return null;

        Task taskFound;

        for ( Task task : tasks ) {

            if ( task.equals(taskClone) ) {

                taskFound = task;
                return taskFound;

            }

        }

        return null;

    }

    public void editTask(Task taskToModify, Task taskToUse) {

        Task reelTaskToModify = this.returnTask(taskToModify);

        if ( reelTaskToModify == null ) throw new IllegalArgumentException( "TaskToModify isn't present in the ToDoList." );

        String newTitle = taskToUse.getTitle();
        String newDescription = taskToUse.getDescription();

        reelTaskToModify.editTitle( newTitle );
        reelTaskToModify.editDescription( newDescription );


    }
}
