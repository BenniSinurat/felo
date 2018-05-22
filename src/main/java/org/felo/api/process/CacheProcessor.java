package org.felo.api.process;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class CacheProcessor implements Callable {
	private HazelcastInstance instance;

	public Object onCall(MuleEventContext eventContext) throws Exception {
		HashMap payload = (HashMap) eventContext.getMessage().getPayload();
		String method = (String) payload.get("methodType");
		if (method.equalsIgnoreCase("PUT")) {
			String mapName = (String) payload.get("name");
			String key = (String) payload.get("key");
			Object value = payload.get("value");
			putCache(mapName, key, value);
			return null;
		}
		if (method.equalsIgnoreCase("GET")) {
			String mapName = (String) payload.get("name");
			String key = (String) payload.get("key");
			return getCache(mapName, key);
		}
		if (method.equalsIgnoreCase("DELETE")) {
			String mapName = (String) payload.get("name");
			String key = (String) payload.get("key");
			removeCache(mapName, key);
			return null;
		}
		if (method.equalsIgnoreCase("EXPIRED")) {
			String mapName = (String) payload.get("name");
			String key = (String) payload.get("key");
			Object value = payload.get("value");
			Long ttl = Long.parseLong((String) payload.get("ttl"));
			TimeUnit timeunit = TimeUnit.SECONDS;
			putCacheTTL(mapName, key, value, ttl, timeunit);
			return null;
		}
		throw new Exception("INVALID_METHOD");
	}

	public void putCache(String mapName, String key, Object value) {
		IMap cacheMap = this.instance.getMap(mapName);
		cacheMap.put(key, value);
	}
	
	public void putCacheTTL(String mapName, String key, Object value, long ttl, TimeUnit timeunit) {
		IMap cacheMap = this.instance.getMap(mapName);
		cacheMap.put(key, value,ttl,timeunit);
	}

	public Object getCache(String mapName, String key) {
		IMap cacheMap = this.instance.getMap(mapName);
		return cacheMap.get(key);
	}

	public void removeCache(String mapName, String key) {
		IMap cacheMap = this.instance.getMap(mapName);
		cacheMap.remove(key);
	}

	public HazelcastInstance getInstance() {
		return this.instance;
	}

	public void setInstance(HazelcastInstance instance) {
		this.instance = instance;
	}
}