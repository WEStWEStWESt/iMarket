package com.home_projects.imarket.converters.interfaces;

public interface Converter<S, T> {
    T convert(S s, T t);
    T convert(S s);
}
