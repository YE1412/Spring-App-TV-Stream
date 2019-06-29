package tvs.daohibernate.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
@Entity(name="movie")
@Table(name = "movie",
	uniqueConstraints = {
	   @UniqueConstraint(columnNames = {
	      "id"
	   })
	})
@NamedQuery(
        name="findAllMoviesOrderAsc",
        query="SELECT m FROM movie m ORDER BY m.name ASC"
)
public class Movie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST },
		      fetch = FetchType.LAZY, mappedBy = "owner")
	private Set<Certification> certifs = new HashSet<Certification>();;
	@Basic(optional = false)
	@Column(name = "title_short", length = 55,
      nullable = false, unique = false)
	private String name;
	@Version()
	@Transient
	private long version = 0;
	@Transient
	public static long updateCounter = 0;
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
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	
	@PreUpdate
   public void beforeUpdate() {
      System.err.println("PreUpdate of " + this);
   }

   @PostUpdate
   public void afterUpdate() {
      System.err.println("PostUpdate of " + this);
      updateCounter++;
   }
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Movie(id=" + getId() + ",title=" + getName() + ",)";
	}
	
	public Set<Certification> getCertifs() {
		return certifs;
	}
	public void setCertifs(Set<Certification> certifs) {
		this.certifs = certifs;
	}
	public void addCertifs(Certification c) {
      if (c == null) {
         certifs = new HashSet<Certification>();
      }
      certifs.add(c);
      c.setOwner(this);
   }
	
}
