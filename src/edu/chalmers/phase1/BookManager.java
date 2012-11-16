package edu.chalmers.phase1;

import java.util.ArrayList;

public interface BookManager {
	/**
	 * 
	 * @return the number of books in the library
	 */
	public int count();
	/**
	 * 
	 * @param index: index of the book we want to retreive
	 * @return the book at index or null if there is no book at this index
	 */
	public Book getBook(int index);
	/**
	 * 
	 * @return a new instance of a book
	 */
	public Book createBook();
	/**
	 * 
	 * @return the list of all the book in the library
	 */
	public ArrayList<Book> getAllBooks();
	/**
	 * 
	 * @param book remove this book from the library
	 */
	public void removeBook(Book book);
	/**
	 * Change the index of a book
	 * @param from index of the book to move
	 * @param to index where the book have to be moved
	 */
	public void moveBook (int from, int to);
	/**
	 * 
	 * @return the minimal book price in the library
	 */
	public int getMinPrice();
	/**
	 * 
	 * @return the maximal book price in the library
	 */
	public int getMaxPrice();
	/**
	 * 
	 * @return return the average price of all the book in the library
	 */
	public float getMeanPrice();
	/**
	 * 
	 * @return return the sum of all the books prices
	 */
	public int getTotalCost();
	/**
	 * 
	 */
	public void saveChanges();
	/**
	 * 
	 * @param b add the book b to the library
	 */
	public void addBook(Book b);
	/**
	 * 
	 * @return return the list of all the titles of the boooks in the library
	 */
	ArrayList<String> getAllBooksTitles();
	
}
