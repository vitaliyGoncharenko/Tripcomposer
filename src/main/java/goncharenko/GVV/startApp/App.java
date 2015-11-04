package goncharenko.GVV.startApp;

import goncharenko.GVV.config.AppConfig;
import goncharenko.GVV.manager.Manager;
import goncharenko.GVV.manager.impl.ManagerImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Vitaliy on 02.11.2015.
 */
public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        Manager managerService = ctx.getBean(ManagerImpl.class);
        managerService.saveResponse();

        System.exit(0);
    }
}
