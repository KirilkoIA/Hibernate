package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class Util {
    private static final String URl = "jdbc:mysql://localhost:3306/testbase";
    private static final String userNameDB = "root";
    private static final String passwordDB = "suhar_17a17";
    private static final String ConDB = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/testbase?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    private static SessionFactory sessionFactory;


    public Util() {

    }

    public Statement getConnection() {
        try {
            Class.forName(ConDB);
            return (DriverManager.getConnection(URl, userNameDB, passwordDB)).createStatement();
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties prop = new Properties();

                prop.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                prop.put(Environment.USER, userNameDB );
                prop.put(Environment.PASS, passwordDB);
                prop.put(Environment.URL, URL);
                prop.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                prop.put(Environment.SHOW_SQL, "true");
                prop.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(prop);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(registry);
            }catch(Exception exc) {
                exc.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
