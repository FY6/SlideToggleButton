package com.wfy.slide.toggle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ToggleButtonView extends View {

	private Bitmap switchBackgroundBitmap;
	private Bitmap switchSlideButtonBitmap;
	private int downX;
	private int currentX;

	public ToggleButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ToggleButtonView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ToggleButtonView(Context context) {
		super(context);
	}

	/**
	 * 设置开关的背景图片
	 * 
	 * @param switchBackground
	 *            背景图片
	 */
	public void setSwitchBackgroundBitmap(int switchBackground) {
		switchBackgroundBitmap = BitmapFactory.decodeResource(getResources(),
				switchBackground);
	}

	/**
	 * 设置开关滑动的按钮
	 * 
	 * @param slideButton
	 */
	public void setSwitchSlideButtonBitmap(int slideButton) {
		switchSlideButtonBitmap = BitmapFactory.decodeResource(getResources(),
				slideButton);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 保存ToggleButtonview的宽和高
		setMeasuredDimension(switchBackgroundBitmap.getWidth(),
				switchBackgroundBitmap.getHeight());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();
		canvas.drawBitmap(switchBackgroundBitmap, 0, 0, paint);
		/*
		 * int left = switchBackgroundBitmap.getWidth() -
		 * switchSlideButtonBitmap.getWidth();
		 */
		int left = currentX - switchSlideButtonBitmap.getWidth() / 2;
		if (isSliding) {
			if (left < 0) {
				left = 0;
			}
			if (left > (switchBackgroundBitmap.getWidth() - switchSlideButtonBitmap
					.getWidth())) {
				left = switchBackgroundBitmap.getWidth()
						- switchSlideButtonBitmap.getWidth();
			}
		} else {
			if (currentState == ToggleState.OPEN) {
				left = switchBackgroundBitmap.getWidth()
						- switchSlideButtonBitmap.getWidth();
			} else {
				left = 0;
			}
		}

		canvas.drawBitmap(switchSlideButtonBitmap, left, 0, paint);
	}

	boolean isSliding = false;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		currentX = (int) event.getX();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			isSliding = true;
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			int centerX = switchBackgroundBitmap.getWidth() / 2;
			if (currentX > centerX) {
				if (currentState != ToggleState.OPEN) {
					currentState = ToggleState.OPEN;
					if (listener != null) {
						listener.onToggleButtonStateListener(currentState);
					}
				}

			} else {
				if (currentState != ToggleState.CLOSE) {
					currentState = ToggleState.CLOSE;
					if (listener != null) {
						listener.onToggleButtonStateListener(currentState);
					}
				}

			}
			isSliding = false;
			break;
		}
		invalidate();
		return true;
	}

	ToggleState currentState = ToggleState.CLOSE;

	enum ToggleState {
		OPEN, CLOSE
	}

	private OnToggleButtonStateListener listener;

	interface OnToggleButtonStateListener {
		void onToggleButtonStateListener(ToggleState state);
	}

	public void setOnToggleButtonStateListener(
			OnToggleButtonStateListener listener) {
		this.listener = listener;
	}
}
