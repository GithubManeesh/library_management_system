public class Member{
    private int id;
    private String name;
    private int booksIssued;

    public Member(int id, String name, int booksIssued){
        this.id= id;
        this.name= name;
        this.booksIssued= booksIssued;
    }

    public int getId(){ 
	return id; 
    }
    public String getName(){ 
	return name; 
    }
    public int getBooksIssued(){
	return booksIssued; 
    }

    public void setBooksIssued(int booksIssued){
        this.booksIssued= booksIssued;
    }

    @Override
    public String toString(){
        return "Member ID: " +id+
               " | Name: " +name+
               " | Books Issued: "+booksIssued;
    }

    public String toFileString(){
        return id+ "|" +name+ "|" +booksIssued;
    }

    public static Member fromFileString(String line) {
        String[] parts= line.split("\\|");
        int id= Integer.parseInt(parts[0]);
        String name= parts[1];
        int booksIssued= Integer.parseInt(parts[2]);
        return new Member(id, name, booksIssued);
    }
}