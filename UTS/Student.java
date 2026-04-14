public class Student {
    private String id;
    private String name;
    private String studyProgram;

    public Student(String id, String name, String studyProgram) {
        this.id = id;
        this.name = name;
        this.studyProgram = studyProgram;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getStudyProgram() { return studyProgram; }

    public void showStudent() {
        System.out.printf("| %-6s | %-10s | %-30s |%n", id, name, studyProgram);
    }
}
