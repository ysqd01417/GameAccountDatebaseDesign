package GameAccount;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class GameMessage extends JFrame implements ActionListener {

    JLabel jlGame = null;
    JLabel jlText = null;//注释
    JLabel jlGamename = null;
    JLabel jlGametype = null;
    JLabel jlGamemanufacturer = null;
    JLabel jlGameaddress = null;

    JButton jbQuery = null;

    JComboBox<String> jcbGamename = null;
    JComboBox<String> jcbGametype = null;
    JComboBox<String> jcbGamemanufacturer = null;
    JComboBox<String> jcbGameaddress = null;

    JPanel jp1,jp2,jp3,jp4,jp5 = null;
    JPanel jpTop,jpBottom = null;
    JTable jtGameJTable = null;
    JScrollPane jspGameJScrollPane = null;
    Vector gameVector = null;
    Vector titleVector = null;

    private static DataOperation dataOperation;
    String Gamenamestr = null;
    String Gametypestr = null;
    String Gamemanufacturerstr = null;
    String Gameaddressstr = null;

    public GameMessage() {

        dataOperation = new DataOperation();

        jlGame = new JLabel("游戏信息表");
        jlText = new JLabel("注*：选项内容均未初始化,若查询请先切换至其他选项,若查看所有游戏，直接点击查询即可");
        jlText.setFont(new Font("宋体", Font.BOLD, 15));
        jlText.setForeground(Color.RED);
        jlGamename = new JLabel("游戏名称");
        jlGametype = new JLabel("游戏类型");
        jlGamemanufacturer = new JLabel("游戏厂商");
        jlGameaddress = new JLabel("游戏地区");

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

        jcbGamemanufacturer = new JComboBox<>();
        try{
            String sql = "select `manufacturer_name` from `manufacturer`;";
            dataOperation.connect();
            ResultSet rs = dataOperation.executeQuery(sql);
            while(rs.next()){
                jcbGamemanufacturer.addItem(rs.getString("manufacturer_name"));
            }
            dataOperation.disconnect();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }

        jcbGameaddress = new JComboBox<>();
        try{
            String sql = "select distinct `manufacturer_address` from `manufacturer`;";
            dataOperation.connect();
            ResultSet rs = dataOperation.executeQuery(sql);
            while(rs.next()){
                jcbGameaddress.addItem(rs.getString("manufacturer_address"));
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

        jcbGametype.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch(e.getStateChange()){
                    case ItemEvent.SELECTED:
                        Gametypestr = (String) e.getItem();
                        System.out.println("选中： " + Gametypestr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中：" + e.getItem());
                        break;
                }
            }
        });

        jcbGamemanufacturer.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch(e.getStateChange()){
                    case ItemEvent.SELECTED:
                        Gamemanufacturerstr = (String) e.getItem();
                        System.out.println("选中： " + Gamemanufacturerstr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中： " + e.getItem());
                        break;
                }
            }
        });

        jcbGameaddress.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch(e.getStateChange()){
                    case ItemEvent.SELECTED:
                        Gameaddressstr = (String) e.getItem();
                        System.out.println("选中： " + Gameaddressstr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中： " + e.getItem());
                        break;
                }
            }
        });

        gameVector = new Vector();
        titleVector = new Vector();

        titleVector.add("游戏编号");
        titleVector.add("游戏名称");
        titleVector.add("游戏类型");
        titleVector.add("游戏发布日期");
        titleVector.add("游戏评价");
        titleVector.add("游戏状态");
        titleVector.add("游戏厂商");
        titleVector.add("游戏地区");

        jtGameJTable = new JTable(gameVector, titleVector);
        jtGameJTable.setPreferredScrollableViewportSize(new Dimension(910, 220));
        jspGameJScrollPane = new JScrollPane(jtGameJTable);
        jspGameJScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jspGameJScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        /*jspGameJScrollPane.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

            }
        });*/

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jpTop = new JPanel();
        jpBottom = new JPanel();

        jp1.add(jlGame, BorderLayout.NORTH);
        jp2.add(jspGameJScrollPane);

        jp3.add(jlGamename);
        jp3.add(jcbGamename);
        jp3.add(jlGametype);
        jp3.add(jcbGametype);
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp3.setPreferredSize(new Dimension(35, 35));

        jp4.add(jlGamemanufacturer);
        jp4.add(jcbGamemanufacturer);
        jp4.add(jlGameaddress);
        jp4.add(jcbGameaddress);
        jp4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp4.setPreferredSize(new Dimension(35, 35));

        jp5.add(jlText);
        jp5.add(jbQuery);
        jp5.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp5.setPreferredSize(new Dimension(35, 35));

        jpTop.add(jp1);
        jpTop.add(jp2);

        jpBottom.setLayout(new GridLayout(3,1));
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

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("查询")) {
            queryProcess();
            Gamenamestr = null;
            Gametypestr = null;
            Gamemanufacturerstr = null;
            Gameaddressstr = null;
        }else{
            JOptionPane.showMessageDialog(null,
                    "请选择查询条件","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryProcess(){
        try{
            String sql = "select * from `game` join `manufacturer` " +
                    "on `game_manufacturer` = `manufacturer_id`";
            boolean judge = false;
            if(Gamenamestr != null){
                sql += " where ";
                sql += "`game_name` = '" + Gamenamestr + "'";
                judge = true;
            }
            if(Gametypestr != null ){
                if(judge){
                    sql += " and ";
                }else{
                    sql += " where ";
                }
                sql += "`game_type` = '" + Gametypestr + "'";
                judge = true;
            }
            if(Gamemanufacturerstr != null){
                if(judge){
                    sql += " and ";
                }else{
                    sql += " where ";
                }
                sql += "`manufacturer_name` = '" + Gamemanufacturerstr + "'";
            }
            if(Gameaddressstr != null){
                if(judge){
                    sql += " and ";
                }else{
                    sql += " where ";
                }
                sql += "`manufacturer_address` = '" + Gameaddressstr + "'";
            }
            sql += ";";
            dataOperation.connect();
            ResultSet rs = dataOperation.executeQuery(sql);
            gameVector.clear();
            while(rs.next()){
                Vector<String> v = new Vector<>();
                v.add(rs.getString("game_id"));
                v.add(rs.getString("game_name"));
                v.add(rs.getString("game_type"));
                v.add(rs.getString("game_date"));
                v.add(rs.getString("game_evaluation"));
                v.add(rs.getString("game_status"));
                v.add(rs.getString("manufacturer_name"));
                v.add(rs.getString("manufacturer_address"));
                gameVector.add(v);
            }
            jtGameJTable.updateUI();
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }finally {
            dataOperation.disconnect();
        }
    }
}





