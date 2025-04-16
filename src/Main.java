import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private User currentUser;
    private final List<User> userList = new ArrayList<>();
    private final List<Task> taskList = new ArrayList<>();
    private final List<Category> categoryList = new ArrayList<>();
    private final List<Reminder> reminderList = new ArrayList<>();
    private int nextTaskId = 1;
    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        Main app = new Main();
        app.startApplication();
    }

    private void startApplication() {
        System.out.println("=== Task Manager Application ===");
        
        // Create admin user if none exists
        if (userList.isEmpty()) {
            userList.add(new User("admin1", "admin", "admin123", "admin@taskmanager.com", "admin"));
        }

        boolean running = true;
        while (running) {
            if (currentUser == null) {
                showLoginMenu();
                String choice = scanner.nextLine();
                handleLoginMenuChoice(choice);
            } else {
                showMainMenu();
                String choice = scanner.nextLine();
                running = handleMainMenuChoice(choice);
            }
        }
        System.out.println("Thank you for using Task Manager!");
    }

    private void showLoginMenu() {
        System.out.println("\n=== Login Menu ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private void handleLoginMenuChoice(String choice) {
        switch (choice) {
            case "1":
                handleLogin();
                break;
            case "2":
                handleRegistration();
                break;
            case "3":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void showMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Create Task");
        System.out.println("2. List All Tasks");
        System.out.println("3. Create Category");
        System.out.println("4. List All Categories");
        System.out.println("5. Set Reminder for Task");
        System.out.println("6. Check Active Reminders");
        System.out.println("7. Logout");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private boolean handleMainMenuChoice(String choice) {
        switch (choice) {
            case "1":
                createTaskFromInput();
                break;
            case "2":
                displayAllTasks();
                break;
            case "3":
                createCategoryFromInput();
                break;
            case "4":
                displayAllCategories();
                break;
            case "5":
                setReminderForTask();
                break;
            case "6":
                checkActiveReminders();
                break;
            case "7":
                logout();
                return true;
            case "8":
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    private void handleLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : userList) {
            if (user.login(username, password)) {
                currentUser = user;
                System.out.println("Login successful! Welcome, " + username + "!");
                return;
            }
        }
        System.out.println("Login failed. Invalid username or password.");
    }

    private void handleRegistration() {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        if (userList.stream().anyMatch(u -> u.getUsername().equals(username))) {
            System.out.println("Username already exists. Please choose another.");
            return;
        }

        userList.add(new User(userId, username, password, email, "member"));
        System.out.println("Registration successful! You can now login.");
    }

    private void createTaskFromInput() {
        System.out.println("\n=== Create New Task ===");
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        
        LocalDateTime dueDate = null;
        while (dueDate == null) {
            System.out.print("Enter due date (yyyy-MM-dd HH:mm): ");
            String dueDateStr = scanner.nextLine();
            try {
                dueDate = LocalDateTime.parse(dueDateStr, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm");
            }
        }

        Task task = createTask(title, description, dueDate);
        System.out.println("Task created successfully with ID: " + task.getTaskId());
        
        if (!categoryList.isEmpty()) {
            System.out.println("Available categories:");
            for (int i = 0; i < categoryList.size(); i++) {
                System.out.println((i + 1) + ". " + categoryList.get(i).getName());
            }
            System.out.print("Select category to assign (0 to skip): ");
            try {
                int catChoice = Integer.parseInt(scanner.nextLine());
                if (catChoice > 0 && catChoice <= categoryList.size()) {
                    assignCategoryToTask(task, categoryList.get(catChoice - 1));
                    System.out.println("Task assigned to category: " + categoryList.get(catChoice - 1).getName());
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Task not assigned to any category.");
            }
        }
    }

    private void displayAllTasks() {
        System.out.println("\n=== All Tasks ===");
        if (taskList.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        for (Task task : taskList) {
            System.out.println("ID: " + task.getTaskId());
            System.out.println("Title: " + task.getTitle());
            System.out.println("Description: " + task.getDescription());
            System.out.println("Due: " + task.getDueDate().format(dateTimeFormatter));
            System.out.println("Status: " + task.getStatus().getDescription());
            System.out.println("Priority: " + task.getPriority().getDescription());
            
            List<Category> taskCategories = categoryList.stream()
                .filter(c -> c.getTasks().contains(task))
                .toList();
            
            if (!taskCategories.isEmpty()) {
                System.out.print("Categories: ");
                taskCategories.forEach(c -> System.out.print(c.getName() + " "));
                System.out.println();
            }
            
            reminderList.stream()
                .filter(r -> r.getTaskId() == task.getTaskId())
                .forEach(r -> System.out.println("Reminder: " + 
                    r.getReminderDateTime().format(dateTimeFormatter) + 
                    " (Active: " + r.isReminderActive() + ")"));
            
            System.out.println("-------------------");
        }
    }

    private void createCategoryFromInput() {
        System.out.print("\nEnter category name: ");
        String name = scanner.nextLine();
        Category category = createCategory(name);
        System.out.println("Category created: " + category.getName());
    }

    private void displayAllCategories() {
        System.out.println("\n=== All Categories ===");
        if (categoryList.isEmpty()) {
            System.out.println("No categories available.");
            return;
        }

        for (Category category : categoryList) {
            System.out.println(category.getName() + " (" + category.getTasks().size() + " tasks)");
        }
    }

    private void setReminderForTask() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available to set reminders.");
            return;
        }

        System.out.println("\n=== Set Reminder ===");
        System.out.println("Available tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).getTitle() + 
                " (Due: " + taskList.get(i).getDueDate().format(dateTimeFormatter) + ")");
        }

        System.out.print("Select task: ");
        try {
            int taskChoice = Integer.parseInt(scanner.nextLine());
            if (taskChoice < 1 || taskChoice > taskList.size()) {
                System.out.println("Invalid task selection.");
                return;
            }

            Task selectedTask = taskList.get(taskChoice - 1);
            LocalDateTime reminderTime = null;
            while (reminderTime == null) {
                System.out.print("Enter reminder date/time (yyyy-MM-dd HH:mm): ");
                String reminderTimeStr = scanner.nextLine();
                try {
                    reminderTime = LocalDateTime.parse(reminderTimeStr, dateTimeFormatter);
                    if (reminderTime.isAfter(selectedTask.getDueDate())) {
                        System.out.println("Reminder cannot be after task due date.");
                        reminderTime = null;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm");
                }
            }

            Reminder reminder = createReminder(selectedTask, reminderTime);
            System.out.println("Reminder set for: " + 
                reminder.getReminderDateTime().format(dateTimeFormatter));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    private void checkActiveReminders() {
        System.out.println("\n=== Active Reminders ===");
        boolean hasActiveReminders = false;

        for (Reminder reminder : reminderList) {
            if (reminder.isReminderActive() && LocalDateTime.now().isAfter(reminder.getReminderDateTime())) {
                System.out.println("REMINDER: " + reminder.getTitle() + 
                    " (Due: " + reminder.getDueDate().format(dateTimeFormatter) + ")");
                hasActiveReminders = true;
            }
        }

        if (!hasActiveReminders) {
            System.out.println("No active reminders at this time.");
        }
    }

    private void logout() {
        if (currentUser != null) {
            System.out.println("Logging out " + currentUser.getUsername());
            currentUser.logout();
            currentUser = null;
        }
    }

    // Core application methods
    public Task createTask(String title, String description, LocalDateTime dueDate) {
        if (currentUser == null) throw new IllegalStateException("User not logged in");
        Task task = new Task(nextTaskId++, title, description, dueDate, currentUser);
        taskList.add(task);
        return task;
    }

    public void assignCategoryToTask(Task task, Category category) {
        if (task == null || category == null) return;
        category.addTask(task);
        task.addCategory(category);
    }

    public Category createCategory(String name) {
        Category category = new Category(name);
        categoryList.add(category);
        return category;
    }

    public Reminder createReminder(Task task, LocalDateTime reminderDateTime) {
        if (task == null) throw new IllegalArgumentException("Task cannot be null");
        Reminder reminder = new Reminder(
            task.getTaskId(),
            task.getTitle(),
            task.getDescription(),
            task.getDueDate(),
            task.getCreator(),
            reminderDateTime
        );
        reminderList.add(reminder);
        return reminder;
    }
}