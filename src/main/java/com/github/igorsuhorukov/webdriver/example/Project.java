package com.github.igorsuhorukov.webdriver.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.logging.Logger;

@XmlAccessorType(XmlAccessType.FIELD)
class Project {
    private static Logger logger = Logger.getLogger(Project.class.getName());

    private String name;
    private String description;
    private String license;
    private String link;

    public Project(String url, WebDriver driver) {
        logger.info(String.format("Fetching %s", url));
        driver.get(url);
        WebElement main = driver.findElement(By.id("main"));
        name = main.findElement(By.tagName("h3")).getText();
        description = main.findElement(By.xpath("//h3/following-sibling::table/tbody/tr/td[1]")).getText();
        link = main.findElement(By.xpath("//td[text()='HomePage']/following-sibling::*")).getText();
        license = main.findElement(By.xpath("//td[text()='License']/following-sibling::*")).getText();
    }
}
