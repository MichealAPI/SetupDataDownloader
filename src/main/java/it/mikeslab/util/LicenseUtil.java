package it.mikeslab.util;

import java.util.Scanner;

public class LicenseUtil {

    public static String LICENSE_KEY;

    public static void inputLicenseKey() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your license key: ");

        LICENSE_KEY = scanner.nextLine();

    }


}
