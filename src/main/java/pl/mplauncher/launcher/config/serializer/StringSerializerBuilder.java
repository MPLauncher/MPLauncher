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
package pl.mplauncher.launcher.config.serializer;

import org.diorite.config.serialization.StringSerializer;

import java.util.function.Function;

public interface StringSerializerBuilder<T> {

    static <T> StringSerializerBuilder<T> create() {
        return new StringSerializerBuilderImpl<>();
    }

    StringSerializerBuilder<T> of(Class<T> aClass);

    StringSerializerBuilder<T> serializer(Function<T, String> serializer);

    StringSerializerBuilder<T> deserializer(Function<String, T> deserializer);

    StringSerializer<T> build();

}
