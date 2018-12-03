package org.zdd.bookstore.pay;


import org.zdd.bookstore.common.pojo.BSResult;
import org.zdd.bookstore.common.utils.BSResultUtil;

public abstract class AbstractPay implements IPayService {

    @Override
    public BSResult pay(PayContext payContext) throws Exception {
        prepareAndPay(payContext);
        afterPay(payContext);
        return BSResultUtil.success();
    }


}
