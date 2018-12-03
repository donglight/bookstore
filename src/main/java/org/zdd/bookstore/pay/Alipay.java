package org.zdd.bookstore.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.zdd.bookstore.model.entity.BookInfo;
import org.zdd.bookstore.model.entity.Orders;
import org.zdd.bookstore.model.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class Alipay extends AbstractPay{

    @Autowired
    private AlipayConfig alipayConfig;

    @Override
    public void prepareAndPay(PayContext payContext) throws IOException {
        AlipayClient alipayClient = new DefaultAlipayClient(
                alipayConfig.getOpenApiDomain(),
                alipayConfig.getAppId(),
                alipayConfig.getMerchantPrivateKey(),
                alipayConfig.getFormat(),
                alipayConfig.getCharset(),
                alipayConfig.getAlipayPublicKey(),
                alipayConfig.getSignType()); //获得初始化的AlipayClient

        Orders order = payContext.getOrders();
        BookInfo book = payContext.getBookInfos().get(0);
        HttpServletResponse httpResponse = payContext.getResponse();



        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());
        alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+order.getOrderId()+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":\""+order.getPayment()+"\"," +
                "    \"subject\":\""+book.getOutline()+"\"," +
                "    \"body\":\""+book.getOutline()+"\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }"+
                "  }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + alipayConfig.getCharset());
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @Override
    public void afterPay(PayContext payContext) {
    }
}
