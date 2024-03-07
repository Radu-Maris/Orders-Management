package BusinessLogic;
import DataAccess.ProductDAO;
import Model.Product;
import java.util.List;
import java.util.NoSuchElementException;


public class ProductBLL {
    private ProductDAO productDAO;

    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    /**
     *
     * @param id
     * searches for a product with the id given as parameter in the database
     * @return
     */
    public Product findProductById(int id) {
        Product st = productDAO.findById(id,"id");
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     *
     * @param product
     * takes a Product object,
     * inserts it into the database using productDAO,
     * and returns the inserted Product object.
     * If the insertion fails, it prints an error message.
     * @return
     */
    public Product insertProduct(Product product){
        Product st = productDAO.insert(product);
        if(st==null){
            System.out.println("The insertion did not work!");
        }
        return st;
    }

    /**
     *
     * @param id
     * takes a Product object,
     * deletes it from the database.
     * If the deletion fails, it prints an error message.
     * @return
     */
    public Product deleteProduct(int id){
        int st = productDAO.delete(id);
        if(st==1){
            System.out.println("The deletion of " + id + " did not work!");
        }
        return null;
    }

    /**
     *
     * @param id
     * @param product
     * takes a Product object and an id of the object meant to be edited,
     * then changes the values of the object with given id
     * to the ones form the Product object.
     * If the edit fails, it prints an error message.
     * @return
     */
    public Product updateProduct(int id, Product product){
        Product st = productDAO.update(id,product);
        if(st==null){
            System.out.println("The update of " + id + " did not work!");
        }
        return st;
    }

    /**
     * the function finds all products stored in the database
     * and stores them in a list of products
     * @return
     */
    public List<Product> findAllProducts(){
        List<Product> listaProduct = productDAO.findAll();
        return listaProduct;
    }

}
