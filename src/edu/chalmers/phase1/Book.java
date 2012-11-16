package edu.chalmers.phase1;

import java.io.Serializable;

public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String author;
	private String title;
	private int price;
	private String isbn;
	private String course;
	
	/**
	 * 
	 * @param author name of the author of the book
	 * @param title title of the book
	 * @param price price of the book
	 * @param isbn Code of the book
	 * @param course course code of the book
	 */
	public Book(String author, String title, int price, String isbn,
			String course) {
		super();
		this.author = author;
		this.title = title;
		this.price = price;
		this.isbn = isbn;
		this.course = course;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
	public String toString() {
		return "Title:" + title + ",Author:" + author + ",Price:" + price + ",Course:" + course + ",ISBN:" + isbn; 
	}
}
