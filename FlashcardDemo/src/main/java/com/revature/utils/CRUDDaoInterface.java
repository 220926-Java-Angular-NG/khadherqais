package com.revature.utils;
import java.util.List;
public interface CRUDDaoInterface<T> {
    // DAO- Data access object
    // a pattern that provides an abstract interface to
    // some type of database or other persistence mechanism
    // we are returning  an int because we are  returning the primary key of this newly added row
   // all of these below down method for this interface must be implementing by class implement this interface (my note)
    int create(T t);

    List<T> getAll();

    T getById(int id);
    T update(T t);


    boolean delete(T t);


}
