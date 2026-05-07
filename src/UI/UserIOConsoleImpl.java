package UI;

import java.util.Scanner;

// Reading the user input and verification

public class UserIOConsoleImpl implements UserIO{

    Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    public double readDouble(String prompt) {
        System.out.println(prompt);
        double usrDouble = scanner.nextDouble();
        scanner.nextLine();
        return usrDouble;
    }

    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        double usrDouble = scanner.nextDouble();
        scanner.nextLine();

        while(min > usrDouble || usrDouble > max) {
            System.out.println(prompt);
            usrDouble = scanner.nextDouble();
            scanner.nextLine();
        }
        return usrDouble;
    }

    public float readFloat(String prompt) {
        System.out.println(prompt);
        float usrFloat = scanner.nextFloat();
        scanner.nextLine();
        return usrFloat;
    }

    public float readFloat(String prompt, float min, float max) {
        System.out.println(prompt);
        float usrFloat = scanner.nextFloat();
        scanner.nextLine();

        while(min > usrFloat || usrFloat > max ) {
            System.out.println(prompt);
            usrFloat = scanner.nextFloat();
            scanner.nextLine();
        }
        return usrFloat;
    }

    public int readInt(String prompt) {
        System.out.println(prompt);
        int usrNum = scanner.nextInt();
        scanner.nextLine();
        return usrNum;
    }

    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        int usrNum = scanner.nextInt();
        scanner.nextLine();
        while(min > usrNum || usrNum > max) {
            System.out.println(prompt);
            usrNum = scanner.nextInt();
            scanner.nextLine();
        }
        return usrNum;
    }

    public long readLong(String prompt) {
        System.out.println(prompt);
        long usrLong = scanner.nextLong();
        scanner.nextLine();
        return usrLong;
    }

    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        long usrLong = scanner.nextLong();
        scanner.nextLine();

        while(min > usrLong || max > usrLong) {
            System.out.println(prompt);
            usrLong = scanner.nextLong();
            scanner.nextLine();
        }
        return usrLong;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String usrInput = scanner.nextLine();
        return usrInput;
    }
}