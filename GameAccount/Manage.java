package GameAccount;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Manage extends JFrame implements ActionListener {

    JButton JBMessage = null;
    JButton JBPerGame = null;
    JButton JBAllGame = null;
    JButton JBLogin = null;

    JPanel jp1,jp2,jp3,jp4 = null;
    JPanel jp = null;

    private static DataOperation dataoperation;
    private static UserMessage p;
    private static ManufacturerMessage manu;
    private static QuerySelect q;
    private static PersonGameMessage pgm;
    private static ManuGameMessage mgm;
    private static AdminMessage admin;
    private static Login l;
    private ResultSet mrs;

    public Manage(ResultSet rs) {
        dataoperation = new DataOperation();
        this.mrs = rs;

        JBMessage = new JButton("个人信息查询");
        JBMessage.setFont(new Font("宋体", Font.BOLD, 20));
        JBPerGame = new JButton("个人游戏信息查询");
        JBPerGame.setFont(new Font("宋体", Font.BOLD, 20));
        JBAllGame = new JButton("所有游戏查询");
        JBAllGame.setFont(new Font("宋体", Font.BOLD, 20));
        JBLogin = new JButton("返回登录");
        JBLogin.setFont(new Font("宋体", Font.BOLD, 20));

        JBMessage.addActionListener(this);
        JBPerGame.addActionListener(this);
        JBAllGame.addActionListener(this);
        JBLogin.addActionListener(this);

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp = new JPanel();

        jp1.add(JBMessage);
        jp1.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp1.setPreferredSize(new Dimension(250, 100));

        jp2.add(JBPerGame);
        jp2.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp2.setPreferredSize(new Dimension(250, 100));

        jp3.add(JBAllGame);
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp3.setPreferredSize(new Dimension(250, 100));

        jp4.add(JBLogin);
        jp4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp4.setPreferredSize(new Dimension(250, 100));

        jp.setLayout(new GridLayout(4, 1));
        jp.add(jp1);
        jp.add(jp2);
        jp.add(jp3);
        jp.add(jp4);

        this.add("South", jp);

        this.setLayout(new BorderLayout());
        this.add(jp, BorderLayout.SOUTH);

        this.setTitle("管理界面");
        this.setSize(500,475);
        this.setLocation(475,125);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("个人信息查询")){
            System.out.println("个人信息查询");
            queryper(mrs);
        }else if(e.getActionCommand().equals("个人游戏信息查询")){
            System.out.println("个人游戏信息查询");
            try{
                int num = Integer.parseInt(mrs.getString(1));
                if(num<1000){
                    if(mrs.getString("user_identity").equals("玩家")){
                        this.pgm = new PersonGameMessage(mrs);
                    }else{
                        this.admin = new AdminMessage();
                    }
                }else{
                    this.mgm = new ManuGameMessage(mrs);
                }
            }catch(Exception event){
                JOptionPane.showMessageDialog(null,
                        "发生错误","错误",JOptionPane.ERROR_MESSAGE);
            }
        }else if(e.getActionCommand().equals("所有游戏查询")){
            System.out.println("所有游戏查询");
            this.q = new QuerySelect();
        }else{
            this.dispose();
            this.l = new Login();
        }
    }

    public void queryper(ResultSet rs){
        try{
            if(rs != null){
                int num = Integer.parseInt(rs.getString(1));//判断是用户还是厂商
                if(num < 1000){
                    this.p = new UserMessage(rs);
                }else{
                    this.manu = new ManufacturerMessage(rs);
                }
            }else{
                JOptionPane.showMessageDialog(null,
                        "没有找到用户","错误",JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "查询过程发生错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

}







