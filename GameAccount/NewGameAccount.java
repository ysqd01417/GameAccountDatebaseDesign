package GameAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.time.*;

public class NewGameAccount extends JFrame implements ActionListener {

    JLabel jlnewaccount = null;
    JLabel jlaccountid = null;
    JLabel jlaccountName = null;
    JLabel jlaccountgrade = null;
    JLabel jlaccountdate = null;
    JLabel jlaccountmoney = null;
    JLabel jlaccountstatus = null;
    JLabel jlaccountuser = null;
    JLabel jlaccountgame = null;

    JTextField jtfaccountid = null;
    JTextField jtfaccountName = null;
    JTextField jtfaccountgrade = null;
    JTextField jtfaccountdate = null;
    JTextField jtfaccountmoney = null;
    JTextField jtfaccountstatus = null;
    JTextField jtfaccountuser = null;
    JTextField jtfaccountgame = null;

    JComboBox<String> jCBgame = null;
    String jCBgameStr = "";
    JButton jbInsert = null;

    JPanel jp1,jp2,jp3,jp4,jp5 = null;
    JPanel jp = null;

    JScrollPane NewaccountJScrollPane = null;

    private static DataOperation dataOperation ;
    private static int sid;
    LocalDate date;

    public NewGameAccount(int id,int uid) {
        dataOperation = new DataOperation();
        date = LocalDate.now();
        this.sid = id;

        jlnewaccount = new JLabel("新建游戏账号信息");
        jlnewaccount.setFont(new Font("宋体", Font.BOLD, 25));
        jlaccountid = new JLabel("账号*");
        jlaccountName = new JLabel("姓名*");
        jlaccountgrade = new JLabel("等级*");
        jlaccountdate = new JLabel("日期*");
        jlaccountmoney = new JLabel("点券*");
        jlaccountstatus = new JLabel("状态*");
        jlaccountuser = new JLabel("玩家id*");
        jlaccountgame = new JLabel("游戏id*");

        jtfaccountid = new JTextField(15);
        jtfaccountid.setEditable(false);
        jtfaccountid.setText(String.valueOf(sid));
        jtfaccountName = new JTextField(15);
        jtfaccountgrade = new JTextField(15);
        jtfaccountgrade.setEditable(false);
        jtfaccountgrade .setText(String.valueOf(1));
        jtfaccountdate = new JTextField(15);
        jtfaccountdate.setEditable(false);
        jtfaccountdate.setText(date.toString());
        jtfaccountmoney = new JTextField(15);
        jtfaccountmoney.setEditable(false);
        jtfaccountmoney.setText(String.valueOf(100));
        jtfaccountstatus = new JTextField(15);
        jtfaccountstatus.setEditable(false);
        jtfaccountstatus.setText("T");
        jtfaccountuser = new JTextField(15);
        jtfaccountuser.setEditable(false);
        jtfaccountuser.setText(String.valueOf(uid));
        jtfaccountgame = new JTextField(15);
        jtfaccountgame.setEditable(false);

        jbInsert = new JButton("创建新游戏账号");
        jbInsert.setFont(new Font("宋体", Font.BOLD, 20));
        jbInsert.addActionListener(this);

        jCBgame = new JComboBox<>();
        try{
            String sql = "select `game_name` from `game`;";
            dataOperation.connect();
            ResultSet rs = dataOperation.executeQuery(sql);
            while(rs.next()){
                jCBgame.addItem(rs.getString("game_name"));
            }
            dataOperation.disconnect();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }

        jCBgame.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.SELECTED:
                        jCBgameStr = (String) e.getItem();
                        System.out.println("选中： " + jCBgameStr);
                        try{
                            String sql = "select `game_id` from `game` " +
                                    "where `game_name` = '" + jCBgameStr + "'" +
                                    " and `game_status` = 'T' ";
                            dataOperation.connect();
                            ResultSet rs = dataOperation.executeQuery(sql);
                            if(rs.next()){
                                jtfaccountgame.setText(rs.getString("game_id"));
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "没有找到数据","提示",JOptionPane.INFORMATION_MESSAGE);
                            }
                            dataOperation.disconnect();
                        }catch(Exception event){
                            event.printStackTrace();
                            JOptionPane.showMessageDialog(null,
                                    "操作错误","错误",JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中： " + e.getItem());
                        break;
                }
            }
        });

        NewaccountJScrollPane = new JScrollPane();

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp = new JPanel();

        jp1.add(jlnewaccount);
        jp1.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp1.setPreferredSize(new Dimension(250, 80));

        jp2.add(jlaccountid);
        jp2.add(jtfaccountid);
        jp2.add(jlaccountName);
        jp2.add(jtfaccountName);
        jp2.add(jlaccountgrade);
        jp2.add(jtfaccountgrade);
        jp2.setLayout((new FlowLayout(FlowLayout.CENTER)));
        jp2.setPreferredSize(new Dimension(250, 80));

        jp3.add(jlaccountdate);
        jp3.add(jtfaccountdate);
        jp3.add(jlaccountmoney);
        jp3.add(jtfaccountmoney);
        jp3.add(jlaccountstatus);
        jp3.add(jtfaccountstatus);
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp3.setPreferredSize(new Dimension(250, 80));

        jp4.add(jCBgame);
        jp4.add(jlaccountuser);
        jp4.add(jtfaccountuser);
        jp4.add(jlaccountgame);
        jp4.add(jtfaccountgame);
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

        this.add("North", NewaccountJScrollPane);
        this.add("South", jp);

        this.setTitle("新用户注册");
        this.setSize(900,450);
        this.setLocation(400,150);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("创建新游戏账号")
                &&!jtfaccountName.getText().isEmpty()
                &&!jtfaccountgame.getText().isEmpty()){
            insertprocess();
            sid++;
            jtfaccountid.setText(String.valueOf(sid));
            jtfaccountName.setText("");
            jtfaccountgame.setText("");
            jCBgameStr = "";
        }
    }

    public void insertprocess(){
        String sid = jtfaccountid.getText().trim();
        String sname = jtfaccountName.getText().trim();
        String sgrade = jtfaccountgrade.getText().trim();
        String sdate = jtfaccountdate.getText().trim();
        String smoney = jtfaccountmoney.getText().trim();
        String sstatus = jtfaccountstatus.getText().trim();
        String saccuser = jtfaccountuser.getText().trim();
        String saccgame = jtfaccountgame.getText().trim();

        String sql = "insert into `gameaccount` values(";
        sql += sid + ",'";
        sql += sname + "','";
        sql += sgrade + "','";
        sql += sdate + "','";
        sql += smoney + "','";
        sql += sstatus + "','";
        sql += saccuser + "','";
        sql += saccgame + "');";
        try{
            if(dataOperation.executeUpdate(sql) < 1){
                System.out.println("updateProcess(). update database failed.");
            }else{
                JOptionPane.showMessageDialog(null,
                        "创建游戏账号成功！","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }
}
