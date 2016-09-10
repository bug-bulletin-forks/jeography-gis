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

package de.topobyte.jeography.viewer.geometry.list;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import de.topobyte.jeography.executables.JeographyGIS;
import de.topobyte.jeography.viewer.config.Configuration;

/**
 * @author Sebastian Kuerten (sebastian@topobyte.de)
 */
public class TestShowingGeometryList
{

	/**
	 * Test for the list
	 * 
	 * @param args
	 *            none
	 * @throws Exception
	 *             on failure
	 */
	public static void main(String[] args) throws Exception
	{
		int zoom = 6;
		double lon = 10.9, lat = 51.5;
		int width = 800, height = 600;

		Configuration configuration = Configuration
				.createDefaultConfiguration();

		JeographyGIS gis = new JeographyGIS(null, configuration, 0, null, true,
				false, false, false, false);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(gis);

		frame.setSize(new Dimension(width, height));
		frame.setVisible(true);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		gis.getViewer().getMapWindow().gotoLonLat(lon, lat);
		gis.getViewer().getMapWindow().zoom(zoom);
		gis.getViewer().repaint();

		JDialog dialog = new JDialog(frame);
		ShowingGeometryList showingGeometryList = new ShowingGeometryList(
				gis.getViewer());
		dialog.setContentPane(showingGeometryList);
		dialog.pack();
		dialog.setVisible(true);
	}

}