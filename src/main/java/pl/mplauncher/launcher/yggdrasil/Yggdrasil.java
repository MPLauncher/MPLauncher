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

import okhttp3.MediaType;
import org.diorite.libs.com.google.gson.JsonObject;

import java.util.concurrent.CompletableFuture;

public interface Yggdrasil {

    String URL = "https://authserver.mojang.com/";
    String CONTENT_TYPE = "application/json";
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    static Yggdrasil create() {
        return new YggdrasilImpl();
    }

    CompletableFuture<JsonObject> authenticate(JsonObject payload);

    CompletableFuture<JsonObject> refresh(JsonObject payload);

    CompletableFuture<JsonObject> validate(JsonObject payload);

    CompletableFuture<JsonObject> signout(JsonObject payload);

}
