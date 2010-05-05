--   -------------------------------------------------- 
--   Generated by Enterprise Architect Version 7.5.850
--   Created On : Thursday, 22 April, 2010 
--   DBMS       : Oracle 
--   -------------------------------------------------- 




--  Create Tables 
CREATE TABLE @PREFIX@h_associationEntity
(
	associationEntityGuid     VARCHAR2(36) NOT NULL,
	entityGuid                VARCHAR2(36) NOT NULL,
	forwardName               VARCHAR2(100),    --  The name or role that the "from" entity plays with respect to the "to" entry.  Should be phrased in terms of the default language of the association and imply direction. 
	reverseName               VARCHAR2(100),    --  How the association should be represented when reading from target to source 
	isNavigable               CHAR(1),    --  True means that the reverse direction of the associaton is "navigable", meaning that it is makes sense to represent the target to source side of the association. 
	isTransitive              CHAR(1),    --  True means that association is transitive ( r(a,b), r(b,c) -> r(a,c)). False means not transitive. If absent, transitivity is unknown or not applicable. 
	isTranslationAssociation  CHAR(1),    --  If true, the association set represents a translation mapping from source to the target. 
	entryStateGuid            VARCHAR2(36) NOT NULL
)
;

COMMENT ON TABLE @PREFIX@h_associationEntity IS 'The definition of a binary relation that can be asserted between two entities or an entity and a property. The entityType for the class concept must be "associationEntity".'
;
COMMENT ON COLUMN @PREFIX@h_associationEntity.forwardName               IS 'The name or role that the "from" entity plays with respect to the "to" entry.  Should be phrased in terms of the default language of the association and imply direction.'
;
COMMENT ON COLUMN @PREFIX@h_associationEntity.reverseName               IS 'How the association should be represented when reading from target to source'
;
COMMENT ON COLUMN @PREFIX@h_associationEntity.isNavigable               IS 'True means that the reverse direction of the associaton is "navigable", meaning that it is makes sense to represent the target to source side of the association.'
;
COMMENT ON COLUMN @PREFIX@h_associationEntity.isTransitive              IS 'True means that association is transitive ( r(a,b), r(b,c) -> r(a,c)). False means not transitive. If absent, transitivity is unknown or not applicable.'
;
COMMENT ON COLUMN @PREFIX@h_associationEntity.isTranslationAssociation  IS 'If true, the association set represents a translation mapping from source to the target.'
;

CREATE TABLE @PREFIX@h_codingScheme
(
	codingSchemeGuid   VARCHAR2(36) NOT NULL,
	codingSchemeName   VARCHAR2(50) NOT NULL,    --  a unique identifier for the coding scheme within a particular context. Usually a mnemonic 
	codingSchemeURI    VARCHAR2(250) NOT NULL,    --  the coding scheme URI 
	representsVersion  VARCHAR2(50) NOT NULL,    --  The system release currently represented by the coding scheme 
	formalName         VARCHAR2(250),    --  Official name of the coding scheme. 
	defaultLanguage    VARCHAR2(32),    --  language to use if not otherwise specified 
	approxNumConcepts  NUMBER(18),    --  Hint for the system about the approximate number of concepts in the scheme (optional) 
	description        CLOB,
	copyright          CLOB,    --  Copyright notice (optional) 
	isActive           CHAR(1) DEFAULT 1,
	owner              VARCHAR2(250),
	status             VARCHAR2(50),
	effectiveDate      TIMESTAMP,
	expirationDate     TIMESTAMP,
	releaseGuid        VARCHAR2(36),    --  The globally unique name of a system release. 
	entryStateGuid     VARCHAR2(36) NOT NULL    --  A unique key to entryState table. 
)
;

COMMENT ON COLUMN @PREFIX@h_codingScheme.codingSchemeName   IS 'a unique identifier for the coding scheme within a particular context. Usually a mnemonic'
;
COMMENT ON COLUMN @PREFIX@h_codingScheme.codingSchemeURI    IS 'the coding scheme URI'
;
COMMENT ON COLUMN @PREFIX@h_codingScheme.representsVersion  IS 'The system release currently represented by the coding scheme'
;
COMMENT ON COLUMN @PREFIX@h_codingScheme.formalName         IS 'Official name of the coding scheme.'
;
COMMENT ON COLUMN @PREFIX@h_codingScheme.defaultLanguage    IS 'language to use if not otherwise specified'
;
COMMENT ON COLUMN @PREFIX@h_codingScheme.approxNumConcepts  IS 'Hint for the system about the approximate number of concepts in the scheme (optional)'
;
COMMENT ON COLUMN @PREFIX@h_codingScheme.copyright          IS 'Copyright notice (optional)'
;
COMMENT ON COLUMN @PREFIX@h_codingScheme.releaseGuid        IS 'The globally unique name of a system release.'
;
COMMENT ON COLUMN @PREFIX@h_codingScheme.entryStateGuid     IS 'A unique key to entryState table.'
;

