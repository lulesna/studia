import java.util.EmptyStackException;

public class Stack {
	private String [] elements;
	private int top = -1;

	public Stack() {
		elements = new String[10];
	}

	public void push(String element){
		if (top == (elements.length - 1)) {
			expandArray();
		}
		elements[++top] = element;
	}

	public String pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		String element = elements[top];
		elements[top--] = null;

		// zmniejszenie rozmiaru tablicy, jeśli jest wykorzystana w mniej niż 25%
		if (elements.length > 10 && top < elements.length / 4) {
			shrinkArray();
		}

		return element;
	}

	public String peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return elements[top];
	}

	// zwiększenie tablicy dwukrotnie
	private void expandArray() {
		String[] newElements = new String[elements.length * 2];
		System.arraycopy(elements, 0, newElements, 0, arraySize());
		elements = newElements;
	}

	// zmniejszenie tablicy o połowę
	private void shrinkArray() {
		String[] newElements = new String[elements.length / 2];
		System.arraycopy(elements, 0, newElements, 0, currentSize());
		elements = newElements;
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public int currentSize() {
		return top + 1;
	}

	public int arraySize() {
		return elements.length;
	}
}
