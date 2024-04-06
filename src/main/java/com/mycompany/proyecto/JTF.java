package com.mycompany.proyecto;

import javax.swing.JTextField;

public class JTF {
    
    private JTextField jtf;
    
    public JTF(JTextField jtf)
    {
        this.jtf=jtf;
    }
    
    public synchronized void setJtf(String t)
    {
        jtf.setText(t);
    }
    
    public synchronized String getJtf()
    {
        return jtf.getText();
    }
}