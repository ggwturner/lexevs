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
package org.lexgrid.loader.processor.support;

/**
 * The Interface EntityResolver.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public interface EntityResolver<I> {

	/**
	 * Gets the entity code.
	 * 
	 * @param item the item
	 * 
	 * @return the entity code
	 */
	public String getEntityCode(I item);
	
	/**
	 * Gets the entity code namespace.
	 * 
	 * @param item the item
	 * 
	 * @return the entity code namespace
	 */
	public String getEntityCodeNamespace(I item);
	
	/**
	 * Gets the entity description.
	 * 
	 * @param item the item
	 * 
	 * @return the entity description
	 */
	public String getEntityDescription(I item);
	
	/**
	 * Gets the checks if is active.
	 * 
	 * @param item the item
	 * 
	 * @return the checks if is active
	 */
	public boolean getIsActive(I item);
	
	/**
	 * Gets the checks if is anonymous.
	 * 
	 * @param item the item
	 * 
	 * @return the checks if is anonymous
	 */
	public boolean getIsAnonymous(I item);
	
	/**
	 * Gets the checks if is defined.
	 * 
	 * @param item the item
	 * 
	 * @return the checks if is defined
	 */
	public boolean getIsDefined(I item);
	
	public String[] getEntityTypes(I item);
}