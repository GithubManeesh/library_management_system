public class Book{
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author, boolean isIssued){
        this.id= id;
        this.title= title;
        this.author= author;
        this.isIssued= isIssued;
    }

    public int getId(){
	return id;
    }
    public String getTitle(){
	return title;
    }
    public String getAuthor(){	
	return author; 
    }
    public boolean isIssued(){ 
	return isIssued; 
    }

    public void setIssued(boolean issued){ 
	this.isIssued= issued; 
	}

    @Override
    public String toString(){
        return "ID: " +id+
               " | Title: " + title +
               " | Author: " + author +
               " | Status: " + (isIssued ? "Issued" : "Available");
    }

    public String toFileString(){
        return id + "|" +title+ "|" +author+ "|" +isIssued;
    }

    public static Book fromFileString(String line){
        String[] parts= line.split("\\|");
        int id= Integer.parseInt(parts[0]);
        String title= parts[1];
        String author= parts[2];
        boolean issued= Boolean.parseBoolean(parts[3]);
        return new Book(id, title, author, issued);
    }
}