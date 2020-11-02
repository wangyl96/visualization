#Spring Security 与 OAuth2（完整案例）, 原文链接https://www.jianshu.com/p/d80061e6d900
个人 OAuth2 全部文章
Spring Security 与 OAuth2（介绍）：https://www.jianshu.com/p/68f22f9a00ee
Spring Security 与 OAuth2（授权服务器）：https://www.jianshu.com/p/227f7e7503cb
Spring Security 与 OAuth2（资源服务器）：https://www.jianshu.com/p/6dd03375224d
Spring Security 与 OAuth2（客户端）：https://www.jianshu.com/p/03e515c2b43f
Spring Security 与 OAuth2（相关类参考）：https://www.jianshu.com/p/c2395772bc86
Spring Security 与 OAuth2（完整案例）：https://www.jianshu.com/p/d80061e6d900
案例简述
简述：
允许内存、数据库、JWT等方式存储令牌
允许 JWT 方式验证令牌
允许从内存、数据库中读取客户端详情
封装配置类，简化配置，通过注解方式定制使用何种令牌存储方式、客户端详情获取方式，可使用 JWT 方式存储令牌，从数据库中获取客户端详情
提供完整单元测试
较为详细的代码注释
允许从授权服务器（适用于 JDBC、内存存储令牌）验证令牌 该代码尚未完善，仅供参考
数据库 Schema : https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
Demo Git 地址：https://gitee.com/LinYuanTongXue/OAuth2-Demo
Demo 流程：
使用 OAuth2 密码授权方式提供令牌
资源服务器1（也为客户端）提供登录接口，资源所有者（用户）通过将个人账号密码提供给 资源服务器1，资源服务器1 通过该信息向授权服务器获取令牌
资源服务器1（也为客户端）通过令牌（其中包含了客户端、用户等信息）访问自身受保护的资源（需要权限才能查看的资源）
资源服务器2（也可资源服务器）不包含登录接口，但其提供了某些受保护的资源（需要资源服务器1带着访问令牌才能访问）
资源服务器1（也为客户端）通过令牌向 资源服务器2（资源服务器） 请求其受保护的资源
使用
授权服务器通过继承 AuthServerConfig 抽象类来配置授权服务器
资源服务器、客户端通过继承 ResServerConfig 抽象类来配置资源服务器
Web 权限配置可继承 AbstractSecurityConfig 抽象类来简化配置
授权服务器通过在启动类添加以下注解来设置令牌存储、客户端信息获取方式
@EnableAuthJWTTokenStore：使用 JWT 存储令牌
@EnableDBClientDetailsService：通过数据库获取客户端详情
@EnableDBTokenStore：通过数据库存储令牌
资源服务器通过在启动类添加以下注解来设置令牌解析方式
@EnableResJWTTokenStore：使用 JWT 解析令牌
@EnableRemoteTokenService：通过授权服务器验证令牌 该代码尚未完善，仅供参考
项目结构
下图是 Demo 项目结构，使用了 Maven 之间的继承关系，并添加了热部署，不了解的可以查看下 Git 上的 Demo 源码
oauth2-config：该包中定义了一些通用的类，例如授权服务器、资源服务器配置类，服务继承该类来简化配置
authentication-server：授权服务器
resource1-server：资源服务器1（也为客户端）
resource2-server：资源服务器2（也为资源服务器）
bd1je.png
代码
oauth2-config（通用配置类库）
权限枚举常量

/**
 * @author: 林塬
 * @date: 2018/1/20
 * @description: 权限常量
 */
public enum AuthoritiesEnum {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    ANONYMOUS("ROLE_ANONYMOUS");

    private String role;

    AuthoritiesEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
授权服务器 JWT 方式存储令牌

/**
 * @author: 林塬
 * @date: 2018/1/20
 * @description: 授权服务器 TokenStore 配置类，使用 JWT RSA 非对称加密
 */
public class AuthJWTTokenStore {

    @Bean("keyProp")
    public KeyProperties keyProperties(){
        return new KeyProperties();
    }

    @Resource(name = "keyProp")
    private KeyProperties keyProperties;

    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory
                (keyProperties.getKeyStore().getLocation(), keyProperties.getKeyStore().getSecret().toCharArray())
                .getKeyPair(keyProperties.getKeyStore().getAlias());
        converter.setKeyPair(keyPair);
        return converter;
    }

}
资源服务器 JWT 方式解析令牌

/**
 * @author: 林塬
 * @date: 2018/1/20
 * @description: 资源服务器 TokenStore 配置类，使用 JWT RSA 非对称加密
 */
