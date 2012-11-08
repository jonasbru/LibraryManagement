package edu.chalmers.phase1;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.actionbarsherlock.R;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import android.widget.EditText;

/*
 * Allow to create or modify book's informations
 */
public class BookEditActivity extends SherlockActivity {

	private AlertDialog.Builder alt_bld;
	private int mode = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d("DEBUG_TAG","NewEdit");
    	setTheme(R.style.Theme_Sherlock);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_edit);
        alt_bld = new AlertDialog.Builder(this);
        alt_bld.setMessage(R.string.book_edit_popup).setCancelable(true);
        mode = getIntent().getExtras().getInt("MODE");// 1 for modify a book, 2 for create a book
        
        switch (mode) {
		case 0:// we want to create a book
			
			break;
		case 1: // we want to modify an existing book
			int bookId = getIntent().getExtras().getInt("BOOK_ID");
			fillField(SimpleBookManager.getBookManager().getBook(bookId));
			break;
		default:
			break;
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.activity_book_edit,menu);// activity_book_edit, menu);
        return true;

    }
   
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("TAG","Validating ---------"+((EditText)findViewById(R.id.book_edit_title)).getText().length());
        
    	switch (item.getItemId()) {
        case R.id.validate_button:
        	if(((EditText)findViewById(R.id.book_edit_title)).getText().length() != 0){
        		String author = ((EditText)findViewById(R.id.book_edit_author)).getText().toString();
        		String title = ((EditText)findViewById(R.id.book_edit_title)).getText().toString();
        		String isbn = ((EditText)findViewById(R.id.book_edit_isbn)).getText().toString();
        		String course = ((EditText)findViewById(R.id.book_edit_course)).getText().toString();
        		Integer price;
        		//Get the price
        		if(((EditText)findViewById(R.id.book_edit_price)).getText().length() == 0){
        			price = 0;
        		}else{
        			price = Integer.parseInt(((EditText)findViewById(R.id.book_edit_price)).getText().toString());
        		}
        		
        		switch (mode) {
				case 0: // new book
					//Save the book
	        		Book newBook = new Book(author,title,price, isbn, course);
	        		SimpleBookManager.getBookManager().addBook(newBook);
					break;
				case 1: //modify existing book
					int bookId = getIntent().getExtras().getInt("BOOK_ID");
					Book b = SimpleBookManager.getBookManager().getBook(bookId);
					b.setAuthor(author);
					b.setCourse(course);
					b.setIsbn(isbn);
					b.setPrice(price);
					b.setTitle(title);
					break;

				default:
					break;
				}

        		SimpleBookManager.getBookManager().saveChanges();

        		Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
        	}else{
        		//Need to fill the title
        		AlertDialog alert = alt_bld.create();
        		alert.show();
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
    
    public void fillField(Book book){
    	((EditText)findViewById(R.id.book_edit_author)).setText(book.getAuthor());
    	((EditText)findViewById(R.id.book_edit_course)).setText(book.getCourse());
    	((EditText)findViewById(R.id.book_edit_isbn)).setText(book.getIsbn());
    	((EditText)findViewById(R.id.book_edit_title)).setText(book.getTitle());
    	((EditText)findViewById(R.id.book_edit_price)).setText(String.valueOf(book.getPrice()));

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
