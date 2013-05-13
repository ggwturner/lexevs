package edu.mayo.informatics.lexgrid.convert.directConversions.medDRA;import java.io.FileNotFoundException;import java.io.FileReader;import java.lang.reflect.Field;import java.net.URI;import java.util.ArrayList;import java.util.List;import org.LexGrid.LexBIG.Utility.logging.LgMessageDirectorIF;import org.LexGrid.codingSchemes.CodingScheme;import org.LexGrid.commonTypes.EntityDescription;import org.LexGrid.commonTypes.Text;import org.LexGrid.commonTypes.types.EntityTypes;import org.LexGrid.concepts.Entities;import org.LexGrid.concepts.Entity;import org.LexGrid.concepts.Presentation;import org.LexGrid.naming.Mappings;import org.LexGrid.naming.SupportedCodingScheme;import org.LexGrid.naming.SupportedHierarchy;import org.LexGrid.naming.SupportedLanguage;import org.lexevs.logging.messaging.impl.CachingMessageDirectorImpl;import au.com.bytecode.opencsv.CSVReader;import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;import au.com.bytecode.opencsv.bean.CsvToBean;import edu.mayo.informatics.lexgrid.convert.directConversions.medDRA.Data.Database;import edu.mayo.informatics.lexgrid.convert.directConversions.medDRA.Data.DatabaseRecord;import edu.mayo.informatics.lexgrid.convert.directConversions.medDRA.Data.MedDRARecord_meddra_release;import edu.mayo.informatics.lexgrid.convert.directConversions.medDRA.Data.MedDRA_Metadata;public class MedDRAMapToLexGrid {    private static MedDRA_Metadata [] meddraMetaData = MedDRA_Metadata.values();    private LgMessageDirectorIF messages_;    private URI medDRASourceDir;    private Database meddraDatabase;        public MedDRAMapToLexGrid(URI inFileName, LgMessageDirectorIF lg_messages) {        this.messages_ = new CachingMessageDirectorImpl(lg_messages);        this.medDRASourceDir = inFileName;        meddraDatabase = new Database();    }    public void readMedDRAFiles() {        try{            this.createDatabase();        } catch (Exception e) {            messages_.error("Failed to read MedDRA data files, check connection values");            e.printStackTrace();        }    }        public void mapToLexGrid(CodingScheme csclass){        try {            loadCodingScheme(csclass);            loadConcepts(csclass);//            loadRelations(csclass, internalIdToEntityHash);        } catch (Exception e) {            messages_.error("Failed to connect to RIM Database, check connection values");            e.printStackTrace();        }                     messages_.info("Mapping completed, returning to loader");    }    private void loadConcepts(CodingScheme csclass) {        messages_.info("Loading concepts");        String entityCodeNamespace = csclass.getCodingSchemeName();                Entities concepts = csclass.getEntities();        if (concepts == null) {            concepts = new Entities();            csclass.setEntities(concepts);        }                String table;                messages_.info("Loading System Organ Classes");        table = MedDRA_Metadata.SYSTEM_ORGAN_CLASSES.tablename();        this.loadTable(table, entityCodeNamespace, csclass);                        messages_.info("Loading High Level Group Terms");        table = MedDRA_Metadata.HIGH_LEVEL_GROUP_TERMS.tablename();        this.loadTable(table, entityCodeNamespace, csclass);                messages_.info("Loading High Level Terms");        table = MedDRA_Metadata.HIGH_LEVEL_TERMS.tablename();        this.loadTable(table, entityCodeNamespace, csclass);                        messages_.info("Loading Preferred Terms");        table = MedDRA_Metadata.PREF_TERMS.tablename();        this.loadTable(table, entityCodeNamespace, csclass);                        messages_.info("Loading Low Level Terms");        table = MedDRA_Metadata.LOW_LEVEL_TERMS.tablename();        this.loadTable(table, entityCodeNamespace, csclass);            }    private void loadTable(String table, String entityCodeNamespace, CodingScheme csclass) {        List<DatabaseRecord> records = meddraDatabase.get(table);        Entities entities = csclass.getEntities();        for(DatabaseRecord record : records){            Entity newEntity = new Entity();            newEntity.setEntityCode(record.getCode());            newEntity.setEntityType(new String[] { EntityTypes.CONCEPT.toString() });            newEntity.setEntityCodeNamespace(entityCodeNamespace);            newEntity.setIsActive(true);                        List<Presentation> presentations = record.getPresentations();            newEntity.setPresentation(presentations);                        entities.addEntity(newEntity);        }            }//  private void loadRelations(CodingScheme csclass, Hashtable<Integer, Entity> internalIdToEntityHash2) {//  messages_.info("Loading all relations properties");//  //}    private void loadCodingScheme(CodingScheme csclass) {        try {            messages_.info("Loading coding scheme information");            // Determine which bean contains coding scheme info            String name = MedDRA2LGConstants.DEFAULT_NAME;            csclass.setCodingSchemeName(name);            csclass.setCodingSchemeURI(MedDRA2LGConstants.DEFAULT_URN);            csclass.setFormalName(MedDRA2LGConstants.DEFAULT_FORMAL_NAME);                        EntityDescription enDesc = new EntityDescription();            enDesc.setContent(MedDRA2LGConstants.DEFAULT_ENTITY_DESCRIPTION);            csclass.setEntityDescription(enDesc);                        csclass.setDefaultLanguage(MedDRA2LGConstants.DEFAULT_LANGUAGE_EN);            String version = this.getVersion();            csclass.setRepresentsVersion(version);                        Text txt = new Text();            txt.setContent(MedDRA2LGConstants.DEFAULT_COPYRIGHT);            csclass.setCopyright(txt);            csclass.setMappings(new Mappings());                        // Add SupportedCodingScheme and SupportedLanguage Mappings            SupportedCodingScheme scs = new SupportedCodingScheme();            scs.setLocalId(csclass.getCodingSchemeName());            scs.setUri(csclass.getCodingSchemeURI());            csclass.getMappings().addSupportedCodingScheme(scs);            SupportedLanguage lang = new SupportedLanguage();            lang.setLocalId(MedDRA2LGConstants.DEFAULT_LANGUAGE_EN);            csclass.getMappings().addSupportedLanguage(lang);                        // Add SupportedHierarchy Mappings            SupportedHierarchy hierarchy = new SupportedHierarchy();            hierarchy.setLocalId(MedDRA2LGConstants.ASSOCIATION_HAS_SUBTYPE);            ArrayList<String> list = new ArrayList<String>();            list.add(MedDRA2LGConstants.ASSOCIATION_HAS_SUBTYPE);            hierarchy.setAssociationNames(list);            hierarchy.setRootCode(MedDRA2LGConstants.DEFAULT_ROOT_NODE);            hierarchy.setIsForwardNavigable(true);            csclass.getMappings().addSupportedHierarchy(hierarchy);                        hierarchy = new SupportedHierarchy();            hierarchy.setLocalId(MedDRA2LGConstants.ASSOCIATION_IS_A);            list = new ArrayList<String>();            list.add(MedDRA2LGConstants.ASSOCIATION_HAS_SUBTYPE);            hierarchy.setAssociationNames(list);            hierarchy.setRootCode(MedDRA2LGConstants.DEFAULT_ROOT_NODE);            hierarchy.setIsForwardNavigable(true);            csclass.getMappings().addSupportedHierarchy(hierarchy);               } catch (Exception e) {            messages_.error("Failed while preparing MedDRA Coding Scheme Class", e);            e.printStackTrace();        }     }    private String getVersion() {        String version = null;        List<DatabaseRecord> records = meddraDatabase.get(MedDRA_Metadata.RELEASE.tablename());        if(records.size() == 1){            version = ((MedDRARecord_meddra_release) records.get(0)).getVersion();        }        return version;    }    public Database createDatabase(){        String input;                for(int i=0; i < meddraMetaData.length; i++){            input = medDRASourceDir.getPath() + meddraMetaData[i].filename();            try {                FileReader fileReader = new FileReader(input);                CSVReader reader = new CSVReader(fileReader, '$');                ColumnPositionMappingStrategy<DatabaseRecord> strat = new ColumnPositionMappingStrategy<DatabaseRecord>();                strat.setType(meddraMetaData[i].classname());                String[] columns = getFields(meddraMetaData[i].classname());                             strat.setColumnMapping(columns);                    CsvToBean<DatabaseRecord> csv = new CsvToBean<DatabaseRecord>();                List<DatabaseRecord> list = csv.parse(strat, reader);                meddraDatabase.add(meddraMetaData[i].tablename(), list);            } catch (FileNotFoundException e) {                e.printStackTrace();            }        }                return meddraDatabase;    }    private String[] getFields(Class<?> class1) {        Field [] fields = class1.getDeclaredFields();           String [] fieldnames = new String[fields.length - 1];                for(int i=1; i < fields.length; i++){            fieldnames[i-1] = fields[i].getName();        }        return fieldnames;    }}