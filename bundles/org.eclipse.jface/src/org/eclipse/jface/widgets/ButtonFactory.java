/*******************************************************************************
* Copyright (c) 2018 SAP SE and others.
*
* This program and the accompanying materials
* are made available under the terms of the Eclipse Public License 2.0
* which accompanies this distribution, and is available at
* https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
*
* Contributors:
*     SAP SE - initial version
******************************************************************************/
package org.eclipse.jface.widgets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 *
 * This class provides a convenient shorthand for creating and initializing
 * {@link Button}. This offers several benefits over creating Button normal way:
 *
 * <ul>
 * <li>The same factory can be used many times to create several Button
 * instances</li>
 * <li>The setters on ButtonFactory all return "this", allowing them to be
 * chained</li>
 * <li>ButtonFactory accepts a Lambda for {@link SelectionEvent} (see
 * {@link #onSelect})</li>
 * </ul>
 *
 * Example usage:
 *
 * <pre>
 * Button button = ButtonFactory.newButton(SWT.PUSH) //
 * 		.text("Click me!") //
 * 		.onSelect(event -> buttonClicked(event)) //
 * 		.layoutData(gridData) //
 * 		.create(parent);
 * </pre>
 * <p>
 * The above example creates a push button with a text, registers a
 * SelectionListener and finally creates the button in "parent".
 * <p>
 *
 * <pre>
 * ButtonFactory buttonFactory = ButtonFactory.newButton(SWT.PUSH).onSelect(event -> buttonClicked(event));
 * buttonFactory.text("Button 1").create(parent);
 * buttonFactory.text("Button 2").create(parent);
 * buttonFactory.text("Button 3").create(parent);
 * </pre>
 * <p>
 * The above example creates three buttons using the same instance of
 * ButtonFactory.
 * <p>
 */
public final class ButtonFactory extends ControlFactory<ButtonFactory, Button> {

	private String text;
	private Collection<SelectionListener> selectionListeners = new ArrayList<>();
	private Image image;

	private ButtonFactory(int style) {
		super(ButtonFactory.class, (Composite parent) -> new Button(parent, style));
	}

	/**
	 * Creates a new ButtonFactory with the given style. Refer to
	 * {@link Button#Button(Composite, int)} for possible styles.
	 *
	 * @param style
	 * @return a new ButtonFactory instance
	 */
	public static ButtonFactory newButton(int style) {
		return new ButtonFactory(style);
	}

	/**
	 * Sets the Button text.
	 *
	 * @param text
	 * @return this
	 */
	public ButtonFactory text(String text) {
		this.text = text;
		return this;
	}

	/**
	 * Sets the Button image.
	 *
	 * @param image
	 * @return this
	 */
	public ButtonFactory image(Image image) {
		this.image = image;
		return this;
	}

	/**
	 * Creates a {@link SelectionListener} and registers it for the widgetSelected
	 * event. If event is raised it calls the given consumer. The
	 * {@link SelectionEvent} is passed to the consumer.
	 *
	 * @param consumer
	 * @return this
	 */
	public ButtonFactory onSelect(Consumer<SelectionEvent> consumer) {
		this.selectionListeners.add(SelectionListener.widgetSelectedAdapter(consumer));
		return this;
	}

	@Override
	protected void applyProperties(Button button) {
		super.applyProperties(button);

		if (this.text != null) {
			button.setText(this.text);
		}
		if (this.image != null) {
			button.setImage(this.image);
		}
		this.selectionListeners.forEach(l -> button.addSelectionListener(l));
	}
}