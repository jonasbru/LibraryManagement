package edu.chalmers.phase1;


import android.R.id;
import android.app.Activity;
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

/*
 * Activity wich manage 2 tabs (fragments): collection and summary
 */
public class FragmentTabsActivity extends SherlockFragmentActivity {
    private SherlockFragment sumFragment= new SummaryFragment();
    private SherlockFragment collectionFragment= new CollectionFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Sherlock);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragmenttabs);
        
        //Create the 2 tabs
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
         
        ActionBar.Tab sumTab = actionBar.newTab();
        ActionBar.Tab collectionTab = actionBar.newTab();
        collectionTab.setText("Collection");
        sumTab.setText("Summary");
        sumTab.setTabListener(new MyTabListener(sumFragment));
        collectionTab.setTabListener(new MyTabListener(collectionFragment));
        
        actionBar.addTab(collectionTab);
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
        	// Open the activity bookEditActivity in order to create a new book
        	Intent myIntent = new Intent( this, BookEditActivity.class);
        	myIntent = new Intent( this, BookEditActivity.class);
        	myIntent.putExtra("MODE", (int)0);// Mode 0: Create mode
        	startActivityForResult(myIntent, 0);
        return true;
        default:
        return super.onOptionsItemSelected(item);
    	}
    }
 
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==Activity.RESULT_OK)
		{
			((CollectionFragment)collectionFragment).update();
			((SummaryFragment)sumFragment).updateSummary(sumFragment.getView());

		}
	}
    
    /*
     * Tabs' Listner
     */
    public class MyTabListener implements TabListener {

		public SherlockFragment fragment;
	
		MyTabListener(SherlockFragment fr) {
		    Log.d("MYTAG", "Creating a fragmentListener w/ " + fr);
		    this.fragment = fr;
		}

        @Override
        public void onTabSelected(Tab tab, FragmentTransaction transaction) {
        	transaction.replace(R.id.tabContent, fragment);
        	Log.d("DEBUG_TAG", "Switch Tab to " + fragment);
        }

        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction transaction) {
        }

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			
		}

    }
    
}