public class ResJWTTokenStore {

    private static final String PUBLIC_KEY = "pubkey.txt";

    @Autowired
    private ResourceServerProperties resourceServerProperties;

    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(getPubKey());
        return converter;
    }

    /**
     * 获取非对称加密公钥 Key
     * @return 公钥 Key
     */
    private String getPubKey() {
        Resource resource = new ClassPathResource(ResJWTTokenStore.PUBLIC_KEY);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException ioe) {
            return getKeyFromAuthorizationServer();
        }
    }

    /**
     * 通过访问授权服务器获取非对称加密公钥 Key
     * @return 公钥 Key
     */
    private String getKeyFromAuthorizationServer() {
        ObjectMapper objectMapper = new ObjectMapper();
        String pubKey = new RestTemplate().getForObject(resourceServerProperties.getJwt().getKeyUri(), String.class);
        try {
            Map map = objectMapper.readValue(pubKey, Map.class);
            return map.get("value").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
数据库方式存储令牌

/**
 * @author: 林塬
 * @date: 2018/1/22
 * @description: 使用数据库存取令牌
 */
public class DBTokenStore {

    @Autowired
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }

}
数据库方式加载客户端详情

/**
 * @author: 林塬
 * @date: 2018/1/22
 * @description: 通过数据库加载客户端详情
 */
public class DBClientDetailsService {

    @Autowired
    private DataSource dataSource;

    @Bean
    public ClientDetailsService clientDetailsService(){
        return new JdbcClientDetailsService(dataSource);
    }

}
授权服务器解析令牌

/**
 * @author: 林塬
 * @date: 2018/1/22
 * @description: 通过访问远程授权服务器 check_token 端点验证令牌
 */
public class RemoteTokenService {

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Resource(name = "authServerProp")
    private AuthorizationServerProperties authorizationServerProperties;

    @Bean(name = "authServerProp")
    public AuthorizationServerProperties authorizationServerProperties(){
        return new AuthorizationServerProperties();
    }

    @Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(authorizationServerProperties.getCheckTokenAccess());
        remoteTokenServices.setClientId(oAuth2ClientProperties.getClientId());
        remoteTokenServices.setClientSecret(oAuth2ClientProperties.getClientSecret());
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
        return remoteTokenServices;
    }

    @Bean
    public AccessTokenConverter accessTokenConverter() {
        return new DefaultAccessTokenConverter();
    }

}
WebSecurity 权限类

/**
 * @author: 林塬
 * @date: 2018/1/20
 * @description: Web 权限配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public abstract class AbstractSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostConstruct
    public void init() {
        try {
            authenticationManagerBuilder
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed", e);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui/index.html").hasAuthority(AuthoritiesEnum.ADMIN.getRole());
    }

授权服务器配置类

/**
 * @author: 林塬
 * @date: 2018/1/20
 * @description: OAuth2 授权服务器配置类
 */
@EnableAuthorizationServer
public abstract class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired(required = false)
    private JdbcClientDetailsService jdbcClientDetailsService;

    //令牌失效时间
    public int accessTokenValiditySeconds;

    //刷新令牌失效时间
    public int refreshTokenValiditySeconds;

    //是否可以重用刷新令牌
    public boolean isReuseRefreshToken;

    //是否支持刷新令牌
    public boolean isSupportRefreshToken;


    public AuthServerConfig(int accessTokenValiditySeconds, int refreshTokenValiditySeconds, boolean isReuseRefreshToken, boolean isSupportRefreshToken) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.isReuseRefreshToken = isReuseRefreshToken;
        this.isSupportRefreshToken = isSupportRefreshToken;
    }

    /**
     * 配置授权服务器端点，如令牌存储，令牌自定义，用户批准和授权类型，不包括端点安全配置
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        Collection<TokenEnhancer> tokenEnhancers = applicationContext.getBeansOfType(TokenEnhancer.class).values();
        TokenEnhancerChain tokenEnhancerChain=new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(new ArrayList<>(tokenEnhancers));

        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setReuseRefreshToken(isReuseRefreshToken);
        defaultTokenServices.setSupportRefreshToken(isSupportRefreshToken);
        defaultTokenServices.setTokenStore(tokenStore);
        defaultTokenServices.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
        defaultTokenServices.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        //若通过 JDBC 存储令牌
        if (Objects.nonNull(jdbcClientDetailsService)){
            defaultTokenServices.setClientDetailsService(jdbcClientDetailsService);
        }

        endpoints
            .authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService)
            .tokenServices(defaultTokenServices);
    }


    /**
     * 配置授权服务器端点的安全
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

}
资源服务器配置类

/**
 * @author: 林塬
 * @date: 2018/1/20
 * @description: OAuth2 资源服务器配置类
 */
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public abstract class ResServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired(required = false)
    private RemoteTokenServices remoteTokenServices;

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Bean
    @Qualifier("authorizationHeaderRequestMatcher")
    public RequestMatcher authorizationHeaderRequestMatcher() {
        return new RequestHeaderRequestMatcher("Authorization");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .exceptionHandling()
            .and()
                .headers()
                .frameOptions()
                .disable()
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .requestMatcher(authorizationHeaderRequestMatcher());
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        resources.resourceId(oAuth2ClientProperties.getClientId());
        if (Objects.nonNull(remoteTokenServices)) {
            resources.tokenServices(remoteTokenServices);
        }
    }
}
注解

