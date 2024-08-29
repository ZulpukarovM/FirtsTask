package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Магомед", "Зулпукаров", (byte)20);
        userService.saveUser("Абдул", "Ахмедов", (byte)21);
        userService.saveUser("Ахмедхан", "Ахмедханов", (byte)23);
        userService.saveUser("Амир", "Сердеров", (byte)19);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
