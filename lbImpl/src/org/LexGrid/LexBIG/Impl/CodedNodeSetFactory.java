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
package org.LexGrid.LexBIG.Impl;

import org.LexGrid.LexBIG.DataModel.Collections.LocalNameList;
import org.LexGrid.LexBIG.DataModel.Core.CodingSchemeVersionOrTag;
import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.Exceptions.LBInvocationException;
import org.LexGrid.LexBIG.Exceptions.LBParameterException;
import org.LexGrid.LexBIG.Exceptions.LBResourceUnavailableException;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet;
import org.LexGrid.LexBIG.Utility.Constructors;
import org.LexGrid.LexBIG.Utility.ServiceUtility;
import org.lexevs.locator.LexEvsServiceLocator;
import org.lexevs.registry.model.RegistryEntry;
import org.lexevs.registry.service.Registry;

/**
 * A factory for creating CodedNodeGraph objects.
 */
public class CodedNodeSetFactory {

    private static String VERSION_18 = "1.8";
    private static String VERSION_20 = "2.0";

    /**
     * Gets the coded node graph.
     * 
     * @param codingScheme the coding scheme
     * @param versionOrTag the version or tag
     * @param relationContainerName the relation container name
     * 
     * @return the coded node graph
     * 
     * @throws LBParameterException the LB parameter exception
     */
    public CodedNodeSet getCodedNodeSet(String codingScheme, CodingSchemeVersionOrTag versionOrTag, Boolean activeOnly, LocalNameList types)
    throws LBInvocationException, LBParameterException, LBResourceUnavailableException {
        String uri = LexEvsServiceLocator.getInstance().getSystemResourceService().getUriForUserCodingSchemeName(codingScheme);
        String version = ServiceUtility.getVersion(codingScheme, versionOrTag);

        Registry registry = LexEvsServiceLocator.getInstance().getRegistry();

        RegistryEntry entry = 
            registry.getCodingSchemeEntry(Constructors.createAbsoluteCodingSchemeVersionReference(uri, version));

        if(entry.getDbSchemaVersion().equals(VERSION_18)){

            return new CodedNodeSetImpl(uri, versionOrTag, activeOnly, types);

        }

        if(entry.getDbSchemaVersion().equals(VERSION_20)){

            return new CodedNodeSetImpl(uri, versionOrTag, activeOnly, types);

        }

        throw new LBParameterException("Could not create a CodedNodeSet for CodingScheme: " + codingScheme);
    }
}