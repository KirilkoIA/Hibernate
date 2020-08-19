package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserDaoHibernateImpl userTable = new UserDaoHibernateImpl();
        userTable.createUsersTable();

        User user = new User("Садам", "Хусейн", (byte) 40);
        User user1 = new User("Осам", "Бенладен", (byte) 42);
        User user2 = new User("Никола", "Тесла", (byte) 34);
        User user3 = new User("Николай", "Коперник", (byte) 53);

        userTable.saveUser(user.getName(), user.getLastName(), user.getAge());
        userTable.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userTable.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userTable.saveUser(user3.getName(), user3.getLastName(), user3.getAge());

        List<User> list = userTable.getAllUsers();
        for (User userPrint : list) {
            System.out.println(userPrint.toString());
        }

        userTable.cleanUsersTable();

        userTable.dropUsersTable();

    }
}
