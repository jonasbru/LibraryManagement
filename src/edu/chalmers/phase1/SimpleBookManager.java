package edu.chalmers.phase1;

import java.util.ArrayList;
import java.util.List;

public class SimpleBookManager implements BookManager {

	private ArrayList<Book> library = new ArrayList<Book>();
	private static SimpleBookManager simpleBookManager = null;

	private SimpleBookManager(){
		Book b1 = new Book("Andrew Hunt", "The Pragmatic Programmer", 200, "AHTPP", "ComputerS");
		Book b2 = new Book("Marc J. Rochkind", "Advanced UNIX Programming", 300, "MJRAUP", "ComputerS");
		Book b3 = new Book("Alain de Botton", "The Architecture of Happiness", 200, "AHTPP", "Archi");
		Book b4 = new Book("Richard Dawkins", "The Selfish Gene", 320, "RDTSG", "Biology");
		Book b5 = new Book("Douglas R. Hofstadter","Gödel, Escher, Bach: An Eternal Golden Braid", 180, "RDTSG", "Maths");
		library.add(b1);
		library.add(b2);
		library.add(b3);
		library.add(b4);
		library.add(b5);
	}
	
	@Override
	public int count() {
		return library.size() ;
	}

	@Override
	public Book getBook(int index) {
		if( index >= 0 && index < library.size())
			return library.get(index);
		else
			return null;
	}

	@Override
	public Book createBook() {
		Book b = new Book("", "", 0, "", "");
		library.add(b);
		return b;
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
		if(library.size() != 0)
			return getTotalCost()/library.size();
		else
			return 0;
	}

	@Override
	public int getTotalCost() {
		int tot = 0;
		for( int i = 0; i < library.size(); i++){
			tot += library.get(i).getPrice();
		}
		return tot;
	}
	
	@Override
	public ArrayList<String> getAllBooksTitles() {
		ArrayList<String> list = new ArrayList<String>();
		for( int i = 0; i < library.size(); i++){
			list.add(library.get(i).getTitle());
		}
		return list;
	}

	@Override
	public void saveChanges() {
		// TODO Auto-generated method stub
		
	}
	
	static public BookManager getBookManager(){
		if (simpleBookManager == null) {
			simpleBookManager = new SimpleBookManager();
		}
		return simpleBookManager;
	}

	@Override
	public void addBook(Book b) {
		library.add(b);
	}
}
