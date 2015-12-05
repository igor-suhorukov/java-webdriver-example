package com.github.igorsuhorukov.webdriver.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

/**
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class Projects {
    @XmlAttribute
    private String source;
    private List<CategoryContainer> category;

    public Projects() {
    }

    public Projects(String url, WebDriver driver){
        this.source = url;
        driver.get(url);
        WebElement leftmenu = driver.findElement(By.id("leftmenu"));
        List<WebElement> linkList = leftmenu.findElements(By.tagName("a"));
        this.category = linkList.stream().map(tag -> tag.getAttribute("href")).collect(Collectors.toList()).stream()
            .map(link -> {
                CategoryPage categoryPage = new CategoryPage(link, driver);
                List<Project> projects = categoryPage.getFrameworkLinks().stream()
                        .map(frameworkLink -> new Project(frameworkLink, driver)).collect(Collectors.toList());

                return new CategoryContainer(categoryPage.getTitle(), projects);
            }).collect(Collectors.toList());
    }
}
