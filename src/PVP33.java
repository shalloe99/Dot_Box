
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
public class PVP33 extends JFrame {

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
    private ArrayList<ArrayList<EdgeComponent>> edgesCols = new ArrayList<>();
    private ArrayList<ArrayList<RectangleComponent>> rectangles = new ArrayList<>();
    private Color currentColor;
    private Color firstColor;
    private Color secondColor;
    Component component;
    EdgeComponent edgeComponent;
    int[][] rows;
    int[][] cols;
    // Variables end;
    public PVP33(int d,String c){// Constructor initialize
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



        for(int w=0;w<100;w++)
            array.add(w, 1);
        initialize();
        GameMouseListener mouseListener = new GameMouseListener();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
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
        int width = 190 + 100 * (a - 1);
        int height = 200 + 100 * (b - 1);
        //JFrame setup
        setTitle("CS102A Project Demo");
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        PVP33.this.getContentPane().setBackground(Color.WHITE);// Set Background
        //Dot & Edges setup

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


        int k = 0;
        int l = 0;
        while (k <= a - 2) {
            ArrayList<EdgeComponent> Temp_edgesRows = new ArrayList<>();
            for (int m = 0; m <= b - 1; m++) {//horizontal
                Temp_edgesRows.add(new EdgeComponent(85 + 100 * k, 80 + 100 * m, 100, 10));//�洢���������߶�
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
                e.setColor(currentColor);
                e.setVisible(true);
                getContentPane().add(e);
            }
        }
        for (ArrayList<EdgeComponent> eArray : edgesCols) {
            for (EdgeComponent e: eArray) {
                e.setColor(currentColor);
                e.setVisible(true);
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
            event = SwingUtilities.convertMouseEvent(PVP33.this, event, getContentPane());
            component = getContentPane().getComponentAt(event.getPoint());
            if (component instanceof EdgeComponent) { // Clicked on the EdgeComponent
                edgeComponent = (EdgeComponent) component;
                if (edgeComponent.isFree()) {
                    // Draw the line
                    edgeComponent.setColor(currentColor);
                    edgeComponent.setFree(false);
                    edgeComponent.setVisible(true);
                    edgeComponent.repaint();
                    MusicPlayer play = new MusicPlayer("C:\\Users\\楚镜\\Desktop\\Project\\BackgroundMusic\\II.mp3");
                    play.start();
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



                        squares = new_squares;
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

        }// MouseClicked ends
        @Override
        public void mouseMoved(MouseEvent event) {  //MouseMoved initialize   //�����ƶ�
            if(currentColor==firstColor) {
                event = SwingUtilities.convertMouseEvent(PVP33.this, event, getContentPane());
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
        public boolean exist( ArrayList array,int i) {//�ж�Linkedlistĳһ����Ԫ���Ƿ�����,�������ڣ���Ԫ������Ԫ�������±���ͬ����֮����
            Object exist=array.get(i);
            if((Integer)exist==0)
                return true;
            else
                return false;
        }// exist
    }// mouseListener class ends
    public static void main(String[] args){
        Runnable runnable=new PVP33.Runner();
        SwingUtilities.invokeLater(runnable);
    }

    static class Runner implements Runnable{
        @Override
        public void run() {
            PVP33 pvp = new PVP33(3,"红色");
            pvp.setVisible(true);
        }
    }
}// PVP class ends