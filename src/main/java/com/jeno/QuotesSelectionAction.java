package com.jeno;

import java.util.regex.Pattern;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.openapi.util.TextRange;
import com.jeno.settings.BracketSelectionSettings;

public class QuotesSelectionAction extends AnAction {

	private static final Pattern QUOTE_MATCHING_PATTERN = Pattern.compile("([\"'])((?:\\\\\\1|(?:(?!\\1)).)*)(\\1)");

	@Override
	public void actionPerformed(AnActionEvent e) {
		final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
		final int currentCursor = editor.getCaretModel().getCurrentCaret().getSelectionStart();
		final EditorHighlighter highlighter = editor.getHighlighter();
		final HighlighterIterator iterator = highlighter.createIterator(currentCursor);

		if (!iterator.atEnd()) {
			String text = iterator.getDocument().getText(TextRange.create(iterator.getStart(), iterator.getEnd()));
			boolean includeBrackets = BracketSelectionSettings.getInstance().isIncludeBracketsInSelection();
			int start = includeBrackets ? iterator.getStart() : iterator.getStart() + 1;
			int end = includeBrackets ? iterator.getEnd() : iterator.getEnd() - 1;
			if (QUOTE_MATCHING_PATTERN.matcher(text).matches()) {
				editor.getSelectionModel().setSelection(start, end);
			} else {
				String extendedText = iterator.getDocument().getText(TextRange.create(iterator.getStart() - 1, iterator.getEnd() + 1));
				if (QUOTE_MATCHING_PATTERN.matcher(extendedText).matches()) {
					editor.getSelectionModel().setSelection(start - 1, end + 1);
				}
			}
		}
	}


}
