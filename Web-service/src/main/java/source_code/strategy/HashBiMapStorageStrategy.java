package main.java.source_code.strategy;

import com.google.common.collect.HashBiMap;

import java.util.Map;

import static com.google.common.collect.HashBiMap.create;

public class HashBiMapStorageStrategy implements StorageStrategy {
    HashBiMap<Long, String> data = create();

    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        return data.inverse().get(value);
    }

    @Override
    public String getValue(Long key) {
        return data.get(key);
    }
}
