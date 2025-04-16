import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Create users
        User user1 = new User("Agung");
        User user2 = new User("Budi");
        
        // Create categories
        Category workCategory = new Category("Work");
        Category personalCategory = new Category("Personal");
        
        // Create tasks
        Task task1 = new Task("Finish my assignment", user1, workCategory, Priority.HIGH, Status.IN_PROGRESS);
        Task task2 = new Task("Buy groceries", user2, personalCategory, Priority.MEDIUM, Status.NOT_STARTED);
        
        // Create reminder task
        Reminder reminderTask = new Reminder("Meeting with client", user1, workCategory, Priority.HIGH, Status.NOT_STARTED, LocalDateTime.of(2025, 4, 10, 10, 0));
        
        // Display tasks
        System.out.println(task1);
        System.out.println(task2);
        System.out.println(reminderTask);
    }
}
