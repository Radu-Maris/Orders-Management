package Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

public class ReflectiveTable <T>{
    public ReflectiveTable() {

    }

    /**
     *
     * @param table
     * @param list
     * the function generates a table using reflexion in order to dysplay
     * the data from the database in the application
     */
    public void generateTable(JTable table, List<T> list){
        if(list.size()>0) {
            DefaultTableModel tableModel = new DefaultTableModel();
            for (Field field : list.get(0).getClass().getDeclaredFields()) {
                tableModel.addColumn(field.getName());
            }

            for (T t: list) {
                Vector vector = new Vector<>();
                for (Field field : t.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    try{
                        vector.add(field.get(t));
                    }
                    catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                tableModel.addRow(vector);
            }
            table.setModel(tableModel);
        }
    }

}
