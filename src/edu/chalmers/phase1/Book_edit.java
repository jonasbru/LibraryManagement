package edu.chalmers.phase1;


import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class Book_edit extends Activity {

	private AlertDialog.Builder alt_bld;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_edit);
        alt_bld = new AlertDialog.Builder(this);
        alt_bld.setMessage(R.string.book_edit_popup).setCancelable(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_book_edit,menu);// activity_book_edit, menu);
        return true;

    }
   
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("TAG","Validating ---------"+((EditText)findViewById(R.id.book_edit_title)).getText().length());
        
    	switch (item.getItemId()) {
        case R.id.validate_button:
        	if(((EditText)findViewById(R.id.book_edit_title)).getText().length() != 0){
        		//Bundle bundle = new Bundle();
        		Intent intent = new Intent();
        		//bundle.putString("update", "OK");
            	//Intent mIntent = new Intent();
            	//mIntent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                Log.d("TAG","V1");
                finish();
        	}else{
        		AlertDialog alert = alt_bld.create();
        		alert.show();
        		Log.d("TAG","V2");
        	}
            
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
    
    /*
     * Popup
     * 
     */
    /*public class dialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.book_edit_popup);
                   /*.setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                           // FIRE ZE MISSILES!
                       }
                   })
                   .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                           // User cancelled the dialog
                       }
                   });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }*/
}
