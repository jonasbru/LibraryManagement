package edu.chalmers.phase1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;



public class DetailActivity extends SherlockActivity {

	MenuItem modify;
	MenuItem delete;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setTheme(R.style.Theme_Sherlock);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);
        
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
        inflater.inflate(R.menu.book_detail, menu);
/*
    	menu.add("Modify")
            .setIcon(R.drawable.edit)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM| MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        menu.add("Delete")
            .setIcon(R.drawable.delete)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
*/    	 
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent myIntent;
    	int bookId = getIntent().getExtras().getInt("BOOK_ID");
    	BookManager bm = SimpleBookManager.getBookManager();
        //respond to menu item selection
    	switch (item.getItemId()) {
	        case R.id.modify:
	        	myIntent = new Intent( this, Book_edit.class);
	        	myIntent.putExtra("MODE", (int)1);
	        	myIntent.putExtra("BOOK_ID", (int)bookId);
	        	startActivityForResult(myIntent, 0);
	        return true;
	        case R.id.delete:
	        	Intent intent = new Intent();
	        	bm.removeBook(bm.getBook(bookId));
                setResult(RESULT_OK, intent);
	        	finish();
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
			Log.d("TAG","back to DetailActivity");
		}
	};
	
	public void update(){
		retreiveBookInfo();
	}
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.book_detail, menu);
            android.view.MenuInflater inflater =getMenuInflater();
            inflater.inflate(R.menu.simple_addbutton, menu);
        return true;
    }
    */
    
    /* A metttre dans le handler pour retourner a l'activite precedente (collection)
     * Button next = (Button) findViewById(R.id.Button02);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
     */
}
