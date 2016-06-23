package com.insist.saki.service;

import com.insist.saki.entity.TestEntity;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface TestService {

    public Object save(TestEntity test);

    TestEntity get(Long id);
}
