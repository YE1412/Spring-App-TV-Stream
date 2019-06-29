package tvs.daohibernate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity(name = "user")
@Component()
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
@ToString(of= {"nom","birthDay"})
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NonNull
	@Id
	@Column(unique = true, nullable = false)
	@Getter @Setter
	private String nom;
	@NonNull
	@Getter @Setter
	private String pass;
	@NonNull
	@Getter @Setter
	private Date birthDay;

}
