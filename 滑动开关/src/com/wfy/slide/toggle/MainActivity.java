package com.wfy.slide.toggle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.wfy.slide.toggle.ToggleButtonView.OnToggleButtonStateListener;
import com.wfy.slide.toggle.ToggleButtonView.ToggleState;

public class MainActivity extends Activity {

	private ToggleButtonView toggleButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toggleButton = (ToggleButtonView) findViewById(R.id.toggle_button);
		toggleButton.setSwitchBackgroundBitmap(R.drawable.switch_background);
		toggleButton.setSwitchSlideButtonBitmap(R.drawable.slide_button);
		toggleButton
				.setOnToggleButtonStateListener(new OnToggleButtonStateListener() {

					@Override
					public void onToggleButtonStateListener(
							ToggleState toggleState) {
						Toast.makeText(MainActivity.this, toggleState + "",
								Toast.LENGTH_SHORT).show();
					}
				});
	}
}
