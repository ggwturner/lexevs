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
package edu.mayo.informatics.lexgrid.convert.formats.inputFormats.swt;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;

import edu.mayo.informatics.lexgrid.convert.exceptions.ConnectionFailure;
import edu.mayo.informatics.lexgrid.convert.formats.InputFormatSWTInterface;
import edu.mayo.informatics.lexgrid.convert.formats.inputFormats.Owl;
import edu.mayo.informatics.lexgrid.convert.swt.DialogHandler;
import edu.mayo.informatics.lexgrid.convert.swt.formatPanels.FileEntryComposite;

/**
 * Details for reading from Owl files.
 * 
 * @author <A HREF="mailto:kanjamala.pradip@mayo.edu">Pradip Kanjamala</A>
 * @version subversion $Revision: 1052 $ checked in on $Date: 2006-01-30
 *          11:42:24 +0000 (Mon, 30 Jan 2006) $
 */
public class OwlSWT extends Owl implements InputFormatSWTInterface {
    protected FileEntryComposite fec = null;

    public Composite createComposite(Composite parent, int style, DialogHandler errorHandler) {
        fec = new FileEntryComposite(parent, style, description, errorHandler);
        fec.setFileSelection("C:\\terminology.owl");
        return fec;
    }

    public String testConnection() throws ConnectionFailure {
        getFileLocationFromComposite();
        return super.testConnection();
    }

    public Menu createToolsMenu(Shell shell, final DialogHandler errorHandler) {
        return null;
    }

    private void getFileLocationFromComposite() {
        if (fec != null) {
            try {
                setFileLocation(new URI(fec.getFileSelection()));
            } catch (URISyntaxException e) {
                setFileLocation(new File(fec.getFileSelection()).toURI());
            }
        }
    }
}