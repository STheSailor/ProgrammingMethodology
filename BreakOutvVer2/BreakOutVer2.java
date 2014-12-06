import acm.graphics.*;
import acm.gui.IntField;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JLabel;

public class BreakOutVer2 extends ConsoleProgram {
	
	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;
	
	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;
	
	/** Width of a brick */
	private static final int BRICK_WIDTH =
			(WIDTH - (NBRICKS_PER_ROW + 1) * BRICK_SEP) / NBRICKS_PER_ROW;
	
	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_DIA = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;
	
	/** Number of turns */
	private static final int NTURNS = 3;
	
	/** Animation cycle delay */
	private static final int DELAY = 10;
	
	public void run(){
		setLayout(new GridLayout(1,2));
		bCanvas = new GCanvas();
		add(bCanvas);
		currentTimes=NTURNS;
		createController();
						
		setup();
		
		
		addActionListeners();
		
		
		while(!gameOver()){
			moveBall();
			checkForCollisions();
			
			//if(currentTimes<=0)break;
			
			pause(DELAY);
			//if(isBricksClean()) break;
			//i--;
		}
	}
	
	private void createController(){
		add(new JLabel("Lives:"), SOUTH);
		livesField=new IntField(currentTimes);
		
	
		add(livesField,SOUTH);
		livesField.addActionListener(this);
		
		startButton = new JButton("Start");
		add(startButton,SOUTH);
		
		restartButton = new JButton("Restart");
		add(restartButton,SOUTH);
		
	
	}
	
	private void setup(){
	    gameboard = new GRect(WIDTH ,HEIGHT);
		bCanvas.add(gameboard);
				
		setupBricks();
		setupPaddle();	
		bCanvas.addMouseListener(this);
		
	}
	
	private void setupBricks(){
		for(int i=0;i<NBRICK_ROWS;i++){
			for(int j=0;j<NBRICKS_PER_ROW;j++){
				int x = BRICK_WIDTH*j+BRICK_SEP*(j+1);
				int y = BRICK_Y_OFFSET + i*(BRICK_HEIGHT+BRICK_SEP);
				brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				if(i<2){
					brick.setColor(Color.red);
				}else if(i<4){
					brick.setColor(Color.orange);
				}else if (i<6){
					brick.setColor(Color.yellow);
				}else if (i<8){
					brick.setColor(Color.green);
				}else if (i<10){
					brick.setColor(Color.cyan);
				}
				bCanvas.add(brick,x, y);
				number_of_bricks++;
			}
		}
	}
	
	private void setupPaddle(){
		paddle = new GRect((WIDTH- PADDLE_WIDTH)/2,HEIGHT-(PADDLE_Y_OFFSET+PADDLE_HEIGHT),
				PADDLE_WIDTH,PADDLE_HEIGHT );
		paddle.setFilled(true);
		bCanvas.add(paddle);
	}
	
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		if (source == startButton&&ball == null){
			pause(1000);
			int ct = currentTimes-1;
			livesField.setValue(ct);
			vx = rgen.nextDouble(1.0, 3.0);
			if (rgen.nextBoolean(0.5)) vx = -vx;
			vy = 3.0;
			ball = new GOval(WIDTH/2.5,HEIGHT/2,BALL_DIA,BALL_DIA);
			ball.setFilled(true);
			bCanvas.add(ball);
		}else if(source ==restartButton && ball==null){
			currentTimes=NTURNS;
			int ct=currentTimes;
			livesField.setValue(ct);
			bCanvas.removeAll();
			setup();
			
		}
	}
