package GameAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewUser extends JFrame implements ActionListener {

    JLabel jlnewuser = null;
    JLabel jluserid = null;
    JLabel jluserName = null;
    JLabel jlPassword = null;
    JLabel jlsex = null;
    JLabel jlmail = null;
    JLabel jlphone = null;
    JLabel jlidentity = null;
    JLabel jlstatus = null;

    //JTextField jtfnewuser = null;
    JTextField jtfuserid = null;
    JTextField jtfuserName = null;
    JTextField jtfPassword = null;
    JTextField jtfsex = null;
    JTextField jtfmail = null;
    JTextField jtfphone = null;
    JTextField jtfstatus = null;

    JComboBox<String> jCBidentity = null;
    String jCBidentityStr = "玩家";
    JButton jbInsert = null;

    JPanel jp1,jp2,jp3,jp4,jp5 = null;
    JPanel jp = null;

    JScrollPane NewUserJScrollPane = null;

    private static DataOperation dataOperation ;
    private static int sid;

    public NewUser(int id) {
        this.sid = id;
        jlnewuser = new JLabel("新用户信息");
        jlnewuser.setFont(new Font("宋体", Font.BOLD, 25));
        jluserid = new JLabel("账号*");
        jluserName = new JLabel("姓名*");
        jlPassword = new JLabel("密码*");
        jlsex = new JLabel("性别*");
        jlmail = new JLabel("邮箱");
        jlphone = new JLabel("电话*");
        jlidentity = new JLabel("身份*");
        jlstatus = new JLabel("状态*");

        jtfuserid = new JTextField(15);
        jtfuserid.setEditable(false);
        jtfuserid.setText(String.valueOf(sid));
        jtfuserName = new JTextField(15);
        jtfPassword = new JTextField(15);
        jtfsex = new JTextField(15);
        jtfmail = new JTextField(15);
        jtfphone = new JTextField(15);
        jtfstatus = new JTextField(15);
        jtfstatus.setEditable(false);
        jtfstatus.setText("T");

        jbInsert = new JButton("创建新用户");
        jbInsert.setFont(new Font("宋体", Font.BOLD, 20));
        jbInsert.addActionListener(this);

        jCBidentity = new JComboBox<>();
        jCBidentity.addItem("玩家");
        jCBidentity.addItem("管理员");

        jCBidentity.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.SELECTED:
                        jCBidentityStr = (String) e.getItem();
                        System.out.println("选中： " + jCBidentityStr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中： " + e.getItem());
                        break;
                }
            }
        });

        NewUserJScrollPane = new JScrollPane();

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp = new JPanel();

        jp1.add(jlnewuser);
        jp1.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp1.setPreferredSize(new Dimension(250, 80));

        jp2.add(jluserid);
        jp2.add(jtfuserid);
        jp2.add(jluserName);
        jp2.add(jtfuserName);
        jp2.add(jlPassword);
        jp2.add(jtfPassword);
        jp2.setLayout((new FlowLayout(FlowLayout.CENTER)));
        jp2.setPreferredSize(new Dimension(250, 80));

        jp3.add(jlsex);
        jp3.add(jtfsex);
        jp3.add(jlmail);
        jp3.add(jtfmail);
        jp3.add(jlphone);
        jp3.add(jtfphone);
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp3.setPreferredSize(new Dimension(250, 80));

        jp4.add(jlstatus);
        jp4.add(jtfstatus);
        jp4.add(jlidentity);
        jp4.add(jCBidentity);
        jp4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp4.setPreferredSize(new Dimension(250, 80));

        jp5.add(jbInsert);
        jp5.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp5.setPreferredSize(new Dimension(250, 80));

        jp.setLayout(new GridLayout(5,1));
        jp.add(jp1);
        jp.add(jp2);
        jp.add(jp3);
        jp.add(jp4);
        jp.add(jp5);

        this.add("North", NewUserJScrollPane);
        this.add("South", jp);

        this.setTitle("新用户注册");
        this.setSize(900,450);
        this.setLocation(400,150);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        dataOperation = new DataOperation();
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("创建新用户")
                &&!jtfuserName.getText().isEmpty()
                &&!jtfPassword.getText().isEmpty()
                &&!jtfsex.getText().isEmpty()
                &&!jtfphone.getText().isEmpty()){
            insertprocess();
            sid++;
            jtfuserid.setText(String.valueOf(sid));
            jtfuserName.setText("");
            jtfPassword.setText("");
            jtfsex.setText("");
            jtfmail.setText("");
            jtfphone.setText("");
        }
    }

    public void insertprocess(){
        String sid = jtfuserid.getText().trim();
        String sname = jtfuserName.getText().trim();
        String spassword = jtfPassword.getText().trim();
        String ssex = jtfsex.getText().trim();
        String mail = jtfmail.getText().trim();
        String phone = jtfphone.getText().trim();
        String status = jtfstatus.getText().trim();
        String identity = jCBidentityStr;

        String sql = "insert into `user` values(";
        sql += sid + ",'";
        sql += spassword + "','";
        sql += sname + "','";
        sql += ssex + "','";
        sql += mail + "','";
        sql += phone + "','";
        sql += status + "','";
        sql += identity + "');";
        try{
            if(dataOperation.executeUpdate(sql) < 1){
                System.out.println("updateProcess(). update database failed.");
            }else{
                JOptionPane.showMessageDialog(null,
                        "创建用户成功！","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }
}

