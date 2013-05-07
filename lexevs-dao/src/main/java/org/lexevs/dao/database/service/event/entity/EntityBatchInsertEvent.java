/*
 * Copyright: (c) 2004-2010 Mayo Foundation for Medical Education and 
 * Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
 * triple-shield Mayo logo are trademarks and service marks of MFMER.
 *
 * Except as contained in the copyright notice above, or as used to identify 
 * MFMER as the author of this software, the trade names, trademarks, service
 * marks, or product names of the copyright holder shall not be used in
 * advertising, promotion or otherwise in connection with this software without
 * prior written authorization of the copyright holder.
 * 
 * Licensed under the Eclipse Public License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * 
 * 		http://www.eclipse.org/legal/epl-v10.html
 * 
 */
package org.lexevs.dao.database.service.event.entity;

import java.util.List;

import org.LexGrid.concepts.Entity;

/**
 * The Class EntityBatchInsertEvent.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public class EntityBatchInsertEvent {
	
	/** The coding scheme uri. */
	private String codingSchemeUri;
	
	/** The version. */
	private String version;
	
	/** The entities. */
	private List<? extends Entity> entities;

	/**
	 * Instantiates a new entity batch insert event.
	 * 
	 * @param codingSchemeUri the coding scheme uri
	 * @param version the version
	 * @param entities the entities
	 */
	public EntityBatchInsertEvent(String codingSchemeUri, String version,
			List<? extends Entity> entities) {
		super();
		this.codingSchemeUri = codingSchemeUri;
		this.version = version;
		this.entities = entities;
	}
	
	/**
	 * Gets the coding scheme uri.
	 * 
	 * @return the coding scheme uri
	 */
	public String getCodingSchemeUri() {
		return codingSchemeUri;
	}
	
	/**
	 * Sets the coding scheme uri.
	 * 
	 * @param codingSchemeUri the new coding scheme uri
	 */
	public void setCodingSchemeUri(String codingSchemeUri) {
		this.codingSchemeUri = codingSchemeUri;
	}
	
	/**
	 * Gets the version.
	 * 
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * Sets the version.
	 * 
	 * @param version the new version
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * Gets the entities.
	 * 
	 * @return the entities
	 */
	public List<? extends Entity> getEntities() {
		return entities;
	}
	
	/**
	 * Sets the entities.
	 * 
	 * @param entities the new entities
	 */
	public void setEntities(List<? extends Entity> entities) {
		this.entities = entities;
	}
}