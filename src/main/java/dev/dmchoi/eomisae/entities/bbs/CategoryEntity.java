package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;

public class CategoryEntity implements IEntity<CategoryEntity> {

    public static CategoryEntity build() {
        return new CategoryEntity();
    }
    private int index;
    private String name;
    private int boardIndex;

    public int getIndex() {
        return index;
    }

    public CategoryEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getBoardIndex() {
        return boardIndex;
    }

    public CategoryEntity setBoardIndex(int boardIndex) {
        this.boardIndex = boardIndex;
        return this;
    }

    @Override
    public CategoryEntity clone() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.index = this.index;
        categoryEntity.name = this.name;
        categoryEntity.boardIndex = this.boardIndex;
        return categoryEntity;
    }

    @Override
    public void copyValuesOf(CategoryEntity categoryEntity) {
        this.index = categoryEntity.index;
        this.name = categoryEntity.name;
        this.boardIndex = categoryEntity.boardIndex;
    }
}
