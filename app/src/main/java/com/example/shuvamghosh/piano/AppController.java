package com.example.shuvamghosh.piano;

import android.app.Application;
import android.content.res.TypedArray;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by Shuvam Ghosh on 3/5/2016.
 */
public class AppController extends Application {

    public static int[] SOUND_TONES_BASIC=new int [7];
    public static int[] SOUND_TONES_SHARP=new int [7];
    private boolean loaded=false;

    private static AppController myInstance;

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
}
