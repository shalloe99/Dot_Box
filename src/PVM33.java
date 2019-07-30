import java.awt.event.InputEvent;
import javax.sound.midi.SysexMessage;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
public class PVM33 extends JFrame {

    JLabel title1=new JLabel();
    JLabel title2=new JLabel();
    JLabel title3=new JLabel();

    // Variables initialize
    public static String term;
    public static int player1_score = 0;
    public static int player2_score = 0;
    public static int squares = 0;
    public int a,b,A,B,counter;

    public Random random;

    public ArrayList<Integer> array;


    private ArrayList<ArrayList<EdgeComponent>> edgesRows = new ArrayList<>();
    private ArrayList<ArrayList<EdgeComponent>> edgesCols = new ArrayList<>();private static Robot robot;
    private ArrayList<ArrayList<RectangleComponent>> rectangles = new ArrayList<>();

    private Color currentColor;
    private Color firstColor;
    private Color secondColor;

    Component component;
    EdgeComponent edgeComponent;
    int[][] rows;
    int[][] cols;
    // Variables end;
    public PVM33(int d,String c,String t){
        // Constructor initialize
        A = 0;
        B = 0;
        a = d;
        b = d;
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

        if (t.equals("先手")) {
            term = "Player01";
        }else if (t.equals("后手")) {
            term = "Player02";
        }





        for(int w=0;w<100;w++)
            array.add(w, 1);
        initialize();
        try {// Create auto bot
            robot = new Robot();
        } catch (Exception e) {
            // TODO Auto-generated catch block（这里要做异常处理）
            e.printStackTrace();
        }//寻找try{}内的异常
        // 当没有trycatch时，如果出现异常则程序报错。
        // 当有trycatch时，如果出现异常则程序正常运行，并且把错误信息存储到Exception中。
        // 因此catch是用来提取异常信息的
        // printStackTrace方法 打印异常信息在程序中出错的原因和位置

        GameMouseListener mouseListener = new GameMouseListener();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }// Constructor ends（停止写构造函数）


