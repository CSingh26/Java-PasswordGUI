package main.java.model;

public class PasswordEntry {
    private String userName;
    private String emailId;
    private String serviceName;
    private String password;

    public PasswordEntry() {
        this.userName = null;
        this.emailId = null;
        this.serviceName = null;
        this.password = null;
    }

    public PasswordEntry(String newUser, String userEmailId, String serName, String pwd) {
        this.userName = newUser;
        this.emailId = userEmailId;
        this.serviceName = serName;
        this.password = pwd;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        if (userName != null && !userName.trim().isEmpty()) {
            this.userName = userName;
        }
    }

    public void setEmailId(String emailId) {
        if (emailId != null && !emailId.trim().isEmpty()) {
            this.emailId = emailId;
        }
    }

    public void setServiceName(String serviceName) {
        if (serviceName != null && !serviceName.trim().isEmpty()) {
            this.serviceName = serviceName;
        }
    }

    public void setPassword(String password) {
        if (password != null && !password.trim().isEmpty()) {
            this.password = password;
        }
    }
}
