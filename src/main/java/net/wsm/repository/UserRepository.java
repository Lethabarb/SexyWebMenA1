package net.wsm.repository;

import java.sql.*;
import java.util.ArrayList;

import javax.persistence.EntityExistsException;


import net.wsm.model.*;

public class UserRepository {
    private DbContext context = new DbContext();
    private Connection con = context.getDbConnection();

    public synchronized User[] getAll() {
        // ArrayList<User> list = new ArrayList<>();
        // ResultSet res;
        // try {
        // Statement req = con.createStatement();
        // res = req.executeQuery("SELECT * FROM [User]");
        // while (res.next()) {
        // User u = new User(res.getString(1),
        // res.getInt(2),
        // res.getString(3),
        // res.getString(4),
        // res.getString(5),
        // res.getString(6),
        // res.getTimestamp(7).toLocalDateTime());
        // list.add(u);
        // }
        // } catch (SQLException e) {
        // System.out.println(e.getMessage());
        // }
        // list = context.getAsync(list.getClass());
        User[] users = context.getAsync(User.class);
        return context.getAsync(User.class);
    }

    public synchronized User getByEmail(String email) {
        // ResultSet res;
        // User u = new User();
        // try {
        // Statement req = con.createStatement();
        // res = req.executeQuery(String.format("SELECT * FROM [User] WHERE [email] =
        // '%s'", email));
        // res.next();
        // u = new User(res.getString(2),
        // res.getInt(3),
        // res.getString(4),
        // res.getString(5),
        // res.getString(6),
        // res.getString(7),
        // res.getTimestamp(8).toLocalDateTime());
        // } catch (SQLException e) {
        // System.out.println(e.getMessage());
        // return new User("err",0,"err");
        // }
        User[] users = context.getAsync(User.class, String.format("email = '%s'", email));
        return users[0];
    }

    public synchronized boolean createUser(User user) {
        // try {
        //     Statement req = con.createStatement();
        //     if (!getByEmail(user.getEmail()).getEmail().equals("err"))
        //         throw new EntityExistsException();
        //     req.executeUpdate(String.format("INSERT INTO [User] VALUES ('%s',%o,'%s','%s','%s','%s','%s')",
        //             user.getEmail(), user.getRole(), user.getAuthToken(), user.getFirstName(), user.getFirstName(),
        //             user.getContactNumber(), java.sql.Date.valueOf(user.getTokenExp().toLocalDate())));
        //     return true;
        // } catch (Exception e) {
        //     System.out.println(e.getMessage());
        //     return false;
        // }
        // if (user.getId() == null || user.getId().equals("")) return false;
        return context.CreateAsync(user);
    }

    public synchronized boolean updateUser(User user) {
        // User old = getByEmail(email);
        // String query = "UPDATE [User] SET ";
        // boolean first = true;
        // if (!user.getEmail().equals(old.getEmail())) {
        // query += String.format("email = '%s'", user.getEmail());
        // first = false;
        // }
        // if (user.getRole() != old.getRole()) {
        // if (!first) query += ", ";
        // query += String.format("role = '%o'", user.getRole());
        // first = false;
        // }
        // if (!user.getAuthToken().equals(old.getAuthToken())) {
        // if (!first) query += ", ";
        // query += String.format("authToken = '%s'", user.getAuthToken());
        // first = false;
        // }
        // if (!user.getFirstname().equals(old.getFirstname())) {
        // if (!first) query += ", ";
        // query += String.format("firstName = '%s'", user.getFirstname());
        // first = false;
        // }
        // if (!user.getSurname().equals(old.getSurname())) {
        // if (!first) query += ", ";
        // query += String.format("lastName = '%s'", user.getSurname());
        // first = false;
        // }
        // if (!user.getContactNumber().equals(old.getContactNumber())) {
        // if (!first) query += ", ";
        // query += String.format("contactNumber = '%s'", user.getContactNumber());
        // first = false;
        // }
        // if (!user.getTokenExpiry().equals(old.getTokenExpiry())) {
        // if (!first) query += ", ";
        // query += String.format("tokenExp = '%s'",
        // java.sql.Date.valueOf(user.getTokenExpiry().toLocalDate()));
        // first = false;
        // }
        // query += String.format(" WHERE email = '%s'", old.getEmail());
        // try {
        // Statement req = con.createStatement();
        // System.out.println(query);
        // req.executeUpdate(query);
        // return true;

        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // return false;
        // }
        return context.updateAsync(user, user.getId());
    }

    public boolean deleteUser(String email) {
        // try {
        //     Statement req = con.createStatement();
        //     req.executeUpdate(String.format("DELETE FROM [User] WHERE [email] = %s", email));
        //     return true;

        // } catch (Exception e) {
        //     System.out.println(e.getMessage());
        //     return false;
        // }
        User u = getByEmail(email);
        return context.deleteAsync(User.class, u.getId());
    }
}