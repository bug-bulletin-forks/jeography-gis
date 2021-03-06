// Copyright 2016 Sebastian Kuerten
//
// This file is part of jeography.
//
// jeography is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// jeography is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with jeography. If not, see <http://www.gnu.org/licenses/>.

package de.topobyte.jeography.viewer.geometry.manage;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * @author Sebastian Kuerten (sebastian@topobyte.de)
 */
public class EventJDialog extends JDialog
{

	private static final long serialVersionUID = -2309722106918770733L;

	/**
	 * @param parent
	 *            the parent component.
	 * @param string
	 *            the title.
	 */
	public EventJDialog(JFrame parent, String string)
	{
		super(parent, string);
	}

	@Override
	public void setVisible(boolean visible)
	{
		super.setVisible(visible);
		for (VisibilityListener l : listeners) {
			l.visibilityChanged(visible);
		}
	}

	private List<VisibilityListener> listeners = new ArrayList<>();

	/**
	 * @param l
	 *            the listener to add.
	 */
	public void addVisibilityListener(VisibilityListener l)
	{
		listeners.add(l);
	}

	/**
	 * @param l
	 *            the listener to remove.
	 */
	public void removeVisibilityListener(VisibilityListener l)
	{
		listeners.remove(l);
	}

}
