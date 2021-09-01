package mercadolivre.mercadolivre.config.security;

import mercadolivre.mercadolivre.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
//@Profile(value = {"prod", "test"})
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    /**
     * configure metódo responsavelpor configurar a autenticação
      * @param auth --> Contrutor do autenticationManager
     * @throws Exception --> gera exceção caso não consiga contruir
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * Método que especifica quais são as permissões necessárias para acessar determinadas rotas
     * @param http --> verifica o endereço da requisição
     * @throws Exception --> se tiver algo errado é lançada uma exceção
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/categorias").permitAll()
                .antMatchers(HttpMethod.POST, "/usuarios").permitAll()
                .antMatchers(HttpMethod.GET, "/categorias/*").permitAll()
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .antMatchers(HttpMethod.GET, "/ranking-vendedores").permitAll()
                .antMatchers(HttpMethod.POST, "/ranking-vendedores").permitAll()
                .antMatchers(HttpMethod.GET, "/notafiscal").permitAll()
                .antMatchers(HttpMethod.POST, "/notafiscal").permitAll()
                .antMatchers(HttpMethod.DELETE, "/categorias/*").hasRole("MODERADOR")
                .antMatchers(HttpMethod.POST, "/produtos/*").hasRole("MODERADOR")
                .anyRequest().authenticated() //todas as outras requisições não especificadas tem que ser autenticadas
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);


    }


    //Configuracoes de recursos estaticos(js, css, imagens, etc.)
    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring().antMatchers("/**");
        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }



}
