package edu.chalmers.phase1;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;


import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
//import com.actionbarsherlock.sample.demos.R;

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
        testTab.setText("Test Tab");
        sumTab.setText("Sum Tab");
        sumTab.setTabListener(new MyTabListener(sumFragment));
        testTab.setTabListener(new MyTabListener(testFragment));
        
        actionBar.addTab(testTab);
        actionBar.addTab(sumTab);
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
