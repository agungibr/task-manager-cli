import java.time.LocalDateTime;

public class Reminder extends Task {
    private LocalDateTime reminderDateTime;
    private boolean isActive;

    public Reminder(int taskId, String title, String description, 
                   LocalDateTime dueDate, User creator, LocalDateTime reminderDateTime) {
        super(taskId, title, description, dueDate, creator);
        setReminderDateTime(reminderDateTime);
        this.isActive = true;
    }

    public LocalDateTime getReminderDateTime() {
        return reminderDateTime;
    }

    public void setReminderDateTime(LocalDateTime reminderDateTime) {
        if (reminderDateTime == null) {
            throw new IllegalArgumentException("Reminder date/time cannot be null");
        }
        if (reminderDateTime.isAfter(getDueDate())) {
            throw new IllegalArgumentException("Reminder cannot be after due date");
        }
        this.reminderDateTime = reminderDateTime;
    }

    public void activateReminder() {
        this.isActive = true;
    }

    public void deactivateReminder() {
        this.isActive = false;
    }

    public boolean isReminderActive() {
        return isActive;
    }

    public boolean shouldTrigger() {
        return isActive && LocalDateTime.now().isAfter(reminderDateTime);
    }

    @Override
    public String toString() {
        return "Reminder for '" + getTitle() + "' at " + reminderDateTime + 
               " (Active: " + isActive + ")";
    }
}