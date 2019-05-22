package com.example.demo.test;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class MyCsvDataReader {

    private FlatFileItemReader<CreditBill> reader;

    public ItemReader<CreditBill> read(){
        reader = new FlatFileItemReader<CreditBill>();
        reader.setResource(new ClassPathResource("ch02/data/credit-card-bill-201303.csv"));
        reader.setLineMapper(new DefaultLineMapper<CreditBill>() {{ //在此处对CVS文件的数据和领域模型类做对应映射
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "accountID","name","amount","date","address"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<CreditBill>() {{
                setTargetType(CreditBill.class);
            }});
        }});
        return reader;
    }
}
