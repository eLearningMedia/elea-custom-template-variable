package es.elearningmedia;

import javax.servlet.http.HttpServletRequest;

import blackboard.platform.context.Context;
import blackboard.platform.context.ContextManagerFactory;
import blackboard.util.resolver.ResolverComponent;

/**
 * This handles a custom template variable that uses deferred expansion, to give
 * run-time results of the current user. Template variables are in the following
 * form: '@X@user.first_name@X@'
 */
public class CustomResolver implements ResolverComponent {

	private static HttpServletRequest _request = null;

	public CustomResolver(HttpServletRequest request) {
		_request = request;
	}

	public String[] getKeys() {

		return (new String[] { "user" }); // the first part of the template
											// variable (@X@ tag) - first_name
											// in @X@user.first_name@X@
	}

	public String resolve(String method, String attributes[]) {
		String varout = null;

		if ("first_name".equalsIgnoreCase(method)) { // the second part of the
														// @X@ tag - first_name
														// in
														// @X@user.first_name@X@

			try {

				Context ctx = ContextManagerFactory.getInstance().getContext();
				ContextManagerFactory.getInstance().setContext(_request);
				// The variable show a greeting using the user given name and
				// family name with the date and time on page load
				varout = ctx.getUser().getGivenName();

				return varout;

			} catch (Exception e) {

			} finally {
				ContextManagerFactory.getInstance().releaseContext();
			}

		}
		return null;
	}

}
