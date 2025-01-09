package GameAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManufacturerMessage extends JFrame implements ActionListener {

    JLabel jlManu = null;
    JLabel jlText = null;
    JLabel jlmanuid = null;
    JLabel jlmanuname = null;
    JLabel jlmanupw1 = null;
    JLabel jlmanupw2 = null;
    JLabel jlmanumail = null;
    JLabel jlmanuphone = null;
    JLabel jlmanuaddress = null;
    JLabel jlmanustatus = null;
    JTextField jtfmanuid = null;
    JTextField jtfmanuname = null;
    JPasswordField jpfmanupw1 = null;
    JPasswordField jpfmanupw2 = null;
    JTextField jtfmanumail = null;
    JTextField jtfmanuphone = null;
    JTextField jtfmanuaddress = null;
    JTextField jtfmanustatus = null;

    JButton jbmanuupdateInfo = null;
    JButton jbmanuupdatepw = null;

    JPanel jp1, jp2, jp3, jp4, jp5 = null;
    JPanel jp = null;

    JScrollPane ManuJScrollPane = null;

    private static DataOperation dataOperation ;

    public ManufacturerMessage(ResultSet rs) {
        try{
            jlManu = new JLabel("厂商信息");
            jlManu.setFont(new Font("宋体", Font.BOLD, 25));
            jlText = new JLabel("注意：更新信息需要填写上方所有带*的内容，修改密码只需要输入两次新密码即可！");
            jlText.setFont(new Font("宋体", Font.BOLD, 15));
            jlText.setForeground(Color.RED);
            jlmanuid = new JLabel("账号*");
            jlmanupw1 = new JLabel("新密码*");
            jlmanupw2 = new JLabel("确认新密码*");
            jlmanuname = new JLabel("名称*");
            jlmanumail = new JLabel("邮箱*");
            jlmanuphone = new JLabel("电话*");
            jlmanuaddress = new JLabel("地址*");
            jlmanustatus = new JLabel("状态");

            jtfmanuid = new JTextField(15);
            jtfmanuid.setEditable(false);
            jtfmanuid.setText(rs.getString(1));
            jpfmanupw1 = new JPasswordField(15);
            jpfmanupw2 = new JPasswordField(15);

            jtfmanuname = new JTextField(15);
            jtfmanuname.setText(rs.getString(3));

            jtfmanumail = new JTextField(15);
            jtfmanumail.setText(rs.getString(4));

            jtfmanuphone = new JTextField(15);
            jtfmanuphone.setText(rs.getString(5));

            jtfmanuaddress = new JTextField(15);
            jtfmanuaddress.setText(rs.getString(6));

            jtfmanustatus = new JTextField(15);
            jtfmanustatus.setEditable(false);
            jtfmanustatus.setText(rs.getString(7));

            jbmanuupdateInfo = new JButton("更新信息");
            jbmanuupdateInfo.setFont(new Font("宋体", Font.BOLD, 20));
            jbmanuupdatepw = new JButton("修改密码");
            jbmanuupdatepw.setFont(new Font("宋体", Font.BOLD, 20));

            jbmanuupdateInfo.addActionListener(this);
            jbmanuupdatepw.addActionListener(this);

            ManuJScrollPane = new JScrollPane();

            jp1 = new JPanel();
            jp2 = new JPanel();
            jp3 = new JPanel();
            jp4 = new JPanel();
            jp5 = new JPanel();
            jp = new JPanel();

            jp1.add(jlManu);
            jp1.setLayout(new FlowLayout(FlowLayout.CENTER));
            jp1.setPreferredSize(new Dimension(20, 85));

            jp2.add(jlmanuid);
            jp2.add(jtfmanuid);
            jp2.add(jlmanuname);
            jp2.add(jtfmanuname);
            jp2.add(jlmanumail);
            jp2.add(jtfmanumail);
            jp2.setLayout((new FlowLayout(FlowLayout.CENTER)));
            jp2.setPreferredSize(new Dimension(20, 85));

            jp3.add(jlmanuphone);
            jp3.add(jtfmanuphone);
            jp3.add(jlmanuaddress);
            jp3.add(jtfmanuaddress);
            jp3.add(jlmanustatus);
            jp3.add(jtfmanustatus);
            jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
            jp3.setPreferredSize(new Dimension(20, 85));

            jp4.add(jlText);
            jp4.add(jbmanuupdateInfo);
            jp4.setLayout(new FlowLayout(FlowLayout.CENTER));
            jp4.setPreferredSize(new Dimension(20, 85));

            jp5.add(jlmanupw1);
            jp5.add(jpfmanupw1);
            jp5.add(jlmanupw2);
            jp5.add(jpfmanupw2);
            jp5.add(jbmanuupdatepw);
            jp5.setLayout(new FlowLayout(FlowLayout.CENTER));
            jp5.setPreferredSize(new Dimension(20, 85));

            jp.setLayout(new GridLayout(5,1));
            jp.add(jp1);
            jp.add(jp2);
            jp.add(jp3);
            jp.add(jp4);
            jp.add(jp5);

            this.add("North", ManuJScrollPane);
            this.add("South", jp);

            this.setTitle("厂商信息查询修改");
            this.setSize(850,500);
            this.setLocation(450,100);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setVisible(true);
            this.setResizable(false);

        }catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null,
                    "你输入的账号不存在，请重新输入！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("更新信息")
                &&!jlmanuname.getText().isEmpty()
                &&!jlmanumail.getText().isEmpty()
                &&!jlmanuphone.getText().isEmpty()
                &&!jlmanuaddress.getText().isEmpty()){
            updataProcess();
        }else if(e.getActionCommand().equals("修改密码")
                &&!String.valueOf(jpfmanupw1.getPassword()).equals("")
                &&!String.valueOf(jpfmanupw2.getPassword()).equals("")){
            String spwd1=new String(jpfmanupw1.getPassword());
            String spwd2=new String(jpfmanupw2.getPassword());
            if(spwd1.equals(spwd2)){
                changePwProcess(spwd1);
            }else{
                JOptionPane.showMessageDialog(null,
                        "两次密码不一致，请确认后在输入！","提示",JOptionPane.ERROR_MESSAGE);
            }
            jpfmanupw1.setText("");
            jpfmanupw2.setText("");
        }

    }

    public void updataProcess(){
        String manuid = jlmanuid.getText().trim();
        String manuname = jtfmanuname.getText().trim();
        String manumail = jtfmanumail.getText().trim();
        String manuphone = jtfmanuphone.getText().trim();
        String manuaddress = jtfmanuaddress.getText().trim();

        String sql = "update user set manufacturer_name = '";
        sql += manuname + "',manufacturer_mail = '";
        sql += manumail + "',manufacturer_phone = '";
        sql += manuphone + "',manufacturer_address = '";
        sql += manuaddress+ "'";
        sql += " where manufacturer_id = '" + manuid + "'";
        try{
            if(dataOperation.executeUpdate(sql)<1){
                System.out.println("updateProcess(). update database failed.");
            }else{
                JOptionPane.showMessageDialog(null,
                        "更新成功！","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }

    }

    public void changePwProcess(String Pw){
        String sql = "update user set manufacturer_passward = '";
        sql += Pw + "'";
        sql += " where user_id = '" + jtfmanuid.getText().trim() + "'";
        try{
            if(dataOperation.executeUpdate(sql)<1){
                System.out.println("changePwProcess(). update database failed.");
            }else{
                JOptionPane.showMessageDialog(null,
                        "修改密码成功","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,
                    "操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }
}













