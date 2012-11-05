package edu.chalmers.phase1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class DetailActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);
        
        BookManager bm = new SimpleBookManager();
        Book b = bm.getBook(0);
        
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
        getMenuInflater().inflate(R.menu.book_detail, menu);
        return true;
    }
}
