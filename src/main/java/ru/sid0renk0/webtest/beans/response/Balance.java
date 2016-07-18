package ru.sid0renk0.webtest.beans.response;


import ru.sid0renk0.webtest.beans.mapping.BalanceAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class Balance extends Response {

    @XmlJavaTypeAdapter(BalanceAdapter.class)
    @XmlElement(name = "extra")
    private BigDecimal balance;

    @XmlJavaTypeAdapter(BalanceAdapter.class)
    @XmlElement(name = "extra")
    private BigDecimal balance1 = BigDecimal.ONE;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
