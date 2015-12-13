public class GameBoard implements Comparable<GameBoard>
{
	//In this board the empty spot to should be represented as a 9
	private int[][] board;
	public int moves;
	public int priority;
	
	public GameBoard(int[][] blocks)
	{
		if(blocks.length == 0 || blocks[0].length == 0)
		{
			throw new IndexOutOfBoundsException();
		}
		if(blocks.length != blocks[0].length)
		{
			throw new IndexOutOfBoundsException();
		}
		board = blocks;
		priority = 0;
		moves = 0;
	}
	
	public int[][] board()
	{
		return arrayCopy(board);
	}
	
	public int size()
	{
		return 8;
	}
	
	/**
	public void setMoves(int i)
	{
		moves = i;
	}
	
	public void setPriority(int p)
	{
		prioirty = p;
	}
	*/
	
	public int hamming()
	{
		int thingsOutOfPlace = 0;
		for(int i = 0; i < 9 ; i++)
		{
			if(board[i/3][i%3] == 9)
			{
				
			}
			else
			{
				if(board[i/3][i%3] != i+1)
				{
					thingsOutOfPlace++;
				}
			}
				
		}
		return thingsOutOfPlace;
	}
	
	public int manhattan()
	{
		int manhattan = 0;
		int spots = 0;
		for(int num = 1; num <=8; num++)
		{
			for(int i = 0; i < 9 ; i++)
			{
				if(board[i/3][i%3] == 9)
				{
				
				}
				else
				{
					if(board[i/3][i%3] == num)
					{
						spots = Math.abs((i/3) - ((num-1)/3)) + Math.abs((i%3) - ((num-1)%3));
					}
				}
			}
			manhattan +=spots;
		}
		return manhattan;
	}
	
	public boolean isGoal()
	{
		if (hamming() == 0)
		{
			return true;
		}
		return false;
	}
	
	public boolean isSolvable()
	{
		int inversions = 0;
		for(int i = 0; i < 9; i++)
		{
			int item = board[i/3][i%3];
			for(int j = i+1; j< 9; j++)
			{
				int comparor = board[j/3][j%3];
				if(comparor == 9 || item == 9)
				{
				
				}
				else
				{
					if(comparor < item)
					{
						inversions++;
					}
				}
			}
		}
		if(inversions % 2 == 0)
		{
			return true;
		}
		return false;
	}
	
	public boolean equals(Object y)
	{
		if(this.toString().equals(y.toString()))
		{
			return true;
		}
		return false;
	}
	
	public GameBoard move(int r1, int c1, int r2, int c2)
	{
		GameBoard b = new GameBoard(arrayCopy(board));
		int hold = b.board[r1][c1];
		b.board[r1][c1] = b.board[r2][c2];
		b.board[r2][c2] = hold;
		b.moves = (moves);
		b.priority = priority;
		return b;
	}
	
	//@SuppressWarnings("unchecked")
	public Iterable<GameBoard> neighbors()
	{
		int r = 0;
		int c = 0;
		for(int i = 0; i < 9 ; i++)
		{
			if(board[i/3][i%3] == 9)
			{
				r = i/3;
				c = i%3;
			}
		}
		Vector<GameBoard> v = new Vector<GameBoard>();
		//System.out.println("row:" + r + " col:" + c);
		if(r - 1 >-1 && r - 1 < 3)
		{
			v.add(move(r,c,r-1,c));
			//System.out.println(move(r,c,r-1,c));
		}
		if(r + 1 >-1 && r + 1 < 3)
		{
			v.add(move(r,c,r+1,c));
			//System.out.println(move(r,c,r+1,c));
		}
		if(c - 1 >-1 && c - 1 < 3)
		{
			v.add(move(r,c,r,c-1));
			//System.out.println(move(r,c,r,c-1));
		}
		if(c + 1 >-1 && c + 1 < 3)
		{
			v.add(move(r,c,r,c+1));
			//System.out.println(move(r,c,r,c+1));

		}
		return v;
	}
	
	public static int[][] arrayCopy(int[][] old)
	{
		int[][] copy = new int[old.length][old[0].length];
		for(int r = 0; r < old.length; r++)
		{
			for(int c = 0; c <old[0].length; c++)
			{
				copy[r][c] = old[r][c];
			}
		}
		return copy;
	}
	
	public String toString()
	{
		String s = "";
		for(int[] r: board)
		{
			for(int c: r)
			{
				if(c != 9)
				{
					s+= c + " ";
				}
				else
				{
					s+= "  ";
				}
			}
			s+= "\n";
		}
		return s;
	}
	
	public int compareTo(GameBoard other)
	{
		return priority - other.priority;
	}
}
