package school.redrover.model;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class CreateBugPage extends BaseMainHeaderPage<CreateItemErrorPage> {

    @FindBy(xpath = "//div[@id = 'error-description']//h2")
    private WebElement errorMessage;

    public CreateBugPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get an Error message text")
    public String getErrorMessage() {
        return getWait5().until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }
}
