package ru.sid0renk0.webtest.beans.response;

import ru.sid0renk0.webtest.beans.mapping.ExtraHolder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "response")
public class Response extends ExtraHolder {
    private Integer resultCode;

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    @XmlElement(name = "result-code")
    public Integer getResultCode() {
        return resultCode;
    }
}

