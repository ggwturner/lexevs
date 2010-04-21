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
package org.LexGrid.valuedomain.impl;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.commonTypes.Property;
import org.LexGrid.naming.SupportedAssociation;
import org.LexGrid.naming.SupportedCodingScheme;
import org.LexGrid.naming.SupportedHierarchy;
import org.LexGrid.naming.SupportedNamespace;
import org.LexGrid.valueSets.PickListDefinition;
import org.LexGrid.valueSets.PickListEntry;
import org.LexGrid.valueSets.PickListEntryExclusion;
import org.LexGrid.valueSets.PickListEntryNode;
import org.junit.Test;
import org.lexgrid.valuesets.LexEVSPickListDefinitionServices;
import org.lexgrid.valuesets.impl.LexEVSPickListDefinitionServicesImpl;

/**
 * JUnit for LexEVSPickListServices.
 * 
 * @author <A HREF="mailto:dwarkanath.sridhar@mayo.edu">Sridhar Dwarkanath</A>
 */
public class LexEVSPickListServicesImplTest extends TestCase{
	
	private LexEVSPickListDefinitionServices pls_;
	
	/**
	 * Test method for {@link org.lexgrid.valuedomain.impl.LexEVSPickListServicesImpl#getPickListDefinitionById(java.lang.String)}.
	 * @throws LBException 
	 */
//	@Test
//	public void testGetPickListDefinitionById() throws LBException {
//		PickListDefinition pickList = getPickListService().getPickListDefinitionById("SRITEST:AUTO:DomesticAutoMakers");
//		
//		assertTrue(pickList.getDefaultEntityCodeNamespace().equals("Automobiles"));
//		assertTrue(pickList.getDefaultLanguage().equals("en"));
//		assertTrue(pickList.getEntityDescription().equals("DomesticAutoMakers"));
//		
//		Mappings maps = pickList.getMappings();
//		SupportedCodingScheme scs = (SupportedCodingScheme) maps.getSupportedCodingScheme()[0];
//		assertTrue(scs.getLocalId().equals("Automobiles"));
//		assertTrue(scs.getUri().equals("urn:oid:11.11.0.1"));
//		
//		List<SupportedDataType> sdts = maps.getSupportedDataTypeAsReference();
//		assertTrue(sdts.size() == 2);
//		for (SupportedDataType sdt : sdts)
//		{
//			String localId = sdt.getLocalId();
//			assertTrue(localId.equals("texthtml") || localId.equals("textplain"));
//		}
//		
//		SupportedLanguage sl = (SupportedLanguage) maps.getSupportedLanguage()[0];
//		assertTrue(sl.getLocalId().equals("en"));
//		assertTrue(sl.getUri().equals("www.en.org/orsomething"));
//		
//		SupportedNamespace sn = (SupportedNamespace) maps.getSupportedNamespace()[0];
//		assertTrue(sn.getEquivalentCodingScheme().equals("Automobiles"));
//		assertTrue(sn.getLocalId().equals("Automobiles"));
//		assertTrue(sn.getUri().equals("urn:oid:11.11.0.1"));
//		
//		List<SupportedProperty> sps = maps.getSupportedPropertyAsReference();
//		assertTrue(sps.size() == 2);
//		for (SupportedProperty sp : sps)
//		{
//			assertTrue(sp.getLocalId().equals("definition") || sp.getLocalId().equals("textualPresentation"));
//		}
//		
//		List<SupportedSource> sss = maps.getSupportedSourceAsReference();
//		assertTrue(sss.size() == 2);
//		for (SupportedSource ss : sss)
//		{
//			String localId = ss.getLocalId();
//			
//			assertTrue(localId.equals("lexgrid.org") || localId.equals("_111101"));
//			
//			if (localId.equals("lexgrid.org"))
//			{
//				assertTrue(ss.getAssemblyRule().equals("rule1"));
//				assertTrue(ss.getUri().equals("http://informatics.mayo.edu"));
//			}
//		}
//		
//		assertTrue(pickList.getPickListEntryNode().length == 8);
//		
//		List<PickListEntryNode> plens = pickList.getPickListEntryNodeAsReference();
//		for (PickListEntryNode plen : plens)
//		{
//			String plEntryId = plen.getPickListEntryId();
//			assertTrue(plEntryId.equals("PL005p1")
//					|| plEntryId.equals("PL005p2")
//					|| plEntryId.equals("PLFordp1")
//					|| plEntryId.equals("PLJaguarp1")
//					|| plEntryId.equals("PLChevroletp1")
//					|| plEntryId.equals("PLOldsmobilep1")
//					|| plEntryId.equals("PLGMp1")
//					|| plEntryId.equals("PLGMp2"));
//			
//			if (plEntryId.equals("PL005p1"))
//			{
//				PickListEntry ple = plen.getPickListEntryNodeChoice().getInclusionEntry();
//				assertTrue(ple.getEntityCode().equals("005"));
//				assertTrue(ple.getEntityCodeNamespace().equals("Automobiles"));
//				assertTrue(ple.getPropertyId().equals("p1"));
//				assertTrue(ple.getPickText().equals("Domestic Auto Makers"));
//				
//				EntryState es = plen.getEntryState();
//				assertTrue(es.getContainingRevision().equals("R001"));
//				assertTrue(es.getPrevRevision().equals("R00A"));
//				assertTrue(es.getRelativeOrder() == 1);
//				assertTrue(es.getChangeType().name().equals(ChangeType.NEW.name()));	
//				
//				List<String> pcs = ple.getPickContextAsReference();
//				assertTrue(pcs.size() == 2);				
//				for (String pc : pcs)
//				{
//					assertTrue(pc.equals("Domestic Auto Makers") || pc.equals("Cars"));
//				}
//				
//				Property prop = (Property) plen.getProperties().getProperty()[0];
//				assertTrue(prop.getPropertyName().equals("definition"));
//				assertTrue(prop.getValue().getDataType().equals("textplain"));
//				assertTrue(prop.getValue().getContent().equals("Definition for Domestic Auto Makers"));
//				EntryState pes = prop.getEntryState();
//				assertTrue(pes.getContainingRevision().equals("R001"));
//				assertTrue(pes.getPrevRevision().equals("R00A"));
//				assertTrue(pes.getRelativeOrder() == 1);
//				assertTrue(pes.getChangeType().name().equals(ChangeType.NEW.name()));				
//			}
//			else if (plEntryId.equals("PL005p2"))
//			{
//				PickListEntry ple = plen.getPickListEntryNodeChoice().getInclusionEntry();
//				assertTrue(ple.getEntityCode().equals("005"));
//				assertTrue(ple.getEntityCodeNamespace().equals("Automobiles"));
//				assertTrue(ple.getPropertyId().equals("p2"));
//				assertTrue(ple.getPickText().equals("American Car Companies"));
//				
//				EntryState pes = plen.getEntryState();
//				assertTrue(pes.getContainingRevision().equals("R001"));
//				assertTrue(pes.getPrevRevision().equals("R00A"));
//				assertTrue(pes.getRelativeOrder() == 1);
//				assertTrue(pes.getChangeType().name().equals(ChangeType.NEW.name()));	
//				
//				List<String> pcs = Arrays.asList(ple.getPickContext());
//				assertTrue(pcs.size() == 0);				
//				
//				Property prop = (Property) plen.getProperties().getProperty()[0];
//				assertTrue(prop.getPropertyName().equals("definition"));
//				assertTrue(prop.getValue().getDataType().equals("textplain"));
//				assertTrue(prop.getValue().getContent().equals("Definition for Amerocan Auto Makers"));
//				pes = prop.getEntryState();
//				assertTrue(pes.getContainingRevision().equals("R001"));
//				assertTrue(pes.getPrevRevision().equals("R00A"));
//				assertTrue(pes.getRelativeOrder() == 1);
//				assertTrue(pes.getChangeType().name().equals(ChangeType.NEW.name()));				
//			}
//			else if (plEntryId.equals("PLFordp1"))
//			{
//				PickListEntry ple = plen.getPickListEntryNodeChoice().getInclusionEntry();
//				assertTrue(ple.getEntityCode().equals("Ford"));
//				assertTrue(ple.getEntityCodeNamespace().equals("Automobiles"));
//				assertTrue(ple.getPropertyId().equals("p1"));
//				assertTrue(ple.getPickText().equals("Ford"));
//				
//				List<String> pcs = Arrays.asList(ple.getPickContext());
//				assertTrue(pcs.size() == 0);				
//				
//				Property prop = (Property) plen.getProperties().getProperty()[0];
//				assertTrue(prop.getPropertyName().equals("textualPresentation"));
//				assertTrue(prop.getIsActive() == true);
//				assertTrue(prop.getLanguage().equals("en"));
//				assertTrue(prop.getPropertyId().equals("p1"));
//				assertTrue(prop.getPropertyType().equals("presentation"));
//				assertTrue(prop.getStatus().equals("active"));
//				String owner = prop.getOwner();
//				assertTrue(owner.equals("Ford"));
//				assertTrue(prop.getValue().getDataType().equals("textplain"));
//				assertTrue(prop.getValue().getContent().equals("Property for Ford"));
//				EntryState pes = prop.getEntryState();
//				assertTrue(pes.getContainingRevision().equals("R001"));
//				assertTrue(pes.getPrevRevision().equals("R00A"));
//				assertTrue(pes.getRelativeOrder() == 1);
//				assertTrue(pes.getChangeType().name().equals(ChangeType.NEW.name()));				
//			}
//		}
//		
//		pickList = getPickListService().getPickListDefinitionById("SRITEST:FA:MicrobialStructureOntologyMinusMCell");
//		
//		assertTrue(pickList.getDefaultEntityCodeNamespace().equals("fungal_anatomy"));
//		assertTrue(pickList.getDefaultLanguage().equals("en"));
//		assertTrue(pickList.getDefaultSortOrder().equals("Assending"));
//		assertTrue(pickList.getEntityDescription().equals("Microbial Structure Ontology  with out Mating cell"));
//		
//		plens = Arrays.asList(pickList.getPickListEntryNode());
//		List<PickListEntry> plInclusionList = new ArrayList<PickListEntry>();
//		List<PickListEntryExclusion> plExclusionList = new ArrayList<PickListEntryExclusion>();
//		
//		PickListEntry plInclusion = null;
//		PickListEntryExclusion plExclusion = null;
//		
//		for (PickListEntryNode plen : plens)
//		{
//			plInclusion = plen.getPickListEntryNodeChoice().getInclusionEntry();
//			if (plInclusion != null)
//				plInclusionList.add(plInclusion);
//			
//			plExclusion = plen.getPickListEntryNodeChoice().getExclusionEntry();
//			if (plExclusion != null)
//				plExclusionList.add(plExclusion);
//		}
//		
//		assertTrue(plInclusionList.size() == 8);
//		assertTrue(plExclusionList.size() == 1);
//		
//		plInclusionList.clear();
//		plExclusionList.clear();
//	}
//	
//	/**
//	 * Test method for {@link org.lexgrid.valuedomain.impl.LexEVSPickListServicesImpl#resolvePickListForTerm(java.lang.String, java.lang.String, java.lang.String, java.lang.String[], boolean)}.
//	 * @throws LBException 
//	 */
//	@Test
//	public void testResolvePickListForTerm() throws LBException {
//		ResolvedPickListEntryList pickLists = getPickListService().resolvePickListForTerm("SRITEST:AUTO:AllDomesticButGM", "Jaguar", MatchAlgorithms.exactMatch.name(), null, null, false);
//		assertTrue(pickLists.getResolvedPickListEntryCount() == 1);
//		
//		Iterator<ResolvedPickListEntry> plItr = pickLists.iterateResolvedPickListEntry();
//		while(plItr.hasNext())
//		{
//			ResolvedPickListEntry pickList = plItr.next();
//			assertTrue(pickList.getEntityCode().equals("Jaguar"));
//			assertTrue(pickList.getPropertyId().equals("p1"));
//		}
//		
//		pickLists = getPickListService().resolvePickListForTerm("SRITEST:FA:MicrobialStructureOntologyMinusMCell", "cell", MatchAlgorithms.LuceneQuery.name(), null, null, false);
//		plItr = pickLists.iterateResolvedPickListEntry();
//		while(plItr.hasNext())
//		{
//			ResolvedPickListEntry pickList = plItr.next();
//			assertTrue(pickList.getEntityCode().equals("FAO:0000032"));
//		}
//		
//		pickLists = getPickListService().resolvePickListForTerm("SRITEST:FA:MicrobialStructureOntologyMinusMCell", "structure", MatchAlgorithms.LuceneQuery.name(), null, null, false);
//		plItr = pickLists.iterateResolvedPickListEntry();
//		while(plItr.hasNext())
//		{
//			ResolvedPickListEntry pickList = plItr.next();
//			assertTrue(pickList.getEntityCode().equals("FAO:0000001") || pickList.getEntityCode().equals("FAO:0000018"));
//		}
//		
//		pickLists.removeAllResolvedPickListEntry();
//	}
//	
//	@Test
//	public void testResolvePickListForTermForCompleteDomain() throws LBException {
//		ResolvedPickListEntryList pickLists = getPickListService().resolvePickListForTerm("SRITEST:FA:MSOntologyAndHyphaInMycelium", "hypha", MatchAlgorithms.LuceneQuery.name(), null, null, false);
//		
//		assertTrue(pickLists.getResolvedPickListEntryCount() == 4);
//		
//		Iterator<ResolvedPickListEntry> plItr = pickLists.iterateResolvedPickListEntry();
//		while(plItr.hasNext())
//		{
//			ResolvedPickListEntry pickList = plItr.next();
//			assertTrue(pickList.getEntityCode().equals("FAO:0001014")
//					|| pickList.getEntityCode().equals("FAO:0001013")
//					|| pickList.getEntityCode().equals("FAO:0001004")
//					|| pickList.getEntityCode().equals("FAO:0001003"));
//			assertTrue(pickList.getEntityCodeNamespace().equals("fungal_anatomy"));
//			assertTrue(pickList.getPickText().contains("hypha"));
//		}
//		
//		pickLists.removeAllResolvedPickListEntry();
//	}
//	
//	@Test
//	public void testResolvePickList() throws LBException {
//		ResolvedPickListEntryList pleList = getPickListService().resolvePickList("SRITEST:AUTO:DomesticAutoMakers", true);
//		
//		assertTrue(pleList.getResolvedPickListEntryCount() == 8);
//		
//		Iterator<ResolvedPickListEntry> plItr = pleList.iterateResolvedPickListEntry();
//		while(plItr.hasNext())
//		{
//			ResolvedPickListEntry plen = plItr.next();
//			
//			assertTrue(plen.getEntityCodeNamespace().equals("Automobiles"));
//			
//			String code = plen.getEntityCode();
//			assertTrue(code.equals("005")
//					|| code.equals("Ford")
//					|| code.equals("GM")
//					|| code.equals("Jaguar")
//					|| code.equals("Chevy")
//					|| code.equals("73"));
//			
//			if (code.equals("005"))
//			{
//				assertTrue((plen.getPickText().equals("Domestic Auto Makers") && plen.getPropertyId().equals("p1"))
//						|| (plen.getPickText().equals("American Car Companies") && plen.getPropertyId().equals("p2")));
//			}
//			else if (code.equals("Chevy"))
//			{
//				assertTrue(plen.getPickText().equals("Chevrolet") && plen.getPropertyId().equals("p1"));
//			}
//			else if (code.equals("Ford"))
//			{
//				assertTrue(plen.getPickText().equals("Ford") && plen.getPropertyId().equals("p1"));
//			}
//			else if (code.equals("GM"))
//			{
//				assertTrue((plen.getPickText().equals("General Motors") && plen.getPropertyId().equals("p1"))
//						|| (plen.getPickText().equals("GM") && plen.getPropertyId().equals("p2")));
//			}
//			else if (code.equals("Jaguar"))
//			{
//				assertTrue(plen.getPickText().equals("Jaguar") && plen.getPropertyId().equals("p1"));
//			}
//			else if (code.equals("73"))
//			{
//				assertTrue(plen.getPickText().equals("Oldsmobile") && plen.getPropertyId().equals("p1"));
//			}
//		}
//		
//		pleList = getPickListService().resolvePickList("SRITEST:FA:MicrobialStructureOntologyMinusMCell", true);
//		
//		assertTrue(pleList.getResolvedPickListEntryCount() == 8);
//		
//		plItr = pleList.iterateResolvedPickListEntry();
//		while(plItr.hasNext())
//		{
//			ResolvedPickListEntry plen = plItr.next();
//			
//			assertTrue(plen.getEntityCodeNamespace().equals("fungal_anatomy"));
//			
//			String code = plen.getEntityCode();
//			assertTrue(code.equals("FAO:0000001")
//					|| code.equals("FAO:0000005")
//					|| code.equals("FAO:0000018")
//					|| code.equals("FAO:0000019")
//					|| code.equals("FAO:0000032")
//					|| code.equals("FAO:0001001"));
//			
//			if (code.equals("FAO:0000001"))
//			{
//				assertTrue((plen.getPickText().equals("microbial structure ontology") && plen.getPropertyId().equals("p1"))
//						|| (plen.getPickText().equals("fungal structure ontology") && plen.getPropertyId().equals("p2")));
//			}
//			else if (code.equals("FAO:0000005"))
//			{
//				assertTrue(plen.getPickText().equals("obsolete") && plen.getPropertyId().equals("p1"));
//			}
//			else if (code.equals("FAO:0000018"))
//			{
//				assertTrue(plen.getPickText().equals("unicellular structure") && plen.getPropertyId().equals("p1"));
//			}
//			else if (code.equals("FAO:0000019"))
//			{
//				assertTrue(plen.getPickText().equals("spore") && plen.getPropertyId().equals("p1"));
//			}
//			else if (code.equals("FAO:0000032"))
//			{
//				assertTrue((plen.getPickText().equals("vegetative cell") && plen.getPropertyId().equals("p1"))
//						|| (plen.getPickText().equals("yeast-form") && plen.getPropertyId().equals("p3")));
//			}
//			else if (code.equals("FAO:0001001"))
//			{
//				assertTrue(plen.getPickText().equals("hypha") && plen.getPropertyId().equals("p1"));
//			}
//		}
//	}
//	
//	@Test
//	public void testResolvePickListForCompleteDomain() throws LBException {
//		ResolvedPickListEntryList pickLists = getPickListService().resolvePickList("SRITEST:FA:hyphaLeafOnly", true);
//		
//		assertTrue(pickLists.getResolvedPickListEntryCount() == 3);
//		
//		Iterator<ResolvedPickListEntry> plItr = pickLists.iterateResolvedPickListEntry();
//		while(plItr.hasNext())
//		{
//			ResolvedPickListEntry pickList = plItr.next();
//			assertTrue((pickList.getEntityCode().equals("FAO:0001018")
//						&& pickList.getPickText().equals("hypha with dolipore septa"))
//					|| (pickList.getEntityCode().equals("FAO:0001014")
//						&& pickList.getPickText().equals("aseptate hypha in mycelium"))
//					|| (pickList.getEntityCode().equals("FAO:0001013")
//						&& pickList.getPickText().equals("hypha with dolipore septa, in mycelium")));
//			assertTrue(pickList.getEntityCodeNamespace().equals("fungal_anatomy"));
//			assertTrue(pickList.getPickText().contains("hypha"));
//		}
//		
//		pickLists.removeAllResolvedPickListEntry();
//	}
//	
//	/**
//	 * Test method for {@link org.lexgrid.valuedomain.impl.LexEVSPickListServicesImpl#getPickListValueDomain(java.lang.String)}.
//	 * @throws LBException 
//	 */
//	@Test
//	public void testGetPickListValueDomain() throws LBException {
//		URI valueDomainURI = getPickListService().getPickListValueSetDefinition("SRITEST:AUTO:AllDomesticButGM");		
//		assertTrue(valueDomainURI.toString().equals("SRITEST:AUTO:AllDomesticButGM"));
//		
//		valueDomainURI = getPickListService().getPickListValueSetDefinition("SRITEST:AUTO:DomesticAutoMakers");		
//		assertTrue(valueDomainURI.toString().equals("SRITEST:AUTO:DomesticAutoMakers"));
//	}

