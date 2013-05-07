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
package org.LexGrid.LexBIG.gui.load;

import java.io.File;
import java.net.URI;

import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.Extensions.Load.OBO_Loader;
import org.LexGrid.LexBIG.gui.DialogHandler;
import org.LexGrid.LexBIG.gui.LB_GUI;
import org.LexGrid.LexBIG.gui.LoadExportBaseShell;
import org.LexGrid.LexBIG.gui.Utility;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * GUI that allows loading OBO files into LexBIG.
 * 
 * @author <A HREF="mailto:armbrust.daniel@mayo.edu">Dan Armbrust</A>
 * @version subversion $Revision: $ checked in on $Date: $
 */
public class OBOLoader extends LoadExportBaseShell {
	public OBOLoader(LB_GUI lb_gui) {
		super(lb_gui);
		try {
			Shell shell = new Shell(lb_gui_.getShell().getDisplay());
			shell.setSize(500, 400);
			shell.setImage(new Image(shell.getDisplay(), this.getClass()
					.getResourceAsStream("/icons/load.gif")));

			dialog_ = new DialogHandler(shell);

			OBO_Loader loader = (OBO_Loader) lb_gui_.getLbs()
					.getServiceManager(null).getLoader("OBOLoader");

			shell.setText(loader.getName());

			buildGUI(shell, loader);

			shell.open();

			shell.addShellListener(shellListener);
		} catch (Exception e) {
			dialog_.showError("Unexpected Error", e.toString());
		}

	}

	private void buildGUI(Shell shell, final OBO_Loader loader) {
		Group options = new Group(shell, SWT.NONE);
		options.setText("Load Options");
		shell.setLayout(new GridLayout());

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		options.setLayoutData(gd);

		GridLayout layout = new GridLayout(3, false);
		options.setLayout(layout);

		Label fileLabel = new Label(options, SWT.NONE);
		fileLabel.setText("OBO source file");

		final Text file = new Text(options, SWT.BORDER);
		gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
		file.setLayoutData(gd);

		Button fileChooseButton = Utility.getFileChooseButton(options, file,
				new String[] { "*.obo" }, new String[] { "OBO file (obo)" });

		gd = new GridData(SWT.CENTER);
		gd.widthHint = 60;
		fileChooseButton.setLayoutData(gd);

		/* ****************************** */
		Label fileMetaLabel = new Label(options, SWT.NONE);
		fileMetaLabel.setText("OBO metadata file (optional)");

		final Text metaFile = new Text(options, SWT.BORDER);
		gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
		metaFile.setLayoutData(gd);

		Button metaFileChooseButton = Utility.getFileChooseButton(options,
				metaFile, new String[] { "*.xml" },
				new String[] { "OBO metadata file (xml)" });
		gd = new GridData(SWT.CENTER);
		gd.widthHint = 60;
		metaFileChooseButton.setLayoutData(gd);

		Label paramFileLabel = new Label(options, SWT.NONE);
		paramFileLabel.setText("Manifest file (optional)");

		final Text paramFile = new Text(options, SWT.BORDER);
		gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
		paramFile.setLayoutData(gd);

		Button paramFileChooseButton = Utility.getFileChooseButton(options,
				paramFile, new String[] { "*.xml" },
				new String[] { "Manifest file (xml)" });
		gd = new GridData(SWT.CENTER);
		gd.widthHint = 60;
		paramFileChooseButton.setLayoutData(gd);

		final Button load = new Button(options, SWT.PUSH);
		load.setText("Load");
		gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
		gd.horizontalSpan = 3;
		gd.widthHint = 60;
		load.setLayoutData(gd);

		load.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent arg0) {
				URI uri = null;
				URI metaURI = null;

				// is this a local file?
				File theFile = new File(file.getText());

				if (theFile.exists()) {
					uri = theFile.toURI();
				} else {
					// is it a valid URI (like http://something)
					try {
						uri = new URI(file.getText());
						uri.toURL().openConnection();
					} catch (Exception e) {
						dialog_.showError("Path Error",
								"No file could be located at this location");
						return;
					}
				}

				if (metaFile.getText().length() > 0) {
					// is this a local file?
					File theMetaFile = new File(metaFile.getText());

					if (theMetaFile.exists()) {
						metaURI = theMetaFile.toURI();
					} else {
						// is it a valid URI (like http://something)
						try {
							metaURI = new URI(metaFile.getText());
							metaURI.toURL().openConnection();
						} catch (Exception e) {
							dialog_
									.showError("Path Error",
											"No meta file could be located at this location");
							return;
						}
					}
				}

				URI paramUri = null;
				File theParamFile = new File(paramFile.getText());

				if (theParamFile.exists()) {
					paramUri = theParamFile.toURI();
				} else {
					// is it a valid URI (like http://something)
					try {
						if (!isNull(paramFile.getText())) {
							paramUri = new URI(paramFile.getText());
							paramUri.toURL().openConnection();
						}
					} catch (Exception e) {
						dialog_.showError("Path Error",
								"No file could be located at this location");
						return;
					}
				}

				try {
					setLoading(true);
					loader.setCodingSchemeManifestURI(paramUri);
					loader.load(uri, metaURI, false, true);
					load.setEnabled(false);
				}

				catch (LBException e) {
					dialog_.showError("Loader Error", e.toString());
					load.setEnabled(true);
					setLoading(false);
					return;
				}
			}

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// 
			}

		});

		Composite status = getStatusComposite(shell, loader);
		gd = new GridData(GridData.FILL_BOTH);
		status.setLayoutData(gd);

	}

	private boolean isNull(String str) {
		return ((str == null) || ("".equals(str.trim())) || ("null".equals(str)));
	}

}