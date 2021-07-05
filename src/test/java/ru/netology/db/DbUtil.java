package ru.netology.db;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static BeanHandler<User> userResultHandler = new BeanHandler<>(User.class);
    private static BeanListHandler<Code> codeResultHandler = new BeanListHandler<>(Code.class);

    private static QueryRunner runner = new QueryRunner();

    public static void clearDb() throws SQLException {
        var usersSQL = "DELETE from users";
        var codeSQL = "DELETE from auth_codes";
        var cardsSQL = "DELETE from cards";
        var cardsTransSQL = "DELETE from card_transactions";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app",
                        "pass")
        ) {
            runner.execute(conn, cardsTransSQL);
            runner.execute(conn, cardsSQL);
            runner.execute(conn, codeSQL);
            runner.execute(conn, usersSQL);
        }
    }


    public static String getUserId(String login) throws SQLException {
        var dataSQL = "select * from users where login = ?";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app",
                        "pass")
        ) {
            return runner.query(conn, dataSQL, userResultHandler, login).getId();
        }
    }

    public static String getCode(String userId) throws SQLException {
        var dataSQL = "select id, user_id as user, code, created from auth_codes where user_id = ? order by created desc";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app",
                        "pass")
        ) {
            return runner.query(conn, dataSQL, codeResultHandler, userId).get(0).getCode();
        }
    }


}
