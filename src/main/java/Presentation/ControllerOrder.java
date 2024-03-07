package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.ProductBLL;
import BusinessLogic.ShoppingCartBLL;
import Model.Client;
import Model.Product;
import Model.ReflectiveTable;
import Model.ShoppingCart;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControllerOrder {

    private OrderView orderView;

    private String orderId;
    private String clientId;
    private String productId;
    private String orderQuantity;
    private ProductView productView;
    public ControllerOrder(OrderView orderView){
        this.orderView = orderView;
        orderView.addVerificaBackButton(new ControllerOrder.VerificaBack());
        orderView.addVerificaAdd(new ControllerOrder.VerificaAdd());
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
                orderView.setVisible(false);
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
     * then you can add a new Order in the table based on the data writen
     * in the text boxes
     */
    class VerificaAdd implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            orderId = orderView.getTextField1().getText();
            clientId = orderView.getTextField2().getText();
            productId = orderView.getTextField3().getText();
            orderQuantity = orderView.getTextField4().getText();
            try{
                ProductBLL productBLL = new ProductBLL();
                ShoppingCartBLL aux = new ShoppingCartBLL();
                ShoppingCart aux2 = new ShoppingCart(Integer.valueOf(orderId), Integer.valueOf(clientId), Integer.valueOf(productId), Integer.valueOf(orderQuantity));
                if(Integer.valueOf(orderQuantity) > productBLL.findProductById(Integer.valueOf(productId)).getProductStock()) {
                    JOptionPane.showMessageDialog(orderView,"In stock sunt doar " + productBLL.findProductById(Integer.valueOf(productId)).getProductStock());
                }
                else{
                    ShoppingCart clientAux2 = aux.insertOrder(aux2);
                }
                List<ShoppingCart> listaOrder = aux.findAllOrders();
                ReflectiveTable<ShoppingCart> reflectiveTable = new ReflectiveTable<ShoppingCart>();
                reflectiveTable.generateTable(orderView.getTable1(),listaOrder);
            }
            catch (Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }
}
