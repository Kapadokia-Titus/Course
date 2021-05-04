package kapadokia.nyandoro.course.model;

public class User {
    private String userId;
    private String user_pass;
    private String course;

    public User() {
    }

    public User(String userId, String user_pass, String course) {
        this.userId = userId;
        this.user_pass = user_pass;
        this.course = course;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
