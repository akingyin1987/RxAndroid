package rx.android.samples;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 *  
 * User: Geek_Soledad(msdx.android@qq.com) 
 * Date: 2015-07-24 
 * Time: 11:31 
 *  
 */
public class MyApp  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("MyApp--->>>>>");
        Stetho.initialize(Stetho.newInitializerBuilder(this).
                enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).build());

    }
}