CREATE TABLE @PREFIX@h_csMultiAttrib
(
	csMultiAttribGuid  VARCHAR2(36) NOT NULL,
	codingSchemeGuid   VARCHAR2(36) NOT NULL,
	attributeType      VARCHAR2(30) NOT NULL,    --  localName and source 
	attributeValue     VARCHAR2(250) NOT NULL,    --  The localName and source. 
	subRef             VARCHAR2(250),
	role               VARCHAR2(250),
	entryStateGuid     VARCHAR2(36) NOT NULL
)
;

COMMENT ON COLUMN @PREFIX@h_csMultiAttrib.attributeType      IS 'localName and source'
;
COMMENT ON COLUMN @PREFIX@h_csMultiAttrib.attributeValue     IS 'The localName and source.'
;

CREATE TABLE @PREFIX@h_entity
(
	entityGuid           VARCHAR2(36) NOT NULL,
	codingSchemeGuid     VARCHAR2(36) NOT NULL,
	entityCode           VARCHAR2(200) NOT NULL,    --  unique entity code within coding system 
	entityCodeNamespace  VARCHAR2(50) NOT NULL,    --  The local name of the namespace for the entrycode.  Defaults to the codingSchemeName of the containing codedEntry 
	isDefined            CHAR(1),    --  TRUE means that the concept has at least one set of necessary and sufficient condition. 
	isAnonymous          CHAR(1),    --  True means that this node doesn't have an actual code in the code system 
	description          CLOB,
	isActive             CHAR(1) DEFAULT 1,
	owner                VARCHAR2(250),
	status               VARCHAR2(50),
	effectiveDate        TIMESTAMP,
	expirationDate       TIMESTAMP,
	entryStateGuid       VARCHAR2(36) NOT NULL
)
;

COMMENT ON COLUMN @PREFIX@h_entity.entityCode           IS 'unique entity code within coding system'
;
COMMENT ON COLUMN @PREFIX@h_entity.entityCodeNamespace  IS 'The local name of the namespace for the entrycode.  Defaults to the codingSchemeName of the containing codedEntry'
;
COMMENT ON COLUMN @PREFIX@h_entity.isDefined            IS 'TRUE means that the concept has at least one set of necessary and sufficient condition.'
;
COMMENT ON COLUMN @PREFIX@h_entity.isAnonymous          IS 'True means that this node doesn''t have an actual code in the code system'
;

CREATE TABLE @PREFIX@h_entityAssnQuals
(
	entityAssnQualsGuid  VARCHAR2(36) NOT NULL,
	referenceGuid        VARCHAR2(36) NOT NULL,
	qualifierName        VARCHAR2(50) NOT NULL,    --  The local name of a qualifier. Must be in supportedAssociationQualifier. 
	qualifierValue       VARCHAR2(250) NOT NULL,    --  The qualifier value 
	entryStateGuid       VARCHAR2(36) NOT NULL
)
;

COMMENT ON COLUMN @PREFIX@h_entityAssnQuals.qualifierName        IS 'The local name of a qualifier. Must be in supportedAssociationQualifier.'
;
COMMENT ON COLUMN @PREFIX@h_entityAssnQuals.qualifierValue       IS 'The qualifier value'
;

CREATE TABLE @PREFIX@h_entityAssnsToData
(
	entityAssnsDataGuid        VARCHAR2(36) NOT NULL,
	associationPredicateGuid   VARCHAR2(36) NOT NULL,
	sourceEntityCode           VARCHAR2(200) NOT NULL,
	sourceEntityCodeNamespace  VARCHAR2(50) NOT NULL,
	associationInstanceId      VARCHAR2(50),    --  Identifier that uniqely names this entry within the context of the associationInstance. 
	isDefining                 CHAR(1),
	isInferred                 CHAR(1),
	dataValue                  CLOB,    --  associated data value 
	isActive                   CHAR(1),
	owner                      VARCHAR2(250),
	status                     VARCHAR2(50),
	effectiveDate              TIMESTAMP,
	expirationDate             TIMESTAMP,
	entryStateGuid             VARCHAR2(36) NOT NULL    --  A unique key to entryState table. 
)
;

