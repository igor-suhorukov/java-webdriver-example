package com.github.igorsuhorukov.webdriver.example;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
class CategoryContainer {
    private String category;
    private List<Project> project;

    public CategoryContainer(String category, List<Project> project) {
        this.category = category;
        this.project = project;
    }
}