/**
 * @author: 林塬
 * @date: 2018/1/22
 * @description: 在启动类上添加该注解来----开启 JWT 令牌存储（授权服务器-非对称加密）
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AuthJWTTokenStore.class)
public @interface EnableAuthJWTTokenStore {
}
/**
 * @author: 林塬
 * @date: 2018/1/22
 * @description: 在启动类上添加该注解来----开启从数据库加载客户端详情
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(DBClientDetailsService.class)
public @interface EnableDBClientDetailsService {
}
/**
 * @author: 林塬
 * @date: 2018/1/22
 * @description: 在启动类上添加该注解来----开启通过数据库存储令牌
 *               数据库 schema ：https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(DBTokenStore.class)
public @interface EnableDBTokenStore {
}
/**
 * @author: 林塬
 * @date: 2018/1/22
 * @description: 在启动类上添加该注解来----开启通过授权服务器验证访问令牌（适用于 JDBC、内存存储令牌）
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RemoteTokenService.class)
public @interface EnableRemoteTokenService {
}
/**
 * @author: 林塬
 * @date: 2018/1/22
 * @description: 在启动类上添加该注解来----开启 JWT 令牌存储（资源服务器-非对称加密）
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ResJWTTokenStore.class)
public @interface EnableResJWTTokenStore {
}
authentication-server（授权服务器）
授权服务器配置

/**
 * @author: 林塬
 * @date: 2018/1/10
 * @description: OAuth2 授权服务器配置
 */
@Configuration
public class AuthorizationServerConfig extends AuthServerConfig {

    /**
     * 调用父类构造函数，设置令牌失效日期等信息
     */
    public AuthorizationServerConfig() {
        super((int)TimeUnit.DAYS.toSeconds(1), 0, false, false);
    }

    /**
     * 配置客户端详情
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
        clients.inMemory()                          // 使用内存存储客户端信息
                .withClient("resource1")       // client_id
                .secret("secret")                   // client_secret
                .authorizedGrantTypes("authorization_code","password")     // 该client允许的授权类型
                .scopes("read")                    // 允许的授权范围
                .autoApprove(true);                  //登录后绕过批准询问(/oauth/confirm_access)
    }
}

WebSecurity 权限配置

/**
 * @author: 林塬
 * @date: 2018/1/19
 * @description: 权限配置
 */
@Configuration
public class WebSecurityConfig extends AbstractSecurityConfig {

}
UserDetailsService 实现

/**
 * @author: 林塬
 * @date: 2018/1/9
 * @description: 用户信息获取
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    /**
     * 通过 Username 加载用户详情
     * @param username 用户名
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("linyuan")) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode("123456");
            UserDetails userDetails = new User("linyuan",
                    password,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(AuthoritiesEnum.USER.getRole()));
            return userDetails;
        }
        return null;
    }
}
启动类

@SpringBootApplication
@EnableAuthJWTTokenStore    // 使用 JWT 存储令牌
//@EnableDBClientDetailsService //从 JDBC 加载客户端详情,需配置在启动类上，若在子类上会出现顺序问题，导致 Bean 创建失败
public class AuthenticationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServerApplication.class, args);
    }
}
微服务配置

server:
  port: 9005
encrypt:
  key-store:
    location: mytest.jks
    secret: mypass
    alias: mytest
# 若从数据库中获取客户端信息则需配置数据库源
#spring:
#  datasource:
#    driver-class-name: org.h2.Driver
#    url: jdbc:h2:mem:test
#    username: sa
#  h2:
#    console:
#      enabled: true
单元测试

/**
 * @author: 林塬
 * @date: 2018/1/16
 * @description: 令牌单元测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthenticationServerApplication.class)
@AutoConfigureMockMvc
public class TokenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 密码授权模式获取令牌
     *
     * @throws Exception
     */
    @Test
    public void getToken() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("username", Collections.singletonList("linyuan"));
        map.put("password", Collections.singletonList("123456"));
        map.put("grant_type", Collections.singletonList("password"));
        map.put("scope", Collections.singletonList("read"));
        int status = this.mockMvc.perform(
                post("/oauth/token")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("resource1:secret".getBytes()))
                        .params(map)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andReturn().getResponse().getStatus();
        switch (status) {
            case HttpStatus.SC_OK:
                log.info("密码授权模式获取令牌---------------->成功（200）");
                break;
            case HttpStatus.SC_UNAUTHORIZED:
                log.info("密码授权模式获取令牌---------------->失败（401---没有权限，请检查验证信息，账号是否存在、客户端信息）");
                break;
            case HttpStatus.SC_BAD_REQUEST:
                log.info("密码授权模式获取令牌---------------->失败（400---请求失败，请检查密码是否正确）");
                break;
            default:
                log.info("密码授权模式获取令牌---------------->失败（{}---未知结果）",status);
                break;
        }
        Assert.assertEquals(status,HttpStatus.SC_OK);
    }

}
resource2-server（资源服务器2）
资源服务器配置

