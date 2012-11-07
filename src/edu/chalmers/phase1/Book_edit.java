package edu.chalmers.phase1;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class Book_edit extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_edit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_book_edit,menu);// activity_book_edit, menu);
        return true;
    }
   
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("TAG","Validating ---------");
        //respond to menu item selection
        
    	switch (item.getItemId()) {
        case R.id.validate_button:
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
            return true;
        default:
        return super.onOptionsItemSelected((android.view.MenuItem) item);
    	}
    }
    
    public void onBackPressed() {
    	Bundle bundle = new Bundle();
    	bundle.putString("status", "OK");
    	Intent mIntent = new Intent();
    	mIntent.putExtras(bundle);
    	setResult(RESULT_OK, mIntent);
        finish();
        super.onBackPressed();
    }    

}
