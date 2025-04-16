import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category {
    private String name;
    private List<Task> tasks = new ArrayList<>();

    public Category(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        this.name = name;
    }

    public void addTask(Task task) {
        if (task != null && !tasks.contains(task)) {
            tasks.add(task);
            if (!task.getCategories().contains(this)) {
                task.addCategory(this);
            }
        }
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.getCategories().remove(this);
    }

    // Getters and setters
    public String getName() { return name; }
    public List<Task> getTasks() { return new ArrayList<>(tasks); }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name.equalsIgnoreCase(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    @Override
    public String toString() {
        return "Category: " + name + " (" + tasks.size() + " tasks)";
    }
}