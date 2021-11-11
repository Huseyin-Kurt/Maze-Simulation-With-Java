
public class Qmatris {
	
	double[] yonler;
	boolean[] gecildimi;
	
	public Qmatris()
	{	
		gecildimi=new boolean[8];
		
		yonler=new double[8];
		
		for(int i=0;i<8;i++)
		{
			yonler[i]=0;
			gecildimi[i]=false;
		}
	}

}
