package main.java.source_code.strategy;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;

public class FileBucket {
    Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile("t" + new BigInteger(130, new SecureRandom()).toString(10),".txt");
            Files.deleteIfExists(path);
            path.toFile().deleteOnExit();
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getFileSize() {
        long size = 0;
        try {
            size = Files.size(path);
        } catch(IOException e) {e.printStackTrace();}
           return size;
    }

    public void putEntry(Entry entry) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path));
            while (entry != null) {
                oos.writeObject(entry);
                entry = entry.next;
            }
        } catch(IOException e) { e.printStackTrace(); }
    }

    public Entry getEntry() {
        if(getFileSize() == 0)
            return null;
        try {
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path));
            Entry entry = (Entry) ois.readObject();
            return entry;
        }catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
        return null;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch(IOException e) { e.printStackTrace(); }
    }
}
