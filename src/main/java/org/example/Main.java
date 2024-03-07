package org.example;
import BusinessLogic.ClientBLL;
import BusinessLogic.ProductBLL;
import Model.Client;
import Model.Product;
import Presentation.Controller;
import Presentation.View;

public class Main {
    public static void main(String[] args) {
        ClientBLL x = new ClientBLL();
        Client clientAux = x.findClientById(1);
        System.out.println(clientAux.toString());

        ProductBLL y = new ProductBLL();
        Product productAux = y.findProductById(1);
        System.out.println(productAux.toString());

        View view = new View();
        Controller controller = new Controller(view);
        view.setVisible(true);

    }
}