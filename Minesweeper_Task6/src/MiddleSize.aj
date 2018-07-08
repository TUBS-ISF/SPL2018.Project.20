
import Base.Cell;

public aspect MiddleSize {
	
	pointcut Middle() : execution(Cell[][] Base.Size.getGamefield());
	
	Cell[][] around() : Middle() { 
		return (new Cell[16][16]);
	}
}
