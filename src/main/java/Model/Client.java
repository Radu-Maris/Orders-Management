package Model;
public class Client {
    private int id;
    private String clientName;
    private int clientAge;
    private String clientCountry;
    public Client(){
        super();
    }
    public Client(int clientId, String clientName, int clientAge, String clientCountry) {
        super();
        this.id = clientId;
        this.clientName = clientName;
        this.clientAge = clientAge;
        this.clientCountry = clientCountry;
    }
    public Client(String clientName, int clientAge, String clientCountry) {
        super();
        this.clientName = clientName;
        this.clientAge = clientAge;
        this.clientCountry = clientCountry;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    public int getClientAge() {
        return clientAge;
    }
    public void setClientAge(int clientAge) {
        this.clientAge = clientAge;
    }
    public String getClientCountry() {
        return clientCountry;
    }
    public void setClientCountry(String clientCountry) {
        this.clientCountry = clientCountry;
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + clientName + ", age=" + clientAge + ", country=" + clientCountry
                + "]";
    }

}