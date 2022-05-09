package model;

public class Appointments {
    private  int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private String Start;       // datetime
    private String End;         // datetime
    private String Create_Date; // datetime
    private String Created_By;
    private String Last_Update; // timestamp
    private String Last_Updated_By;
    private int Customer_ID;
    private int User_ID;
    private int Contact_ID;

    /**
     * Constructor for the Appointments model
     * @param appointment_ID
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param create_Date
     * @param created_By
     * @param last_Update
     * @param last_Updated_By
     * @param customer_ID
     * @param user_ID
     * @param contact_ID
     */
    public Appointments(int appointment_ID, String title, String description, String location, String type, String start, String end, String create_Date, String created_By, String last_Update, String last_Updated_By, int customer_ID, int user_ID, int contact_ID) {
        Appointment_ID = appointment_ID;
        Title = title;
        Description = description;
        Location = location;
        Type = type;
        Start = start;
        End = end;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
        Customer_ID = customer_ID;
        User_ID = user_ID;
        Contact_ID = contact_ID;
    }

    /**
     * Get the ID value for the Appointment.
     * @return the ID.
     */
    public int getAppointment_ID() {
        return Appointment_ID;
    }

    /**
     * returns the Appointment_ID value.
     * @param appointment_ID
     */
    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }

    /**
     * Get the title of the appointment.
     * @return the title.
     */
    public String getTitle() {
        return Title;
    }

    /**
     * Set the title of the appointment.
     * @param title of the appointment.
     */
    public void setTitle(String title) {
        Title = title;
    }

    /**
     * Get the description of the appointment.
     * @return the description.
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Set the description of the appointment.
     * @param description of the appointment.
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     * Get the location of the appointment.
     * @return the location.
     */
    public String getLocation() {
        return Location;
    }

    /**
     * Set the location of the appointment.
     * @param location of the appointment.
     */
    public void setLocation(String location) {
        Location = location;
    }

    /**
     * Get the type of appointment.
     * @return the type.
     */
    public String getType() {
        return Type;
    }

    /**
     * Set the type of appointment.
     * @param type of appointment.
     */
    public void setType(String type) {
        Type = type;
    }

    /**
     * Get the Start Date and Time of the appointment.
     * @return the time specified.
     */
    public String getStart() {
        return Start;
    }

    /**
     * Set the Start Date and Time of the appointment.
     * @param start - the UTC time of the appointment.
     */
    public void setStart(String start) {
        Start = start;
    }

    /**
     * Get the End Date and Time of the appointment.
     * @return the time specified.
     */
    public String getEnd() {
        return End;
    }

    /**
     * Set the End Date and Time of the appointment.
     * @param end the UTC time of the appointment.
     */
    public void setEnd(String end) {
        End = end;
    }

    /**
     * Get the audit column, record create date.
     * @return the date.
     */
    public String getCreate_Date() {
        return Create_Date;
    }

    /**
     * Set the audit column, record create date.
     * @param create_Date the date.
     */
    public void setCreate_Date(String create_Date) {
        Create_Date = create_Date;
    }

    /**
     * Get the audit column, created by.
     * @return the initial author.
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     * Set the audit column, created by.
     * @param created_By The initial author.
     */
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    /**
     * Get the audit column, last update date.
     * @return the date.
     */
    public String getLast_Update() {
        return Last_Update;
    }

    /**
     * Set the audit column, last update date.
     * @param last_Update The date.
     */
    public void setLast_Update(String last_Update) {
        Last_Update = last_Update;
    }

    /**
     * Get the audit column, updated by.
     * @return The change author.
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * Set the audit column, last updated by.
     * @param last_Updated_By The change author.
     */
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    /**
     * Get the primary key value, customer id.
     * @return the customer id.
     */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /**
     * Set the primary key value, customer id. (auto increment is taking care of this)
     * @param customer_ID The primary key sequence.
     */
    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    /**
     * Get the User ID.
     * @return The User Id.
     */
    public int getUser_ID() {
        return User_ID;
    }

    /**
     * Set the User ID value.
     * @param user_ID The User Id.
     */
    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    /**
     * Get the Contact ID.
     * @return the Contact ID.
     */
    public int getContact_ID() {
        return Contact_ID;
    }

    /**
     * Set the Contact ID.
     * @param contact_ID The employee being met with.
     */
    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }
}

