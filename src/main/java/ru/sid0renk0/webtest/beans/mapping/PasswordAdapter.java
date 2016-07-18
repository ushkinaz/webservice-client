package ru.sid0renk0.webtest.beans.mapping;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PasswordAdapter extends XmlAdapter<ExtrasElement, String> {
    @Override
    public String unmarshal(ExtrasElement v) throws Exception {
        return v.getValue();
    }

    @Override
    public ExtrasElement marshal(String name) throws Exception {
        return new ExtrasElement("password", name);
    }
}
