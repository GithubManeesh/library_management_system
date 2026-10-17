import java.util.ArrayList;
import java.util.Scanner;

public class Main{

    static Scanner sc= new Scanner(System.in);
    static Library library= new Library();

    public static void main(String[] args){
        library.setBooks(FileManager.loadBooks());
        library.setMembers(FileManager.loadMembers());

        System.out.println("========================================");
        System.out.println("   LIBRARY MANAGEMENT SYSTEM");
        System.out.println("========================================");

        int choice;
        do{
            printMenu();
            choice= readInt("Enter choice: ");

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewAllBooks();
                case 3 -> searchBook();
                case 4 -> removeBook();
                case 5 -> addMember();
                case 6 -> viewAllMembers();
                case 7 -> issueBook();
                case 8 -> returnBook();
                case 9 -> {
                    FileManager.saveBooks(library.getAllBooks());
                    FileManager.saveMembers(library.getAllMembers());
                    System.out.println("Data saved. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }while (choice!=9);

        sc.close();
    }

    static void printMenu(){
        System.out.println("\n--- MENU ---");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book by Title");
        System.out.println("4. Remove Book");
        System.out.println("5. Register Member");
        System.out.println("6. View All Members");
        System.out.println("7. Issue Book");
        System.out.println("8. Return Book");
        System.out.println("9. Exit & Save");
    }

    static int readInt(String prompt){
        while(true){
            System.out.print(prompt);
            try{
                int value= Integer.parseInt(sc.nextLine().trim());
                return value;
            }catch (NumberFormatException e){
                System.out.println("Please enter a valid number.");
            }
        }
    }

    static String readString(String prompt){
        while(true){
            System.out.print(prompt);
            String value= sc.nextLine().trim();
            if(!value.isEmpty()){
		return value;
    }
            System.out.println("Input cannot be empty.");
        }
    }

    static void addBook(){
        int id= readInt("Enter book ID: ");
        String title= readString("Enter title: ");
        String author= readString("Enter author: ");

        Book book= new Book(id, title, author, false);
        if(library.addBook(book)){
            System.out.println("Book added successfully.");
        }else{
            System.out.println("A book with this ID already exists.");
        }
    }

    static void viewAllBooks(){
        ArrayList<Book> books= library.getAllBooks();
        if(books.isEmpty()){
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("\n--- All Books ---");
        for (Book b : books){
            System.out.println(b);
        }
    }

    static void searchBook(){
        String keyword= readString("Enter title keyword: ");
        ArrayList<Book> results= library.searchBooksByTitle(keyword);
        if(results.isEmpty()){
            System.out.println("No books matched.");
        }else{
            System.out.println("\n--- Search Results ---");
            for (Book b : results) {
                System.out.println(b);
            }
        }
    }

    static void removeBook(){
        int id= readInt("Enter book ID to remove: ");
        if(library.removeBook(id)){
            System.out.println("Book removed.");
        }else{
            System.out.println("Cannot remove (not found or currently issued).");
        }
    }

    static void addMember(){
        int id= readInt("Enter member ID: ");
        String name= readString("Enter member name: ");

        Member member= new Member(id, name, 0);
        if(library.addMember(member)) {
            System.out.println("Member registered.");
        }else{
            System.out.println("A member with this ID already exists.");
        }
    }

    static void viewAllMembers(){
        ArrayList<Member> members= library.getAllMembers();
        if(members.isEmpty()){
            System.out.println("No members registered.");
            return;
        }
        System.out.println("\n--- All Members ---");
        for (Member m : members){
            System.out.println(m);
        }
    }

    static void issueBook(){
        int bookId= readInt("Enter book ID: ");
        int memberId= readInt("Enter member ID: ");
        String result= library.issueBook(bookId, memberId);
        if(result.equals("SUCCESS")){
            System.out.println("Book issued successfully.");
        }else{
            System.out.println("Failed: "+result);
        }
    }

    static void returnBook(){
        int bookId= readInt("Enter book ID: ");
        int memberId= readInt("Enter member ID: ");
        String result= library.returnBook(bookId, memberId);
        if(result.equals("SUCCESS")){
            System.out.println("Book returned successfully.");
        }else{
            System.out.println("Failed: "+result);
        }
    }
}