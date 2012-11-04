package edu.chalmers.phase1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class TestActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		BookManager m = new SimpleBookManager();
		Log.d("TestActivity", m.getAllBooks().toString());
	}
}
