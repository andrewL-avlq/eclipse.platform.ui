/******************************************************************************* * Copyright (c) 2005 IBM Corporation and others. * All rights reserved. This program and the accompanying materials * are made available under the terms of the Eclipse Public License v1.0 * which accompanies this distribution, and is available at * http://www.eclipse.org/legal/epl-v10.html * * Contributors: *     IBM Corporation - initial API and implementation ******************************************************************************/package org.eclipse.jface.menus;/** * <p> * A location for a menu element. A location carries with it information about * the group in which is should appear, as well as the sort order with respect * to other menu elements. The location can also specify a style of image to * associate with the menu element and a mnemonic. * </p> * <p> * Clients may instantiate this class, but must not extend. * </p> * <p> * <strong>EXPERIMENTAL</strong>. This class or interface has been added as * part of a work in progress. There is a guarantee neither that this API will * work nor that it will remain the same. Please do not use this API without * consulting with the Platform/UI team. * </p> *  * @since 3.2 */public final class SLocation {	/**	 * The constant to use if there is no mnemonic for this location.	 */	public static final char MNEMONIC_NONE = 0;	/**	 * The constant to use if the position for the location is not specified.	 */	public static final int POSITION_UNSPECIFIED = 0;	/**	 * The constant to use if the position for the location is the start of the	 * current <code>IMenuCollection</code>.	 */	public static final int POSITION_START = 1;	/**	 * The constant to use if the position for the location is the end of the	 * current <code>IMenuCollection</code>.	 */	public static final int POSITION_END = 2;	/**	 * The constant to use if the position for the location is to be before a	 * menu element with a particular identifier.	 */	public static final int POSITION_BEFORE = 3;	/**	 * The constant to use if the position for the location is to be after a	 * menu element with a particular identifier.	 */	public static final int POSITION_AFTER = 4;	/**	 * The mnemonic to use in this location. This value may be <code>null</code>.	 */	private final char mnemonic;	/**	 * The style of image to use in this location. This value may be	 * <code>null</code> if the default image style should be used.	 */	private final String imageStyle;	/**	 * The position the menu element should be given relative to other menu	 * elements. This should be one of the position constants given by this	 * class.	 */	private final int position;	/**	 * The identifier of the menu element to which the position is relative.	 * This value is <code>null</code> iff the position is not a relative	 * position.	 */	private final String relativeTo;	/**	 * Constructs a new instance of <code>SLocation</code>.	 * 	 * @param mnemonic	 *            The mnemonic to use in this particular location. The mnemonic	 *            should be translated. If there is no mnemonic, then send	 *            <code>MNEMONIC_NONE</code>	 * @param imageStyle	 *            The style of image to use in this location. If this value is	 *            <code>null</code>, then the default image style is used.	 * @param position	 *            The position in which the menu element should appear with	 *            respect to other menu elements. This value should be one of	 *            the position constants defined in this class.	 * @param relativeTo	 *            The identifier of the menu element to which the position is	 *            relative. This value is required if the position is	 *            <code>POSITION_AFTER</code> or <code>POSITION_BEFORE</code>.	 *            Otherwise, this value should be <code>null</code>.	 * 	 * @see SLocation#POSITION_AFTER	 * @see SLocation#POSITION_BEFORE	 * @see SLocation#POSITION_END	 * @see SLocation#POSITION_START	 * @see SLocation#POSITION_UNSPECIFIED	 */	public SLocation(char mnemonic, String imageStyle, final int position,			final String relativeTo) {		if ((imageStyle != null) && (imageStyle.length() == 0)) {			imageStyle = null;		}		if ((position < POSITION_UNSPECIFIED) || (position > POSITION_AFTER)) {			throw new IllegalArgumentException(					"A location needs a valid position.  Got: " + position); //$NON-NLS-1$		}		if ((position == POSITION_AFTER) || (position == POSITION_BEFORE)) {			if (relativeTo == null) {				throw new NullPointerException(						"A location positioned before or after needs an identifier of the menu element to which the position is relative"); //$NON-NLS-1$			}		} else if (relativeTo != null) {			throw new IllegalArgumentException(					"A relative identifier was provided for a non-relative position"); //$NON-NLS-1$		}		this.mnemonic = mnemonic;		this.imageStyle = imageStyle;		this.position = position;		this.relativeTo = relativeTo;	}	/**	 * Returns the mnemonic for this location. The mnemonic should be	 * translated.	 * 	 * @return The mnemonic. If no mnemonic, then <code>MNEMONIC_NONE</code>.	 */	public final char getMnemonic() {		return mnemonic;	}}