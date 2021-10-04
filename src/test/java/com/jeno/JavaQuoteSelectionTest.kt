package com.jeno

import com.intellij.openapi.editor.LogicalPosition
import com.intellij.psi.PsiDocumentManager
import com.intellij.testFramework.EditorTestUtil
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.intellij.lang.annotations.Language

class JavaQuoteSelectionTest : LightJavaCodeInsightFixtureTestCase() {

	@Language("Java") val code =
			"""
public class Test<T> {

	public String simpleString() {
		return "My simple quote string with no inner quotes";
	}
	
	public String stringWithInnerQuotes() {
		return "My simple \"quote\" string with no inner quotes";
	}
	
	public String stringWithInnerSingleQuotes() {
		return "My simple 'quote' string with no inner quotes";
	}
	
}
				""".trimIndent()

	override fun setUp() {
		super.setUp()
		myFixture.configureByText("QuoteTest.java", code)
		PsiDocumentManager.getInstance(project).commitAllDocuments()
	}

	fun testSimpleQuoteSelection() {
		setSelectionAndAssert(3, 15, "My simple quote string with no inner quotes")
	}

	fun testQuoteSelectionWithInnerQuotes() {
		setSelectionAndAssert(7, 45, "My simple \\\"quote\\\" string with no inner quotes")
	}

	fun testQuoteSelectionWithInnerSingleQuotes() {
		setSelectionAndAssert(11, 45, "My simple 'quote' string with no inner quotes")
	}

	private fun setSelectionAndAssert(line: Int, column: Int, assertion: String) {
		editor.caretModel.moveToLogicalPosition(LogicalPosition(line, column))
		editor.selectionModel.removeSelection()
		EditorTestUtil.executeAction(editor, "QuotesSelection.Plugin")
		println("Setting caret at $line:$column and performing complete bracket selection")
		println("Editor text with carets and selection:")
		println()
		println(EditorTestUtil.getTextWithCaretsAndSelections(editor))
		println()
		val selectedText = editor.selectionModel.selectedText
		selectedText.shouldNotBeNull()
		// Trim next lines and stuff and we should end up with the return statement
		selectedText.trimIndent() shouldBe assertion.trimIndent()
	}

}