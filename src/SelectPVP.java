import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectPVP extends JFrame
{
    //创建一个JLayeredPane用于分层的。
    JLayeredPane layeredPane;
    //创建一个Panel和一个Label用于存放图片，作为背景。
    JPanel jp;
    JLabel jl;
    ImageIcon image;

    public static void main(String args[]) {
        SelectPVP l=new SelectPVP();
        l.showSelections();

    }

    public void showSelections()
    {
        MusicPlayer play = new MusicPlayer("src\\Project\\BackgroundMusic\\Mode.mp3");
        play.start();
        layeredPane=new JLayeredPane();
        image=new ImageIcon("src\\Project\\Photo\\1.jpg");//随便找一张图就可以看到效果。
        //创建背景的那些东西
        jp=new JPanel();
        jp.setBounds(0,0,image.getIconWidth(),image.getIconHeight());

        jl=new JLabel(image);
//		jl.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
        jp.add(jl);

        setTitle("选择界面");

        JButton button1=new javax.swing.JButton();
        button1.setText("确定");
        button1.setBounds(232,210,70,40);
        button1.setContentAreaFilled(false);
        button1.setBorder(BorderFactory.createRaisedBevelBorder());
        button1.setFont(new java.awt.Font("Dialog", 1, 10));
        add(button1);

        JButton button2=new javax.swing.JButton();
        button2.setBounds(10,10,90,25);
        button2.setText("返回大厅");
        button2.setContentAreaFilled(false);
        button2.setBorder(BorderFactory.createRaisedBevelBorder());
        button2.setFont(new java.awt.Font("Dialog", 1, 10));
        add(button2);

        JLabel title=new JLabel();
        title.setText("人类vs人类");
        title.setBounds(217,20,100,20);
        title.setFont(new java.awt.Font("Dialog", 1, 15));
        add(title);
        setBackground(Color.BLACK);

        JLabel horizontal1=new JLabel();
        horizontal1.setText("点数(水平,竖直)：");
        horizontal1.setBounds(167,60,100,20);
        add(horizontal1);
        setBackground(Color.BLACK);

        //添加账号输入框，并添加监控事件
        JTextField horizontal2=new JTextField();
        horizontal2.setBounds(267,60,100,20);
        add(horizontal2);

        JLabel vertical1=new JLabel();
        vertical1.setText("颜色(红色/蓝色)：");
        vertical1.setBounds(167,100,100,20);
        add(vertical1);
        setBackground(Color.BLACK);

        //添加账号输入框，并添加监控事件
        JTextField vertical2=new JTextField();
        vertical2.setBounds(267,100,100,20);
        add(vertical2);





        //将jp放到最底层。
        layeredPane.add(jp,JLayeredPane.DEFAULT_LAYER);
        //将...放到高一层的地方
        layeredPane.add(title,JLayeredPane.MODAL_LAYER);

        layeredPane.add(button1,JLayeredPane.MODAL_LAYER);

        layeredPane.add(button2,JLayeredPane.MODAL_LAYER);

        layeredPane.add(horizontal1,JLayeredPane.MODAL_LAYER);

        layeredPane.add(horizontal2,JLayeredPane.MODAL_LAYER);

        layeredPane.add(vertical1,JLayeredPane.MODAL_LAYER);

        layeredPane.add(vertical2,JLayeredPane.MODAL_LAYER);




        this.setLocationRelativeTo(null);//居中
        this.setLayeredPane(layeredPane);
        this.setSize(image.getIconWidth(),image.getIconHeight());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        button1.addActionListener(new ActionListener(){
            //为按钮一设置事件接收器

            public void actionPerformed(ActionEvent e)
            {
                //开启
                MusicPlayer play1 = new MusicPlayer("src\\Project\\BackgroundMusic\\I.mp3");
                play1.start();  //开启
                // 通过我们获取的登录界面对象，用dispose方法关闭它
                dispose();
                MusicPlayer play2 = new MusicPlayer("src\\Project\\BackgroundMusic\\PVP.mp3");
                play2.start();


                if(horizontal2.getText().equals("2")){
                    PVP33 pvp = new PVP33(2,vertical2.getText());
                    pvp.setVisible(true);
                }
                if(horizontal2.getText().equals("3")){
                    PVP33 pvp = new PVP33(3,vertical2.getText());
                    pvp.setVisible(true);
                }
                if(horizontal2.getText().equals("4")){
                    PVP33 pvp = new PVP33(4,vertical2.getText());
                    pvp.setVisible(true);
                }
                if(horizontal2.getText().equals("5")){
                    PVP33 pvp = new PVP33(5,vertical2.getText());
                    pvp.setVisible(true);
                }
                if(horizontal2.getText().equals("6")){
                    PVP33 pvp = new PVP33(6,vertical2.getText());
                    pvp.setVisible(true);
                }
                if(horizontal2.getText().equals("7")){
                    PVP33 pvp = new PVP33(7,vertical2.getText());
                    pvp.setVisible(true);
                }
                if(horizontal2.getText().equals("8")){
                    PVP33 pvp = new PVP33(8,vertical2.getText());
                    pvp.setVisible(true);
                }
                if(horizontal2.getText().equals("9")){
                    PVP33 pvp = new PVP33(9,vertical2.getText());
                    pvp.setVisible(true);
                }
                if(horizontal2.getText().equals("10")){
                    PVP33 pvp = new PVP33(10,vertical2.getText());
                    pvp.setVisible(true);
                }

            }
        });

        button2.addActionListener(new ActionListener(){
            //为按钮一设置事件接收器

            public void actionPerformed(ActionEvent e)
            {
                //开启
                play.stop();
                MusicPlayer play = new MusicPlayer("Project\\BackgroundMusic\\I.mp3");
                play.start();  //开启
                // 通过我们获取的登录界面对象，用dispose方法关闭它
                dispose();
            }
        });






    }
}

