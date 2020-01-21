package Game;

public enum ETiles 
{
	Null(null),
	Grass("grassCenter.png"),
	Lava("liquidLava.png"),
	Water("liquidWater.png"),
	Snow("snowCenter.png"),
	Stone("stoneCenter.png");
	
	private String aFileName;
	
	ETiles(String pFileName)
	{
		this.aFileName = pFileName;
	}
	
	public String mFileName()
	{
		return this.aFileName;
	}
}