COMMENT ON COLUMN @PREFIX@h_entityAssnsToData.associationInstanceId      IS 'Identifier that uniqely names this entry within the context of the associationInstance.'
;
COMMENT ON COLUMN @PREFIX@h_entityAssnsToData.dataValue                  IS 'associated data value'
;
COMMENT ON COLUMN @PREFIX@h_entityAssnsToData.entryStateGuid             IS 'A unique key to entryState table.'
;

CREATE TABLE @PREFIX@h_entityAssnsToEntity
(
	entityAssnsGuid            VARCHAR2(36) NOT NULL,
	associationPredicateGuid   VARCHAR2(36) NOT NULL,
	sourceEntityCode           VARCHAR2(200) NOT NULL,
	sourceEntityCodeNamespace  VARCHAR2(50) NOT NULL,
	targetEntityCode           VARCHAR2(200) NOT NULL,
	targetEntityCodeNamespace  VARCHAR2(50) NOT NULL,
	associationInstanceId      VARCHAR2(50),    --  a unique key to identify association instance.  
	isDefining                 CHAR(1),
	isInferred                 CHAR(1),
	isActive                   CHAR(1),
	owner                      VARCHAR2(250),
	status                     VARCHAR2(50),
	effectiveDate              TIMESTAMP,
	expirationDate             TIMESTAMP,
	entryStateGuid             VARCHAR2(36) NOT NULL    --  A unique key to entryState table. 
)
;

COMMENT ON COLUMN @PREFIX@h_entityAssnsToEntity.associationInstanceId      IS 'a unique key to identify association instance. '
;
COMMENT ON COLUMN @PREFIX@h_entityAssnsToEntity.entryStateGuid             IS 'A unique key to entryState table.'
;

CREATE TABLE @PREFIX@h_property
(
	propertyGuid          VARCHAR2(36) NOT NULL,
	referenceGuid         VARCHAR2(36) NOT NULL,
	referenceType         VARCHAR2(50) NOT NULL,
	propertyId            VARCHAR2(50),    --  The unique name of the property within the context of the coded entry 
	propertyType          VARCHAR2(15),    --  Used to differentiate the objects stored in this table.  Can be - 'presentation', 'definition', 'comment', 'instruction', 'property' 
	propertyName          VARCHAR2(250) NOT NULL,    --  The local name or tag of the property. Must be in supportedProperty 
	language              VARCHAR2(32),    --  The written or spoken language of the property. Must be in supportedLanguage. Default- defaultLanguage for coding Scheme 
	format                VARCHAR2(50),
	isPreferred           CHAR(1),    --  True means that, *if* the text meets the selection criteria, it should be the preferred form. 
	matchIfNoContext      CHAR(1),    --  True if the list entry matches if no context is supplied 
	degreeOfFidelity      VARCHAR2(50),    --  How closely a term approximates the meaning of a concept 
	representationalForm  VARCHAR2(50),    --  How the term represents the concept (abbrev, acronym, etc.) - Must be in supportedRepresentationalForm 
	propertyValue         CLOB NOT NULL,    --  additional tag and associated value that further identifies or describes the intent of the entity code. 
	isActive              CHAR(1),
	owner                 VARCHAR2(250),
	status                VARCHAR2(50),
	effectiveDate         TIMESTAMP,
	expirationDate        TIMESTAMP,
	entryStateGuid        VARCHAR2(36) NOT NULL
)
;

COMMENT ON COLUMN @PREFIX@h_property.propertyId            IS 'The unique name of the property within the context of the coded entry'
;
COMMENT ON COLUMN @PREFIX@h_property.propertyType          IS 'Used to differentiate the objects stored in this table.  Can be - ''presentation'', ''definition'', ''comment'', ''instruction'', ''property'''
;
COMMENT ON COLUMN @PREFIX@h_property.propertyName          IS 'The local name or tag of the property. Must be in supportedProperty'
;
COMMENT ON COLUMN @PREFIX@h_property.language              IS 'The written or spoken language of the property. Must be in supportedLanguage. Default- defaultLanguage for coding Scheme'
;
COMMENT ON COLUMN @PREFIX@h_property.isPreferred           IS 'True means that, *if* the text meets the selection criteria, it should be the preferred form.'
;
COMMENT ON COLUMN @PREFIX@h_property.matchIfNoContext      IS 'True if the list entry matches if no context is supplied'
;
COMMENT ON COLUMN @PREFIX@h_property.degreeOfFidelity      IS 'How closely a term approximates the meaning of a concept'
;
COMMENT ON COLUMN @PREFIX@h_property.representationalForm  IS 'How the term represents the concept (abbrev, acronym, etc.) - Must be in supportedRepresentationalForm'
;
COMMENT ON COLUMN @PREFIX@h_property.propertyValue         IS 'additional tag and associated value that further identifies or describes the intent of the entity code.'
;

