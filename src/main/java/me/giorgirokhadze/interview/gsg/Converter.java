package me.giorgirokhadze.interview.gsg;

public interface Converter<F, T> {
	T convert(F originalValue);
}
