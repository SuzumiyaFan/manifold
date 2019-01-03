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

package manifold.api.json.schema;

import java.util.List;
import java.util.function.Supplier;
import manifold.api.json.IJsonParentType;
import manifold.api.json.IJsonType;

public class LazyRefJsonType implements IJsonType
{
  private final Supplier<IJsonType> _supplier;
  private Object _defaultValue;

  LazyRefJsonType( Supplier<IJsonType> supplier )
  {
    _supplier = supplier;
  }

  public IJsonType resolve()
  {
    IJsonType type = _supplier.get();
    while( type instanceof LazyRefJsonType )
    {
      type = ((LazyRefJsonType)type).resolve();
    }
    if( _defaultValue != null )
    {
      type.setDefaultValue( _defaultValue );
    }
    return type;
  }

  @Override
  public String getName()
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getIdentifier()
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public IJsonParentType getParent()
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object getDefaultValue()
  {
    return _defaultValue;
  }
  @Override
  public IJsonType setDefaultValue( Object value )
  {
    _defaultValue = value;
    return this;
  }

  @Override
  public List<IJsonType> getDefinitions()
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setDefinitions( List<IJsonType> definitions )
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean equalsStructurally( IJsonType type2 )
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public IJsonType merge( IJsonType type )
  {
    throw new UnsupportedOperationException();
  }
}
