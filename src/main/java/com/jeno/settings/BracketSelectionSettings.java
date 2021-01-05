package com.jeno.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;

@State(
		name = "BracketSelectionSettings",
		storages = {@Storage("bracket_selection.xml")}
)
public class BracketSelectionSettings implements PersistentStateComponent<BracketSelectionSettings> {

	private boolean includeBracketsInSelection;

	public static BracketSelectionSettings getInstance() {
		return ServiceManager.getService(BracketSelectionSettings.class);
	}

	@Override
	public BracketSelectionSettings getState() {
		return this;
	}

	@Override
	public void loadState(BracketSelectionSettings jBehaveSettings) {
		XmlSerializerUtil.copyBean(jBehaveSettings, this);
	}

	public boolean isIncludeBracketsInSelection() {
		return includeBracketsInSelection;
	}

	public void setIncludeBracketsInSelection(boolean includeBracketsInSelection) {
		this.includeBracketsInSelection = includeBracketsInSelection;
	}

}