    public void initialize() { // initialize method




            currentColor = Color.RED;
            firstColor = Color.RED;
            secondColor = Color.BLUE;

        // Grid Setup


        // Status Check System set up
        rows = new int[a][a-1];
        cols = new int[b-1][b];
        for(int i = 0;i < a;i++)
            for (int j = 0;j<a-1;j++)
                rows[i][j] = 0;
        for(int i = 0;i < b-1;i++)
            for (int j = 0;j<b;j++)
                cols[i][j] = 0;

        int width = 190+100 * (a - 1);
        int height = 200+100 * (b - 1);//奇怪的整框长宽设定

        //JFrame setup
        setTitle("CS102A Project Demo");
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        PVM33.this.getContentPane().setBackground(Color.WHITE);// Set Background

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
                Temp_edgesRows.add(new EdgeComponent(85 + 100 * k, 80 + 100 * m, 100, 10));//int x, int y, int width, int height
            }
            edgesRows.add(Temp_edgesRows);
            k++;
        }
        while (l <= a - 1) {
            ArrayList<EdgeComponent> Temp_edgesCols = new ArrayList<>();
            for (int n = 0; n <= b - 2; n++) {//vertical
                Temp_edgesCols.add(new EdgeComponent(80 + 100 * l, 85 + 100 * n, 10, 100));
            }
            edgesCols.add(Temp_edgesCols);
            l++;
        }
        for (ArrayList<EdgeComponent> eArray : edgesRows) {
            for (EdgeComponent e: eArray) {
                e.setColor(currentColor);
                e.setVisible(false);
                getContentPane().add(e);
            }
        }
        for (ArrayList<EdgeComponent> eArray : edgesCols) {
            for (EdgeComponent e: eArray) {
                e.setColor(currentColor);
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


    private class GameMouseListener extends MouseInputAdapter {// mouseListener initialize
        @Override// This code can be simplified, many similarities
        public void mouseClicked(MouseEvent event) {
            if (term =="Player01") {
                // User/Player1 term
                event = SwingUtilities.convertMouseEvent(PVM33.this, event, getContentPane());
                component = getContentPane().getComponentAt(event.getPoint());
                //getContentPane() 初始化容器,并用来在容器内添加控件
                //getComponentAt 确定此组件或其直接子组件之一是否包含(x,y)位置

                if (component instanceof EdgeComponent) { // Clicked on the EdgeComponent
                    edgeComponent = (EdgeComponent) component;
                    if (edgeComponent.isFree()) {
                        MusicPlayer play = new MusicPlayer("C:\\Users\\楚镜\\Desktop\\Project\\BackgroundMusic\\II.mp3");
                        play.start();
                        // Draw the line
                        edgeComponent.setColor(currentColor);
                        edgeComponent.setFree(false);
                        edgeComponent.setVisible(true);
                        edgeComponent.repaint();
                        //Find the edge Component and turn on the switch
                        for (int i = 0; i < edgesRows.size(); i++) {
                            for (int j = 0; j < edgesRows.get(i).size(); j++) {
                                if (edgesRows.get(i).get(j) == edgeComponent) {
                                    rows[j][i] = 1;
                                }
                            }
                        }
                        for (int i = 0; i < edgesCols.size(); i++) {
                            for (int j = 0; j < edgesCols.get(i).size(); j++) {
                                if (edgesCols.get(i).get(j) == edgeComponent) {
                                    cols[j][i] = 1;
                                }
                            }
                        }
                        // Check for the number of completed squares
                        int new_squares = 0;
                        for (int i = 0; i < rows.length - 1; i++) {
                            for (int j = 0; j < rows[i].length; j++) {
                                if (rows[i][j] == 1 && rows[i + 1][j] == 1) {
                                    if (cols[i][j] == 1 && cols[i][j + 1] == 1) {
                                        new_squares++;
                                        rectangles.get(j).get(i).closedoff(currentColor);
                                    }
                                }
                            }
                        }

                        // Check for anyone winning
                        if (new_squares > squares) {
                            if (currentColor == firstColor) {// Player01 scores
                                player1_score+= (new_squares-squares);
                            } else if (currentColor == secondColor) {// Player02 scores
                                player2_score+= (new_squares-squares);
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



                            squares++;
                            if (squares == ((a - 1) * (b - 1))) {// Someone win the game
                                String winner = "";
                                if (player1_score == player2_score) {// Draw No one wins
                                    winner ="everyone";
                                    title3.setText("The winner is "+winner+".");
                                } else {
                                    winner = (player1_score > player2_score) ? "Player1" : "Player2";
                                    title3.setText("The winner is "+winner+".");
                                }
                            }
                        } else {
                            //Switch terms to the other player
                            currentColor = (currentColor == Color.RED) ? Color.BLUE : Color.RED;
                            term = (term == "Player01") ? "Player02" : "Player01";
                        }
                    }
                }
            }// Player01 play ends
            if(term == "Player02") {
                // Robot term/Player2 term
                do {
                    int robot_X, robot_Y;
                    do {
                        Boolean robot_Row_Col = random.nextBoolean();
                        if (robot_Row_Col) {// True robot select Rows Edge
                            //Rows 90 + 100 * k, 81 + 100 * m, 100, 8)
                            robot_X = 90 + 100 * random.nextInt(a - 1);
                            robot_Y = 82 + 100 * random.nextInt(b);
                            MusicPlayer play = new MusicPlayer("C:\\Users\\楚镜\\Desktop\\Project\\BackgroundMusic\\II.mp3");
                            play.start();
                        } else {// False robot select Cols Edge
                            // Cols 81 + 100 * l, 90 + 100 * n, 8, 100)
                            robot_X = 81 + 100 * random.nextInt(a);
                            robot_Y = 90 + 100 * random.nextInt(b - 1);
                            MusicPlayer play = new MusicPlayer("C:\\Users\\楚镜\\Desktop\\Project\\BackgroundMusic\\II.mp3");
                            play.start();
                        }
                        event = SwingUtilities.convertMouseEvent(PVM33.this, event, getContentPane());
                        component = getContentPane().getComponentAt(robot_X, robot_Y);
                        if (component instanceof EdgeComponent) { // Clicked on the EdgeComponent
                            edgeComponent = (EdgeComponent) component;
                        }
                    } while (!edgeComponent.isFree());// if edge component is not free, keep looking for free space
                    // Draw the line
                    edgeComponent.setColor(currentColor);
                    edgeComponent.setFree(false);
                    edgeComponent.setVisible(true);
                    edgeComponent.repaint();
                    //Find the edge Component and turn on the switch
                    for (int i = 0; i < edgesRows.size(); i++) {
                        for (int j = 0; j < edgesRows.get(i).size(); j++) {
                            if (edgesRows.get(i).get(j) == edgeComponent) {
                                rows[j][i] = 1;
                            }
                        }
                    }
                    for (int i = 0; i < edgesCols.size(); i++) {
                        for (int j = 0; j < edgesCols.get(i).size(); j++) {
                            if (edgesCols.get(i).get(j) == edgeComponent) {
                                cols[j][i] = 1;
                            }
                        }
                    }
                    // Check for the number of completed squares
                    int new_squares = 0;
                    for (int i = 0; i < rows.length - 1; i++) {
                        for (int j = 0; j < rows[i].length; j++) {
                            if (rows[i][j] == 1 && rows[i + 1][j] == 1) {
                                if (cols[i][j] == 1 && cols[i][j + 1] == 1) {
                                    new_squares++;
                                    rectangles.get(j).get(i).closedoff(currentColor);
                                }
                            }
                        }
                    }

                    // Check for anyone winning
                    if (new_squares > squares) {
                        if (currentColor == firstColor) {// Player01 scores
                            player1_score+= (new_squares-squares);
                        } else if (currentColor == secondColor) {// Player02 scores
                            player2_score+= (new_squares-squares);
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


                        squares++;
                        if (squares == ((a - 1) * (b - 1))) {// Someone win the game
                            String winner = "";
                            if (player1_score == player2_score) {// Draw No one wins

                                winner ="everyone";
                                title3.setText("The winner is "+winner+".");

                            } else {
                                winner = (player1_score > player2_score) ? "Player1" : "Player2";
                                title3.setText("The winner is "+winner+".");
                            }

                            term = (term == "Player02") ? "Player01" : "Player02";
                        }
                    } else {
                        //Switch terms to the other player
                        currentColor = (currentColor == Color.RED) ? Color.BLUE : Color.RED;
                        term = (term == "Player02") ? "Player01" : "Player02";
                    }
                    // System.out.println("RUN--");
                }while(term =="Player02");// while-loop allows robot keep clicking
            }// Robot/Player02 play ends
        }// MouseClicked ends
        @Override
        public void mouseMoved(MouseEvent event) {  //MouseMoved initialize
            if(currentColor==firstColor) {
                event = SwingUtilities.convertMouseEvent(PVM33.this, event, getContentPane());
                Component component = getContentPane().getComponentAt(event.getPoint());
                for (ArrayList<EdgeComponent> eArray : edgesRows) {
                    for (EdgeComponent e: eArray) {
                        if(e.isFree()){
                            if (e == component) {
                                e.setColor(currentColor);
                                e.setVisible(true);
                            } else {
                                e.setVisible(false);
                            }
                        }
                    }
                }
                for (ArrayList<EdgeComponent> eArray : edgesCols) {
                    for (EdgeComponent e: eArray) {
                        if(e.isFree()){
                            if (e == component) {
                                e.setColor(currentColor);
                                e.setVisible(true);
                            } else {
                                e.setVisible(false);
                            }
                        }
                    }
                }
            }// This is player1's term
        }// MouseMoved ends
        public boolean exist( ArrayList array,int i) {
            Object exist=array.get(i);
            if((Integer)exist==0)
                return true;
            else
                return false;
        }// exist
    }// mouseListener class ends


    public static void main(String[] args){
        Runnable runnable=new Runner();
        SwingUtilities.invokeLater(runnable);
    }

    static class Runner implements Runnable{
        @Override
        public void run() {
            PVM33 pvm = new PVM33(3,"红色","Player01");
            pvm.setVisible(true);
        }
    }


}

