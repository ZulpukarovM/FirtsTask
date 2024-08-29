package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
         String sql = "CREATE TABLE `newdb`.`users` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `idusers_UNIQUE` (`id` ASC) VISIBLE)";
        try(Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate(sql);

        } catch (SQLException e ){
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE `newdb`.`users`";
        try (Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users (name, lastName, age) values ('"+name+"', '"+lastName+"', '"+age+"')";
        try(PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)){
            preparedStatement.execute();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = "delete from `newdb`.`users` where id = "+id;
        try (Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String sql = "select * from `newdb`.`users`";
        try (Statement statement = Util.getConnection().createStatement()){
            ArrayList<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                users.add(new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age")));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void cleanUsersTable() {
        String sql = "delete FROM users";
        try (Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
