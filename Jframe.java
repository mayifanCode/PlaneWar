package com.myf.plane1102;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Jframe {
	public static void main(String[] args) {
		Jframe frame = new Jframe();
		frame.showUI();
        
	}

	public void showUI() {
		ArrayList<Plane> PaperPlaneArray= new ArrayList<Plane>();
		ArrayList<Goods> goodsArray = new ArrayList<Goods>();
		JFrame jf = new JFrame();
		jf.setSize(1000, 1000);
		jf.getContentPane().setBackground(Color.white);
		jf.setTitle("雷电游戏");
		jf.setDefaultCloseOperation(3);
		jf.setLocationRelativeTo(null);

		JPanel jp1 = new JPanel();
		jp1.setBackground(Color.white);
		jf.add(jp1, BorderLayout.NORTH);

//		String[] button = { "新游戏" };
		Listener listener = new Listener();
		listener.setlistener(listener);
//		
//		
//		for (int i = 0; i < button.length; i++) {
//			JButton but = new JButton(button[i]);
//			jp1.add(but);
//			but.addActionListener(listener);
//		}
		Plane bluePlane = new Plane(500,900,200,100,3,0);
	    int rand2 = -(int) (Math.random() * 1000);
		int rand3 = 300 + (int) (Math.random() * 700);
		Plane lxPlane = new Plane(rand3,rand2,200,100,3,0);
		for(int i=0;i<7;i++)
		{
	    int rand1 = -(int) (Math.random() * 1000);
		int rand = 200 + (int) (Math.random() * 800);
		Plane PaperPlane=new Plane(rand,rand1,50,50,2,0);
		PaperPlaneArray.add(PaperPlane);
		}
		for(int i=0;i<3;i++)
		{
		    int rand4 = -(int) (Math.random() * 1000);
			int rand5 = 200 + (int) (Math.random() * 800);			
		    Goods goods= new Goods(rand5,rand4,60,1);
		    goodsArray.add(goods);
		 }
		PlaneJpanel cp = new PlaneJpanel();
		cp.setPaperArray(PaperPlaneArray);
		cp.setBackground(Color.black);
		
        cp.addMouseListener(listener);
        
		jf.add(cp, BorderLayout.CENTER);
		
		

		jf.setVisible(true);

		Graphics g = cp.getGraphics();
		listener.setg(g);
		listener.setcp(cp);
		listener.setplane(bluePlane);
		listener.setlxplane(lxPlane);
		listener.setPaperPlane(PaperPlaneArray);
		listener.setgoods(goodsArray);
		cp.setlxplane(lxPlane);
		cp.setplane(bluePlane);
        cp.setcp(cp);
		cp.addKeyListener(listener);
		cp.setlistener(listener);		
		cp.setgoods(goodsArray);
		//获取焦点
		cp.requestFocus();
	}

}
