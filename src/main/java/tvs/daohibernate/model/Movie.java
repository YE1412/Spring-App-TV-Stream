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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tvs.web.model.FormDataWithFile;
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
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
@ToString(of= {"name", })
public class Movie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter
	private int id;
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST },
		      fetch = FetchType.LAZY, mappedBy = "owner")
	private Set<Certification> certifs = new HashSet<Certification>();;
	@Basic(optional = false)
	@Column(name = "title_short", length = 55,
      nullable = false, unique = false)
	@NonNull
	@Getter @Setter
	private String name;
	@Version()
	@Transient
	private long version = 0;
	@Getter @Setter
	private String filePath;
	@Transient
	public static long updateCounter = 0;
	
	@PreUpdate
   public void beforeUpdate() {
//      System.err.println("PreUpdate of " + this);
   }

   @PostUpdate
   public void afterUpdate() {
//      System.err.println("PostUpdate of " + this);
      updateCounter++;
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
