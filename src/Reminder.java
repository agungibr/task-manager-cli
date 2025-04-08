import java.time.LocalDateTime;

public class Reminder extends Task {
    private LocalDateTime reminderDateTime;
    private boolean isActive;

    public Reminder(String taskId, String title, String description, LocalDateTime dueDate, LocalDateTime reminderDateTime) {
        super(taskId, title, description, dueDate);
        this.reminderDateTime = reminderDateTime;
        this.isActive = true; 
    }

    public LocalDateTime getReminderDateTime() {
        return reminderDateTime;
    }

    public void setReminderDateTime(LocalDateTime reminderDateTime) {
        this.reminderDateTime = reminderDateTime;
    }

    public void activateReminder() {
        isActive = true;
        System.out.println("Reminder diaktifkan untuk: " + getTitle());
    }

    public void deactivateReminder() {
        isActive = false;
        System.out.println("Reminder dinonaktifkan untuk: " + getTitle());
    }

    public boolean isReminderActive() {
        return isActive;
    }

    public void checkReminder() {
        if (isActive && LocalDateTime.now().isAfter(reminderDateTime)) {
            System.out.println("Reminder: " + getTitle() + " akan hampir habis");
        }
    }
}