package sample.database.dataBaseUtil;

import sample.Datamodel.Person;

import java.util.List;

public interface CustomerDAO {
    public List<Person> selectAll();
}
