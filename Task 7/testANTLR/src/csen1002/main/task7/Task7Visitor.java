// Generated from D:/GUC/Semester 10/Compilers Lab - ACL/Tasks/Task 7/testANTLR/grammar/Task7.g4 by ANTLR 4.13.1
package csen1002.main.task7;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Task7Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Task7Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Task7Parser#test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest(Task7Parser.TestContext ctx);
}