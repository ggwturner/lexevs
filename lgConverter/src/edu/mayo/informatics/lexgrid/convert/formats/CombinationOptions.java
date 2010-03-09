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
package edu.mayo.informatics.lexgrid.convert.formats;

import edu.mayo.informatics.lexgrid.convert.formats.inputFormats.LexGridLDAP;
import edu.mayo.informatics.lexgrid.convert.formats.inputFormats.LexGridSQL;
import edu.mayo.informatics.lexgrid.convert.formats.inputFormats.LexGridSQLLite;
import edu.mayo.informatics.lexgrid.convert.formats.inputFormats.NCIMetaThesaurusSQL;
import edu.mayo.informatics.lexgrid.convert.formats.inputFormats.UMLSSQL;
import edu.mayo.informatics.lexgrid.convert.formats.outputFormats.LexGridLDAPOut;
import edu.mayo.informatics.lexgrid.convert.formats.outputFormats.LexGridSQLLiteOut;
import edu.mayo.informatics.lexgrid.convert.formats.outputFormats.LexGridSQLOut;
import edu.mayo.informatics.lexgrid.convert.utility.Constants;

/**
 * A place to define options which apply to a specific input/ouput combination.
 * 
 * @author <A HREF="mailto:armbrust.daniel@mayo.edu">Dan Armbrust</A>
 * @author <A HREF="mailto:johnson.thomas@mayo.edu">Thomas Johnson</A>
 * @version subversion $Revision: 7198 $ checked in on $Date: 2008-02-15
 *          18:08:36 +0000 (Fri, 15 Feb 2008) $
 */
public class CombinationOptions {
    public static Option[] getOptionsForCombination(InputFormatInterface inputFormat, OutputFormatInterface outputFormat) {
        if (inputFormat == null || outputFormat == null) {
            return new Option[] {};
        }
        if (inputFormat.getDescription().equals(LexGridLDAP.description)) {
            if (outputFormat.getDescription().equals(LexGridSQLLiteOut.description)) {
                // custom LDAP -> sql lite converter.
                return new Option[] { new Option(Option.ENFORCE_INTEGRITY, new Boolean(true)) };

            }
        } else if (inputFormat.getDescription().equals(NCIMetaThesaurusSQL.description)) {
            // metathesaurus input format
            if (outputFormat.getDescription().equals(LexGridSQLOut.description)) {
                // custom nci metathesaurus -> sql converter
                return new Option[] { new Option(Option.ENFORCE_INTEGRITY, new Boolean(true)),
                        new Option(Option.SQL_FETCH_SIZE, new String(Constants.mySqlBatchSize + "")),
                        new Option(Option.ROOT_RECALC, new Boolean(false)), };
            }
        } else if (inputFormat.getDescription().equals(LexGridSQLLite.description)) {
            // LexGridSQLLite inputformat
            if (outputFormat.getDescription().equals(LexGridLDAPOut.description)) {
                // custom sql lite to ldap converter
                return new Option[] { new Option(Option.FAIL_ON_ERROR, new Boolean(true)) };
            }
        } else if (inputFormat.getDescription().equals(LexGridSQL.description)) {
            // LexGridSQL inputformat
            if (outputFormat.getDescription().equals(LexGridLDAPOut.description)) {
                // custom sql to ldap converter
                return new Option[] { new Option(Option.FAIL_ON_ERROR, new Boolean(true)),
                        new Option(Option.DO_WITH_EMF, new Boolean(false)) };
            } else if (outputFormat.getDescription().equals(LexGridSQLLiteOut.description)) {
                // custom SQL -> sql lite converter.
                return new Option[] { new Option(Option.ENFORCE_INTEGRITY, new Boolean(true)) };
            }
        } else if (inputFormat.getDescription().equals(UMLSSQL.description)) {
            // UMLS SQL input
            if (outputFormat.getDescription().equals(LexGridSQLOut.description)) {
                // custom UMLS to SQL converter
                return new Option[] { new Option(Option.ENFORCE_INTEGRITY, new Boolean(true)),
                        new Option(Option.SQL_FETCH_SIZE, new String(Constants.mySqlBatchSize + "")),
                        new Option(Option.MRHIER_OPT, new Integer(0)) };
            }
        }

        return new Option[] {};
    }

    public static Option[] getEMFSpecificOptions(InputFormatInterface inputFormat, OutputFormatInterface outputFormat,
            OptionHolder optionsSoFar) {
        if (ConversionLauncher.willBeDoneWithEMF(inputFormat, outputFormat, optionsSoFar)) {
            // if it will be done with EMF....

            if (inputFormat.getDescription().equals(LexGridSQL.description)
                    || outputFormat.getDescription().equals(LexGridSQLOut.description)) {
                return new Option[] { new Option(Option.FAIL_ON_ERROR, new Boolean(false)) };
            }
        }
        return new Option[] {};
    }

}