package journal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class CuratorJournal {
    private static final ArrayList<StudentRecord> journal = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nМеню:");
            System.out.println("1. Додати запис");
            System.out.println("2. Показати всі записи");
            System.out.println("3. Вийти");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addRecord();
                case "2" -> showRecords();
                case "3" -> {
                    running = false;
                    System.out.println("Роботу завершено.");
                }
                default -> System.out.println("Неправильний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static void addRecord() {
        String lastName = readNonEmptyString("Введіть прізвище студента: ");
        String firstName = readNonEmptyString("Введіть ім'я студента: ");
        LocalDate birthDate = readDate("Введіть дату народження (yyyy-MM-dd): ");
        String phone = readPhone("Введіть телефон у форматі +380XXXXXXXXX: ");
        String address = readNonEmptyString("Введіть адресу (вулиця, будинок, квартира): ");

        StudentRecord record = new StudentRecord(lastName, firstName, birthDate, phone, address);
        journal.add(record);
        System.out.println("✅ Запис успішно додано!");
    }

    private static void showRecords() {
        if (journal.isEmpty()) {
            System.out.println("Журнал порожній.");
            return;
        }
        System.out.println("\nВсі записи:");
        for (StudentRecord record : journal) {
            System.out.println(record);
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Поле не може бути порожнім. Спробуйте ще раз.");
        }
    }

    private static LocalDate readDate(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Неправильний формат дати. Використайте yyyy-MM-dd.");
            }
        }
    }

    private static String readPhone(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches("\\+380\\d{9}")) return input;
            System.out.println("Неправильний формат телефону. Приклад: +380XXXXXXXXX");
        }
    }
}
