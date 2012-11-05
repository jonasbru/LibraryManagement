package edu.chalmers.phase1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class SummaryActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        
        BookManager bm = new SimpleBookManager();

        TextView numberBooks = (TextView) findViewById(R.id.numberBooksText);
        int nbBooks = bm.count();
        numberBooks.setText(nbBooks + (nbBooks == 1 ? " book" : " books") + " in your library");
        
        TextView totalCost = (TextView) findViewById(R.id.TotalCostText);
        totalCost.setText(bm.getTotalCost() + " SEK");
        
        TextView mostExpensive = (TextView) findViewById(R.id.MostExpensiveText);
        mostExpensive.setText(bm.getMaxPrice() + " SEK");
        
        TextView leastExpensive = (TextView) findViewById(R.id.LeastExpensiveText);
        leastExpensive.setText(bm.getMinPrice() + " SEK");
        
        TextView averagePrice = (TextView) findViewById(R.id.AverageItemText);
        averagePrice.setText(bm.getMeanPrice() + " SEK");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_summary, menu);
        return true;
    }
}
