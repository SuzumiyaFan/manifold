/*
 * Copyright (c) 2019 - Manifold Systems LLC
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

package manifold.preprocessor;


import org.junit.Test;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

#define TRUE
#define FOO

public class ExpressionTest {
  @Test
  public void testBaseline() {
    boolean success = false;

    #if FALSE
    fail();
    #endif

     #if TRUE
      success = true;
     #endif
    assertTrueAndFlip(success);
  }

  public void testAnd() {
    boolean success = false;

    #if FALSE
    fail();
    #endif

     #if TRUE
      success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if TRUE && TRUE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if TRUE && TRUE && TRUE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

    #if TRUE && FALSE
    fail();
    #endif

    #if FALSE && TRUE
    fail();
    #endif

    #if TRUE && FALSE && TRUE
    fail();
    #endif

    #if FALSE && TRUE && FALSE
    fail();
    #endif

    #if FALSE && FALSE && FALSE
    fail();
    #endif
  }

  public void testOr() {
    boolean success = false;

     #if TRUE || TRUE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if FALSE || TRUE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if TRUE || FALSE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

    #if FALSE || FALSE
    fail();
    #endif

     #if TRUE || FALSE || TRUE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if FALSE || TRUE || FALSE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

    #if FALSE || FALSE || FALSE
    fail();
    #endif
  }

  @Test
  public void testPrecedence() {
    boolean success = false;

     #if TRUE || TRUE && FALSE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if TRUE || FALSE && TRUE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if TRUE || FALSE && FALSE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if FALSE || TRUE && FALSE
    fail();
     #endif

     #if FALSE || FALSE && TRUE
    fail();
     #endif

     #if FALSE && FALSE || FALSE
    fail();
     #endif

     #if FALSE && TRUE || FALSE
    fail();
     #endif

     #if FALSE && TRUE || FALSE
    fail();
     #endif

     #if FALSE && FALSE || TRUE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if FALSE && (FALSE || TRUE)
    fail();
     #endif
  }

  @Test
  public void testNot() {
    boolean success = false;

     #if !FALSE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if !(FALSE)
      success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if !FALSE && TRUE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if !!FALSE
    fail();
     #endif

     #if !TRUE
    fail();
     #endif

     #if !(TRUE)
      fail();
     #endif

     #if !!TRUE
    success = true;
     #endif
      success = assertTrueAndFlip(success);
  }

  @Test
  public void testEquality() {
    boolean success = false;

     #if FALSE == FALSE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if TRUE == TRUE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if TRUE == FOO
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if TRUE == FALSE
    fail();
     #endif

     #if FALSE == TRUE
    fail();
     #endif

     #if FALSE == FOO
    fail();
     #endif

     #if FALSE == FALSE || FALSE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if FALSE || FALSE == FALSE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if FALSE == FALSE && TRUE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if FALSE == TRUE || TRUE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if TRUE || TRUE == FALSE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if (TRUE || FALSE) == FALSE
    fail();
     #endif

     #if TRUE || (FALSE == FALSE)
    success = true;
     #endif
  }

  @Test
  public void testNotEquality() {
    boolean success = false;

     #if FALSE != FALSE
    fail();
     #endif

     #if TRUE != TRUE
    fail();
     #endif

     #if TRUE != FOO
    fail();
     #endif

     #if TRUE != FALSE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if FALSE != TRUE
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if FALSE != FOO
    success = true;
     #endif
      success = assertTrueAndFlip(success);

     #if FALSE != FALSE || FALSE
    fail();
     #endif

     #if FALSE || FALSE != FALSE
    fail();
     #endif

     #if FALSE != FALSE && TRUE
    fail();
     #endif
  }

  private boolean assertTrueAndFlip( boolean cond )
  {
    assertTrue( cond );
    return false;
  }
}
