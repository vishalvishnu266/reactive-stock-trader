import com.lightbend.lagom.javadsl.api.transport.TransportException;
import com.typesafe.config.Config;
import play.Environment;
import play.api.OptionalSourceMapper;
import play.api.UsefulException;
import play.http.DefaultHttpErrorHandler;
import play.api.routing.Router;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.concurrent.CompletionStage;


class CustomHttpErrorHandler extends DefaultHttpErrorHandler {

    @Inject
    public CustomHttpErrorHandler(Config configuration,
                                  Environment environment,
                                  OptionalSourceMapper sourceMapper,
                                  Provider<Router> routes) {
        super(configuration, environment, sourceMapper, routes);
    }

    @Override
    protected CompletionStage<Result> onDevServerError(Http.RequestHeader request, UsefulException exception) {
        if (exception.cause instanceof TransportException) {
            // TODO Pull out the Lagom HTTP message and return that instead of double wrapped
            return super.onDevServerError(request, exception);
        } else {
            return super.onDevServerError(request, exception);
        }
    }
}
