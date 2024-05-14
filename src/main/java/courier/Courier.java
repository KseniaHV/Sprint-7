package courier;

public class Courier {
    private String login;
    private String password;
    private String firstName;
    public Courier() {
    }
    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public static Courier createdCourier() {
        Courier courier = new Courier("Tufan63", "0988", "Vasilievich");
        return courier;
    }
    public static Courier duplicateCourier() {
        Courier doubleCourier = new Courier("Tufan63", "1234", "Dmitrievich");
        return doubleCourier;
    }
    public static Courier noRequiredField() {
        Courier withoutLogin = new Courier(null, "0988", "Vasilievich");
        return withoutLogin;
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
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
