import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver) {    // constructor
        super(driver);
    }
    @FindBy(className = "inventory_list")   // element
    private WebElement inventoryList;
    @FindBy(className = "inventory_item")    // element
    private List<WebElement> inventoryItems;

    @FindBy(className = "inventory_item_name")    // element
    private List<WebElement> inventoryNames;

    @FindBy(id = "react-burger-menu-btn")    // element
    private WebElement burgerMenuBtn;

    public boolean inventoryListIsDisplayed() { // обращаемся к elements которые прописали выше
        return inventoryList.isDisplayed();
    }

    public int getItemsQuantity(){  // Метод проверяет кол-во элементов
       return inventoryItems.size();
    }
//
//    public boolean allItemsAreDisplayed() {
//        for (WebElement item: inventoryItems){ // проверка всех
//            if (item.isDisplayed()) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public boolean all1ItemsAreDisplayed() {
//        for (WebElement item: inventoryItems){ // проверка всех
//            if (!item.isDisplayed()) {
//                return false;
//            }
//        }
//        return true;
//    }

    public boolean allItemsAreDisplayed(){  // Если ни разу не зайдем в фолс, возвратим тру, иначе - цикл остановится*(фолс).
        boolean displayed = true;
        for (WebElement item: inventoryItems){
            if(!item.isDisplayed()) {
                displayed = false;
            }
        }
        return displayed;
    }

    public boolean allItemNamesAreDisplayedNotEmpty(){
        // 1. is displayed
        boolean displayed = true;
        for (WebElement name: inventoryNames){
            if(!name.isDisplayed()) {
                displayed = false;
            }
        }
        return displayed;
    }

    public boolean allNamesAreNotEmpty(){
        // 2. not empty
        boolean notEmpty = true;
        for (WebElement name: inventoryNames){
            if(!name.getText().isEmpty()) {
                notEmpty = false;
            }
        }
        return notEmpty;
    }

//    public boolean allItemNamesAreContainsSauceLabs(){
//        // contains "Sauce Labs"
//        boolean sauceLabs = true;
//        for (WebElement nameContais: inventoryNames){
//            if(!nameContais.getText("Sauce Labs")) {
//                sauceLabs  = false;
//            }
//        }
//        return sauceLabs;
//    }
    public boolean allNamesAreStartWithSauceLabs(){
        // contains "Sauce Labs"
        boolean allContains = true;
        int index = 1;
        for (WebElement name: inventoryNames){
            if(!name.getText().startsWith("Sauce Labs")){
                allContains=false;
                System.out.println("Item with product number[" + index +"] does not start with Sauce Labs");
            }
            index++;
        }
        return allContains;
    }

    public void clickOnBurgerMenuBtn(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenuBtn));
        burgerMenuBtn.click();
    }


}
