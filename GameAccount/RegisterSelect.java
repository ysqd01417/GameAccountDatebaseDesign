package GameAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterSelect extends JFrame implements ActionListener {

    JButton JBUserRegister = null;
    JButton JBManuRegister = null;

    JPanel jp1,jp2 = null;
    JPanel jp = null;

    private static DataOperation dataoperation;
    private static NewUser u;
    private static NewManufacturer m;

    public RegisterSelect() {
        dataoperation = new DataOperation();

        JBUserRegister = new JButton("用户注册");
        JBUserRegister.setFont(new Font("宋体", Font.BOLD, 20));
        JBManuRegister = new JButton("厂商注册");
        JBManuRegister.setFont(new Font("宋体", Font.BOLD, 20));

        JBUserRegister.addActionListener(this);
        JBManuRegister.addActionListener(this);

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp = new JPanel();

        jp1.add(JBUserRegister);
        jp1.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp1.setPreferredSize(new Dimension(250, 100));

        jp2.add(JBManuRegister);
        jp2.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp2.setPreferredSize(new Dimension(250, 100));

        jp.setLayout(new GridLayout(2, 1));
        jp.add(jp1);
        jp.add(jp2);

        this.add("South", jp);

        this.setLayout(new BorderLayout());
        this.add(jp, BorderLayout.SOUTH);

        this.setTitle("管理界面");
        this.setSize(350,325);
        this.setLocation(475,125);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("用户注册")) {
            userRegister();
        }else if(e.getActionCommand().equals("厂商注册")){
            manuRegister();
        }
    }

    public void userRegister() {
        String sql="";
        int id=100;
        try{
            sql = "select max(`user_id`) from `user`;";
            dataoperation.connect();
            ResultSet rs = dataoperation.executeQuery(sql);
            if(rs.next()){
                if(id<=rs.getInt(1)){
                    id=rs.getInt(1);
                }
            }
            dataoperation.disconnect();
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,
                    "空集合","错误",JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "发生错误","错误",JOptionPane.ERROR_MESSAGE);
        }finally {
            this.u = new NewUser(id+1);
        }
    }

    public void manuRegister() {
        String sql="";
        int id=1000;
        try{
            sql = "select max(`manufacturer_id`) from `manufacturer`;";
            dataoperation.connect();
            ResultSet rs = dataoperation.executeQuery(sql);
            if(rs.next()){
                if(id<=rs.getInt(1)){
                    id=rs.getInt(1);
                }
            }
            dataoperation.disconnect();
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,
                    "空集合","错误",JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "发生错误","错误",JOptionPane.ERROR_MESSAGE);
        }finally {
            this.m = new NewManufacturer(id+1);
        }
    }

}
