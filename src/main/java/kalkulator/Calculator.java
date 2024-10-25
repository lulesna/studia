package kalkulator;

import java.util.Scanner;

public class Calculator {
	private int state = 0;
	private int memory = 0;

	public int getState() {
		return state;
	}

	public void setState(long state) {
		if (checkState(state)) {
			this.state = (int) state;
		}
	}

	// sprawdza czy stan mieści się w zakresie int
	public boolean checkState(long value) {
		if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
			System.out.println("Przekroczono zakres int, stan pozostaje bez zmian: " + state);
			return false;
		}
		return true;
	}

	// zapisuje stan do pamięci
	public void setMemory(int state) {
		this.memory = state;
		System.out.println("Zapisano wynik.");
	}

	// wyświetla stan
	public Integer getMemory() {
		return memory;
	}

	public void add(int value) {
		long result = (long) state + value;
        if (checkState(result)) {
            state = (int) result;
        }
	}

	public void mult(int value) {
        long result = (long) state * value;
        if (checkState(result)) {
            state = (int) result;
        }
	}

	public void sub(int value) {
        long result = (long) state - value;
        if (checkState(result)) {
            state = (int) result;
        }
	}

	public void div(int value) {
		if (value != 0) {
			state /= value;
		} else {
			System.out.println("Nie można dzielić przez zero.");
		}
	}

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		Scanner input = new Scanner(System.in);

		System.out.println("Wybierz działanie: dodawanie (+), odejmowanie (-), mnożenie (*), dzielenie bez reszty (/).");
		System.out.println("Lub operację na pamięci: zapisz aktualny stan do pamięci (m), wczytaj wartość z pamięci (mr).");
		System.out.println("Lub opcję: wyjdź (q), zmień stan kalkulatora (s).");

		while (true) {
			System.out.println("\nObecny stan: " + calculator.getState());
			System.out.println("Pamięć: " + calculator.getMemory());
			System.out.print("Twój wybór: ");

			String choice = input.nextLine().trim();

			if (choice.equals("q")) {
				break;
			}

			switch (choice) {
				case "+":
					System.out.print("Podaj wartość, którą chcesz dodać: ");
					calculator.add(getValueInput(input, calculator));
					break;
				case "-":
					System.out.print("Podaj wartość, którą chcesz odjąć: ");
					calculator.sub(getValueInput(input, calculator));
					break;
				case "*":
					System.out.print("Podaj wartość, przez którą chcesz pomnożyć: ");
					calculator.mult(getValueInput(input, calculator));
					break;
				case "/":
					System.out.print("Podaj wartość, przez którą chcesz podzielić: ");
					calculator.div(getValueInput(input, calculator));
					break;
				case "s":
					System.out.print("Podaj nowy stan: ");
					calculator.setState(getValueInput(input, calculator));
					break;
				case "m":
					calculator.setMemory(calculator.state);
					break;
				default:
					System.out.println("Niepoprawny wybór.");
			}
		}
		input.close();
	}

	private static int getValueInput(Scanner input, Calculator calculator) {
		while (true) {
			String value = input.nextLine().trim();
			if (value.equals("mr")) {
				return calculator.getMemory();
			}
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException e) {
				System.out.print("Nieprawidłowa wartość. Podaj liczbę w zakresie int: ");
			}
		}
	}
}