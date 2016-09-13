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

package de.topobyte.jeography.gis;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import de.topobyte.jeography.executables.JeographyGIS;
import de.topobyte.jeography.viewer.MouseMode;
import de.topobyte.jeography.viewer.action.AboutAction;
import de.topobyte.jeography.viewer.action.ConfigurationAction;
import de.topobyte.jeography.viewer.action.CrosshairAction;
import de.topobyte.jeography.viewer.action.DialogAction;
import de.topobyte.jeography.viewer.action.FullscreenAction;
import de.topobyte.jeography.viewer.action.GeometryIndexAction;
import de.topobyte.jeography.viewer.action.GeometryInfoAction;
import de.topobyte.jeography.viewer.action.GeometryListAction;
import de.topobyte.jeography.viewer.action.GeometryManagerAction;
import de.topobyte.jeography.viewer.action.GeometrySelectionAction;
import de.topobyte.jeography.viewer.action.GotoAction;
import de.topobyte.jeography.viewer.action.GridAction;
import de.topobyte.jeography.viewer.action.ManualAction;
import de.topobyte.jeography.viewer.action.MapWindowPanelAction;
import de.topobyte.jeography.viewer.action.MeasurePanelAction;
import de.topobyte.jeography.viewer.action.MouseModeAction;
import de.topobyte.jeography.viewer.action.NetworkStateAction;
import de.topobyte.jeography.viewer.action.OperationAction;
import de.topobyte.jeography.viewer.action.OverlayAction;
import de.topobyte.jeography.viewer.action.OverlayTileConfigAction;
import de.topobyte.jeography.viewer.action.PolygonalSelectionSnapAction;
import de.topobyte.jeography.viewer.action.QuitAction;
import de.topobyte.jeography.viewer.action.SelectionPolyPanelAction;
import de.topobyte.jeography.viewer.action.SelectionRectPanelAction;
import de.topobyte.jeography.viewer.action.SelectionSnapAction;
import de.topobyte.jeography.viewer.action.ShowStatusbarAction;
import de.topobyte.jeography.viewer.action.ShowToolbarAction;
import de.topobyte.jeography.viewer.action.TileConfigAction;
import de.topobyte.jeography.viewer.action.TileNumberAction;
import de.topobyte.jeography.viewer.action.ZoomAction;
import de.topobyte.jeography.viewer.config.TileConfig;
import de.topobyte.jeography.viewer.core.Viewer;
import de.topobyte.jeography.viewer.geometry.list.operation.OperationList;
import de.topobyte.jeography.viewer.geometry.list.operation.Operations;
import de.topobyte.jeography.viewer.geometry.list.operation.ShowingBufferUnionList;
import de.topobyte.jeography.viewer.geometry.list.operation.ShowingTranslateList;
import de.topobyte.jeography.viewer.geometry.list.operation.transform.ShowingTransformList;
import de.topobyte.jeography.viewer.util.ActionUtil;
import de.topobyte.jeography.viewer.util.EmptyIcon;

/**
 * @author Sebastian Kuerten (sebastian@topobyte.de)
 */
public class GisActions
{

