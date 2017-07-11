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
package pl.mplauncher.launcher.config.serializer

import org.apache.commons.lang3.Validate
import org.diorite.config.serialization.DeserializationData
import org.diorite.config.serialization.SerializationData
import org.diorite.config.serialization.Serializer

import java.util.function.BiConsumer
import java.util.function.Function

class SerializerBuilderImpl<T> implements SerializerBuilder<T> {

    private Class<T> aClass

    private BiConsumer<T, SerializationData> serializer
    private Function<DeserializationData, T> deserializer

    protected SerializerBuilderImpl() {
    }

    @Override
    SerializerBuilder<T> of(Class<T> aClass) {
        Validate.isTrue(aClass != null)

        this.aClass = aClass
        return this
    }

    @Override
    SerializerBuilder<T> serializer(BiConsumer<T, SerializationData> serializer) {
        Validate.isTrue(serializer != null)

        this.serializer = serializer
        return this
    }

    @Override
    SerializerBuilder<T> deserializer(Function<DeserializationData, T> deserializer) {
        Validate.isTrue(deserializer != null)

        this.deserializer = deserializer;
        return this
    }

    @Override
    Serializer<T> build() {
        Validate.isTrue(aClass != null)
        Validate.isTrue(serializer != null)
        Validate.isTrue(deserializer != null)

        return Serializer.of(aClass, serializer, deserializer)
    }

}

