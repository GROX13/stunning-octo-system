package me.giorgirokhadze.interview.gdg;

public interface Converter<F, T> {
	T convert(F originalValue);
}
