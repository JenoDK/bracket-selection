package com.jeno;

import com.intellij.openapi.editor.Editor;

public class EndBracketSelection extends BracketSelectionAbstractAction {

	@Override
	protected void performSelection(Editor editor, int currentCursor, int openingBracketIndex, int closingBracketIndex) {
		if (closingBracketIndex >= 0) {
			editor.getSelectionModel().setSelection(currentCursor, closingBracketIndex);
		}
	}

}
