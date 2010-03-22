/*
 * Copyright: (c) 2004-2009 Mayo Foundation for Medical Education and 
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
package org.lexevs.dao.database.service.codingscheme;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.LexGrid.codingSchemes.CodingScheme;
import org.LexGrid.commonTypes.Property;
import org.LexGrid.commonTypes.PropertyQualifier;
import org.LexGrid.concepts.Entities;
import org.LexGrid.concepts.Entity;
import org.LexGrid.concepts.PropertyLink;
import org.LexGrid.relations.AssociationPredicate;
import org.LexGrid.relations.AssociationQualification;
import org.LexGrid.relations.AssociationSource;
import org.LexGrid.relations.AssociationTarget;
import org.LexGrid.relations.Relations;
import org.junit.Test;
import org.lexevs.dao.database.service.DatabaseServiceManager;
import org.lexevs.dao.database.service.error.DatabaseError;
import org.lexevs.dao.database.service.error.ErrorCallbackListener;
import org.lexevs.dao.database.utility.DaoUtility;
import org.lexevs.dao.test.LexEvsDbUnitTestBase;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * The Class VersionableEventCodingSchemeServiceTest.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public class VersionableEventCodingSchemeServiceTest extends LexEvsDbUnitTestBase {

	@Resource
	private DatabaseServiceManager databaseServiceManager;
	
	/** The service. */
	@Resource
	private VersionableEventCodingSchemeService service;
	
	/**
	 * Insert coding scheme.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void insertCodingScheme() throws Exception{
		
		CodingScheme scheme = new CodingScheme();
		scheme.setApproxNumConcepts(111l);
		scheme.setCodingSchemeName("testName");
		scheme.setCodingSchemeURI("uri");
		scheme.setRepresentsVersion("v1");
		
		service.insertCodingScheme(scheme);
	}
	
	/**
	 * Insert coding scheme with local name.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void insertCodingSchemeWithLocalName() throws Exception{

		CodingScheme scheme = new CodingScheme();
		scheme.setApproxNumConcepts(111l);
		scheme.setCodingSchemeName("testName");
		scheme.setCodingSchemeURI("uri");
		scheme.setRepresentsVersion("v1");
		
		scheme.addLocalName("localName");
		
		service.insertCodingScheme(scheme);
	}
	
	/**
	 * Test insert coding scheme with entity and property.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testInsertCodingSchemeWithEntityAndProperty() throws Exception{

		CodingScheme scheme = new CodingScheme();
		scheme.setApproxNumConcepts(111l);
		scheme.setCodingSchemeName("testName");
		scheme.setCodingSchemeURI("uri");
		scheme.setRepresentsVersion("v1");
		
		scheme.setEntities(new Entities());
		
		Entity entity = new Entity();
		entity.setEntityCode("code");
		entity.setEntityCodeNamespace("ns");
		
		Property prop = new Property();
		prop.setPropertyName("name");
		prop.setPropertyId("id");
		prop.setValue(DaoUtility.createText("value"));
		
		entity.addProperty(prop);
		
		scheme.getEntities().addEntity(entity);
		
		service.insertCodingScheme(scheme);
		
		JdbcTemplate template = new JdbcTemplate(this.getDataSource());
		assertEquals(1, template.queryForInt("Select count(*) from codingScheme"));
		assertEquals(1, template.queryForInt("Select count(*) from entity"));
		assertEquals(1, template.queryForInt("Select count(*) from property"));
			
	}
	
	/**
	 * Test insert coding scheme with everything.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testInsertCodingSchemeWithEverything() throws Exception{
		CodingScheme scheme = new CodingScheme();
		scheme.setApproxNumConcepts(111l);
		scheme.setCodingSchemeName("testName");
		scheme.setCodingSchemeURI("uri");
		scheme.setRepresentsVersion("v1");
		
		scheme.setEntities(new Entities());
		
		Entity entity = new Entity();
		entity.setEntityCode("code");
		entity.setEntityCodeNamespace("ns");
		
		Entity entity2 = new Entity();
		entity2.setEntityCode("code2");
		entity2.setEntityCodeNamespace("ns");
		
		Property prop = new Property();
		prop.setPropertyName("name");
		prop.setPropertyId("id");
		prop.setValue(DaoUtility.createText("value"));
		
		Property prop2 = new Property();
		prop2.setPropertyName("name2");
		prop2.setPropertyId("id2");
		prop2.setValue(DaoUtility.createText("value2"));
		
		PropertyQualifier qual = new PropertyQualifier();
		qual.setPropertyQualifierName("pname");
		qual.setPropertyQualifierType("qualType");
		qual.setValue(DaoUtility.createText("test content"));
		prop.addPropertyQualifier(qual);
	
		entity.addProperty(prop);
		entity.addProperty(prop2);
		
		PropertyLink link = new PropertyLink();
		link.setSourceProperty("id");
		link.setTargetProperty("id2");
		link.setPropertyLink("prop-link");
		
		Relations rel = new Relations();
		rel.setContainerName("rel-name");
		
		AssociationPredicate pred = new AssociationPredicate();
		pred.setAssociationName("assoc-name");
		
		AssociationSource source = new AssociationSource();
		source.setSourceEntityCode("code");
		source.setSourceEntityCodeNamespace("ns");
		
		AssociationTarget target = new AssociationTarget();
		target.setTargetEntityCode("code2");
		target.setTargetEntityCodeNamespace("ns");
		source.addTarget(target);
		
		AssociationQualification assocQual = new AssociationQualification();
		assocQual.setAssociationQualifier("assoc-qual");
		assocQual.setQualifierText(DaoUtility.createText("qual-text"));
		
		target.addAssociationQualification(assocQual);
		
		entity.addPropertyLink(link);
		
		scheme.getEntities().addEntity(entity);
		
		scheme.addRelations(rel);
		rel.addAssociationPredicate(pred);
		pred.addSource(source);
		
		service.insertCodingScheme(scheme);
		
		JdbcTemplate template = new JdbcTemplate(this.getDataSource());
		assertEquals(1, template.queryForInt("Select count(*) from codingScheme"));
		assertEquals(1, template.queryForInt("Select count(*) from entity"));
		assertEquals(2, template.queryForInt("Select count(*) from property"));
		assertEquals(1, template.queryForInt("Select count(*) from propertymultiattrib"));
		assertEquals(1, template.queryForInt("Select count(*) from propertylinks"));
		assertEquals(1, template.queryForInt("Select count(*) from entityassnstoentity"));
		assertEquals(1, template.queryForInt("Select count(*) from associationpredicate"));
		assertEquals(1, template.queryForInt("Select count(*) from relation"));
		
		
	}
	
	/**
	 * Test insert coding scheme with everything.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testErrorCallbackCodingSchemeService() throws Exception{
		CodingScheme scheme = new CodingScheme();
		scheme.setApproxNumConcepts(111l);
		scheme.setCodingSchemeName("testName");
		scheme.setCodingSchemeURI("uri");
		
		CachingCallback callback = new CachingCallback();
		
		CodingSchemeService service = databaseServiceManager.getCodingSchemeService(callback);
		
		service.insertCodingScheme(scheme);
		
		List<DatabaseError> errors = callback.errors;
		
		assertEquals(1, errors.size());	
		
		assertEquals(CodingSchemeService.INSERT_CODINGSCHEME_ERROR, errors.get(0).getErrorCode());
	}
	
	private class CachingCallback implements ErrorCallbackListener {

		List<DatabaseError> errors = new ArrayList<DatabaseError>();
		
		@Override
		public void onDatabaseError(DatabaseError databaseError) {
			errors.add(databaseError);
		}
	}
}
