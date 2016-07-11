package com.example.linxj.Module;

import com.example.linxj.roboguice.MyProvider;
import com.google.inject.AbstractModule;

/**
 * Created by linxj on 16/7/11.
 */

public class MyModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Integer.class).toProvider(MyProvider.class);
    }
}
