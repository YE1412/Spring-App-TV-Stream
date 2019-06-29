package tvs.interf.enums;

import org.springframework.stereotype.Service;

@Service
public interface RegistrableQuery {
	/**
	 * @return la requ�te JPQL.
	 */
	public String getQuery();

	/**
	 * @return l'identifiant de la requ�te JPQL.
	 */
	public String getIdentifier();
}
