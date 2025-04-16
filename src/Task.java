import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task {
    private int taskId;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private User creator;
    private Priority priority;
    private Status status;
    private List<Category> categories = new ArrayList<>();

    public Task(int taskId, String title, String description, LocalDateTime dueDate, User creator) {
        if (title == null || title.isEmpty()) throw new IllegalArgumentException("Title cannot be empty");
        if (dueDate == null) throw new IllegalArgumentException("Due date cannot be null");
        if (creator == null) throw new IllegalArgumentException("Creator cannot be null");
        
        this.taskId = taskId;
        this.title = title;
        this.description = description != null ? description : "";
        this.dueDate = dueDate;
        this.creator = creator;
        this.priority = Priority.NOT_URGENT;
        this.status = Status.NOT_STARTED;
    }

    public void addCategory(Category category) {
        if (category != null && !categories.contains(category)) {
            categories.add(category);
            if (!category.getTasks().contains(this)) {
                category.addTask(this);
            }
        }
    }

    // Getters and setters
    public int getTaskId() { return taskId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDateTime getDueDate() { return dueDate; }
    public User getCreator() { return creator; }
    public Priority getPriority() { return priority; }
    public Status getStatus() { return status; }
    public List<Category> getCategories() { return new ArrayList<>(categories); }

    public void setTitle(String title) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        }
    }

    public void setDescription(String description) {
        this.description = description != null ? description : "";
    }

    public void setDueDate(LocalDateTime dueDate) {
        if (dueDate != null) {
            this.dueDate = dueDate;
        }
    }

    public void setPriority(Priority priority) {
        if (priority != null) {
            this.priority = priority;
        }
    }

    public void setStatus(Status status) {
        if (status != null) {
            this.status = status;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskId == task.taskId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId);
    }
}