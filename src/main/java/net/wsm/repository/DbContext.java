package net.wsm.repository;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import com.google.gson.*;

import javax.naming.spi.DirStateFactory.Result;

import net.wsm.repository.deserializers.LocalDateDeserializer;
import net.wsm.repository.deserializers.LocalDateTimeDeserializer;

public class DbContext {
    private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer()).registerTypeAdapter(LocalDate.class, new LocalDateDeserializer()).create();

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
            System.out.println(r.getString(1));
            T[] o = (T[]) gson.fromJson(r.getString(1), Array.newInstance(c, 255).getClass());
            System.out.println(o.toString());
            return o;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public synchronized <T> T[] getAsync(Class c, String[] conditions) {
        try {
            Connection con = getDbConnection();
            Statement s = con.createStatement();
            String conds = "";
            for (int i = 0; i < conditions.length; i++) {
                conds += conditions[i];
            }
            ResultSet r = s
                    .executeQuery(String.format("SELECT * FROM [%s] WHERE %s FOR JSON PATH", c.getName(), conds));
            r.next();
            System.out.println(r.getString(1));
            Gson gson = new Gson();
            T[] o = (T[]) gson.fromJson(r.getString(1), Array.newInstance(c, 255).getClass());
            System.out.println(o.toString());
            return o;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
