package Presentation;

import BusinessLogic.ProductBLL;
import Model.Product;
import Model.ReflectiveTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControllerProduct {
    private ProductView productView;
    private String productId;
    private String productName;
    private String produtPrice;
    private String productStock;

    public ControllerProduct(ProductView productView){
        this.productView = productView;
        productView.addVerificaBackButton(new ControllerProduct.VerificaBack());
        productView.addVerificaAdd(new VerificaAdd());
        productView.addVerificaDeleteButton(new VerificaDelete());
        productView.addVerificaFindByIdButton(new VerificaFindById());
        productView.addVerificaEditButton(new VerificaEdit());
    }
    /**
     * the class checks if the button Back is pressed and if it is
     * then you go back to the Main window
     */
    class VerificaBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                View view = new View();
                view.setVisible(true);
                productView.setVisible(false);
                Controller controller = new Controller(view);
            }
            catch (Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }
    /**
     * the class checks if the button Add is pressed and if it is
     * then you can add a new Product in the table based on the data writen
     * in the text boxes
     */
    class VerificaAdd implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            productId = productView.getTextField1().getText();
            productName = productView.getTextField2().getText();
            produtPrice = productView.getTextField3().getText();
            productStock = productView.getTextField4().getText();
            try{
                ProductBLL aux = new ProductBLL();
                Product aux2 = new Product(Integer.valueOf(productId),productName,Integer.valueOf(produtPrice),Integer.valueOf(productStock));
                Product clientAux2 = aux.insertProduct(aux2);

                List<Product> listaProduct = aux.findAllProducts();
                ReflectiveTable<Product> reflectiveTable = new ReflectiveTable<Product>();
                reflectiveTable.generateTable(productView.getTable1(),listaProduct);

            }
            catch (Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }
    /**
     * the class checks if the button Delete is pressed and if it is
     * then you can delete a Product from the table based on the id written
     * in the text boxes
     */
    class VerificaDelete implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            productId = productView.getTextField1().getText();
            try{
                ProductBLL aux = new ProductBLL();
                Product productAux2 = aux.deleteProduct(Integer.valueOf(productId));

                List<Product> listaProduct = aux.findAllProducts();
                ReflectiveTable<Product> reflectiveTable = new ReflectiveTable<Product>();
                reflectiveTable.generateTable(productView.getTable1(),listaProduct);
            }
            catch (Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }
    /**
     * the class checks if the button FindById is pressed and if it is
     * then a pop-up apperes and shows the data of the Product with the given id
     */
    class VerificaFindById implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            productId = productView.getTextField1().getText();
            try {
                ProductBLL aux = new ProductBLL();
                Product productAux2 = aux.findProductById(Integer.valueOf(productId));
                JOptionPane.showMessageDialog(productView, "The searched Product is: id = " + aux.findProductById(Integer.valueOf(productId)).getId() + " | name = " + aux.findProductById(Integer.valueOf(productId)).getProductName() + " | price = " + aux.findProductById(Integer.valueOf(productId)).getProductPrice() + " | Stock = " + aux.findProductById(Integer.valueOf(productId)).getProductStock());

                List<Product> listaProduct = aux.findAllProducts();
                ReflectiveTable<Product> reflectiveTable = new ReflectiveTable<Product>();
                reflectiveTable.generateTable(productView.getTable1(), listaProduct);
            } catch (Exception event) {
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }
    /**
     * the class checks if the button Edit is pressed and if it is
     * then you can edit an existing Product in the table based on the data written
     * in the text boxes
     */
        class VerificaEdit implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e){
                productId = productView.getTextField1().getText();
                productName = productView.getTextField2().getText();
                produtPrice = productView.getTextField3().getText();
                productStock = productView.getTextField4().getText();
                try{
                    ProductBLL aux = new ProductBLL();
                    Product aux2 = new Product(Integer.valueOf(productId),productName,Integer.valueOf(produtPrice),Integer.valueOf(productStock));
                    Product clientAux2 = aux.updateProduct(Integer.valueOf(productId),aux2);

                    List<Product> listaProduct = aux.findAllProducts();
                    ReflectiveTable<Product> reflectiveTable = new ReflectiveTable<Product>();
                    reflectiveTable.generateTable(productView.getTable1(),listaProduct);
                }
                catch (Exception event){
                    event.printStackTrace();
                    System.out.println("Error!");
                }
            }
        }
}
