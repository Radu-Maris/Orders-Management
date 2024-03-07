package BusinessLogic;
import DataAccess.ClientDAO;
import Model.Client;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private ClientDAO clientDAO;
    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    /**
     *
     * @param id
     * searches for a client with the id given as parameter in the database
     * @return
     */
    public Client findClientById(int id) {
        Client st = clientDAO.findById(id,"id");
        if (st == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     *
     * @param client
     *
     * takes a Client object,
     * inserts it into the database using clientDAO,
     * and returns the inserted Client object.
     * If the insertion fails, it prints an error message.
     *
     * @return
     */
    public Client insertClient(Client client){
        Client st = clientDAO.insert(client);
        if(st==null){
            System.out.println("The insertion did not work!");
        }
        return st;
    }

    /**
     * @param id
     * takes a Client object,
     * deletes it from the database.
     * If the deletion fails, it prints an error message.
     * @return
     */
    public Client deleteClient(int id){
        int st = clientDAO.delete(id);
        if(st==1){
            System.out.println("The deletion of " + id + " did not work!");
        }
        return null;
    }

    /**
     *
     * @param id
     * @param client
     * takes a Client object and an id of the object meant to be edited,
     * then changes the values of the object with given id
     * to the ones form the Client object.
     * If the edit fails, it prints an error message.
     * @return
     */
    public Client updateClient(int id, Client client){
        Client st = clientDAO.update(id,client);
        if(st==null){
            System.out.println("The update of " + id + " did not work!");
        }
        return st;
    }

    /**
     * the function finds all clients stored in the database
     * and stores them in a list of clients
     * @return
     */
    public List<Client> findAllClients(){
        List<Client> listaClienti = clientDAO.findAll();
        return listaClienti;
    }

}
