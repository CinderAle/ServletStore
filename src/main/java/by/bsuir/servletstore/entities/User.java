package by.bsuir.servletstore.entities;

public class User {
    private final int id;
    private final String name;
    private final String email;
    private final int role;
    private final boolean banStatus;

    public User(int id, String name, String email, int role, boolean banStatus) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.banStatus = banStatus;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getRole() {
        return role;
    }

    public boolean isBanStatus() {
        return banStatus;
    }
}
