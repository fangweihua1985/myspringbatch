package com.example.demo.mapper;

import com.example.demo.entity.creditBill;
import com.example.demo.entity.creditBillExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface CreditBillMapper {
    int countByExample(creditBillExample example);

    int deleteByExample(creditBillExample example);

    int deleteByPrimaryKey(Long id);

    int insert(creditBill record);

    int insertSelective(creditBill record);

    List<creditBill> selectByExample(creditBillExample example);

    creditBill selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") creditBill record, @Param("example") creditBillExample example);

    int updateByExample(@Param("record") creditBill record, @Param("example") creditBillExample example);

    int updateByPrimaryKeySelective(creditBill record);

    int updateByPrimaryKey(creditBill record);


    List<creditBill>  selectPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
}