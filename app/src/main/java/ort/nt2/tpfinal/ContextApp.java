package ort.nt2.tpfinal;

import android.app.Application;
import android.content.Context;

public class ContextApp extends Application {
    private static ContextApp instance;

    public static ContextApp getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance;
        // or return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
