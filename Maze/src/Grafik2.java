import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Grafik2 extends JPanel {
	JLabel eksiyuzYazisi,yuzYazisi,ikiyuzYazisi,ucyuzYazisi,dortyuzYazisi,besyuzYazisi,altiyuzYazisi,yediyuzYazisi,sekizyuzYazisi,dokuzyuzYazisi,binYazisi;
	JFrame grafikPenceresi;
	ArrayList<Integer> adimGrafik;
	ArrayList<Integer>xKoordinat,yKoordinat;
	boolean cizildimi=false;
	
	
	public Grafik2(ArrayList<Integer> adimGrafik)
	{
		super();
		setLayout(null);
		this.setBackground(Color.pink);
		
		this.adimGrafik=adimGrafik;
		
		xKoordinat=new ArrayList<Integer>();
		yKoordinat=new ArrayList<Integer>();
		
		grafikPenceresi=new JFrame();
		grafikPenceresi.setSize(1920, 1080);
		grafikPenceresi.setLocationRelativeTo(null);
		grafikPenceresi.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		eksiyuzYazisi=new JLabel("0");
		eksiyuzYazisi.setBounds(0,940 , 100, 85);
		eksiyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 15));
		eksiyuzYazisi.setForeground(Color.red);
		
		yuzYazisi=new JLabel("1250");
		yuzYazisi.setBounds(0,760 , 100, 85);
		yuzYazisi.setFont(new Font("Arial", Font.PLAIN, 15));
		yuzYazisi.setForeground(Color.green);
		
		ikiyuzYazisi=new JLabel("2500");
		ikiyuzYazisi.setBounds(0,680 , 100, 85);
		ikiyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 15));
		ikiyuzYazisi.setForeground(Color.green);
		
		ucyuzYazisi=new JLabel("3750");
		ucyuzYazisi.setBounds(0,600 , 100, 85);
		ucyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 15));
		ucyuzYazisi.setForeground(Color.green);
		
		dortyuzYazisi=new JLabel("5000");
		dortyuzYazisi.setBounds(0,520 , 100, 85);
		dortyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 15));
		dortyuzYazisi.setForeground(Color.green);
		
		besyuzYazisi=new JLabel("7500");
		besyuzYazisi.setBounds(0,440 , 100, 85);
		besyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 15));
		besyuzYazisi.setForeground(Color.green);
		
		altiyuzYazisi=new JLabel("8750");
		altiyuzYazisi.setBounds(0,360 , 100, 85);
		altiyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 15));
		altiyuzYazisi.setForeground(Color.green);
		
		yediyuzYazisi=new JLabel("10000");
		yediyuzYazisi.setBounds(0,280 , 100, 85);
		yediyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 15));
		yediyuzYazisi.setForeground(Color.green);
		
		sekizyuzYazisi=new JLabel("11250");
		sekizyuzYazisi.setBounds(0,200 , 100, 85);
		sekizyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 15));
		sekizyuzYazisi.setForeground(Color.green);
		
		dokuzyuzYazisi=new JLabel("12500");
		dokuzyuzYazisi.setBounds(0,120 , 100, 85);
		dokuzyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 15));
		dokuzyuzYazisi.setForeground(Color.green);
		
		binYazisi=new JLabel("15000");
		binYazisi.setBounds(0,40 , 100, 85);
		binYazisi.setFont(new Font("Arial", Font.PLAIN, 15));
		binYazisi.setForeground(Color.green);
		
		
		this.add(eksiyuzYazisi);
		this.add(yuzYazisi);
		this.add(ikiyuzYazisi);
		this.add(ucyuzYazisi);
		this.add(dortyuzYazisi);
		this.add(besyuzYazisi);
		this.add(altiyuzYazisi);
		this.add(yediyuzYazisi);
		this.add(sekizyuzYazisi);
		this.add(dokuzyuzYazisi);
		this.add(binYazisi);
		
		grafikPenceresi.add(this);
		grafikPenceresi.setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		g.drawLine(80, 880, 1900, 880);
		
		int carpan=1820/adimGrafik.size();
		
		if(cizildimi==false)
		{
			for(int i=0;i<adimGrafik.size();i++)
			{
				int x=80+i*carpan;
				int y=0;
				
				
				 if(adimGrafik.get(i)<1250)
				{
					y=940+40;
				}
				
				else if(adimGrafik.get(i)<=2500&&adimGrafik.get(i)>=1250)
				{
					y=760+40;
				}
				
				else if(adimGrafik.get(i)<=3750&&adimGrafik.get(i)>2500)
				{
					y=680+40;
				}
				
				else if(adimGrafik.get(i)<=5000&&adimGrafik.get(i)>3750)
				{
					y=600+40;
				}
				
				else if(adimGrafik.get(i)<=6250&&adimGrafik.get(i)>5000)
				{
					y=520+40;
				}
				
				else if(adimGrafik.get(i)<=7500&&adimGrafik.get(i)>6250)
				{
					y=440+40;
				}
				
				else if(adimGrafik.get(i)<=8750&&adimGrafik.get(i)>7500)
				{
					y=360+40;
				}
				
				else if(adimGrafik.get(i)<=10000&&adimGrafik.get(i)>8750)
				{
					y=280+40;
				}
				
				else if(adimGrafik.get(i)<=11250&adimGrafik.get(i)>10000)
				{
					y=200+40;
				}
				
				else if(adimGrafik.get(i)<=12500&&adimGrafik.get(i)>11250)
				{
					y=120+40;
				}
				
				else if(adimGrafik.get(i)>12500)
				{
					y=40+40;
				}
				
				xKoordinat.add(x);
				yKoordinat.add(y);
				
			}
			
			cizildimi=true;
		}
		
		
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke(3));
	    
	    
		for(int i=0;i<xKoordinat.size()-1;i++)
		{	
			
			g.setColor(Color.white);
			g.drawOval(xKoordinat.get(i), yKoordinat.get(i), 10, 10);
			g.fillOval(xKoordinat.get(i), yKoordinat.get(i), 10, 10);
			
			g.setColor(Color.black);
			g2.drawLine(xKoordinat.get(i)+5, yKoordinat.get(i)+5, xKoordinat.get(i+1)+5, yKoordinat.get(i+1)+5);
		}
		
		g.setColor(Color.white);
		g.drawOval(xKoordinat.get(xKoordinat.size()-1), yKoordinat.get(yKoordinat.size()-1), 10, 10);
		g.fillOval(xKoordinat.get(xKoordinat.size()-1), yKoordinat.get(yKoordinat.size()-1), 10, 10);
		
		grafikPenceresi.setVisible(true);
	}
	
}
