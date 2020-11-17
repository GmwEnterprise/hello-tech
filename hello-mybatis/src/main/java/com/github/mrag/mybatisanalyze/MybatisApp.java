package com.github.mrag.mybatisanalyze;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisApp {

    public static void main(String[] args) throws IOException {
        InputStream config = Resources.getResourceAsStream("classpath:mybatis-conf.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        SqlSession session = factory.openSession();
        // session.getMapper()
    }
}
