package com.edu.knowit.knowit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageZoomActivity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = "ImageZoomActivity";

    ImageButton buttonClose;
    private Intent intent;
    private Bundle extraBundle;
    private String imageUrl;

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f,
                    Math.min(mScaleFactor, 10.0f));
            mImageView.setScaleX(mScaleFactor);
            mImageView.setScaleY(mScaleFactor);
            return true;
        }
    }

    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        setContentView(R.layout.activity_image_zoom);
        mImageView=(ImageView)findViewById(R.id.imageView);
        buttonClose = (ImageButton) findViewById(R.id.close);
        buttonClose.setOnClickListener(this);
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        if(extractExtraFromBundle()){
            Picasso.get().load(imageUrl).into(mImageView);
        }

    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        mScaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.close:
              super.onBackPressed();
              finish();
              break;
      }
    }

    private boolean extractExtraFromBundle(){

        extraBundle = intent.getExtras();
        if(extraBundle!= null){
            imageUrl = extraBundle.getString("image");
            return true;
        }else {
            Log.d(TAG,"Bundle values are not pass");
            return false;
        }
    }
}
