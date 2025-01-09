package GameAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMessage extends JFrame implements ActionListener {

    JLabel jLplayer = null;
    JLabel jLPlayerid = null;//id
    JLabel jLText = null;
    JLabel jLPlayerpw1 = null;//密码1
    JLabel jLPlayerpw2 = null;//密码2
    JLabel jLPlayername = null;//姓名
    JLabel jLsex = null;//性别
    JLabel jLPlayermail = null;//邮箱
    JLabel jLPlayerphone = null;//电话
    JLabel jLstatus = null;
    JTextField jTPlayerid = null;
    JPasswordField jPPlayerpw1 = null;
    JPasswordField jPPlayerpw2 = null;
    JTextField jTPlayername = null;
    JTextField jTsex = null;
    JTextField jTPlayermail = null;
    JTextField jTPlayerphone = null;
    JTextField jTstatus = null;

    JButton jBUpdateInfor = null;//更新信息
    JButton jBUpdatepw = null;//更新密码

    JPanel jp1,jp2,jp3,jp4,jp5,jp6 = null;
    JPanel jp = null;

    JScrollPane PlayerJScrollPane = null;

    private static DataOperation dataOperation;

    public UserMessage(ResultSet rs) {
        try{
            jLplayer = new JLabel("用户信息");
            jLplayer.setFont(new Font("宋体", Font.BOLD, 25));
            jLText = new JLabel("注意：更新信息需要填写上方所有带*的内容，修改密码只需要输入两次新密码即可！");
            jLText.setFont(new Font("宋体", Font.BOLD, 15));
            jLText.setForeground(Color.RED);
            jLPlayerid = new JLabel("账号*");
            jLPlayerpw1 = new JLabel("新密码*");
            jLPlayerpw2 =new JLabel("确认新密码*");
            jLPlayername = new JLabel("姓名*");
            jLsex = new JLabel("性别*");
            jLPlayermail = new JLabel("邮箱*");
            jLPlayerphone = new JLabel("电话*");
            jLstatus = new JLabel("状态");
            dataOperation = new DataOperation();

            jTPlayerid = new JTextField(15);
            jTPlayerid.setEditable(false);
            jTPlayerid.setText(rs.getString(1));

            jPPlayerpw1 = new JPasswordField(15);
            jPPlayerpw2 = new JPasswordField(15);

            jTPlayername = new JTextField(15);
            jTPlayername.setText(rs.getString(3));

            jTsex = new JTextField(15);
            jTsex.setText(rs.getString(4));

            jTPlayermail = new JTextField(15);
            jTPlayermail.setText(rs.getString(5));

            jTPlayerphone = new JTextField(15);
            jTPlayerphone.setText(rs.getString(6));

            jTstatus = new JTextField(15);
            jTstatus.setEditable(false);
            jTstatus.setText(rs.getString(7));

            jBUpdateInfor = new JButton("更新信息");
            jBUpdateInfor.setFont(new Font("宋体", Font.BOLD, 20));
            jBUpdatepw = new JButton("修改密码");
            jBUpdatepw.setFont(new Font("宋体", Font.BOLD, 20));

            jBUpdateInfor.addActionListener(this);
            jBUpdatepw.addActionListener(this);

            PlayerJScrollPane = new JScrollPane();

            jp1 = new JPanel();
            jp2 = new JPanel();
            jp3 = new JPanel();
            jp4 = new JPanel();
            jp5 = new JPanel();
            jp6 = new JPanel();
            jp = new JPanel();

            jp1.add(jLplayer);
            jp1.setLayout(new FlowLayout(FlowLayout.CENTER));
            jp1.setPreferredSize(new Dimension(20, 85));

            jp2.add(jLPlayerid);
            jp2.add(jTPlayerid);
            jp2.add(jLPlayername);
            jp2.add(jTPlayername);
            jp2.setLayout(new FlowLayout(FlowLayout.CENTER));
            jp2.setPreferredSize(new Dimension(20, 85));

            jp3.add(jLPlayermail);
            jp3.add(jTPlayermail);
            jp3.add(jLPlayerphone);
            jp3.add(jTPlayerphone);
            jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
            jp3.setPreferredSize(new Dimension(20, 85));

            jp4.add(jLsex);
            jp4.add(jTsex);
            jp4.add(jLstatus);
            jp4.add(jTstatus);
            jp4.setLayout(new FlowLayout(FlowLayout.CENTER));
            jp4.setPreferredSize(new Dimension(20, 85));

            jp5.add(jLText);
            jp5.add(jBUpdateInfor);
            jp5.setLayout(new FlowLayout(FlowLayout.CENTER));
            jp5.setPreferredSize(new Dimension(20, 85));

            jp6.add(jLPlayerpw1);
            jp6.add(jPPlayerpw1);
            jp6.add(jLPlayerpw2);
            jp6.add(jPPlayerpw2);
            jp6.add(jBUpdatepw);
            jp6.setLayout(new FlowLayout(FlowLayout.CENTER));
            jp6.setPreferredSize(new Dimension(20, 85));

            jp.setLayout(new GridLayout(6,1));
            jp.add(jp1);
            jp.add(jp2);
            jp.add(jp3);
            jp.add(jp4);
            jp.add(jp5);
            jp.add(jp6);

            this.add("North", PlayerJScrollPane);
            this.add("South", jp);

            this.setLayout(new BorderLayout());
            this.add(jp, BorderLayout.SOUTH);

            this.setTitle("玩家信息查询修改");
            this.setSize(850,600);
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
            &&!jLPlayername.getText().isEmpty()
            &&!jTPlayermail.getText().isEmpty()
            &&!jTPlayerphone.getText().isEmpty()){
            updataProcess();
        }else if(e.getActionCommand().equals("修改密码")
            &&!String.valueOf(jPPlayerpw1.getPassword()).equals("")
            &&!String.valueOf(jPPlayerpw2.getPassword()).equals("")){
            String spwd1=new String(jPPlayerpw1.getPassword());
            String spwd2=new String(jPPlayerpw2.getPassword());
            if(spwd1.equals(spwd2)){
                changePwProcess(spwd1);
            }else{
                JOptionPane.showMessageDialog(null,
                        "两次密码不一致，请确认后在输入！","提示",JOptionPane.ERROR_MESSAGE);
            }
            jPPlayerpw1.setText("");
            jPPlayerpw2.setText("");
        }

    }

    public static void main(String[] args) {
        Login L = new Login();
    }

    public void updataProcess(){
        String playerid = jTPlayerid.getText().trim();
        String playername = jTPlayername.getText().trim();
        String playermail = jTPlayermail.getText().trim();
        String playerphone = jTPlayerphone.getText().trim();

        String sql = "update user set user_name = '";
        sql += playername + "',user_mail = '";
        sql += playermail + "',user_phone = '";
        sql += playerphone + "'";
        sql += " where user_id = '" + playerid + "'";
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
        String sql = "update user set user_passward = '";
        sql += Pw + "'";
        sql += " where user_id = '" + jTPlayerid.getText().trim() + "';";
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
