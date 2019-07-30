
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;


public class MVM33 extends JFrame {
    // Variables initialize
    JLabel title1=new JLabel();
    JLabel title2=new JLabel();
    JLabel title3=new JLabel();

    public static String term;

    public static int player1_score = 0;
    public static int player2_score = 0;


    public static int squares = 0;
    public static int new_squares = 0;
    public int a,b,A,B,counter;
    public Random random;

    public ArrayList<Integer> array;

    private ArrayList<ArrayList<EdgeComponent>> edgesRows = new ArrayList<>();
    private ArrayList<ArrayList<EdgeComponent>> edgesCols = new ArrayList<>();
    private ArrayList<ArrayList<RectangleComponent>> rectangles = new ArrayList<>();
    private Color currentColor;
    private Color firstColor;
    private Color secondColor;
    Component component;
    EdgeComponent edgeComponent;
    int[][] rows;
    int[][] cols;
    boolean check = true;
    // Variables end;




    public MVM33(int d,String c){// Constructor initialize
        A = 0;
        B = 0;
        a = d;
        b = d;
        term = "Player01";
        counter = 0;
        array = new ArrayList<Integer>();
        random = new Random();

        if (c.equals("红色")) {
            currentColor = Color.RED;
            firstColor = Color.RED;
            secondColor = Color.BLUE;
        }else if (c.equals("蓝色")) {
            currentColor = Color.BLUE;
            firstColor = Color.BLUE;
            secondColor = Color.RED;
        }


        initialize();
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Perform a task...
                run();
                if (isWinner()){// If someone win,stop the repetition
                    ((Timer)evt.getSource()).stop();
                }
                //update();
            }
        };
        Timer timer = new Timer(1000 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();
    }// Constructor ends
    public void initialize() {
        // initialize method
        //Color Setup




        // Status Check System set up
        rows = new int[a][a-1];
        cols = new int[b-1][b];
        for(int i = 0;i < a;i++)
            for (int j = 0;j<a-1;j++)
                rows[i][j] = 0;
        for(int i = 0;i < b-1;i++)
            for (int j = 0;j<b;j++)
                cols[i][j] = 0;
            //先分别给这两个二维数组随便赋值
        int width = 190 + 100 * (a - 1);
        int height = 200 + 100 * (b - 1);
        //JFrame setup
        setTitle("CS102A Project Demo");
        setSize(width, height);
        setLocationRelativeTo(null);//使窗口显示到屏幕中央
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//使用 System exit 方法退出当前JFrame，换言之是让按右上角x键时窗口能被关闭。
        setLayout(null);//默认流式布局 flowLayout
        MVM33.this.getContentPane().setBackground(Color.WHITE);// Set Background this.getContentPane()用来初始化一个容器

        title1.setText("Player1Score:"+player1_score);
        title1.setBounds(30,30,200,20);
        title1.setFont(new java.awt.Font("Dialog", 1, 15));
        add(title1);

        title2.setText("Player2Score:"+player2_score);
        title2.setBounds(30,50,200,20);
        title2.setFont(new java.awt.Font("Dialog", 1, 15));
        add(title2);


        title3.setBounds(30,10,200,20);
        title3.setFont(new java.awt.Font("Dialog", 1, 15));
        add(title3);





        //Dot & Edges setup
        int k = 0;
        int l = 0;

        while (k <= a - 2) {
            ArrayList<EdgeComponent> Temp_edgesRows = new ArrayList<>();
            for (int m = 0; m <= b - 1; m++) {//horizontal
                Temp_edgesRows.add(new EdgeComponent(85 + 100 * k, 80 + 100 * m, 100, 10));
            }
            edgesRows.add(Temp_edgesRows);
            k++;
        }
        while (l <= a - 1) {
            ArrayList<EdgeComponent> Temp_edgesCols = new ArrayList<>();
            for (int n = 0; n <= b - 2; n++) {//vertical
                Temp_edgesCols.add(new EdgeComponent(80 + 100 * l, 85 + 100 * n, 10, 100));//�洢���������߶�
            }
            edgesCols.add(Temp_edgesCols);
            l++;
        }
        for (ArrayList<EdgeComponent> eArray : edgesRows) {
            for (EdgeComponent e: eArray) {
                e.setVisible(false);
                getContentPane().add(e);
            }
        }
        for (ArrayList<EdgeComponent> eArray : edgesCols) {
            for (EdgeComponent e: eArray) {
                e.setVisible(false);
                getContentPane().add(e);
            }
        }
        // Edge ends
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                DotComponent dotComponent = new DotComponent(75 + 100 * i, 75 + 100 * j, 20);
                getContentPane().add(dotComponent);
            }
        }// Dots ends
        // Dot & Edges setup ends
        // Rectangle set up
        for (int i = 0; i < a-1;i++ ) {
            ArrayList<RectangleComponent> Temp_Rectangle = new ArrayList<>();
            for (int j = 0;j < b-1;j++ ) {
                RectangleComponent temp_rectangle = new RectangleComponent(75 + 100 * i, 75 + 100 * j);
                Temp_Rectangle.add(temp_rectangle);
                getContentPane().add(temp_rectangle);
            }
            rectangles.add(Temp_Rectangle);
        }// Rectangle setup ends

    }/* initialize ends*/


    public boolean isWinner(){
        boolean check = false;
        new_squares = completedSquares();
        if (new_squares > squares) {
            if (currentColor == firstColor) {// Player01 scores
                player1_score += (new_squares - squares);
            } else if (currentColor == secondColor) {// Player02 scores
                player2_score += (new_squares - squares);
            }


            title1.setText("Player1Score:"+player1_score);
            title1.setBounds(30,30,200,20);
            title1.setFont(new java.awt.Font("Dialog", 1, 15));
            add(title1);

            title2.setText("Player2Score:"+player2_score);
            title2.setBounds(30,50,200,20);
            title2.setFont(new java.awt.Font("Dialog", 1, 15));
            add(title2);


            title3.setBounds(30,10,200,20);
            title3.setFont(new java.awt.Font("Dialog", 1, 15));
            add(title3);




            squares = new_squares;
            if (squares == ((a - 1) * (b - 1))) {// Someone win the game
                String winner = "";
                if (player1_score == player2_score) {// Draw No one wins
                    winner ="everyone";
                    title3.setText("The winner is "+winner+".");

                } else {
                    winner = (player1_score > player2_score) ? "Player1" : "Player2";
                    title3.setText("The winner is "+winner);
                }


                check = true;
                //dispose();
            }
        } else {
            //Switch terms to the other player
            currentColor = (currentColor == Color.RED) ? Color.BLUE : Color.RED;
            term = (term == "Player01") ? "Player02" : "Player01";
            check = false;

        }
        return check;
    }// winner method ends - call completedSquares

    private int completedSquares(){
        //Check for the number of completed squares
        int New_Squares = 0;
        for (int i = 0; i < rows.length - 1; i++) {
            for (int j = 0; j < rows[i].length; j++) {
                if (rows[i][j] == 1 && rows[i + 1][j] == 1) {
                    if (cols[i][j] == 1 && cols[i][j + 1] == 1) {
                        New_Squares++;
                        rectangles.get(j).get(i).closedoff(currentColor);
                    }
                }
            }
        }

        return New_Squares;
    }
    public void run(){
        // Robot term
        int robot_X, robot_Y;
        Boolean robot_Row_Col;
        int status;
        do {
            robot_Row_Col = random.nextBoolean();
            if (robot_Row_Col) {// True robot select Rows Edge
                //Rows 90 + 100 * k, 81 + 100 * m, 100, 8)
                MusicPlayer play = new MusicPlayer("C:\\Users\\楚镜\\Desktop\\Project\\BackgroundMusic\\II.mp3");
                play.start();
                robot_X = random.nextInt(a);
                robot_Y = random.nextInt(b-1);
                status = rows[robot_X][robot_Y];

            } else {// False robot select Cols Edge
                // Cols 81 + 100 * l, 90 + 100 * n, 8, 100)
                MusicPlayer play = new MusicPlayer("C:\\Users\\楚镜\\Desktop\\Project\\BackgroundMusic\\II.mp3");
                play.start();
                robot_X = random.nextInt(a-1);
                robot_Y = random.nextInt(b);
                status = cols[robot_X][robot_Y];

            } } while(status != 0);// if edge component is not free, keep looking for free space

        update(robot_Row_Col,robot_X,robot_Y,currentColor);
    }// Run Method ends - call update
    private void update(boolean robot_bool,int robot_x,int robot_y,Color color){
        // Draw the line
        if(robot_bool){
            rows[robot_x][robot_y] = 1;//表示已被占据
            edgesRows.get(robot_y).get(robot_x).setColor(color);
            edgesRows.get(robot_y).get(robot_x).setFree(false);
            edgesRows.get(robot_y).get(robot_x).setVisible(true);
            edgesRows.get(robot_y).get(robot_x).repaint();
        }else{
            cols[robot_x][robot_y] = 1;
            edgesCols.get(robot_y).get(robot_x).setColor(color);
            edgesCols.get(robot_y).get(robot_x).setFree(false);
            edgesCols.get(robot_y).get(robot_x).setVisible(true);
            edgesCols.get(robot_y).get(robot_x).repaint();
        }
        invalidate();
        validate();
        repaint();
    }//update method ends
    public static void main(String[] args){
        Runnable runnable=new MVM33.Runner();
        SwingUtilities.invokeLater(runnable);
    }

    static class Runner implements Runnable{
        @Override
        public void run() {
            MVM33 mvm = new MVM33(6,"红色");
            mvm.setVisible(true);
        }
    }
}// MVM class ends