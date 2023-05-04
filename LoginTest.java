import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest extends BaseTest{

    @Test
    public void loginWithValidDataPO() {
        LoginPage loginPage = new LoginPage(driver); // Экземпляр класса основного Логин Пейдж
        loginPage.enterValueToUserName(validUser);
        loginPage.enterValueToPassword(validUser);
        loginPage.clickOnLoginButton();
        // дописать шаг проверка успешной реализации
        InventoryPage inventoryPage = new InventoryPage(driver);
        assertTrue(inventoryPage.inventoryListIsDisplayed());
    }

//    hw Inventory page + проверку написать

    @Test   // valid data
    public void loginWithValidDataPQ() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterValueToUserName(validUser);
        loginPage.enterValueToPassword(validUser);
        loginPage.clickOnLoginButton();
        // проверка успешной авторизации
        InventoryPage inventoryPage = new InventoryPage(driver);
        assertTrue(inventoryPage.inventoryListIsDisplayed());
    }

    @Test   // locked user
    public void loginWithLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterValueToUserName(lockedOutUser);
        loginPage.enterValueToPassword(lockedOutUser);
        loginPage.clickOnLoginButton();
        // assert error message text
//        loginPage.errorMessageTextIsCorrect("Epic sadface: Sorry, this user has been locked out.");
//        assertEquals("Epic sadface: Sorry, this user has been locked out.", loginPage.getErrorMessageText());
        assertTrue("Sorry, this user has been locked out.",
                loginPage.getErrorMessageText().contains("Sorry, this user has been locked out."));
    }

    @Test
    public void loginWithInvalidData() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterValueToUserName(invalidUser);
        loginPage.enterValueToPassword(invalidUser);
        loginPage.clickOnLoginButton();
        // assert error message text
        assertTrue(loginPage.getErrorMessageText().
                contains("Username and password do not match any user in this service"));
    }
    // logout

    @Test
    public void logoutSideBarLinkIsClickable() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.successLogin(validUser);
        InventoryPage inventoryPage = new InventoryPage(driver);
        assertTrue(inventoryPage.inventoryListIsDisplayed());
        inventoryPage.clickOnBurgerMenuBtn();
        SideBar sideBar = new SideBar(driver);
        sideBar.clickOnLogoutBtn();
        assertTrue(loginPage.loginButtonIsDisplayed());
    }
}
