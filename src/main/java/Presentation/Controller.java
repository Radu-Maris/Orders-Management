package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.ProductBLL;
import Model.Client;
import Model.Product;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller{
    private View view;
    public Controller(View view){
        this.view = view;
        view.addVerificaClientButton(new VerificaClient());
        view.addVerificaProductButton(new VerificaProduct());
        view.addVerificaOrderButton(new VerificaOrder());
        afiseazaBuna();
    }

    /**
     * used to open a pop-up to dysplay the name of the developer
     */
    public void afiseazaBuna(){
        JOptionPane.showMessageDialog(view,"Developed by Maris Radu-Ioan");
    }

    /**
     * the class is used to check the button Client to open the Client edit window
     */
    class VerificaClient implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                ClientBLL x = new ClientBLL();
                Client clientAux = x.findClientById(1);
                System.out.println(clientAux.toString());
                ClientView clientView = new ClientView();
                clientView.setVisible(true);
                view.setVisible(false);
                ControllerClient controllerClient = new ControllerClient(clientView);
            }
            catch (Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }

    /**
     * the class is used to check the button Product to open the Product edit window
     */
    class VerificaProduct implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                ProductBLL y = new ProductBLL();
                Product productAux = y.findProductById(1);
                System.out.println(productAux.toString());
                ProductView productView = new ProductView();
                productView.setVisible(true);
                view.setVisible(false);
                ControllerProduct controllerProduct = new ControllerProduct(productView);
            }
            catch (Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }

    /**
     * the class is used to check the button Order to open the Order edit window
     */
    class VerificaOrder implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                ClientBLL x = new ClientBLL();
                Client clientAux = x.findClientById(1);
                System.out.println(clientAux.toString());
                OrderView orderView = new OrderView();
                orderView.setVisible(true);
                view.setVisible(false);
                ControllerOrder controllerOrder = new ControllerOrder(orderView);
            }
            catch (Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }
}
