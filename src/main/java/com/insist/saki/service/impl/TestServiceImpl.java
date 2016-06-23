package com.insist.saki.service.impl;

import com.insist.saki.entity.TestEntity;
import com.insist.saki.dao.TestDao;
import com.insist.saki.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    public Object save(TestEntity test) {
        return testDao.save(test);
    }

    public TestEntity get(Long id) {
        return testDao.getById(id);
    }
}
