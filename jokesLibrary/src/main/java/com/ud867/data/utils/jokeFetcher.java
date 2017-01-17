package com.ud867.data.utils;

import java.util.ArrayList;
import java.util.List;

public class jokeFetcher {

    private static List<String> jokeList=populateJokes();

    public jokeFetcher(){
        super();
        populateJokes();
    }

    public static String getJoke() {
        int rand=(int)(Math.random() * (jokeList.size()-1));
        return jokeList.get(rand);
    }

    private static List<String> populateJokes(){
        if(jokeList==null){
            jokeList=new ArrayList<>();
            jokeList.add("This is totally a funny joke");
            jokeList.add("The attention span of a computer is only as long as its electrical cord.");
            jokeList.add("An unbreakable toy is useful for breaking other toys.");
            jokeList.add("Misery loves company, but company does not reciprocate.");
            jokeList.add("Real Programmers don't comment their code.  If it was hard to write, it should be hard to understand.");
            jokeList.add("Mistakes are often the stepping stones to utter failure.");
            jokeList.add("The good is when you do bad things for bad people");
        }
        return jokeList;
    }
}
