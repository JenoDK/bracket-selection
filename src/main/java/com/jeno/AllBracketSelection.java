package com.jeno;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.TextRange;
import com.jeno.settings.BracketSelectionSettings;

public class AllBracketSelection extends BracketSelectionAbstractAction {

	@Override
	protected void performSelection(Editor editor, TextRange brace1Start, TextRange brace2End, int currentCursor) {
		if (brace1Start != null && brace2End != null) {
			boolean includeBrackets = BracketSelectionSettings.getInstance().isIncludeBracketsInSelection();
			int start = includeBrackets ? brace1Start.getEndOffset() - 1 : brace1Start.getEndOffset();
			int end = includeBrackets ? brace2End.getStartOffset() + 1 : brace2End.getStartOffset();
			editor.getSelectionModel().setSelection(start, end);
		}
	}

}
