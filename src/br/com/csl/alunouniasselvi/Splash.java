package br.com.csl.alunouniasselvi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {

	private final int TIME_SPLASH = 2500;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				Splash.this.startActivity(intent);
				Splash.this.finish();				
			}
		},TIME_SPLASH);
	}
}
