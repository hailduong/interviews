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

  @Test
  public void testSmallNumbers() {
    String input = "2\n6\n100\n0\n";
    String expectedOutput = "-1\n3\n5";
    provideInput(input);
    LargestPrimeDivisor.main(new String[]{});
    assertEquals(expectedOutput, getOutput());
    // Kiểm tra các số nhỏ: số nguyên tố (2), số có ước số nguyên tố lớn nhất (6, 100).
  }

  @Test
  public void testPrimeNumber() {
    String input = "17\n0\n";
    String expectedOutput = "-1";
    provideInput(input);
    LargestPrimeDivisor.main(new String[]{});
    assertEquals(expectedOutput, getOutput());
    // Kiểm tra số nguyên tố (17), không có ước số nguyên tố lớn nhất ngoài chính nó.
  }

  @Test
  public void testLargeNumber() {
    String input = "13195\n0\n";
    String expectedOutput = "29"; // Ước số nguyên tố lớn nhất của 13195 là 29
    provideInput(input);
    LargestPrimeDivisor.main(new String[]{});
    assertEquals(expectedOutput, getOutput());
    // Kiểm tra số lớn (13195), có ước số nguyên tố lớn nhất là 29.
  }

  @Test
  public void testNegativeNumbers() {
    String input = "-100\n0\n";
    String expectedOutput = "5";
    provideInput(input);
    LargestPrimeDivisor.main(new String[]{});
    assertEquals(expectedOutput, getOutput());
    // Kiểm tra số âm (-100), phải tính giá trị tuyệt đối và tìm ước số nguyên tố lớn nhất.
  }

  @Test
  public void testSinglePrimePower() {
    String input = "49\n0\n"; // 49 = 7^2
    String expectedOutput = "-1";
    provideInput(input);
    LargestPrimeDivisor.main(new String[]{});
    assertEquals(expectedOutput, getOutput());
    // Kiểm tra số là số mũ của một nguyên tố duy nhất (7^2), phải trả về -1.
  }

  @Test
  public void testMultiplePrimeDivisors() {
    String input = "30\n0\n"; // 30 = 2 * 3 * 5
    String expectedOutput = "5";
    provideInput(input);
    LargestPrimeDivisor.main(new String[]{});
    assertEquals(expectedOutput, getOutput());
    // Kiểm tra số có nhiều ước số nguyên tố (2, 3, 5), phải trả về ước số nguyên tố lớn nhất là 5.
  }

  @Test
  public void testNumberOne() {
    String input = "1\n0\n";
    String expectedOutput = "-1";
    provideInput(input);
    LargestPrimeDivisor.main(new String[]{});
    assertEquals(expectedOutput, getOutput());
    // Kiểm tra trường hợp N = 1, không có ước số nguyên tố, trả về -1.
  }


}
