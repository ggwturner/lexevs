package edu.mayo.informatics.lexgrid.convert.directConversions.medDRA;import java.net.URI;import org.LexGrid.LexBIG.Utility.logging.CachingMessageDirectorIF;import org.LexGrid.codingSchemes.CodingScheme;import org.lexevs.logging.messaging.impl.CachingMessageDirectorImpl;import org.LexGrid.LexBIG.Utility.logging.LgMessageDirectorIF;public class MedDRA2LGMain {    private LgMessageDirectorIF messages;    public CodingScheme map(URI inFileName, LgMessageDirectorIF lg_messages)            throws Exception {        this.messages = new CachingMessageDirectorImpl(lg_messages);        if (inFileName == null) {            messages.fatalAndThrowException("Error! Input file name is null.");        }        CachingMessageDirectorIF logger = new CachingMessageDirectorImpl(messages);        CodingScheme csclass = null;        try{            csclass = new CodingScheme();            MedDRAMapToLexGrid medDRAMap = new MedDRAMapToLexGrid(inFileName, messages);            medDRAMap.readMedDRAFiles();            medDRAMap.mapToLexGrid(csclass);            logger.info("Processing DONE!!");        } catch (Exception e) {            messages.fatalAndThrowException("Failed in MedDRA Mapping...", e);        }                    return csclass;    }    public static void main(String args[]) {    }        }