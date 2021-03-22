package ContactList;

import java.sql.Connection;
import java.util.List;

public abstract class ContactDAO <T extends ContactDTO>  {

    protected final Connection connection;

    public ContactDAO(Connection connection){
        super();
        this.connection = connection;
    }

    public abstract T findByID(long id);
    public abstract List<T> findAll();
    public abstract T update(T dto);
    public abstract T create (T dto);
    public abstract void delete(long id);
}
