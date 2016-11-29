package com.letgo.letgotv.business.resolver;

import com.letgo.letgotv.backend.BackendManager;

/**
 * Base Resolver Class: all Business resolver must extend this
 */
public class BaseBusinessResolver {

    protected BackendManager backendManager;

    public BaseBusinessResolver(BackendManager backendManager) {

        this.backendManager = backendManager;
    }
}

