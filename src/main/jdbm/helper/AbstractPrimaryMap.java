/*******************************************************************************
 * Copyright 2010 Cees De Groot, Alex Boisvert, Jan Kotek
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package jdbm.helper;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Comparator;

import jdbm.InverseHashView;
import jdbm.PrimaryHashMap;
import jdbm.SecondaryHashMap;
import jdbm.SecondaryKeyExtractor;
import jdbm.SecondaryTreeMap;

public abstract class AbstractPrimaryMap<K, V> extends AbstractMap<K,V> implements PrimaryHashMap<K, V>{

	
	public <A> SecondaryHashMap<A, K,V> secondaryHashMap(String objectName,
			SecondaryKeyExtractor<A, K,V> secondaryKeyExtractor) {
		return SecondaryKeyHelper.secondaryHashMap(objectName,secondaryKeyExtractor,this);
	}

	public <A> SecondaryTreeMap<A, K, V> secondaryTreeMap(String objectName,
			SecondaryKeyExtractor<A, K, V> secondaryKeyExtractor,
			Comparator<A> secondaryKeyComparator) {
		return SecondaryKeyHelper.secondaryTreeMap(objectName,secondaryKeyExtractor,secondaryKeyComparator,this);	
	}

	@SuppressWarnings("unchecked")
	public <A extends Comparable> SecondaryTreeMap<A, K, V> secondaryTreeMap(
			String objectName, SecondaryKeyExtractor<A, K, V> secondaryKeyExtractor) {
		return SecondaryKeyHelper.secondaryTreeMap(objectName,secondaryKeyExtractor,
				ComparableComparator.INSTANCE,this);
	}

	
	public <A> SecondaryHashMap<A, K,V> secondaryHashMapManyToOne(String objectName,
			SecondaryKeyExtractor<Iterable<A>, K,V> secondaryKeyExtractor) {
		return SecondaryKeyHelper.secondaryHashMapManyToOne(objectName,secondaryKeyExtractor,this);
	}

	public <A> SecondaryTreeMap<A, K, V> secondaryTreeMapManyToOne(String objectName,
			SecondaryKeyExtractor<Iterable<A>, K, V> secondaryKeyExtractor,
			Comparator<A> secondaryKeyComparator) {
		return SecondaryKeyHelper.secondarySortedMapManyToOne(objectName,secondaryKeyExtractor,secondaryKeyComparator,this);	
	}
 
	@SuppressWarnings("unchecked")
	public <A extends Comparable> SecondaryTreeMap<A, K, V> secondaryTreeMapManyToOne(
			String objectName, SecondaryKeyExtractor<Iterable<A>, K, V> secondaryKeyExtractor) {
		return SecondaryKeyHelper.secondarySortedMapManyToOne(objectName,secondaryKeyExtractor,
				ComparableComparator.INSTANCE,this);
	}
	
	public InverseHashView<K, V> inverseHashView(String objectName) {
		return SecondaryKeyHelper.inverseHashView(this,objectName);
	}

	public V find(K k) throws IOException {
		return get(k);
	}

}
