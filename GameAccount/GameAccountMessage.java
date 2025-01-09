package GameAccount;

import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class GameAccountMessage extends JFrame implements ActionListener {

    JLabel jlGameAccount = null;
    JLabel jlText = null;
    JLabel jlcomparison = null;
    JLabel jlGamename = null;//游戏名称
    JLabel jlAccountuser = null;//账号玩家
    JLabel jlGamemanu = null;//游戏厂商
    JLabel jlAccountusersex = null;//玩家性别
    JLabel jlAccountgrade = null;//账户等级
    JLabel jlGametype = null;//游戏类别
    JLabel jlplayercount = null;

    JTextField jtfGamename = null;
    JTextField jtfAccountuser = null;
    JTextField jtfGamemanu = null;
    JTextField jtfAccountusersex = null;
    JTextField jtfAccountgrade = null;
    JTextField jtfGametype = null;
    JTextField jtfplayercount = null;

    JButton jbQuery = null;

    JComboBox<String> jcbGamename = null;
    JComboBox<String> jcbAccountuser = null;
    JComboBox<String> jcbGamemanu = null;
    JComboBox<String> jcbAccountusersex = null;
    JComboBox<String> jcbGametype = null;

    JPanel jp1,jp2,jp3,jp4,jp5 = null;
    JPanel jpTop,jpBottom = null;
    JTable accountTable = null;
    JScrollPane accountScroll = null;
    Vector accountVector= null;
    Vector titleVector = null;

    private static DataOperation dataOperation;
    String Gamenamestr = null;
    String Accountuserstr = null;
    String Gamemanustr = null;
    String Accountusersexstr = null;
    String Gametypestr = null;

    public GameAccountMessage() {
        dataOperation = new DataOperation();

        jlGameAccount = new JLabel("游戏账号信息表");
        jlText = new JLabel("注*：选项内容均未初始化,若查询请先切换至其他选项,若查看所有游戏，直接点击查询即可");
        jlText.setFont(new Font("宋体", Font.BOLD, 15));
        jlText.setForeground(Color.RED);
        jlcomparison = new JLabel(" >= ");
        jlGamename = new JLabel("游戏名称");
        jlAccountuser = new JLabel("账号玩家");
        jlGamemanu = new JLabel("游戏厂商");
        jlAccountusersex = new JLabel("玩家性别");
        jlAccountgrade = new JLabel("账号等级");
        jlGametype = new JLabel("游戏类型");
        jlplayercount = new JLabel("玩家数量");

        jtfAccountgrade = new JTextField(15);
        jtfplayercount = new JTextField(15);
        jtfplayercount.setEditable(false);

        jbQuery = new JButton("查询");
        jbQuery.setFont(new Font("宋体", Font.BOLD, 20));
        jbQuery.addActionListener(this);

        jcbGamename = new JComboBox<>();
        try{
            String sql = "select `game_name` from `game`;";
            dataOperation.connect();
            ResultSet rs = dataOperation.executeQuery(sql);
            while(rs.next()){
                jcbGamename.addItem(rs.getString("game_name"));
            }
            dataOperation.disconnect();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }

        jcbAccountuser = new JComboBox<>();
        try{
            String sql = "select `user_name` from `user`" +
                    " where `user_status` = 'T'" +
                    " and `user_identity` = '玩家';";
            dataOperation.connect();
            ResultSet rs = dataOperation.executeQuery(sql);
            while(rs.next()){
                jcbAccountuser.addItem(rs.getString("user_name"));
            }
            dataOperation.disconnect();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }

        jcbGamemanu = new JComboBox<>();
        try{
            String sql = "select `manufacturer_name` from `manufacturer`;";
            dataOperation.connect();
            ResultSet rs = dataOperation.executeQuery(sql);
            while(rs.next()){
                jcbGamemanu.addItem(rs.getString("manufacturer_name"));
            }
            dataOperation.disconnect();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }

        jcbAccountusersex = new JComboBox<>();
        jcbAccountusersex.addItem("男");
        jcbAccountusersex.addItem("女");

        jcbGametype = new JComboBox<>();
        try{
            String sql = "select distinct `game_type` from `game`;";
            dataOperation.connect();
            ResultSet rs = dataOperation.executeQuery(sql);
            while(rs.next()){
                jcbGametype.addItem(rs.getString("game_type"));
            }
            dataOperation.disconnect();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }

        jcbGamename.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch(e.getStateChange()){
                    case ItemEvent.SELECTED:
                        Gamenamestr = (String) e.getItem();
                        System.out.println("选中：" + Gamenamestr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中：" + e.getItem());
                        break;
                }
            }
        });
        jcbAccountuser.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch(e.getStateChange()){
                    case ItemEvent.SELECTED:
                        Accountuserstr = (String) e.getItem();
                        System.out.println("选中：" + Accountuserstr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中" + e.getItem());
                        break;
                }
            }
        });
        jcbGamemanu.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch(e.getStateChange()){
                    case ItemEvent.SELECTED:
                        Gamemanustr = (String) e.getItem();
                        System.out.println("选中：" + Gamemanustr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中：" + e.getItem());
                        break;
                }
            }
        });
        jcbAccountusersex.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch(e.getStateChange()){
                    case ItemEvent.SELECTED:
                        Accountusersexstr = (String) e.getItem();
                        System.out.println("选中：" + Accountusersexstr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中：" + e.getItem());
                        break;
                }
            }
        });
        jcbGametype.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch(e.getStateChange()){
                    case ItemEvent.SELECTED:
                        Gametypestr = (String) e.getItem();
                        System.out.println("选中：" + Gametypestr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中：" + e.getItem());
                        break;
                }
            }
        });

        accountVector = new Vector();
        titleVector = new Vector();

        titleVector.add("游戏账号");
        titleVector.add("账号名称");
        titleVector.add("账号等级");
        titleVector.add("创建时间");
        titleVector.add("账号点券");
        titleVector.add("账号状态");
        titleVector.add("玩家");
        titleVector.add("游戏名称");
        titleVector.add("地区");

        accountTable = new JTable(accountVector, titleVector);
        accountTable.setPreferredScrollableViewportSize(new Dimension(910, 220));
        accountScroll = new JScrollPane(accountTable);
        accountScroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        accountScroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jpTop = new JPanel();
        jpBottom = new JPanel();

        jp1.add(jlGameAccount, BorderLayout.NORTH);
        jp2.add(accountScroll);

        jp3.add(jlGamename);
        jp3.add(jcbGamename);
        jp3.add(jlAccountuser);
        jp3.add(jcbAccountuser);
        jp3.add(jlGamemanu);
        jp3.add(jcbGamemanu);
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp3.setPreferredSize(new Dimension(35, 35));

        jp4.add(jlAccountusersex);
        jp4.add(jcbAccountusersex);
        jp4.add(jlGametype);
        jp4.add(jcbGametype);
        jp4.add(jlAccountgrade);
        jp4.add(jlcomparison);
        jp4.add(jtfAccountgrade);
        jp4.add(jlplayercount);
        jp4.add(jtfplayercount);
        jp4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp4.setPreferredSize(new Dimension(35, 35));

        jp5.add(jlText);
        jp5.add(jbQuery);
        jp5.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp5.setPreferredSize(new Dimension(35, 35));

        jpTop.add(jp1);
        jpTop.add(jp2);

        jpBottom.setLayout(new GridLayout(3, 1));
        jpBottom.add(jp3);
        jpBottom.add(jp4);
        jpBottom.add(jp5);

        this.add("North", jpTop);
        this.add("South", jpBottom);

        this.setLayout(new GridLayout(2,1));
        this.setTitle("游戏信息查询");
        this.setSize(970, 650);
        this.setLocation(300, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("查询")) {
            queryProcess();
            Gamenamestr = null;
            Accountuserstr = null;
            Gamemanustr = null;
            Accountusersexstr = null;
            Gametypestr = null;
            jtfAccountgrade.setText("");
        }else{
            JOptionPane.showMessageDialog(null,
                    "请选择查询条件","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryProcess(){
        try{
            String sql = "select * from `gameaccountquery`";
            String sql1 = "select `user_name` from `gameaccountquery`";
            String s = " ";
            boolean judge = false;
            int num = 0;
            if(Gamenamestr != null){
                s += "where ";
                s += "`game_name` = '" + Gamenamestr + "'";
                judge = true;
            }
            if(Accountuserstr != null){
                if(judge){
                    s += " and ";
                }else{
                    s += "where ";
                }
                s += "`user_name` = '" + Accountuserstr + "'";
                judge = true;
            }
            if(Gamemanustr != null){
                if(judge){
                    s += " and ";
                }else{
                    s += "where ";
                }
                s += "`game_name` in ( " +
                        "select `game_name` from `manufacturer` join `game` " +
                        "on `manufacturer_id` = `game_manufacturer` where " +
                        "`manufacturer_name` = '" + Gamemanustr + "')";
                judge = true;
            }
            if(Accountusersexstr != null){
                if(judge){
                    s += " and ";
                }else{
                    s += "where ";
                }
                s += "`user_name` in ( " +
                        "select `user_name` from `user` " +
                        "where `user_sex` = '" + Accountusersexstr + "')";
                judge = true;
            }
            if(Gametypestr != null){
                if(judge){
                    s += " and ";
                }else{
                    s += "where ";
                }
                s += "`game_name` in ( " +
                        "select `game_name` from `game` " +
                        "where `game_type` = '" + Gametypestr + "')" ;
                judge = true;
            }
            if(!jtfAccountgrade.getText().isEmpty()){
                if(judge){
                    s += " and ";
                }else{
                    s += "where ";
                }
                s += "`account_grade` >= '" + jtfAccountgrade.getText().trim() + "'";
            }
            sql += s;
            sql1 += s;
            sql1 += " group by `user_name` having count(*)>0";
            dataOperation.connect();
            ResultSet rs = dataOperation.executeQuery(sql);
            ResultSet rs1 = dataOperation.executeQuery(sql1);
            accountVector.clear();
            while(rs.next()){
                Vector<String> v = new Vector<>();
                v.add(rs.getString("account_id"));
                v.add(rs.getString("account_name"));
                v.add(rs.getString("account_grade"));
                v.add(rs.getString("account_date"));
                v.add(rs.getString("account_money"));
                v.add(rs.getString("account_status"));
                v.add(rs.getString("user_name"));
                v.add(rs.getString("game_name"));
                v.add(rs.getString("manufacturer_address"));
                accountVector.add(v);
            }
            while(rs1.next()){
                num +=1;
            }
            System.out.println(num);
            jtfplayercount.setText(String.valueOf(num));
            accountTable.updateUI();
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }finally {
            dataOperation.disconnect();
        }
    }
}



