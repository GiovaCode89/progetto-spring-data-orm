package org.example.progettospringdataorm.web;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }


    //Indica all'app il file di configurazione
    @Override
    protected Class<?>[] getServletConfigClasses()
    {return new Class <?>[] {Config.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    // crea la dispatchet servlet
    @Override
    protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        DispatcherServlet ds= new DispatcherServlet(servletAppContext);
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }
}
