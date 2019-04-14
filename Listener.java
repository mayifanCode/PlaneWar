package com.myf.plane1102;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.Paper;
import java.util.ArrayList;
import java.util.Random;

public class Listener implements KeyListener, ActionListener,MouseListener {
	static ArrayList<Bullet> bulletsMe = new ArrayList<Bullet>();// static可以保证对象的唯一性,别处通过类来调用

	public ThreadPlane tb;

	public ThreadBullet tb2;

	public ThreadBackGround tb3;
    private int x,y;
	private PlaneJpanel cp;
	private Graphics g;
	private Plane bluePlane;
	private Plane lxPlane;
	public int startFlag = 0;
	private Listener listener;
	private ArrayList<Plane> PaperPlaneArray;
	private ArrayList<Goods> goodsArray;

	public void setgoods(ArrayList<Goods> goodsArray) {
		// TODO Auto-generated method stub
		this.goodsArray = goodsArray;
	}

	public void setPaperPlane(ArrayList<Plane> PaperPlaneArray) {
		this.PaperPlaneArray = PaperPlaneArray;
	}

	public void setlistener(Listener listener) {
		this.listener = listener;
	}

	public void setlxplane(Plane lxPlane) {
		this.lxPlane = lxPlane;
	}

	public void setplane(Plane bluePlane) {
		this.bluePlane = bluePlane;
	}

	public void setg(Graphics g) {
		this.g = g;
	}

	public void setcp(PlaneJpanel cp) {
		this.cp = cp;
		this.cp.setBulletsMe(bulletsMe);

	}
    public void mouseClicked(MouseEvent e){
    	
    }
    public void mousePressed(MouseEvent e){

    }
    public void mouseReleased(MouseEvent e){
    	if(startFlag==0)
    	{
	    	x=e.getX();
	    	y=e.getY();
	    	if(x>375&&x<595&&y>810&&y<905)
	    		start();
    	}
    	if(startFlag==1)
    	{
	    	x=e.getX();
	    	y=e.getY();
	    	if(x>895&&x<945&&y>865&&y<915)
	    		start();
    	}   	
    }
    public void mouseEntered(MouseEvent e){
    	
    }
    public void mouseExited(MouseEvent e){
    	
    }
	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		// System.out.println("keyPressed");
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			bluePlane.y -= 20;
			if (bluePlane.y < 0)
				bluePlane.y = 0;
			// System.out.println("y-");
			break;
		case KeyEvent.VK_DOWN:
			bluePlane.y += 20;
			if (bluePlane.y > 900)
				bluePlane.y = 900;
			break;
		case KeyEvent.VK_LEFT:
			bluePlane.x -= 20;
			if (bluePlane.x < 10)
				bluePlane.x = 10;
			break;
		case KeyEvent.VK_RIGHT:
			bluePlane.x += 20;
			if (bluePlane.x > 980)
				bluePlane.x = 980;
			break;
		case KeyEvent.VK_SPACE:
			// System.out.println("发射子弹");
			addBulletMe();
			break;
		}
	}

	public void keyReleased(KeyEvent e) {

	}
	public void actionPerformed(ActionEvent e) {
		// 获取焦点
//		cp.requestFocus();
	}
	public void start() 
	{		
			//System.out.println("开始");
		     //获取焦点
		    cp.requestFocus();
			if (startFlag == 0) {
				startFlag = 1;
				if (tb == null) {
					ThreadPlane tb = new ThreadPlane();// 飞机线程
					tb.setlxplane(lxPlane);
					tb.start();
					tb.setcp(cp);
					tb.setg(g);
					tb.setlistener(listener);
					tb.setPaper(PaperPlaneArray);
					tb.setgoods1(goodsArray);
					tb.setbullet(bulletsMe);
					tb.setblue(bluePlane);
					cp.settb(tb);
				}

				if (tb2 == null) {
					ThreadBullet tb2 = new ThreadBullet();// 子弹线程
					tb2.start();
					tb2.setbullets(bulletsMe);
					tb2.setbluePlane(bluePlane);
					tb2.setlxPlane(lxPlane);
					tb2.setbluePlane(PaperPlaneArray);
					cp.settb2(tb2);
					tb2.setgoods(goodsArray);
				}
				if (tb3 == null) {
					ThreadBackGround tb3 = new ThreadBackGround();// 背景线程
					tb3.start();
					tb3.setcp(cp);
					cp.settb3(tb3);
				}
			} else {
				int rand2 = -(int) (Math.random() * 1000);
				int rand3 = 200 + (int) (Math.random() * 800);
				lxPlane.y = rand2;
				lxPlane.x = rand3;
				lxPlane.life = 100;
				bluePlane.life = 100;
				for (int j = 0; j < PaperPlaneArray.size(); j++) {
					Plane plane = PaperPlaneArray.get(j);
					rand2 = -(int) (Math.random() * 1000);
					rand3 = 200 + (int) (Math.random() * 800);
					plane.y = rand2;
					plane.x = rand3;
					plane.life = 100;
				}
				for (int k = 0; k < goodsArray.size(); k++) {
					Goods goods = goodsArray.get(k);
					rand2 = -(int) (Math.random() * 1000);
					rand3 = 200 + (int) (Math.random() * 800);
					goods.y = rand2;
					goods.x = rand3;
				}
				// System.out.println(tb);
				ThreadPlane.pass = 1;
				ThreadPlane.passFlag = 1;
				ThreadPlane.count = 0;
				for (int i = 0; i < bulletsMe.size(); i++) {
					// System.out.println(bulletsMe.size());
					bulletsMe.remove(bulletsMe.get(i));
					i--;
				}
				ThreadBullet.score = 0;
				bluePlane.y = 900;
				bluePlane.x = 500;
			}

		}
	

	public void addBulletMe() {
		int num = 1 + (int) (Math.random() * 20);
		Bullet bullet = new Bullet(bluePlane.x - 15, bluePlane.y - 110, num, 0);
		bulletsMe.add(bullet);

	}

	public void addBulletEnemy() {
		int num = 1 + (int) (Math.random() * 40);
		Bullet bullet = new Bullet(lxPlane.x - 25, lxPlane.y + 93, num, 1);
		bulletsMe.add(bullet);
		// System.out.println(lxPlane.y);

	}

}
