package lab1;

import java.util.Scanner;

public class  Lab1 {

    // Перетворення у будь-яку систему з основою base (2, 8, 16)
    public static String convert(int number, int base) {
        if (number == 0) return "0";

        String digits = "0123456789ABCDEF"; // для шістнадцяткової
        String result = "";

        while (number > 0) {
            int remainder = number % base;          // остача
            result = digits.charAt(remainder) + result;  // додаємо спереду
            number = number / base;                 // зменшуємо число
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть ціле додатне число: ");
        int n = scanner.nextInt();

        if (n < 0) {
            System.out.println("Потрібне додатне число!");
            return;
        }

        String binary = convert(n, 2);
        String octal = convert(n, 8);
        String hex = convert(n, 16);

        System.out.println("Двійкова: " + binary);
        System.out.println("Вісімкова: " + octal);
        System.out.println("Шістнадцяткова: " + hex);
    }
}