package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {

    @FindBy(id="txtUsername")
    public WebElement usernameField;

    @FindBy(id="txtPassword")
    public WebElement passwordField;

    @FindBy(name="Submit")
    public WebElement loginButton;

    @FindBy(xpath = "//span[@id='spanMessage']")
    public WebElement expMsg;

   public LoginPage() {
       PageFactory.initElements(driver, this);
    }

}
