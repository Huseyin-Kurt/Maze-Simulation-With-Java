import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GirisMenusu extends JPanel implements ActionListener {
	
	JFrame pencere;
	JLabel baslangic,hedef,satir,sutun;
	JLabel arkaPlan;
	ImageIcon arkaPlanResmi=new ImageIcon(new ImageIcon("girisMenusuResim.jpg").getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH));
	JTextField baslangicSatir,baslangicSutun,hedefSatir,hedefSutun;
	JButton tamamButonu;
	int baslangicSatirIndis,baslangicSutunIndis,hedefSatirIndis,hedefSutunIndis;
	
	
	public GirisMenusu(JFrame pencere)
	{
		super();
		setLayout(null);
		
		this.pencere=pencere;
		
		baslangic=new JLabel("Baþlangýç Alaný:");
		baslangic.setBounds(300,200 , 150, 150);
		baslangic.setFont(new Font("Arial", Font.PLAIN, 20));
		baslangic.setForeground(Color.green);
		
		hedef=new JLabel("Hedef Alaný:");
		hedef.setBounds(300, 400, 150, 150);
		hedef.setFont(new Font("Arial", Font.PLAIN, 20));
		hedef.setForeground(Color.green);
		
		satir=new JLabel("Satýr");
		satir.setFont(new Font("Arial", Font.PLAIN, 20));
		satir.setBounds(500,100,100,100);
		satir.setForeground(Color.red);
		
		sutun=new JLabel("Sütun");
		sutun.setFont(new Font("Arial", Font.PLAIN, 20));
		sutun.setBounds(700,100,100,100);
		sutun.setForeground(Color.red);
		
		baslangicSatir=new JTextField();
		baslangicSatir.setBounds(500, 260, 60, 30);
		baslangicSatir.setFont(new Font("Arial", Font.PLAIN, 30));
		
		baslangicSutun=new JTextField();
		baslangicSutun.setBounds(700,260,60,30);
		baslangicSutun.setFont(new Font("Arial", Font.PLAIN, 30));
		
		hedefSatir=new JTextField();
		hedefSatir.setBounds(500, 460, 60, 30);
		hedefSatir.setFont(new Font("Arial", Font.PLAIN, 30));
		
		hedefSutun=new JTextField();
		hedefSutun.setBounds(700,460,60,30);
		hedefSutun.setFont(new Font("Arial", Font.PLAIN, 30));
		
		arkaPlan=new JLabel(arkaPlanResmi);
		arkaPlan.setBounds(0, 0, 1366, 768);
		
		tamamButonu=new JButton("Tamam");
		tamamButonu.setBounds(450,550,300,100);
		tamamButonu.setFont(new Font("Arial", Font.PLAIN, 30));
		tamamButonu.setBackground(Color.yellow);
		
		
		tamamButonu.addActionListener(this);
		
		add(baslangic);
		add(hedef);
		add(satir);
		add(sutun);
		add(baslangicSatir);
		add(baslangicSutun);
		add(hedefSatir);
		add(hedefSutun);
		add(tamamButonu);
		add(arkaPlan);
		
		
		pencere.add(this);
		pencere.setVisible(true);
		
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Tamam"))
		{
			baslangicSatirIndis=Integer.parseInt(baslangicSatir.getText());
			baslangicSutunIndis=Integer.parseInt(baslangicSutun.getText());
			
			hedefSatirIndis=Integer.parseInt(hedefSatir.getText());
			hedefSutunIndis=Integer.parseInt(hedefSutun.getText());
			
			this.setVisible(false);
			pencere.remove(this);
			
			try {
				CalismaMenusu calismaMenusu=new CalismaMenusu(pencere, baslangicSatirIndis, baslangicSutunIndis, hedefSatirIndis, hedefSutunIndis);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}

}
