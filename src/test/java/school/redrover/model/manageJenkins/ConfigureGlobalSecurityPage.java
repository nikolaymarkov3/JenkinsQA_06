package school.redrover.model.manageJenkins;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseSubmenuPage;
import school.redrover.runner.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class ConfigureGlobalSecurityPage extends BaseSubmenuPage<ConfigureGlobalSecurityPage> {

    @FindBy(css = ".jenkins-section__title")
    private List<WebElement> listSectionTitles;

    @FindBy(css = ".jenkins-form-label")
    private List<WebElement> listFormLabels;

    @FindBy(xpath = "//a[starts-with(@tooltip,'Help')]")
    private List<WebElement> listHelpButtons;

    @FindBy(xpath = "//div[@class='jenkins-form-item ']//div[@class='jenkins-select']//option")
    private List<WebElement> menus;

    @FindBy(xpath = "//div[@class='jenkins-radio']")
    private List<WebElement> radioButtons;

    @FindBy(xpath = "//div[@nameref='rowSetStart6']//span[@class='jenkins-checkbox']")
    private List<WebElement> apiTokenCheckboxes;

    @FindBy(xpath = "//div[@class='jenkins-form-item ']//div[@class='jenkins-select']")
    private WebElement hostKeyVerificationDropdown;

    @FindBy(xpath = "//button[@name = 'Apply']")
    private WebElement applyButton;

    @FindBy(xpath = "//div[@id='notification-bar']/span")
    private WebElement savedNotificationText;

    public ConfigureGlobalSecurityPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String callByMenuItemName() {
        return "Configure Global Security";
    }

    @Step("Get number of Form's Labels on the Configure Global Security page")
    public int getNumberOfTitles() {
        return listFormLabels.size();
    }

    @Step("Get number of of Help Buttons on the Configure Global Security page")
    public int getNumberOfHelpButtons() {
        return listHelpButtons.size();
    }

    @Step("Click on 'Host Key Verification Strategy' dropdown menu on the Configure Global Security page")
    public ConfigureGlobalSecurityPage navigateToHostKeyVerificationStrategyDropdownAndClick() {
        Actions action = new Actions(getDriver());
        action.moveToElement(hostKeyVerificationDropdown).click().perform();

        return this;
    }

    @Step("Get list of 'Host Key Verification Strategy' dropdown menu on the Configure Global Security page")
    public List<String> getDropDownMenuTexts() {
        return TestUtils.getTexts(menus);
    }

    @Step("Get list of 'API Token' Checkboxes on the Configure Global Security page")
    private List<WebElement> getAPITokenCheckboxes() {
        return apiTokenCheckboxes;
    }

    @Step("Get list of radio buttons on the Configure Global Security page")
    public List<WebElement> getRadioButtons() {
        return radioButtons;
    }

    @Step("Check list of 'API Token' Checkboxes is clickable on the Configure Global Security page")
    public boolean checkAPITokenCheckboxes() {
        for (int i = 0; i <= 2; i++) {
            WebElement checkBox = getWait2().until(ExpectedConditions.elementToBeClickable(getAPITokenCheckboxes().get(i)));
            if (!checkBox.isSelected()) {
                new Actions(getDriver()).click(checkBox).perform();
            }
            if (!checkBox.isEnabled()) {

                return false;
            }
        }

        return true;
    }

    @Step("Check list of radio buttons is clickable on the Configure Global Security page")
    public boolean checkRadioButtons() {
        for (int i = 0; i <= 5; i++) {
            WebElement radioButton = getWait5().until(ExpectedConditions.elementToBeClickable(getRadioButtons().get(i)));
            if (!radioButton.isSelected()) {
                new Actions(getDriver()).click(radioButton).perform();
            }
            if (!radioButton.isEnabled()) {

                return false;
            }
        }

        return true;
    }

    @Step("Click on the 'Apply' button on the Configure Global Security page")
    public ConfigureGlobalSecurityPage clickApplyButton() {
        applyButton.click();

        return new ConfigureGlobalSecurityPage(getDriver());
    }

    @Step("Get notification text after click on 'Apply' button on the Configure Global Security page")
    public String getSavedNotificationText() {
        return getWait5().until(ExpectedConditions.visibilityOf(savedNotificationText)).getText();
    }

    @Step("Get list of Section's Titles on the Configure Global Security page")
    public List<String> getSectionTitleList() {
        List<String> sectionTitleList = new ArrayList<>();
        for (WebElement element : listSectionTitles) {
            sectionTitleList.add(element.getText());
        }
        return sectionTitleList;
    }
}
