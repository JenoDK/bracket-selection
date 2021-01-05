package com.jeno.settings;

import javax.swing.*;

public class BracketSelectionSettingsPanel {

	private JPanel panel;
	private JPanel appearancePanel;
	private JCheckBox includeBrackets;

	private BracketSelectionSettings settings;

	public BracketSelectionSettingsPanel() {
		settings = BracketSelectionSettings.getInstance();
	}

	public void apply() {
		settings.setIncludeBracketsInSelection(includeBrackets.isSelected());
	}

	public void reset() {
		includeBrackets.setSelected(settings.isIncludeBracketsInSelection());
	}

	public boolean isModified() {
		return settings.isIncludeBracketsInSelection() != includeBrackets.isSelected();
	}

	public JComponent getPanel() {
		return panel;
	}


}
