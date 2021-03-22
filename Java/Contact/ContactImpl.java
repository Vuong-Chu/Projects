package ContactList;

import java.sql.Connection;
import java.util.List;

public class ContactImpl extends ContactDAO<Contact>{

    public ContactImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Contact findByID(long id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Contact update(Contact dto) {
        return null;
    }

    @Override
    public Contact create(Contact dto) {
        return null;
    }


    @Override
    public void delete(long id) {

    }


}
