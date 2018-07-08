
import Base.Cell;

public aspect BigSize {
	
	pointcut Big() : execution(Cell[][] Base.Size.getGamefield());
	
	Cell[][] around() : Big() { 
		return (new Cell[30][16]);
	}
}
