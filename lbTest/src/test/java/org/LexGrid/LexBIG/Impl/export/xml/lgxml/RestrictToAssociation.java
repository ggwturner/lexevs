package org.LexGrid.LexBIG.Impl.export.xml.lgxml;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.Impl.export.xml.lgxml.util.CngFactory;
import org.LexGrid.LexBIG.Impl.export.xml.lgxml.util.CnsFactory;
import org.LexGrid.LexBIG.Impl.export.xml.lgxml.util.CodingSchemeChecker;
import org.LexGrid.LexBIG.Impl.export.xml.lgxml.util.ExportDataVerifier;
import org.LexGrid.LexBIG.Impl.export.xml.lgxml.util.ExportHelper;
import org.LexGrid.LexBIG.Impl.export.xml.lgxml.util.ImportHelper;
import org.LexGrid.LexBIG.Impl.export.xml.lgxml.util.Logger;
import org.LexGrid.LexBIG.Impl.export.xml.lgxml.util.OutputDir;
import org.LexGrid.LexBIG.Impl.export.xml.lgxml.util.TestCleaner;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeGraph;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet;

public class RestrictToAssociation extends TestCase {

    private final String INPUT_FILE_NAME_AUTO2 = "resources/testData/lgXmlExport/Automobiles2.xml";
    
    private final String CS_AUTO2_URI = "urn:oid:11.11.0.1";
    private final String CS_AUTO2_VERSION = "1.1";
    
    private OutputDir outputDir;
    
    
    // uses
    private final String[] assoc1 = {"<lgRel:source",
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"",
                "xsi:schemaLocation=\"http://LexGrid.org/schema/2010/01/LexGrid/codingSchemes  http://LexGrid.org/schema/2010/01/LexGrid/codingSchemes.xsd\"",
                "sourceEntityCodeNamespace=\"Automobiles\" sourceEntityCode=\"A0001\">",
                "<lgRel:target targetEntityCode=\"Tires\" targetEntityCodeNamespace=\"ExpendableParts\"/>",
            "</lgRel:source>"};
    
    private final String[] assocPred1 = {
    		"<lgRel:associationPredicate associationName=\"uses\">"
    };
    
    
    // hasSubType
    private final String[] assoc2 = {"<lgRel:source",
            "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"",
            "xsi:schemaLocation=\"http://LexGrid.org/schema/2010/01/LexGrid/codingSchemes  http://LexGrid.org/schema/2010/01/LexGrid/codingSchemes.xsd\"",
            "sourceEntityCodeNamespace=\"Automobiles\" sourceEntityCode=\"005\">",
            "<lgRel:target targetEntityCode=\"Chrysler\" targetEntityCodeNamespace=\"ExpendableParts\"/>",
        "</lgRel:source>"};
    
    private final String[] assocPred2 = {
    		"<lgRel:associationPredicate associationName=\"hasSubtype\">"
    };
    
    private final String[] entity1 = {    
    		"<lgCon:entity",
    		"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"",
    		"xsi:schemaLocation=\"http://LexGrid.org/schema/2010/01/LexGrid/codingSchemes  http://LexGrid.org/schema/2010/01/LexGrid/codingSchemes.xsd\"",
    		"isActive=\"true\" status=\"asfd\" entityCode=\"A0001\" entityCodeNamespace=\"Automobiles\">",
    		"<lgCommon:entityDescription>Automobile</lgCommon:entityDescription>",
    		"<lgCon:entityType>concept</lgCon:entityType>",
    		"<lgCon:presentation propertyName=\"textualPresentation\"",
    		"propertyId=\"t1\" propertyType=\"presentation\"", 
    		"language=\"en\" isPreferred=\"true\" matchIfNoContext=\"true\">",
    		"<lgCommon:source>A0001</lgCommon:source>",
    		"<lgCommon:value dataType=\"textplain\">Automobile</lgCommon:value>",
    		"</lgCon:presentation>",
    		"<lgCon:definition propertyName=\"definition\" propertyId=\"p1\"",
    		"propertyType=\"definition\" language=\"en\" isPreferred=\"true\">",
    		"<lgCommon:source>A0001</lgCommon:source>",
    		"<lgCommon:value dataType=\"textplain\">An automobile</lgCommon:value>",
    		"</lgCon:definition>",
    		"</lgCon:entity>"
    };
        
