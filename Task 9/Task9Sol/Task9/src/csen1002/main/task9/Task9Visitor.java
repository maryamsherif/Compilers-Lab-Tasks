// Generated from D:/GUC/Semester 10/Compilers Lab - ACL/Tasks/Task 9/Task9Sol/Task9/grammar/Task9.g4 by ANTLR 4.13.1
package csen1002.main.task9;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Task9Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Task9Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Task9Parser#s}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitS(Task9Parser.SContext ctx);
	/**
	 * Visit a parse tree produced by {@link Task9Parser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitF(Task9Parser.FContext ctx);
	/**
	 * Visit a parse tree produced by {@link Task9Parser#t}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitT(Task9Parser.TContext ctx);
	/**
	 * Visit a parse tree produced by {@link Task9Parser#n}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitN(Task9Parser.NContext ctx);
	/**
	 * Visit a parse tree produced by {@link Task9Parser#d}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitD(Task9Parser.DContext ctx);
}