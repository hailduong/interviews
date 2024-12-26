/**
 * All integer numbers are divisible by primes. If a number is divisible by more than one prime number,
 * then it obviously has a largest prime divisor. The numbers which do not fall in this category do not
 * have a largest prime divisor. Given a number N your job is to write a program that finds its largest
 * prime divisor. An integer number n is divisible by another integer number m if there is an integer t
 * such that mt = n.
 * Input
 * The input file contains at most 450 sets of inputs. Each line contains a decimal integer N. N does
 * not have more than 14 digits. Input is terminated by a line containing a single zero. So no other line
 * except the last line contains a zero in the input. This line need not be processed.
 * Output
 * For each line of the input produce one line of output. This line contains an integer LPD, which is the
 * largest prime divisor of the input number N. If the input number is not divisible by more than one
 * prime number output a ‘-1’.
 * Sample Input 
 * 2
 * 6
 * 100
 * 0
 * Sample Output
 * -1
 * 3
 * 5
 */
//https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=2461

import java.util.Scanner;

public class LargestPrimeDivisor {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		long number = input.nextLong();

		while (number != 0) {
			number = Math.abs(number); // Đảm bảo xử lý số dương
			long largestPrimeDivisor = findLargestPrimeDivisor(number);
			System.out.println(largestPrimeDivisor);
			number = input.nextLong();  // Nhập số tiếp theo
		}
	}

	// Phương thức tách logic tìm ước số nguyên tố lớn nhất
	static long findLargestPrimeDivisor(long number) {
		long largestPrimeDivisor = -1;
		long primeDivisorCount = 0;
		long sqrtOfNumber = (long) Math.sqrt(number);

		for (long i = 2; i <= sqrtOfNumber; i++) {
			boolean isPrimeDivisor = false;
			while (number % i == 0) {
				isPrimeDivisor = true;
				largestPrimeDivisor = i;
				number /= i;
			}
			if (isPrimeDivisor) {
				primeDivisorCount++;
			}
		}

		if (number > 1) {
			primeDivisorCount++;
			largestPrimeDivisor = number;
		}

		// Kiểm tra số lượng ước số nguyên tố
		if (primeDivisorCount == 1) {
			return -1; // Số chỉ có một ước số nguyên tố (như lũy thừa của một số nguyên tố)
		}

		return largestPrimeDivisor;
	}
}
