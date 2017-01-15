package com.udacity.gradle.builditbigger;

/**
 * Created by dnbhatia on 1/15/2017.
 */
public class CustomMessageEvent {
    private String customJoke;

    public CustomMessageEvent(String customJoke){
        this.customJoke=customJoke;
    }

    public String getCustomJoke() {
        return customJoke;
    }
}
