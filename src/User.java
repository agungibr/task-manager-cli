import java.util.Objects;

public class User {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String role;

    public User(String userId, String username, String password, String email, String role) {
        if (userId == null || userId.isEmpty()) throw new IllegalArgumentException("User ID cannot be empty");
        if (username == null || username.isEmpty()) throw new IllegalArgumentException("Username cannot be empty");
        if (password == null || password.isEmpty()) throw new IllegalArgumentException("Password cannot be empty");
        if (email == null || email.isEmpty()) throw new IllegalArgumentException("Email cannot be empty");
        
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role != null ? role : "member";
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void logout() {
        System.out.println("User " + username + " logged out");
    }

    public boolean canEditTask(Task task) {
        return "admin".equals(role) || this.equals(task.getCreator());
    }

    // Getters
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getRole() { return role; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}