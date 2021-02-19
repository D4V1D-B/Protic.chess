package Modele;

public class Plateau {

	private Object[][] plateau;
	
	public Plateau(Object[][] newPlateau)
	{
		if(isPlateauValide(newPlateau))
		this.plateau = newPlateau;
	}
	
	private boolean isPlateauValide(Object[][] plateauVerify)
	{
		boolean verification = true;
		for(int i = 0; i<plateauVerify.length;i++)
		{
			for(int j = 0; j<plateau[i].length;j++)
			{
				verification= i < 8|| j < 8;
			}
		}
		return verification;
	}
	
	public Object[][] getPlateau()
	{
		return plateau;
	}
	
	public void setPlateau(Object[][] newPlateau)
	{
		plateau = newPlateau;
	}
}
