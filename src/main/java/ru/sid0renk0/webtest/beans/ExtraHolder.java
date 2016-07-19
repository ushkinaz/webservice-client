package ru.sid0renk0.webtest.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Базовый класс, хранит список дополнительных пропертей
 * Наследники класса должны реализовывать set/get[PropertyName], делигируя фактическую работы в этот класс, в set/getExtraValue
 * Таким образом получается больше контроля над типами пропертей и исключаются случаи добавления "левых" пропертей
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "response")
public abstract class ExtraHolder {

    /**
     * Для хранения выбран Map, что бы упростить работу с классом и инкапсулировать весь ужас маппинга в XML
     * Альтернативные варианты либо сложнее в реализации ({@code @XmlJavaTypeAdapter}),
     * либо ухудшают производительность на пустом месте при добавлении полей ({@code List<Extra>})
     *
     */
    private Map<String, Extra> extra = new HashMap<>();

    public ExtraHolder() {
        extra = new HashMap<>();
    }

    @XmlElement(name = "extra")
    public Collection<Extra> getExtra() {
        //Декоратор вокруг extra необходим, что бы при десериализации правильно происходило наполнение коллекции
        //Остальные мутаторы (addAll etc) сознательно проигнорированы

        return new LinkedList<Extra>(extra.values()) {
            @Override
            public boolean add(Extra newExtra) {
                return extra.put(newExtra.getName(), newExtra) != null;
            }
        };
    }

    protected void setExtraValue(String name, String value) {
        Extra newExtra = new Extra(name, value);
        extra.put(name, newExtra);
    }

    protected String getExtraValue(String name) {
        return extra.get(name).getValue();
    }
}
