package com.myself.service;

import com.myself.beans.CacheKeyConstants;

public interface SysCacheService {
    String getFromCache(CacheKeyConstants prefix, String... keys);

    void saveCache(String toSavedValue, int timeoutSeconds, CacheKeyConstants prefix);

    void saveCache(String toSavedValue, int timeoutSeconds, CacheKeyConstants prefix,String... keys);
}