/**
 * @author: 林塬
 * @date: 2018/1/11
 * @description: 资源服务器访问权限配置
 */
@Configuration
public class WebSecurityConfig extends ResServerConfig{

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
                //访问受保护资源 /res 的要求：客户端 Scope 为 read，用户本身角色为 USER
                .antMatchers("/res")
                .access("#oauth2.hasScope('read') and hasRole('USER')");
    }

}
受保护资源

/**
 * @author: 林塬
 * @date: 2018/1/16
 * @description: 资源服务器2-资源接口
 */
@RestController
public class ResController {

    @GetMapping("/res")
    public ResponseEntity<String> res(){
        return ResponseEntity.ok("<h1>这是资源服务器2的受保护的资源</h1>");
    }

}
启动类

@SpringBootApplication
@EnableResJWTTokenStore //OAuth2 使用 JWT 解析令牌
public class Resource2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Resource2ServerApplication.class, args);
    }
}
微服务配置

server:
  port: 9006
security:
  oauth2:
    resource:
      jwt:
        key-uri: http://localhost:9005/oauth/token_key
resource1-server（资源服务器1（客户端））
资源服务器访问权限配置

/**
 * @author: 林塬
 * @date: 2018/1/18
 * @description: 资源服务器访问权限配置
 */
@Configuration
public class WebSecurityConfig extends ResServerConfig {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/res","/res2/res")
                .access("#oauth2.hasScope('read') and hasRole('USER')");
    }

}
用户登录数据传输对象

/**
 * @author: 林塬
 * @date: 2018/1/15
 * @description: 用户登录数据传输对象
 */
@Data
public class LoginDTO implements Serializable {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}
资源服务器受保护资源

/**
 * @author: 林塬
 * @date: 2018/1/16
 * @description: 资源服务器1-资源接口
 */
@RestController
@AllArgsConstructor
public class ResController {

    private RestTemplate restTemplate;

    @GetMapping("/res")
    public ResponseEntity<String> res(){
        return ResponseEntity.ok("<h1>这是资源服务器1的受保护的资源</h1>");
    }

    /**
     * 访问资源服务器2-资源接口
     * @param httpReq
     * @return
     */
    @GetMapping("/res2/res")
    public ResponseEntity<String> remoteRes(HttpServletRequest httpReq){
        //HttpEntity
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization",httpReq.getHeader("Authorization"));
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        //请求资源服务器2的资源
        return restTemplate.exchange("http://localhost:9006/res",HttpMethod.GET,httpEntity,String.class);
    }

}
令牌管理接口

/**
 * @author: 林塬
 * @date: 2018/1/16
 * @description: 令牌管理接口
 */
@RestController
@AllArgsConstructor
public class TokenController {

    private OAuth2ClientProperties oAuth2ClientProperties;

    private OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails;

    private RestTemplate restTemplate;

