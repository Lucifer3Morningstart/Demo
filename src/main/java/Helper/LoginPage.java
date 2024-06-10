package Helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends XpathHelper{

    WebDriver driver;
    @FindBy(xpath=USERNAME_XPATH)
    private WebElement userNameField;
    @FindBy(xpath=PASSWORD_XPATH)
    private WebElement passwordField;
    @FindBy(xpath=LOGIN_BUTTON_XPATH)
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void loginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password){
        userNameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
