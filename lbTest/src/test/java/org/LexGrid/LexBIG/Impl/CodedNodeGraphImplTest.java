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
package org.LexGrid.LexBIG.Impl;

import junit.framework.TestCase;

import org.LexGrid.LexBIG.DataModel.Collections.ResolvedConceptReferenceList;
import org.LexGrid.LexBIG.DataModel.Core.AssociatedConcept;
import org.LexGrid.LexBIG.DataModel.Core.Association;
import org.LexGrid.LexBIG.DataModel.Core.ConceptReference;
import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.Exceptions.LBParameterException;
import org.LexGrid.LexBIG.Impl.helpers.TestFilter2;
import org.LexGrid.LexBIG.Impl.testUtility.DataTestUtils;
import org.LexGrid.LexBIG.Impl.testUtility.ServiceHolder;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeGraph;
import org.LexGrid.LexBIG.LexBIGService.LexBIGService;
import org.LexGrid.LexBIG.Utility.Constructors;
import org.LexGrid.LexBIG.Utility.ConvenienceMethods;

/**
 * JUnit test cases for the coded node graph.
 * 
 * @author <A HREF="mailto:armbrust.daniel@mayo.edu">Dan Armbrust</A>
 * @version subversion $Revision: $ checked in on $Date: $
 */
public class CodedNodeGraphImplTest extends TestCase {
    
    private LexBIGService lbsi;
    
    public void setUp(){
        lbsi = ServiceHolder.instance().getLexBIGService(); 
    }

