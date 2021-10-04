package com.jeno;

import java.util.regex.Pattern;

import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.jeno.settings.BracketSelectionSettings;

public class QuotesSelectionAction extends AnAction {

	private static final Pattern QUOTE_MATCHING_PATTERN = Pattern.compile("([\"'])((?:\\\\\\1|(?:(?!\\1)).)*)(\\1)");

	@Override
	public void actionPerformed(AnActionEvent e) {
		final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
		final int currentCursor = editor.getCaretModel().getCurrentCaret().getSelectionStart();
		final EditorHighlighter highlighter = ((EditorEx) editor).getHighlighter();
		final HighlighterIterator iterator = highlighter.createIterator(currentCursor);

		if (!iterator.atEnd()) {
			PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);
			PsiElement elementAt = tryToFindStringElement(currentCursor, psiFile);
			String text = elementAt.getText();

			boolean includeBrackets = BracketSelectionSettings.getInstance().isIncludeBracketsInSelection();
			int elementStart = elementAt.getTextOffset();
			int elementEnd = elementAt.getTextOffset() + elementAt.getTextLength();
			int start = includeBrackets ? elementStart : elementStart + 1;
			int end = includeBrackets ? elementEnd : elementEnd - 1;

			if (QUOTE_MATCHING_PATTERN.matcher(text).matches()) {
				editor.getSelectionModel().setSelection(start, end);
			} else {
				String extendedText = iterator.getDocument().getText(TextRange.create(elementStart - 1, elementEnd + 1));
				if (QUOTE_MATCHING_PATTERN.matcher(extendedText).matches()) {
					editor.getSelectionModel().setSelection(start - 1, end + 1);
				}
			}
		}
	}

	@Nullable
	private PsiElement tryToFindStringElement(int currentCursor, PsiFile psiFile) {
		return psiFile.findElementAt(currentCursor);
	}


}
