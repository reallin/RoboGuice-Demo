package com.example.linxj.roboguice;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linxj.model.Project;
import com.google.inject.Inject;

import okhttp3.OkHttpClient;
import roboguice.activity.RoboActivity;
import roboguice.context.event.OnCreateEvent;
import roboguice.context.event.OnDestroyEvent;
import roboguice.event.Observes;
import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {
    @InjectView(R.id.name)
    TextView name;
    @InjectResource(R.string.autor_name)
            String authorName;
    OkHttpClient client = new OkHttpClient();
    @Inject
    Project project;
    @Inject
    Integer integer1;
    @Inject
    Integer integer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        name.setText(authorName+" "+integer1+" "+integer2);


    }
    public void inital(@Observes OnCreateEvent e){
        //Syst
        name.setText(authorName);
        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show();
    }
    public void end(@Observes OnDestroyEvent e){
        Toast.makeText(this,"onDestory",Toast.LENGTH_SHORT).show();
    }

}
