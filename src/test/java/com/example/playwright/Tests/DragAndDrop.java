package com.example.playwright.Tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.qameta.allure.Description;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DragAndDrop {

    String WEB_URL = "https://commitquality.com/practice-drag-and-drop";
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().
                setHeadless(true)
                .setSlowMo(1000)
        );
        page = browser.newPage();
    }

    //Por cada pagina que se visite, buscar para imprimir la URL de esa pagina por consola

    @Test
    @DisplayName("Prueba de Arrastrar y Soltar")
    @Description("Esta prueba verifica la funcionalidad de arrastrar y soltar en una página web.")
    @Parameters("ParameterTest1")
    public void testDragAndDrop() {
        page.navigate(WEB_URL);
        page.locator("//div[@id='small-box']").dragTo(page.locator("//div[@class='large-box '][text()='Drag the small box here.']"));
        Locator successMessage = page.getByText("Success!");
        assertThat(successMessage).isVisible();
    }

    @AfterEach
    public void tearDown() {
        page.close();
        browser.close();
        playwright.close();
    }

}
