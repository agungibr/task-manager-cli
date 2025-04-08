public class User {
    private String userId;
    private String username;
    private String password;
    private String email;

    public User(String userId, String username, String password, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void register(String userId, String username, String password, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        System.out.println("Akun berhasil dibuat.");
    }

    public boolean login(String inputUsername, String inputPassword) {
        if (!this.username.equals(inputUsername)) {
            System.out.println("Username salah.");
            return false;
        }
    
        if (!this.password.equals(inputPassword)) {
            System.out.println("Password salah.");
            return false;
        }
    
        System.out.println("Login berhasil.");
        return true;
    }    

    public void logout() {
        System.out.println("Logout berhasil.");
    }

    public void updateProfile(String newEmail, String newPassword) {
        this.email = newEmail;
        this.password = newPassword;
        System.out.println("Data akun berhasil diperbarui.");
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
