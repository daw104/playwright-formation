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
                .setSlowMo(2000)

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
    @Description("Esta prueba verifica la funcionalidad de login")
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


    @AfterEach
    public void tearDown() {
        page.close();
        browser.close();
        playwright.close();
    }

}
