package com.github.igorsuhorukov.webdriver.example;

import com.github.igorsuhorukov.phantomjs.PhantomJsDowloader;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.StringWriter;

public class CrawlerJavaSource {

    public static void main(String[] args) throws Exception{
        String baseUrl = args[0];
        System.setProperty("phantomjs.binary.path", PhantomJsDowloader.getPhantomJsPath());
        WebDriver driver = new PhantomJSDriver();
        try (FileWriter fileWriter = new FileWriter("projects.xml")){
            Projects projects = new Projects(baseUrl, driver);
            IOUtils.write(toXmlString(projects), fileWriter);
        } finally {
            driver.quit();
        }
    }

    private static String toXmlString(Projects categoryContainer) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Projects.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(categoryContainer, stringWriter);
        return stringWriter.toString();
    }
}
