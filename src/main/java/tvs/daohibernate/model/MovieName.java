package tvs.daohibernate.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity(name="movie_name")
@Table(name = "movie_name",
uniqueConstraints = {
   @UniqueConstraint(columnNames = {
      "id"
   })
})
@NamedQuery(
        name="findAllMoviesNames",
        query="SELECT new tvs.daohibernate.model.MovieName(m.name, m.id) FROM movie m"
)
public class MovieName implements Serializable{
	
	public MovieName() {
		super();
	}
	public MovieName(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "title_short", length = 55,
      nullable = false, unique = false)
	private String name;
	@Id
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "MovieName [id=" + id + ", name=" + name + "]";
	}
	
}
