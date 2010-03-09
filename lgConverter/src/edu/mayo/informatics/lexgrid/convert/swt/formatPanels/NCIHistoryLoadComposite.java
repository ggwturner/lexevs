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
package edu.mayo.informatics.lexgrid.convert.swt.formatPanels;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import edu.mayo.informatics.lexgrid.convert.swt.DialogHandler;

/**
 * A SWT Composite that collects information necessary for selecting a file.
 * 
 * @author <A HREF="mailto:armbrust.daniel@mayo.edu">Dan Armbrust</A>
 * @version subversion $Revision: 1052 $ checked in on $Date: 2006-01-30
 *          05:42:24 -0600 (Mon, 30 Jan 2006) $
 */
public class NCIHistoryLoadComposite extends Composite {
    Text fileName;
    Text versionsFileName;

    public NCIHistoryLoadComposite(Composite parent, int style, String description, DialogHandler errorHandler) {
        super(parent, style);
        this.setLayout(new GridLayout(1, true));

        Group group = new Group(this, SWT.NONE);
        group.setText(description);
        group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        group.setLayout(new GridLayout(3, false));

        Label fileLabel = new Label(group, SWT.NONE);
        fileLabel.setText("File Name:  ");

        GridData gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_CENTER);
        fileName = new Text(group, SWT.SINGLE | SWT.BORDER);
        fileName.setLayoutData(gd);

        Button choose = new Button(group, SWT.PUSH);
        choose.setText("Browse...");
        choose.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent arg0) {
            }

            public void widgetSelected(SelectionEvent arg0) {
                FileDialog fileDialog = new FileDialog(NCIHistoryLoadComposite.this.getShell());
                fileDialog.setText("Please select a file");
                String file = fileDialog.open();
                if (file != null) {
                    fileName.setText(file);
                }
            }

        });

        Label versionsFileLabel = new Label(group, SWT.NONE);
        versionsFileLabel.setText("Versions File Name:  ");

        gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_CENTER);
        versionsFileName = new Text(group, SWT.SINGLE | SWT.BORDER);
        versionsFileName.setLayoutData(gd);

        choose = new Button(group, SWT.PUSH);
        choose.setText("Browse...");
        choose.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent arg0) {
            }

            public void widgetSelected(SelectionEvent arg0) {
                FileDialog fileDialog = new FileDialog(NCIHistoryLoadComposite.this.getShell());
                fileDialog.setText("Please select a file");
                String file = fileDialog.open();
                if (file != null) {
                    versionsFileName.setText(file);
                }
            }

        });

    }

    public String getFileSelection() {
        return fileName.getText();
    }

    public String getVersionsFileSelection() {
        return versionsFileName.getText();
    }

    public void setVersionsFileSelection(String versionsFileLocation) {
        versionsFileName.setText(versionsFileLocation);
    }

    public void setFileSelection(String fileLocation) {
        fileName.setText(fileLocation);
    }
}