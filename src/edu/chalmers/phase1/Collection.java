package edu.chalmers.phase1;

import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragment;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
/*
 * Display the list of all the titles list
 */
public class Collection extends SherlockFragment {

	private BookManager m = new SimpleBookManager();
	private ArrayAdapter<String> listAdapter; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_collection, container, false);
        ListView collectionLayout = (ListView)(v.findViewById(R.id.collectionListView));
         
        listAdapter = new ArrayAdapter<String>(getActivity() , R.layout.simple_row, m.getAllBooksTitles());  
        collectionLayout.setAdapter(listAdapter);

        return v;
    }
}
