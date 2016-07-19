package ru.sid0renk0.webtest.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class Extra {

    @XmlAttribute
    private String name;

    @XmlValue
    private String value;

    /**
     * Для сериализации
     */
    Extra() {
    }

    public Extra(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }


    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("{%s='%s'}", name, value);
    }
}
