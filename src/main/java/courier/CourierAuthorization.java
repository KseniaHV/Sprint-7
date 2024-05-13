package courier;

public class CourierAuthorization {
    private String login;
    private String password;
    public CourierAuthorization() {
    }
    public CourierAuthorization(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public static CourierAuthorization from(Courier courier){
        return new CourierAuthorization(courier.getLogin(), courier.getPassword());
    }
    public static CourierAuthorization wrongPassword() {
        CourierAuthorization credentials = new CourierAuthorization("Tufan63", "password123");
        return credentials;
    }
    public static CourierAuthorization missedLogin() {
        CourierAuthorization autho = new CourierAuthorization(null, "0988");
        return autho;
    }
    public static CourierAuthorization invalidLogin() {
        CourierAuthorization autho = new CourierAuthorization("Tufan01", "0988");
        return autho;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
