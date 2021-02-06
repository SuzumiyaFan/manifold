/*
 * Copyright (c) 2018 - Manifold Systems LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package manifold.api.type;

import com.sun.tools.javac.api.BasicJavacTask;
import manifold.internal.javac.TypeProcessor;

public interface ICompilerComponent
{
  void init( BasicJavacTask javacTask, TypeProcessor typeProcessor );

  default boolean isSuppressed( String issueKey )
  {
    return false;
  }

  /**
   * Called when the JavacPlugin initializes and whenever the compiler context changes e.g., when annotation processors
   * make rounds. This is where, if need be, you hack into the compiler before compilation starts.
   */
  default void tailorCompiler()
  {
  }
}
