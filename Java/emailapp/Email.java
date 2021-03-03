package emailapp;

public class Email {
    private String firstname;
    private String lastname;
    private String password;
    private String department;
    private final int mailboxCapacity = 500;
    private String email;
    
    public Email(String firstname, String lastname, String department) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department.toLowerCase();
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void createPassword() {
        StringBuilder str = new StringBuilder();
        for(int i=0; i<9; i++) {
            str.append((char)((int)(Math.random()*(126-33)+33)));
        }
        password = str.toString();
    }
    
    
    public String getDepartment() {
        return department;
    }
    
    public String getMailBoxCapacity() {
        return (mailboxCapacity +" MB");
    }
    
    public void createEmail() {
        if(department.equals("n/a")) {
            email = String.format("%s.%s@abc.com", firstname, lastname);
        }else {
            email = String.format("%s.%s@%s.abc.com", firstname, lastname,department);
        }
    }
    
    
}