    public void testAreCodesRelated() throws LBException {
        CodedNodeGraph cng = lbsi.getNodeGraph("Automobiles", null, "relations");

        // A couple of non-transitive tests
        assertTrue(cng.areCodesRelated(Constructors.createNameAndValue("hasSubtype", null),
                Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Ford", "urn:oid:11.11.0.1"), true).booleanValue());

        // This is technically, illegal. But its what everyone is going to do...
        // so I bent some rules.
        assertTrue(cng.areCodesRelated(Constructors.createNameAndValue("hasSubtype", null),
                Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Ford", "urn:oid:11.11.0.1"), true).booleanValue());

        // This is technically illegal as well... bent the rules the same as
        // above.
        assertTrue(cng.areCodesRelated(Constructors.createNameAndValue("hasSubtype", null),
                Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Ford", "urn:oid:11.11.0.1"), true).booleanValue());

        // This is how hasSubtype is supposed to be specified, if they wanted to
        // fully specify it
        // If you don't know where this oid came from, you can see why I bent
        // the rules...
        assertTrue(cng.areCodesRelated(
                Constructors.createNameAndValue("hasSubtype", null),
                Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Ford", "urn:oid:11.11.0.1"), true).booleanValue());

        // those 4 tests should all work with directOnly set to false as well...
        assertTrue(cng.areCodesRelated(Constructors.createNameAndValue("hasSubtype", null),
                Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Ford", "urn:oid:11.11.0.1"), false).booleanValue());

        // This is technically, illegal. But its what everyone is going to do...
        // so I bent some rules.
        assertTrue(cng.areCodesRelated(Constructors.createNameAndValue("hasSubtype", null),
                Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Ford", "urn:oid:11.11.0.1"), false).booleanValue());

        // This is technically illegal as well... bent the rules the same as
        // above.
        assertTrue(cng.areCodesRelated(Constructors.createNameAndValue("hasSubtype", null),
                Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Ford", "urn:oid:11.11.0.1"), false).booleanValue());

        // This is how hasSubtype is supposed to be specified, if they wanted to
        // fully specify it
        // If you don't know where this oid came from, you can see why I bent
        // the rules...
        assertTrue(cng.areCodesRelated(
                Constructors.createNameAndValue("hasSubtype", null),
                Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Ford", "urn:oid:11.11.0.1"), false).booleanValue());

        // now test something that requires transitivity
        assertTrue(cng.areCodesRelated(Constructors.createNameAndValue("hasSubtype", null),
                Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Jaguar", "urn:oid:11.11.0.1"), false).booleanValue());

        // This is technically, illegal. But its what everyone is going to do...
        // so I bent some rules.
        assertTrue(cng.areCodesRelated(Constructors.createNameAndValue("hasSubtype", null),
                Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Jaguar", "urn:oid:11.11.0.1"), false).booleanValue());

        // This is technically illegal as well... bent the rules the same as
        // above.
        assertTrue(cng.areCodesRelated(Constructors.createNameAndValue("hasSubtype", null),
                Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Jaguar", "urn:oid:11.11.0.1"), false).booleanValue());

        // This is how hasSubtype is supposed to be specified, if they wanted to
        // fully specify it
        // If you don't know where this oid came from, you can see why I bent
        // the rules...
        assertTrue(cng.areCodesRelated(
                Constructors.createNameAndValue("hasSubtype", null),
                Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Jaguar", "urn:oid:11.11.0.1"), false).booleanValue());

        // and with direct only set to true, they should fail
        assertFalse(cng.areCodesRelated(Constructors.createNameAndValue("hasSubtype", null),
                Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Jaguar", "urn:oid:11.11.0.1"), true).booleanValue());

        // This is technically, illegal. But its what everyone is going to do...
        // so I bent some rules.
        assertFalse(cng.areCodesRelated(Constructors.createNameAndValue("hasSubtype", null),
                Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Jaguar", "urn:oid:11.11.0.1"), true).booleanValue());

        // This is technically illegal as well... bent the rules the same as
        // above.
        assertFalse(cng.areCodesRelated(Constructors.createNameAndValue("hasSubtype", null),
                Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Jaguar", "urn:oid:11.11.0.1"), true).booleanValue());

        // This is how hasSubtype is supposed to be specified, if they wanted to
        // fully specify it
        // If you don't know where this oid came from, you can see why I bent
        // the rules...
        assertFalse(cng.areCodesRelated(
                Constructors.createNameAndValue("hasSubtype", null),
                Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Jaguar", "urn:oid:11.11.0.1"), true).booleanValue());
    }

    public void testIntersection() throws LBException {
        ConvenienceMethods cm = new ConvenienceMethods(lbsi);

        CodedNodeGraph cng = lbsi.getNodeGraph("Automobiles", null, "relations");

        // create a graph that contains everything....
        cng.restrictToAssociations(Constructors.createNameAndValueList("hasSubtype"), null);

        // create a graph that contains 005 -> Ford
        CodedNodeGraph cng2 = lbsi.getNodeGraph("Automobiles", null, "relations");
        cng2.restrictToSourceCodes(cm.createCodedNodeSet(new String[] { "005" }, "Automobiles", null));
        cng2.restrictToTargetCodes(cm.createCodedNodeSet(new String[] { "Ford" }, "Automobiles", null));

        // intersect them
        CodedNodeGraph cng3 = cng.intersect(cng2);

        // result should only contain 005 -> Ford
        // result should be 005 -> Ford -> Jaguar
        ResolvedConceptReference[] rcr = cng3.resolveAsList(null, true, false, -1, -1, null, null, null, 50)
                .getResolvedConceptReference();
        
        assertEquals(1, rcr.length);
        // top node
        assertTrue(rcr[0].getConceptCode().equals("005"));

        // no uplink
        assertTrue(rcr[0].getTargetOf() == null || rcr[0].getTargetOf().getAssociation().length == 0);

        // 1 down link
        Association[] assn = rcr[0].getSourceOf().getAssociation();
        assertTrue(assn.length == 1);
        AssociatedConcept[] ac = assn[0].getAssociatedConcepts().getAssociatedConcept();
        assertEquals(1,ac.length);
        assertEquals("Ford", ac[0].getConceptCode());

        // no more below it
        assertTrue(ac[0].getSourceOf() == null);
    }

    public void testIsCodeInGraph() throws LBException {
        ConvenienceMethods cm = new ConvenienceMethods(lbsi);
        CodedNodeGraph cng = lbsi.getNodeGraph("Automobiles", null, "relations");

        assertTrue(cng.isCodeInGraph(Constructors.createConceptReference("Ford", "Automobiles")).booleanValue());
        assertTrue(cng.isCodeInGraph(Constructors.createConceptReference("Ford", "urn:oid:11.11.0.1")).booleanValue());
        // this is an odd local name we assigned to automobiles.
        assertTrue(cng.isCodeInGraph(Constructors.createConceptReference("Ford", "SomeOtherValue")).booleanValue());
        assertTrue(cng.isCodeInGraph(Constructors.createConceptReference("Jaguar", "Automobiles")).booleanValue());

        cng.restrictToSourceCodes(cm.createCodedNodeSet(new String[] { "005" }, "Automobiles", null));
        assertTrue(cng.isCodeInGraph(Constructors.createConceptReference("Ford", "Automobiles")).booleanValue());
        // this will be false because of the restrict to source codes...
        assertFalse(cng.isCodeInGraph(Constructors.createConceptReference("Jaguar", "Automobiles")).booleanValue());

        cng.restrictToTargetCodes(cm.createCodedNodeSet(new String[] { "GM" }, "Automobiles", null));
        assertFalse(cng.isCodeInGraph(Constructors.createConceptReference("Ford", "Automobiles")).booleanValue());
        assertTrue(cng.isCodeInGraph(Constructors.createConceptReference("GM", "Automobiles")).booleanValue());
    }

    public void testListCodeRelationships() throws LBException {
        CodedNodeGraph cng = lbsi.getNodeGraph("Automobiles", null, "relations");

        ConceptReference[] cr = cng.listCodeRelationships(Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Ford", "Automobiles"), false).getConceptReference();

        assertTrue(cr.length == 1);
        // Yes, has subtype should come back with the global oid for hasSubtype.
        assertTrue(contains(cr, "hasSubtype", "urn:oid:1.3.6.1.4.1.2114.108.1.8.1"));

        // transitive flag switched
        cr = cng.listCodeRelationships(Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Ford", "Automobiles"), true).getConceptReference();

        assertTrue(cr.length == 1);
        // Yes, has subtype should come back with the global oid for hasSubtype.
        assertTrue(contains(cr, "hasSubtype", "urn:oid:1.3.6.1.4.1.2114.108.1.8.1"));

        cr = cng.listCodeRelationships(Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Jaguar", "Automobiles"), false).getConceptReference();

        assertTrue(cr.length == 1);
        // Yes, has subtype should come back with the global oid for hasSubtype.
        assertTrue(contains(cr, "hasSubtype", "urn:oid:1.3.6.1.4.1.2114.108.1.8.1"));

        // transitive flag switched
        cr = cng.listCodeRelationships(Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Jaguar", "Automobiles"), 2).getConceptReference();

        assertTrue(cr.length == 1);

        cr = cng.listCodeRelationships(Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Jaguar", "Automobiles"), 1).getConceptReference();

        assertTrue(cr.length == 0);

        cr = cng.listCodeRelationships(Constructors.createConceptReference("005", "Automobiles"),
                Constructors.createConceptReference("Jaguar", "Automobiles"), 3).getConceptReference();

        assertTrue(cr.length == 0);
    }

    public void testUnion() throws LBException {
        ConvenienceMethods cm = new ConvenienceMethods(lbsi);

        CodedNodeGraph cng = lbsi.getNodeGraph("Automobiles", null, "relations");

        // create a graph that contains 005 -> Ford
        cng.restrictToAssociations(Constructors.createNameAndValueList("hasSubtype"), null);
        cng.restrictToSourceCodes(cm.createCodedNodeSet(new String[] { "005" }, "Automobiles", null));
        cng.restrictToTargetCodes(cm.createCodedNodeSet(new String[] { "Ford" }, "Automobiles", null));

        // create a graph that contains Ford -> * (* is actually Jaguar, in this
        // code system)
        CodedNodeGraph cng2 = lbsi.getNodeGraph("Automobiles", null, "relations");
        cng2.restrictToSourceCodes(cm.createCodedNodeSet(new String[] { "Ford" }, "Automobiles", null));
        cng2.restrictToTargetCodes(cm.createCodedNodeSet(new String[] { "Jaguar" }, "Automobiles", null));

        // join them
        CodedNodeGraph unionGraph = cng.union(cng2);

        // result should be 005 -> Ford -> Jaguar
        ResolvedConceptReference[] rcr = unionGraph.resolveAsList(null, true, false, -1, -1, null, null, null, 50)
                .getResolvedConceptReference();

        assertEquals(1,rcr.length);
        // top node
        assertTrue(rcr[0].getConceptCode().equals("005"));

        // no uplink
        assertTrue(rcr[0].getTargetOf() == null || rcr[0].getTargetOf().getAssociation().length == 0);

        // 1 down link
        Association[] assn = rcr[0].getSourceOf().getAssociation();
        assertTrue(assn.length == 1);
        AssociatedConcept[] ac = assn[0].getAssociatedConcepts().getAssociatedConcept();
        assertTrue(ac.length == 1);
        assertTrue(ac[0].getConceptCode().equals("Ford"));

        // ford has a downlink to Jaguar
        assn = ac[0].getSourceOf().getAssociation();
        
        // ford has a 'uses' and 'hasSubtype' association
        assertTrue(assn.length == 3);
        
        //dig out the 'hasSubtype' Association
        Association hasSubtypeAssoc = null;
        for(Association assoc : assn){
            if(assoc.getAssociationName().equals("hasSubtype")){
                hasSubtypeAssoc = assoc;
            }
        }
        assertTrue(hasSubtypeAssoc != null);
        ac = hasSubtypeAssoc.getAssociatedConcepts().getAssociatedConcept();
        assertTrue(ac.length == 1);
        assertTrue(ac[0].getConceptCode().equals("Jaguar"));
    }
    
    public void testEntityDescriptionOnAssociatiedConcepts() throws Exception, LBParameterException{
        CodedNodeGraph cng = lbsi.getNodeGraph("Automobiles", null, null);
        ResolvedConceptReferenceList rcrl = 
            cng.resolveAsList(Constructors.createConceptReference("005", "Automobiles"), true, true, -1, -1, null, null, null, null, -1);
        assertTrue(rcrl.getResolvedConceptReferenceCount() == 1);
        ResolvedConceptReference ref = rcrl.getResolvedConceptReference(0);
        
        assertNotNull(ref.getSourceOf());
        
        assertTrue(ref.getSourceOf().getAssociation().length > 0);
        
        for(Association assoc : ref.getSourceOf().getAssociation()){
            assertNotNull(assoc.getAssociatedConcepts());
            assertTrue(assoc.getAssociatedConcepts().getAssociatedConcept().length > 0);
            for(AssociatedConcept assocConcept : assoc.getAssociatedConcepts().getAssociatedConcept()){
                assertNotNull("Concept: " + assocConcept.getCode(), assocConcept.getEntityDescription());
            }
        }
    }

    public void testFilter() throws LBException {
        CodedNodeGraph cng = lbsi.getNodeGraph("Automobiles", null, "relations");

        // If I add a filter which only allows 'r' or 'm' as the third letter in
        // entity description
        // this should cut the full tree down to ...
        // Domestic Auto Makers
        // Ford (subclass of Auto Makers)
        // Car

        try {
            TestFilter2.register();
        } catch (LBParameterException e) {
            // can happen if is already registered by another test.
        }

        ResolvedConceptReference[] rcr = cng.resolveAsList(null, true, false, -1, -1, null, null, null,
        		ConvenienceMethods.createLocalNameList(TestFilter2.name_), -1).getResolvedConceptReference();

        assertTrue(rcr.length == 1);
        ResolvedConceptReference ref = null;
        for (int i = 0; i < rcr.length && ref == null; i++)
            if (rcr[i].getConceptCode().equals("005"))
                ref = rcr[i];
        assertTrue(ref != null);
        assertTrue(ref.getConceptCode().equals("005"));
        
        Association assoc = DataTestUtils.getAssociation(ref.getSourceOf().getAssociation(), "hasSubtype");
        
        assertTrue(DataTestUtils.isAssociatedConceptPresent(assoc.getAssociatedConcepts().getAssociatedConcept(), "Ford"));
    }

    private boolean contains(ConceptReference[] cr, String code, String codeSystem) {
        boolean contains = false;
        for (int i = 0; i < cr.length; i++) {
            if (cr[i].getConceptCode().equals(code) && cr[i].getCodingSchemeName().equals(codeSystem)) {
                contains = true;
                break;
            }
        }
        return contains;
    }
}