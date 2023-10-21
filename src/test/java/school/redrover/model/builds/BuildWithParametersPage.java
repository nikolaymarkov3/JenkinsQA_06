package school.redrover.model.builds;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BaseProjectPage;

import java.util.ArrayList;
import java.util.List;

public class BuildWithParametersPage<JobTypePage extends BaseProjectPage<?>> extends BaseMainHeaderPage<BuildWithParametersPage<JobTypePage>> {

    @FindBy(xpath = "//button[@name='Submit']")
    private WebElement buildButton;

    @FindBy(xpath = "//td[@class='build-row-cell']")
    private WebElement buildRowCell;

    @FindBy(xpath = "//div[@class='jenkins-form-description']")
    private WebElement parameterDescription;

    @FindBy(xpath = "//select[@name='value']/option")
    private List<WebElement> choiceParametersList;

    @FindBy(xpath = "//label[@class='attach-previous ']")
    private WebElement booleanParameterName;

    @FindBy(xpath = "//input[@name='value']")
    private WebElement booleanParameterCheckbox;

    @FindBy(xpath = "//table[contains(@class, 'jenkins-pane')]//tr")
    private List<WebElement> buildList;

    private final JobTypePage jobTypePage;

    public BuildWithParametersPage(JobTypePage jobTypePage) {
        super(jobTypePage.getDriver());
        this.jobTypePage = jobTypePage;
    }

    @Step("Click to build")
    public JobTypePage clickBuild(){
        buildButton.click();
        if (buildList.size() < 2) {
            getDriver().navigate().refresh();
            getWait5().until(ExpectedConditions.visibilityOf(buildRowCell));
        }

        return jobTypePage;
    }

    @Step("Check if the name of the added parameter is displayed")
    public boolean isParameterNameDisplayed(String parameterName) {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String
                        .format("//div[@class = 'jenkins-form-label help-sibling' and text() = '%s']", parameterName))))
                .isDisplayed();
    }

    @Step("Get description of the parameter")
    public String getParameterDescription() {
        return parameterDescription.getText();
    }

    @Step("Get list of parameters")
    public List<WebElement> getChoiceParametersList() {
        return choiceParametersList;
    }

    public List<String> getChoiceParametersValuesList() {
        if (getChoiceParametersList().size() > 0) {
            getWait10().until(ExpectedConditions.visibilityOfAllElements(getChoiceParametersList()));
            List<String> valuesList = new ArrayList<>();
            for (WebElement element : getChoiceParametersList()) {
                valuesList.add(element.getText());
            }

            return valuesList;
        }

        return null;
    }

    @Step("Get name of the boolean parameter")
    public String getBooleanParameterName() {
        return booleanParameterName.getText();
    }

    @Step("Get 'checked' attribute of the boolean parameter")
    public String getBooleanParameterCheckbox() {
        return booleanParameterCheckbox.getAttribute("checked");
    }

    @Step("Get state of the boolean parameter")
    public boolean checkedTrue() {
        if(getBooleanParameterCheckbox().equals("true")){

            return true;
        }

        return false;
    }
}
