public class ExcuseLetterStack01 {
    ExcuseLetter01[] stack;
    int size;
    int top;

    ExcuseLetterStack01(int size) {
        this.size = size;
        this.top = -1;
        this.stack = new ExcuseLetter01[size];
    }

    boolean isFull() {
        return top == size - 1;
    }

    boolean isEmpty() {
        return top == -1;
    }

    void push(ExcuseLetter01 letter) {
        if (!isFull()) {
            top++;
            stack[top] = letter;
        } else {
            System.out.println("Stack is already full!!");
        }
    }

    ExcuseLetter01 pop() {
        if (!isEmpty()) {
            ExcuseLetter01 letter = stack[top];
            top--;
            return letter;
        } else {
            System.out.println("There is no data in Stack!!");
            return null;
        }
    }

    ExcuseLetter01 peek() {
        if (!isEmpty()) {
            return stack[top];
        } else {
            System.out.println("There is no data in Stack!!");
            return null;
        }
    }

    void print() {
        System.out.printf("%-8s %-15s %-10s %-25s %-10s%n",
                "ID", "Name", "Class", "Type", "Duration");
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i <= top; i++) {
            System.out.printf("%-8s %-15s %-10s %-25s %-10d day(s)%n",
                    stack[i].id,
                    stack[i].name,
                    stack[i].className,
                    stack[i].getExcuseType(),
                    stack[i].duration);
        }
        System.out.println();
    }

    // Menu 4: Search letter by student name
    void searchByName(String name) {
        boolean found = false;
        for (int i = 0; i <= top; i++) {
            if (stack[i].name.equalsIgnoreCase(name)) {
                System.out.println("Letter found:");
                stack[i].printInfo();
                found = true;
                System.out.println();
            }
        }
        if (!found) {
            System.out.println("No excuse letter found for student: " + name);
        }
    }
}
