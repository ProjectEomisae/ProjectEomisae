package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;

public class ProductStatusEntity implements IEntity<ProductStatusEntity> {
    public static ProductStatusEntity build() {
        return new ProductStatusEntity();
    }

    private String value;
    private String text;

    public String getValue() {
        return value;
    }

    public ProductStatusEntity setValue(String value) {
        this.value = value;
        return this;
    }

    public String getText() {
        return text;
    }

    public ProductStatusEntity setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public ProductStatusEntity clone() {
        ProductStatusEntity productStatusEntity = new ProductStatusEntity();
        productStatusEntity.value = this.value;
        productStatusEntity.text = this.text;
        return productStatusEntity;
    }

    @Override
    public void copyValuesOf(ProductStatusEntity productStatusEntity) {
        this.value = productStatusEntity.value;
        this.text = productStatusEntity.text;
    }
}
