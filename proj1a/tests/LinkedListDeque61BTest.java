import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Performs some basic linked list tests.
 */
public class LinkedListDeque61BTest {

    @Test
    public void addFirstTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
    }

    @Test
    public void addLastTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    @Test
    public void testSizeAndIsEmpty() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
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
    }

    @Test
    public void testGet() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.get(5)).isNull();

        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.get(6)).isNull();
        assertThat(lld1.get(2)).isEqualTo(0);
    }

    @Test
    public void testGetRecursive() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.getRecursive(5)).isNull();

        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.getRecursive(5)).isNull();
        assertThat(lld1.getRecursive(2)).isEqualTo(0);
    }

    @Test
    public void removeFirstTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]

        assertThat(lld1.removeFirst()).isEqualTo("front");
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        assertThat(lld1.removeFirst()).isEqualTo("middle");
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        assertThat(lld1.removeFirst()).isEqualTo("back");
        assertThat(lld1.isEmpty()).isTrue();

        assertThat(lld1.removeFirst()).isNull();
    }

    @Test
    public void removeLastTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst("back"); // after this call we expect: ["back"]
        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]

        assertThat(lld1.removeLast()).isEqualTo("back");
        assertThat(lld1.toList()).containsExactly("front", "middle").inOrder();

        assertThat(lld1.removeLast()).isEqualTo("middle");
        assertThat(lld1.toList()).containsExactly("front").inOrder();

        assertThat(lld1.removeLast()).isEqualTo("front");
        assertThat(lld1.isEmpty()).isTrue();

        assertThat(lld1.removeLast()).isNull();
    }

    @Test
    public void removeFirstAndRemoveLastTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]

        assertThat(lld1.removeFirst()).isEqualTo("front");
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        assertThat(lld1.removeLast()).isEqualTo("back");
        assertThat(lld1.toList()).containsExactly("middle").inOrder();

        assertThat(lld1.removeFirst()).isEqualTo("middle");
        assertThat(lld1.isEmpty()).isTrue();

        assertThat(lld1.removeLast()).isNull();
    }

    // Below, you'll write your own tests for LinkedListDeque61B.
}