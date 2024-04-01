import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /**
     * Returns the total sum in a list of integers
     */
    public static int sum(List<Integer> L) {
        int value = 0;
        for (int i : L) {
            value += i;
        }
        return value;
    }

    /**
     * Returns a list containing the even numbers of the given list
     */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> evenList = new ArrayList<>();
        for (int i : L) {
            if (i % 2 == 0) {
                evenList.add(i);
            }
        }
        return evenList;
    }

    /**
     * Returns a list containing the common item of the two given lists
     */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> commonList = new ArrayList<>();
        List<Integer> targetList;
        List<Integer> otherList;
        if (L1.size()>L2.size()) {
            targetList = L1;
            otherList = L2;
        } else {
            targetList = L2;
            otherList = L1;
        }
        for (int i : targetList) {
            if(otherList.contains(i)){
                commonList.add(i);
            }
        }
        return commonList;
    }


    /**
     * Returns the number of occurrences of the given character in a list of strings.
     */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int number = 0;
        for(String i : words) {
            if (i.indexOf(c) != -1){
                number++;
            }
        }
        return number;
    }
}
