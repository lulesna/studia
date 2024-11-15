import org.junit.*;
import java.util.EmptyStackException;
import static org.junit.Assert.*;

public class StackTest {
	@Test
	public void testNewStack() {
		Stack stack = new Stack();
		assertTrue("nowy stos powinien być pusty", stack.isEmpty());
		assertEquals("rozmiar nowego stosu powinien być 0", 0, stack.currentSize());
	}

	@Test
	public void testPopAfterPush() {
		Stack stack = new Stack();
		stack.push("napis");
		String result = stack.pop();

		assertEquals("pop after push", "napis", result);
	}

	@Test
	public void testPushAndPop() {
		Stack stack = new Stack();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		assertEquals("rozmiar powinien wynosić 3", 3, stack.currentSize());

		assertEquals("3", stack.pop());
		assertEquals("2", stack.pop());
		assertEquals("1", stack.pop());
		assertTrue("stos powinien być pusty", stack.isEmpty());
	}

	@Test
	public void testPushAndPeek() {
		Stack stack = new Stack();
		stack.push("test");
		assertEquals("peek powinien zwrócić element", "test", stack.peek());
		assertEquals(1, stack.currentSize());
		assertEquals("element nadal powinien być na stosie", "test", stack.peek());
	}

	@Test(expected = EmptyStackException.class)
	public void testPopOnEmptyStack() {
		Stack stack = new Stack();
		stack.pop();
	}

	@Test(expected = EmptyStackException.class)
	public void testPeekOnEmptyStack() {
		Stack stack = new Stack();
		stack.peek();
	}

	@Test
	public void testArrayExpansion() {
		Stack stack = new Stack();
		for (int i = 1; i < 13; i++) {
			stack.push("element" + i);
		}

		assertEquals("liczba elementów powinna wynosić 12", 12, stack.currentSize());
		assertEquals("rozmiar tablicy powinien się zwiększyć dwukrotnie (10 -> 20)", 20, stack.arraySize());

		assertEquals("element12", stack.pop());
		assertEquals("element11", stack.pop());
	}

	@Test
	public void testArrayShrinking() {
		Stack stack = new Stack();
		for (int i = 0; i < 20; i++) {
			stack.push("element" + i);
		}

		for (int i = 0; i < 16; i++) {
			stack.pop();
		}

		assertEquals("liczba elementów powinna wynosić 4", 4, stack.currentSize());
		assertEquals("rozmiar tablicy powinien się zmniejszyć o połowę (20 -> 10)", 10, stack.arraySize());
	}

	@Test
	public void testIrregularStringHandling() {
		Stack stack = new Stack();
		stack.push("");
		stack.push("   ");
		stack.push("\n");
		stack.push(null);

		assertEquals(4, stack.currentSize());
        assertNull(stack.pop());
		assertEquals("\n", stack.pop());
		assertEquals("   ", stack.pop());
		assertEquals("", stack.pop());
	}
}
