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
package org.LexGrid.LexBIG.Extensions.Export;

import java.net.URI;

import org.LexGrid.LexBIG.DataModel.Core.AbsoluteCodingSchemeVersionReference;
import org.LexGrid.LexBIG.DataModel.Core.LogEntry;
import org.LexGrid.LexBIG.DataModel.Core.types.LogLevel;
import org.LexGrid.LexBIG.DataModel.InterfaceElements.ExportStatus;
import org.LexGrid.LexBIG.Extensions.Extendable;
import org.LexGrid.LexBIG.Extensions.Load.options.OptionHolder;
import org.LexGrid.LexBIG.Utility.logging.StatusReporter;

/**
 * Defines a class of object used to export content from the underlying LexGrid
 * repository to another repository or file format.
 */
public interface Exporter extends Extendable, StatusReporter {

	public OptionHolder getOptions();
	
	/**
	 * Clears any associated log entries.
	 */
	public void clearLog();

	/**
	 * Returns absolute references for resources written by the most
	 * recent operation; empty if not applicable.
	 */
	public URI[] getReferences();

	/**
	 * Returns log entries for the current or most recent export operation that
	 * match a particular status; null if no operation has been attempted.
	 * 
	 * @param status
	 *            One of several log levels defined by the system, or null to
	 *            return all log entries.
	 */
	public LogEntry[] getLog(LogLevel level);

	/**
	 * Returns status of the current or most recent export operation;
	 * null if no operation has been attempted.
	 */
	public ExportStatus getStatus();
	
	/**
	 * Export a CodingScheme resource to a URI destination.
	 * 
	 * @param source the source
	 * @param destination the destination
	 */
	public void export(AbsoluteCodingSchemeVersionReference source,
			URI destination);
	
	/**
	 * Export a Value Set Definition resource to a URI destination.
	 * 
	 * @param URI of value set definition
	 * @param version of value set definition
	 * @param destination the destination
	 */
	public void exportValueSetDefinition(URI valueSetDefinitionURI, String valueSetDefinitionVersion, 
			URI destination);
	
	/**
	 * Export a Pick List Definition resource to a URI destination.
	 * 
	 * @param Id of Pick List Definition
	 * @param destination the destination
	 */
	public void exportPickListDefinition(String pickListId,
			URI destination);

}