package GameAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class AdminMessage extends JFrame implements ActionListener {

    JLabel jlAdmin = null;
    JLabel jlText = null;
    JLabel jlAccountid = null;
    JLabel jlAccountname = null;
    JLabel jlAccountgrade = null;
    JLabel jlAccountmoney = null;
    JLabel jlAccountstatus = null;
    JLabel jluserid = null;
    JLabel jlusername = null;
    JLabel jluserstatus = null;
    JLabel jlgameid = null;
    JLabel jlgamename = null;
    JLabel jlgamestatus = null;

    JTextField jtfAccountid = null;
    JTextField jtfAccountname = null;
    JTextField jtfAccountgrade = null;
    JTextField jtfAccountmoney = null;
    JTextField jtfAccountstatus = null;
    JTextField jtfuserid = null;
    JTextField jtfusername = null;
    JTextField jtfuserstatus = null;
    JTextField jtfgameid = null;
    JTextField jtfgamename = null;
    JTextField jtfgamestatus = null;

    JButton jbquery = null;
    JButton jbupdateAccount = null;
    JButton jbupdateuser = null;
    JButton jbupdategame = null;

    JPanel jp1, jp2, jp3, jp4, jp5, jp6,jp7 = null;
    JPanel jpTop,jpBottoom = null;
    JTable jtaAdmintable = null;
    JScrollPane jspAdminscroll = null;
    Vector AdminVector = null;
    Vector titleVector = null;

    private static DataOperation dataOperation;

    public AdminMessage() {

        jlAdmin = new JLabel("管理员信息管理表");
        jlText = new JLabel("注：封禁账号将“T”改为“F”，解禁账号将“F”改为“T”,一次只能更新一种信息");
        jlText.setFont(new Font("宋体", Font.BOLD, 15));
        jlText.setForeground(Color.RED);
        jlAccountid = new JLabel("游戏账户");
        jlAccountname = new JLabel("游戏账户名称");
        jlAccountgrade = new JLabel("游戏账户等级");
        jlAccountmoney = new JLabel("游戏账号点券");
        jlAccountstatus = new JLabel("游戏账号状态");
        jluserid = new JLabel("玩家账号");
        jlusername = new JLabel("玩家姓名");
        jluserstatus = new JLabel("玩家账号状态");
        jlgameid = new JLabel("游戏编号");
        jlgamename = new JLabel("游戏名称");
        jlgamestatus = new JLabel("游戏状态");

        jtfAccountid = new JTextField(10);
        jtfAccountid.setEditable(false);
        jtfAccountname = new JTextField(10);
        jtfAccountgrade = new JTextField(10);
        jtfAccountmoney = new JTextField(10);
        jtfAccountstatus = new JTextField(10);
        jtfuserid = new JTextField(10);
        jtfuserid.setEditable(false);
        jtfusername = new JTextField(10);
        jtfuserstatus =  new JTextField(10);
        jtfgameid = new JTextField(10);
        jtfgameid.setEditable(false);
        jtfgamename = new JTextField(10);
        jtfgamestatus = new JTextField(10);

        jbquery = new JButton("查看");
        jbquery.setFont(new Font("宋体", Font.BOLD, 15));
        jbupdateAccount = new JButton("更新游戏账户信息");
        jbupdateAccount.setFont(new Font("宋体", Font.BOLD, 15));
        jbupdateuser = new JButton("更新玩家信息");
        jbupdateuser.setFont(new Font("宋体", Font.BOLD, 15));
        jbupdategame = new JButton("更新游戏信息");
        jbupdategame.setFont(new Font("宋体", Font.BOLD, 15));

        jbquery.addActionListener(this);
        jbupdateAccount.addActionListener(this);
        jbupdateuser.addActionListener(this);
        jbupdategame.addActionListener(this);

        AdminVector = new Vector();
        titleVector = new Vector();

        titleVector.add("游戏账号");
        titleVector.add("游戏账号名称");
        titleVector.add("游戏账号等级");
        titleVector.add("游戏账号点券");
        titleVector.add("游戏账号状态");
        titleVector.add("玩家账号");
        titleVector.add("玩家名称");
        titleVector.add("玩家账号状态");
        titleVector.add("游戏编号");
        titleVector.add("游戏名称");
        titleVector.add("游戏状态");

        jtaAdmintable = new JTable(AdminVector, titleVector);
        jtaAdmintable.setPreferredScrollableViewportSize(new Dimension(910, 220));
        jspAdminscroll = new JScrollPane(jtaAdmintable);
        jspAdminscroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jspAdminscroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        jtaAdmintable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
                Vector<String> v = new Vector();
                v = (Vector) AdminVector.get(row);

                jtfAccountid.setText((String) v.get(0));
                jtfAccountname.setText((String) v.get(1));
                jtfAccountgrade.setText((String) v.get(2));
                jtfAccountmoney.setText((String) v.get(3));
                jtfAccountstatus.setText((String) v.get(4));
                jtfuserid.setText((String) v.get(5));
                jtfusername.setText((String) v.get(6));
                jtfuserstatus.setText((String) v.get(7));
                jtfgameid.setText((String) v.get(8));
                jtfgamename.setText((String) v.get(9));
                jtfgamestatus.setText((String) v.get(10));
            }
        });

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();
        jp7 = new JPanel();
        jpTop = new JPanel();
        jpBottoom = new JPanel();

        jp1.add(jlAdmin);
        jp2.add(jspAdminscroll);

        jp3.add(jlAccountid);
        jp3.add(jtfAccountid);
        jp3.add(jlAccountname);
        jp3.add(jtfAccountname);
        jp3.add(jlAccountgrade);
        jp3.add(jtfAccountgrade);

        jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp3.setPreferredSize(new Dimension(30, 30));

        jp4.add(jlAccountmoney);
        jp4.add(jtfAccountmoney);
        jp4.add(jlAccountstatus);
        jp4.add(jtfAccountstatus);
        jp4.add(jbupdateAccount);
        jp4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp4.setPreferredSize(new Dimension(30, 30));

        jp5.add(jluserid);
        jp5.add(jtfuserid);
        jp5.add(jlusername);
        jp5.add(jtfusername);
        jp5.add(jluserstatus);
        jp5.add(jtfuserstatus);
        jp5.add(jbupdateuser);
        jp5.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp5.setPreferredSize(new Dimension(30, 30));

        jp6.add(jlgameid);
        jp6.add(jtfgameid);
        jp6.add(jlgamename);
        jp6.add(jtfgamename);
        jp6.add(jlgamestatus);
        jp6.add(jtfgamestatus);
        jp6.add(jbupdategame);
        jp6.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp6.setPreferredSize(new Dimension(30, 30));

        jp7.add(jlText);
        jp7.add(jbquery);
        jp7.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp7.setPreferredSize(new Dimension(30, 30));

        jpTop.add(jp1);
        jpTop.add(jp2);

        jpBottoom.setLayout(new GridLayout(5, 1));
        jpBottoom.add(jp3);
        jpBottoom.add(jp4);
        jpBottoom.add(jp5);
        jpBottoom.add(jp6);
        jpBottoom.add(jp7);

        this.add("North", jpTop);
        this.add("South", jpBottoom);

        this.setLayout(new GridLayout(2, 1));
        this.setTitle("管理员信息管理表");
        this.setSize(970,650);
        this.setLocation(300, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        dataOperation = new DataOperation();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("查看")){
            queryprocess();
            jtfAccountid.setText("");
            jtfAccountname.setText("");
            jtfAccountgrade.setText("");
            jtfAccountmoney.setText("");
            jtfAccountstatus.setText("");
            jtfuserid.setText("");
            jtfusername.setText("");
            jtfuserstatus.setText("");
            jtfgameid.setText("");
            jtfgamename.setText("");
            jtfgamestatus.setText("");
        }else if(e.getActionCommand().equals("更新游戏账户信息")
                &&!jtfAccountname.getText().isEmpty()
                &&!jtfAccountgrade.getText().isEmpty()
                &&!jtfAccountmoney.getText().isEmpty()
                &&!jtfAccountstatus.getText().isEmpty()) {
            updateaccountprocess();
        }else if(e.getActionCommand().equals("更新玩家信息")
                &&!jtfusername.getText().isEmpty()
                &&!jtfuserstatus.getText().isEmpty()){
            updateuserprocess();
        }else if(e.getActionCommand().equals("更新游戏信息")
                &&!jtfgamename.getText().isEmpty()
                &&!jtfgamestatus.getText().isEmpty()){
            updategameprocess();
        }
    }

    public void queryprocess(){
        try{
            String sql = "select * from `adminquery`;";

            dataOperation.connect();
            ResultSet qrs = dataOperation.executeQuery(sql);
            AdminVector.clear();
            while(qrs.next()){
                Vector<String> v = new Vector();
                v.add(qrs.getString("account_id"));
                v.add(qrs.getString("account_name"));
                v.add(qrs.getString("account_grade"));
                v.add(qrs.getString("account_money"));
                v.add(qrs.getString("account_status"));
                v.add(qrs.getString("user_id"));
                v.add(qrs.getString("user_name"));
                v.add(qrs.getString("user_status"));
                v.add(qrs.getString("game_id"));
                v.add(qrs.getString("game_name"));
                v.add(qrs.getString("game_status"));
                AdminVector.add(v);
            }
            jtaAdmintable.updateUI();
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }finally {
            dataOperation.disconnect();
        }
    }

    public void updateaccountprocess(){
        String upaccountname = jtfAccountname.getText().trim();
        String upaccountgrade = jtfAccountgrade.getText().trim();
        String upaccountmoney = jtfAccountmoney.getText().trim();
        String upaccountstatus = jtfAccountstatus.getText().trim();
        String accountid = jtfAccountid.getText().trim();

        String sql = "update `adminquery` set `account_name` = '"
                + upaccountname + "',`account_grade` = '"
                + upaccountgrade + "',`account_money` = '"
                + upaccountmoney + "',`account_status` = '"
                + upaccountstatus + "' where `account_id` = '"
                + accountid + "'";
        try{
            if(dataOperation.executeUpdate(sql)<1){
                System.out.println("updateProcess(). update database failed.");
            }else{
                JOptionPane.showMessageDialog(null,
                        "更新成功","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateuserprocess(){
        String upusername = jtfusername.getText().trim();
        String upuserstatus = jtfuserstatus.getText().trim();
        String accountid = jtfAccountid.getText().trim();

        String sql = "update `adminquery` set `user_name` = '"
                + upusername + "',`user_status` = '"
                + upuserstatus +  "' where `account_id` = '"
                + accountid + "'";
        try{
            if(dataOperation.executeUpdate(sql)<1){
                System.out.println("updateProcess(). update database failed.");
            }else{
                JOptionPane.showMessageDialog(null,
                        "更新成功","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updategameprocess(){
        String upgamename = jtfgamename.getText().trim();
        String upgamestatus = jtfgamestatus.getText().trim();
        String accountid = jtfAccountid.getText().trim();

        String sql = "update `adminquery` set `game_name` = '"
                + upgamename + "',`game_status` = '"
                + upgamestatus + "' where `account_id` = '"
                + accountid + "'";
        try{
            if(dataOperation.executeUpdate(sql)<1){
                System.out.println("updateProcess(). update database failed.");
            }else{
                JOptionPane.showMessageDialog(null,
                        "更新成功","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }
}
