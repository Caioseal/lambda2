package application;

import entities.Product;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = scanner.nextLine();

        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();

        List<Product> productList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] fields = line.split(";");
                productList.add(new Product(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = bufferedReader.readLine();
            }

            System.out.println("Email of people whose salary is greater than " + String.format("R$%.2f", salary));

            Comparator<String> stringComparator = Comparator.comparing(String::toLowerCase);
            List<String> emailList = productList.stream().filter(p -> p.getSalary() > salary).map(Product::getEmail).sorted(stringComparator).toList();
            emailList.forEach(System.out::println);

            double sum = productList.stream().filter(p -> p.getName().charAt(0) == 'M').map(Product::getSalary).reduce(0.0, Double::sum);
            System.out.print("Sum of salary of people whose name starts with the letter 'M': " + String.format("R$%.2f", sum));

        } catch (IOException e) {
            e.printStackTrace();

        }
        scanner.close();
    }
}
