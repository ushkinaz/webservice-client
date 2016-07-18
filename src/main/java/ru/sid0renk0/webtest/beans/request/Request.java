package ru.sid0renk0.webtest.beans.request;


import ru.sid0renk0.webtest.beans.mapping.LoginAdapter;
import ru.sid0renk0.webtest.beans.mapping.PasswordAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class Request {
    @XmlElement(name = "request-type", required = true)
    private RequestType requestType;

    @XmlJavaTypeAdapter(LoginAdapter.class)
    @XmlElement(name = "extra", required = true)
    private String login;

    @XmlJavaTypeAdapter(PasswordAdapter.class)
    @XmlElement(name = "extra", required = true)
    private String password;

    public RequestType getRequestType() {
        return requestType;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }
}
