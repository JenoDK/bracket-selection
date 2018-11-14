package com.jeno;

import com.intellij.openapi.editor.Editor;

public class StartBracketSelection extends BracketSelectionAbstractAction {

	@Override
	protected void performSelection(Editor editor, int currentCursor, int openingBracketIndex, int closingBracketIndex) {
		if (openingBracketIndex >= 0) {
			editor.getSelectionModel().setSelection(openingBracketIndex, currentCursor);
		}
	}

}
