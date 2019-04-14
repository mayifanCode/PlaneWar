package com.myf.plane1102;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class ThreadBullet extends Thread {
	public ArrayList<Bullet> bulletsMe;
	public Plane bluePlane;
	public Plane lxPlane;
	private ArrayList<Plane> PaperPlaneArray;
	public static int score = 0;
	private ArrayList<Goods> goodsArray;
	private FileOutputStream fop = null;
	private int rank;
	private int firstScore;
	private Object obj = new Object();

	public void setbluePlane(ArrayList<Plane> PaperPlaneArray) {
		this.PaperPlaneArray = PaperPlaneArray;
	}

	public void setlxPlane(Plane lxPlane) {
		this.lxPlane = lxPlane;
	}

	public void setbluePlane(Plane bluePlane) {
		this.bluePlane = bluePlane;
	}

	public void setbullets(ArrayList<Bullet> bulletsMe) {
		this.bulletsMe = bulletsMe;
	}

	public void run() {
		// File file= new
		// File("E:\\workspace\\mayifan\\src\\com\\myf\\plane1102\\data.txt");
		while (true) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.getStackTrace();
			}
			grameRun();
		}
	}

	public synchronized void grameRun() {

		for (int i = 0; i < Listener.bulletsMe.size(); i++) {
			if (Listener.bulletsMe.get(i).type == 0)// 我方飞机攻击敌方飞机
			{
				Bullet bullet = Listener.bulletsMe.get(i);
				bullet.y -= 5;
				if (bullet.y < lxPlane.y + lxPlane.size / 2 && bullet.y > lxPlane.y - lxPlane.size / 2
						&& bullet.x > lxPlane.x - lxPlane.size / 2 && bullet.x < lxPlane.x + lxPlane.size / 2
						&& bullet.y > 0 && bullet.y < 1000) {
					bullet.y = -100;
					lxPlane.ExplodeFlag = 1;
					lxPlane.life -= bullet.harm;
					if (lxPlane.life < 0) {
						score = score + 200;
						lxPlane.life = 0;
						lxPlane.y = 1500;
					}
					// System.out.println("敌机血量："+lxPlane.life);
				}
				for (int j = 0; j < PaperPlaneArray.size(); j++) {
					Plane PaperPlane = PaperPlaneArray.get(j);
					if (bullet.y < PaperPlane.y + PaperPlane.size && bullet.y > PaperPlane.y && bullet.x > PaperPlane.x
							&& bullet.x < PaperPlane.x + PaperPlane.size && bullet.y > 0 && bullet.y < 1000) {
						bullet.y = -100;
						PaperPlane.ExplodeFlag = 1;
						PaperPlane.life -= bullet.harm;
						if (PaperPlane.life < 0) {
							score = score + 50;
							PaperPlane.life = 0;
							PaperPlane.y = 1500;
						}
						// System.out.println("敌机血量："+lxPlane.life);
					}
				}
				for (int k = 0; k < goodsArray.size(); k++) {
					Goods goods = goodsArray.get(k);
					if (bullet.y < goods.y + goods.size / 2 && bullet.y > goods.y - goods.size / 2
							&& bullet.x > goods.x - goods.size / 2 && bullet.x < goods.x + goods.size / 2
							&& bullet.y > 0 && bullet.y < 1000) {
						bullet.y = -100;
						goods.y = 1100;
						bluePlane.life += 20;
						if (bluePlane.life >= 100)
							bluePlane.life = 100;
						score+=30;
					}
				}
			} else if (Listener.bulletsMe.get(i).type == 1)// 敌方大飞机攻击我方飞机
			{
				Bullet bullet = Listener.bulletsMe.get(i);
				bullet.y += 30;
				if (bullet.y > bluePlane.y - bluePlane.size / 2 && bullet.y < bluePlane.y + bluePlane.size / 2
						&& bullet.x > bluePlane.x - bluePlane.size / 2 && bullet.x < bluePlane.x + bluePlane.size / 2
						&& bullet.y > 0 && bullet.y < 1000) {
					bullet.y = 1100;
					bluePlane.ExplodeFlag = 1;
					bluePlane.life -= bullet.harm;
					if (bluePlane.life <= 0) {
						bluePlane.life = 0;
						bluePlane.y = 2000;						 
						InPut();
						result();
					}
					// System.out.println("我方血量："+bluePlane.life);
				}
			} else if (Listener.bulletsMe.get(i).type == 2)// 敌方小飞机攻击我方飞机
			{
				Bullet bullet = Listener.bulletsMe.get(i);
				bullet.y += 10;
				if (bullet.y > bluePlane.y - bluePlane.size / 2 && bullet.y < bluePlane.y + bluePlane.size / 2
						&& bullet.x > bluePlane.x - bluePlane.size / 2 && bullet.x < bluePlane.x + bluePlane.size / 2
						&& bullet.y > 0 && bullet.y < 1000) {
					bullet.y = 1100;
					bluePlane.ExplodeFlag = 1;
					bluePlane.life -= bullet.harm;
					if (bluePlane.life < 0) {
						bluePlane.life = 0;
						bluePlane.y = 2000;
						InPut();
						result();
					}
					// System.out.println("我方血量："+bluePlane.life);
				}
			}

			// System.out.println("bullet.y = "+bullet.y);
		}

	}

	public void setgoods(ArrayList<Goods> goodsArray) {
		// TODO Auto-generated method stub
		this.goodsArray = goodsArray;
	}

	public void result() {
		JFrame jf2 = new JFrame();
		jf2.setSize(100, 150);
		jf2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jf2.setLocationRelativeTo(null);
		FlowLayout flow = new FlowLayout();
		jf2.setLayout(flow);
		String str = String.valueOf(score);
		JLabel jb = new JLabel("本局得分：" + str);
		JLabel jb2 = new JLabel("历史最高分：" + firstScore);
		JLabel jb3 = new JLabel("您的排名：" + rank);
		jf2.add(jb);
		jf2.add(jb2);
		jf2.add(jb3);
		jf2.setVisible(true);
	}

	public void InPut() {
		int n = 100;
		int[] arr1 = new int[n];
		int[] arr2 = new int[n];

		// for(int i=0;i<n;i++)
		// {
		// arr1[i] = 1+(int)(Math.random()*100);
		// }
       
		try {
			File file = new File("E:\\workspace\\mayifan\\src\\com\\myf\\plane1102\\data.txt"); // 存放数组数据的文件
			BufferedReader in = new BufferedReader(new FileReader(file)); // 把字节转为字符，然后可以read
																			// ,字符缓冲输入流
			String line; // 行数据
			// 逐行读取，并将每个数据放入到数组中
			String[] temp = null;
					while ((line = in.readLine()) != null) {
						temp = line.split("\t");
						for (int j = 0; j < temp.length; j++) {
							arr2[j] = (int) Double.parseDouble(temp[j]);
						}
					}
					in.close();
		            
						// 显示读出的数据
						for (int i = 0; i < temp.length; i++) {
							//System.out.print(arr2[i] + "\t");
						}
			
						for (int i = 0; i < temp.length; i++) {
							if (score >= arr2[i]) {
								for (int j = temp.length - 1; j >= i; j--) {
									arr2[j + 1] = arr2[j];
								}
								arr2[i] = score;
								rank = i + 1;
								break;
							}
						}
						firstScore = arr2[0];
						//System.out.println("\r\n");
						// 显示修改后的数据
//						for (int i = 0; i < temp.length + 1; i++) {
//							System.out.print(arr2[i] + "\t");
//						}

			FileWriter out = new FileWriter(file); // 文件写入流

			for (int i = 0; i < temp.length + 1; i++) {
				out.write(arr2[i] + "\t");
			}
			out.close();

		} catch (IOException e) {
			e.getStackTrace();
		}

	}

}
