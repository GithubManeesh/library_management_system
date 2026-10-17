import java.util.ArrayList;

public class Library{
    private ArrayList<Book> books;
    private ArrayList<Member> members;

    public Library(){
        books= new ArrayList<>();
        members= new ArrayList<>();
    }

    public boolean addBook(Book book){
        if (findBookById(book.getId())!=null) {
            return false;
        }
        books.add(book);
        return true;
    }

    public Book findBookById(int id){
        for (Book b : books){
            if (b.getId()==id){
                return b;
            }
        }
        return null;
    }

    public ArrayList<Book> searchBooksByTitle(String keyword){
        ArrayList<Book> results= new ArrayList<>();
        for (Book b : books){
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                results.add(b);
            }
        }
        return results;
    }

    public boolean removeBook(int id){
        Book b= findBookById(id);
        if (b==null){
		return false;
	}
        if (b.isIssued()){
		return false;
	}
        books.remove(b);
        return true;
    }

    public ArrayList<Book> getAllBooks(){
        return books;
    }

    public boolean addMember(Member member){
        if (findMemberById(member.getId())!=null){
            return false;
        }
        members.add(member);
        return true;
    }

    public Member findMemberById(int id){
        for (Member m : members){
            if (m.getId()==id){
                return m;
            }
        }
        return null;
    }

    public ArrayList<Member> getAllMembers(){
        return members;
    }

    private static final int MAX_BOOKS_PER_MEMBER= 3;
    public String issueBook(int bookId, int memberId){
        Book b= findBookById(bookId);
        if (b==null){
		return "Book not found.";
	}
        if (b.isIssued()){
		return "Book is already issued.";
	}

        Member m= findMemberById(memberId);
        if (m==null){
		return "Member not found.";
	}
        if (m.getBooksIssued()>=MAX_BOOKS_PER_MEMBER){
            return "Member has reached the limit of "+MAX_BOOKS_PER_MEMBER+" books.";
	}
        b.setIssued(true);
        m.setBooksIssued(m.getBooksIssued()+1);
        return "SUCCESS";
    }

    public String returnBook(int bookId, int memberId){
        Book b= findBookById(bookId);
        if (b==null){
		return "Book not found.";
	}	
        if (!b.isIssued()){
		return "Book was not issued.";
	}
        Member m= findMemberById(memberId);
        if (m==null){
		return "Member not found.";
	}
        b.setIssued(false);
        m.setBooksIssued(Math.max(0, m.getBooksIssued()-1));
        return "SUCCESS";
    }

    public void setBooks(ArrayList<Book> books){
        this.books= books;
    }

    public void setMembers(ArrayList<Member> members){
        this.members= members;
    }
}