	/**
	 * Test method for {@link org.lexgrid.valuedomain.impl.LexEVSPickListServicesImpl#getPickListDefinitionIdForValueSetDefinitionUri(java.net.URI)}.
	 * @throws URISyntaxException 
	 * @throws LBException 
	 */
	@Test
	public void testGetPickListDefinitionIdForValueSetDefinitionUri() throws LBException, URISyntaxException {
//		System.out.println("In testGetPickListDefinitionIdForValueSetDefinitionUri");
//		List<String> pickListIds = getPickListService().getPickListDefinitionIdForValueSetDefinitionUri(new URI("TESTVSD"));
//		
//		System.out.println("Pick List id size : " + pickListIds.size());
//		
//		for (String plId : pickListIds)
//		{
//			System.out.println("Pick List Id : " + plId);
//		}
		
		
//		List<PickListDefinition> pickLists = getPickListService().getPickListDefinitionsForValueSetDef(new URI("SRITEST:AUTO:DomesticAutoMakers"));
//		
//		for (PickListDefinition pickList : pickLists)
//		{
//			assertTrue(pickList.getPickListId().equals("SRITEST:AUTO:DomesticAutoMakers"));
//			assertTrue(pickList.getDefaultEntityCodeNamespace().equals("Automobiles"));
//			
//			Iterator<PickListEntryNode> pleItr = pickList.getPickListEntryNodeAsReference().iterator();
//			
//			while (pleItr.hasNext())
//			{
//				PickListEntryNode plEntry = pleItr.next();
//				assertTrue(plEntry.getPickListEntryId().equals("PL005p1")
//						|| plEntry.getPickListEntryId().equals("PL005p2")
//						|| plEntry.getPickListEntryId().equals("PLFordp1")
//						|| plEntry.getPickListEntryId().equals("PLJaguarp1")
//						|| plEntry.getPickListEntryId().equals("PLChevroletp1")
//						|| plEntry.getPickListEntryId().equals("PLOldsmobilep1")
//						|| plEntry.getPickListEntryId().equals("PLGMp1")
//						|| plEntry.getPickListEntryId().equals("PLGMp2"));
//			}
//		}
	}
	
