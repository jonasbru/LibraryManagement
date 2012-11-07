package edu.chalmers.phase1;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
