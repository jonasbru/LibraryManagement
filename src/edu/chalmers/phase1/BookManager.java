package edu.chalmers.phase1;

import java.util.ArrayList;

public interface BookManager {
	public int count();
	public Book getBook(int index);
	public Book createBook();
	public ArrayList<Book> getAllBooks();
	public void removeBook(Book book);
	public void moveBook (int from, int to);
	public int getMinPrice();
	public int getMaxPrice();
	public float getMeanPrice();
	public int getTotalCost();
	public void saveChanges();
	ArrayList<String> getAllBooksTitles();
	
}
