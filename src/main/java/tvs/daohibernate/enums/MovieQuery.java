package tvs.daohibernate.enums;

import tvs.interf.enums.RegistrableQuery;

public enum MovieQuery implements RegistrableQuery{
	/**
	 * retourne les film en fonction de leur titres. 
	 * Argument JPQL attendu : aucun.	
	 */
	FIND_BY_NAME_ASC("SELECT m FROM movie m ORDER BY m.name ASC");
	
	/**
	 * String JPQL de la requête
	 */
	final String query;
	

	/**
	 * constructeur pour chaque valeur de l'enum.
	 * 
	 * @param returnedClass
	 * @param query
	 */
	private MovieQuery(String query)
	{
		this.query = query;
	}
	@Override
	public String getQuery() {
		// TODO Auto-generated method stub
		return this.query;
	}

	@Override
	public String getIdentifier() {
		// TODO Auto-generated method stub
		return String.format("%s_%s", this.getClass(), this.name());
	}

}
