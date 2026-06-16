public class Student18 {
    String nim, name, className;
    double ipk;

    // Default Constructor
    public Student18 () {
    }

    // Parameterized Constructor
    public Student18 (String nm, String nama, String kls, double ip) {
        nim = nm;
        name = nama;
        className = kls;
        ipk = ip;
    }

    // Method untuk menampilkan data mahasiswa
    void print () {
        System.out.println(nim + " - " + name + " - " + className + " - " + ipk);
    }
}