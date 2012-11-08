package edu.chalmers.phase1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import android.content.SharedPreferences;
import android.util.Log;

public class SimpleBookManager implements BookManager {

	//Singleton
	private static SimpleBookManager simpleBookManager = null;	
	static public SimpleBookManager getBookManager(){
		if (simpleBookManager == null) {
			simpleBookManager = new SimpleBookManager();
		}
		return simpleBookManager;
	}

	
	private ArrayList<Book> library = new ArrayList<Book>();
	private SharedPreferences sp;

	private SimpleBookManager(){
	}
	
	@SuppressWarnings("unchecked")
	public boolean loadBooks(SharedPreferences sp){
		this.sp = sp;
		
//		if(!sp.contains("books")) {		
			library.add(new Book("Andrew Hunt", "The Pragmatic Programmer", 200, "AHTPP", "ComputerS"));
			library.add(new Book("Marc J. Rochkind", "Advanced UNIX Programming", 300, "MJRAUP", "ComputerS"));
			library.add(new Book("Alain de Botton", "The Architecture of Happiness", 200, "AHTPP", "Archi"));
			library.add(new Book("Richard Dawkins", "The Selfish Gene", 320, "RDTSG", "Biology"));
			library.add(new Book("Douglas R. Hofstadter","Gödel, Escher, Bach: An Eternal Golden Braid", 180, "RDTSG", "Maths"));			
//		} else {
//				try {
//					Log.d("CACA get", sp.getString("books", ""));
//					library = (ArrayList<Book>) fromString(sp.getString("books", ""));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			
//		}
		
		return true;
	}

	@Override
	public void saveChanges() {
		SharedPreferences.Editor editor = this.sp.edit();
		
		try {
			Log.d("CACA put", toString(this.library));
			editor.putString("books", toString(this.library));
		} catch (IOException e) {
			
		}

		editor.commit();
		
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
		return library.size() > 0 ? getTotalCost()/library.size() : 0;
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
	public void addBook(Book b) {
		library.add(b);
	}
	
	
	//Serialization
    private Object fromString(String s) throws IOException, ClassNotFoundException {
    	try { 
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(s.getBytes())); 
            Object object = in.readObject(); 
            in.close(); 
       
            return object; 
          } catch(ClassNotFoundException cnfe) { 
            Log.e("deserializeObject", "class not found error", cnfe); 
       
            return null; 
          } catch(IOException ioe) { 
            Log.e("deserializeObject", "io error", ioe); 
       
            return null; 
          } 
    }
    private String toString(Serializable o) throws IOException {
//    	ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
//        
//        try { 
//          ObjectOutput out = new ObjectOutputStream(bos); 
//          out.writeObject(o); 
//          out.close(); 
//     
//          // Get the bytes of the serialized object 
//          byte[] buf = bos.toByteArray(); 
//     
//          return new String(buf); 
//        } catch(IOException ioe) { 
//          Log.e("serializeObject", "error", ioe); 
//     
//          return null; 
//        }
    	
//    	Bla bla = new Bla(); 
    	   
    	  // assuming that both those serialize and deserialize methods are under the SerializerClass 
    	  byte[] blaBytes = serializeObject(o); 
    	  Log.d("CACA", new String(blaBytes));
    	  
    	  Serializable deserializedBla = (Serializable) deserializeObject(blaBytes); 
    	  Log.d("CACA", deserializedBla.toString());
    	  
        return "";
    }
    
    //------------------------------------------------------------
    public static byte[] serializeObject(Object o) { 
        ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
     
        try { 
          ObjectOutput out = new ObjectOutputStream(bos); 
          out.writeObject(o); 
          out.close(); 
     
          // Get the bytes of the serialized object 
          byte[] buf = bos.toByteArray(); 
     
          return buf; 
        } catch(IOException ioe) { 
          Log.e("serializeObject", "error", ioe); 
     
          return null; 
        } 
      } 
    
    public static Object deserializeObject(byte[] b) { 
        try { 
          ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(b)); 
          Object object = in.readObject(); 
          in.close(); 
     
          return object; 
        } catch(ClassNotFoundException cnfe) { 
          Log.e("deserializeObject", "class not found error", cnfe); 
     
          return null; 
        } catch(IOException ioe) { 
          Log.e("deserializeObject", "io error", ioe); 
     
          return null; 
        } 
      }
    
    
}

//class Bla implements Serializable { 
//    public String blaDate; 
//    public String blaText; 
//  }
