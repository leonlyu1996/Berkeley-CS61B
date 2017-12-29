import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDeque1B {

    @Test
    public void testAddFirstRemoveFirst() {
        StudentArrayDeque<Integer> testedArrayDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> goodArrayDeque = new ArrayDequeSolution<>();
        for (int i = 0; i < 100; i++) {
            int randomInteger = StdRandom.uniform(10);
            goodArrayDeque.addFirst(randomInteger);
            testedArrayDeque.addFirst(randomInteger);
            assertEquals(goodArrayDeque.removeFirst(), testedArrayDeque.removeFirst());
        }
    }

    @Test
    public void testAddFirstRemoveLast() {
        StudentArrayDeque<Integer> testedArrayDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> goodArrayDeque = new ArrayDequeSolution<>();
        for (int i = 0; i < 100; i++) {
            int randomInteger = StdRandom.uniform(10);
            goodArrayDeque.addFirst(randomInteger);
            testedArrayDeque.addFirst(randomInteger);
            assertEquals(goodArrayDeque.removeLast(), testedArrayDeque.removeLast());
        }
    }

    @Test
    public void testAddLastRemoveFirst() {
        StudentArrayDeque<Integer> testedArrayDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> goodArrayDeque = new ArrayDequeSolution<>();
        for (int i = 0; i < 100; i++) {
            int randomInteger = StdRandom.uniform(10);
            goodArrayDeque.addLast(randomInteger);
            testedArrayDeque.addLast(randomInteger);
            assertEquals(goodArrayDeque.removeFirst(), testedArrayDeque.removeFirst());
        }
    }

    @Test
    public void testAddLastRemoveLast() {
        StudentArrayDeque<Integer> testedArrayDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> goodArrayDeque = new ArrayDequeSolution<>();
        for (int i = 0; i < 100; i++) {
            int randomInteger = StdRandom.uniform(10);
            goodArrayDeque.addLast(randomInteger);
            testedArrayDeque.addLast(randomInteger);
            assertEquals(goodArrayDeque.removeLast(), testedArrayDeque.removeLast());
        }
    }

    @Test
    public void testMultiAddRemove() {

        StudentArrayDeque<Integer> testedArrayDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> goodArrayDeque = new ArrayDequeSolution<>();

        int repeatTimes = 10;

        OperationSequence fs = new OperationSequence();
        for (int j = 0; j < repeatTimes; j++) {
            int randomInteger = StdRandom.uniform(10);
            goodArrayDeque.addLast(randomInteger);
            testedArrayDeque.addLast(randomInteger);
            DequeOperation dequeOp = new DequeOperation("addFirst", randomInteger);
            fs.addOperation(dequeOp);
        }
        for (int j = 0; j < repeatTimes; j++) {
            Integer expect = goodArrayDeque.removeLast();
            Integer real = testedArrayDeque.removeLast();
            DequeOperation dequeOp = new DequeOperation("removeLast");
            fs.addOperation(dequeOp);
            assertEquals("\n" + fs.toString(), expect, real);
        }

    }

}
