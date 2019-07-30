import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hall extends JFrame
{

    JLayeredPane layeredPane;//创建一个用于分层的JLayeredPane
    JPanel jp;
    JLabel jl;
    ImageIcon image;


    public static void main(String args[]) {
        Hall l=new Hall();
        l.showHall();

    }


    public void showHall()
    {   MusicPlayer play = new MusicPlayer("src\\Project\\BackgroundMusic\\Login.mp3");
        play.start();
        layeredPane=new JLayeredPane();
        image=new ImageIcon("src\\Project\\Photo\\2.png");//随便找一张图就可以看到效果。
        //创建背景的那些东西
        jp=new JPanel();
        jp.setBounds(0,0,image.getIconWidth(),image.getIconHeight());

        jl=new JLabel(image);
//		jl.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
        jp.add(jl);

        setTitle("选择界面");

        JButton button1=new javax.swing.JButton();
        button1.setBounds(844,120,630,150);
        button1.setContentAreaFilled(false);
        button1.setBorderPainted(false);
        button1.setBorder(BorderFactory.createRaisedBevelBorder());
        add(button1);

        JButton button2=new javax.swing.JButton();
        button2.setBounds(1055,300,280,100);
        button2.setContentAreaFilled(false);
        button2.setBorderPainted(false);
        button2.setBorder(BorderFactory.createRaisedBevelBorder());
        add(button2);






        //将jp放到最底层。
        layeredPane.add(jp,JLayeredPane.DEFAULT_LAYER);
        //将...放到高一层的地方
        layeredPane.add(button1,JLayeredPane.MODAL_LAYER);
        layeredPane.add(button2,JLayeredPane.MODAL_LAYER);



        this.setLocationRelativeTo(null);//居中
        this.setLayeredPane(layeredPane);
        this.setSize(image.getIconWidth(),image.getIconHeight());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        button1.addActionListener(new ActionListener(){
            //为按钮一设置事件接收器

            public void actionPerformed(ActionEvent e)
            {   play.stop();
                MusicPlayer play = new MusicPlayer("src\\Project\\BackgroundMusic\\I.mp3");
                play.start();  //开启

                Mode l=new Mode();
                l.showMode();

                // 通过我们获取的登录界面对象，用dispose方法关闭它


            }
        });

        button2.addActionListener(new ActionListener(){
            //为按钮一设置事件接收器

            public void actionPerformed(ActionEvent e)
            {
                play.stop();
                MusicPlayer play = new MusicPlayer("src\\Project\\BackgroundMusic\\I.mp3");
                play.start();  //开启


                Division division=new Division();
                division.showDivision();

                // 通过我们获取的登录界面对象，用dispose方法关闭它


            }
        });


    }
}

