import java.util.ArrayList;

class Calculator {
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