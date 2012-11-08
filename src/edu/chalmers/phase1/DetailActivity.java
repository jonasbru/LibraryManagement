package edu.chalmers.phase1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

/*
 * Display details of a book
 */
public class DetailActivity extends SherlockActivity {

	MenuItem modify;
	MenuItem delete;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setTheme(R.style.Theme_Sherlock);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
		retreiveBookInfo();           
    }

    public void retreiveBookInfo(){
    	int bookId = getIntent().getExtras().getInt("BOOK_ID");
        BookManager bm = SimpleBookManager.getBookManager();
        Book b = bm.getBook(bookId);
        
        TextView title = (TextView) findViewById(R.id.TitleText);
        title.setText(b.getTitle());
        
        TextView author = (TextView) findViewById(R.id.AuthorText);
        author.setText(b.getAuthor());
        
        TextView course = (TextView) findViewById(R.id.CourseText);
        course.setText(b.getCourse());
        
        TextView price = (TextView) findViewById(R.id.PriceText);
        price.setText(b.getPrice() + " SEK");
        
        TextView isbn = (TextView) findViewById(R.id.ISBNText);
        isbn.setText(b.getIsbn());
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.activity_book_detail, menu); 	 
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent myIntent;
    	int bookId = getIntent().getExtras().getInt("BOOK_ID");
    	
        //respond to menu item selection
    	switch (item.getItemId()) {
	        case R.id.modify:
	        	myIntent = new Intent( this, BookEditActivity.class);
	        	myIntent.putExtra("MODE", (int)1); // Mode 1: edit mode
	        	myIntent.putExtra("BOOK_ID", (int)bookId);
	        	startActivityForResult(myIntent, 0);
	        return true;
	        case R.id.delete:
	        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        	builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
	        	    .setNegativeButton("No", dialogClickListener).show();
	        return true;
	        default:
	        	return super.onOptionsItemSelected(item);
    	}
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode==Activity.RESULT_OK)
		{
			update();
			Log.d("DEBUG_TAG","Back to DetailActivity");
		}
	};
	
	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
	    @Override
	    public void onClick(DialogInterface dialog, int which) {
	        switch (which){
	        case DialogInterface.BUTTON_POSITIVE:
	        	BookManager bm = SimpleBookManager.getBookManager();
	        	int bookId = getIntent().getExtras().getInt("BOOK_ID");
	        	Intent intent = new Intent();
	        	bm.removeBook(bm.getBook(bookId));
                setResult(RESULT_OK, intent);
	        	finish();
	            break;

	        case DialogInterface.BUTTON_NEGATIVE:
	            //No button clicked
	            break;
	        }
	    }
	};
	
	public void update(){
		retreiveBookInfo();
	}
}
