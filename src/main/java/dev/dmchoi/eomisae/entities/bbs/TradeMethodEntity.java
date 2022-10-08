package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;

public class TradeMethodEntity implements IEntity<TradeMethodEntity> {
    public static TradeMethodEntity build() {
        return new TradeMethodEntity();
    }

    private String value;
    private String text;

    public String getValue() {
        return value;
    }

    public TradeMethodEntity setValue(String value) {
        this.value = value;
        return this;
    }

    public String getText() {
        return text;
    }

    public TradeMethodEntity setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public TradeMethodEntity clone() {
        TradeMethodEntity tradeMethodEntity = new TradeMethodEntity();
        tradeMethodEntity.value = this.value;
        tradeMethodEntity.text = this.text;
        return tradeMethodEntity;
    }

    @Override
    public void copyValuesOf(TradeMethodEntity tradeMethodEntity) {
        this.value = tradeMethodEntity.value;
        this.text = tradeMethodEntity.text;
    }
}
