package com.zjn.myblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjn.myblog.dao.TypeMapper;
import com.zjn.myblog.entity.Type;
import com.zjn.myblog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public int saveType(Type type) {
        return typeMapper.insert(type);
    }

    @Override
    public Type getType(Long id) {
        return typeMapper.selectById(id);
    }

    @Override
    public Page<Type> listType(Integer pageNum, Integer pageSize) {
        QueryWrapper<Type> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        Page<Type> page = typeMapper.selectPage(new Page<Type>(pageNum, pageSize), queryWrapper);
        PagePlus<Type> pagePlus = new PagePlus<>();
        BeanUtils.copyProperties(page, pagePlus);
        pagePlus.pageCount = (page.getTotal() + page.getSize() - 1) / page.getSize();
        return pagePlus;
    }

    public class PagePlus<T> extends Page<T> {
        public long pageCount;
    }

    @Override
    public void updateType(Type type) {
        typeMapper.updateById(type);
    }

    @Override
    public void deleteType(Long id) {
        typeMapper.deleteById(id);
    }

    @Override
    public boolean existType(String name) {
        Integer count = typeMapper.selectCount(new QueryWrapper<Type>().eq("name", name));
        return count > 0;
    }
}
