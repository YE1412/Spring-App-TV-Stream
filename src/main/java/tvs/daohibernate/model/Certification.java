package tvs.daohibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
@Entity(name="certification")
@NamedQuery(
        name="findMovieByCertif",
        query = "SELECT DISTINCT m FROM certification c, IN(c.owner) m WHERE c.name LIKE :name"
)
public class Certification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id()
	private int id;
	@Column(length = 150, nullable = false)
	private String name;
	@Column(length = 5, nullable = false)
	private long annee;
	@ManyToOne(optional = true)
	@JoinColumn(name = "owner")
	private Movie owner;
	
	public Certification() {
		super();
	}
	public Certification(int id, String name, long annee) {
		super();
		this.id = id;
		this.name = name;
		this.annee = annee;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getAnnee() {
		return annee;
	}
	public void setAnnee(long annee) {
		this.annee = annee;
	}
	public void setOwner(Movie m) {
		this.owner = m;
	}
	public Movie getOwner(Movie m) {
		return this.owner;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Certificetion(name=" + getName() + ",annee=" + getAnnee() + ")";
	}
	
}
