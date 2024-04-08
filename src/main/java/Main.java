import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("На скольких человек необходимо разделить счёт?");
                Scanner s = new Scanner(System.in);
                int human = s.nextInt();
                if (human < 1) {
                    System.out.println("Некорректное значение для подсчета\nВведите корректное значение\n");
                } else if (human == 1) {
                    System.out.println("Сумма поделенная на 1 останется неизменной\n");
                } else if (human > 1) {
                    Calculator calculator = new Calculator();
                    while (true) {
                        System.out.println("Введите название товара или введите 'Завершить 'для завершения");
                        Scanner m = new Scanner(System.in);
                        String name = m.nextLine();
                        if (name.equalsIgnoreCase("Завершить")) {
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
                            break;
                        } else {
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
                }
            } catch (InputMismatchException e) {
                System.out.println("Введите число человек\n");
            }
        }
    }

    static class Product {
        String name;
        double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }
    }

    static class Calculator {
        private ArrayList<Product> products;

        public Calculator() {
            products = new ArrayList<>();
        }

        public void add(Product product) {
            products.add(product);
        }

        public double summa() {
            double sum = 0;
            for (Product product : products) {
                sum += product.getPrice();
            }
            return sum;
        }

        public ArrayList<String> listProducts() {
            ArrayList<String> namePrice = new ArrayList<>();
            for (Product product : products) {
                namePrice.add(product.getName() + ":" + product.getPrice());
            }
            return namePrice;
        }
    }
}