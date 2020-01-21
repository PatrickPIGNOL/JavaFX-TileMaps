package Game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Game 
{
	private Map<ETiles, Image> aTiles;
	private List<List<ETiles>> aMap;
	
	private double aMouseX;
	private double aMouseY;
	
	public Game(List<List<ETiles>> pMap)
	{
		this.aMap = pMap;
		this.aTiles = new HashMap<ETiles, Image>();
	}

	public void mMouseMove(MouseEvent e)
	{
		this.mOnMouseMoved(e);
	}
	
	private void mOnMouseMoved(MouseEvent e)
	{
		this.aMouseX = e.getX();
		this.aMouseY = e.getY();
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
				if(vTile == null)
				{
					continue;
				}
				else
				{
					pGraphicsContext.drawImage(vTile, vXIndex * vTile.getWidth(), vYIndex * vTile.getHeight()); 
				}
			}
		}
		
		Image vTile = this.aTiles.get(ETiles.Grass);
		int vXIndex = (int) (this.aMouseX / vTile.getWidth());
		int vYIndex = (int) (this.aMouseY / vTile.getHeight());
		Font vFont = Font.font( "Times New Roman", FontWeight.BOLD, 14 );
		if((vXIndex < this.aMap.size()) && (vYIndex < this.aMap.get(0).size()))
		{
			this.mDrawText(pGraphicsContext, 10.0, 20.0, vFont, "ID: " + this.aMap.get(vYIndex).get(vXIndex).ordinal(), 0.0, Color.GREEN, Color.BLACK);
		}
		else
		{
			this.mDrawText(pGraphicsContext, 10.0, 20.0, vFont, "Out...", 0.0, Color.GREEN, Color.BLACK);
		}
	}
	
	private void mDrawText(GraphicsContext pGraphicsContext, double pX, double pY, Font pFont, String pText, double pLineWidth, Paint pFillColor, Paint pStrokeColor)
	{
		pGraphicsContext.setFill(pFillColor);
		pGraphicsContext.setFont(pFont);
		pGraphicsContext.fillText(pText, pX, pY);
		if(pLineWidth > 0.0)
		{
			pGraphicsContext.setStroke(pStrokeColor);
			pGraphicsContext.setLineWidth(pLineWidth);
		    pGraphicsContext.strokeText(pText, pX, pY);
		}
	}
}
