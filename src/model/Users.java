package model;

/**
 * This model class creates the constructor, and all of the getters and setters.
 */
public class Users {
    private int userId;
    private String userName;
    private String password;


    /**
     * Basic constructor, using all fields of this model.
     * @param userId The primary key of the Users table.
     * @param userName The userName assigned to the account.
     * @param password The password used to validate the right to use this service.
     */
    public Users(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Get the UserId
     * @return the int value.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Set the UserId.
     * @param userId The ID value to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the userName value.
     * @return The userName.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the userName
     * @param userName The userName value to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Retrieves the Password for value.
     * @return the password value.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password
     * @param password The password value to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
