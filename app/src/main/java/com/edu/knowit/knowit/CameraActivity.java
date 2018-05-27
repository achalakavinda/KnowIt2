package com.edu.knowit.knowit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.edu.knowit.knowit.Util.Camera.ResultHolder;
import com.edu.knowit.knowit.Util.FilePaths;
import com.wonderkiln.camerakit.CameraKit;
import com.wonderkiln.camerakit.CameraKitEventCallback;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraView;

import java.io.FileOutputStream;
import java.io.IOException;

public class CameraActivity extends AppCompatActivity
{
	CameraView cameraView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_camera);
		
		cameraView = findViewById(R.id.camera);
		
		cameraView.setJpegQuality(10);
		
		findViewById(R.id.captureImage).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view) {
				
				cameraView.captureImage(new CameraKitEventCallback<CameraKitImage>()
				{
					@Override
					public void callback(CameraKitImage cameraKitImage) {
						
						imageCaptured(cameraKitImage);
						
					}
				});
				
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		cameraView.start();
	}
	
	@Override
	protected void onPause() {
		cameraView.stop();
		super.onPause();
	}
	
	public void imageCaptured(CameraKitImage image) {
		byte[] jpeg = image.getJpeg();
		
		try
		{
			
			FilePaths paths = new FilePaths();
			
			writeToFile(jpeg, paths.CAMERA + "/" + Math.random() + ".jpg");
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		long callbackTime = System.currentTimeMillis();
		
		ResultHolder.dispose();
		
		ResultHolder.setImage(jpeg);
		
		ResultHolder.setNativeCaptureSize(cameraView.getCaptureSize());
		
		ResultHolder.setTimeToCallback(callbackTime - System.currentTimeMillis());
		
		Intent intent = new Intent(this, PreviewActivity.class);
		
		this.startActivity(intent);
	}
	
	public void writeToFile(byte[] data, String fileName) throws IOException {
		FileOutputStream out = new FileOutputStream(fileName);
		
		out.write(data);
		
		out.close();
	}
}
