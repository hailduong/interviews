import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LargestPrimeDivisorTest {

  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;

  private ByteArrayInputStream testIn;
  private ByteArrayOutputStream testOut;

  @BeforeEach
  public void setUpOutput() {
    testOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(testOut));
  }

  private void provideInput(String data) {
    testIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(testIn);
  }

  private String getOutput() {
    return testOut.toString().trim();
  }

  @AfterEach
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }

  @Test()
  // Kiểm tra các số nhỏ: số nguyên tố (2), số có ước số nguyên tố lớn nhất (6, 100).
  public void testSmallNumbers() {
    String input = "2\n6\n100\n0\n";
    String expectedOutput = "-1\n3\n5";
    provideInput(input);
    LargestPrimeDivisor.main(new String[]{});
    assertEquals(expectedOutput, getOutput());
  }

  @Test
  // Kiểm tra số nguyên tố (17), không có ước số nguyên tố lớn nhất ngoài chính nó.
  public void testPrimeNumber() {
    String input = "17\n0\n";
    String expectedOutput = "-1";
    provideInput(input);
    LargestPrimeDivisor.main(new String[]{});
    assertEquals(expectedOutput, getOutput());
  }

  @Test
  // Kiểm tra số lớn (13195), có ước số nguyên tố lớn nhất là 29.
  public void testLargeNumber() {
    String input = "13195\n0\n";
    String expectedOutput = "29"; // Ước số nguyên tố lớn nhất của 13195 là 29
    provideInput(input);
    LargestPrimeDivisor.main(new String[]{});
    assertEquals(expectedOutput, getOutput());
  }

  @Test
  // Kiểm tra số âm (-100), phải tính giá trị tuyệt đối và tìm ước số nguyên tố lớn nhất.
  public void testNegativeNumbers() {
    String input = "-100\n0\n";
    String expectedOutput = "5";
    provideInput(input);
    LargestPrimeDivisor.main(new String[]{});
    assertEquals(expectedOutput, getOutput());
  }

  @Test
  // Kiểm tra số là số mũ của một nguyên tố duy nhất (7^2), phải trả về -1.
  public void testSinglePrimePower() {
    String input = "49\n0\n"; // 49 = 7^2
    String expectedOutput = "-1";
    provideInput(input);
    LargestPrimeDivisor.main(new String[]{});
    assertEquals(expectedOutput, getOutput());
  }

  // Test khi số có nhiều ước số nguyên tố
  @Test
  public void testMultiplePrimeDivisors() {
    long number = 30; // 30 = 2 * 3 * 5
    long expectedOutput = 5;

    long actualOutput = LargestPrimeDivisor.findLargestPrimeDivisor(number);

    assertEquals(expectedOutput, actualOutput);
  }

  // Test khi số là 1, không có ước số nguyên tố
  @Test
  public void testNumberOne() {
    long number = 1;
    long expectedOutput = -1;

    long actualOutput = LargestPrimeDivisor.findLargestPrimeDivisor(number);
    assertEquals(expectedOutput, actualOutput);
  }


}
