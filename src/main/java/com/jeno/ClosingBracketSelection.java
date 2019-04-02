package com.jeno;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.TextRange;

public class ClosingBracketSelection extends BracketSelectionAbstractAction {

	@Override
	protected void performSelection(Editor editor, TextRange brace1Start, TextRange brace2End, int currentCursor) {
		if (brace2End != null) {
			editor.getSelectionModel().setSelection(currentCursor, brace2End.getStartOffset());
		}
	}

}
