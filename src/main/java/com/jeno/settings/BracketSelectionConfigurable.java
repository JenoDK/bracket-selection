package com.jeno.settings;

import javax.swing.*;

import com.intellij.openapi.options.Configurable;

public class BracketSelectionConfigurable implements Configurable {

	private BracketSelectionSettingsPanel settingsPanel;

	@Override
	public String getDisplayName() {
		return "Bracket Selection";
	}

	@Override
	public String getHelpTopic() {
		return null;
	}

	@Override
	public JComponent createComponent() {
		if (settingsPanel == null) {
			settingsPanel = new BracketSelectionSettingsPanel();
		}
		return settingsPanel.getPanel();
	}

	@Override
	public boolean isModified() {
		if (settingsPanel != null) {
			return settingsPanel.isModified();
		}
		return false;
	}

	@Override
	public void apply() {
		if (settingsPanel != null) {
			settingsPanel.apply();
		}
	}

	@Override
	public void reset() {
		if (settingsPanel != null) {
			settingsPanel.reset();
		}
	}

	@Override
	public void disposeUIResources() {
		settingsPanel = null;
	}
}
