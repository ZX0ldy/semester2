public class Book {
    private String code;
    private String title;
    private int year;

    public Book(String code, String title, int year) {
        this.code = code;
        this.title = title;
        this.year = year;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getYear() { return year; }

    public void showBook() {
        System.out.printf("| %-6s | %-20s | %-4d |%n", code, title, year);
    }
}
