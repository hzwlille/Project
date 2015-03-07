package com.yaps.petstore.cache;

public interface ServiceCache {

	public abstract <T> T get(String key);

	public abstract void set(String key, Object o);

}