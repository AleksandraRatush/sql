package ru.netology.test;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.db.DbUtil;
import ru.netology.page.CodePage;
import ru.netology.page.LoginPage;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {


    @BeforeEach
    public void init() {
        open("http://localhost:9999/");
    }

    @AfterAll
    public static void down() {
        //DbUtil.clearDb();
    }

    @Test
    public void loginSuccess() throws SQLException {
        LoginPage loginPage = new LoginPage();
        DataHelper.User user = DataHelper.getUser();
        String userId = DbUtil.getUserId(user.getLogin());
        CodePage codePage = loginPage.login(user);
        String code = DbUtil.getCode(userId);
        codePage.inputCode(code);
    }

    @Test
    public void loginFakePass() {
        LoginPage loginPage = new LoginPage();
        DataHelper.User user = DataHelper.getUserWithFakePassword();
        loginPage.fakeLogin(user);
    }

    @Test
    public void loginFakeLogin() {
        LoginPage loginPage = new LoginPage();
        DataHelper.User user = DataHelper.getUserWithFakeLogin();
        loginPage.fakeLogin(user);
    }

    @Test
    public void loginFakeCode() throws SQLException {
        LoginPage loginPage = new LoginPage();
        DataHelper.User user = DataHelper.getUser();
        String userId = DbUtil.getUserId(user.getLogin());
        CodePage codePage = loginPage.login(user);
        String code = DbUtil.getCode(userId);
        codePage.inputFakeCode(DataHelper.makeCodeFake(code));
    }
}