package kalkulator;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

	// dodawanie

	@Test
	public void testAddOne(){
		Calculator sut = new Calculator();
		sut.add(1);
		assertEquals("0+1 = 1", 1, sut.getState());
	}

	@Test
	public void testAddZero(){
		Calculator sut = new Calculator();
		sut.add(0);
		assertEquals("0+0 = 0", 0, sut.getState());
	}

	@Test
	public void testAddNegative() {
		Calculator sut = new Calculator();
		sut.setState(5);
		sut.add(-3);
		assertEquals("5+(-3) = 2", 2, sut.getState());
	}

	@Test
	public void testAddOneToMaxValueMinusOne() {
		Calculator sut = new Calculator();
		sut.setState(Integer.MAX_VALUE - 1);
		sut.add(1);
		assertEquals("MAX_VALUE-1 + 1 = MAX_VALUE", Integer.MAX_VALUE, sut.getState());
	}

	@Test
	public void testAddMaxValueToMaxValue() {
		Calculator sut = new Calculator();
		sut.setState(Integer.MAX_VALUE);
		sut.add(Integer.MAX_VALUE);
		assertEquals("Stan pozostaje bez zmian.", Integer.MAX_VALUE, sut.getState());
	}

	// mnożenie

	@Test
	public void testMultOneByTwo(){
		Calculator sut = new Calculator();
		sut.setState(1);
		sut.mult(2);
		assertEquals("1*2 = 2", 2, sut.getState());
	}

	@Test
	public void testMultByZero(){
		Calculator sut = new Calculator();
		sut.setState(1);
		sut.mult(0);
		assertEquals("1*0 = 0", 0, sut.getState());
	}

	@Test
	public void testMultByNegative() {
		Calculator sut = new Calculator();
		sut.setState(3);
		sut.mult(-2);
		assertEquals("3*(-2) = -6", -6, sut.getState());
	}

	@Test
	public void testMultNegativeByNegative() {
		Calculator sut = new Calculator();
		sut.setState(-3);
		sut.mult(-2);
		assertEquals("(-3)*(-2) = 6", 6, sut.getState());
	}

	@Test
	public void testMultByMaxValue() {
		Calculator sut = new Calculator();
		sut.setState(2);
		sut.mult(Integer.MAX_VALUE);
		assertEquals("Stan pozostaje bez zmian.", 2, sut.getState());
	}

	// odejmowanie

	@Test
	public void testSubOne(){
		Calculator sut = new Calculator();
		sut.setState(0);
		sut.sub(1);
		assertEquals("0-1 = -1", -1, sut.getState());
	}

	@Test
	public void testSubZero(){
		Calculator sut = new Calculator();
		sut.setState(0);
		sut.sub(0);
		assertEquals("0-0 = 0", 0, sut.getState());
	}

	@Test
	public void testSubNegative() {
		Calculator sut = new Calculator();
		sut.setState(5);
		sut.sub(-3);
		assertEquals("5-(-3) = 8", 8, sut.getState());
	}

	@Test
	public void testSubFromNegative() {
		Calculator sut = new Calculator();
		sut.setState(-5);
		sut.sub(3);
		assertEquals("(-5)-3 = -8", -8, sut.getState());
	}

	@Test
	public void testSubtractMinValueFromZero() {
		Calculator sut = new Calculator();
		sut.setState(0);
		sut.sub(Integer.MIN_VALUE);
		assertEquals("Stan pozostaje bez zmian.", 0, sut.getState());
	}

	// dzielenie

	@Test
	public void testDivSixByTwo() {
		Calculator sut = new Calculator();
		sut.setState(6);
		sut.div(2);
		assertEquals("6/2 = 3", 3, sut.getState());
	}

	@Test
	public void testDivByZero() {
		Calculator sut = new Calculator();
		sut.setState(5);
		sut.div(0);
		assertEquals("Stan pozostaje bez zmian.", 5, sut.getState());
	}

	@Test
	public void testDivByNegative() {
		Calculator sut = new Calculator();
		sut.setState(6);
		sut.div(-2);
		assertEquals("6/(-2) = -3", -3, sut.getState());
	}

	@Test
	public void testDivNegativeByNegative() {
		Calculator sut = new Calculator();
		sut.setState(-6);
		sut.div(-2);
		assertEquals("(-6)/(-2) = 3", 3, sut.getState());
	}

	@Test
	public void testMaxValueDivByOne() {
		Calculator sut = new Calculator();
		sut.setState(Integer.MAX_VALUE);
		sut.div(1);
		assertEquals("MAX_VALUE / 1 = MAX_VALUE", Integer.MAX_VALUE, sut.getState());
	}
}
