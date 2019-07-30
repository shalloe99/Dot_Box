import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Division extends JFrame
{
    //创建一个JLayeredPane用于分层的。
    JLayeredPane layeredPane;
    //创建一个Panel和一个Label用于存放图片，作为背景。
    JPanel jp;
    JLabel jl;
    ImageIcon image;

    public static void main(String args[]) {
        Division l=new Division();
        l.showDivision();

    }


    public void showDivision()
    {   MusicPlayer play = new MusicPlayer("src\\Project\\BackgroundMusic\\Division.mp3");
        play.start();  //开启
        layeredPane=new JLayeredPane();
        image=new ImageIcon("src\\Project\\Photo\\4.jpg");//随便找一张图就可以看到效果。
        //创建背景的那些东西
        jp=new JPanel();
        jp.setBounds(0,0,image.getIconWidth(),image.getIconHeight());

        jl=new JLabel(image);
//		jl.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
        jp.add(jl);

        setTitle("分工情况");

        JLabel text1=new JLabel();
        text1.setText("人机模式、人人模式、机机模式实现，背景音乐播放实现，GUI实现");
        text1.setBounds(90,175,1400,100);
        text1.setFont(new java.awt.Font("Dialog", 1, 25));
        add(text1);
        setBackground(Color.BLACK);

        JLabel text2=new JLabel();
        text2.setText("三种模式方块涂色问题Debug，GUI背景图片显示层级问题Debug，机器延迟时间下思路，记分规则思路");
        text2.setBounds(90,275,1400,100);
        text2.setFont(new java.awt.Font("Dialog", 1, 25));
        add(text2);
        setBackground(Color.BLACK);

        JButton button=new javax.swing.JButton();
        button.setBounds(90,75,100,50);
        button.setText("返回大厅");
        button.setFont(new java.awt.Font("Dialog", 1, 15));
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        add(button);





        //将jp放到最底层。
        layeredPane.add(jp,JLayeredPane.DEFAULT_LAYER);
        //将...放到高一层的地方
        layeredPane.add(text1,JLayeredPane.MODAL_LAYER);

        layeredPane.add(text2,JLayeredPane.MODAL_LAYER);

        layeredPane.add(button,JLayeredPane.MODAL_LAYER);


        this.setLocationRelativeTo(null);//居中
        this.setLayeredPane(layeredPane);
        this.setSize(image.getIconWidth(),image.getIconHeight());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        button.addActionListener(new ActionListener(){
            //为按钮一设置事件接收器

            public void actionPerformed(ActionEvent e)
            {   dispose();
                play.stop();
                MusicPlayer play = new MusicPlayer("src\\Project\\BackgroundMusic\\I.mp3");
                play.start();

                //开启
                // 通过我们获取的登录界面对象，用dispose方法关闭它


            }
        });


    }
}
