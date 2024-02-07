package com.crux.society.utils;

import java.lang.reflect.Field;

public class TestUtils {

  public static <V, T> void setField(T entity, String fieldName, V value) {
    Field field;
    try {
      field = entity.getClass().getDeclaredField(fieldName);
    } catch (NoSuchFieldException e) {
      throw new RuntimeException(e);
    }
    field.setAccessible(true);
    try {
      field.set(entity, value);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}
