package Presentation;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View extends JFrame{
    private JPanel panel1;
    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;

    public void setDimension(int w, int h){
        add(panel1);
        setBounds(300,200,w,h);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public View(){
        setDimension(600,300);
    }

    public void addVerificaClientButton(ActionListener listener){this.clientButton.addActionListener(listener);}

    public void addVerificaProductButton(ActionListener listener){this.productButton.addActionListener(listener);}

    public void addVerificaOrderButton(ActionListener listener){this.orderButton.addActionListener(listener);}
}
