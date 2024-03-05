package com.example.playwright.Tests;
import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class CaptureScreenshot {
    String URL = "https://www.lambdatest.com/selenium-playground/simple-form-demo";

    @Test
    public void takeScreenshot(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        Page page = browser.newPage();
        page.navigate(URL);

        System.out.println(page.title());

        //screenshot
        Page.ScreenshotOptions screenshotOptions = new Page.ScreenshotOptions();
        page.screenshot(screenshotOptions.setPath(Paths.get("./snapshots/screenshot.png")));


        page.close();
        playwright.close();

    }

    @Test
    public void fullPageScreenshot(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        Page page = browser.newPage();
        page.navigate(URL);

        System.out.println(page.title());

        //screenshot
        Page.ScreenshotOptions screenshotOptions = new Page.ScreenshotOptions();

        page.screenshot(screenshotOptions.setFullPage(true)
                .setPath(Paths.get("./snapshots/screenshot_fullpage.png")));

        page.close();
        playwright.close();


    }

    @Test
    public void locatorScreenshot(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch
                (new BrowserType.LaunchOptions().setHeadless(true)
                );
        Page page = browser.newPage();
        page.navigate(URL);

        System.out.println(page.title());

        //screenshot of a locator
        Page.ScreenshotOptions screenshotOptions = new Page.ScreenshotOptions();
        Locator bookDemoBtn = page.locator("//button[@role='button'][text()='Book a Demo']");
        bookDemoBtn.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("./snapshots/book_btn_demo_locator.png")));

        page.close();
        playwright.close();
    }

    @Test
    public void headerScreenshot(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch
                (new BrowserType.LaunchOptions().setHeadless(true)
                );
        Page page = browser.newPage();
        page.navigate(URL);

        System.out.println(page.title());

        //screenshot of a locator
        Page.ScreenshotOptions screenshotOptions = new Page.ScreenshotOptions();
        Locator bookDemoBtn = page.locator("//header[@id='header']");
        bookDemoBtn.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("./snapshots/header.png")));

        page.close();
        playwright.close();

    }


    @Test
    public void screenshotWithInput(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch
                (new BrowserType.LaunchOptions().setHeadless(true)
                );
        Page page = browser.newPage();
        page.navigate(URL);

        System.out.println(page.title());

        // Rellenar el input
        Locator inputMessage = page.locator("//input[@id='user-message']");
        inputMessage.fill("Este es un mensaje");
        inputMessage.scrollIntoViewIfNeeded();


        // Tomar la captura de pantalla de toda la página
        Page.ScreenshotOptions screenshotOptions = new Page.ScreenshotOptions();

        page.screenshot(screenshotOptions.setFullPage(true)
                .setPath(Paths.get("./snapshots/input.png")));

        page.close();
        playwright.close();

    }




}
