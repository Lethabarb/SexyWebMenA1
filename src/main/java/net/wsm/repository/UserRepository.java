package net.wsm.repository;

import java.sql.*;

import net.wsm.model.*;

public class UserRepository {
    private DbContext context = new DbContext();
    private Connection con = context.getDbConnection();

    public synchronized User[] getAll() {
        User[] users = context.getAsync(User.class);
        return context.getAsync(User.class);
    }

    public synchronized User getByEmail(String email) {
        
        User[] users = context.getAsync(User.class, String.format("email = '%s'", email));
        return users == null ? new User("notFound", 0, "notFound"): users[0];
    }

    public synchronized User getById(int id) {
        User[] users = context.getAsync(User.class, String.format("id = %s", id));
        return users[0];
    }

    public synchronized boolean createUser(User user) {
        return context.CreateAsync(user);
    }

    public synchronized boolean updateUser(User user) {
        return context.updateAsync(user, user.getId());
    }

    public boolean deleteUser(String email) {
        User u = getByEmail(email);
        return context.deleteAsync(User.class, u.getId());
    }
}