package com.example.playwright.Tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Description;

import java.awt.*;

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
    @DisplayName("Prueba de Arrastrar y Soltar")
    @Description("Esta prueba verifica la funcionalidad de arrastrar y soltar en una página web.")
    public void testDragAndDrop() {
        page.navigate(WEB_URL);
        System.out.println("URL actual: " + page.url()); // Imprimir la URL actual
        page.locator("//div[@id='small-box']").dragTo(page.locator("//div[@class='large-box '][text()='Drag the small box here.']"));
        Locator successMessage = page.locator("//DIV[@class='large-box inside'][text()='Success!']");
        assertThat(successMessage).isVisible();
    }

    @AfterEach
    public void tearDown() {
        page.close();
        browser.close();
        playwright.close();
    }

}
