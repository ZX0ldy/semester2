public class StudentAssignmentStack01 {
    Student01[] stack;
    int size;
    int top;

    StudentAssignmentStack01(int size) {
        this.size = size;
        top = -1;
        stack = new Student01[size];
    }

    boolean isFull() {
        if (top == size - 1) {
            return true;
        } else {
            return false;
        }
    }

    boolean isEmpty() {
        if (top == -1) {
            return true;
        } else {
            return false;
        }
    }

    void push(Student01 std) {
        if (!isFull()) {
            top++;
            stack[top] = std;
        } else {
            System.out.println("Stack is already full!!");
        }
    }

    Student01 pop() {
        if (!isEmpty()) {
            Student01 std = stack[top];
            top--;
            return std;
        } else {
            System.out.println("There is no data in Stack!!");
            return null;
        }
    }

    Student01 peek() {
        if (!isEmpty()) {
            return stack[top];
        } else {
            System.out.println("There is no data in Stack!!");
            return null;
        }
    }

    void print() {
        System.out.printf("%-10s\t%-15s\t%-10s%n", "NIM", "Name", "Class Name");
        for (int i = 0; i <= top; i++) {
            System.out.printf("%-10s\t%-15s\t%-10s%n",
                    stack[i].nim, stack[i].name, stack[i].className);
        }
        System.out.println();
    }

    // Experiment 2: Convert grade to binary
    String convertToBinary(int grade) {
        ConversionStack01 convStack = new ConversionStack01();
        while (grade > 0) {
            int mod = grade % 2;
            convStack.push(mod);
            grade = grade / 2;
        }
        String binary = "";
        while (!convStack.isEmpty()) {
            binary += convStack.pop();
        }
        return binary;
    }

    // Q5: Get the first student who submitted (bottom of stack)
    Student01 getBottom() {
        if (!isEmpty()) {
            return stack[0];
        } else {
            System.out.println("There is no data in Stack!!");
            return null;
        }
    }

    // Q6: Count assignments in stack
    int countAssignments() {
        return top + 1;
    }
}
