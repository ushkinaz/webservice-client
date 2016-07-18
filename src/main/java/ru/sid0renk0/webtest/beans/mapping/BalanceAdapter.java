package ru.sid0renk0.webtest.beans.mapping;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;
import java.math.MathContext;

public class BalanceAdapter extends XmlAdapter<ExtrasElement, BigDecimal> {
    @Override
    public BigDecimal unmarshal(ExtrasElement v) throws Exception {
        return new BigDecimal(v.getValue(), MathContext.DECIMAL32);
    }

    @Override
    public ExtrasElement marshal(BigDecimal balance) throws Exception {
        return new ExtrasElement("balance", balance.toString());
    }
}
