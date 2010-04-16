package org.lexgrid.loader.database.key;

import org.LexGrid.relations.AssociationPredicate;
import org.lexevs.dao.database.access.DaoManager;
import org.lexevs.dao.database.access.association.AssociationDao;
import org.lexevs.dao.database.access.codingscheme.CodingSchemeDao;
import org.lexevs.dao.database.service.DatabaseServiceManager;
import org.lexevs.dao.database.service.daocallback.DaoCallbackService.DaoCallback;

public class AssociationPredicateCreatingKeyResolver implements AssociationPredicateKeyResolver{

	private DatabaseServiceManager databaseServiceManager;
	
	public String resolveKey(
			final String codingSchemeUri, 
			final String version, 
			final String relationContainerName,
			final String associationName) {
		return databaseServiceManager.getDaoCallbackService().executeInDaoLayer(new DaoCallback<String>(){

			public String execute(DaoManager daoManager) {
				
				CodingSchemeDao codingSchemedao = daoManager.getCodingSchemeDao(codingSchemeUri, version);
				AssociationDao associationDao = daoManager.getAssociationDao(codingSchemeUri, version);
				
				String codingSchemeId = 
					codingSchemedao.
						getCodingSchemeUIdByUriAndVersion(
								codingSchemeUri, version);
				
				String relationId = associationDao.getRelationsId(codingSchemeId, relationContainerName);
				
				String associationPredicateId = associationDao.
							getAssociationPredicateUid(codingSchemeId, associationName);
				
				if(associationPredicateId == null){
					return associationDao.
						insertAssociationPredicate(
								codingSchemeId, 
								relationId, 
								buildDefaultAssociationPredicate(associationName),
								false);
				} else {
					return associationPredicateId;
				}
			}	
		});
	}
	
	protected AssociationPredicate buildDefaultAssociationPredicate(String name){
		AssociationPredicate predicate = new AssociationPredicate();
		predicate.setAssociationName(name);
		return predicate;
	}

	public void setDatabaseServiceManager(DatabaseServiceManager databaseServiceManager) {
		this.databaseServiceManager = databaseServiceManager;
	}

	public DatabaseServiceManager getDatabaseServiceManager() {
		return databaseServiceManager;
	}

}