	@Test
	public void testGetPickListDefinitionForPickListId() throws LBException, URISyntaxException {
		System.out.println("In testGetPickListDefinitionForPickListId");
		PickListDefinition plDef = getPickListService().getPickListDefinitionById("TESTPL1");
		System.out.println("pickList id" + plDef.getPickListId());
		
		if (plDef.getProperties() != null)
		{
			for (Property prop : plDef.getProperties().getPropertyAsReference())
			{
				System.out.println("TESTPL1 - property id : " + prop.getPropertyId());
				System.out.println("TESTPL1 - property name : " + prop.getPropertyName());
				System.out.println("TESTPL1 - property value : " + prop.getValue().getContent());
				System.out.println("TESTPL1 - property datatype : " + prop.getValue().getDataType());
				System.out.println("TESTPL1 - property owner : " + prop.getOwner());
				System.out.println("TESTPL1 - property status : " + prop.getStatus());
				System.out.println("TESTPL1 - property isActive : " + prop.isIsActive());
			}
		}
		
		for (PickListEntryNode plEntryNode : plDef.getPickListEntryNodeAsReference())
		{
			System.out.println("plEntryNode id : " + plEntryNode.getPickListEntryId());
			
			PickListEntry plEntry = plEntryNode.getPickListEntryNodeChoice().getInclusionEntry();
			if (plEntry != null)
			{
				System.out.println("plEntry entityCode : " + plEntry.getEntityCode());
				System.out.println("plEntry entityCodeNS : " + plEntry.getEntityCodeNamespace());
			}
			
			PickListEntryExclusion plExclusion = plEntryNode.getPickListEntryNodeChoice().getExclusionEntry();
			if (plExclusion != null)
			{
				System.out.println("plExclusion entityCode : " + plExclusion.getEntityCode());
				System.out.println("plExclusion entityCodeNS : " + plExclusion.getEntityCodeNamespace());
			}
			
			if (plEntryNode.getProperties() != null)
			{
				for (Property prop : plEntryNode.getProperties().getPropertyAsReference())
				{
					System.out.println("TESTPL1 entry - property id : " + prop.getPropertyId());
					System.out.println("TESTPL1 entry - property name : " + prop.getPropertyName());
					System.out.println("TESTPL1 entry - property value : " + prop.getValue().getContent());
					System.out.println("TESTPL1 entry - property datatype : " + prop.getValue().getDataType());
					System.out.println("TESTPL1 entry - property owner : " + prop.getOwner());
					System.out.println("TESTPL1 entry - property status : " + prop.getStatus());
					System.out.println("TESTPL1 entry - property isActive : " + prop.isIsActive());
				}
			}
		}		
		
		if (plDef.getMappings() != null)
		{
			for (SupportedAssociation sa : plDef.getMappings().getSupportedAssociation())
			{
				System.out.println("sa uri : " + sa.getUri());
				System.out.println("sa localId : " + sa.getLocalId());
				System.out.println("sa content : " + sa.getContent());
			}
			
			for (SupportedCodingScheme scs : plDef.getMappings().getSupportedCodingScheme())
			{
				System.out.println("scs uri : " + scs.getUri());
				System.out.println("scs localId : " + scs.getLocalId());
				System.out.println("scs content : " + scs.getContent());
				System.out.println("scs isimported : " + scs.getIsImported());
			}
			
			for (SupportedNamespace sn : plDef.getMappings().getSupportedNamespace())
			{
				System.out.println("sn uri : " + sn.getUri());
				System.out.println("sn localId : " + sn.getLocalId());
				System.out.println("sn content : " + sn.getContent());
				System.out.println("sn EquivalentCodingScheme : " + sn.getEquivalentCodingScheme());
			}
			
			for (SupportedHierarchy sh : plDef.getMappings().getSupportedHierarchy())
			{
				System.out.println("sh uri : " + sh.getUri());
				System.out.println("sh localId : " + sh.getLocalId());
				System.out.println("sh content : " + sh.getContent());
				System.out.println("sh rootCode : " + sh.getRootCode());
				for (String assn : sh.getAssociationNamesAsReference())
				{
					System.out.println("sh assn : " + assn);
				}
			}
		}
	}
	
