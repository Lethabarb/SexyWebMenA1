package net.wsm.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import ch.qos.logback.core.subst.Token.Type;

public class DbContext {
    protected Connection getDbConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection c = DriverManager.getConnection("jdbc:sqlserver://wsm-seng2050.database.windows.net:1433;database=wsm;user=wsm@wsm-seng2050;password=Seng2050;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
            return c;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
