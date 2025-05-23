/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.maven.api.services;

import java.util.Optional;

import org.apache.maven.api.ExtensibleEnum;
import org.apache.maven.api.Service;
import org.apache.maven.api.annotations.Nonnull;

/**
 * Registry for extensible enum values that allows looking up enum instances by their identifiers.
 * <p>
 * This service provides access to all registered instances of a specific extensible enum type.
 * It's used internally by Maven and can also be used by plugins and extensions to access
 * custom enum values that have been registered through SPI providers.
 *
 * @param <T> the specific type of extensible enum managed by this registry
 * @since 4.0.0
 */
public interface ExtensibleEnumRegistry<T extends ExtensibleEnum> extends Service {
    @Nonnull
    Optional<T> lookup(@Nonnull String id);

    @Nonnull
    default T require(@Nonnull String id) {
        return lookup(id).orElseThrow(() -> new IllegalArgumentException("Unknown extensible enum value '" + id + "'"));
    }
}
