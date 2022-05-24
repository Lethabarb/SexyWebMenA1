package net.wsm.repository;

import java.sql.*;
import java.util.ArrayList;
import net.wsm.model.*;

public class UserRepository {
    private DbContext context = new DbContext();
    private Connection con = context.getDbConnection();

    protected Statement req = con.createStatement();

    public ArrayList<User> getAll() {
        ArrayList<User> list = new ArrayList<>();
        ResultSet res;
        try {
            res = req.executeQuery("SELECT * FROM [User]");
            while (res.next()) {
                User u = new User(res.getString(1),
                        res.getInt(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                        res.getTimestamp(7).toLocalDateTime());
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public User getByEmail(String email) {
        ResultSet res;
        User u = new User();
        try {
            res = req.executeQuery(String.format("SELECT * FROM [User] WHERE [email] = '%s'", email));
            res.next();
            u = new User(res.getString(1),
                    res.getInt(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6),
                    res.getTimestamp(7).toLocalDateTime());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return u;
    }
}