package com.myf.plane1102;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PlaneJpanel extends JPanel {
    private Plane bluePlane;
    private ThreadBackGround tb3;
    private Graphics g;
    private Graphics buffgr;
    private Image iBuffer;
    private PlaneJpanel cp;
    private ImageIcon img1,img,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13,img14;
    private Listener listener;
    private ArrayList<Bullet> bulletsMe;
    private Plane lxPlane;
    private ThreadBullet tb2;
    private ArrayList<Plane>  PaperPlaneArray;
    private ThreadPlane tb;
    private int count=0;
    private String str;
	private ArrayList<Goods> goodsArray;
	
	public void setgoods(ArrayList<Goods> goodsArray) {
		
		this.goodsArray=goodsArray;
	}    
    public void settb(ThreadPlane tb)
    {
    	this.tb=tb;
    }
    public void setPaperArray(ArrayList<Plane> PaperPlaneArray)
    {
    	this.PaperPlaneArray=PaperPlaneArray;
    }
    public void settb2(ThreadBullet tb2)
    {
    	this.tb2=tb2;
    }
    public void setlxplane(Plane lxPlane)
    {
    	this.lxPlane=lxPlane;
    }
    public void setBulletsMe(ArrayList<Bullet> bulletsMe){
    	this.bulletsMe=bulletsMe;
    }
    public void setlistener(Listener listener)
    {
    	this.listener=listener;
    }
    public PlaneJpanel(){
        img=new ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\plane1102\\BluePlane.png");
		img1=new ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\plane1102\\timg.png");
		img2=new ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\plane1102\\zidan1.png");
		img3=new ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\plane1102\\LXPlane.png");
		img4=new ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\plane1102\\zidan2.png");
		img5=new ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\plane1102\\baozha.png");
		img6=new ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\plane1102\\PaperPlane_01.png");
		img7=new ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\plane1102\\first.png");
		img8=new ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\plane1102\\second.png");
		img9=new ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\plane1102\\third.png");
		img10=new ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\plane1102\\wuxian.png");
		img11=new ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\plane1102\\jijiubao.png");
		img12=new ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\plane1102\\fengmian.png");
		img13=new ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\plane1102\\anniu.png");
		img14=new ImageIcon("E:\\workspace\\mayifan\\src\\com\\myf\\plane1102\\restart.png");
    }
    public void setcp(PlaneJpanel cp)
    {
    	this.cp=cp;
    }
    public void settb3(ThreadBackGround tb3)
    {
    	this.tb3=tb3;
    }
	public void setplane(Plane bluePlane)
	{
		this.bluePlane=bluePlane;
	}

	public void paint(Graphics g)
	{
		super.paint(g);
	    if(iBuffer==null)
	    {
	    	iBuffer=createImage(cp.getWidth(),cp.getHeight());
	        buffgr=iBuffer.getGraphics();
	        if(listener.startFlag==0)
			g.drawImage(img12.getImage(),185, -60, 600 ,600 ,null);
	        g.setColor(Color.white);
	        g.drawString("游戏规则:",380,530);
	        g.drawString("1、通过上、下、左、右按键控制飞机移动。",380,580);         
	        g.drawString("2、按下空格键发射子弹进行攻击。",380,630);
	        g.drawString("3、敌机会对你进行攻击，击落敌机将获取积分。",380,680);
	        g.drawString("4、打中急救包可回复生命值并获取积分。",380,730);
	        g.drawString("5、游戏结束获取分数和排名。",380,780);
	        g.drawImage(img13.getImage(), 340, 790, 300, 150, null);
	    }	    
        if(listener.startFlag==1)
        {
	        //System.out.println(tb3);			
			buffgr.drawImage(img1.getImage(),0, tb3.BackGroundY, tb3.xMax , tb3.yMax-tb3.BackGroundY ,null);	
			buffgr.drawImage(img1.getImage(),0, 0, tb3.xMax , tb3.BackGroundY ,null);
			for(int i=0;i<goodsArray.size();i++)
			{
				Goods goods= goodsArray.get(i);
				buffgr.drawImage(img11.getImage(), goods.x-goods.size/2,goods.y-goods.size/2, goods.size, goods.size, null);
			}
			if(bluePlane.life>0)
			buffgr.drawImage(img.getImage(),bluePlane.x-bluePlane.size/2, bluePlane.y-bluePlane.size/2, bluePlane.size , bluePlane.size ,null);
			if(lxPlane.life>0)
			buffgr.drawImage(img3.getImage(),lxPlane.x-lxPlane.size/2, lxPlane.y-lxPlane.size/2, lxPlane.size ,lxPlane.size ,null);
			for(int i=0;i<PaperPlaneArray.size();i++)
			{
				Plane PaperPlane=PaperPlaneArray.get(i);
				buffgr.drawImage(img6.getImage(),PaperPlane.x, PaperPlane.y, PaperPlane.size ,PaperPlane.size ,null);
			}
			buffgr.setColor(Color.ORANGE);
			
			buffgr.drawString("血量:", 20, 893);
			buffgr.drawString("分数:", 20, 850);
			String str=String.valueOf(tb2.score);
			buffgr.drawString(str, 150, 850);
			buffgr.setColor(Color.green);
			buffgr.drawRect(70, 880, 200, 20);
			buffgr.fillRect(70, 880, (int)(((double)bluePlane.life/100)*200), 20);
			if(bluePlane.ExplodeFlag==1&&bluePlane.life>0)
			{
				buffgr.drawImage(img5.getImage(),bluePlane.x-bluePlane.size/2, bluePlane.y-bluePlane.size/2, bluePlane.size ,bluePlane.size ,null);
				bluePlane.ExplodeFlag=0;
			}
			if(lxPlane.ExplodeFlag==1&&lxPlane.life>0)
			{
				buffgr.drawImage(img5.getImage(),lxPlane.x-lxPlane.size/2, lxPlane.y-lxPlane.size/2, lxPlane.size ,lxPlane.size ,null);
				lxPlane.ExplodeFlag=0;
			}
			for(int i=0; i<PaperPlaneArray.size(); i++)
			{
				Plane PaperPlane=PaperPlaneArray.get(i);
			    if(PaperPlane.ExplodeFlag==1&&PaperPlane.life>0)
			    buffgr.drawImage(img5.getImage(),PaperPlane.x, PaperPlane.y, PaperPlane.size ,PaperPlane.size ,null);
				PaperPlane.ExplodeFlag=0;	
			}
			
						
			for(int i=0;i<Listener.bulletsMe.size();i++){
				if(bulletsMe.get(i).type==0)
				buffgr.drawImage(img2.getImage(),Listener.bulletsMe.get(i).x,Listener.bulletsMe.get(i).y,50,50,null);
				else if(bulletsMe.get(i).type==1)
				buffgr.drawImage(img4.getImage(),Listener.bulletsMe.get(i).x,Listener.bulletsMe.get(i).y,50,50,null);
				else if(bulletsMe.get(i).type==2)
				buffgr.drawImage(img4.getImage(),Listener.bulletsMe.get(i).x,Listener.bulletsMe.get(i).y,25,25,null);
			}
			if(tb.pass==1&&tb.passFlag==1)
			{
				buffgr.drawImage(img7.getImage(),cp.getWidth()/2-200, cp.getHeight()/2-100,  400, 200, null);
				count++;
				if(count==10)
				{
				tb.passFlag=0;
				count=0;
				}
			}
			if(tb.pass==2&&tb.passFlag==1)
			{
				buffgr.drawImage(img8.getImage(),cp.getWidth()/2-200, cp.getHeight()/2-100,  400, 200, null);
				count++;
				if(count==10)
				{
				tb.passFlag=0;
				count=0;
				}
			}
			if(tb.pass==3&&tb.passFlag==1)
			{
				buffgr.drawImage(img9.getImage(),cp.getWidth()/2-200, cp.getHeight()/2-100,  400, 200, null);
				count++;
				if(count==10)
				{
				tb.passFlag=0;
				count=0;
				}
			}
			if(tb.pass==4&&tb.passFlag==1)
			{
				buffgr.drawImage(img10.getImage(),cp.getWidth()/2-200, cp.getHeight()/2-100,  400, 200, null);
				count++;
				if(count==10)
				{
				tb.passFlag=0;
				count=0;
				}
			}
			buffgr.drawImage(img14.getImage(), 895, 865, 50,50, null);
			//buffgr.drawImage(img2, BulletsMe.x, BulletsMe.y-100, observer);
	        g.drawImage(iBuffer, 0, 0, cp.getWidth(),cp.getHeight(),null);
        }
	}

	
}
