package com.jeno;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.TextRange;

public abstract class BracketSelectionAbstractAction extends AnAction {

	protected static final int LOWER_LIMIT = 1000;
	protected static final int UPPER_LIMIT = 1000;

	@Override
	public void actionPerformed(AnActionEvent e) {
		final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
		final Document document = editor.getDocument();
		int currentCursor = editor.getCaretModel().getOffset();
		int start = currentCursor >= LOWER_LIMIT ? currentCursor - LOWER_LIMIT : 0;
		int end = currentCursor + UPPER_LIMIT;
		if (end >= document.getTextLength()) {
			end = document.getTextLength() - 1;
		}
		int openingBracketIndex = editor.getDocument().getText(new TextRange(start, currentCursor)).lastIndexOf((int) '(') + start + 1;
		int textAfterCursorStart = currentCursor + 1;
		int closingBracketIndex = editor.getDocument().getText(new TextRange(textAfterCursorStart, end)).indexOf((int) ')') + textAfterCursorStart;
		performSelection(editor, currentCursor, openingBracketIndex, closingBracketIndex);
	}

	protected abstract void performSelection(
			Editor editor,
			int currentCursor,
			int openingBracketIndex,
			int closingBracketIndex);

}
