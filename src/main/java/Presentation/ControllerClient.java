package Presentation;

import BusinessLogic.ClientBLL;
import Model.Client;
import Model.ReflectiveTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControllerClient {

    private ClientView clientView;
    private String clientId;
    private String clientName;
    private String clientAge;
    private String clientCountry;

    public ControllerClient(ClientView clientView){
        this.clientView = clientView;
        clientView.addVerificaBackButton(new VerificaBack());
        clientView.addVerificaAddButton(new VerificaAdd());
        clientView.addVerificaDeleteButton(new VerificaDelete());
        clientView.addVerificaFindByIdButton(new VerificaFindById());
        clientView.addVerificaEditButton(new VerificaEdit());
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
                clientView.setVisible(false);
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
     * then you can add a new Client in the table based on the data writen
     * in the text boxes
     */
    class VerificaAdd implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            clientId = clientView.getTextField1().getText();
            clientName = clientView.getTextField2().getText();
            clientAge = clientView.getTextField3().getText();
            clientCountry = clientView.getTextField4().getText();
            try{
                ClientBLL aux = new ClientBLL();
                Client aux2 = new Client(Integer.valueOf(clientId),clientName,Integer.valueOf(clientAge),clientCountry);
                Client clientAux2 = aux.insertClient(aux2);

                List<Client> listaClient = aux.findAllClients();
                ReflectiveTable<Client> reflectiveTable = new ReflectiveTable<Client>();
                reflectiveTable.generateTable(clientView.getTable1(),listaClient);

            }
            catch (Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }

    /**
     * the class checks if the button Delete is pressed and if it is
     * then you can delete a Client from the table based on the id written
     * in the text boxes
     */
    class VerificaDelete implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            clientId = clientView.getTextField1().getText();
            try{
                ClientBLL aux = new ClientBLL();
                Client clientAux2 = aux.deleteClient(Integer.valueOf(clientId));

                List<Client> listaClient = aux.findAllClients();
                ReflectiveTable<Client> reflectiveTable = new ReflectiveTable<Client>();
                reflectiveTable.generateTable(clientView.getTable1(),listaClient);

            }
            catch (Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }

    /**
     * the class checks if the button FindById is pressed and if it is
     * then a pop-up apperes and shows the data of the Client with the given id
     */
    class VerificaFindById implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            clientId = clientView.getTextField1().getText();
            try{
                ClientBLL aux = new ClientBLL();
                Client clientAux2 = aux.findClientById(Integer.valueOf(clientId));
                JOptionPane.showMessageDialog(clientView,"The searched client is: id = " + aux.findClientById(Integer.valueOf(clientId)).getId() + " | name = " + aux.findClientById(Integer.valueOf(clientId)).getClientName() + " | age = " + aux.findClientById(Integer.valueOf(clientId)).getClientAge() + " | country = " + aux.findClientById(Integer.valueOf(clientId)).getClientCountry());

                List<Client> listaClient = aux.findAllClients();
                ReflectiveTable<Client> reflectiveTable = new ReflectiveTable<Client>();
                reflectiveTable.generateTable(clientView.getTable1(),listaClient);

            }
            catch (Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }

    /**
     * the class checks if the button Edit is pressed and if it is
     * then you can edit an existing Client in the table based on the data written
     * in the text boxes
     */
    class VerificaEdit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            clientId = clientView.getTextField1().getText();
            clientName = clientView.getTextField2().getText();
            clientAge = clientView.getTextField3().getText();
            clientCountry = clientView.getTextField4().getText();
            try{
                ClientBLL aux = new ClientBLL();
                Client aux2 = new Client(Integer.valueOf(clientId),clientName,Integer.valueOf(clientAge),clientCountry);
                Client clientAux2 = aux.updateClient(Integer.valueOf(clientId),aux2);

                List<Client> listaClient = aux.findAllClients();
                ReflectiveTable<Client> reflectiveTable = new ReflectiveTable<Client>();
                reflectiveTable.generateTable(clientView.getTable1(),listaClient);

            }
            catch (Exception event){
                event.printStackTrace();
                System.out.println("Error!");
            }
        }
    }

}
