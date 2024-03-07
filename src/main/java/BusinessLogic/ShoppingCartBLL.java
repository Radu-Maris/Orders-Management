package BusinessLogic;
import DataAccess.ShoppingCartDAO;
import Model.Product;
import Model.ShoppingCart;

import java.util.List;
import java.util.NoSuchElementException;

public class ShoppingCartBLL {
    private ShoppingCartDAO orderDAO;
    public ShoppingCartBLL() {
        orderDAO = new ShoppingCartDAO();
    }

    /**
     *
     * @param order
     * takes a ShoppingCart object,
     * inserts it into the database using orderDAO (ShoppingCartDAO),
     * and returns the inserted ShoppingCart object.
     * If the insertion fails, it prints an error message.
     * @return
     */
    public ShoppingCart insertOrder(ShoppingCart order){
        ShoppingCart st = orderDAO.insert(order);
        if(st==null){
            System.out.println("The insertion did not work!");
        }
        return st;
    }

    /**
     * the function finds all orders stored in the database
     * and stores them in a list of type ShoppingCart
     * @return
     */
    public List<ShoppingCart> findAllOrders(){
        List<ShoppingCart> listaOrder = orderDAO.findAll();
        return listaOrder;
    }

}