    public void testLexGridExportRestrictToAssociation() {
    	Logger.log("RestrictToAssociation: testLexGridExportRestrictToAssociation: entry");
    	boolean rv = false;
		this.init();
		try {
			
			// check if coding scheme already exists
			rv = CodingSchemeChecker.exists(this.CS_AUTO2_URI, this.CS_AUTO2_VERSION);
			assertFalse("coding scheme " + this.CS_AUTO2_URI + "/" + this.CS_AUTO2_VERSION + " should not exist", rv);
			
			// import coding scheme
			rv = ImportHelper.importLgXml(this.INPUT_FILE_NAME_AUTO2);
			Assert.assertTrue("loaded LexGrid data from " + this.INPUT_FILE_NAME_AUTO2, rv);
			
			// create CNS and CNG objects
			CodedNodeGraph cng = CngFactory.crateCngRestrictToAssociation(this.CS_AUTO2_URI, this.CS_AUTO2_VERSION, "uses");
			CodedNodeSet cns = CnsFactory.crateCnsRestrictToAssociation(this.CS_AUTO2_URI, this.CS_AUTO2_VERSION);
			
			// export 
			rv = ExportHelper.export(
					this.CS_AUTO2_URI, 
					this.CS_AUTO2_VERSION, 
					this.outputDir.getOutputDirAsString(), 
					true, 
					cns, 
					cng);
			Assert.assertTrue("exported data to " + this.outputDir.getOutputDirAsString(), rv);
			
			//-----------------------------------------------------------------
			// verify verify verify verify verify verify verify verify
			//-----------------------------------------------------------------
			String fullyQualifiedOutputFile = ExportHelper.getExportedFileName(this.outputDir.getOutputDirAsString());
			
			rv = ExportDataVerifier.verifyOutFileHasContent(fullyQualifiedOutputFile, this.entity1);
			Assert.assertFalse("search string entity1 should not exist in file", rv);
			
			rv = ExportDataVerifier.verifyOutFileHasContent(fullyQualifiedOutputFile, this.assocPred1);
			Assert.assertTrue("search string assocPred1 should exist in file", rv);
			
			rv = ExportDataVerifier.verifyOutFileHasContent(fullyQualifiedOutputFile, this.assocPred2);
			Assert.assertFalse("search string assocPred2 should not exist in file", rv);			
			
			rv = ExportDataVerifier.verifyOutFileHasContent(fullyQualifiedOutputFile, this.assoc1);
			Assert.assertTrue("search string assoc1 should exist in file", rv);
			
			rv = ExportDataVerifier.verifyOutFileHasContent(fullyQualifiedOutputFile, this.assoc2);
			Assert.assertFalse("search string assoc1 should not exist in file", rv);
			
			// cleanup 
			TestCleaner.cleanUp(this.outputDir.getOutputDirAsString(), this.CS_AUTO2_URI, this.CS_AUTO2_VERSION);
			this.outputDir.deleteOutputDir();
			
		} catch (LBException e) {
			e.printStackTrace();
			Assert.fail("RestrictToAssociation: testLexGridExportRestrictToAssociation: caught exception: " + e.getMessage());
		}
		Logger.log("RestrictToAssociation: testLexGridExportRestrictToAssociation: exit");
    }
        
    public void init() {
    	Logger.log("RestrictToAssociation: init: entry");
    	
		// cleanup 
    	Logger.log("RestrictToAssociation: init: clean up any failed test artifacts");
    	this.outputDir = new OutputDir();
		TestCleaner.cleanUp(this.outputDir.getOutputDirAsString(), this.CS_AUTO2_URI, this.CS_AUTO2_VERSION);
    	
    	Logger.log("RestrictToAssociation: init: exit");
    }    
}