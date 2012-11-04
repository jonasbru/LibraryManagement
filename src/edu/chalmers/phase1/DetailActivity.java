package edu.chalmers.phase1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DetailActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.book_detail, menu);
        return true;
    }
}
