/**
 * This software is released as part of the Pumpernickel project.
 * 
 * All com.pump resources in the Pumpernickel project are distributed under the
 * MIT License:
 * https://raw.githubusercontent.com/mickleness/pumpernickel/master/License.txt
 * 
 * More information about the Pumpernickel project is available here:
 * https://mickleness.github.io/pumpernickel/
 */
package com.pump.showcase;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.pump.plaf.PlafPaintUtils;
import com.pump.plaf.TexturePaintPanelUI;
import com.pump.swing.PartialLineBorder;
import com.pump.swing.toolbar.CustomizedToolbar;
import com.pump.util.JVM;

/** A demo app for the {@link CustomizedToolbar}.
 *
 */
public class CustomizedToolbarDemo extends JPanel {
	private static final long serialVersionUID = 1L;

	JButton customize = new JButton("Customize...");
	//these are the components we [may] display in the toolbar:
	JComponent[] list = new JComponent[] {
			customize,
			new JCheckBox("Check box"),
			new JLabel("Label"),
			new JButton("Button"),
			new JSlider()
	};
	CustomizedToolbar toolbar = new CustomizedToolbar(list,new String[] {"0","\t","1"},"toolbar demo");
	

	public CustomizedToolbarDemo() {

		for(int a = 0; a<list.length; a++) {
			list[a].setName( ""+a ); //give every component a unique name
			list[a].setOpaque(false);
		}

		ActionListener showCustomizeAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolbar.displayDialog( JVM.isMac ? 350 : 280);
			}
		};
		customize.addActionListener(showCustomizeAction);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 0; c.weightx = 1; c.weighty = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(toolbar, c);
		c.gridy++; c.weighty = 1; c.fill = GridBagConstraints.BOTH;
		JPanel fluff = new JPanel();
		fluff.setBorder(new PartialLineBorder(Color.DARK_GRAY, new Insets(1,0,0,0)));
		fluff.setUI(new TexturePaintPanelUI(PlafPaintUtils.getCheckerBoard(3)));
		fluff.setOpaque(false);
		add(fluff, c);
	}
}