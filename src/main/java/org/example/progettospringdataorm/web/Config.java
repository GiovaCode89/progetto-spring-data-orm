
package org.example.progettospringdataorm.web;




import org.example.progettospringdataorm.db.dao.impl.ClienteDaoImpl;
import org.example.progettospringdataorm.db.dao.inteface.simple.ClienteDao;
import org.example.progettospringdataorm.db.dao.inteface.simple.GeneralDao;
import org.example.progettospringdataorm.db.entity.Cliente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import javax.sql.DataSource;

//@EnableTransactionManagement = notazione che permette al nostro programma di compiere le transazioni
//@EnableJpaRepositories = abilita l'applicazione ad utilizzare spring data repository
    //basepackage= permette di indicare il package che contiene le interfacce che estendono CrudRepository
    //entityManagerFactoryRef= indica il bean che istanzia l'EntityManagerFactory (obbligatorio per Spring Data Repository)
    //transactionManagerRef= indica il bean che istanzia il TransactionManager (obbligatorio per Spring Data Repository)

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.example.progettospringdataorm.controller")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.example.progettospringdataorm.db.dao.inteface.repository",
                        entityManagerFactoryRef="eMF",
                        transactionManagerRef = "tM")
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
        ds.setUrl("jdbc:mysql://localhost:3306/mio_spring_db_orm?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("beta1710");
        return ds;
    }

    @Bean(name="eMF")
    public LocalContainerEntityManagerFactoryBean getEntityManager(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        //permette di aggiornare la struttura delle tabelle del db in base all'implementazioni delle classi entity
        adapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory= new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(getDbConnection());
        factory.setJpaVendorAdapter(adapter);
        //Specifico il package dove sono contenute le classi Entity
        factory.setPackagesToScan("org.example.progettospringdataorm.db.entity");
        return factory;

    }


    //Restituisce un oggetto che permette di compiere le transazioni
    @Bean(name="tM")
    public PlatformTransactionManager getTransactionManager(){
        JpaTransactionManager jtm= new JpaTransactionManager(getEntityManager().getObject());
        return jtm;
    }



    @Bean
    public ClienteDao getClienteService(){
        return new ClienteDaoImpl();
    }

    @Bean
    public GeneralDao<Cliente> getGeneralDaoCliente(){
        return new ClienteDaoImpl();
    }

}

