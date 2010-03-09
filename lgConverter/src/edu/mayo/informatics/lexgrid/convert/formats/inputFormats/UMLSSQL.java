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
package edu.mayo.informatics.lexgrid.convert.formats.inputFormats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.LexGrid.util.sql.sqlReconnect.WrappedConnection;
import org.apache.log4j.Logger;

import edu.mayo.informatics.lexgrid.convert.exceptions.ConnectionFailure;
import edu.mayo.informatics.lexgrid.convert.exceptions.UnexpectedError;
import edu.mayo.informatics.lexgrid.convert.formats.InputFormatInterface;
import edu.mayo.informatics.lexgrid.convert.formats.Option;
import edu.mayo.informatics.lexgrid.convert.formats.baseFormats.SQLBase;
import edu.mayo.informatics.lexgrid.convert.formats.outputFormats.LexGridSQLOut;
import edu.mayo.informatics.lexgrid.convert.utility.StringComparator;

/**
 * Details for reading from a UMLS server.
 * 
 * @author <A HREF="mailto:armbrust.daniel@mayo.edu">Dan Armbrust</A>
 * @version subversion $Revision: 7198 $ checked in on $Date: 2008-02-15
 *          18:08:36 +0000 (Fri, 15 Feb 2008) $
 */
public class UMLSSQL extends SQLBase implements InputFormatInterface {
    public static final String description = "UMLS SQL Database";
    private static Logger log = Logger.getLogger("convert.gui");

    public UMLSSQL(String username, String password, String server, String driver) {
        this.username = username;
        this.password = password;
        this.server = server;
        this.driver = driver;
    }

    public UMLSSQL() {

    }

    public String getConnectionSummary() {
        return getConnectionSummary(description);
    }

    public String getDescription() {
        return description;
    }

    public Option[] getOptions() {
        return new Option[] {};
    }

    public String[] getSupportedOutputFormats() {
        return new String[] { LexGridSQLOut.description };
    }

    public String[] getAvailableTerminologies() throws UnexpectedError, ConnectionFailure {
        try {
            String[] terminologies = new String[] {};

            ArrayList temp = new ArrayList();

            DriverManager.setLoginTimeout(5);
            Connection sqlConnection;
            try {
                sqlConnection = new WrappedConnection(getUsername(), getPassword(), getDriver(), getServer());

            } catch (ClassNotFoundException e1) {
                log.error("The class you specified for your sql driver could not be found on the path.");
                throw new ConnectionFailure(
                        "The class you specified for your sql driver could not be found on the path.");
            }
            PreparedStatement getTerminologies = sqlConnection
                    .prepareStatement("Select RSAB, SSN from MRSAB where SABIN = ?");
            getTerminologies.setString(1, "Y");
            ResultSet results = getTerminologies.executeQuery();
            while (results.next()) {
                temp.add(results.getString("RSAB") + " -- " + results.getString("SSN"));
            }
            results.close();
            getTerminologies.close();
            sqlConnection.close();

            terminologies = (String[]) temp.toArray(new String[temp.size()]);
            Arrays.sort(terminologies, new StringComparator());

            return terminologies;
        } catch (ConnectionFailure e) {
            throw e;
        } catch (Exception e) {
            log.error("An error occurred while getting the terminologies.", e);
            throw new UnexpectedError("An error occurred while getting the terminologies.", e);
        }
    }
}