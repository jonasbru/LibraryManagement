package edu.chalmers.phase1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;

/*
 * Display the list of all the titles list
 */
public class Collection extends SherlockFragment {

	static final int NEW_BOOK = 1;
	private BookManager m = SimpleBookManager.getBookManager();
	private ArrayAdapter<String> listAdapter;
	
	public ArrayAdapter<String> getListAdapter() {
		return listAdapter;
	}

	ListView collectionLayout;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_collection, container, false);
        collectionLayout = (ListView)(v.findViewById(R.id.collectionListView));
        collectionLayout.setOnItemClickListener(mMessageClickedHandler);
        listAdapter = new ArrayAdapter<String>(getActivity() , R.layout.simple_row, m.getAllBooksTitles());  
        collectionLayout.setAdapter(listAdapter);
        Log.d("TAG", "Collection created");
        
        return v;
    }
    
    // Create a message handling object as an anonymous class.
    private OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
        	Log.d("TAG", "Book "+ position + " selected");
            
        	Intent myIntent = new Intent(getView().getContext(), DetailActivity.class);
        	myIntent.putExtra("BOOK_ID", (int)position);
        	myIntent.putExtra("MODE", (int)0);
            startActivityForResult(myIntent, 0);
        }
    };
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode==Activity.RESULT_OK)
		{
			update();
			Log.d("TAG","back to collection");
		}else if(resultCode==Activity.RESULT_CANCELED){
			Log.d("TAG","CANCEL");
			update();
		}
	}
    
    public void update(){
    	getListAdapter().clear();
    	for (String title : m.getAllBooksTitles()) {
    		listAdapter.add(title);
    	}
		listAdapter.notifyDataSetChanged();
    }
    
}
