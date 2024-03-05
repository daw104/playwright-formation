package com.example.playwright.Tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ExecutionVideos {
    String URL = "https://www.lambdatest.com/selenium-playground/simple-form-demo";


    @Test
    public void videoTestCodeGen() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setSlowMo(2000)
            );
            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos/"))
                            .setRecordVideoSize(1280, 720)
            );

            Page page = context.newPage();
            page.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");

            Locator myAccount = page.locator("//a[contains(., 'My account')][@role='button']");
            myAccount.hover();
            page.locator("//a[contains(., 'Login')]").click();


            page.locator("//input[@id='input-email']").click();
            page.locator("//input[@id='input-email']").fill("i@i.com");
            page.locator("//input[@id='input-password']").click();
            page.locator("//input[@id='input-password']").fill("1234");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Edit your account")).click();
            page.locator("//input[@id='input-firstname']").click();
            page.locator("//input[@id='input-firstname']").fill("test");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();

            Locator successMessage = page.getByText("Success: Your account has been successfully updated.");
            assertThat(successMessage).isVisible();

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(" My account")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout")).click();
            assertThat(page).hasTitle("Account Logout");

            //cerrar video

            //cerrar
            page.close();
            context.close();
            browser.close();
            playwright.close();


        }

    }

}

