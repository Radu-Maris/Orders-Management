package DataAccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     *
     * @param field
     * the function generates a SQL select query string
     * based on a given field.
     * @return String
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * the function generates a SQL select all query string
     *
     * @return String
     */
    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     *
     * @param field1
     * @param field2
     * @param field3
     * @param field4
     * the function generates a SQL insert query string
     * based on 4 fields (one for each collomn)
     *
     * @return String
     */
    private String insertQuery(String field1, String field2, String field3, String field4) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" VALUES (" + formatValue(field1) + "," + formatValue(field2) + "," + formatValue(field3) + "," + formatValue(field4));
        sb.append(")");
        return sb.toString();
    }

    /**
     * @param id
     * the function generates a SQL delete query string
     * based on a given id.
     * @return String
     */
    private String deleteQuery(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id = " + id);
        return sb.toString();
    }

    /**
     *
     * @param id
     * @param field1
     * @param field2
     * @param field3
     * @param colomn1
     * @param colomn2
     * @param colomn3
     *
     * the function generates a SQL edit/update query string
     * based on a given id and replaces the data in the collumns
     * @return String
     */
    private String updateQuery(int id,String field1, String field2, String field3,String colomn1, String colomn2, String colomn3) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET " + colomn1 + "=" + formatValue(field1) + "," + colomn2 + "=" + formatValue(field2) + "," + colomn3 + "=" + formatValue(field3));
        sb.append(" WHERE id = " + id);
        return sb.toString();
    }

    /**
     * @param value
     * the formats the object given as parameter to a String that can be
     * used in other querys
     * @return String
     */
    private String formatValue(Object value) {
        if (value == null) {
            return "NULL";
        } else if (value instanceof String || value instanceof Character) {
            return "'" + value.toString() + "'";
        } else {
            return value.toString();
        }
    }

    /**
     * the function finds all data stored in a certain table
     * @return
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     *
     * @param id
     * @param fieldName
     *
     * the function finds a certain object based it's id given as parameter
     * @return
     */
    public T findById(int id,String fieldName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(fieldName);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     *
     * @param resultSet
     * The createObjects function takes a ResultSet as input and returns a list of objects.
     * It uses reflection to dynamically create objects of type T with a parameterless constructor.
     * It retrieves field values from the ResultSet and assigns them to the corresponding object fields using reflection.
     * The populated objects are added to a list, which is returned at the end.
     * @return list
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @param t
     * the function is responsible for inserting an object in a table
     * @return t
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Object> list = new ArrayList<>();
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(t);
                list.add(value);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        String query = "";

        String field1 = list.get(0).toString();
        String field2 = list.get(1).toString();
        String field3 = list.get(2).toString();
        String field4 = list.get(3).toString();

        query = insertQuery(field1,field2,field3,field4);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     *
     * @param id
     * the function is responsible for deleting an object from the table
     * based on it's id given as parameter
     * @return
     */
    public int delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = deleteQuery(id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

            return 0;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    /**
     *
     * @param id
     * @param t
     * the function is used to update an entry in a table
     * the entry that is meant to be edited is found by id given as parameter
     * and then updats the fields
     * @return
     */
    public T update(int id,T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Object> list = new ArrayList<>();
        List<String> list2 = new ArrayList<String>();
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(t);
                list.add(value);
                list2.add(field.getName());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        String query = "";
        String field2,field3,field4;
        String colomn2,colomn3,colomn4;

        field2 = list.get(1).toString();
        field3 = list.get(2).toString();
        field4 = list.get(3).toString();

        colomn2 = list2.get(1);
        colomn3 = list2.get(2);
        colomn4 = list2.get(3);

        query = updateQuery(id,field2,field3,field4,colomn2,colomn3,colomn4);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }
}
