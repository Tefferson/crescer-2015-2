package sql.br.com.cwi.model;

public abstract class BaseModel {
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
