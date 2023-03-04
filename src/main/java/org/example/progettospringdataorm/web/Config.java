
package org.example.progettospringdataorm.web;

/*
import org.example.progettospringdata.db.dao.CategoriaDao;
import org.example.progettospringdata.db.dao.ClienteDao;
import org.example.progettospringdata.db.dao.ProdottoDao;
import org.example.progettospringdata.db.dao.impl.CategoriaDaoImpl;
import org.example.progettospringdata.db.dao.impl.ClienteDaoImpl;
import org.example.progettospringdata.db.dao.impl.ProdottoDaoImpl;

 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.example.progettospringdata.controller")
public class Config {

    //Creo il FreeMarkerViewResolver
    @Bean
    public FreeMarkerViewResolver createResolver(){
        FreeMarkerViewResolver resolver= new FreeMarkerViewResolver();
        resolver.setPrefix("");
        //setSuffix("...") indica il formato dei file da prendere nella cartella delle view
        resolver.setSuffix(".ftl");
        return resolver;
    }


    //Creo il FreeMarkerConfigurer
    @Bean
    public FreeMarkerConfigurer configureFreeMarker(){
        FreeMarkerConfigurer config= new FreeMarkerConfigurer();
        //'setTemplateLoaderPath("...")' indica la path dove vi sono le view
        config.setTemplateLoaderPath("/WEB-INF/view/");
        return config;
    }

    //creo connessione al db
    @Bean
    public DataSource getDbConnection(){
        DriverManagerDataSource ds= new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/mio_spring_db?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("beta1710");
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManager(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);

        LocalContainerEntityManagerFactoryBean factory= new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(getDbConnection());
        factory.setJpaVendorAdapter(adapter);
        factory.setPackagesToScan("org.example.progettospringdataorm.db");
        return factory;

    }


/*
    @Bean
    public ClienteDao getClienteService(){
        return new ClienteDaoImpl(getDbConnection());
    }

    @Bean
    public ProdottoDao getProdottoService(){
        return new ProdottoDaoImpl(getDbConnection());
    }

    @Bean
    public CategoriaDao getCategoriaService(){
        return new CategoriaDaoImpl(getDbConnection());
    }
*/

}

