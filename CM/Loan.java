public class Loan {
    private Student std;
    private Book book;
    private int loanDuration;
    private int loanLimit;
    private int fine;
    private int late;

    public Loan(Student std, Book book, int loanDuration) {
        this.std = std;
        this.book = book;
        this.loanDuration = loanDuration;
        this.loanLimit = 5;
        this.fine = 0;
        this.late = 0;
        calculateFine();
    }

    public void calculateFine() {
        if (loanDuration > loanLimit) {
            late = loanDuration - loanLimit;
            fine = late * 2000;
        } else {
            late = 0;
            fine = 0;
        }
    }

    public Student getStudent() { return std; }
    public Book getBook() { return book; }
    public int getLoanDuration() { return loanDuration; }
    public int getLoanLimit() { return loanLimit; }
    public int getFine() { return fine; }
    public int getLate() { return late; }

    public void showLoan() {
        System.out.printf("| %-6s | %-10s | %-20s | %-14d | %-10d | %-12s |%n",
                std.getId(),
                std.getName(),
                book.getTitle(),
                loanDuration,
                late,
                "Rp " + String.format("%,d", fine).replace(',', '.'));
    }
}
