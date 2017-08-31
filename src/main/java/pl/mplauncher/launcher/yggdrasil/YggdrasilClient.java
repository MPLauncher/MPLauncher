/*
   Copyright 2017 MPLauncher Team

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package pl.mplauncher.launcher.yggdrasil;

import okhttp3.*;
import okhttp3.internal.ws.RealWebSocket;
import org.apache.commons.lang3.Validate;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import java.util.Random;

public class YggdrasilClient extends OkHttpClient {

    private final OkHttpClient backend;

    public YggdrasilClient(OkHttpClient backend) {
        Validate.notNull(backend);

        this.backend = backend.newBuilder()
                .addInterceptor(chain -> chain.proceed(chain.request().newBuilder().addHeader(
                        "Content-Type", "application/json").build())).build();
    }

    public YggdrasilClient() {
        this(new OkHttpClient());
    }

    @Override
    public int readTimeoutMillis() {
        return backend.readTimeoutMillis();
    }

    @Override
    public int writeTimeoutMillis() {
        return backend.writeTimeoutMillis();
    }

    @Override
    public int pingIntervalMillis() {
        return backend.pingIntervalMillis();
    }

    @Override
    public Proxy proxy() {
        return backend.proxy();
    }

    @Override
    public ProxySelector proxySelector() {
        return backend.proxySelector();
    }

    public ConnectionPool connectionPool() {
        return backend.connectionPool();
    }

    public boolean followSslRedirects() {
        return backend.followSslRedirects();
    }

    public boolean followRedirects() {
        return backend.followRedirects();
    }

    public boolean retryOnConnectionFailure() {
        return backend.retryOnConnectionFailure();
    }

    public Dispatcher dispatcher() {
        return backend.dispatcher();
    }

    public List<Protocol> protocols() {
        return backend.protocols();
    }

    public List<ConnectionSpec> connectionSpecs() {
        return backend.connectionSpecs();
    }

    public List<Interceptor> interceptors() {
        return backend.interceptors();
    }

    public List<Interceptor> networkInterceptors() {
        return backend.networkInterceptors();
    }

    public Call newCall(Request request) {
        Validate.notNull(request);

        return backend.newCall(request);
    }

    public WebSocket newWebSocket(Request request, WebSocketListener listener) {
        Validate.notNull(request);
        Validate.notNull(listener);

        RealWebSocket webSocket = new RealWebSocket(request, listener, new Random());
        webSocket.connect(this);
        return webSocket;
    }

    public OkHttpClient.Builder newBuilder() {
        return backend.newBuilder();
    }

}
