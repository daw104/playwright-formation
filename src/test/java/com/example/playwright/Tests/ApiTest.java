package com.example.playwright.Tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ApiTest {

    String API_URL = "https://gorest.co.in/public/v2/users";
    Playwright playwright;
    APIRequest request;
    APIRequestContext requestContext;


    @BeforeEach
    public void setup(){
         playwright = Playwright.create();
         request = playwright.request(); //Para crear un HTTP request
         requestContext =  request.newContext();
    }


        @Test
        public void getUsersApiTest() throws IOException {

            APIResponse apiResponse = requestContext.get(API_URL);

            int statusCode = apiResponse.status();
            System.out.println("Response status is: " + statusCode);
            Assert.assertEquals(statusCode, 200);
            Assert.assertTrue(apiResponse.ok());

            System.out.println("-----response StatusText------");
            String statusText =  apiResponse.statusText();
            System.out.println(statusText);


            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
            String jsonFormatted = jsonResponse.toPrettyString();
            System.out.println("JSON RESPONSE: " + jsonFormatted);

            String apiUrl = apiResponse.url();
            Assert.assertEquals(apiUrl, API_URL);

            System.out.println("-----response headers------");
            Map<String, String> headers = apiResponse.headers();
            System.out.println(headers);


        }


        @Test
        public void getUserQueryParameters() throws IOException {
            APIResponse apiResponse = requestContext.get(API_URL, RequestOptions.create()
                    .setQueryParam("id",6762684 )
                    .setQueryParam("status", "inactive"));

            int statusCode = apiResponse.status();
            System.out.println("Response status is: " + statusCode);
            Assert.assertEquals(statusCode, 200);
            Assert.assertTrue(apiResponse.ok());

            System.out.println("-----response StatusText------");
            String statusText =  apiResponse.statusText();
            System.out.println(statusText);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
            String jsonFormatted = jsonResponse.toPrettyString();
            System.out.println("JSON RESPONSE: " + jsonFormatted);

        }





}
