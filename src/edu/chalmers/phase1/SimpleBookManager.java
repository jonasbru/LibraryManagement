package edu.chalmers.phase1;

import java.util.ArrayList;
import java.util.List;

public class SimpleBookManager implements BookManager {

	private ArrayList<Book> library = new ArrayList<Book>();
	
	@Override
	public int count() {
		return library.size() ;
	}

	@Override
	public Book getBook(int index) {
		if( index > 0 && index < library.size())
			return library.get(index);
		else
			return null;
	}

	@Override
	public Book createBook() {
		
		return null;
	}

	@Override
	public ArrayList<Book> getAllBooks() {
		return library;
	}

	@Override
	public void removeBook(Book book) {
		library.remove(book);
	}

	@Override
	public void moveBook(int from, int to) {
		Book book1 = library.remove(from);
		library.add(library.remove(to));
		library.add(book1);
	}

	@Override
	public int getMinPrice() {
		if( library.size() == 0)
			return 0;
		
		int min = Integer.MAX_VALUE;
		for( int i = 0; i < library.size(); i++){
			if(min > library.get(i).getPrice())
				min = library.get(i).getPrice();
		}
		return min;
	}

	@Override
	public int getMaxPrice() {
		int max = 0;
		for( int i = 0; i < library.size(); i++){
			if(max < library.get(i).getPrice())
				max = library.get(i).getPrice();
		}
		return max;
	}

	@Override
	public float getMeanPrice() {
		int tot = 0;

		return 0;
	}

	@Override
	public int getTotalCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void saveChanges() {
		// TODO Auto-generated method stub
		
	}

}