	public static void setupActions(JFrame frame, final JeographyGIS gis,
			JToolBar toolbar, JMenuBar menuBar, List<TileConfig> tileConfigs,
			List<TileConfig> overlayConfigs)
	{
		final Viewer viewer = gis.getViewer();

		MouseModeAction mma1 = new MouseModeAction(gis, MouseMode.NAVIGATE);
		MouseModeAction mma2 = new MouseModeAction(gis, MouseMode.SELECT);
		MouseModeAction mma3 = new MouseModeAction(gis, MouseMode.POLYSELECT);
		MouseModeAction mma4 = new MouseModeAction(gis, MouseMode.DRAG);
		List<MouseModeAction> actions = new ArrayList<>();
		actions.add(mma1);
		actions.add(mma2);
		actions.add(mma3);
		actions.add(mma4);
		SelectionSnapAction snap = new SelectionSnapAction(gis);
		PolygonalSelectionSnapAction snapPolygonal = new PolygonalSelectionSnapAction(
				gis);

		ConfigurationAction configure = new ConfigurationAction(gis);
		NetworkStateAction network = new NetworkStateAction(viewer);
		ShowStatusbarAction ssa = new ShowStatusbarAction(gis);
		ShowToolbarAction sta = new ShowToolbarAction(gis);
		GridAction grid = new GridAction(viewer);
		TileNumberAction tileNumbers = new TileNumberAction(viewer);
		CrosshairAction crosshair = new CrosshairAction(viewer);
		ZoomAction zoomIn = new ZoomAction(viewer, true);
		ZoomAction zoomOut = new ZoomAction(viewer, false);
		OverlayAction overlay = new OverlayAction(viewer);
		GeometryInfoAction geometryInfo = new GeometryInfoAction(gis);
		QuitAction quit = new QuitAction(gis, frame);
		GeometryManagerAction gma = new GeometryManagerAction(gis);
		SelectionRectPanelAction srpa = new SelectionRectPanelAction(gis);
		SelectionPolyPanelAction sppa = new SelectionPolyPanelAction(gis);
		MapWindowPanelAction mpa = new MapWindowPanelAction(gis);
		GeometryListAction gla = new GeometryListAction(viewer, viewer);
		GeometryIndexAction gia = new GeometryIndexAction(gis);
		GeometrySelectionAction gsa = new GeometrySelectionAction(gis);
		MeasurePanelAction mla = new MeasurePanelAction(gis);
		FullscreenAction fullscreen = new FullscreenAction(gis);
		GotoAction gta = new GotoAction(gis);

		ManualAction manual = new ManualAction(gis);
		AboutAction about = new AboutAction(gis);

		JToggleButton buttonMma1 = new JToggleButton(mma1);
		JToggleButton buttonMma2 = new JToggleButton(mma2);
		JToggleButton buttonMma3 = new JToggleButton(mma3);
		JToggleButton buttonMma4 = new JToggleButton(mma4);
		JToggleButton buttonGrid = new JToggleButton(grid);
		JToggleButton buttonTileNumbers = new JToggleButton(tileNumbers);
		JToggleButton buttonCrosshair = new JToggleButton(crosshair);
		JToggleButton buttonOverlay = new JToggleButton(overlay);
		JToggleButton buttonGeometryInfo = new JToggleButton(geometryInfo);
		JToggleButton buttonSnap = new JToggleButton(snap);
		JToggleButton buttonSnapPolygonal = new JToggleButton(snapPolygonal);
		JToggleButton buttonGM = new JToggleButton(gma);
		JToggleButton buttonSRP = new JToggleButton(srpa);
		JToggleButton buttonSPP = new JToggleButton(sppa);
		JToggleButton buttonMWP = new JToggleButton(mpa);
		buttonMma1.setText(null);
		buttonMma2.setText(null);
		buttonMma3.setText(null);
		buttonMma4.setText(null);
		buttonGrid.setText(null);
		buttonTileNumbers.setText(null);
		buttonCrosshair.setText(null);
		buttonOverlay.setText(null);
		buttonGeometryInfo.setText(null);
		buttonSnap.setText(null);
		buttonSnapPolygonal.setText(null);
		buttonGM.setText(null);
		buttonSRP.setText(null);
		buttonSPP.setText(null);
		buttonMWP.setText(null);

		toolbar.add(network);
		toolbar.addSeparator();
		toolbar.add(buttonMma1);
		toolbar.add(buttonMma2);
		toolbar.add(buttonMma3);
		toolbar.add(buttonMma4);
		toolbar.addSeparator();
		toolbar.add(buttonGrid);
		toolbar.add(buttonTileNumbers);
		toolbar.add(buttonCrosshair);
		toolbar.add(zoomIn);
		toolbar.add(zoomOut);
		toolbar.add(buttonOverlay);
		toolbar.add(buttonSnap);
		toolbar.add(buttonSnapPolygonal);
		toolbar.add(buttonGeometryInfo);
		toolbar.addSeparator();
		toolbar.add(buttonMWP);
		toolbar.add(buttonSRP);
		toolbar.add(buttonSPP);
		toolbar.add(buttonGM);

		JMenu menuFile = new JMenu("File");
		menuFile.setMnemonic('F');
		menuBar.add(menuFile);
		menuFile.add(new JMenuItem(network));
		menuFile.add(new JMenuItem(configure));
		menuFile.add(new JMenuItem(quit));

		JMenu menuView = new JMenu("View");
		menuView.setMnemonic('V');
		menuBar.add(menuView);
		menuView.add(new JCheckBoxMenuItem(ssa));
		menuView.add(new JCheckBoxMenuItem(sta));
		menuView.add(new JCheckBoxMenuItem(grid));
		menuView.add(new JCheckBoxMenuItem(tileNumbers));
		menuView.add(new JCheckBoxMenuItem(crosshair));
		menuView.add(zoomIn);
		menuView.add(zoomOut);
		menuView.add(new JCheckBoxMenuItem(overlay));

		JMenu menuMap = new JMenu("Map");
		menuMap.setMnemonic('M');
		menuBar.add(menuMap);
		menuMap.add(gta);

		JMenu menuTiles = new JMenu("Tiles");
		menuTiles.setMnemonic('T');
		menuBar.add(menuTiles);
		for (TileConfig config : tileConfigs) {
			menuTiles.add(new TileConfigAction(viewer, config));
		}

		JMenu menuOverlays = new JMenu("Overlays");
		menuOverlays.setMnemonic('O');
		menuBar.add(menuOverlays);
		for (TileConfig config : overlayConfigs) {
			menuOverlays.add(new OverlayTileConfigAction(viewer, config));
		}

		JMenu menuWindows = new JMenu("Windows");
		menuWindows.setMnemonic('W');
		menuBar.add(menuWindows);
		menuWindows.add(new JCheckBoxMenuItem(gma));
		menuWindows.add(new JCheckBoxMenuItem(srpa));
		menuWindows.add(new JCheckBoxMenuItem(mpa));

		menuWindows.add(new JMenuItem(gia));
		menuWindows.add(new JMenuItem(gsa));
		menuWindows.add(new JMenuItem(gla));
		menuWindows.add(new JMenuItem(mla));

		JMenu menuOperationsAdd = new JMenu("operations");
		menuOperationsAdd.setIcon(new EmptyIcon(24));
		menuWindows.add(menuOperationsAdd);

		menuOperationsAdd.add(new OperationAction(Operations.UNION, gis));
		menuOperationsAdd
				.add(new OperationAction(Operations.INTERSECTION, gis));
		menuOperationsAdd.add(new OperationAction(Operations.DIFFERENCE, gis));
		menuOperationsAdd.add(new OperationAction(Operations.COLLECTION, gis));
		menuOperationsAdd.add(new OperationAction(Operations.HULL, gis));

		menuOperationsAdd.add(new DialogAction(gis,
				"res/images/geometryOperation/union.png", "Union Buffered",
				"union geometries and create a buffer of the result") {

			private static final long serialVersionUID = 1L;

			@Override
			protected OperationList createDialog()
			{
				return new ShowingBufferUnionList(viewer);
			}
		});

		menuOperationsAdd.add(new DialogAction(gis,
				"res/images/geometryOperation/collection.png", "Translate",
				"collect and translate geometries") {

			private static final long serialVersionUID = 1L;

			@Override
			protected OperationList createDialog()
			{
				return new ShowingTranslateList(viewer);
			}
		});

		menuOperationsAdd.add(new DialogAction(gis,
				"res/images/geometryOperation/collection.png", "Transform",
				"collect and transform geometries") {

			private static final long serialVersionUID = 1L;

			@Override
			protected OperationList createDialog()
			{
				return new ShowingTransformList(viewer);
			}
		});

		JMenu menuHelp = new JMenu("Help");
		menuHelp.setMnemonic('H');
		menuBar.add(menuHelp);
		menuHelp.add(new JMenuItem(manual));
		menuHelp.add(new JMenuItem(about));

		menuBar.updateUI();

		/* key bindings */

		InputMap inputMap = gis.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = gis.getActionMap();

		ActionUtil.setupMovementActions(viewer, inputMap, actionMap);
		ActionUtil.setupZoomActions(viewer, inputMap, actionMap);

		inputMap.put(KeyStroke.getKeyStroke('a'), "a");
		inputMap.put(KeyStroke.getKeyStroke('s'), "s");
		inputMap.put(KeyStroke.getKeyStroke('d'), "d");
		inputMap.put(KeyStroke.getKeyStroke('f'), "f");

		actionMap.put("a", mma1);
		actionMap.put("s", mma2);
		actionMap.put("d", mma3);
		actionMap.put("f", mma4);

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "f5");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), "f6");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), "f7");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), "f8");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "f9");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), "f10");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), "f11");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "f12");

		actionMap.put("f5", grid);
		actionMap.put("f6", tileNumbers);
		actionMap.put("f7", crosshair);
		actionMap.put("f8", overlay);
		actionMap.put("f9", geometryInfo);
		actionMap.put("f11", fullscreen);

		inputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK),
				"ctrl f");
		inputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK),
				"ctrl g");
		inputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK),
				"ctrl s");
		inputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK),
				"ctrl p");

		actionMap.put("ctrl g", gta);
		actionMap.put("ctrl s", srpa);
		actionMap.put("ctrl p", mpa);

		inputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_MASK),
				"ctrl f1");
		inputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.CTRL_MASK),
				"ctrl f2");
		inputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.CTRL_MASK),
				"ctrl f3");
		inputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK),
				"ctrl f4");
		inputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F5, InputEvent.CTRL_MASK),
				"ctrl f5");
		inputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F6, InputEvent.CTRL_MASK),
				"ctrl f6");
		inputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F7, InputEvent.CTRL_MASK),
				"ctrl f7");
		inputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F8, InputEvent.CTRL_MASK),
				"ctrl f8");
		inputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F9, InputEvent.CTRL_MASK),
				"ctrl f9");
		inputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F10, InputEvent.CTRL_MASK),
				"ctrl f10");
		inputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F11, InputEvent.CTRL_MASK),
				"ctrl f11");
		inputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F12, InputEvent.CTRL_MASK),
				"ctrl f12");

		actionMap.put("ctrl f1", new OperationAction(Operations.UNION, gis));
		actionMap.put("ctrl f2", new OperationAction(Operations.INTERSECTION,
				gis));
		actionMap.put("ctrl f3",
				new OperationAction(Operations.DIFFERENCE, gis));
		actionMap.put("ctrl f5", gla);
	}

}
