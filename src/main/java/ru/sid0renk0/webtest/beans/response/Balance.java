package ru.sid0renk0.webtest.beans.response;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class Balance extends Response {
    private static final String EXTRA_BALANCE = "balance";

    public void setBalance(BigDecimal balance) {
        setExtraValue(EXTRA_BALANCE, balance.toString());
    }

    public BigDecimal getBalance() {
        return new BigDecimal(getExtraValue(EXTRA_BALANCE));
    }
}
