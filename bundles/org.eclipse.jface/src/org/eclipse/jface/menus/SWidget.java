/******************************************************************************* * Copyright (c) 2005 IBM Corporation and others. * All rights reserved. This program and the accompanying materials * are made available under the terms of the Eclipse Public License v1.0 * which accompanies this distribution, and is available at * http://www.eclipse.org/legal/epl-v10.html * * Contributors: *     IBM Corporation - initial API and implementation ******************************************************************************/package org.eclipse.jface.menus;/** * <p> * <strong>EXPERIMENTAL</strong>. This class or interface has been added as * part of a work in progress. There is a guarantee neither that this API will * work nor that it will remain the same. Please do not use this API without * consulting with the Platform/UI team. * </p> *  * @since 3.2 */public final class SWidget extends MenuElement {	private IWidget thirdPartyCode;	private SLocation[] locations;	public SWidget(final String id) {		super(id);	}	public final void define(final IWidget widget, final SLocation[] locations) {		this.thirdPartyCode = widget;		this.locations = locations;		this.defined = true;	}	public final SLocation[] getLocations() {		final SLocation[] result = new SLocation[locations.length];		System.arraycopy(locations, 0, result, 0, locations.length);		return result;	}	public final IWidget getWidget() {		return thirdPartyCode;	}	public final String toString() {		if (string == null) {			final StringBuffer stringBuffer = new StringBuffer();			stringBuffer.append("SItem("); //$NON-NLS-1$			stringBuffer.append(id);			stringBuffer.append(',');			stringBuffer.append(locations);			stringBuffer.append(',');			try {				stringBuffer.append(thirdPartyCode);			} catch (final Exception e) {				// A bogus toString() in third-party code. Ignore.				stringBuffer.append(e.getClass().getName());			}			stringBuffer.append(',');			stringBuffer.append(defined);			stringBuffer.append(')');			string = stringBuffer.toString();		}		return string;	}	public final void undefine() {		string = null;		defined = false;		locations = null;		thirdPartyCode = null;	}}