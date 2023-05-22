package Main.Controller.Customer;


import Main.Entity.*;


import java.util.*;


public class CustomerLoginController {
    private User user;
    private String email;
    private String password;

    /**
     * This constructor is used to create a LoginController object
     * @param email is the email of the user
     * @param password is the password of the user
     */
    public CustomerLoginController(String email, String password) {
        this.user = new User();
        this.email = email;
        this.password = password;
    }

    /**
     * This constructor is used to create a LoginController object with the user's role
     * @param userRole is the role of the user
     * @param canLogin is a String that indicates whether the user can log in or not
     * @param email is the email of the user
     */
    public CustomerLoginController(String userRole, String canLogin, String email) {
        if (canLogin.equals("T")) {
            this.user = new User();
            ArrayList<String> userInfo = this.user.getDB().select("*", email);
            this.user = new Customer(userInfo.get(0), userInfo.get(1), email, userInfo.get(3), userInfo.get(4), userRole);
        }
    }

    /**
     * This method is used to log in a user
     * @return an ArrayList of Strings that contains the user's role, whether the user can log in or not, and the user's email
     */
    public ArrayList<String> login() { 
        String userRole = "Customer";
        String canLogin = "F";
        String userEmail ="";     

        // login customer, if successful, set canLogin to "T" 
        if (this.user.login(this.email, this.password, userRole)) {
            canLogin = "T";
            userEmail = this.email;
        }  

        return new ArrayList<>(Arrays.asList(userRole, canLogin, userEmail)); 
    }
}

