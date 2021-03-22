package ContactList;

public class Contact implements ContactDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String notes;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public long getID() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }
}
