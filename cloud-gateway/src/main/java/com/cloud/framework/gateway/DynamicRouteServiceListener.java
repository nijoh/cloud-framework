//package cloudGateway;
//
//import com.alibaba.cloud.nacos.NacosConfigManager;
//import com.alibaba.nacos.api.config.ConfigService;
//import com.alibaba.nacos.api.config.listener.Listener;
//import com.alibaba.nacos.api.exception.NacosException;
//import com.alibaba.nacos.common.utils.CollectionUtils;
//import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.cloud.gateway.config.GatewayProperties;
//import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
//import org.springframework.cloud.gateway.filter.FilterDefinition;
//import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
//import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.context.ApplicationEvent;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.ApplicationEventPublisherAware;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//import org.yaml.snakeyaml.Yaml;
//
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.concurrent.Executor;
//import java.util.stream.Collectors;
//
//@Component
//public class DynamicRouteServiceListener implements CommandLineRunner, ApplicationEventPublisherAware {
//
//    @Autowired
//    NacosConfigManager nacosConfigManager;
//
//    @Autowired
//    DefaultListableBeanFactory defaultListableBeanFactory;
//
//    private ApplicationEventPublisher applicationEventPublisher;
//
//    private void Start() throws NacosException {
//        String dataId="cloud-gateway.yml";
//        String group="DEFAULT_GROUP";
//        Long timeOut=2000L;
//        ConfigService configService = nacosConfigManager.getConfigService();
//        configService.getConfigAndSignListener(dataId, group, timeOut, new Listener() {
//            @Override
//            public Executor getExecutor() {
//                return null;
//            }
//
//            @Override
//            public void receiveConfigInfo(String context) {
//                System.out.println("收到变更："+context);
//                List<RouteDefinition> routList = getRouteDefinitionsByYaml(context);
//                GatewayProperties bean = defaultListableBeanFactory.getBean(GatewayProperties.class);
//                bean.setRoutes(routList);
//                applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
//            }
//        });
//    }
//    @Override
//    public void run(String... args) throws Exception {
//        Start();
//    }
//
//    private List<RouteDefinition> getRouteDefinitionsByYaml(String configInfo) {
//        Yaml yaml = new Yaml();
//        Map<Object, Object> document = yaml.load(configInfo);
//        Map<Object, Object> springMap=(Map<Object, Object>) document.get("spring");
//        Map<Object, Object> cloudMap=(Map<Object, Object>) springMap.get("cloud");
//        Map<Object, Object> gatewayMap=(Map<Object, Object>)cloudMap.get("gateway");
//        List<Map<String, Object>> routeList = (List<Map<String, Object>>) gatewayMap.get("routes");
//        List<RouteDefinition> targetRouteDefinitions = new ArrayList<>(routeList.size());
//        for (Map<String, Object> routeItem : routeList) {
//            RouteDefinition routeDefinition = new RouteDefinition();
//            routeDefinition.setId((String) routeItem.get("id"));
//            routeDefinition.setUri(URI.create((String) routeItem.get("uri")));
//            List<String> predicateStrList = (List<String>) routeItem.get("predicates");
//            List<PredicateDefinition> predicateDefinitions = predicateStrList.stream().map(PredicateDefinition::new).collect(Collectors.toList());
//            routeDefinition.setPredicates(predicateDefinitions);
//            List<String> filterStrList = (List<String>) routeItem.get("filters");
//            if (CollectionUtils.isNotEmpty(filterStrList)) {
//                List<FilterDefinition> filterDefinitions = filterStrList.stream().map(FilterDefinition::new).collect(Collectors.toList());
//                routeDefinition.setFilters(filterDefinitions);
//            }
//            Object orderObj = routeItem.get("order");
//            int order = Objects.isNull(orderObj) ? 0 : (int) orderObj;
//            routeDefinition.setOrder(order);
//            targetRouteDefinitions.add(routeDefinition);
//        }
//
//        return targetRouteDefinitions;
//    }
//
//
//    @Override
//    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//        this.applicationEventPublisher=applicationEventPublisher;
//    }
//}
