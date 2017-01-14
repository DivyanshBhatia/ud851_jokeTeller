package com.ud867.data.utils.test;

import com.ud867.data.utils.jokeFetcher;
import org.junit.Test;

public class jokeTests {
    @Test
    public void test() {
        jokeFetcher joker = new jokeFetcher();
        assert joker.getJoke().length() != 0;
    }
}
