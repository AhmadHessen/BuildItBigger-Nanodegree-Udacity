package com.example.test;

import com.example.javajokerlib.Joker;

import org.junit.Test;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JokeTest
{
    @Test
    public void test()
    {
        Joker joker = new Joker();
        assert joker.tellJoke ().length() != 0;
    }
}