    /**
     * 通过密码授权方式向授权服务器获取令牌
     * @param loginDTO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/login")
    public ResponseEntity<OAuth2AccessToken> login(@RequestBody @Valid LoginDTO loginDTO, BindingResult bindingResult)  throws Exception{
        if (bindingResult.hasErrors()) {
            throw new Exception("登录信息格式错误");
        } else {
            //Http Basic 验证
            String clientAndSecret = oAuth2ClientProperties.getClientId()+":"+oAuth2ClientProperties.getClientSecret();
            //这里需要注意为 Basic 而非 Bearer
            clientAndSecret = "Basic "+Base64.getEncoder().encodeToString(clientAndSecret.getBytes());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization",clientAndSecret);
            //授权请求信息
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.put("username", Collections.singletonList(loginDTO.getUsername()));
            map.put("password", Collections.singletonList(loginDTO.getPassword()));
            map.put("grant_type", Collections.singletonList(oAuth2ProtectedResourceDetails.getGrantType()));
            map.put("scope", oAuth2ProtectedResourceDetails.getScope());
            //HttpEntity
            HttpEntity httpEntity = new HttpEntity(map,httpHeaders);
            //获取 Token
            return restTemplate.exchange(oAuth2ProtectedResourceDetails.getAccessTokenUri(), HttpMethod.POST,httpEntity,OAuth2AccessToken.class);
        }
    }
}
启动类

@SpringBootApplication
@EnableResJWTTokenStore //OAuth2 使用 JWT 解析令牌
public class Resource1ServerApplication {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Resource1ServerApplication.class, args);
    }
}
微服务配置

server:
  port: 9007
security:
  oauth2:
    client:
      clientId: resource1
      clientSecret: secret
      userAuthorizationUri: http://localhost:9005/oauth/authorize
      grant-type: password
      scope: read
      access-token-uri: http://localhost:9005/oauth/token
    resource:
      jwt:
        key-uri: http://localhost:9005/oauth/token_key
  basic:
    enabled: false
单元测试


/**
 * @author: 林塬
 * @date: 2018/1/19
 * @description: 资源获取单元测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Resource1ServerApplication.class)
@AutoConfigureMockMvc
public class ResControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private OAuth2AccessToken oAuth2AccessToken;

    /**
     * 获取令牌
     * @throws Exception
     */
    @Before
    public void getToken() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("linyuan");
        loginDTO.setPassword("123456");

        byte[] body = this.mockMvc.perform(
                post("/login")
                        .content(objectMapper.writeValueAsBytes(loginDTO))
                        .contentType(MediaType.APPLICATION_JSON)    //请求数据的格式
                        .accept(MediaType.APPLICATION_JSON)         //接收返回数据的格式
        ).andExpect(status().isOk())
         .andReturn().getResponse().getContentAsByteArray();
        oAuth2AccessToken = objectMapper.readValue(body,OAuth2AccessToken.class);
    }

    /**
     * 测试访问本地受保护资源
     * @throws Exception
     */
    @Test
    public void testGetLocalRes() throws Exception{
        int status = this.mockMvc.perform(
                get("/res")
                        .header("Authorization",OAuth2AccessToken.BEARER_TYPE+" "+oAuth2AccessToken.getValue())
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andReturn().getResponse().getStatus();
        printStatus(status);
        Assert.assertEquals(status,HttpStatus.SC_OK);
    }

    /**
     * 测试访问资源服务器2受保护资源
     * @throws Exception
     */
    @Test
    public void testGetRes2lRes() throws Exception{
        int status = this.mockMvc.perform(
                get("/res2/res")
                        .header("Authorization",OAuth2AccessToken.BEARER_TYPE+" "+oAuth2AccessToken.getValue())
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andReturn().getResponse().getStatus();
        printStatus(status);
        Assert.assertEquals(status,HttpStatus.SC_OK);
    }

    private void printStatus(int status){
        switch (status) {
            case HttpStatus.SC_OK:
                log.info("测试访问受保护资源---------------->成功（200）");
                break;
            case HttpStatus.SC_UNAUTHORIZED:
                log.info("测试访问受保护资源---------------->失败（401---没有权限，请确认令牌无误，角色权限无误，请注意是否 Authorization 请求头部 Basic 打成了 Bearer）");
                break;
            case HttpStatus.SC_BAD_REQUEST:
                log.info("测试访问受保护资源---------------->失败（400---请求失败，请检查用户密码是否正确）");
                break;
            default:
                log.info("测试访问本地受保护资源---------------->失败（{}---未知结果）",status);
                break;
        }
    }
}

作者：林塬
链接：https://www.jianshu.com/p/d80061e6d900
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。