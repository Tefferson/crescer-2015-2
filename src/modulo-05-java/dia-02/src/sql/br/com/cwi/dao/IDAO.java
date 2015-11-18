package sql.br.com.cwi.dao;

import java.util.List;

public interface IDAO<T> {
	void insert(T t);
	List<T> listAll();
	int update(T t);
	void delete(Long id);
	T load(Long id);
	List<T> find(T t);
}
