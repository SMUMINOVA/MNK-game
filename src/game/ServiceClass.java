package game;

import java.util.Scanner;

public class ServiceClass {
    public static Integer TryParseInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    public static int InputProcessing(String text, Scanner in, boolean isPlayerCount) {
        int m = ServiceClass.TryParseInt(in.next());
        if (isPlayerCount) {
            while (m <= 1) {
                System.out.println("Количество " + text + " - должно быть целым положительным числом больше 1. Попробуйте снова: ");
                m = ServiceClass.TryParseInt(in.next());
            }
        } else {
            while (m <= 0) {
                System.out.println("Количество " + text + " - должно быть целым положительным числом. Попробуйте снова: ");
                m = ServiceClass.TryParseInt(in.next());
            }
        }
        return m;
    }

    public static int PlayerProcessing(String text, Scanner in, int count) {
        int m = ServiceClass.TryParseInt(in.next());
        while (m < 0 || m > count) {
            System.out.println("Количество " + text + " - должно быть целым положительным число, не превышающем " + count + " . Попробуйте снова: ");
            m = ServiceClass.TryParseInt(in.next());
        }
        return m;
    }
}
