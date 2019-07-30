import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mode extends JFrame
{
    //创建一个JLayeredPane用于分层的。
    JLayeredPane layeredPane;
    //创建一个Panel和一个Label用于存放图片，作为背景。
    JPanel jp;
    JLabel jl;
    ImageIcon image;

    public static void main(String args[]) {
        Mode l=new Mode();
        l.showMode();

    }


    public void showMode()
    {   MusicPlayer play = new MusicPlayer("src\\Project\\BackgroundMusic\\Mode.mp3");
        play.start();
        layeredPane=new JLayeredPane();
        image=new ImageIcon("src\\Project\\Photo\\3.png");//随便找一张图就可以看到效果。
        //创建背景的那些东西
        jp=new JPanel();
        jp.setBounds(0,0,image.getIconWidth(),image.getIconHeight());

        jl=new JLabel(image);
//		jl.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
        jp.add(jl);

        setTitle("选择界面");

        JButton button1=new javax.swing.JButton();
        button1.setBounds(145,115,195,450);
        button1.setContentAreaFilled(false);
        button1.setBorderPainted(false);
        button1.setBorder(BorderFactory.createRaisedBevelBorder());
        add(button1);

        JButton button2=new javax.swing.JButton();
        button2.setBounds(410,115,195,450);
        button2.setContentAreaFilled(false);
        button2.setBorderPainted(false);
        button2.setBorder(BorderFactory.createRaisedBevelBorder());
        add(button2);

        JButton button3=new javax.swing.JButton();
        button3.setBounds(675,115,195,450);
        button3.setContentAreaFilled(false);
        button3.setBorderPainted(false);
        button3.setBorder(BorderFactory.createRaisedBevelBorder());
        add(button3);

        JButton button4=new javax.swing.JButton();
        button4.setBounds(70,20,150,50);
        button4.setContentAreaFilled(false);
        button4.setBorderPainted(false);
        button4.setBorder(BorderFactory.createRaisedBevelBorder());
        add(button4);




        //将jp放到最底层。
        layeredPane.add(jp,JLayeredPane.DEFAULT_LAYER);
        //将...放到高一层的地方
        layeredPane.add(button1,JLayeredPane.MODAL_LAYER);

        layeredPane.add(button2,JLayeredPane.MODAL_LAYER);

        layeredPane.add(button3,JLayeredPane.MODAL_LAYER);

        layeredPane.add(button4,JLayeredPane.MODAL_LAYER);



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
                play.stop();
                MusicPlayer play = new MusicPlayer("src\\Project\\BackgroundMusic\\I.mp3");
                play.start();  //开启
                dispose();
                SelectMVM l=new SelectMVM();
                l.showSelections();

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
                dispose();
                SelectPVP l=new SelectPVP();
                l.showSelections();

                // 通过我们获取的登录界面对象，用dispose方法关闭它


            }
        });

        button3.addActionListener(new ActionListener(){
        //为按钮一设置事件接收器

        public void actionPerformed(ActionEvent e)
        {   play.stop();
            MusicPlayer play = new MusicPlayer("src\\Project\\BackgroundMusic\\I.mp3");
            play.start();  //开启
            dispose();
            SelectPVM l=new SelectPVM();
            l.showSelections();

            // 通过我们获取的登录界面对象，用dispose方法关闭它


        }
    });

        button4.addActionListener(new ActionListener(){
            //为按钮一设置事件接收器

            public void actionPerformed(ActionEvent e)
            {   play.stop();
                MusicPlayer play = new MusicPlayer("src\\Project\\BackgroundMusic\\I.mp3");
                play.start();  //开启
                // 通过我们获取的登录界面对象，用dispose方法关闭它
                dispose();


                // 通过我们获取的登录界面对象，用dispose方法关闭它


            }
        });

    }
}

