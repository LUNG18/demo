package com.example.configclient.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.configclient.Enums.DataSourceEnum;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    public final static String MAPPER_LOCATION = "classpath:mapper/*.xml";
    public final static String POJO_PACKAGE = "com.example.configclient.pojo";

    /**
     * master 数据源
     */
    @Value("${murl}")
    private String m_url;
    @Value("${musername}")
    private String m_username;
    @Value("${mpassword}")
    private String m_password;
    @Value("${mdriver}")
    private String m_driver;

    /**
     * slave_1 数据源
     */
    @Value("${s1url}")
    private String s_1_url;
    @Value("${s1username}")
    private String s_1_username;
    @Value("${s1password}")
    private String s_1_password;
    @Value("${s1driver}")
    private String s_1_driver;

    @Bean
    public DynamicDataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();

        //master--jdbc配置
        DruidDataSource m_dataSource = new DruidDataSource();
        m_dataSource.setDriverClassName(m_driver);
        m_dataSource.setUrl(m_url);
        m_dataSource.setUsername(m_username);
        m_dataSource.setPassword(m_password);

        //slave_1--jdbc配置
        DruidDataSource s_1_dataSource = new DruidDataSource();
        s_1_dataSource.setDriverClassName(s_1_driver);
        s_1_dataSource.setUrl(s_1_url);
        s_1_dataSource.setUsername(s_1_username);
        s_1_dataSource.setPassword(s_1_password);

        Map<Object, Object> map = new HashMap<>();
        map.put(DataSourceEnum.Master.getValue(), m_dataSource);
        map.put(DataSourceEnum.Slave1.getValue(), s_1_dataSource);

        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(s_1_dataSource);

        return dynamicDataSource;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource")DataSource dynamicDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dynamicDataSource);
        sessionFactory.setTypeAliasesPackage(POJO_PACKAGE);
        sessionFactory.setMapperLocations(
            new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION)
        );
        return sessionFactory.getObject();
    }
}
