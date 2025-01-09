package GameAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;

public class NewGame extends JFrame implements ActionListener {

    JLabel jlnewgame = null;
    JLabel jlgameid = null;
    JLabel jlgameName = null;
    JLabel jlgametype = null;
    JLabel jlgamedate = null;
    JLabel jlgameevaluation = null;
    JLabel jlgamestatus = null;
    JLabel jlgamemanu = null;

    //JTextField jtfnewuser = null;
    JTextField jtfgameid = null;
    JTextField jtfgameName = null;
    JTextField jtfgametype = null;
    JTextField jtfgamedate = null;
    JTextField jtfgameevaluation = null;
    JTextField jtfgamestatus = null;
    JTextField jtfgamemanu = null;

    JButton jbInsert = null;

    JPanel jp1,jp2,jp3,jp4,jp5 = null;
    JPanel jp = null;

    JScrollPane NewgameJScrollPane = null;

    private static DataOperation dataOperation ;
    private static int sid;
    //private ResultSet mrs;
    LocalDate date;

    public NewGame(int id, int manuid) {
        dataOperation = new DataOperation();
        this.sid = id;
        date = LocalDate.now();

        jlnewgame = new JLabel("新游戏信息");
        jlnewgame.setFont(new Font("宋体", Font.BOLD, 25));
        jlgameid = new JLabel("编号*");
        jlgameName = new JLabel("名字*");
        jlgametype = new JLabel("类型*");
        jlgamedate = new JLabel("日期*");
        jlgameevaluation = new JLabel("评价");
        jlgamestatus = new JLabel("状态*");
        jlgamemanu = new JLabel("厂商id*");

        jtfgameid = new JTextField(15);
        jtfgameid.setEditable(false);
        jtfgameid.setText(String.valueOf(sid));
        jtfgameName = new JTextField(15);
        jtfgametype = new JTextField(15);
        jtfgamedate = new JTextField(15);
        jtfgamedate.setEditable(false);
        jtfgamedate.setText(date.toString());
        jtfgameevaluation = new JTextField(15);
        jtfgamestatus = new JTextField(15);
        jtfgamestatus.setEditable(false);
        jtfgamestatus.setText("T");
        jtfgamemanu = new JTextField(15);
        jtfgamemanu.setEditable(false);
        jtfgamemanu.setText(String.valueOf(manuid));

        jbInsert = new JButton("创建新游戏");
        jbInsert.setFont(new Font("宋体", Font.BOLD, 20));
        jbInsert.addActionListener(this);

        NewgameJScrollPane = new JScrollPane();

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp = new JPanel();

        jp1.add(jlnewgame);
        jp1.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp1.setPreferredSize(new Dimension(150, 80));

        jp2.add(jlgameid);
        jp2.add(jtfgameid);
        jp2.add(jlgameName);
        jp2.add(jtfgameName);
        jp2.setLayout((new FlowLayout(FlowLayout.CENTER)));
        jp2.setPreferredSize(new Dimension(150, 80));

        jp3.add(jlgametype);
        jp3.add(jtfgametype);
        jp3.add(jlgamedate);
        jp3.add(jtfgamedate);
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp3.setPreferredSize(new Dimension(150, 80));

        jp4.add(jlgameevaluation);
        jp4.add(jtfgameevaluation);
        jp4.add(jlgamestatus);
        jp4.add(jtfgamestatus);
        jp4.add(jlgamemanu);
        jp4.add(jtfgamemanu);
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

        this.add("North", NewgameJScrollPane);
        this.add("South", jp);

        this.setTitle("新游戏创建");
        this.setSize(900,450);
        this.setLocation(400,150);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("创建新游戏")
                &&!jtfgameName.getText().isEmpty()
                &&!jtfgametype.getText().isEmpty()){
            insertprocess();
            sid++;
            jtfgameid.setText(String.valueOf(sid));
            jtfgameName.setText("");
            jtfgametype.setText("");
            jtfgameevaluation.setText("");
        }
    }

    public void insertprocess(){
        String sid = jtfgameid.getText().trim();
        String sname = jtfgameName.getText().trim();
        String stype = jtfgametype.getText().trim();
        String sdate = jtfgamedate.getText().trim();
        String sevaluation = jtfgameevaluation.getText().trim();
        String sstatus = jtfgamestatus.getText().trim();
        String smanu = jtfgamemanu.getText().trim();

        String sql = "insert into `game` values(";
        sql += sid + ",'";
        sql += sname + "','";
        sql += stype + "','";
        sql += sdate + "','";
        sql += sevaluation + "','";
        sql += sstatus + "','";
        sql += smanu + "');";
        try{
            if(dataOperation.executeUpdate(sql) < 1){
                System.out.println("updateProcess(). update database failed.");
            }else{
                JOptionPane.showMessageDialog(null,
                        "创建游戏成功！","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

}
