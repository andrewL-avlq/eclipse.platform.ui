/******************************************************************************* * Copyright (c) 2005 IBM Corporation and others. * All rights reserved. This program and the accompanying materials * are made available under the terms of the Eclipse Public License v1.0 * which accompanies this distribution, and is available at * http://www.eclipse.org/legal/epl-v10.html * * Contributors: *     IBM Corporation - initial API and implementation *******************************************************************************/package org.eclipse.jface.menus;import org.eclipse.core.expressions.Expression;/** * <p> * <strong>EXPERIMENTAL</strong>. This class or interface has been added as * part of a work in progress. There is a guarantee neither that this API will * work nor that it will remain the same. Please do not use this API without * consulting with the Platform/UI team. * </p> *  * @since 3.2 */public final class SMenu extends MenuElement {	private IDynamicMenu dynamic;	private String label;	private SLocation[] locations;	private Expression visibleWhen;	public SMenu(final String id) {		super(id);	}	public final void define(final IDynamicMenu dynamic, final String label,			final SLocation[] locations, final Expression visibleWhen) {		this.dynamic = dynamic;		this.label = label;		this.locations = locations;		this.visibleWhen = visibleWhen;		this.defined = true;	}	public final IDynamicMenu getDynamic() {		return dynamic;	}	public final String getLabel() {		return label;	}	public final SLocation[] getLocations() {		final SLocation[] result = new SLocation[locations.length];		System.arraycopy(locations, 0, result, 0, locations.length);		return result;	}	public final Expression getVisibleWhen() {		return visibleWhen;	}	public final String toString() {		if (string == null) {			final StringBuffer stringBuffer = new StringBuffer();			stringBuffer.append("SMenu("); //$NON-NLS-1$			stringBuffer.append(id);			stringBuffer.append(',');			stringBuffer.append(label);			stringBuffer.append(',');			stringBuffer.append(visibleWhen);			stringBuffer.append(',');			stringBuffer.append(dynamic);			stringBuffer.append(',');			stringBuffer.append(defined);			stringBuffer.append(')');			string = stringBuffer.toString();		}		return string;	}	public final void undefine() {		string = null;		defined = false;		label = null;		visibleWhen = null;		dynamic = null;	}}