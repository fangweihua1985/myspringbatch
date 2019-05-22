package com.example.demo.test;

import com.example.demo.entity.creditBill;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MyCsvDataProcessor implements ItemProcessor<CreditBill, creditBill> {
    @Override
    public creditBill process(CreditBill item) throws Exception {
        creditBill bill = new creditBill();
        BeanUtils.copyProperties(bill,item);
        return bill;
    }

}
