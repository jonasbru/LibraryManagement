package edu.chalmers.phase1;

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
 * Display the list of all the titles and allows the selection of one of these
 */
public class CollectionFragment extends SherlockFragment {

	private BookManager m = SimpleBookManager.getBookManager();
	private ArrayAdapter<String> listAdapter; // To manage the list of titles
	private ListView collectionLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment and fill it with an adapter
        View v = inflater.inflate(R.layout.fragment_collection, container, false);
        listAdapter = new ArrayAdapter<String>(getActivity() , R.layout.simple_row, m.getAllBooksTitles());  
        
        collectionLayout = (ListView)(v.findViewById(R.id.collectionListView));
        collectionLayout.setOnItemClickListener(mMessageClickedHandler); // Attach the listener for selection
        collectionLayout.setAdapter(listAdapter);
        Log.d("DEBUG_TAG", "Collection created");
        
        return v;
    }
    
    // Create a message handling witch manage the selection of a book.
    private OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
        	Log.d("DEBUG_TAG", "Book "+ position + " selected");
            
        	// Launch the activity of details of the book
        	Intent myIntent = new Intent(getView().getContext(), DetailActivity.class);
        	myIntent.putExtra("BOOK_ID", (int)position);
            startActivityForResult(myIntent, 0);
        }
    };
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		update(); // If a book have been modified through detailActivity
	}
    
    // Refresh the title list
    public void update(){
    	listAdapter.clear();
    	for (String title : m.getAllBooksTitles()) {
    		listAdapter.add(title);
    	}
		listAdapter.notifyDataSetChanged();
    }
    
	public ArrayAdapter<String> getListAdapter() {
		return listAdapter;
	}
    
}
