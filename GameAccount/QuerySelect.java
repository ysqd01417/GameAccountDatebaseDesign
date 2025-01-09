package GameAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuerySelect extends JFrame implements ActionListener {

    JButton jbgQuery = null;
    JButton jbgaQuery = null;

    JPanel jp1,jp2 = null;
    JPanel jp = null;

    private static DataOperation dataOperation;
    private static GameMessage game;
    private static GameAccountMessage ga;

    public QuerySelect() {
        jbgQuery = new JButton("游戏信息查询");
        jbgQuery.setFont(new Font("宋体", Font.BOLD, 20));
        jbgaQuery = new JButton("游戏账号查询");
        jbgaQuery.setFont(new Font("宋体", Font.BOLD, 20));

        jbgQuery.addActionListener(this);
        jbgaQuery.addActionListener(this);

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp = new JPanel();


        jp1.add(jbgQuery);
        jp1.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp1.setPreferredSize(new Dimension(250, 100));

        jp2.add(jbgaQuery);
        jp2.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp2.setPreferredSize(new Dimension(250, 100));

        jp.setLayout(new GridLayout(2, 1));
        jp.add(jp1);
        jp.add(jp2);

        this.add("South",jp);

        this.setLayout(new BorderLayout());
        this.add(jp,BorderLayout.SOUTH);

        this.setTitle("查询选择");
        this.setSize(350,300);
        this.setLocation(500,150);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        dataOperation = new DataOperation();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("游戏信息查询")) {
            System.out.println("游戏信息查询");
            this.game = new GameMessage();
        }else if(e.getActionCommand().equals("游戏账号查询")){
            System.out.println("游戏账号查询");
            this.ga = new GameAccountMessage();
        }
    }
}






