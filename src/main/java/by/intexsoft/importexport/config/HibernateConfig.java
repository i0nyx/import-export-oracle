package by.intexsoft.importexport.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan("by.intexsoft.importexport")
@PropertySources({@PropertySource("classpath:oracle.properties"),
                  @PropertySource("classpath:hibernate.properties")})
@EnableJpaRepositories("by.intexsoft.importexport.repositories")
public class HibernateConfig {
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String HIBERNATE_HBM2DDL = "hibernate.hbm2ddl.auto";
    @Value("${oracle.url}")
    private String url;
    @Value("${oracle.user}")
    private String user;
    @Value("${oracle.password}")
    private String password;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateDDL;
    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.db.driver}")
    private String hibernateDbDriver;
    @Value("${entitymanager.packages.to.scan}")
    private String pathPojo;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(hibernateDbDriver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(pathPojo);
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        return new Properties() {{
            setProperty(HIBERNATE_HBM2DDL, hibernateDDL);
            setProperty(HIBERNATE_DIALECT, hibernateDialect);
            setProperty(HIBERNATE_SHOW_SQL, hibernateShowSql);
        }};
    }
}