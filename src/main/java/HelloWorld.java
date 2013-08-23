import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		resp.getWriter().print("Hello from Java!\n");
	}

	public static void main(final String[] args) throws Exception {

		final String PORT = System.getenv("PORT");

		final int port;
		if (PORT == null) {
			/** development */
			port = 8888;
		} else {
			/** heroku dynamic port */
			port = Integer.valueOf(PORT);
		}

		final Server server = new Server(port);

		final ServletContextHandler context = new ServletContextHandler(
				ServletContextHandler.SESSIONS);

		context.setContextPath("/");

		server.setHandler(context);

		context.addServlet(new ServletHolder(new HelloWorld()), "/*");

		server.start();

		server.join();

	}

}
