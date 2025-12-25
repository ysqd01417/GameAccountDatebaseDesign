package GameAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener{
    JLabel jLGameManagement = null;
    JLabel jLLoginUser = null;
    JLabel jLLoginPassword = null;
    JLabel jLLoginStatus = null;//身份

    JTextField jTFLoginUser  = null;//用户框
    JPasswordField jTFLoginPassword = null;//密码框

    JButton jBLogin = null;//登录
    JButton jBExit = null;//退出
    JButton jBregister = null;//注册

    JComboBox<String> jCBSelectUser = null;//用户选择

    JPanel jP1, jP2, jP3, jP4, jP5 = null;
    JPanel jP = null;

    JScrollPane LoginJScrollPane = null;//滚轮

    private static DataOperation dataOperation;
    private static Manage M;
    private static RegisterSelect rgs;
    String SelectUserStr = "玩家";

    public Login() {
        jLGameManagement = new JLabel("游戏账号信息管理系统");
        jLGameManagement.setFont(new Font("宋体", Font.BOLD, 25));
        jLLoginUser = new JLabel("账号：");
        jLLoginUser.setFont(new Font("宋体", Font.BOLD, 15));
        jLLoginPassword = new JLabel("密码：");
        jLLoginPassword.setFont(new Font("宋体", Font.BOLD, 15));
        jLLoginStatus = new JLabel("身份：");
        jLLoginStatus.setFont(new Font("宋体", Font.BOLD, 15));

        jTFLoginUser = new JTextField(15);
        jTFLoginPassword = new JPasswordField(15);

        jBLogin = new JButton("登录");
        jBLogin.setFont(new Font("宋体", Font.BOLD, 15));
        jBExit = new JButton("退出");
        jBExit.setFont(new Font("宋体", Font.BOLD, 15));
        jBregister = new JButton("注册");
        jBregister.setFont(new Font("宋体", Font.BOLD, 15));

        jBLogin.addActionListener(this);
        jBExit.addActionListener(this);
        jBregister.addActionListener(this);

        jCBSelectUser = new JComboBox<>();
        jCBSelectUser.setFont(new Font("宋体", Font.BOLD, 15));
        jCBSelectUser.addItem("玩家");
        jCBSelectUser.addItem("管理员");
        jCBSelectUser.addItem("厂商");
        jCBSelectUser.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.SELECTED:
                        SelectUserStr = (String) e.getItem();
                        System.out.println("选中：" + SelectUserStr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中：" + e.getItem());
                        break;
                }
            }
        });

        LoginJScrollPane = new JScrollPane();

        jP1 = new JPanel();
        jP2 = new JPanel();
        jP3 = new JPanel();
        jP4 = new JPanel();
        jP5 = new JPanel();
        jP = new JPanel();

        jP1.add(jLGameManagement);
        jP1.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP1.setPreferredSize(new Dimension(20, 85));

        jP2.add(jLLoginUser);
        jP2.add(jTFLoginUser);
        jP2.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP2.setPreferredSize(new Dimension(20, 85));

        jP3.add(jLLoginPassword);
        jP3.add(jTFLoginPassword);
        jP3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP3.setPreferredSize(new Dimension(20, 85));

        jP4.add(jLLoginStatus);
        jP4.add(jCBSelectUser);
        jP4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP4.setPreferredSize(new Dimension(20, 85));

        jP5.add(jBLogin);
        jP5.add(jBExit);
        jP5.add(jBregister);
        jP5.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP5.setPreferredSize(new Dimension(20, 85));

        jP.setLayout(new GridLayout(5, 1));
        jP.add(jP1);
        jP.add(jP2);
        jP.add(jP3);
        jP.add(jP4);
        jP.add(jP5);

        this.add("North", LoginJScrollPane);
        this.add("South", jP);

        this.setLayout(new BorderLayout());
        this.add(jP, BorderLayout.SOUTH);

        this.setTitle("登录界面");
        this.setSize(460,500);
        this.setLocation(450,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        dataOperation = new DataOperation();

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("登录")
                &&!jTFLoginUser.getText().isEmpty()
                &&!String.valueOf(jTFLoginPassword.getPassword()).equals("")) {
            String sID = jTFLoginUser.getText().trim();
            String sPW = new String(jTFLoginPassword.getPassword());
            //System.out.println("actionPerformed().登录"+sPW);
            loginProcess(sID,sPW,SelectUserStr);
            jTFLoginPassword.setText("");
        }else if(e.getActionCommand().equals("注册")){
            this.rgs = new RegisterSelect();
        }else if(e.getActionCommand().equals("退出")) {
            System.out.println("actionPerformed().退出");
            System.exit(0);
        }
    }

    public  void loginProcess(String sID, String sPW, String userStr) {
        try{

            String sql="";

            if(userStr.equals("玩家")){
                sql = "select * from `user` where " + "`user_id`" + "='" + sID + "'" + "and" + "`user_identity`" + "='" + userStr + "'";
            } else if (userStr.equals("管理员")) {
                sql = "select * from `user` where " + "`user_id`" + "='" + sID + "'" + "and" + "`user_identity`" + "='" + userStr + "'";
            } else if (userStr.equals("厂商")){
                sql = "select * from `manufacturer` where " + "`manufacturer_id`" + "='" + sID + "'";
            }

            dataOperation.connect();
            ResultSet rs = dataOperation.executeQuery(sql);
            rs.next();
            if(rs.getString(7).equals("T")){
                if(sPW.equals(rs.getString(2))){
                    JOptionPane.showMessageDialog(null,
                            "登录成功:" , "提示", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    this.M=new Manage(rs);
                }else{
                    JOptionPane.showMessageDialog(null,
                            "用户密码错误，请重新输入：","错误",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null,
                        "此账号被封禁！","错误",JOptionPane.ERROR_MESSAGE);
            }
        }catch(SQLException sqle){
            System.out.println("sqle = " + sqle);
            JOptionPane.showMessageDialog(null,
                    "你输入的账号不存在，请重新输入：","错误",JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "你输入的账号不存在，请重新输入：","错误",JOptionPane.ERROR_MESSAGE);
        }
    }
}


















