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
package edu.mayo.informatics.lexgrid.convert.formats.outputFormats;

import java.sql.Connection;

import org.LexGrid.util.sql.DBUtility;

import edu.mayo.informatics.lexgrid.convert.exceptions.ConnectionFailure;
import edu.mayo.informatics.lexgrid.convert.formats.Option;
import edu.mayo.informatics.lexgrid.convert.formats.OutputFormatInterface;
import edu.mayo.informatics.lexgrid.convert.formats.baseFormats.SQLBase;

/**
 * Details for registering a terminology with the LexGrid Services Index.
 * 
 * @author <A HREF="mailto:armbrust.daniel@mayo.edu">Dan Armbrust</A>
 * @version subversion $Revision: 258 $ checked in on $Date: 2005-11-15 11:11:26
 *          -0600 (Tue, 15 Nov 2005) $
 */
public class RegisterLexGridTerminology extends SQLBase implements OutputFormatInterface {

    public static final String description = "Register LexGrid Terminology";

    public RegisterLexGridTerminology(String username, String password, String server, String driver) {
        this.username = username;
        this.password = password;
        this.server = server;
        this.driver = driver;
    }

    public RegisterLexGridTerminology() {

    }

    public String getConnectionSummary() {
        return "The terminologies that you select from the input location will be registered in the LexGrid Services Index."
                + "\n" + super.getConnectionSummary("\nLexGrid Service Index Server:");
    }

    public String getDescription() {
        return description;
    }

    public Option[] getOptions() {
        return new Option[] { new Option(Option.HOSTED_BY, "Biomedical Informatics"),
                new Option(Option.CONTACT_INFO, "informatics@mayo.edu"), new Option(Option.PUBLIC_USERNAME, "lexgrid"),
                new Option(Option.PUBLIC_PASSWORD, "lexgrid") };
    }

    public String testConnection() throws ConnectionFailure {
        if (server == null || server.length() == 0) {
            throw new ConnectionFailure("The connection string is required");
        }
        if (driver == null || driver.length() == 0) {
            throw new ConnectionFailure("The driver name is required");
        }
        try {
            Connection c = DBUtility.connectToDatabase(server, driver, username, password);
            c.close();
        } catch (Exception e) {
            throw new ConnectionFailure(e.toString());
        }
        return "";
    }
}