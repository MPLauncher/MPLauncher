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

import org.diorite.config.serialization.DeserializationData;
import org.diorite.config.serialization.SerializationData;
import org.diorite.config.serialization.Serializer;

import java.util.function.BiConsumer;
import java.util.function.Function;

public interface SerializerBuilder<T> {

    static <T> SerializerBuilder<T> create() {
        return new SerializerBuilderImpl<>();
    }

    SerializerBuilder<T> of(Class<T> aClass);

    SerializerBuilder<T> serializer(BiConsumer<T, SerializationData> serializer);

    SerializerBuilder<T> deserializer(Function<DeserializationData, T> deserializer);

    Serializer<T> build();

}