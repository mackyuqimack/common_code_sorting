import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * 监听器往设置全局变量
 */
@Component
public class ContextLoadListener implements ApplicationListener<ContextRefreshedEvent> {
    @Value("${UPLOAD_URL}")
    private String UPLOAD_URL;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 将 ApplicationContext 转化为 WebApplicationContext
        WebApplicationContext webApplicationContext =
                (WebApplicationContext) contextRefreshedEvent.getApplicationContext();
        // 从 webApplicationContext 中获取  servletContext
        ServletContext servletContext = webApplicationContext.getServletContext();
        // servletContext设置值
        servletContext.setAttribute("UPLOAD_URL", UPLOAD_URL);
    }
}