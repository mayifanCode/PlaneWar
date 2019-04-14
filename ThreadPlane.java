package com.myf.plane1102;

import java.awt.Graphics;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ThreadPlane extends Thread{
     public Graphics g;
	 public PlaneJpanel cp;
	 private Listener listener;
	 private Plane lxPlane;
	 private Plane bluePlane;
	 public static int count=0;
	 private ArrayList<Plane> PaperPlaneArray;
	 private ArrayList<Bullet> bulletsMe;
	 public static  int  pass = 1  ; //1:第一关
	 public static  int  passFlag=1;//用于产生关卡提示
	private ArrayList<Goods> goodsArray;
	public void setgoods1(ArrayList<Goods> goodsArray) {
		// TODO Auto-generated method stub
		this.goodsArray=goodsArray;
	}
	 public void setblue(Plane bluePlane)
	 {
		 this.bluePlane=bluePlane;
	 }
	public void setbullet(ArrayList<Bullet> bulletsMe) 
	{
		this.bulletsMe = bulletsMe;
	}
	 public void setPaper(ArrayList<Plane> PaperPlaneArray)
	 {
		 this.PaperPlaneArray=PaperPlaneArray;
	 }
	 public void setlxplane(Plane lxPlane)
	 {
		 this.lxPlane=lxPlane;
	 }
	 public void setlistener(Listener listener)
	 {
		 this.listener=listener;
	 }
    public void setcp(PlaneJpanel cp)
    {
    	this.cp=cp;
    }
	public void setg(Graphics g)
	{
		this.g=g;	
	}
	public void run()
	{
		while(true)
		{		
			cp.repaint();
			for(int i=0;i<PaperPlaneArray.size();i++)
			{
				Plane PaperPlane=PaperPlaneArray.get(i);
				PaperPlane.y+=PaperPlane.vy;
				if(count%15==0&&PaperPlane.life>0&&PaperPlane.y>0&&PaperPlane.y<1000)
				{
						int num = 1 + (int) (Math.random() * 15);									
						Bullet bullet=new Bullet(PaperPlane.x+13,PaperPlane.y+45,num,2);
						int random = 1+(int)(Math.random()*3);
						if(random==3)
						bulletsMe.add(bullet);					
				}
			}
			lxPlane.y+=lxPlane.vy;
			if(count%18==0&&lxPlane.life>0&&lxPlane.y+lxPlane.size/2>0&&lxPlane.y<1000)
			{
			    int random1 = 1+(int)(Math.random()*2);
				if(random1==2)
			    listener.addBulletEnemy();				   
			}
			count++;
			if(count==650)
			{
			      count=0;
			      int rand2 = -(int) (Math.random() * 1000);
				  int rand3 = 200 + (int) (Math.random() * 800);
			      lxPlane.y=rand2;
			      lxPlane.x=rand3;
			      lxPlane.life=100;
			      for(int j=0;j<PaperPlaneArray.size();j++)
			      {
			    	  Plane plane = PaperPlaneArray.get(j);
				      rand2 = -(int) (Math.random() * 1000);
					  rand3 = 200 + (int) (Math.random() * 800);
					  plane.y=rand2;
					  plane.x=rand3;
					  plane.life=100;							  
			      }
			      for(int k=0;k<goodsArray.size();k++){
			    	  Goods goods= goodsArray.get(k);
				      rand2 = -(int) (Math.random() * 1000);
					  rand3 = 200 + (int) (Math.random() * 800);
					  goods.y=rand2;
					  goods.x=rand3;
			    	  }
					for(int i=0;i<3;i++)
					{
				    int rand1 = -(int) (Math.random() * 1000);
					int rand = 200 + (int) (Math.random() * 800);
					Plane PaperPlane=new Plane(rand,rand1,50,50,2,0);
					PaperPlaneArray.add(PaperPlane);
					}
					
				    int rand4 = -(int) (Math.random() * 1000);
					int rand5 = 200 + (int) (Math.random() * 800);			
				    Goods goods= new Goods(rand5,rand4,60,1);
				    goodsArray.add(goods);
					
						
			      pass++;
			      passFlag=1;
			}
			    for(int j=0;j<goodsArray.size();j++)
			    {
			    	Goods goods= goodsArray.get(j);
			    	goods.y+=5;
			    }
			
			
			
			
			
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}		
		}
		
		
		
		
	}

	
	
	
	
}
	
	
	

