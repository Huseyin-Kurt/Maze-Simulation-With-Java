import javax.swing.JFrame;

public class AnaFonksiyon {

	public static void main(String[] args) {
		
		JFrame pencere=new JFrame();
		pencere.setSize(1366, 768);
		pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pencere.setLocationRelativeTo(null);
		pencere.setVisible(true);
		
		
		GirisMenusu girisMenusu=new GirisMenusu(pencere);

	}

}