	@Test
	public void testGetPickListIdByEntityReference() throws LBException{
		System.out.println("in testGetPickListIdByEntityReference");
		
		Map<String, String> plRefs = getPickListService().getReferencedPLDefinitions("entityCode 2", null, null, false);
		
		System.out.println("is plRef null? " + (plRefs == null));
//		for (String plId : plRefs.keySet())
//		{
//			System.out.println("pl ids : " + plId);
//		}
	}

	/**
	 * 
	 * @throws LBException
	 */
	@Test
	public void testListPickListIds() throws LBException {
//		System.out.println("In listPickListIds");
//		List<String> pickListIds = getPickListService().listPickListIds();
//		
//		System.out.println("Pick list Id size : " + pickListIds.size());
//		
//		assertTrue(pickListIds.size() >= 1);
//		
//		for (String plId : pickListIds)
//		{
//			System.out.println("Pick List Id : " + plId);
//		}
//		
//		pickListIds.clear();
	}
	
	@Test
	public void testGetPickListIdsForSupportedTagAndValue(){
		System.out.println("in testGetPickListIdsForSupportedTagAndValue");
		List<String> pickListIds = getPickListService().getPickListIdsForSupportedTagAndValue("codingScheme", "scs uri");
		
		for (String pickListId : pickListIds)
		{
			System.out.println("pick list with supported cs 'scs uri' : " + pickListId);
		}
	}
	
	/**
	 * 
	 * @throws LBException
	 */
	@Test
	public void testGetValueSetDefURIForPickListId() throws LBException {
//		System.out.println("In testGetValueSetDefURIForPickListId");
//		URI vsdURI = getPickListService().getPickListValueSetDefinition("TESTPL1");
//		
//		System.out.println("VSD URI : " + vsdURI);
	}
	
	private LexEVSPickListDefinitionServices getPickListService(){
		if (pls_ == null) {
			LexEVSPickListDefinitionServicesImpl pickListService = new LexEVSPickListDefinitionServicesImpl();
//			pickListService.setLexBIGService(ServiceHolder.instance().getLexBIGService());
			pls_ = pickListService;
		}
		return pls_;
	}
}