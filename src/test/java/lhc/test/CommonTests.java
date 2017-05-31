package lhc.test;

import java.text.DecimalFormat;

import org.junit.Test;

public class CommonTests {
	@Test
	public void test() {
		DecimalFormat format = new DecimalFormat("0");
		System.out.println(format.format(40));
	}
}
