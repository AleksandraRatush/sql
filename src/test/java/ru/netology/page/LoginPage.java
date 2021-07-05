package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;
import ru.netology.db.User;

import static com.codeborne.selenide.Selenide.$;


public class LoginPage {

    private final SelenideElement login = $("[data-test-id='login'] .input__control");
    private final SelenideElement password = $("[data-test-id='password'] .input__control");
    private final SelenideElement actionLogin = $("[data-test-id='action-login']");



    public CodePage login(DataHelper.User user) {
        login.setValue(user.getLogin());
        password.setValue(user.getPassword());
        actionLogin.click();
        return new CodePage();
    }

    public void fakeLogin(DataHelper.User user) {
        login.setValue(user.getLogin());
        password.setValue(user.getPassword());
        actionLogin.click();
        $("[data-test-id='error-notification'].notification_visible")
                .should(Condition.exist);
    }


}
