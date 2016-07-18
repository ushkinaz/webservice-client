package ru.sid0renk0.webtest.beans.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class ExtrasElement {

    @XmlAttribute
    private String name;

    @XmlValue
    private String value;

    public ExtrasElement() {
    }

    public ExtrasElement(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }


    public String getValue() {
        return value;
    }
}
