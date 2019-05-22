package com.example.demo.test;

import com.example.demo.entity.creditBill;
import com.example.demo.mapper.CreditBillMapper;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MyCsvDataWriter  implements ItemWriter<creditBill> {
    @Resource
    private CreditBillMapper creditBillMappers;

    @Override
    public void write(List<? extends creditBill> items) throws Exception {
        for(creditBill creditBill1:items){
            creditBillMappers.insertSelective(creditBill1);
        }

    }
}
