import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CalismaMenusu extends JPanel implements ActionListener {
	
	JFrame pencere;
	GridLayout yonetici;
	JPanel calismaPenceresi;
	Alan[][] alanlar;
	int[][][][] rMatris;
	Qmatris[][] qMatris;
	int baslangicSatir,baslangicSutun,hedefSatir,hedefSutun;
	
	JFrame menuPenceresi;
	JPanel menu;
	JButton yazdirmaButonu, rMatrisButonu,qMatrisButonu,kontrolButonu,oynatButonu,grafikButonu,grafik2Butonu;
	
	ArrayList<Integer>yolSatirlari;
	ArrayList<Integer>yolSutunlari;
	Timer zamanlayici;
	boolean basladimi=false;
	int yolSayaci=0;
	
	ArrayList<Double>kazancGrafik;
	
	ArrayList<Integer>adimGrafik;
	
	public CalismaMenusu(JFrame pencere,int baslangicSatir,int baslangicSutun,int hedefSatir,int hedefSutun) throws IOException
	{
		super();
		setLayout(null);
		
		yolSatirlari=new ArrayList<Integer>();
		yolSutunlari=new ArrayList<Integer>();
		
		zamanlayici=new Timer(500,this);
		
		kazancGrafik=new ArrayList<Double>();
		
		adimGrafik=new ArrayList<Integer>();
		
		this.pencere=pencere;
		
		pencere.setVisible(false);
		pencere.setSize(1920,1080);
		pencere.setLocationRelativeTo(null);
		pencere.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		yonetici=new GridLayout(50,50);
		
		calismaPenceresi=new JPanel();
		calismaPenceresi.setSize(1920,1050);
		calismaPenceresi.setLayout(yonetici);
		
		
		alanlar=new Alan[50][50];
		
		for(int i=0;i<50;i++)
		{
			for(int j=0;j<50;j++)
			{
				alanlar[i][j]=new Alan();
				alanlar[i][j].durum="bos";
				alanlar[i][j].deger=(int)(Math.random()*9)+1;
				alanlar[i][j].setText(Integer.toString(alanlar[i][j].deger));
				alanlar[i][j].setBackground(Color.white);
				alanlar[i][j].setFont(new Font("Arial", Font.PLAIN, 8));
			}
		}
		
		
		int kontrol=(50*50*30)/100;
		int duvarSayisi=0;
		
		while(true)
		{
			int i=(int)(Math.random()*50);
			int j=(int)(Math.random()*50);
			
			if(!alanlar[i][j].durum.equals("duvar"))
			{
				alanlar[i][j].durum="duvar";
				alanlar[i][j].setBackground(Color.red);
				duvarSayisi++;
				
				if(duvarSayisi==kontrol)
				{
					break;
				}
			}
		}
		
		
		
		alanlar[baslangicSatir][baslangicSutun].durum="baslangic";
		alanlar[baslangicSatir][baslangicSutun].setBackground(Color.green);
		alanlar[baslangicSatir][baslangicSutun].setText("");
		
		alanlar[hedefSatir][hedefSutun].durum="hedef";
		alanlar[hedefSatir][hedefSutun].setBackground(Color.blue);
		alanlar[hedefSatir][hedefSutun].setText("");
		
		
		for(int i=0;i<50;i++)
		{
			for(int j=0;j<50;j++)
			{
				calismaPenceresi.add(alanlar[i][j]);
			}
		}
		
		
		add(calismaPenceresi);
		
		pencere.add(this);
		pencere.setVisible(true);
		
		this.baslangicSatir=baslangicSatir;
		this.baslangicSutun=baslangicSutun;
		this.hedefSatir=hedefSatir;
		this.hedefSutun=hedefSutun;
		
		
		menuOlustur();

	}
	
	public void menuOlustur()
	{
		menuPenceresi=new JFrame();
		menuPenceresi.setSize(400, 780);
		menuPenceresi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuPenceresi.setLocationRelativeTo(null);
		
		menu=new JPanel();
		menu.setLayout(null);
		menu.setSize(400,780);
		
		yazdirmaButonu=new JButton();
		yazdirmaButonu.setText("Alaný Yazdýr");
		yazdirmaButonu.setBounds(100, 0, 200, 50);
		yazdirmaButonu.setBackground(Color.yellow);
		yazdirmaButonu.setFont(new Font("Arial", Font.PLAIN, 20));
		yazdirmaButonu.addActionListener(this);
		
		rMatrisButonu=new JButton();
		rMatrisButonu.setText("R Matris Hesabý");
		rMatrisButonu.setBounds(100, 100, 200, 50);
		rMatrisButonu.setBackground(Color.yellow);
		rMatrisButonu.setFont(new Font("Arial", Font.PLAIN, 20));
		rMatrisButonu.addActionListener(this);
		
		qMatrisButonu=new JButton();
		qMatrisButonu.setText("Q Matris Hesabý");
		qMatrisButonu.setBounds(100, 200, 200, 50);
		qMatrisButonu.setBackground(Color.red);
		qMatrisButonu.setFont(new Font("Arial", Font.PLAIN, 20));
		qMatrisButonu.addActionListener(this);
		
		kontrolButonu=new JButton();
		kontrolButonu.setText("Yol Kontrolü");
		kontrolButonu.setBounds(100, 300, 200, 50);
		kontrolButonu.setBackground(Color.red);
		kontrolButonu.setFont(new Font("Arial", Font.PLAIN, 20));
		kontrolButonu.addActionListener(this);
		
		oynatButonu=new JButton();
		oynatButonu.setText("Hareket Ettir");
		oynatButonu.setBounds(100, 400, 200, 50);
		oynatButonu.setBackground(Color.red);
		oynatButonu.setFont(new Font("Arial", Font.PLAIN, 20));
		oynatButonu.addActionListener(this);
		
		grafikButonu=new JButton();
		grafikButonu.setText("Kazanç Grafik");
		grafikButonu.setBounds(100, 500, 200, 50);
		grafikButonu.setBackground(Color.red);
		grafikButonu.setFont(new Font("Arial", Font.PLAIN, 20));
		grafikButonu.addActionListener(this);
		
		grafik2Butonu=new JButton();
		grafik2Butonu.setText("Adým Grafik");
		grafik2Butonu.setBounds(100, 600, 200, 50);
		grafik2Butonu.setBackground(Color.red);
		grafik2Butonu.setFont(new Font("Arial", Font.PLAIN, 20));
		grafik2Butonu.addActionListener(this);
		
		menu.add(yazdirmaButonu);
		menu.add(rMatrisButonu);
		menu.add(qMatrisButonu);
		menu.add(kontrolButonu);
		menu.add(oynatButonu);
		menu.add(grafikButonu);
		menu.add(grafik2Butonu);
		
		menuPenceresi.add(menu);
		menuPenceresi.setVisible(true);
		
	}
	
	public void yazdirma() throws IOException
	{
		File dosya;
		FileWriter dosyaYazicisi;
		BufferedWriter yazici;
		
		dosya=new File("engel.txt");
		dosyaYazicisi=new FileWriter(dosya.getAbsoluteFile());
		yazici=new BufferedWriter(dosyaYazicisi);
		
		for(int i=0;i<50;i++)
		{	
			for(int j=0;j<50;j++)
			{
				yazici.write("("+i+","+j+")\t deðer="+alanlar[i][j].deger+"\t durum="+alanlar[i][j].durum+"\n");
			}
		}
		
		yazici.close();
		
		System.out.println("Alan bilgileri engel.txtye yazdýrýldý");
		
	}
	
	public void rMatrisBulma()
	{
		rMatris=new int[50][50][50][50];
		
		for(int i=0;i<50;i++)
		{
			for(int j=0;j<50;j++)
			{
				for(int k=0;k<50;k++)
				{
					for(int l=0;l<50;l++)
					{	
						
						
						rMatris[i][j][k][l]=-1000;
					}
				}
			}
		}
		
		
		for(int i=0;i<50;i++)
		{
			for(int j=0;j<50;j++)
			{
				//en soldakiler
				if(j==0)
				{
					//0,0 indisi
					if(i==0)
					{	
						//sag
						if(!alanlar[i][j+1].durum.equals("duvar"))
						{
							rMatris[i][j][i][j+1]=0;
							
							if(alanlar[i][j+1].durum.equals("hedef"))
							{
								rMatris[i][j][i][j+1]=1000;
							}
						}
						
						//sað alt
						if(!alanlar[i+1][j+1].durum.equals("duvar"))
						{
							rMatris[i][j][i+1][j+1]=0;
							
							if(alanlar[i+1][j+1].durum.equals("hedef"))
							{
								rMatris[i][j][i+1][j+1]=1000;
							}
						}
						
						//alt
						if(!alanlar[i+1][j].durum.equals("duvar"))
						{
							rMatris[i][j][i+1][j]=0;
							
							if(alanlar[i+1][j].durum.equals("hedef"))
							{
								rMatris[i][j][i+1][j]=1000;
							}
						}
						
					}
					
					//49,0 indisi
					
					else if(i==49)
					{
						//üst
						if(!alanlar[i-1][j].durum.equals("duvar"))
						{
							rMatris[i][j][i-1][j]=0;
							
							if(alanlar[i-1][j].durum.equals("hedef"))
							{
								rMatris[i][j][i-1][j]=1000;
							}
						}
						
						//sag üst
						if(!alanlar[i-1][j+1].durum.equals("duvar"))
						{
							rMatris[i][j][i-1][j+1]=0;
							
							if(alanlar[i-1][j+1].durum.equals("hedef"))
							{
								rMatris[i][j][i-1][j+1]=1000;
							}
						}
						
						//sag
						if(!alanlar[i][j+1].durum.equals("duvar"))
						{
							rMatris[i][j][i][j+1]=0;
							
							if(alanlar[i][j+1].durum.equals("hedef"))
							{
								rMatris[i][j][i][j+1]=1000;
							}
						}
					}
					
					//digerleri
					else
					{
						//üst
						if(!alanlar[i-1][j].durum.equals("duvar"))
						{
							rMatris[i][j][i-1][j]=0;
							
							if(alanlar[i-1][j].durum.equals("hedef"))
							{
								rMatris[i][j][i-1][j]=1000;
							}
						}
						
						//sag üst
						if(!alanlar[i-1][j+1].durum.equals("duvar"))
						{
							rMatris[i][j][i-1][j+1]=0;
							
							if(alanlar[i-1][j+1].durum.equals("hedef"))
							{
								rMatris[i][j][i-1][j+1]=1000;
							}
						}
						
						//sag
						if(!alanlar[i][j+1].durum.equals("duvar"))
						{
							rMatris[i][j][i][j+1]=0;
							
							if(alanlar[i][j+1].durum.equals("hedef"))
							{
								rMatris[i][j][i][j+1]=1000;
							}
						}
						
						//sað alt
						if(!alanlar[i+1][j+1].durum.equals("duvar"))
						{
							rMatris[i][j][i+1][j+1]=0;
							
							if(alanlar[i+1][j+1].durum.equals("hedef"))
							{
								rMatris[i][j][i+1][j+1]=1000;
							}
						}
						
						//alt
						if(!alanlar[i+1][j].durum.equals("duvar"))
						{
							rMatris[i][j][i+1][j]=0;
							
							if(alanlar[i+1][j].durum.equals("hedef"))
							{
								rMatris[i][j][i+1][j]=1000;
							}
						}
						
						
					}
				}
				
				//En saðdakiler
				else if(j==49)
				{
					//0,49 indisi
					if(i==0)
					{
						//sol
						if(!alanlar[i][j-1].durum.equals("duvar"))
						{
							rMatris[i][j][i][j-1]=0;
							
							if(alanlar[i][j-1].durum.equals("hedef"))
							{
								rMatris[i][j][i][j-1]=1000;
							}
						}
						
						//sol alt
						if(!alanlar[i+1][j-1].durum.equals("duvar"))
						{
							rMatris[i][j][i+1][j-1]=0;
							
							if(alanlar[i+1][j-1].durum.equals("hedef"))
							{
								rMatris[i][j][i+1][j-1]=1000;
							}
						}
						
						//alt
						if(!alanlar[i+1][j].durum.equals("duvar"))
						{
							rMatris[i][j][i+1][j]=0;
							
							if(alanlar[i+1][j].durum.equals("hedef"))
							{
								rMatris[i][j][i+1][j]=1000;
							}
						}
					}
					
					//49,49 indisi
					else if(i==49)
					{
						//üst
						if(!alanlar[i-1][j].durum.equals("duvar"))
						{
							rMatris[i][j][i-1][j]=0;
							
							if(alanlar[i-1][j].durum.equals("hedef"))
							{
								rMatris[i][j][i-1][j]=1000;
							}
						}
						
						//sol üst
						if(!alanlar[i-1][j-1].durum.equals("duvar"))
						{
							rMatris[i][j][i-1][j-1]=0;
							
							if(alanlar[i-1][j-1].durum.equals("hedef"))
							{
								rMatris[i][j][i-1][j-1]=1000;
							}
						}
						
						//sol
						if(!alanlar[i][j-1].durum.equals("duvar"))
						{
							rMatris[i][j][i][j-1]=0;
							
							if(alanlar[i][j-1].durum.equals("hedef"))
							{
								rMatris[i][j][i][j-1]=1000;
							}
						}
					}
					
					//digerleri
					else
					{
						//üst
						if(!alanlar[i-1][j].durum.equals("duvar"))
						{
							rMatris[i][j][i-1][j]=0;
							
							if(alanlar[i-1][j].durum.equals("hedef"))
							{
								rMatris[i][j][i-1][j]=1000;
							}
						}
						
						//sol üst
						if(!alanlar[i-1][j-1].durum.equals("duvar"))
						{
							rMatris[i][j][i-1][j-1]=0;
							
							if(alanlar[i-1][j-1].durum.equals("hedef"))
							{
								rMatris[i][j][i-1][j-1]=1000;
							}
						}
						
						//sol
						if(!alanlar[i][j-1].durum.equals("duvar"))
						{
							rMatris[i][j][i][j-1]=0;
							
							if(alanlar[i][j-1].durum.equals("hedef"))
							{
								rMatris[i][j][i][j-1]=1000;
							}
						}
						
						//sol alt
						if(!alanlar[i+1][j-1].durum.equals("duvar"))
						{
							rMatris[i][j][i+1][j-1]=0;
							
							if(alanlar[i+1][j-1].durum.equals("hedef"))
							{
								rMatris[i][j][i+1][j-1]=1000;
							}
						}
						
						//alt
						if(!alanlar[i+1][j].durum.equals("duvar"))
						{
							rMatris[i][j][i+1][j]=0;
							
							if(alanlar[i+1][j].durum.equals("hedef"))
							{
								rMatris[i][j][i+1][j]=1000;
							}
						}
					}
				}
				
				//en üsttekiler
				else if(i==0)
				{
					//0,0 ve 0,49 harici
					if(j!=0&&j!=49)
					{
						//sol
						if(!alanlar[i][j-1].durum.equals("duvar"))
						{
							rMatris[i][j][i][j-1]=0;
							
							if(alanlar[i][j-1].durum.equals("hedef"))
							{
								rMatris[i][j][i][j-1]=1000;
							}
						}
						
						//sol alt
						if(!alanlar[i+1][j-1].durum.equals("duvar"))
						{
							rMatris[i][j][i+1][j-1]=0;
							
							if(alanlar[i+1][j-1].durum.equals("hedef"))
							{
								rMatris[i][j][i+1][j-1]=1000;
							}
						}
						
						//alt
						if(!alanlar[i+1][j].durum.equals("duvar"))
						{
							rMatris[i][j][i+1][j]=0;
							
							if(alanlar[i+1][j].durum.equals("hedef"))
							{
								rMatris[i][j][i+1][j]=1000;
							}
						}
						
						//sag
						if(!alanlar[i][j+1].durum.equals("duvar"))
						{
							rMatris[i][j][i][j+1]=0;
							
							if(alanlar[i][j+1].durum.equals("hedef"))
							{
								rMatris[i][j][i][j+1]=1000;
							}
						}
						
						//sað alt
						if(!alanlar[i+1][j+1].durum.equals("duvar"))
						{
							rMatris[i][j][i+1][j+1]=0;
							
							if(alanlar[i+1][j+1].durum.equals("hedef"))
							{
								rMatris[i][j][i+1][j+1]=1000;
							}
						}
					}
				}
				
				
				//en alttakiler
				else if(i==49)
				{
					//49,0 ve 49,49 hariç
					if(j!=0&&j!=49)
					{
						
						//üst
						if(!alanlar[i-1][j].durum.equals("duvar"))
						{
							rMatris[i][j][i-1][j]=0;
							
							if(alanlar[i-1][j].durum.equals("hedef"))
							{
								rMatris[i][j][i-1][j]=1000;
							}
						}
						
						//sol üst
						if(!alanlar[i-1][j-1].durum.equals("duvar"))
						{
							rMatris[i][j][i-1][j-1]=0;
							
							if(alanlar[i-1][j-1].durum.equals("hedef"))
							{
								rMatris[i][j][i-1][j-1]=1000;
							}
						}
						
						//sol
						if(!alanlar[i][j-1].durum.equals("duvar"))
						{
							rMatris[i][j][i][j-1]=0;
							
							if(alanlar[i][j-1].durum.equals("hedef"))
							{
								rMatris[i][j][i][j-1]=1000;
							}
						}
						
						//sag üst
						if(!alanlar[i-1][j+1].durum.equals("duvar"))
						{
							rMatris[i][j][i-1][j+1]=0;
							
							if(alanlar[i-1][j+1].durum.equals("hedef"))
							{
								rMatris[i][j][i-1][j+1]=1000;
							}
						}
						
						//sag
						if(!alanlar[i][j+1].durum.equals("duvar"))
						{
							rMatris[i][j][i][j+1]=0;
							
							if(alanlar[i][j+1].durum.equals("hedef"))
							{
								rMatris[i][j][i][j+1]=1000;
							}
						}
						
						
					}
				}
				
				//ortadakiler
				else
				{
					//üst
					if(!alanlar[i-1][j].durum.equals("duvar"))
					{
						rMatris[i][j][i-1][j]=0;
						
						if(alanlar[i-1][j].durum.equals("hedef"))
						{
							rMatris[i][j][i-1][j]=1000;
						}
					}
					
					//sol üst
					if(!alanlar[i-1][j-1].durum.equals("duvar"))
					{
						rMatris[i][j][i-1][j-1]=0;
						
						if(alanlar[i-1][j-1].durum.equals("hedef"))
						{
							rMatris[i][j][i-1][j-1]=1000;
						}
					}
					
					//sol
					if(!alanlar[i][j-1].durum.equals("duvar"))
					{
						rMatris[i][j][i][j-1]=0;
						
						if(alanlar[i][j-1].durum.equals("hedef"))
						{
							rMatris[i][j][i][j-1]=1000;
						}
					}
					
					//sag üst
					if(!alanlar[i-1][j+1].durum.equals("duvar"))
					{
						rMatris[i][j][i-1][j+1]=0;
						
						if(alanlar[i-1][j+1].durum.equals("hedef"))
						{
							rMatris[i][j][i-1][j+1]=1000;
						}
					}
					
					//sag
					if(!alanlar[i][j+1].durum.equals("duvar"))
					{
						rMatris[i][j][i][j+1]=0;
						
						if(alanlar[i][j+1].durum.equals("hedef"))
						{
							rMatris[i][j][i][j+1]=1000;
						}
					}
					
					//sað alt
					if(!alanlar[i+1][j+1].durum.equals("duvar"))
					{
						rMatris[i][j][i+1][j+1]=0;
						
						if(alanlar[i+1][j+1].durum.equals("hedef"))
						{
							rMatris[i][j][i+1][j+1]=1000;
						}
					}
					
					//sol alt
					if(!alanlar[i+1][j-1].durum.equals("duvar"))
					{
						rMatris[i][j][i+1][j-1]=0;
						
						if(alanlar[i+1][j-1].durum.equals("hedef"))
						{
							rMatris[i][j][i+1][j-1]=1000;
						}
					}
					
					//alt
					if(!alanlar[i+1][j].durum.equals("duvar"))
					{
						rMatris[i][j][i+1][j]=0;
						
						if(alanlar[i+1][j].durum.equals("hedef"))
						{
							rMatris[i][j][i+1][j]=1000;
						}
					}
				}
			}
		}
		
		System.out.println("R matrisi hesaplandý");
	}
	
	public void qMatrisBulma()
	{
		qMatris=new Qmatris[50][50];
		
		for(int i=0;i<50;i++)
		{
			for(int j=0;j<50;j++)
			{
				qMatris[i][j]=new Qmatris();
				
				//burala out of bounds olmamasý için hiç girilmeyecek o yüzden duvar durumunu önceden atýyoruz
				
				//en üst
				if(i==0)
				{
					qMatris[i][j].yonler[0]=-1000;
					qMatris[i][j].yonler[1]=-1000;
					qMatris[i][j].yonler[7]=-1000;
				}
				//en alt
				
				else if(i==49)
				{
					qMatris[i][j].yonler[3]=-1000;
					qMatris[i][j].yonler[4]=-1000;
					qMatris[i][j].yonler[5]=-1000;
				}
				
				//en sol
				 if(j==0)
				{
					 qMatris[i][j].yonler[6]=-1000;
					 qMatris[i][j].yonler[7]=-1000;
					 qMatris[i][j].yonler[5]=-1000;
				}
				
				//en sað
				else if(j==49)
				{
					qMatris[i][j].yonler[1]=-1000;
					qMatris[i][j].yonler[2]=-1000;
					qMatris[i][j].yonler[3]=-1000;
				}
			}
		}
		
		//0,0 için
		qMatris[0][0].yonler[0]=-1000;
		qMatris[0][0].yonler[1]=-1000;
		qMatris[0][0].yonler[2]=0;
		qMatris[0][0].yonler[3]=0;
		qMatris[0][0].yonler[4]=0;
		qMatris[0][0].yonler[5]=-1000;
		qMatris[0][0].yonler[6]=-1000;
		qMatris[0][0].yonler[7]=-1000;
		
		//49,0 için
		qMatris[49][0].yonler[0]=0;
		qMatris[49][0].yonler[1]=0;
		qMatris[49][0].yonler[2]=0;
		qMatris[49][0].yonler[3]=-1000;
		qMatris[49][0].yonler[4]=-1000;
		qMatris[49][0].yonler[5]=-1000;
		qMatris[49][0].yonler[6]=-1000;
		qMatris[49][0].yonler[7]=-1000;
		
		//0,49 için
		qMatris[0][49].yonler[0]=-1000;
		qMatris[0][49].yonler[1]=-1000;
		qMatris[0][49].yonler[2]=-1000;
		qMatris[0][49].yonler[3]=-1000;
		qMatris[0][49].yonler[4]=0;
		qMatris[0][49].yonler[5]=0;
		qMatris[0][49].yonler[6]=0;
		qMatris[0][49].yonler[7]=-1000;
		
		//49,49 için
		qMatris[49][49].yonler[0]=-1000;
		qMatris[49][49].yonler[1]=-1000;
		qMatris[49][49].yonler[2]=-1000;
		qMatris[49][49].yonler[3]=-1000;
		qMatris[49][49].yonler[4]=-1000;
		qMatris[49][49].yonler[5]=0;
		qMatris[49][49].yonler[6]=0;
		qMatris[49][49].yonler[7]=0;
		
		
		//iterasyon sayýsý
		
		for(int i=0;i<25000;i++)
		{
			int adimSayisi=qMatrisHesaplama();
			
			if(i<100)
			{
				adimGrafik.add(adimSayisi);
			}
			
		}
		
		System.out.println("Q matris hesaplandý");
		
	}
	
	public int qMatrisHesaplama()
	{
		int mevcutSatir,mevcutSutun,gidilcekSatir=0,gidilcekSutun=0,yon;
		
		mevcutSatir=baslangicSatir;
		mevcutSutun=baslangicSutun;
		
		int adimSayisi=0;
		
		while(true)
		{	
			
			do
			{
				//yon belirle
				yon=(int)(Math.random()*8);
				if(yon==0)
				{
					gidilcekSatir=mevcutSatir-1;
					gidilcekSutun=mevcutSutun;
				}
				
				else if(yon==1)
				{
					gidilcekSatir=mevcutSatir-1;
					gidilcekSutun=mevcutSutun+1;
				}
				
				else if(yon==2)
				{
					gidilcekSatir=mevcutSatir;
					gidilcekSutun=mevcutSutun+1;
				}
				
				else if(yon==3)
				{
					gidilcekSatir=mevcutSatir+1;
					gidilcekSutun=mevcutSutun+1;
				}
				
				else if(yon==4)
				{
					gidilcekSatir=mevcutSatir+1;
					gidilcekSutun=mevcutSutun;
				}

				else if(yon==5)
				{
					gidilcekSatir=mevcutSatir+1;
					gidilcekSutun=mevcutSutun-1;
				}
				
				else if(yon==6)
				{
					gidilcekSatir=mevcutSatir;
					gidilcekSutun=mevcutSutun-1;
				}
				
				else if(yon==7)
				{
					gidilcekSatir=mevcutSatir-1;
					gidilcekSutun=mevcutSutun-1;
				}
				
				adimSayisi++;
				
			}while((gidilcekSatir<0||gidilcekSatir>49||gidilcekSutun<0||gidilcekSutun>49));
			
			
			qMatris[mevcutSatir][mevcutSutun].yonler[yon]=rMatris[mevcutSatir][mevcutSutun][gidilcekSatir][gidilcekSutun]+0.8*maxBulma(gidilcekSatir, gidilcekSutun);
			
			/*if(alanlar[gidilcekSatir][gidilcekSutun].durum.equals("bos"))
			{
				qMatris[mevcutSatir][mevcutSutun].yonler[yon]+=3+alanlar[gidilcekSatir][gidilcekSutun].deger;
			}
			
			else if(alanlar[gidilcekSatir][gidilcekSutun].durum.equals("duvar"))
			{
				qMatris[mevcutSatir][mevcutSutun].yonler[yon]+=-3-alanlar[gidilcekSatir][gidilcekSutun].deger;
			}
			
			else if(alanlar[gidilcekSatir][gidilcekSutun].durum.equals("hedef"))
			{
				qMatris[mevcutSatir][mevcutSutun].yonler[yon]+=5+alanlar[gidilcekSatir][gidilcekSutun].deger;
			}
			*/
			mevcutSatir=gidilcekSatir;
			mevcutSutun=gidilcekSutun;
			
			
			if(mevcutSatir==hedefSatir&&mevcutSutun==hedefSutun)
			{	
				break;
			}
		}
		
		return adimSayisi;
	}
	
	public double maxBulma(int satir,int sutun)
	{
		double max=qMatris[satir][sutun].yonler[0];
		
		for(int i=1;i<8;i++)
		{
			if(qMatris[satir][sutun].yonler[i]>max)
			{
				max=qMatris[satir][sutun].yonler[i];
			}
		}
		
		return max;
	}
	
	public void kontrol()
	{
		int mevcutSatir=baslangicSatir;
		int mevcutSutun=baslangicSutun;
		
		int gidilcekSatir=0,gidilcekSutun=0;
		
		int yon=0;
		
		double[] qTutucu=new double[8];
		int[] yonTutucu=new int[8];
		
		
		
		while(true)
		{
			for(int i=0;i<8;i++)
			{
				qTutucu[i]=qMatris[mevcutSatir][mevcutSutun].yonler[i];
				yonTutucu[i]=i;
			}
			
			for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				{
					if(qTutucu[i]>qTutucu[j])
					{
						double qTemp=qTutucu[i];
						qTutucu[i]=qTutucu[j];
						qTutucu[j]=qTemp;
						
						int yonTemp=yonTutucu[i];
						yonTutucu[i]=yonTutucu[j];
						yonTutucu[j]=yonTemp;
					}
				}
			}
			
			boolean cikilcakmi=false;
			for(int i=0;i<8;i++)
			{
				yon=yonTutucu[i];
				
				if(i==7)
				{
					cikilcakmi=true;
					break;
				}
				
				if(qMatris[mevcutSatir][mevcutSutun].gecildimi[i]==false)
				{
					break;
				}
			}
			
			if(cikilcakmi==true)
			{
				System.out.println("Döngü sonsuz,tekrar matris aç!");
				break;
			}
			if(yon==0)
			{
				gidilcekSatir=mevcutSatir-1;
				gidilcekSutun=mevcutSutun;
			}
			
			else if(yon==1)
			{
				gidilcekSatir=mevcutSatir-1;
				gidilcekSutun=mevcutSutun+1;
			}
			
			else if(yon==2)
			{
				gidilcekSatir=mevcutSatir;
				gidilcekSutun=mevcutSutun+1;
			}
			
			else if(yon==3)
			{
				gidilcekSatir=mevcutSatir+1;
				gidilcekSutun=mevcutSutun+1;
			}
			
			else if(yon==4)
			{
				gidilcekSatir=mevcutSatir+1;
				gidilcekSutun=mevcutSutun;
			}
			
			else if(yon==5)
			{
				gidilcekSatir=mevcutSatir+1;
				gidilcekSutun=mevcutSutun-1;
			}
			
			else if(yon==6)
			{
				gidilcekSatir=mevcutSatir;
				gidilcekSutun=mevcutSutun-1;
			}
			
			else if(yon==7)
			{
				gidilcekSatir=mevcutSatir-1;
				gidilcekSutun=mevcutSutun-1;
			}
			
			qMatris[mevcutSatir][mevcutSutun].gecildimi[yon]=true;
			
			System.out.println("yon=\t"+yon+"\tmevcut=\t"+mevcutSatir+","+mevcutSutun+"\t hedef=\t"+hedefSatir+","+hedefSutun);
			
			for(int i=0;i<8;i++)
			{
				System.out.print(qMatris[mevcutSatir][mevcutSutun].yonler[i]+"\t,");
			}
			
			System.out.print("\n\n");
			
			yolSatirlari.add(mevcutSatir);
			yolSutunlari.add(mevcutSutun);
			
			kazancGrafik.add(qMatris[mevcutSatir][mevcutSutun].yonler[yon]);
			
			mevcutSatir=gidilcekSatir;
			mevcutSutun=gidilcekSutun;
			
			
			
			if(mevcutSatir==hedefSatir&&mevcutSutun==hedefSutun)
			{	
				System.out.println("mevcut=\t"+mevcutSatir+","+mevcutSutun+"\t hedef=\t"+hedefSatir+","+hedefSutun);
				
				yolSatirlari.add(mevcutSatir);
				yolSutunlari.add(mevcutSutun);
				
				kazancGrafik.add(qMatris[mevcutSatir][mevcutSutun].yonler[yon]);
				break;
			}
		}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==zamanlayici)
		{
			if(yolSatirlari.get(yolSayaci)==hedefSatir&&yolSutunlari.get(yolSayaci)==hedefSutun)
			{
				zamanlayici.stop();
				basladimi=false;
				yolSayaci=0;
				
				oynatButonu.setBackground(Color.yellow);
				menuPenceresi.setVisible(true);
			}
			
			else
			{
				int gidilcekSatir=yolSatirlari.get(yolSayaci+1);
				int gidilcekSutun=yolSutunlari.get(yolSayaci+1);
				
				int mevcutSatir=yolSatirlari.get(yolSayaci);
				int mevcutSutun=yolSutunlari.get(yolSayaci);
				
				if(alanlar[mevcutSatir][mevcutSutun].durum.equals("bos"))
				{
					alanlar[mevcutSatir][mevcutSutun].setText(Integer.toString(alanlar[mevcutSatir][mevcutSutun].deger));
					alanlar[mevcutSatir][mevcutSutun].setBackground(Color.yellow);
				}
				
				else if(alanlar[mevcutSatir][mevcutSutun].durum.equals("duvar"))
				{
					alanlar[mevcutSatir][mevcutSutun].setText(Integer.toString(alanlar[mevcutSatir][mevcutSutun].deger));
					alanlar[mevcutSatir][mevcutSutun].setBackground(Color.yellow);
				}
				
				alanlar[gidilcekSatir][gidilcekSutun].setText("");
				alanlar[gidilcekSatir][gidilcekSutun].setBackground(Color.green);
				
				calismaPenceresi.setVisible(true);
				
				yolSayaci++;
				
			
			}
		}
		
		else if(e.getActionCommand().equals("Alaný Yazdýr"))
		{
			try {
				yazdirma();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			yazdirmaButonu.setBackground(Color.green);
			menuPenceresi.setVisible(true);
		}
		
		else if(e.getActionCommand().equals("R Matris Hesabý"))
		{
			rMatrisBulma();
			
			rMatrisButonu.setBackground(Color.green);
			qMatrisButonu.setBackground(Color.yellow);
			menuPenceresi.setVisible(true);
		}
		
		else if(e.getActionCommand().equals("Q Matris Hesabý"))
		{
			qMatrisBulma();
			

			qMatrisButonu.setBackground(Color.green);
			kontrolButonu.setBackground(Color.yellow);
			menuPenceresi.setVisible(true);
			
		}
		
		else if(e.getActionCommand().equals("Yol Kontrolü"))
		{
			kontrol();
			

			kontrolButonu.setBackground(Color.green);
			oynatButonu.setBackground(Color.yellow);
			grafikButonu.setBackground(Color.yellow);
			grafik2Butonu.setBackground(Color.yellow);
			menuPenceresi.setVisible(true);
		}
		
		else if(e.getActionCommand().equals("Hareket Ettir"))
		{
			if(basladimi==false)
			{
				zamanlayici.start();
				basladimi=true;
				
				oynatButonu.setBackground(Color.green);
				menuPenceresi.setVisible(true);
			}
			
			else
			{
				zamanlayici.stop();
				basladimi=false;
				
				oynatButonu.setBackground(Color.yellow);
				menuPenceresi.setVisible(true);
			}
		}
		
		else if(e.getActionCommand().equals("Kazanç Grafik"))
		{	
			Grafik1 grafik1=new Grafik1(kazancGrafik);
			
			grafikButonu.setBackground(Color.green);
			menuPenceresi.setVisible(true);
		}
		
		else if(e.getActionCommand().equals("Adým Grafik"))
		{	
			Grafik2 grafik2=new Grafik2(adimGrafik);
			
			grafik2Butonu.setBackground(Color.green);
			menuPenceresi.setVisible(true);
		}
	}

}
