package edu.chalmers.phase1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;

public class SimpleBookManager implements BookManager {

	// Singleton
	private static SimpleBookManager simpleBookManager = null;

	static public SimpleBookManager getBookManager() {
		if (simpleBookManager == null) {
			simpleBookManager = new SimpleBookManager();
		}
		return simpleBookManager;
	}

	private ArrayList<Book> library = new ArrayList<Book>();
	private SharedPreferences sp;

	private SimpleBookManager() {
	}

	@SuppressWarnings("unchecked")
	public boolean loadBooks(SharedPreferences sp) {
		this.sp = sp;

		if (!sp.contains("books")) {
			library.add(new Book("Andrew Hunt", "The Pragmatic Programmer",
					200, "AHTPP", "ComputerS"));
			library.add(new Book("Marc J. Rochkind",
					"Advanced UNIX Programming", 300, "MJRAUP", "ComputerS"));
			library.add(new Book("Alain de Botton",
					"The Architecture of Happiness", 200, "AHTPP", "Archi"));
			library.add(new Book("Richard Dawkins", "The Selfish Gene", 320,
					"RDTSG", "Biology"));
			library.add(new Book("Douglas R. Hofstadter",
					"Gödel, Escher, Bach: An Eternal Golden Braid", 180,
					"RDTSG", "Maths"));
		} else {
			this.library = (ArrayList<Book>) deserializeObject(sp.getString(
					"books", ""));
		}

		return true;
	}

	@Override
	public void saveChanges() {
		SharedPreferences.Editor editor = this.sp.edit();

		String s = serializeObject(this.library);

		editor.putString("books", s);

		editor.commit();

		// toString(this.library);

	}

	@Override
	public int count() {
		return library.size();
	}

	@Override
	public Book getBook(int index) {
		if (index >= 0 && index < library.size())
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
		if (library.size() == 0)
			return 0;

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < library.size(); i++) {
			if (min > library.get(i).getPrice())
				min = library.get(i).getPrice();
		}
		return min;
	}

	@Override
	public int getMaxPrice() {
		int max = 0;
		for (int i = 0; i < library.size(); i++) {
			if (max < library.get(i).getPrice())
				max = library.get(i).getPrice();
		}
		return max;
	}

	@Override
	public float getMeanPrice() {
		return library.size() > 0 ? getTotalCost() / library.size() : 0;
	}

	@Override
	public int getTotalCost() {
		int tot = 0;
		for (int i = 0; i < library.size(); i++) {
			tot += library.get(i).getPrice();
		}
		return tot;
	}

	@Override
	public ArrayList<String> getAllBooksTitles() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < library.size(); i++) {
			list.add(library.get(i).getTitle());
		}
		return list;
	}

	@Override
	public void addBook(Book b) {
		library.add(b);
	}

	public static String serializeObject(Object o) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ObjectOutput out = new ObjectOutputStream(bos);
			out.writeObject(o);
			out.close();

			// Get the bytes of the serialized object
			byte[] buf = bos.toByteArray();

			return Base64.encodeToString(buf, Base64.DEFAULT);
		} catch (IOException ioe) {
			Log.e("serializeObject", "error", ioe);

			return null;
		}
	}

	public static Object deserializeObject(String b) {
		try {
			ObjectInputStream in = new ObjectInputStream(
					new ByteArrayInputStream(Base64.decode(b, Base64.DEFAULT)));
			Object object = in.readObject();
			in.close();

			return object;
		} catch (ClassNotFoundException cnfe) {
			Log.e("deserializeObject", "class not found error", cnfe);

			return null;
		} catch (IOException ioe) {
			Log.e("deserializeObject", "io error", ioe);

			return null;
		}
	}

}
