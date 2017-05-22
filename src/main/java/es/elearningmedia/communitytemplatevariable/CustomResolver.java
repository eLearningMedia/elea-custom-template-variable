package es.elearningmedia.communitytemplatevariable;

import blackboard.data.course.Course;
import blackboard.data.user.User;
import blackboard.util.resolver.ResolverComponent;

/**
 * This handles a custom template variable that uses deferred expansion, to give
 * run-time results of the current user. Template variables are in the following
 * form: '@X@custom_user.first_name@X@'
 */
public class CustomResolver implements ResolverComponent {

	private static final String[] DEFAULT_KEYS = { "custom_user", "custom_course" };
	private User _user = null;
	private Course _course = null;
	private final String[] _keys;

	public CustomResolver(User user, Course course) {
		this(DEFAULT_KEYS, user, course);
	}

	public CustomResolver(String[] keys, User user, Course course) {
		this._user = user;
		this._course = course;
		this._keys = keys;
	}

	public String[] getKeys() {
		return this._keys;
	}

	public String resolve(String method, String attributes[]) {

		if (this._user != null) {

			if ("first_name".equalsIgnoreCase(method)) {
				return this._user.getGivenName();
			}

		}
		
		if (this._course != null) {

			if ("description".equalsIgnoreCase(method)) {
				return this._course.getDescription();
			}

		}
		return null;
	}

}
