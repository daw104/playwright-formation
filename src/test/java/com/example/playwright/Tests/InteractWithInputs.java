package com.example.playwright.Tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InteractWithInputs {

    @Test
    public void testWithInputs(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        String message = "Testing inputs";
        page.locator("//input[@id='user-message']").type(message); // simula la entrada de texto caracter por caracter, No borra el contenido existente en el campo de entrada antes de escribir el nuevo texto.
        page.locator("//button[@id='showInput']").click();
       // String textContent = page.locator("//p[@id='message']").textContent();//leemos el contenido del texto
        String placeHolderValue = page.locator("//input[@id='sum1']").getAttribute("placeholder");
        System.out.println(placeHolderValue);
        PlaywrightAssertions.assertThat(page.locator("//p[@id='message']")).hasText(message);
        playwright.close();
    }

    @Test
    public void testInputWithFill(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/generate-file-to-download-demo");
        String dataToEnter = "Testing with inputs using fill";
        page.locator("//textarea[@id='textbox']").fill(dataToEnter);// borra cualquier texto existente en el campo de entrada antes de ingresar el nuevo texto proporcionado

        playwright.close();
    }

    @Test
    public void testGetValuesAndClearText(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://letcode.in/edit");
        String textBoxValue = page.locator("//input[@id='getMe']").inputValue(); //obtenemos el valor del input
        System.out.println(textBoxValue);
        page.locator("//input[@id='clearMe']").clear();
        playwright.close();
    }


    @Test
    public void testHandleCheckBox(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/checkbox-demo");
        Locator isAgeSelected = page.locator("//input[@id='isAgeSelected']");
        assertThat(isAgeSelected).not().isChecked(); //comprobamos que NO esta seleccionada por default
        isAgeSelected.check(); //Ahora SI seleccionamos  este checkbox
        assertThat(isAgeSelected).isChecked(); //Comprobamos que se selecciona correctamente
        playwright.close();
    }








}
