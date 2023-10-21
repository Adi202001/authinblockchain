public class PasswordRecord {
    private String username;
    private String website;
    private String password;

    public PasswordRecord(String username, String website, String password) {
        this.username = username;
        this.website = website;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Username: " + username + "\n" +
               "Website: " + website + "\n" +
               "Password: " + password;
    }

    // You can add more methods for encryption/decryption and data validation as needed.
}
