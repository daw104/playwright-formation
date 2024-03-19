package com.example.playwright.Tests;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;

import java.awt.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AdminOrangePanel {

    String WEB_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().
                setHeadless(false)
                .setSlowMo(1000)

        );
        page = browser.newPage();
        // Obtener el tamaño de la pantalla
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();
        page.setViewportSize(screenWidth, screenHeight);//para ajustar pantalla completo
    }


    @Test
    @DisplayName("Prueba de Login")
    @Description("Esta prueba verifica la funcionalidad de login")
    public void login() {
        page.navigate(WEB_URL);
        System.out.println("URL actual: " + page.url());
        String username = "Admin";
        String password = "admin123";
        page.locator("//INPUT[@class='oxd-input oxd-input--focus']").fill(username);
        page.getByPlaceholder("Password").fill(password);
        page.locator("//BUTTON[@type='submit']").click();
        System.out.println("URL actual: " + page.url());
        assertThat(page).hasURL("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }


    @Test
    @DisplayName("Prueba de Login")
    @Description("Esta prueba verifica la funcionalidad de login y de cambiar la contraseña del usuario")
    public void shouldLoginAndChangeCurrentPassword() {
        page.navigate(WEB_URL);
        System.out.println("URL pagina login: " + page.url());
        String username = "Admin";
        String password = "admin123";
        page.locator("//INPUT[@class='oxd-input oxd-input--focus']").fill(username);
        page.getByPlaceholder("Password").fill(password);
        page.locator("//BUTTON[@type='submit']").click();
        System.out.println("URL pagina home: " + page.url());
        page.locator("//I[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']").click();
        page.locator("//A[@href='/web/index.php/pim/updatePassword'][text()='Change Password']").click();
        System.out.println("URL pagina cambiar contraseña : " + page.url());
        String newPassword = "12345678a";
        String confirmPassword = "12345678a";
        page.locator("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/input[1]").fill(password);
        page.locator("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input[1]").fill(newPassword);
        page.locator("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[1]/div[2]/input[1]").fill(confirmPassword);
        page.locator("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/button[2]").click();
        Locator changePasswordSuccess = page.locator("//div[@id='oxd-toaster_1']");
        assertThat(changePasswordSuccess).isVisible();
    }


    @Test
    @DisplayName("Prueba de Login")
    @Description("Esta prueba verifica la funcionalidad de login y de eliminar la 3 usuarios")
    public void shouldLoginAndDeleteThreeUsers() throws InterruptedException {
        page.navigate(WEB_URL);
        System.out.println("URL pagina login: " + page.url());
        String username = "Admin";
        String password = "admin123";
        page.locator("//INPUT[@class='oxd-input oxd-input--focus']").fill(username);
        page.getByPlaceholder("Password").fill(password);
        page.locator("//BUTTON[@type='submit']").click();
        System.out.println("URL pagina home: " + page.url());
        page.locator("//body/div[@id='app']/div[1]/div[1]/aside[1]/nav[1]/div[2]/ul[1]/li[1]/a[1]").click();
        page.locator("(//I[@class='oxd-icon bi-check oxd-checkbox-input-icon'])[3]").click(); //marcamos 1 usuario
        page.locator("(//I[@class='oxd-icon bi-check oxd-checkbox-input-icon'])[4]").click(); //marcamos 2 usuario
        Thread.sleep(2000);
        Locator areThreeUsersSelected = page.locator("//SPAN[@class='oxd-text oxd-text--span'][text()='(2) Records Selected']");
        assertThat(areThreeUsersSelected).isVisible();
        Thread.sleep(3000);
        Locator deleteUserButton= page.locator("(//BUTTON[@type='button'])[6]");
        assertThat(deleteUserButton).isVisible();
        deleteUserButton.click();
        Thread.sleep(1000);
        page.locator("(//BUTTON[@type='button'])[18]").click();
        Thread.sleep(2000);
        Locator changePasswordSuccess = page.locator("//div[@id='oxd-toaster_1']");
        assertThat(changePasswordSuccess).isVisible();

    }

    @AfterEach
    public void tearDown() {
        page.close();
        browser.close();
        playwright.close();
    }

}
