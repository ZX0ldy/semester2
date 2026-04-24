public class ExcuseLetter01 {
    String id;
    String name;
    String className;
    char typeOfExcuse; // 'S' = Sick, 'I' = Other personal reasons
    int duration;

    ExcuseLetter01() {
    }

    ExcuseLetter01(String id, String name, String className, char type, int duration) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.typeOfExcuse = type;
        this.duration = duration;
    }

    String getExcuseType() {
        if (typeOfExcuse == 'S' || typeOfExcuse == 's') {
            return "Sick";
        } else if (typeOfExcuse == 'I' || typeOfExcuse == 'i') {
            return "Other Personal Reasons";
        } else {
            return "Unknown";
        }
    }

    void printInfo() {
        System.out.println("ID       : " + id);
        System.out.println("Name     : " + name);
        System.out.println("Class    : " + className);
        System.out.println("Type     : " + getExcuseType());
        System.out.println("Duration : " + duration + " day(s)");
    }
}
