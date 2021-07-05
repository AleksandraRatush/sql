package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CodePage {

    private final SelenideElement code = $("[data-test-id='code'] .input__control");
    private final SelenideElement actionVerify =  $("[data-test-id='action-verify']");


    public void inputCode(String code) {
           this.code.setValue(code);
           actionVerify.click();
    }

    public void inputFakeCode(String code) {
        this.code.setValue(code);
        actionVerify.click();
        $("[data-test-id='error-notification'].notification_visible")
                .should(Condition.exist);
    }

}
