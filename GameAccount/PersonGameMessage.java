package GameAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class PersonGameMessage extends JFrame implements ActionListener {

    JLabel jlPerGame = null;
    JLabel jlGameAccount = null;
    JLabel jlAccountname = null;
    JLabel jlAccountgrade = null;
    JLabel jlAccountdate = null;
    JLabel jlAccountmoney = null;
    JLabel jlgamename = null;
    JLabel jlAccountstatus = null;

    JTextField jtfGameAccount = null;
    JTextField jtfAccountname = null;
    JTextField jtfAccountgrade = null;
    JTextField jtfAccountdate = null;
    JTextField jtfAccountmoney = null;
    JTextField jtfgamename = null;
    JTextField jtfAccountstatus = null;

    JButton jbupdateAccount = null;
    JButton jbcreateAccount = null;
    JButton jbqueryAccount = null;
    JButton jbdeleteAccount = null;

    JPanel jp1, jp2, jp3, jp4, jp5 = null;
    JPanel jpTop,jpBottoom = null;
    JTable jtaGameAccounttable = null;
    JScrollPane jspGameAccountscroll = null;
    Vector GameAccountVector = null;
    Vector titleVector = null;

    private static DataOperation dataOperation;
    private static NewGameAccount nga;
    private ResultSet prs;

    public PersonGameMessage(ResultSet rs) {
        this.prs = rs;

        jlPerGame = new JLabel("个人游戏账号表");
        jlGameAccount = new JLabel("游戏账户");
        jlAccountname = new JLabel("账户名称");
        jlAccountgrade = new JLabel("账户等级");
        jlAccountdate = new JLabel("创建日期");
        jlAccountmoney = new JLabel("游戏点券");
        jlgamename = new JLabel("游戏名称");
        jlAccountstatus = new JLabel("账号状态");

        jtfGameAccount = new JTextField(15);
        jtfGameAccount.setEditable(false);
        jtfAccountname = new JTextField(15);
        jtfAccountgrade = new JTextField(15);
        jtfAccountgrade.setEditable(false);
        jtfAccountdate = new JTextField(15);
        jtfAccountdate.setEditable(false);
        jtfAccountmoney = new JTextField(15);
        jtfAccountmoney.setEditable(false);
        jtfgamename = new JTextField(15);
        jtfgamename.setEditable(false);
        jtfAccountstatus = new JTextField(15);
        jtfAccountstatus.setEditable(false);

        jbqueryAccount = new JButton("查看");
        jbqueryAccount.setFont(new Font("宋体", Font.BOLD, 20));
        jbupdateAccount = new JButton("更新信息");
        jbupdateAccount.setFont(new Font("宋体", Font.BOLD, 20));
        jbdeleteAccount = new JButton("删除此账号");
        jbdeleteAccount.setFont(new Font("宋体", Font.BOLD, 20));
        jbcreateAccount = new JButton("创建新游戏账户");
        jbcreateAccount.setFont(new Font("宋体", Font.BOLD, 20));

        jbqueryAccount.addActionListener(this);
        jbupdateAccount.addActionListener(this);
        jbdeleteAccount.addActionListener(this);
        jbcreateAccount.addActionListener(this);

        GameAccountVector = new Vector();
        titleVector = new Vector();

        titleVector.add("游戏账号");
        titleVector.add("账号名称");
        titleVector.add("账号等级");
        titleVector.add("创建日期");
        titleVector.add("账号点券");
        titleVector.add("游戏名称");
        titleVector.add("账号状态");

        jtaGameAccounttable = new JTable(GameAccountVector, titleVector);
        jtaGameAccounttable.setPreferredScrollableViewportSize(new Dimension(910, 220));
        jspGameAccountscroll = new JScrollPane(jtaGameAccounttable);
        jspGameAccountscroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jspGameAccountscroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        jtaGameAccounttable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
                Vector<String> v = new Vector();
                v = (Vector) GameAccountVector.get(row);

                jtfGameAccount.setText((String) v.get(0));
                jtfAccountname.setText((String) v.get(1));
                jtfAccountgrade.setText((String) v.get(2));
                jtfAccountdate.setText((String) v.get(3));
                jtfAccountmoney.setText((String) v.get(4));
                jtfgamename.setText((String) v.get(5));
                jtfAccountstatus.setText((String) v.get(6));
            }
        });

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jpTop = new JPanel();
        jpBottoom = new JPanel();

        jp1.add(jlPerGame);
        jp2.add(jspGameAccountscroll);

        jp3.add(jlGameAccount);
        jp3.add(jtfGameAccount);
        jp3.add(jlAccountname);
        jp3.add(jtfAccountname);
        jp3.add(jlAccountgrade);
        jp3.add(jtfAccountgrade);
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp3.setPreferredSize(new Dimension(35, 35));

        jp4.add(jlAccountdate);
        jp4.add(jtfAccountdate);
        jp4.add(jlAccountmoney);
        jp4.add(jtfAccountmoney);
        jp4.add(jlgamename);
        jp4.add(jtfgamename);
        jp4.add(jlAccountstatus);
        jp4.add(jtfAccountstatus);
        jp4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp4.setPreferredSize(new Dimension(35, 35));

        jp5.add(jbqueryAccount);
        jp5.add(jbupdateAccount);
        jp5.add(jbdeleteAccount);
        jp5.add(jbcreateAccount);
        jp5.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp5.setPreferredSize(new Dimension(35, 35));

        jpTop.add(jp1);
        jpTop.add(jp2);

        jpBottoom.setLayout(new GridLayout(3, 1));
        jpBottoom.add(jp3);
        jpBottoom.add(jp4);
        jpBottoom.add(jp5);

        this.add("North", jpTop);
        this.add("South", jpBottoom);

        this.setLayout(new GridLayout(2, 1));
        this.setTitle("个人游戏账号表");
        this.setSize(970,650);
        this.setLocation(300, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        dataOperation = new DataOperation();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("查看")){
            queryprocess(prs);
            jtfGameAccount.setText("");
            jtfAccountname.setText("");
            jtfAccountgrade.setText("");
            jtfAccountdate.setText("");
            jtfAccountmoney.setText("");
            jtfgamename.setText("");
            jtfAccountstatus.setText("");
        }else if(e.getActionCommand().equals("更新信息")
                    &&!jtfAccountname.getText().isEmpty()){
            updateprocess();
        }else if(e.getActionCommand().equals("删除此账号")
                    &&!jtfGameAccount.getText().isEmpty()) {
            deleteaccount();
        }else{
            GameAccountcreate(prs);
        }
    }

    public void queryprocess(ResultSet rs){
        try{
            String sql = "select * from `gameaccount` join `game` " +
                    "on `account_game` = `game_id` " +
                    "where `account_user` = '" +
                    rs.getInt("user_id") + "';";

            dataOperation.connect();
            ResultSet qrs = dataOperation.executeQuery(sql);
            GameAccountVector.clear();
            while(qrs.next()){
                Vector<String> v = new Vector();
                v.add(qrs.getString("account_id"));
                v.add(qrs.getString("account_name"));
                v.add(qrs.getString("account_grade"));
                v.add(qrs.getString("account_date"));
                v.add(qrs.getString("account_money"));
                v.add(qrs.getString("game_name"));
                v.add(qrs.getString("account_status"));
                GameAccountVector.add(v);
            }
            jtaGameAccounttable.updateUI();
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }finally {
            dataOperation.disconnect();
        }
    }

    public void updateprocess(){
        String upaccountname = jtfAccountname.getText().trim();
        String accountid = jtfGameAccount.getText().trim();

        String sql = "update `gameaccount` set `account_name` = '"
                + upaccountname + "' where `account_id` = '"
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

    public void deleteaccount(){
        String id = jtfGameAccount.getText().trim();

        String sql = "delete from `gameaccount` " +
                "where `account_id` = '" + id + "'";
        try{
            if(dataOperation.executeUpdate(sql)<1){
                System.out.println("deleteaccount(). delete database failed.");
            }else{
                JOptionPane.showMessageDialog(null,
                        "删除成功","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void GameAccountcreate(ResultSet rs) {
        String sql="";
        int id = 100000;
        int mid = 0;
        try{
            mid = rs.getInt("user_id");
            sql = "select max(`account_id`) from `gameaccount`;";
            dataOperation.connect();
            ResultSet grs = dataOperation.executeQuery(sql);
            if(grs.next()){
                if(id<=grs.getInt(1)){
                    id=grs.getInt(1);
                }
            }
            dataOperation.disconnect();
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,
                    "空集合","错误",JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "发生错误","错误",JOptionPane.ERROR_MESSAGE);
        }finally {
            this.nga = new NewGameAccount(id+1,mid);
        }
    }
}




