package edu.chalmers.phase1;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;


import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
//import com.actionbarsherlock.sample.demos.R;
import com.actionbarsherlock.view.MenuInflater;

public class FragmentTabs extends SherlockFragmentActivity {
    private SherlockFragment sumFragment= new SummaryActivity();
    private SherlockFragment testFragment= new Collection();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Sherlock); //Used for theme switching in samples
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragmenttab);
        
        //We take the support actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //mSelected = (TextView)findViewById(R.id.text);
         
        ActionBar.Tab sumTab = actionBar.newTab();
        ActionBar.Tab testTab = actionBar.newTab();
        testTab.setText("Collection");
        sumTab.setText("Summary");
        sumTab.setTabListener(new MyTabListener(sumFragment));
        testTab.setTabListener(new MyTabListener(testFragment));
        
        actionBar.addTab(testTab);
        actionBar.addTab(sumTab);
        
        SimpleBookManager.getBookManager().loadBooks(getPreferences(0));
    }
    
    
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.simple_addbutton, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        
        //respond to menu item selection
    	switch (item.getItemId()) {
        case R.id.add:
        	Intent myIntent = new Intent( this, Book_edit.class);
        	startActivityForResult(myIntent, 0);
        return true;
        default:
        return super.onOptionsItemSelected(item);
    	}
    }
    
    public class MyTabListener implements TabListener {

		public SherlockFragment fragment;
	
		MyTabListener(SherlockFragment fr) {
		    Log.d("MYTAG", "Creating a fragmentListener w/ " + fr);
		    this.fragment = fr;
		}

        @Override
        public void onTabSelected(Tab tab, FragmentTransaction transaction) {
        	Log.d("TAG", "" + fragment);
        	transaction.replace(R.id.tabContent, fragment);
        	
        	Log.d("TAG", "Terminate " + fragment);
        }

        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction transaction) {
        }

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

    }
    
}
