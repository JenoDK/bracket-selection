package com.jeno;

import com.intellij.codeInsight.highlighting.BraceMatchingUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.util.TextRange;

public abstract class BracketSelectionAbstractAction extends AnAction {

	@Override
	public void actionPerformed(AnActionEvent e) {
		final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
		final CharSequence chars = editor.getDocument().getCharsSequence();
		final int currentCursor = editor.getCaretModel().getCurrentCaret().getSelectionStart();
		final FileType fileType = e.getRequiredData(CommonDataKeys.VIRTUAL_FILE).getFileType();
		final EditorHighlighter highlighter = ((EditorEx) editor).getHighlighter();
		final HighlighterIterator iterator = highlighter.createIterator(currentCursor);

		boolean foundOne = retreatIteratorToFirstLeftBrace(currentCursor, 0, 0, iterator, chars, fileType);
		if (foundOne) {
			TextRange brace1Start = TextRange.create(iterator.getStart(), iterator.getEnd());
			boolean matched = BraceMatchingUtil.matchBrace(chars, fileType, iterator, true);
			TextRange brace2End = iterator.atEnd() ? null : TextRange.create(iterator.getStart(), iterator.getEnd());
			if (matched) {
				performSelection(editor, brace1Start, brace2End, currentCursor);
			}
		}
	}

	/**
	 * We want to find the ultimate left brace for the current iterator
	 *
	 * @param currentCursor
	 * @param RBraceCount
	 * @param LBraceCount
	 * @param iterator
	 * @param chars
	 * @param fileType
	 * @return
	 */
	private boolean retreatIteratorToFirstLeftBrace(int currentCursor, int RBraceCount, int LBraceCount, HighlighterIterator iterator, CharSequence chars, FileType fileType) {
		if (iterator.atEnd()) {
			return false;
		}

		// If we encounter a right side brace we set it to true
		if (BraceMatchingUtil.isRBraceToken(iterator, chars, fileType)) {
			RBraceCount++;
		}

		boolean currentCaretIsRightBeforeLeftBrace = currentCursor == iterator.getStart() && iterator.getEnd() > currentCursor;
		if (BraceMatchingUtil.isLBraceToken(iterator, chars, fileType) && !currentCaretIsRightBeforeLeftBrace) {
			// We encountered a right side brace before encountering a left side, so this is not the utmost left side brace yet
			if (RBraceCount != LBraceCount) {
				LBraceCount++;
			} else {
				return true;
			}
		}

		iterator.retreat();
		return retreatIteratorToFirstLeftBrace(currentCursor, RBraceCount, LBraceCount, iterator, chars, fileType);
	}

	protected abstract void performSelection(
			Editor editor,
			TextRange currentCursor,
			TextRange openingBracketIndex,
			int closingBracketIndex);

}
