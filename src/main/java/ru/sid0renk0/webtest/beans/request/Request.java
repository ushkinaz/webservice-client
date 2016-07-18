package ru.sid0renk0.webtest.beans.request;


import ru.sid0renk0.webtest.beans.mapping.ExtraHolder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class Request extends ExtraHolder {
    private static final String EXTRA_LOGIN    = "login";
    private static final String EXTRA_PASSWORD = "password";

    @XmlElement(name = "request-type", required = true)
    private RequestType requestType;

    public RequestType getRequestType() {
        return requestType;
    }

    public String getLogin() {
        return getExtraValue(EXTRA_LOGIN);
    }

    public String getPassword() {
        return getExtraValue(EXTRA_PASSWORD);
    }

    public void setLogin(String login) {
        setExtraValue(EXTRA_LOGIN, login);
    }

    public void setPassword(String password) {
        setExtraValue(EXTRA_PASSWORD, password);
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }
}
