package com.example.playwright.Tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class LaunchBrowser {

    @Test
    public void testLogin() throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new LaunchOptions().setHeadless(false)
                        .setSlowMo(1000)
            ); //Cuando esta a false, podemos ver el interfaz grafico
        Page page = browser.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/");
        Locator myAccount = page.locator("//a[contains(., 'My account')][@role='button']");
        myAccount.hover();
        page.locator("//a[contains(., 'Login')]").click();
        assertThat(page).hasTitle("Account Login");
        page.getByPlaceholder("E-Mail Address").type("i@i.com");
        page.getByPlaceholder("Password").type("1234");
        page.locator("//input[@value='Login']").click();
        assertThat(page).hasTitle("My Account");
        Thread.sleep(3000);
        page.close();
        browser.close();
        playwright.close();
    }


}
