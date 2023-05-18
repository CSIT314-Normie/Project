package Main.Controller.Admin;


import Main.Entity.*;


import java.util.*;

/*
 * This controller handles updating of account info
 * - update of own account info - all USERS
 */

public class AdminUpdateAccountController {
    private final User user = new User();

    /**
     * To handle the update of account information - USER 
     * @param updatedInfo arraylist of updated account information
     * @param email email of the user to be updated
     * @return boolean true if account information is updated successfully, false otherwise
     */
    public boolean updateAccount(ArrayList<String> updatedInfo, String email) {
        return user.updateAcc(updatedInfo, email);
    }
}
