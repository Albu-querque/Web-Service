package main.java.source_code.strategy;

import java.io.Serializable;
import java.util.Objects;

public class Entry implements Serializable {
     Long key;
     String value;
     Entry next;
     int hash;

     public Entry(int hash, Long key, String value, Entry next) {
         this.hash = hash;
         this.key = key;
         this.value = value;
         this.next = next;
     }

     public Long getKey() { return key; }
     public String getValue() { return value; }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return entry.key == key &&
                entry.value.equals(value);
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}