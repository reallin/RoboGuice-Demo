package com.example.linxj.roboguice;

import com.google.inject.Provider;

/**
 * Created by linxj on 16/7/11.
 */

public class MyProvider implements Provider<Integer> {
    @Override
    public Integer get() {
        //return null;
        return (int)(Math.random()*1000);
    }
}
