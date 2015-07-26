/*
GBC - A convenience class to tame the GridBagLayout

Copyright (C) 2002 Cay S. Horstmann (http://horstmann.com)

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package com.horstmann.corejava;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
* This class simplifies the use of the GridBagConstraints class.
*/
public class GBC extends GridBagConstraints {
	
	public GBC(int gridx, int gridy) {
		this.gridx = gridx;
		this.gridy = gridy;
	}
	
	public GBC(int gridx, int gridy, int gridwidth, int gridheight) {
		this(gridx, gridy);
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}
	
	public GBC setAnchor(int anchor) {
		this.anchor = anchor;
		return this;
	}
	
	public GBC setFill(int fill) {
		this.fill = fill;
		return this;
	}
	
	public GBC setWeight(double weightx, double weighty) {
		this.weightx = weightx;
		this.weighty = weighty;
		return this;
	}
	
	public GBC setInsets(int distance) {
		this.insets = new Insets(distance, distance, distance, distance);
		return this;
	}
	
	public GBC setInsets(int top, int left, int bottom, int right) {
		this.insets = new Insets(top, left, bottom, right);
		return this;
	}
	
	public GBC setIpad(int ipadx, int ipady) {
		this.ipadx = ipadx;
		this.ipady = ipady;
		return this;
	}
}
