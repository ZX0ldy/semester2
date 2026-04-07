public class Student23 {
    String id;
    String name;
    String studentClass;
    double gpa;

    public Student23() {}

    public Student23(String id, String name, String studentClass, double gpa) {
        this.id = id;
        this.name = name;
        this.studentClass = studentClass;
        this.gpa = gpa;
    }

    public void print() {
        System.out.println("ID: " + id + ", Name: " + name +
                           ", Class: " + studentClass + ", GPA: " + gpa);
    }
}
