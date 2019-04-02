package com.jeno;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.TextRange;

public class OpeningBracketSelection extends BracketSelectionAbstractAction {

	@Override
	protected void performSelection(Editor editor, TextRange brace1Start, TextRange brace2End, int currentCursor) {
		if (brace1Start != null) {
			editor.getSelectionModel().setSelection(brace1Start.getEndOffset(), currentCursor);
		}
	}

}
