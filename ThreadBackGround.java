package com.myf.plane1102;

public class ThreadBackGround extends Thread{
    public int BackGroundY;
	public PlaneJpanel cp;
	public  int xMax;
	public  int yMax;
    public void setcp(PlaneJpanel cp)
    {
    	this.cp=cp;
    }
	public void run()
	{
		    xMax=cp.getWidth();	
			yMax=cp.getHeight();
		while(true)
		{						
			BackGroundY+=100;
			
			if(BackGroundY>=yMax)
				BackGroundY=0;
			try{
				Thread.sleep(200);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}		
     }
	
	
	
	
	
	
}
