package com.example.playwright.Tests;
import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class HandleDropdowns {
    String URL = "https://www.lambdatest.com/selenium-playground/select-dropdown-demo";

    @Test
    public void testSelectDropdownByValue(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        Page page = browser.newPage();
        page.navigate(URL);

        Locator dayLocator = page.locator("//SELECT[@id='select-demo']");
        dayLocator.click(); //hacemos click para que se abra el dropdown
        page.waitForTimeout(1000); //esperamos para asegurarnos de que se abra
        String selectDay = "Monday";
        dayLocator.selectOption(selectDay);
        assertThat(page.locator("//p[@class='selected-value text-size-14']")).containsText(selectDay);

        playwright.close();
    }

    @Test
    public void testSelectDropdownByLabel(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        Page page = browser.newPage();
        page.navigate(URL);

        Locator dayLocator = page.locator("//SELECT[@id='select-demo']");
        dayLocator.click(); //hacemos click para que se abra el dropdown
        page.waitForTimeout(1000); //esperamos para asegurarnos de que se abra


        String selectDay = "Sunday";
        dayLocator.selectOption(new SelectOption().setValue(selectDay));
        assertThat(page.locator("//p[@class='selected-value text-size-14']")).containsText(selectDay);
        playwright.close();
    }

    @Test
    public void testSelectDropdownByIndex(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        Page page = browser.newPage();
        page.navigate(URL);

        Locator dayLocator = page.locator("//SELECT[@id='select-demo']");
        dayLocator.click(); //hacemos click para que se abra el dropdown
        page.waitForTimeout(1000); //esperamos para asegurarnos de que se abra


        String selectDay = "Saturday";
        dayLocator.selectOption(new SelectOption().setIndex(7));
        assertThat(page.locator("//p[@class='selected-value text-size-14']")).containsText(selectDay);
        playwright.close();
    }

    @Test
    public void testMultipleValues(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        Page page = browser.newPage();
        page.navigate(URL);

        Locator statesToSelect = page.locator("//SELECT[@id='multi-select']");
        statesToSelect.click(); //hacemos click para que se abra el dropdown
        page.waitForTimeout(1000); //esperamos para asegurarnos de que se abra

        String[] states = {"California", "Florida", "Texas"};
        for (String state : states) {
            statesToSelect.selectOption(state);
        }
        // Verificamos cuántas opciones están seleccionadas
        System.out.println(statesToSelect.evaluate("el => el.selectedOptions.length"));
        // Cerramos el navegador
        playwright.close();


    }



}
