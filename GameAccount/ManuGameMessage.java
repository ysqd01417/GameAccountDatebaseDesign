package GameAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ManuGameMessage extends JFrame implements ActionListener {


    JLabel jlManuGame = null;
    JLabel jlGameId = null;
    JLabel jlGamename = null;
    JLabel jlGameType = null;
    JLabel jlGamedate = null;
    JLabel jlGameEvaluation = null;
    JLabel jlGamestatus = null;

    JTextField jtfGameId = null;
    JTextField jtfGamename = null;
    JTextField jtfGameType = null;
    JTextField jtfGamedate = null;
    JTextField jtfGameEvaluation = null;
    JTextField jtfGamestatus = null;

    JButton jbupdateGame = null;
    JButton jbcreateGame = null;
    JButton jbqueryGame = null;
    JButton jbdeleteGame = null;

    JPanel jp1, jp2, jp3, jp4, jp5 = null;
    JPanel jpTop,jpBottoom = null;
    JTable jtaGametable = null;
    JScrollPane jspGamescroll = null;
    Vector GameVector = null;
    Vector titleVector = null;

    private static DataOperation dataOperation;
    private static NewGame ng;
    private ResultSet prs;

    public ManuGameMessage(ResultSet rs) {
        this.prs = rs;

        jlManuGame = new JLabel("个人游戏账号表");
        jlGameId = new JLabel("游戏账户");
        jlGamename = new JLabel("游戏名称");
        jlGameType = new JLabel("游戏类型");
        jlGamedate = new JLabel("创建日期");
        jlGameEvaluation = new JLabel("游戏评价");
        jlGamestatus = new JLabel("游戏状态");

        jtfGameId = new JTextField(15);
        jtfGameId.setEditable(false);
        jtfGamename = new JTextField(15);
        jtfGameType = new JTextField(15);
        jtfGamedate = new JTextField(15);
        jtfGamedate.setEditable(false);
        jtfGameEvaluation = new JTextField(15);
        jtfGamestatus = new JTextField(15);
        jtfGamestatus.setEditable(false);

        jbqueryGame = new JButton("查看");
        jbqueryGame.setFont(new Font("宋体", Font.BOLD, 20));
        jbupdateGame = new JButton("更新信息");
        jbupdateGame.setFont(new Font("宋体", Font.BOLD, 20));
        jbdeleteGame = new JButton("删除此游戏");
        jbdeleteGame.setFont(new Font("宋体", Font.BOLD, 20));
        jbcreateGame = new JButton("创建新游戏");
        jbcreateGame.setFont(new Font("宋体", Font.BOLD, 20));

        jbqueryGame.addActionListener(this);
        jbupdateGame.addActionListener(this);
        jbdeleteGame.addActionListener(this);
        jbcreateGame.addActionListener(this);

        GameVector = new Vector();
        titleVector = new Vector();

        titleVector.add("游戏账号");
        titleVector.add("游戏名称");
        titleVector.add("游戏类型");
        titleVector.add("创建日期");
        titleVector.add("游戏评价");
        titleVector.add("游戏状态");

        jtaGametable = new JTable(GameVector, titleVector);
        jtaGametable.setPreferredScrollableViewportSize(new Dimension(910, 220));
        jspGamescroll = new JScrollPane(jtaGametable);
        jspGamescroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jspGamescroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        jtaGametable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
                Vector<String> v = new Vector();
                v = (Vector) GameVector.get(row);

                jtfGameId.setText((String) v.get(0));
                jtfGamename.setText((String) v.get(1));
                jtfGameType.setText((String) v.get(2));
                jtfGamedate.setText((String) v.get(3));
                jtfGameEvaluation.setText((String) v.get(4));
                jtfGamestatus.setText((String) v.get(5));
            }
        });

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jpTop = new JPanel();
        jpBottoom = new JPanel();

        jp1.add(jlManuGame);
        jp2.add(jspGamescroll);

        jp3.add(jlGameId);
        jp3.add(jtfGameId);
        jp3.add(jlGamename);
        jp3.add(jtfGamename);
        jp3.add(jlGameType);
        jp3.add(jtfGameType);
        jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp3.setPreferredSize(new Dimension(35, 35));

        jp4.add(jlGamedate);
        jp4.add(jtfGamedate);
        jp4.add(jlGameEvaluation);
        jp4.add(jtfGameEvaluation);
        jp4.add(jlGamestatus);
        jp4.add(jtfGamestatus);
        jp4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp4.setPreferredSize(new Dimension(35, 35));

        jp5.add(jbqueryGame);
        jp5.add(jbupdateGame);
        jp5.add(jbdeleteGame);
        jp5.add(jbcreateGame);
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
        this.setTitle("厂商游戏表");
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
            jtfGameId.setText("");
            jtfGamename.setText("");
            jtfGameType.setText("");
            jtfGamedate.setText("");
            jtfGameEvaluation.setText("");
            jtfGamestatus.setText("");
        }else if(e.getActionCommand().equals("更新信息")
                &&!jtfGamename.getText().isEmpty()
                &&!jtfGameType.getText().isEmpty()
                &&!jtfGamedate.getText().isEmpty()
                &&!jtfGameEvaluation.getText().isEmpty()){
            updateprocess();
        }else if(e.getActionCommand().equals("删除此游戏")
                &&!jtfGameId.getText().isEmpty()) {
            deleteaccount();
        }else{
            Gamecreate(prs);
        }
    }

    public void queryprocess(ResultSet rs){
        try{
            String sql = "select * from `game` " +
                    "where `game_manufacturer` = '" +
                    rs.getInt("manufacturer_id") + "';";
            dataOperation.connect();
            ResultSet qrs = dataOperation.executeQuery(sql);
            GameVector.clear();
            while(qrs.next()){
                Vector<String> v = new Vector();
                v.add(qrs.getString("game_id"));
                v.add(qrs.getString("game_name"));
                v.add(qrs.getString("game_type"));
                v.add(qrs.getString("game_date"));
                v.add(qrs.getString("game_evaluation"));
                v.add(qrs.getString("game_status"));
                GameVector.add(v);
            }
            jtaGametable.updateUI();
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }finally {
            dataOperation.disconnect();
        }
    }

    public void updateprocess(){
        String upgamename = jtfGamename.getText().trim();
        String upgametype = jtfGameType.getText().trim();
        String upgamedate = jtfGamedate.getText().trim();
        String upgameevaluation = jtfGameEvaluation.getText().trim();
        String gameid = jtfGameId.getText().trim();

        String sql = "update `game` set `game_name` = '"
                + upgamename + "', `game_type` = '"
                + upgametype + "', `game_date` = '"
                + upgamedate + "', `game_evaluation` = '"
                + upgameevaluation + "' where `game_id` = '"
                + gameid + "';";
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
        String id = jtfGameId.getText().trim();

        String sql = "delete from `game` " +
                "where `game_id` = '" + id + "'";
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

    public void Gamecreate(ResultSet rs) {
        String sql="";
        int id=10000;
        int mid=0;
        try{
            mid = rs.getInt("manufacturer_id");
            sql = "select max(`game_id`) from `game`;";
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
            this.ng = new NewGame(id+1,mid);
        }
    }

}
