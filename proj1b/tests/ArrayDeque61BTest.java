import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

    @Test
    @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }

    @Test
    public void toListTestBasic() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.toList()).containsExactly().inOrder();
        lld1.addFirst("0");
        lld1.addLast("1");
        lld1.addFirst("2");
        lld1.removeLast();
        assertThat(lld1.toList()).containsExactly("2", "0").inOrder();
    }

    @Test
    public void addFirstTestBasic() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addFirst("0");
        assertThat(lld1.toList()).containsExactly("0").inOrder();

        lld1.addFirst("1");
        assertThat(lld1.toList()).containsExactly("1", "0").inOrder();

        lld1.addFirst("2");
        assertThat(lld1.toList()).containsExactly("2", "1", "0").inOrder();

        lld1.addFirst("3");
        lld1.addFirst("4");
        lld1.addFirst("5");
        lld1.addFirst("6");
        lld1.addFirst("7");
        assertThat(lld1.toList()).containsExactly("7", "6", "5", "4", "3", "2", "1", "0").inOrder();

        lld1.addFirst("8");

        lld1.addFirst("16");
        lld1.addFirst("15");
        lld1.addFirst("14");
        lld1.addFirst("13");
        lld1.addFirst("12");
        lld1.addFirst("11");
        lld1.addFirst("10");
        lld1.addFirst("9");
        assertThat(lld1.toList()).containsExactly("9", "10", "11", "12", "13", "14", "15", "16" ,"8","7", "6", "5", "4", "3", "2", "1", "0").inOrder();
    }

    @Test
    public void addLastTestBasic() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("0");
        assertThat(lld1.toList()).containsExactly("0").inOrder();
        lld1.addLast("1");
        assertThat(lld1.toList()).containsExactly("0", "1").inOrder();
        lld1.addLast("2");
        assertThat(lld1.toList()).containsExactly("0", "1", "2").inOrder();

        lld1.addLast("3");
        lld1.addLast("4");
        lld1.addLast("5");
        lld1.addLast("6");
        lld1.addLast("7");
        assertThat(lld1.toList()).containsExactly("0", "1", "2", "3", "4", "5", "6", "7").inOrder();

        lld1.addLast("8");
        lld1.addLast("9");
        lld1.addLast("10");
        lld1.addLast("11");
        lld1.addLast("12");
        lld1.addLast("13");
        lld1.addLast("14");
        lld1.addLast("15");
        lld1.addLast("16");
        assertThat(lld1.toList()).containsExactly("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16").inOrder();
    }

    @Test
    public void removeFirstTestBasic() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.removeFirst()).isNull();
        assertThat(lld1.toList()).containsExactly().inOrder();
        lld1.addLast("0");
        assertThat(lld1.removeFirst()).isEqualTo("0");
        assertThat(lld1.toList()).containsExactly().inOrder();
        lld1.addLast("0");
        lld1.addLast("1");
        lld1.addLast("2");
        lld1.addLast("3");
        lld1.addLast("4");
        lld1.addLast("5");
        lld1.addLast("6");
        lld1.addLast("7");
        assertThat(lld1.removeFirst()).isEqualTo("0");
        assertThat(lld1.removeFirst()).isEqualTo("1");
        assertThat(lld1.removeFirst()).isEqualTo("2");
        assertThat(lld1.toList()).containsExactly(  "3", "4", "5", "6", "7").inOrder();
    }

    @Test
    public void removeLastTestBasic() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.removeLast()).isNull();
        assertThat(lld1.toList()).containsExactly().inOrder();
        lld1.addFirst("0");
        assertThat(lld1.removeLast()).isEqualTo("0");
        assertThat(lld1.toList()).containsExactly().inOrder();
        lld1.addLast("0");
        lld1.addLast("1");
        lld1.addLast("2");
        lld1.addLast("3");
        lld1.addLast("4");
        lld1.addLast("5");
        lld1.addLast("6");
        lld1.addLast("7");
        lld1.addLast("8");
        assertThat(lld1.removeLast()).isEqualTo("8");
        assertThat(lld1.removeLast()).isEqualTo("7");
        assertThat(lld1.removeLast()).isEqualTo("6");
        assertThat(lld1.removeLast()).isEqualTo("5");
        assertThat(lld1.toList()).containsExactly(  "0","1", "2", "3", "4").inOrder();
        assertThat(lld1.removeLast()).isEqualTo("4");
        assertThat(lld1.removeLast()).isEqualTo("3");
        assertThat(lld1.removeLast()).isEqualTo("2");
        assertThat(lld1.toList()).containsExactly(  "0","1").inOrder();
    }

    @Test
    public void testSizeAndIsEmpty() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.size()).isEqualTo(0);
        assertThat(lld1.isEmpty()).isTrue();

        lld1.addLast(0);
        assertThat(lld1.size()).isEqualTo(1);
        assertThat(lld1.isEmpty()).isFalse();

        lld1.addLast(1);
        assertThat(lld1.size()).isEqualTo(2);
        assertThat(lld1.isEmpty()).isFalse();

        lld1.removeFirst();
        assertThat(lld1.size()).isEqualTo(1);
        assertThat(lld1.isEmpty()).isFalse();

        lld1.removeLast();
        assertThat(lld1.size()).isEqualTo(0);
        assertThat(lld1.isEmpty()).isTrue();

        lld1.removeFirst();
        assertThat(lld1.size()).isEqualTo(0);
        assertThat(lld1.isEmpty()).isTrue();
    }

    @Test
    public void testGet() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.get(5)).isNull();

        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.get(6)).isNull();
        assertThat(lld1.get(2)).isEqualTo(0);
        assertThat(lld1.get(-1)).isNull();
    }

}
