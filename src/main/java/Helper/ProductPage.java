package Helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductPage extends XpathHelper{

    private WebDriver driver;
    @FindBy(xpath = PRODUCT_XPATH)
    private List<WebElement> products;

    @FindBy(xpath = PRODUCT_PRICE_XPATH)
    private List<WebElement> productPrice;

    @FindBy(xpath = ADD_TO_CART_BUTTON_XPATH)
    private List<WebElement> addToCartButton;

    @FindBy(xpath=CART_CONTAINER_XPATH)
    private WebElement cartContainer;
    @FindBy(xpath = CART_ITEMS_DATA_XPATH)
    private List<WebElement> cartItemsData;

    public ProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public int getProductCount(){
        return products.size();
    }

    public void addHighestPriceProductToCart(){
        double highestPrice = 0.0;
        int index = 0;
        for(int i=0; i<productPrice.size(); i++){
            double price =Double.parseDouble(productPrice.get(i).getText().replace("$",""));
            if(price>highestPrice){
                highestPrice = price;
                index = i;
            }
        }
        System.out.println("Highest Price: "+highestPrice+" Index: "+index);
        addToCartButton.get(index).click();
    }
    public boolean isProductInCart(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        cartContainer.click();
        wait = new WebDriverWait(driver,10);
        if(cartItemsData.size()>0) {
            return true;
        }
        else return false;
    }
}
