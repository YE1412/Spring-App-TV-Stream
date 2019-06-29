package tvs.interf.enums;

import org.springframework.stereotype.Service;

@Service
public interface RegistrableQuery {
	/**
	 * @return la requête JPQL.
	 */
	public String getQuery();

	/**
	 * @return l'identifiant de la requête JPQL.
	 */
	public String getIdentifier();
}
