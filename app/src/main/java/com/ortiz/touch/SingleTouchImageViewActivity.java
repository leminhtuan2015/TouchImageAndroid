package com.ortiz.touch;
import java.text.DecimalFormat;

import android.app.Activity;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.touch.R;
import com.ortiz.touch.TouchImageView.OnTouchImageViewListener;


public class SingleTouchImageViewActivity extends Activity {

	private static float zoom1 = (float) 1.0;
	private static PointF point1 = null;
	private static RectF rect1 = null;
	public static float centreX = 0.0f;
	public static float centreY = 0.0f;

	private TouchImageView image;
	private TextView scrollPositionTextView;
	private TextView zoomedRectTextView;
	private TextView currentZoomTextView;
	private DecimalFormat df;
	private Button reloadButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_touchimageview);
		//
		// DecimalFormat rounds to 2 decimal places.
		//
		df = new DecimalFormat("#.##");
		scrollPositionTextView = (TextView) findViewById(R.id.scroll_position);
		zoomedRectTextView = (TextView) findViewById(R.id.zoomed_rect);
		currentZoomTextView = (TextView) findViewById(R.id.current_zoom);
		reloadButton = (Button) findViewById(R.id.reload);
		image = (TouchImageView) findViewById(R.id.img);
		
		//
		// Set the OnTouchImageViewListener which updates edit texts
		// with zoom and scroll diagnostics.
		//
		image.setOnTouchImageViewListener(new OnTouchImageViewListener() {
			
			@Override
			public void onMove() {

				float centreX = image.getX() + image.getWidth()  / 2;
				float centreY = image.getY() + image.getHeight() / 2;

//				Log.d("MOVE CENTE : ", centreX + "  :  " + centreY);

				showInfo();
			}
		});

		reloadButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				reload(image);
			}
		});

//		image.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
//			@Override
//			public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
//				return false;
//			}
//
//			@Override
//			public boolean onDoubleTap(MotionEvent motionEvent) {
//				reload(image);
//				return false;
//			}
//
//			@Override
//			public boolean onDoubleTapEvent(MotionEvent motionEvent) {
//				return false;
//			}
//		});
	}

	private void reload(TouchImageView image){

		if(point1 != null){
//			image.setScrollPosition(point1.x, point1.y);
		}

		if(rect1 != null){
//			image.setSc

//			TouchImageView.DoubleTapZoom doubleTap = new TouchImageView.DoubleTapZoom(zoom1, 1.0, 2.0, false);
//			compatPostOnAnimation(doubleTap);
		}

//		image.setZoom(SingleTouchImageViewActivity.zoom1);

		try {
//			Thread.sleep(2000);

			float centreX = image.getX() + image.getWidth()  * (rect1.left + (Math.abs(rect1.right - rect1.left) / 2));
			float centreY = image.getY() + image.getHeight() * (rect1.top + (Math.abs(rect1.bottom - rect1.top) / 2));
//			image.reloadState(zoom1, 989.8572f, 293.20312f);
//			image.reloadState(zoom1, centreX, centreY);

//			if(point1 != null){
//				image.reloadState(zoom1, centreX * point1.x / 2, centreY * point1.y / 2);
//			} else {
//				image.reloadState(zoom1, centreX, centreY);
//			}
			image.reloadState(zoom1, centreX, centreY);
//			image.setScrollPosition(point1.x, point1.y);
		} catch (Exception e) {

		}


		showInfo();
	}

	private void showInfo(){
		PointF point = image.getScrollPosition();
		RectF rect = image.getZoomedRect();
		float currentZoom = image.getCurrentZoom();
		boolean isZoomed = image.isZoomed();

		zoom1 = currentZoom;
		point1 = point;
		rect1 = rect;

		//INFO
		scrollPositionTextView.setText("x: " + df.format(point.x) + " y: " + df.format(point.y));
		zoomedRectTextView.setText("left: " + df.format(rect.left) + " top: " + df.format(rect.top)
				+ "\nright: " + df.format(rect.right) + " bottom: " + df.format(rect.bottom));
		currentZoomTextView.setText("getCurrentZoom(): " + currentZoom + " isZoomed(): " + isZoomed);
	}
}
