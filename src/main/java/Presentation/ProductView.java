package Presentation;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ProductView extends JFrame{
    private JPanel panel1;
    private JTextField textField1;
    private JTable table1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton addButton;
    private JButton editButton;
    private JButton findByIdButton;
    private JButton deleteButton;
    private JButton backButton;

    public void setDimension(int w, int h){
        add(panel1);
        setBounds(300,200,w,h);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public ProductView(){
        setDimension(600,300);
    }
    public JTextField getTextField1(){return textField1;}
    public JTextField getTextField2(){return textField2;}
    public JTextField getTextField3(){return textField3;}
    public JTextField getTextField4(){return textField4;}

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public void addVerificaBackButton(ActionListener listener){this.backButton.addActionListener(listener);}
    public void addVerificaAdd(ActionListener listener){this.addButton.addActionListener(listener);}
    public void addVerificaEditButton(ActionListener listener){this.editButton.addActionListener(listener);}
    public void addVerificaDeleteButton(ActionListener listener){this.deleteButton.addActionListener(listener);}
    public void addVerificaFindByIdButton(ActionListener listener){this.findByIdButton.addActionListener(listener);}

}
