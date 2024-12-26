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
			long lpd = findLargestPrimeDivisor(Math.abs(number));
			System.out.println(lpd);
			number = input.nextLong();
		}
		input.close();
	}

	/**
	 * Tìm ước số nguyên tố lớn nhất của n. Nếu n chỉ có một ước số nguyên tố hoặc không có, trả về -1
	 */
	public static long findLargestPrimeDivisor(long n) {
		if (n < 2) return -1;
		long originalN = n;
		long largestPrime = -1;
		int primeCount = 0;

		// Kiểm tra ước số 2
		if (n % 2 == 0) {
			primeCount++;
			largestPrime = 2;
			while (n % 2 == 0) {
				n /= 2;
			}
		}

		// Kiểm tra các số lẻ từ 3 đến sqrt(n)
		for (long i = 3; i <= Math.sqrt(n); i += 2) {
			if (n % i == 0) {
				primeCount++;
				largestPrime = i;
				while (n % i == 0) {
					n /= i;
				}
			}
		}

		// Nếu n còn lại là một số nguyên tố lớn hơn 2
		if (n > 2) {
			primeCount++;
			largestPrime = n;
		}

		// Nếu có hơn một ước số nguyên tố, trả về LPD, ngược lại trả về -1
		return primeCount > 1 ? largestPrime : -1;
	}
}
