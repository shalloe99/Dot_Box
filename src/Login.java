import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//首先实现登录界面
public class Login {

    //主函数入口


    public void showUI() {
        MusicPlayer play = new MusicPlayer("src\\Project\\BackgroundMusic\\Login.mp3");
        play.start();
        javax.swing.JFrame jf=new javax.swing.JFrame();
        jf.setTitle("登录界面");
        jf.setSize(340,500);//只对顶级容器有效
        jf.setDefaultCloseOperation(3);//窗体关闭时结束程序
        jf.setLocationRelativeTo(null);//居中
        jf.setResizable(false);

        //选择布局类型，定义流式布局的对象,并且设置每个组件之间相隔5cm
        java.awt.FlowLayout fl=new java.awt.FlowLayout(FlowLayout.CENTER,5,5);
        jf.setLayout(fl);//设置顶级容器的布局为流式布局

        //设置格式大小
        java.awt.Dimension dim1=new java.awt.Dimension(340,300);//图片大小
        java.awt.Dimension dim2=new java.awt.Dimension(50, 50);//标签的大小
        java.awt.Dimension dim3=new java.awt.Dimension(250, 30);//输入框的大小
        java.awt.Dimension dim4=new java.awt.Dimension(100, 40);//按钮的大小

        //添加组件
        //添加图片，先要把图片加载到内存中。利用IamgeIcon类把图片加载到内存
        javax.swing.ImageIcon icon=new javax.swing.ImageIcon("src\\Project\\Photo\\0.jpg");
        JLabel labpic=new JLabel(icon);//不可以直接把图片加到顶级容器中，需要先将其设置为标签
        labpic.setPreferredSize(dim1);
        jf.add(labpic);//再将标签加到顶级容器中

        //添加“账号”标签
        JLabel labname=new JLabel();
        labname.setText("账号：");
        labname.setPreferredSize(dim2);
        jf.add(labname);

        //添加账号输入框，并添加监控事件
        JTextField jt=new JTextField();
        jt.setPreferredSize(dim3);
        jf.add(jt);

        //添加“密码”标签
        JLabel labpassword=new JLabel();
        labpassword.setText("密码：");
        labpassword.setPreferredSize(dim2);
        jf.add(labpassword);

        //添加密码输入框，并添加监控事件
        JPasswordField jp=new JPasswordField();
        jp.setPreferredSize(dim3);
        jf.add(jp);

        //添加一个button按钮
        javax.swing.JButton button=new javax.swing.JButton();
        button.setText("登录");
        button.setPreferredSize(dim4);
        jf.add(button);
        jf.setVisible(true);
        button.setContentAreaFilled(false);

        button.addActionListener(new ActionListener(){
            //为按钮一设置事件接收器

            public void actionPerformed(ActionEvent e)
            {   // Add security system
                //if(jt.getText().equals("黄德淳")&&jp.getText().equals("123456")) {
                 //开启
                play.stop();
                MusicPlayer play = new MusicPlayer("src\\Project\\BackgroundMusic\\I.mp3");
                play.start();  //开启
                jf.dispose();
                Hall l=new Hall();
                l.showHall();

                // 通过我们获取的登录界面对象，用dispose方法关闭它

                //}
            }
        });



    }
}
