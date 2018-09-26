package com.example.fujitsu.cameratest;
import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Fujitsu on 8/28/2016.
 */
public class campreview   extends SurfaceView implements SurfaceHolder.Callback{
    public campreview(Context context,Camera cam) {
        super(context);

        mCam=cam;

        mHolder=getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    private SurfaceHolder mHolder;
    private Camera mCam;
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // TODO Auto-generated method stub
        try{
            mCam.setPreviewDisplay(surfaceHolder);
            mCam.startPreview();
        }catch(IOException e){

        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
