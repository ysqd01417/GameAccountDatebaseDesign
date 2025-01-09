package GameAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewManufacturer extends JFrame implements ActionListener {

    JLabel jlnewmanu = null;
    JLabel jlmanuid = null;
    JLabel jlmanuName = null;
    JLabel jlPassword = null;
    JLabel jlmanumail = null;
    JLabel jlmanuphone = null;
    JLabel jladdress = null;
    JLabel jlstatus = null;

    JTextField jtfmanuid = null;
    JTextField jtfmanuName = null;
    JTextField jtfPassword = null;
    JTextField jtfmanumail = null;
    JTextField jtfmanuphone = null;
    JTextField jtfaddress = null;
    JTextField jtfstatus = null;

    JButton jbInsert = null;

    JPanel jp1,jp2,jp3,jp4,jp5 = null;
    JPanel jp = null;

    JScrollPane NewmanuJScrollPane = null;

    private static DataOperation dataOperation ;
    private static int sid;

    public NewManufacturer(int id) {
        this.sid = id;

        jlnewmanu = new JLabel("新厂商信息");
        jlnewmanu.setFont(new Font("宋体", Font.BOLD, 25));
        jlmanuid = new JLabel("账号*");
        jlmanuName = new JLabel("姓名*");
        jlPassword = new JLabel("密码*");
        jlmanumail = new JLabel("邮箱");
        jlmanuphone = new JLabel("电话*");
        jladdress = new JLabel("地址*");
        jlstatus = new JLabel("状态*");

        jtfmanuid = new JTextField(15);
        jtfmanuid.setEditable(false);
        jtfmanuid.setText(String.valueOf(sid));
        jtfPassword = new JTextField(15);
        jtfmanuName = new JTextField(15);
        jtfaddress = new JTextField(15);
        jtfmanumail = new JTextField(15);
        jtfmanuphone = new JTextField(15);
        jtfstatus = new JTextField(15);
        jtfstatus.setEditable(false);
        jtfstatus.setText("T");

        jbInsert = new JButton("创建新厂商");
        jbInsert.setFont(new Font("宋体", Font.BOLD, 20));
        jbInsert.addActionListener(this);

        NewmanuJScrollPane = new JScrollPane();

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp = new JPanel();

        jp1.add(jlnewmanu);
        jp1.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp1.setPreferredSize(new Dimension(150, 80));

        jp2.add(jlmanuid);
        jp2.add(jtfmanuid);
        jp2.add(jlPassword);
        jp2.add(jtfPassword);
        jp2.setLayout((new FlowLayout(FlowLayout.CENTER)));
        jp2.setPreferredSize(new Dimension(150, 80));

        jp3.add(jlmanuName);
        jp3.add(jtfmanuName);
        jp3.add(jladdress);
        jp3.add(jtfaddress);
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp3.setPreferredSize(new Dimension(150, 80));

        jp4.add(jlmanumail);
        jp4.add(jtfmanumail);
        jp4.add(jlmanuphone);
        jp4.add(jtfmanuphone);
        jp4.add(jlstatus);
        jp4.add(jtfstatus);
        jp4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp4.setPreferredSize(new Dimension(150, 80));

        jp5.add(jbInsert);
        jp5.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp5.setPreferredSize(new Dimension(150, 80));

        jp.setLayout(new GridLayout(5,1));
        jp.add(jp1);
        jp.add(jp2);
        jp.add(jp3);
        jp.add(jp4);
        jp.add(jp5);

        this.add("North", NewmanuJScrollPane);
        this.add("South", jp);

        this.setTitle("新厂商注册");
        this.setSize(900,450);
        this.setLocation(400,150);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        dataOperation = new DataOperation();
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("创建新厂商")
                &&!jtfmanuName.getText().isEmpty()
                &&!jtfPassword.getText().isEmpty()
                &&!jtfaddress.getText().isEmpty()
                &&!jtfmanuphone.getText().isEmpty()){
            insertprocess();
            sid++;
            jtfmanuid.setText(String.valueOf(sid));
            jtfmanuName.setText("");
            jtfPassword.setText("");
            jtfaddress.setText("");
            jtfmanumail.setText("");
            jtfmanuphone.setText("");
        }
    }

    public void insertprocess(){
        String sid = jtfmanuid.getText().trim();
        String sname = jtfmanuName.getText().trim();
        String spassword = jtfPassword.getText().trim();
        String saddress = jtfaddress.getText().trim();
        String mail = jtfmanumail.getText().trim();
        String phone = jtfmanuphone.getText().trim();
        String status = jtfstatus.getText().trim();

        String sql = "insert into `manufacturer` values(";
        sql += sid + ",'";
        sql += spassword + "','";
        sql += sname + "','";
        sql += mail + "','";
        sql += phone + "','";
        sql += saddress + "','";
        sql += status + "');";
        try{
            if(dataOperation.executeUpdate(sql) < 1){
                System.out.println("updateProcess(). update database failed.");
            }else{
                JOptionPane.showMessageDialog(null,
                        "创建厂商成功！","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

}