CREATE TABLE @PREFIX@h_propertyLinks
(
	propertyLinksGuid   VARCHAR2(36) NOT NULL,    --  Global unique identifier for a given property link. 
	sourcePropertyGuid  VARCHAR2(36) NOT NULL,    --  Foreign key to the source property guid. (The identifier of the first property in the link.) 
	link                VARCHAR2(250) NOT NULL,    --  The local name of the type of link between properties.  (Examples include acronymFor, abbreviationOf, spellingVariantOf, etc. Must be in supportedPropertyLink) propertyLink must match a local id of a supportedPropertyLink in the corresponding mapping section. 
	targetPropertyGuid  VARCHAR2(36) NOT NULL,    --  Foreign key to the target property guid. (The identifier of the second property in the link.) 
	entryStateGuid      VARCHAR2(36) NOT NULL
)
;

COMMENT ON TABLE @PREFIX@h_propertyLinks IS 'Table holds the link between two properties.'
;
COMMENT ON COLUMN @PREFIX@h_propertyLinks.propertyLinksGuid   IS 'Global unique identifier for a given property link.'
;
COMMENT ON COLUMN @PREFIX@h_propertyLinks.sourcePropertyGuid  IS 'Foreign key to the source property guid. (The identifier of the first property in the link.)'
;
COMMENT ON COLUMN @PREFIX@h_propertyLinks.link                IS 'The local name of the type of link between properties.  (Examples include acronymFor, abbreviationOf, spellingVariantOf, etc. Must be in supportedPropertyLink) propertyLink must match a local id of a supportedPropertyLink in the corresponding mapping section.'
;
COMMENT ON COLUMN @PREFIX@h_propertyLinks.targetPropertyGuid  IS 'Foreign key to the target property guid. (The identifier of the second property in the link.)'
;

CREATE TABLE @PREFIX@h_propertyMultiAttrib
(
	propMultiAttribGuid  VARCHAR2(36) NOT NULL,
	propertyGuid         VARCHAR2(36) NOT NULL,
	attributeType        VARCHAR2(30) NOT NULL,    --  source, usageContext, qualifier 
	attributeId          VARCHAR2(50),    --  attributeId 
	attributeValue       VARCHAR2(250),    --  The source, usageContext, or qualifier name - must come from a SupportedXXX value 
	subRef               VARCHAR2(250),
	role                 VARCHAR2(250),
	qualifierType 	     VARCHAR2(250),    -- Qualifier type of a property. 
	entryStateGuid       VARCHAR2(36) NOT NULL
)
;

COMMENT ON COLUMN @PREFIX@h_propertyMultiAttrib.attributeType        IS 'source, usageContext, qualifier'
;
COMMENT ON COLUMN @PREFIX@h_propertyMultiAttrib.attributeId          IS 'attributeId'
;
COMMENT ON COLUMN @PREFIX@h_propertyMultiAttrib.attributeValue       IS 'The source, usageContext, or qualifier name - must come from a SupportedXXX value'
;

CREATE TABLE @PREFIX@h_relation
(
	relationGuid               VARCHAR2(36) NOT NULL,
	codingSchemeGuid           VARCHAR2(36) NOT NULL,
	containerName              VARCHAR2(50) NOT NULL,    --  The name of the collection of relations. Must be in supportedContainerName. 
	isMapping                  CHAR(1),    --  An indicator that this set of associations represents a mapping between two terminologies if value is true. 
	sourceCodingScheme         VARCHAR2(50),    --  If not supplied in associationInstance, this is the default source codingScheme. 
	sourceCodingSchemeVersion  VARCHAR2(50),
	targetCodingScheme         VARCHAR2(50),    --  If not supplied in associationInstance, this is the default target codingScheme. 
	targetCodingSchemeVersion  VARCHAR2(50),
	description                CLOB,
	isActive                   CHAR(1),
	owner                      VARCHAR2(250),
	status                     VARCHAR2(50),
	effectiveDate              TIMESTAMP,
	expirationDate             TIMESTAMP,
	entryStateGuid             VARCHAR2(36) NOT NULL
)
;

