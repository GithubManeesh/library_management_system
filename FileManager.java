import java.io.*;
import java.util.ArrayList;

public class FileManager{

    private static final String BOOKS_FILE= "books.txt";
    private static final String MEMBERS_FILE= "members.txt";

    public static void saveBooks(ArrayList<Book> books){
        try (BufferedWriter bw= new BufferedWriter(new FileWriter(BOOKS_FILE))){
            for (Book b : books){
                bw.write(b.toFileString());
                bw.newLine();
            }
        }catch (IOException e){
            System.out.println("Error saving books: "+e.getMessage());
        }
    }

    public static void saveMembers(ArrayList<Member> members){
        try (BufferedWriter bw= new BufferedWriter(new FileWriter(MEMBERS_FILE))){
            for (Member m : members){
                bw.write(m.toFileString());
                bw.newLine();
            }
        }catch (IOException e){
            System.out.println("Error saving members: "+e.getMessage());
        }
    }

    public static ArrayList<Book> loadBooks(){
        ArrayList<Book> books= new ArrayList<>();
        File file= new File(BOOKS_FILE);
       	 if (!file.exists()){
		return books;
	}
        try (BufferedReader br= new BufferedReader(new FileReader(file))){
            String line;
            while ((line= br.readLine())!=null){
               if (line.trim().isEmpty()){
		continue;
	}
                books.add(Book.fromFileString(line));
            }
        }catch (IOException e){
            System.out.println("Error loading books: "+e.getMessage());
        }
        return books;
    }

    public static ArrayList<Member> loadMembers(){
        ArrayList<Member> members= new ArrayList<>();
        File file= new File(MEMBERS_FILE);
        if (!file.exists()){
		return members;
	}
        try (BufferedReader br= new BufferedReader(new FileReader(file))){
            String line;
            while ((line= br.readLine())!=null){
               if (line.trim().isEmpty()){
			continue;
	}
                members.add(Member.fromFileString(line));
            }
        } catch (IOException e){
            System.out.println("Error loading members: "+e.getMessage());
        }
        return members;
    }
}