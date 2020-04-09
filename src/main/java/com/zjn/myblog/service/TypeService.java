package com.zjn.myblog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjn.myblog.entity.Type;

public interface TypeService {
    int saveType(Type type);

    Type getType(Long id);

    Page<Type> listType(Integer pageNum, Integer pageSize);

    void updateType(Type type);

    void deleteType(Long id);

    boolean existType(String name);
}
