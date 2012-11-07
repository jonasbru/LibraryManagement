package edu.chalmers.phase1;

import com.actionbarsherlock.app.SherlockFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SummaryActivity extends SherlockFragment {
	BookManager bm = new SimpleBookManager();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    public void updateSummary(View v){
    	TextView numberBooks = (TextView) v.findViewById(R.id.numberBooksText);
        int nbBooks = bm.count();
        numberBooks.setText(nbBooks + (nbBooks == 1 ? " book" : " books") + " in your library");
        
        TextView totalCost = (TextView) v.findViewById(R.id.TotalCostText);
        totalCost.setText(bm.getTotalCost() + " SEK");
        
        TextView mostExpensive = (TextView) v.findViewById(R.id.MostExpensiveText);
        mostExpensive.setText(bm.getMaxPrice() + " SEK");
        
        TextView leastExpensive = (TextView) v.findViewById(R.id.LeastExpensiveText);
        leastExpensive.setText(bm.getMinPrice() + " SEK");
        
        TextView averagePrice = (TextView) v.findViewById(R.id.AverageItemText);
        averagePrice.setText(bm.getMeanPrice() + " SEK");
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	View v = inflater.inflate(R.layout.activity_summary, container, false);
    	updateSummary(v);
        return v;
    }

}
