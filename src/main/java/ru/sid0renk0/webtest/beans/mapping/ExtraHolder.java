package ru.sid0renk0.webtest.beans.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "response")
public class ExtraHolder {

    private Map<String, Extra> extra = new HashMap<>();

    public ExtraHolder() {
        extra = new HashMap<>();
    }

    @XmlElement(name = "extra")
    public Collection<Extra> getExtra() {
        //Декоратор вокруг extra необходим, что бы при десериализации правильно происходило наполнение коллекции
        return new LinkedList<Extra>(extra.values()) {
            @Override
            public boolean add(Extra newExtra) {
                return extra.put(newExtra.getName(), newExtra) != null;
            }
        };
    }

    public void setExtra(Collection<Extra> extras) {
        extra = new HashMap<>();
        for (Extra ext : extras) {
            extra.put(ext.getName(), ext);
        }
    }

    protected void setExtraValue(String name, String value) {
        Extra newExtra = new Extra(name, value);
        extra.put(name, newExtra);
    }

    protected String getExtraValue(String name) {
        return extra.get(name).getValue();
    }
}
