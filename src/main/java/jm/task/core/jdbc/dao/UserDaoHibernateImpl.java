package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        try(Session session =  Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            NativeQuery query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                    "(id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY, " +
                    " name VARCHAR(255), " +
                    " lastName VARCHAR(255), " +
                    " age INTEGER)");
            query.executeUpdate();
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {

        try(Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
        }catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User c именем - " + name + " добавлен в базу данных");
        }catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {

        try(Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("FROM User").list();
        }catch (Exception exc) {
            exc.printStackTrace();
            return null;
        }

    }

    @Override
    public void cleanUsersTable() {

        try(Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM Users").executeUpdate();
        }catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}
