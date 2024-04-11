import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Main prilozhenie = new Main();
        prilozhenie.go();
    }

    public void go() {
        while (true) {
            int human = amountOfPeople();
            if (human < 1) {
                System.out.println("Некорректное значение для подсчета\nВведите корректное значение\n");
            } else if (human == 1) {
                System.out.println("Сумма поделенная на 1 останется неизменной\n");
            } else if (human > 1) {
                addOrCheck(human);
            }
        }
    }

    private int amountOfPeople() {
        try {
            System.out.println("На скольких человек необходимо разделить счёт?");
            Scanner s = new Scanner(System.in);
            return s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Введите число человек\n");
            return -1;
        }
    }

    private void addOrCheck(int human) {
        Calculator calculator = new Calculator();
        while (true) {
            System.out.println("Введите название товара или введите 'Завершить 'для завершения");
            Scanner m = new Scanner(System.in);
            String name = m.nextLine();
            if (name.equalsIgnoreCase("Завершить")) {
                printCheckAndCalculateSum(human, calculator);
                break;
            } else {
                addProductToCalculator(name, calculator);
            }
        }
    }

    private void printCheckAndCalculateSum(int human, Calculator calculator) {
        System.out.println("Добавленные товары:");
        ArrayList<String> check = calculator.listProducts();
        for (String product : check) {
            System.out.println(product);
        }
        double sumOfCheck = calculator.summa();
        double sumForPeople = sumOfCheck / human;
        int lastDigit = (int) (sumForPeople % 10);
        switch (lastDigit) {
            case 1:
                System.out.printf("\nСумма, которую должен заплатить каждый человек: %.2f рубль\n", sumForPeople);
                break;
            case 2:
            case 3:
            case 4:
                System.out.printf("\nСумма, которую должен заплатить каждый человек: %.2f рубля\n", sumForPeople);
                break;
            default:
                System.out.printf("\nСумма, которую должен заплатить каждый человек: %.2f рублей\n", sumForPeople);
        }
    }

    private void addProductToCalculator(String name, Calculator calculator) {
        try {
            System.out.println("Введите цену товара в виде (рубли.копейки)");
            Scanner n = new Scanner(System.in);
            double price = Double.parseDouble(n.next());
            Product product = new Product(name, price);
            calculator.add(product);
            System.out.println("Товар успешно добавлен в калькулятор\n");
        } catch (NumberFormatException exception) {
            System.out.println("Введите число типа double\n");
        }
    }
}