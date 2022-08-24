package dev.dmchoi.eomisae.interfaces;

public interface IResult<T extends Enum<?>> {

    T getResult();

    void setResult(T t);
}