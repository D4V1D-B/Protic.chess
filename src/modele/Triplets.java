package modele;

public class Triplets
{
	private Integer state;
	private Pieces attaquant;
	private Pieces defendant;
	public Triplets(Integer state, Pieces attaquant, Pieces defendant)
	{
		super();
		setState(state);
		 setAttaquant(attaquant);
		 setDefendant(defendant);
	}
	public Integer getState()
	{
		return state;
	}
	public void setState(Integer state)
	{
		this.state = state;
	}
	public Pieces getAttaquant()
	{
		return attaquant;
	}
	public void setAttaquant(Pieces attaquant)
	{
		this.attaquant = attaquant;
	}
	public Pieces getDefendant()
	{
		return defendant;
	}
	public void setDefendant(Pieces defendant)
	{
		this.defendant = defendant;
	}
}
