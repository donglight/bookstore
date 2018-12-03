package org.zdd.bookstore.model.service.impl;

import org.zdd.bookstore.common.pojo.BSResult;
import org.zdd.bookstore.common.utils.BSResultUtil;
import org.zdd.bookstore.model.dao.StoreMapper;
import org.zdd.bookstore.model.dao.UserMapper;
import org.zdd.bookstore.model.entity.Store;
import org.zdd.bookstore.model.entity.User;
import org.zdd.bookstore.model.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class StoreServiceImpl implements IStoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public Store findById(int storeId) {
        Store store = storeMapper.selectByPrimaryKey(storeId);
        User user = userMapper.selectByPrimaryKey(store.getStoreManagerId());
        store.setStoreManagerName(user.getUsername());
        return store;
    }

    @Override
    public Store findStoreByUserId(Integer userId) {

        Example example = new Example(Store.class);
        example.createCriteria().andEqualTo("storeManagerId", userId);
        List<Store> stores = storeMapper.selectByExample(example);
        if(stores !=null && stores.size() > 0){
            return stores.get(0);
        }
        return null;
    }

    @Override

    public List<Store> findStores() {
        List<Store> stores = storeMapper.selectAll();
        stores.forEach(store -> {
            User user = userMapper.selectByPrimaryKey(store.getStoreManagerId());
            if(user != null){
                store.setStoreManagerName(user.getUsername());
            }
        });
        return stores;
    }

    @Override
    @Transactional
    public BSResult updateStore(Store store) {
        store.setUpdated(new Date());
        storeMapper.updateByPrimaryKeySelective(store);
        return BSResultUtil.success();
    }

    @Override
    @Transactional
    public BSResult deleteStore(int storeId) {
        storeMapper.deleteByPrimaryKey(storeId);
        return BSResultUtil.success();
    }

    @Override
    public BSResult addStore(Store store) {
        store.setCreated(new Date());
        store.setUpdated(new Date());
        storeMapper.insert(store);
        return BSResultUtil.success();
    }

}
