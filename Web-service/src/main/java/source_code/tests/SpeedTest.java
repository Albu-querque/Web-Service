package main.java.source_code.tests;

import org.junit.Assert;
import org.junit.Test;
import main.java.source_code.Helper;
import main.java.source_code.Shortener;
import main.java.source_code.strategy.HashBiMapStorageStrategy;
import main.java.source_code.strategy.HashMapStorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date time = new Date();
        for(String string : strings)
            ids.add(shortener.getId(string));
        return new Date().getTime() - time.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date time = new Date();
        for(Long id : ids)
            strings.add(shortener.getString(id));
        return new Date().getTime() - time.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origString = new HashSet<>();
        for(int i = 0; i < 10000; ++i)
            origString.add(Helper.generateRandomString());

        Set<Long> idsSh1 = new HashSet<>();
        Set<Long> idsSh2 = new HashSet<>();
        Long timeSh1 = getTimeForGettingIds(shortener1, origString, idsSh1);
        Long timeSh2 = getTimeForGettingIds(shortener2, origString, idsSh2);
        Assert.assertTrue(timeSh1 > timeSh2);

        Set<String> stringsSh1 = new HashSet<>();
        Set<String> stringsSh2 = new HashSet<>();
        Long stringsTimeSh1 = getTimeForGettingStrings(shortener1, idsSh1, stringsSh1);
        Long stringsTimeSh2 = getTimeForGettingStrings(shortener2, idsSh2, stringsSh2);
        Assert.assertEquals(timeSh1, timeSh2, 30);


    }
}
