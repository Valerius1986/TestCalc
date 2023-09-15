//import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение: ");
        String input = scanner.nextLine();
        try {
            String result = calc(input);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("throws Exception");
        }
    }

    static String calc(String input) throws Exception {
        var splitStr = input.split(" ");
        if(splitStr.length != 3){
            throw new Exception("throws Exception");
        }

        String str1 = splitStr[0];
        String operator = splitStr[1];
        String str2 = splitStr[2];

        boolean isRoman = isRomanNumber(str1) && isRomanNumber(str2);
        int num1;
        if (isRoman) {
            num1 = romanToArabic(str1);
        } else {
            num1 = Integer.parseInt(str1);
        }

        int num2;
        if (isRoman) {
            num2 = romanToArabic(str2);
        } else {
            num2 = Integer.parseInt(str2);
        }

        if(num1 < 1 || num1 > 10){
            throw new Exception("throws Exception");
        }
        if(num2 < 1 || num2 > 10){
            throw new Exception("throws Exception");
        }

        int result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":

                result = num1 / num2;
                break;
            default:
                throw new Exception("throws Exception");
        }

        if (isRoman) {
            return arabicToRoman(result);
        } else {
            return Integer.toString(result);
        }
    }

    static boolean isRomanNumber(String s) {
        return s.matches("^[IVXLCDM]+$");
    }

    static int romanToArabic(String roman) {
        Map<Character, Integer> romanToArabicMap = new HashMap<>();
        romanToArabicMap.put('I', 1);
        romanToArabicMap.put('V', 5);
        romanToArabicMap.put('X', 10);
        romanToArabicMap.put('L', 50);
        romanToArabicMap.put('C', 100);
        romanToArabicMap.put('D', 500);
        romanToArabicMap.put('M', 1000);

        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int value = romanToArabicMap.get(roman.charAt(i));

            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }

            prevValue = value;
        }

        return result;
    }

    static String arabicToRoman(int num) {
        if (num < 1) {
            throw new IllegalArgumentException("Cannot convert negative number to Roman numerals");
        }

        String[] romanSymbols = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "D", "M"};
        int[] arabicValues = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 1000};
        StringBuilder roman = new StringBuilder();

        for (int i = arabicValues.length - 1; i >= 0; i--) {
            while (num >= arabicValues[i]) {
                roman.append(romanSymbols[i]);
                num -= arabicValues[i];
            }
        }

        return roman.toString();
    }

}