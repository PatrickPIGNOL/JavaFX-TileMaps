package Game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Game 
{
	private Map<ETiles, Image> aTiles;
	private List<List<ETiles>> aMap;
	
	public Game(List<List<ETiles>> pMap)
	{
		this.aMap = pMap;
		this.aTiles = new HashMap<ETiles, Image>();
	}
	
	public void mLoad()
	{
		System.out.println("Game:Chargement des textures...");
		for(ETiles vTile : ETiles.values())
		{
			if(vTile.mFileName() == null)
			{
				this.aTiles.put(vTile, null);
			}
			else
			{
				this.aTiles.put(vTile, new Image(vTile.mFileName()));
			}
		}
		System.out.println("Game:Chargement des textures terminé...");
	}
	
	public void mDraw(GraphicsContext pGraphicsContext)
	{
		this.mOnDraw(pGraphicsContext);
	}
	
	private void mOnDraw(GraphicsContext pGraphicsContext)
	{
		for(int vYIndex = 0; vYIndex < this.aMap.get(0).size(); vYIndex++)
		{
			for(int vXIndex = 0; vXIndex < this.aMap.size(); vXIndex++)
			{
				Image vTile = this.aTiles.get(this.aMap.get(vYIndex).get(vXIndex));
				if(vTile != null)
				{
					pGraphicsContext.drawImage(vTile, vXIndex * vTile.getWidth(), vYIndex * vTile.getHeight()); 
				}
			}
		}
	}
}
