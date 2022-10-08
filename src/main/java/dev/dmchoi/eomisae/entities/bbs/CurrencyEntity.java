package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;

public class CurrencyEntity implements IEntity<CurrencyEntity> {
    public static CurrencyEntity build() {
        return new CurrencyEntity();
    }

    private String value;
    private String text;

    public String getValue() {
        return value;
    }

    public CurrencyEntity setValue(String value) {
        this.value = value;
        return this;
    }

    public String getText() {
        return text;
    }

    public CurrencyEntity setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public CurrencyEntity clone() {
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.value = this.value;
        currencyEntity.text = this.text;
        return currencyEntity;
    }

    @Override
    public void copyValuesOf(CurrencyEntity currencyEntity) {
        this.value = currencyEntity.value;
        this.text = currencyEntity.text;
    }
}
