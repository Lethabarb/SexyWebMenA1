package net.wsm.repository;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.google.gson.*;

import net.wsm.repository.deserializers.LocalDateDeserializer;
import net.wsm.repository.deserializers.LocalDateTimeDeserializer;

public class DbContext {
    private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer()).create();

    protected Connection getDbConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection c = DriverManager.getConnection(
                    "jdbc:sqlserver://wsm-seng2050.database.windows.net:1433;database=wsm;user=wsm@wsm-seng2050;password=Seng2050;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
            return c;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    public synchronized <T> T[] getAsync(Class c) {
        try {
            Connection con = getDbConnection();
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery(String.format("SELECT * FROM [%s] FOR JSON PATH", c.getSimpleName()));
            r.next();
            T[] o = (T[]) gson.fromJson(r.getString(1), Array.newInstance(c, 255).getClass());
            return o;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public synchronized <T> T[] getAsync(Class c, String... conditions) {
        try {
            Connection con = getDbConnection();
            Statement s = con.createStatement();
            String conds = "";
            for (int i = 0; i < conditions.length; i++) {
                conds += conditions[i];
                if (i < conditions.length - 1) {
                    conds += " and ";
                }
            }
            System.out.println((String.format("SELECT * FROM [%s] WHERE %s FOR JSON PATH", c.getSimpleName(), conds)));
            ResultSet r = s
                    .executeQuery(String.format("SELECT * FROM [%s] WHERE %s FOR JSON PATH", c.getSimpleName(), conds));
            r.next();
            T[] o = (T[]) gson.fromJson(r.getString(1), Array.newInstance(c, 255).getClass());
            return o;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public synchronized boolean updateAsync(Object O, int id) {
        // Object old = getAsync(O.getClass(), String.format("id = %d", id));
        try {
            Connection con = getDbConnection();
            Statement s = con.createStatement();
            String query = String.format("UPDATE [%s] SET ", O.getClass().getSimpleName());
            boolean first = true;
            for (Field f : O.getClass().getDeclaredFields()) {
                if (!f.getName().equals("id")) {
                    if (!first)
                        query += ", ";
                    first = false;
                    System.out.println(f.getType().getSimpleName());
                    if (f.getType().getSimpleName().equals("int")) {
                        Method m = O.getClass().getMethod(
                                "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1));
                        if (!m.getReturnType().getSimpleName().equals("int"))
                            throw new IllegalArgumentException("method does not return int");
                        query += String.format("[%s] = %s", f.getName(), m.invoke(O));
                    } else if (f.getType().getSimpleName().equals("LocalDateTime")) {
                        Method m = O.getClass().getMethod(
                                "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1));
                        query += String.format("[%s] = '%s'", f.getName(),
                                java.sql.Date.valueOf(((LocalDateTime) m.invoke(O)).toLocalDate()));
                    } else {
                        Method m = O.getClass().getMethod(
                                "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1));
                        query += String.format("[%s] = '%s'", f.getName(), m.invoke(O));
                    }
                }
            }
            query += String.format(" WHERE id = %d", id);
            System.out.println(query);
            int r = s.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println("ERROR: ");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public synchronized boolean updateAsync(Object O, String id) {
        // Object old = getAsync(O.getClass(), String.format("id = %d", id));
        try {
            Connection con = getDbConnection();
            Statement s = con.createStatement();
            String query = String.format("UPDATE [%s] SET ", O.getClass().getSimpleName());
            boolean first = true;
            for (Field f : O.getClass().getDeclaredFields()) {
                if (!f.getName().equals("id")) {
                    if (!first)
                        query += ", ";
                    first = false;
                    System.out.println(f.getType().getSimpleName());
                    if (f.getType().getSimpleName().equals("int")) {
                        Method m = O.getClass().getMethod(
                                "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1));
                        if (!m.getReturnType().getSimpleName().equals("int"))
                            throw new IllegalArgumentException("method does not return int");
                        query += String.format("[%s] = %s", f.getName(), m.invoke(O));
                    } else if (f.getType().getSimpleName().equals("LocalDateTime")) {
                        Method m = O.getClass().getMethod(
                                "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1));
                        query += String.format("[%s] = '%s'", f.getName(),
                                java.sql.Date.valueOf(((LocalDateTime) m.invoke(O)).toLocalDate()));
                    } else {
                        Method m = O.getClass().getMethod(
                                "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1));
                        query += String.format("[%s] = '%s'", f.getName(), m.invoke(O));
                    }
                }
            }
            query += String.format(" WHERE id = %s", id);
            System.out.println(query);
            int r = s.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println("ERROR: ");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public synchronized boolean deleteAsync(Class c, int id) {
        try {
            Connection con = getDbConnection();
            Statement s = con.createStatement();
            s.execute(String.format("DELETE FROM [%s] WHERE id=%d", c.getSimpleName(), id));
            return true;
        } catch (Exception e) {
            System.out.println("ERROR: ");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public synchronized boolean deleteAsync(Class c, String id) {
        try {
            Connection con = getDbConnection();
            Statement s = con.createStatement();
            s.execute(String.format("DELETE FROM [%s] WHERE id=%s", c.getSimpleName(), id));
            return true;
        } catch (Exception e) {
            System.out.println("ERROR: ");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public synchronized boolean CreateAsync(Object O) {
        try {
            Connection con = getDbConnection();
            Statement s = con.createStatement();
            String query = String.format("INSERT INTO [%s] VALUES (", O.getClass().getSimpleName());
            boolean first = true;
            for (Field f : O.getClass().getDeclaredFields()) {
                if (!first)
                    query += ", ";
                first = false;
                System.out.println(f.getType().getSimpleName());
                if (f.getType().getSimpleName().equals("int")) {
                    Method m = O.getClass().getMethod(
                            "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1));
                    if (!m.getReturnType().getSimpleName().equals("int"))
                        throw new IllegalArgumentException("method does not return int");
                    query += String.format("%s", m.invoke(O));
                } else if (f.getType().getSimpleName().equals("LocalDateTime")) {
                    Method m = O.getClass().getMethod(
                            "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1));
                    query += String.format("'%s'",
                            java.sql.Date.valueOf(((LocalDateTime) m.invoke(O)).toLocalDate()));
                } else {
                    Method m = O.getClass().getMethod(
                            "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1));
                    query += String.format("'%s'", m.invoke(O));
                }
            }
            query += ")";
            System.out.println(query);
            s.execute(query);
            return true;
        } catch (Exception e) {
            System.out.println("ERROR: ");
            System.out.println(e.getMessage());
            return false;
        }
    }

}
