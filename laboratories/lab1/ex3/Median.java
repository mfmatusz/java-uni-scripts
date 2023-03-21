import java.util.LinkedList;
import java.util.Random;

public class Median {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        Random random = new Random();
        list.add(random.nextInt(1001));
        list.add(random.nextInt(1001));
        list.add(random.nextInt(1001));
        list.add(random.nextInt(1001));
        list.add(random.nextInt(1001));
        list.add(random.nextInt(1001));
        list.add(random.nextInt(1001));
        bubbleSort(list);
        int s = list.size();
        int m = s/2;
        int median;
        if ((list.size()%2)==0){
            median = (list.get(m-1)+ list.get(m))/2;
        }
        else{
            median = list.get(m);
        }
        System.out.println(list);
        System.out.println(median);
    }

    public static void deleteMedian(LinkedList<Integer> list){
        int s = list.size();
        int m = s/2;
        if ((list.size()%2)==0){
            list.remove(m-1);
        }
        else{
            list.remove(m-1);
        }
    }

    public static void bubbleSort(LinkedList<Integer> list) {
        int n = list.size();
        boolean swapped;

        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (list.get(i) > list.get(i + 1)) {
                    int temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }
}
