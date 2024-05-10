// Generated from D:/GUC/Semester 10/Compilers Lab - ACL/Tasks/Task 7/testANTLR/grammar/Task7.g4 by ANTLR 4.13.1
package csen1002.main.task7;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link Task7Visitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
@SuppressWarnings("CheckReturnValue")
public class Task7BaseVisitor<T> extends AbstractParseTreeVisitor<T> implements Task7Visitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitTest(Task7Parser.TestContext ctx) { return visitChildren(ctx); }
}