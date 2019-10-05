package me.giorgirokhadze.interview.gsg.controllers.converters;

public interface Converter<F, T> {
	T convert(F originalValue);
}
