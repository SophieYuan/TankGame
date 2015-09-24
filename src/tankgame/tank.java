package tankgame;

//写一个shot类
class Shot implements Runnable{	
	int x;
	int y;
	int direct;
	int speed=10;
	boolean islife=true;
	public Shot(int x,int y,int direct){
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch (this.direct) {
			case 0:
				y-=speed;
				break;
			case 1:
				y+=speed;
				break;
			case 2:
				x-=speed;
				break;
			case 3:
				x+=speed;
				break;

			default:
				break;
			}
			System.out.println("x:"+x+"  y:"+y+" direct"+direct);
			if(x<(0+speed)||x>(600-speed)||y<(0+speed)||y>(400-speed)){
				this.islife=false;
				break;
			}
		}
	}
	
}
//写一个tank类
class Tank{
	//tank的x,y
	int x=0;
	int y=0;
	//direct表示tank的方向，0向上，1向下，2向左，3向右
	int direct=0;
	//tank的速度设置为1
	int speed=1;
	//tank的类型决定tank的颜色
	int color;
	
	public Tank(int x,int y){
		
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}


}
//编写敌人的坦克
class Enemy extends Tank implements Runnable{


	int time;
	Shot shot=null;
	boolean islive=true;
	
	public Enemy(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			
			try {
			Thread.sleep(50);
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
		
			switch (this.getDirect()) {
			case 0:
				y-=speed;
				
				if(y<speed+30) this.setDirect(3);
				break;
			case 1:
				y+=speed;
				
				if(y>(300-speed)) this.setDirect(2);
				break;
			case 2:
				x-=speed;
				
				if(x<speed+30) this.setDirect(0);
				break;
			case 3:
				x+=speed;
				
				if(x>(500-speed)) this.setDirect(1);
				break;

			default:
				break;
			}
		}
	}
	public void newShot(){		
		if(islive){
				switch (this.getDirect()) {
				case 0:
					shot=new Shot(this.x+20, this.y-15,0);
					break;
				case 1:
					shot=new Shot(this.x+20, this.y+55,1);
					break;
				case 2:
					shot=new Shot(this.x-15, this.y+20,2);
					break;
				case 3:
					shot=new Shot(this.x+55, this.y+20,3);
					break;
				}
				Thread t2=new Thread(shot);
				t2.start();
				}

			
			
				
	}
}



//编写我的tank
class Hero extends Tank{

	public Hero(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
    Shot shot=null;
	
	public void shoting(){
		switch (this.direct) {
		case 0:
			shot=new Shot(x+20, y-15,0);
			break;
		case 1:
			shot=new Shot(x+20, y+55,1);
			break;
		case 2:
			shot=new Shot(x-15, y+20,2);
			break;
		case 3:
			shot=new Shot(x+55, y+20,3);
			break;

		default:
			break;
		}
		Thread thread=new Thread(shot);
		thread.start();
		
	}
	public void moveup(){
		y-=speed;		
	}
	public void movedown(){
		y+=speed;		
	}
	public void moveleft(){
		x-=speed;		
	}
	public void moveright(){
		x+=speed;		
	}
}










