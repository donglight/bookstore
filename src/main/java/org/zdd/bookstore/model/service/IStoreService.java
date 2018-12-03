package org.zdd.bookstore.model.service;

import org.zdd.bookstore.common.pojo.BSResult;
import org.zdd.bookstore.model.entity.Store;

import java.util.List;

public interface IStoreService {
    Store findStoreByUserId(Integer userId);

    Store findById(int storeId);

    List<Store> findStores();

    BSResult updateStore(Store store);

    BSResult deleteStore(int storeId);

    BSResult addStore(Store store);
}
