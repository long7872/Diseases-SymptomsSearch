package DataAccessObject;

import java.util.ArrayList;

public interface DAOInterface<T> {
	
	public int insert(T t);
	public int update(T t);
	public int delete(T t);
	public ArrayList<T> selectAll();
	public T selectById(T t);
	public T selectByName(T t);
	public ArrayList<T> selectByCondition(String condition);

}
