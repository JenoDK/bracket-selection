package com.jeno

import com.intellij.openapi.editor.LogicalPosition
import com.intellij.psi.PsiDocumentManager
import com.intellij.testFramework.EditorTestUtil
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.intellij.lang.annotations.Language

class JavaBracketSelectionTest : LightJavaCodeInsightFixtureTestCase() {

	@Language("Java") val code =
			"""
public class Test<T> {

	public Boolean test() {
		String myBooleanString = true ? "true" : (false ? "false" : "false");
		return "true".equals("false") || "false".equals("true".concat("false".length() + "*"));
	}
	
	private static List<Hobby> getHobbies(String...keywords) {
		int x = 5;
		List<String> vector1 = new ArrayList<>();
		List<String> vector2 = new ArrayList<>();
		if (x != vector1.size() && x != vector2.size());
		return null;
	}
	
	public Map<String, Map<String, Exception>> mapReturnMethod() {
		return new HashMap<>();
	}
}
				""".trimIndent()

	override fun setUp() {
		super.setUp()
		myFixture.configureByText("BracketTest.java", code)
		PsiDocumentManager.getInstance(project).commitAllDocuments()
	}

	fun testOnMethodBracketSelection() {
		setSelectionAndAssert(3, 15, """
						String myBooleanString = true ? "true" : (false ? "false" : "false");
						return "true".equals("false") || "false".equals("true".concat("false".length() + "*"));
			""")
	}

	fun testOnMethodBracketSelectionWithCaretAfterInMethodBracketPairs() {
		setSelectionAndAssert(4, 15, """
						String myBooleanString = true ? "true" : (false ? "false" : "false");
						return "true".equals("false") || "false".equals("true".concat("false".length() + "*"));
			""")
	}

	fun testInMethodBracketPairSelection() {
		setSelectionAndAssert(3, 55, "false ? \"false\" : \"false\"")
	}

	fun testInMethodBracketPairSelectionWithCaretBeforeLBracket() {
		// In ".equals<HERE>(", there was a bug that the selection would be the contents of that bracket pair
		setSelectionAndAssert(4, 55, """
						String myBooleanString = true ? "true" : (false ? "false" : "false");
						return "true".equals("false") || "false".equals("true".concat("false".length() + "*"));
			""")
		setSelectionAndAssert(4, 56, "\"true\".concat(\"false\".length() + \"*\")")
	}

	fun testInMethodBracketPairSelectionWithCaretBehindRBracket() {
		// In "vector1.size()<HERE>", there was a bug that the selection would not be anything
		// because it tried to select between "()"
		setSelectionAndAssert(11, 31, "x != vector1.size() && x != vector2.size()")
	}

	fun testGreaterAndLessThanSelection() {
		// Caret at "public Map<<HERE>String, Map<String, Exception>>"
		setSelectionAndAssert(15, 15, "String, Map<String, Exception>")
		setSelectionAndAssert(15, 27, "String, Exception")
	}


	private fun setSelectionAndAssert(line: Int, column: Int, assertion: String) {
		editor.caretModel.moveToLogicalPosition(LogicalPosition(line, column))
		editor.selectionModel.removeSelection()
		EditorTestUtil.executeAction(editor, "BracketSelection.Plugin.All")
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