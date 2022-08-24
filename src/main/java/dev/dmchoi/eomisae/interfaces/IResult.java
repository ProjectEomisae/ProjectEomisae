package dev.dmchoi.eomisae.interfaces;

public interface IResult<T extends Enum<?>> {
//    String ATTRIBUTE_NAME = "result"; // public static final
//    String name(); // 이미 enum타입은 이 name 메서드를 가지고 있다.

    T getResult();

    void setResult(T t);
}