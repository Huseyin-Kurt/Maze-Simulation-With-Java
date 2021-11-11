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

public class Grafik1 extends JPanel {
	JLabel eksiyuzYazisi,yuzYazisi,ikiyuzYazisi,ucyuzYazisi,dortyuzYazisi,besyuzYazisi,altiyuzYazisi,yediyuzYazisi,sekizyuzYazisi,dokuzyuzYazisi,binYazisi;
	JFrame grafikPenceresi;
	ArrayList<Double> kazancGrafik;
	ArrayList<Integer>xKoordinat,yKoordinat;
	boolean cizildimi=false;
	
	
	public Grafik1(ArrayList<Double> kazancGrafik)
	{
		super();
		setLayout(null);
		this.setBackground(Color.pink);
		
		this.kazancGrafik=kazancGrafik;
		
		xKoordinat=new ArrayList<Integer>();
		yKoordinat=new ArrayList<Integer>();
		
		grafikPenceresi=new JFrame();
		grafikPenceresi.setSize(1920, 1080);
		grafikPenceresi.setLocationRelativeTo(null);
		grafikPenceresi.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		eksiyuzYazisi=new JLabel("-100");
		eksiyuzYazisi.setBounds(0,940 , 100, 85);
		eksiyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 25));
		eksiyuzYazisi.setForeground(Color.red);
		
		yuzYazisi=new JLabel("100");
		yuzYazisi.setBounds(0,760 , 100, 85);
		yuzYazisi.setFont(new Font("Arial", Font.PLAIN, 25));
		yuzYazisi.setForeground(Color.green);
		
		ikiyuzYazisi=new JLabel("200");
		ikiyuzYazisi.setBounds(0,680 , 100, 85);
		ikiyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 25));
		ikiyuzYazisi.setForeground(Color.green);
		
		ucyuzYazisi=new JLabel("300");
		ucyuzYazisi.setBounds(0,600 , 100, 85);
		ucyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 25));
		ucyuzYazisi.setForeground(Color.green);
		
		dortyuzYazisi=new JLabel("400");
		dortyuzYazisi.setBounds(0,520 , 100, 85);
		dortyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 25));
		dortyuzYazisi.setForeground(Color.green);
		
		besyuzYazisi=new JLabel("500");
		besyuzYazisi.setBounds(0,440 , 100, 85);
		besyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 25));
		besyuzYazisi.setForeground(Color.green);
		
		altiyuzYazisi=new JLabel("600");
		altiyuzYazisi.setBounds(0,360 , 100, 85);
		altiyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 25));
		altiyuzYazisi.setForeground(Color.green);
		
		yediyuzYazisi=new JLabel("700");
		yediyuzYazisi.setBounds(0,280 , 100, 85);
		yediyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 25));
		yediyuzYazisi.setForeground(Color.green);
		
		sekizyuzYazisi=new JLabel("800");
		sekizyuzYazisi.setBounds(0,200 , 100, 85);
		sekizyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 25));
		sekizyuzYazisi.setForeground(Color.green);
		
		dokuzyuzYazisi=new JLabel("900");
		dokuzyuzYazisi.setBounds(0,120 , 100, 85);
		dokuzyuzYazisi.setFont(new Font("Arial", Font.PLAIN, 25));
		dokuzyuzYazisi.setForeground(Color.green);
		
		binYazisi=new JLabel("1000");
		binYazisi.setBounds(0,40 , 100, 85);
		binYazisi.setFont(new Font("Arial", Font.PLAIN, 25));
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
		
		int carpan=1820/kazancGrafik.size();
		
		if(cizildimi==false)
		{
			for(int i=0;i<kazancGrafik.size();i++)
			{
				int x=80+i*carpan;
				int y=0;
				
				if(i==kazancGrafik.size()-1)
				{
					y=40;
					
					xKoordinat.add(x);
					yKoordinat.add(y);
					
					break;
				}
				
				else if(kazancGrafik.get(i)<0)
				{
					y=940+40;
				}
				
				else if(kazancGrafik.get(i)<=100&&kazancGrafik.get(i)>=0)
				{
					y=760+40;
				}
				
				else if(kazancGrafik.get(i)<=200&&kazancGrafik.get(i)>100)
				{
					y=680+40;
				}
				
				else if(kazancGrafik.get(i)<=300&&kazancGrafik.get(i)>200)
				{
					y=600+40;
				}
				
				else if(kazancGrafik.get(i)<=400&&kazancGrafik.get(i)>300)
				{
					y=520+40;
				}
				
				else if(kazancGrafik.get(i)<=500&&kazancGrafik.get(i)>400)
				{
					y=440+40;
				}
				
				else if(kazancGrafik.get(i)<=600&&kazancGrafik.get(i)>500)
				{
					y=360+40;
				}
				
				else if(kazancGrafik.get(i)<=700&&kazancGrafik.get(i)>600)
				{
					y=280+40;
				}
				
				else if(kazancGrafik.get(i)<=800&&kazancGrafik.get(i)>700)
				{
					y=200+40;
				}
				
				else if(kazancGrafik.get(i)<=900&&kazancGrafik.get(i)>800)
				{
					y=120+40;
				}
				
				else if(kazancGrafik.get(i)>900)
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
			if(i==0)
			{
				g.setColor(Color.green);
			}
			
			else
			{
				g.setColor(Color.white);
			}
			
			g.drawOval(xKoordinat.get(i), yKoordinat.get(i), 10, 10);
			g.fillOval(xKoordinat.get(i), yKoordinat.get(i), 10, 10);
			
			g.setColor(Color.black);
			g2.drawLine(xKoordinat.get(i)+5, yKoordinat.get(i)+5, xKoordinat.get(i+1)+5, yKoordinat.get(i+1)+5);
		}
		
		g.setColor(Color.blue);
		g.drawOval(xKoordinat.get(xKoordinat.size()-1), yKoordinat.get(yKoordinat.size()-1), 10, 10);
		g.fillOval(xKoordinat.get(xKoordinat.size()-1), yKoordinat.get(yKoordinat.size()-1), 10, 10);
		
		grafikPenceresi.setVisible(true);
	}
	
}
