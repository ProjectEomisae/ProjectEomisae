package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;

public class BoardEntity implements IEntity<BoardEntity> {
    public static BoardEntity build() {
        return new BoardEntity();
    }
    private int index;
    private String id;
    private String name;

    private String urlName;
    private int listLevel;
    private int readLevel;
    private int writeLevel;
    private int commentLevel;

    public String getUrlName() {
        return urlName;
    }

    public BoardEntity setUrlName(String urlName) {
        this.urlName = urlName;
        return this;
    }

    public int getIndex() {
        return index;
    }

    public BoardEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public String getId() {
        return id;
    }

    public BoardEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BoardEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getListLevel() {
        return listLevel;
    }

    public BoardEntity setListLevel(int listLevel) {
        this.listLevel = listLevel;
        return this;
    }

    public int getReadLevel() {
        return readLevel;
    }

    public BoardEntity setReadLevel(int readLevel) {
        this.readLevel = readLevel;
        return this;
    }

    public int getWriteLevel() {
        return writeLevel;
    }

    public BoardEntity setWriteLevel(int writeLevel) {
        this.writeLevel = writeLevel;
        return this;
    }

    public int getCommentLevel() {
        return commentLevel;
    }

    public BoardEntity setCommentLevel(int commentLevel) {
        this.commentLevel = commentLevel;
        return this;
    }

    @Override
    public BoardEntity clone() {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.index = this.index;
        boardEntity.id = this.id;
        boardEntity.name = this.name;
        boardEntity.urlName = this.urlName;
        boardEntity.listLevel = this.listLevel;
        boardEntity.readLevel = this.readLevel;
        boardEntity.writeLevel = this.writeLevel;
        boardEntity.commentLevel = this.commentLevel;
        return boardEntity;
    }

    @Override
    public void copyValuesOf(BoardEntity boardEntity) {
        this.index = boardEntity.index;
        this.id = boardEntity.id;
        this.name = boardEntity.name;
        this.urlName = boardEntity.urlName;
        this.listLevel = boardEntity.listLevel;
        this.readLevel = boardEntity.readLevel;
        this.writeLevel = boardEntity.writeLevel;
        this.commentLevel = boardEntity.commentLevel;
    }
}
