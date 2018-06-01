import java.awt.*;
import java.util.*;

public class SandLab
{
  //Step 4,6
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int COAL = 2;
  public static final int WATER = 3;
  public static final int LAVA = 4;
  public static final int STONE = 5;
  
  
  //do not add any more fields below
  private int[][] grid;
  private SandDisplay display;
  
  
  /**
   * Constructor for SandLab
   * @param numRows The number of rows to start with
   * @param numCols The number of columns to start with;
   */
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    // Change this value to add more buttons
    //Step 4,6
    names = new String[5];
    // Each value needs a name for the button
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[COAL] = "Coal";
    names[WATER] = "Water";
    names[LAVA] = "Lava";
    
    
    //1. Add code to initialize the data member grid with same dimensions
    grid = new int[numRows][numCols];
    		
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
    //2. Assign the values associated with the parameters to the grid
   grid[row][col] = tool;
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
      //Step 3
   //Hint - use a nested for loop
    for(int row = 0; row < grid.length; row++)
    {
    	for(int col = 0; col < grid[0].length; col++)
    	{
    		if(grid[row][col] == EMPTY)
    		{
    			display.setColor(row, col, Color.BLACK);
    		}
    		else if(grid[row][col] == METAL)
    		{
    			display.setColor(row, col, Color.LIGHT_GRAY);
    		}
    		else if(grid[row][col] == COAL)
    		{
    			display.setColor(row, col, Color.DARK_GRAY);
    		}
    		else if(grid[row][col] == WATER)
    		{
    			display.setColor(row, col, Color.BLUE);
    		}
    		else if(grid[row][col] == LAVA)
    		{
    			display.setColor(row, col, new Color(234,92,15));
    		}
    		else if(grid[row][col] == STONE)
    		{
    			display.setColor(row, col, new Color(138,128,124));
    		}
    		
    	}
    }
  }

  //Step 5,7
  //called repeatedly.
  //causes one random particle in grid to maybe do something.
  public void step()
  {
    //Remember, you need to access both row and column to specify a spot in the array
    //The scalar refers to how big the value could be
    //int someRandom = (int) (Math.random() * scalar)
    //remember that you need to watch for the edges of the array
    int randomRow = (int) (Math.random() * (grid.length));
    int randomCol = (int) (Math.random() * (grid[0].length));
    if (grid[randomRow][randomCol] == WATER)
    
	{
		int randomDirection = (int) (Math.random() * 3);
		
		if (randomDirection == 0 && randomCol + 1 != grid[0].length)	//Right
		{
			if (grid[randomRow][randomCol + 1] == EMPTY)
			{
				grid[randomRow][randomCol + 1] = WATER;
				grid[randomRow][randomCol] = EMPTY;
			}
			else if(grid[randomRow][randomCol + 1] == LAVA)
			{
				grid[randomRow][randomCol] = STONE;
			}

		}
		else if (randomDirection == 1 && randomCol - 1 != -1)	//Left
		{
			if (grid[randomRow][randomCol - 1] == EMPTY)
			{
				grid[randomRow][randomCol - 1 ] = WATER;
				grid[randomRow][randomCol] = EMPTY;
			}
			else if(grid[randomRow][randomCol - 1] == LAVA)
			{
				grid[randomRow][randomCol] = STONE;
			}

		}
		else if (randomDirection == 2 && randomRow != grid.length - 1)	//Down
		{
			if (grid[randomRow + 1][randomCol] == EMPTY)
			{
				grid[randomRow + 1][randomCol] = WATER;
				grid[randomRow][randomCol] = EMPTY;
			}
			else if(grid[randomRow + 1][randomCol] == LAVA)
			{
				grid[randomRow][randomCol] = STONE;
			}

		}
    }
    if (grid[randomRow][randomCol] == LAVA)
        
	{
		int randomDirection = (int) (Math.random() * 3);
		
		if (randomDirection == 0 && randomCol + 1 != grid[0].length)	//Right
		{
			if (grid[randomRow][randomCol + 1] == EMPTY)
			{
				grid[randomRow][randomCol + 1] = LAVA;
				grid[randomRow][randomCol] = EMPTY;
			}
			
			

		}
		else if (randomDirection == 1 && randomCol - 1 != -1)	//Left
		{
			if (grid[randomRow][randomCol - 1] == EMPTY)
			{
				grid[randomRow][randomCol - 1 ] = LAVA;
				grid[randomRow][randomCol] = EMPTY;
			}
			

		}
		else if (randomDirection == 2 && randomRow != grid.length - 1)	//Down
		{
			if (grid[randomRow + 1][randomCol] == EMPTY)
			{
				grid[randomRow + 1][randomCol] = LAVA;
				grid[randomRow][randomCol] = EMPTY;
			}
			else if (grid[randomRow + 1][randomCol] == WATER)
			{
				grid[randomRow + 1][randomCol] = LAVA;
				grid[randomRow][randomCol] = WATER;
			}

			
			

		}
    }
    if (grid[randomRow][randomCol] == STONE)
        
	{
		int randomDirection = (int) (Math.random() * 3);
		
		if (randomDirection == 2 && randomRow != grid.length - 1)	//Down
		{
			if (grid[randomRow + 1][randomCol] == EMPTY)
			{
				grid[randomRow + 1][randomCol] = STONE;
				grid[randomRow][randomCol] = EMPTY;
			}
			else if(grid[randomRow + 1][randomCol] == LAVA)
			{
				grid[randomRow + 1][randomCol] = STONE;
				grid[randomRow][randomCol] = LAVA;
			}
			else if(grid[randomRow + 1][randomCol] == WATER)
			{
				grid[randomRow + 1][randomCol] = STONE;
				grid[randomRow][randomCol] = WATER;
			}
			
		}
	}
		
}
  
  //do not modify this method!
  public void run()
  {
    while (true) // infinite loop
    {
      for (int i = 0; i < display.getSpeed(); i++)
      {
        step();
      }
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
      {
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
      }
    }
  }
}
