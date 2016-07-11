# RoboGuice-Demo
RoboGuice-Demo简单Demo讲解
## RoboGuice基本用法：
### InjectView注入View
以最简单的例子，在布局文件有一个TextView中：
```
<android.support.constraint.ConstraintLayout android:id="@+id/activity_main"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.linxj.roboguice.MainActivity">

    <TextView
        android:id="@+id/name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="@+id/activity_main"
        app:layout_constraintLeft_toLeftOf="@+id/activity_main"
        app:layout_constraintRight_toRightOf="@+id/activity_main"
        app:layout_constraintTop_toTopOf="@+id/activity_main" />

</android.support.constraint.ConstraintLayout>
```

要在Activity，看Activity代码：
```
@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {
    @InjectView(R.id.name)
    TextView name;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        name.setText(authorName);

    }
    }
```
需要三步：
* 将继承Activity改成RoboActivity。
* 用@InjectView注解来显示注入的组件。
* 将setContentView改成
```
@ContentView(R.layout.activity_main)
```
### 注入资源文件
如注入string,drawable等资源文件，用如下代码：
```
@InjectResource(R.string.autor_name)
            String authorName;
```
### 注入系统服务
其实就是取代了getSystemService。
```
@Inject
private PowerManager pm;
```
### 注入自定义的类：
很简单，比如注入如下project类：
```
public class Project {
    public Project(){
        Log.e("Project enstruct","Project enstruct");
    }
}
```
那么只要在activity中加入如下代码就能注入成功：
```
 @Inject
    Project project;
```
### 运行时动态注入
要在运行时才注入，用的的guice的provide.首先需要新建一个Provider：
```
public class MyProvider implements Provider<Integer> {
    @Override
    public Integer get() {
        //return null;
        return (int)(Math.random()*1000);
    }
}
```
还需要创建一个Module，如下：
```
public class MyModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Integer.class).toProvider(MyProvider.class);
    }
}
```
就是动态的创建一个随机整数，如何使用呢，如下：
```
  @Inject
    Integer integer1;
    @Inject
    Integer integer2;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        name.setText(integer1+" "+integer2);


    }
```
### OnCreate,OnDestory等生命周期方法
roboGuice可以不写OnCreate,OnDestory等生命周期方法，用如下代码代替：
```
 public void inital(@Observes OnCreateEvent e){
        //Syst
        name.setText(authorName);
        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show();
    }
    public void end(@Observes OnDestroyEvent e){
        Toast.makeText(this,"onDestory",Toast.LENGTH_SHORT).show();
    }
```

### DEMO效果图：
![](https://github.com/reallin/RoboGuice-Demo/blob/master/roboguice.png)


