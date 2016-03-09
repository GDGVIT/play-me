package com.example.shuvamghosh.piano;

import android.app.Application;
import android.content.res.TypedArray;
import android.media.AudioManager;
import android.media.SoundPool;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Shuvam Ghosh on 3/5/2016.
 */
public class AppController extends Application {

    public static int[] SOUND_TONES_BASIC=new int [7];
    public static int[] SOUND_TONES_SHARP=new int [7];
    private boolean loaded=false;

    private static AppController myInstance;

    public static final String TAG = AppController.class
            .getSimpleName();
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    @Override
    public void onCreate() {
        super.onCreate();


        myInstance=this;




        setTones();
    }

    public static AppController getInstance(){

        return myInstance;
    }

    public void setTones() {

        SoundPool soundPool;

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                loaded = true;
            }
        });

        TypedArray typedArrayBasic=getResources().obtainTypedArray(R.array.sound_tones_basic);
        TypedArray typedArraySharp=getResources().obtainTypedArray(R.array.sound_tones_sharp);

        for(int i=0;i<typedArrayBasic.length();i++) {
            SOUND_TONES_BASIC[i]=soundPool.load(this, typedArrayBasic.getResourceId(i,-1), 1);
        }

        for(int i=0;i<typedArraySharp.length();i++) {
            SOUND_TONES_SHARP[i]=soundPool.load(this, typedArraySharp.getResourceId(i,-1), 1);
        }
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }



    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