//	 记录鼠标按下时的位置
	public void mousePressed(MouseEvent e) {
		// GPoint has X and Y coordinate
		last = new GPoint(e.getPoint());
		gobj = bCanvas.getElementAt(last);
	}
	
	// 回应鼠标拖动物体 
	public void mouseDragged(MouseEvent e) {
		if (gobj == paddle) {
			gobj.move(e.getX() - last.getX(), e.getY() - e.getY());
			last = new GPoint(e.getPoint());
			//if (e.getX()<0 ){
			//	paddle.setLocation(0,paddle.getY());
			//}if(e.getX()+PADDLE_WIDTH>WIDTH){
			//	paddle.setLocation(WIDTH-PADDLE_WIDTH,paddle.getY());
			//}

		}
	}
	
	
	public void mouseClicked(MouseEvent e) {
		pause(1000);
		if (ball== null) {
			int ct = currentTimes-1;
			livesField.setValue(ct);
			
			vx = rgen.nextDouble(1.0, 3.0);
			if (rgen.nextBoolean(0.5)) vx = -vx;
			vy = 3.0;
			ball = new GOval(WIDTH/2.5,HEIGHT/2,BALL_DIA,BALL_DIA);
			ball.setFilled(true);
			bCanvas.add(ball);

		}
	}
	
	private void moveBall(){
		if (ball != null) {
			ball.move(vx,vy);
		}
	}
	

	private void checkForCollisions(){
		checkWallCollisions();
		
		GObject collider = getCollidingObject();
		if(collider!=gameboard){
			if(collider==paddle){
				bounceOffPaddle();
				
			}else if(collider != null){
				bCanvas.remove(collider);
				//bounceClip.play();
				vy=-vy;
				number_of_bricks--;
			
			}
		}
	}
	
	
	private void checkWallCollisions(){	
		if (ball!=null&&((ball.getY()<0))) {
			vy=-vy;		
		}if (ball!=null&&(ball.getY() > HEIGHT )){
			bCanvas.remove(ball);
			ball=null;
			currentTimes--;
		}if(ball!=null&&((ball.getX()<0)||((ball.getX()+BALL_DIA)>WIDTH))){
			vx=-vx;
		}
	}
	

	
	private GObject getCollidingObject(){
		GObject obj =null;
	    if(ball!=null){
			double x = ball.getX();
			double y = ball.getY();
				if((obj=bCanvas.getElementAt(x, y))!=null){
					obj=bCanvas.getElementAt(x, y);
				}else if((obj=bCanvas.getElementAt(x+BALL_DIA, y))!=null){
					obj=bCanvas.getElementAt(x+BALL_DIA, y);
				}else if((obj=bCanvas.getElementAt(x, y+BALL_DIA))!=null){
					obj=bCanvas.getElementAt(x, y+BALL_DIA);
				}else {
					obj=bCanvas.getElementAt(x+BALL_DIA, y+BALL_DIA);
				}
	    }
	    return obj;
		
	}

	
    private void bounceOffPaddle()
    {
        double dy = (ball.getY() + BALL_DIA) - paddle.getY();
        ball.move(0,-dy);
        
        vy = -vy;
    }
    
	private boolean gameOver(){
		if(number_of_bricks<=0){
			box = new GCompound();
			GRect outline = new GRect(120, 50);
			GLabel label = new GLabel("You win!");
			box.add(outline, -120 / 2, -50 / 2);
			box.add(label, -label.getWidth() / 2, label.getAscent() / 2);
			bCanvas.add(box, bCanvas.getWidth() / 2, bCanvas.getHeight() / 2);
		}
		if(currentTimes<=0){
			box = new GCompound();
			GRect outline = new GRect(120, 50);
			GLabel label = new GLabel("You lose!");
			label.setFont("Courier 24");
			box.add(outline, -120 / 2, -50 / 2);
			box.add(label, -label.getWidth() / 2, label.getAscent() / 2);
			bCanvas.add(box, bCanvas.getWidth() / 2, bCanvas.getHeight() / 2);
		}
		return (number_of_bricks<=0||currentTimes<=0);
	}
	
	private GCanvas bCanvas;
	
	private GObject gobj;          /* 被拉动的物体 */
	private GPoint last;           /* 鼠标最后点击的位置 */
	private GRect paddle;          /*底部移动挡板*/
	private GOval ball;            /*拆墙的小球*/
	private GRect brick;           /*砖块（组成墙体）*/
	private GRect gameboard;       /*游戏画板*/
	
	private double vx,vy;          /*小球速度*/
	private RandomGenerator rgen = RandomGenerator.getInstance();   //随机数生成器
	private int currentTimes;                                       //现在轮数（每次三轮游戏机会）
	private int number_of_bricks;

	private JButton startButton;
	private IntField livesField;
	private JButton restartButton;
	private GCompound box;
}
