
import Base.Cell;

public aspect SmallSize {

	pointcut Small() : execution(Cell[][] Base.Size.getGamefield());
	
	Cell[][] around() : Small() {
		return (new Cell[9][9]);
	}
}
