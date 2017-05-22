package es.elearningmedia.communitytemplatevariable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import blackboard.data.course.Course;
import blackboard.data.user.User;
import blackboard.persist.BbPersistenceManager;
import blackboard.platform.context.Context;
import blackboard.platform.context.ContextEntry;
import blackboard.platform.context.ContextHandler;
import blackboard.platform.context.ContextManagerFactory;
import blackboard.platform.security.EntitlementList;
import blackboard.platform.security.Entitlements;
import blackboard.platform.security.SecurityContext;
import blackboard.util.resolver.Resolver;

//Extends ContextHandler class to include our variable resolver
public class CustomContextHandler implements ContextHandler {
	
	public CustomContextHandler() {

	}

	public Entitlements getEffectiveEntitlements(Context ctx) {
		return new EntitlementList();
	}

	public Entitlements getRestrictedEntitlements(Context ctx) {
		return null;
	}

	public List<SecurityContext> getSecurityContexts(Context unused) {
		return new ArrayList<SecurityContext>();
	}
	//Here is were we inyect our custom resolver
	public List<ContextEntry> resolveKeys(HttpServletRequest request,
			BbPersistenceManager unused) {
		Context ctx = ContextManagerFactory.getInstance().getContext();
		User user = ctx.getUser();
		Course course = ctx.getCourse();
		CustomResolver resolver = new CustomResolver(user, course);
		Resolver.attachResolverToContext(resolver);
		return new LinkedList<ContextEntry>();
	}
	
}
