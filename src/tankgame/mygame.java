package tankgame;
import java.awt.*;
import java.awt.event.*;
import java.util.*;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           

import javax.swing.*;

public class mygame extends JFrame {
	
	MyPanel mp=null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mygame mg=new mygame();

	}
	public mygame(){
		mp=new MyPanel();
		
		
		this.add(mp);
		this.addKeyListener(mp);
		Thread t=new Thread(mp);
		t.start();
		
		
		
		this.setSize(600,400);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

class MyPanel extends JPanel implements KeyListener,Runnable{
	
	Hero hero=null;
	
	Vector<Enemy> Ene=new Vector<Enemy>();
	
	public MyPanel(){
		
		hero=new Hero(500,300);
		int size=1;
		for(int i=0;i<size;i++){
			
			Enemy enemy=new Enemy((int)(Math.random()*100)+i*150+50,(int)(Math.random()*250+50));
			Thread t1=new Thread(enemy);
			t1.start();
			
			
			
			int num = new Random().nextInt(4);
			enemy.setDirect(num);
			Ene.add(enemy);
			
		}
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		//g.fillRect(0, 0, 400, 300);
		this.setBackground(Color.gray);
		//画一个我的tank
		this.drawTank(hero.getX(), hero.getY(),g,this.hero.direct, 0);
		//画出敌人tank
		for(int i=0;i<Ene.size();i++){
			Enemy e=Ene.get(i);
			this.drawTank(e.getX(), e.getY(), g, e.getDirect(), 1);
			e.newShot();
			g.drawRect(e.shot.x, e.shot.y, 1, 1);
			
			
		}
		
		//画一个子弹
		if(hero.shot!=null&&hero.shot.islife!=false){
			g.setColor(Color.black);
			g.drawRect(hero.shot.x, hero.shot.y, 1, 1);
			
		}
	}
	
	
	//画出坦克的函数
	public void drawTank(int x,int y,Graphics g,int direct,int type) {
		// TODO Auto-generated method stub
		switch (type) {
		//我的坦克颜色为蓝色
		case 0:
			g.setColor(Color.blue);
			break;
		//敌人坦克
		case 1:
			g.setColor(Color.red);
			break;

		default:
			break;
		}
		switch (direct) {
		//向上的tank
		case 0:
			g.fillRect(x, y,10,40);
			g.fillRect(x+30, y, 10, 40);
			g.fillOval(x+9, y+9, 22, 22);
			g.drawLine(x+20, y+20, x+20, y-15);
			break;
		//向下的tank
		case 1:
			g.fillRect(x, y,10,40);
			g.fillRect(x+30, y, 10, 40);
			g.fillOval(x+9, y+9, 22, 22);
			g.drawLine(x+20, y+20, x+20, y+55);
			break;
		//向左的tank
		case 2:
			g.fillRect(x, y,40,10);
			g.fillRect(x, y+30, 40, 10);
			g.fillOval(x+9, y+9, 22, 22);
			g.drawLine(x+20, y+20, x-15, y+20);
			
			break;
		//向右的tank
		case 3:
			g.fillRect(x, y,40,10);
			g.fillRect(x, y+30, 40, 10);
			g.fillOval(x+9, y+9, 22, 22);
			g.drawLine(x+20, y+20, x+55, y+20);
			break;

		default:
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_UP){
			this.hero.setDirect(0);
			this.hero.moveup();
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			this.hero.setDirect(1);
			this.hero.movedown();
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			this.hero.setDirect(2);
			this.hero.moveleft();
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			this.hero.setDirect(3);
			this.hero.moveright();
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			this.hero.shoting();
		}
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.repaint();
		}
	}
	
}
