package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;

public class GenderEntity implements IEntity<GenderEntity> {
    public static GenderEntity build() {
        return new GenderEntity();
    }

    private String value;
    private String text;

    public String getValue() {
        return value;
    }

    public GenderEntity setValue(String value) {
        this.value = value;
        return this;
    }

    public String getText() {
        return text;
    }

    public GenderEntity setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public GenderEntity clone() {
        GenderEntity genderEntity = new GenderEntity();
        genderEntity.value = this.value;
        genderEntity.text = this.text;
        return genderEntity;
    }

    @Override
    public void copyValuesOf(GenderEntity genderEntity) {
        this.value = genderEntity.value;
        this.text = genderEntity.text;
    }
}
