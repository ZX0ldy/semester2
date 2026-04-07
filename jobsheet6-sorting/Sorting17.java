public class Sorting23 {
    int[] data;
    int size;

    public Sorting23(int[] data) {
        this.data = data;
        this.size = data.length;
    }

    public void bubbleSort() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    public void print(){
        for (int num : data) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
