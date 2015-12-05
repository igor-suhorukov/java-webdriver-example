package com.github.igorsuhorukov.webdriver.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

class CategoryPage {
    private String title;
    private List<String> frameworkLinks;

    public CategoryPage(String url, WebDriver driver) {
        driver.get(url);
        WebElement main = driver.findElement(By.id("main"));
        List<WebElement> links = main.findElements(By.xpath("div/p/a"));
        title = main.findElement(By.tagName("h1")).getText().replace(" in Java", "");
        this.frameworkLinks = links.stream().map(tag -> tag.getAttribute("href")).collect(Collectors.toList());
    }

    public String getTitle() {
        return title;
    }

    public List<String> getFrameworkLinks() {
        return frameworkLinks;
    }
}
