package com.jeno.csharp;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.rider.ideaInterop.fileTypes.csharp.lexer.CSharpTokenType;

public class CSharpPairedBraceMatcher implements PairedBraceMatcher {
	@Override
	public BracePair @NotNull [] getPairs() {
		return new BracePair[]{
				new BracePair(CSharpTokenType.LBRACE, CSharpTokenType.RBRACE, false),
				new BracePair(CSharpTokenType.LBRACKET, CSharpTokenType.RBRACKET, false),
				new BracePair(CSharpTokenType.LT, CSharpTokenType.GT, false),
				new BracePair(CSharpTokenType.LPARENTH, CSharpTokenType.RPARENTH, false)
		};
	}

	@Override
	public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
		return true;
	}

	@Override
	public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
		return 0;
	}
}
