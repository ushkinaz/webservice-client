package ru.sid0renk0.webtest.beans.request;

import javax.xml.bind.annotation.XmlEnumValue;


public enum RequestType {
    @XmlEnumValue("CREATE-AGT")
    CREATE_AGT,

    @XmlEnumValue("GET-BALANCE")
    GET_BALANCE,
}
