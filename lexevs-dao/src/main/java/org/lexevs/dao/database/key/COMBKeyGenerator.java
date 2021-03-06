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
package org.lexevs.dao.database.key;

import java.util.Date;
import java.util.UUID;

/**
 * The Class Java5UUIDKeyGenerator.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public class COMBKeyGenerator implements KeyProvider<Object,UUID> {

	/* (non-Javadoc)
	 * @see org.lexevs.dao.database.key.KeyProvider#getKey(java.lang.Object)
	 */
	public UUID getKey(Object record) {
		//the actual record doesn't matter -- all generated keys will be unique.
		return getSequentialUUID();
	}
	
	/**
	 * Gets the random uuid.
	 * 
	 * @return the random uuid
	 */
	public static UUID getSequentialUUID(){
		Date date = new Date();
		
		long time = date.getTime();
		long uuidLong = UUID.randomUUID().getMostSignificantBits();
		
		return new UUID(uuidLong,time);
	}
	
	public static void main(String[] args) {
		for(int i=0;i<25;i++) {
			System.out.println(getSequentialUUID());
		}
	}
}