COMMENT ON COLUMN @PREFIX@h_relation.containerName              IS 'The name of the collection of relations. Must be in supportedContainerName.'
;
COMMENT ON COLUMN @PREFIX@h_relation.isMapping                  IS 'An indicator that this set of associations represents a mapping between two terminologies if value is true.'
;
COMMENT ON COLUMN @PREFIX@h_relation.sourceCodingScheme         IS 'If not supplied in associationInstance, this is the default source codingScheme.'
;
COMMENT ON COLUMN @PREFIX@h_relation.targetCodingScheme         IS 'If not supplied in associationInstance, this is the default target codingScheme.'
;


--  Create Primary Key Constraints 
ALTER TABLE @PREFIX@h_associationEntity ADD CONSTRAINT PK_h_associationEntity 
	PRIMARY KEY (entryStateGuid)
;

ALTER TABLE @PREFIX@h_codingScheme ADD CONSTRAINT PK_h_codingScheme 
	PRIMARY KEY (entryStateGuid)
;

ALTER TABLE @PREFIX@h_csMultiAttrib ADD CONSTRAINT PK_h_csMultiAttrib 
	PRIMARY KEY (entryStateGuid)
;

ALTER TABLE @PREFIX@h_entity ADD CONSTRAINT PK_h_entity 
	PRIMARY KEY (entryStateGuid)
;

ALTER TABLE @PREFIX@h_entityAssnQuals ADD CONSTRAINT PK_h_entityAssnQuals 
	PRIMARY KEY (entryStateGuid)
;

ALTER TABLE @PREFIX@h_entityAssnsToData ADD CONSTRAINT PK_h_entityAssnsToData 
	PRIMARY KEY (entryStateGuid)
;

ALTER TABLE @PREFIX@h_entityAssnsToEntity ADD CONSTRAINT PK_h_entityAssnsToEntity 
	PRIMARY KEY (entryStateGuid)
;

ALTER TABLE @PREFIX@h_property ADD CONSTRAINT PK_h_property 
	PRIMARY KEY (entryStateGuid)
;

ALTER TABLE @PREFIX@h_propertyLinks ADD CONSTRAINT PK_h_propertyLinks 
	PRIMARY KEY (entryStateGuid)
;

ALTER TABLE @PREFIX@h_propertyMultiAttrib ADD CONSTRAINT PK_h_propertyMultiAttrib 
	PRIMARY KEY (entryStateGuid)
;

ALTER TABLE @PREFIX@h_relation ADD CONSTRAINT PK_h_relation 
	PRIMARY KEY (entryStateGuid)
;


--  Create Indexes 
CREATE INDEX idx_h_codingSchemeURI ON @PREFIX@h_codingScheme
(codingSchemeURI ASC)
;

CREATE INDEX idx_h_csMultiAttrib ON @PREFIX@h_csMultiAttrib
(codingSchemeGuid ASC, attributeType ASC)
;

CREATE INDEX idx_h_entity ON @PREFIX@h_entity
(codingSchemeGuid ASC, entityCode ASC)
;

CREATE INDEX idx_h_entityNS ON @PREFIX@h_entity
(codingSchemeGuid ASC, entityCode ASC, entityCodeNamespace ASC)
;

CREATE INDEX idx_h_entAsToData_source ON @PREFIX@h_entityAssnsToData
(associationPredicateGuid ASC, sourceEntityCode ASC)
;

CREATE INDEX idx_h_entAsToEnt_source ON @PREFIX@h_entityAssnsToEntity
(associationPredicateGuid ASC, sourceEntityCode ASC)
;

CREATE INDEX idx_h_entAsToEnt_sourceNS ON @PREFIX@h_entityAssnsToEntity
(associationPredicateGuid ASC, sourceEntityCode ASC, sourceEntityCodeNamespace ASC)
;

CREATE INDEX idx_h_entAsToEnt_target ON @PREFIX@h_entityAssnsToEntity
(associationPredicateGuid ASC, targetEntityCode ASC)
;

CREATE INDEX idx_h_entAsToEnt_targetNS ON @PREFIX@h_entityAssnsToEntity
(associationPredicateGuid ASC, targetEntityCode ASC, targetEntityCodeNamespace ASC)
;

CREATE INDEX idx_h_referenceGuid ON @PREFIX@h_property
(referenceGuid ASC)
;

CREATE INDEX idx_h_sourcePropertyGuid ON @PREFIX@h_propertyLinks
(sourcePropertyGuid ASC)
;

CREATE INDEX idx_h_targetPropertyGuid ON @PREFIX@h_propertyLinks
(targetPropertyGuid ASC)
;

CREATE INDEX idx_h_propertyMultiAttrib ON @PREFIX@h_propertyMultiAttrib
(propertyGuid ASC)
;
