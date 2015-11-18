package sql.br.com.cwi.dao;

import java.util.List;

public interface IDAO<T> {
	void insert(T t);
	List<T> listAll();
}
