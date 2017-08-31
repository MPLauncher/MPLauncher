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

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.Validate;
import org.diorite.libs.com.google.gson.JsonObject;

import java.util.concurrent.CompletableFuture;

final class YggdrasilImpl implements Yggdrasil {

    private final Gson gson = new Gson();
    private final OkHttpClient client = new YggdrasilClient();

    @Override
    public CompletableFuture<JsonObject> authenticate(JsonObject payload) {
        Validate.notNull(payload);

        return CompletableFuture.supplyAsync(() -> {
            RequestBody body = RequestBody.create(JSON, gson.toJson(payload));
            Request request = new Request.Builder()
                    .url(URL + "authenticate")
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();

                return gson.fromJson(response.body().string(), JsonObject.class);
            } catch (Exception ex) {
                return null;
            }
        });
    }

    @Override
    public CompletableFuture<JsonObject> refresh(JsonObject payload) {
        Validate.notNull(payload);

        return CompletableFuture.supplyAsync(() -> {
            RequestBody body = RequestBody.create(JSON, gson.toJson(payload));
            Request request = new Request.Builder()
                    .url(URL + "refresh")
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();

                return gson.fromJson(response.body().string(), JsonObject.class);
            } catch (Exception ex) {
                return null;
            }
        });
    }

    @Override
    public CompletableFuture<JsonObject> validate(JsonObject payload) {
        Validate.notNull(payload);

        return CompletableFuture.supplyAsync(() -> {
            RequestBody body = RequestBody.create(JSON, gson.toJson(payload));
            Request request = new Request.Builder()
                    .url(URL + "validate")
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return new JsonObject();
                }

                return null;
            } catch (Exception ex) {
                return null;
            }
        });
    }

    @Override
    public CompletableFuture<JsonObject> signout(JsonObject payload) {
        Validate.notNull(payload);

        return CompletableFuture.supplyAsync(() -> {
            RequestBody body = RequestBody.create(JSON, gson.toJson(payload));
            Request request = new Request.Builder()
                    .url(URL + "signout")
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();

                return gson.fromJson(response.body().string(), JsonObject.class);
            } catch (Exception ex) {
                return null;
            }
        });
    }

}
