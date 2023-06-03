/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Asus
 */
public class myUtils {

    public static final Scanner sc = new Scanner(System.in);
    public static final int MDY = 1, DMY = 0, YMD = 2;

    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }

    public static String getString(String input, String bug) {
        String inputter;
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(input);
                inputter = sc.nextLine().trim();
                if (inputter.length() == 0 || inputter.isEmpty()) {
                    throw new Exception();
                }
                return inputter;
            } catch (Exception e) {
                System.out.println(bug);
            }
        }
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean OK;
        do {
            System.out.print(message + ": ");
            input = sc.nextLine().trim().toUpperCase();
            OK = validStr(input, pattern);
        } while (!OK);
        return input;

    }

    public static double getDouble(String number) {
        double value = 0;
        while (true) {
            try {
                System.out.print(number);
                value = Double.parseDouble(new Scanner(System.in).nextLine());
                if (value < 0) {
                    throw new Exception();
                }
                return value;
            } catch (Exception e) {
                System.out.println("value must not be less than 0!");
            }
        }
    }

    public static int getInt(String number) {
        int input = 0;
        while (true) {
            try {
                System.out.print(number);
                input = Integer.parseInt(new Scanner(System.in).nextLine());
                if (input < 0) {
                    throw new Exception();
                }
                return input;
            } catch (Exception e) {
                System.out.println("Value must not be less than 1!");
            }
        }
    }

    public static boolean checkPhone(String phone) {
        Scanner sc = new Scanner(System.in);
        Pattern pattern = Pattern.compile("^[0-9]{10,12}$");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static boolean checkDate(String date) {

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        try {
            df.setLenient(false);
            df.parse(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String getDate(String input, String bug) {
        boolean check = true;
        String date;
        do {
            date = getString(input, bug);
            check = checkDate(date);
            if (!check) {
                System.out.println("Your birth date must following form MM/dd/yyyy");
            }
        } while (check == false);
        return date;
    }

    public static boolean readBool(String message) {
        System.out.println(message + " [T-True/F-False]");
        String s = sc.nextLine().trim().toUpperCase();
        char c = s.charAt(0);
        return (c == 'T');
    }
}

//    public static boolean isLeap(int y) {
//        boolean result = false;
//        if ((y % 400 == 0) || ((y % 4 == 0) && (y % 100 != 0))) {
//            result = true;
//        }
//        return result;
//    }
//
//     public static boolean valid (int y, int m, int d) {
//        if (y<0 || m<0 || m>12 || d<0 || d>31) return false;
//        int maxD = 31;
//        if (m==4 || m==6 || m==9 || m==11) maxD = 30;
//        else if (m==2) {
//            if (isLeap(y)) maxD = 29;
//            else maxD = 28;
//        }
//        return d <= maxD;
//    }
//    
//    public static Date toDate(int y, int m, int d) {
//        if (!valid(y, m, d)) return null;
//        Calendar cal = Calendar.getInstance();
//        cal.set(y, m-1, d);
//        return cal.getTime();
//    }
//    
//
//    public static Date toDate(String dateStr, int dateFormat) {
//        int yIndex, mIndex, dIndex;
//        switch (dateFormat) {
//            case YMD:
//                yIndex = 0;
//                mIndex = 1;
//                dIndex = 2;
//                break;
//            case MDY:
//                yIndex = 2;
//                mIndex = 0;
//                dIndex = 1;
//                break;
//            case DMY:
//                yIndex = 2;
//                mIndex = 1;
//                dIndex = 0;
//                break;
//            default: return null;
//        }
//        String []parts = dateStr.split("[/-]");
//        if (parts.length != 3) return null;
//        int y = Integer.parseInt(parts[yIndex]);
//        int m = Integer.parseInt(parts[mIndex]);
//        int d = Integer.parseInt(parts[dIndex]);
//        return toDate(y, m, d);
//    }
//    
//    public static Date readMDY(String message) {
//        Date d;
//        String S;
//        do {
//            System.out.println(message + "[m/d/y]: ");
//            S = sc.nextLine().trim();
//            d = toDate(S, MDY);
//            if (d==null) System.out.println("Invalid date.");
//        } while (d == null);
//        return d;
//    }
//}
