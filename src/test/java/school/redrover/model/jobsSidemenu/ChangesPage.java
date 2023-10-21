package school.redrover.model.jobsSidemenu;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BasePage;

public class ChangesPage<JobTypePage extends BasePage<?, ?>> extends BaseMainHeaderPage<ChangesPage<JobTypePage>> {

    @FindBy(xpath = "//div[@id='main-panel']")
    private WebElement mainPanel;

    private final JobTypePage jobTypePage;

    public ChangesPage(JobTypePage jobTypePage) {
        super(jobTypePage.getDriver());
        this.jobTypePage = jobTypePage;
    }

    @Step("Get Heading text from Changes page")
    public String getTextOfPage() {
        return getWait5().until(ExpectedConditions.visibilityOf(mainPanel)).getText();
    }
}
