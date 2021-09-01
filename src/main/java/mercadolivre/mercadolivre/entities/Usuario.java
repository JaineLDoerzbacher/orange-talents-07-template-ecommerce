package mercadolivre.mercadolivre.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.*;


//A interface UserDetails diz quais são os requisitos obrigatórios do usuários
@Entity
public class Usuario implements UserDetails {

    //@Id mostra qual a chave primária da entidade
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Notblank indica que o campo não pode estar em branco e nem ser nulo
    //@Email é a validação JAva para e-mail
    @NotBlank
    @Email
    private String login;

    //@Length diz qual o minimo de caracteres que o campo deve ter
    @NotBlank @Length(min = 6)
    private String senha;

    //@PastOrPresent a data nunca pode ser no futuro (intante de criação)
    @PastOrPresent
    @JsonFormat(pattern = "dd/MM/yyyy" )
    private LocalDateTime instanteCadastro = LocalDateTime.now();

    //
    @ManyToMany(fetch = FetchType.EAGER)
    private List<PerfilUsuario> perfis = new ArrayList<>();

    //Gera uma combinação de dados com um hash único para comparação de dados
    @Override
    public int hashCode() {

        return Objects.hash(id, login, senha, instanteCadastro);
    }

    //Compara se dois objetos são iguais
    @Override
    public boolean equals(Object obj) {
       return Objects.equals(this, obj);
    }

    //Contrutor para inicialização da classe
    public Usuario() {
    }

    //Contrutor da classe
    public Usuario(String login, String senha){
        this.login = login;
        this.senha = senha;
        this.instanteCadastro = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }

    //Converte os campos em string
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", instanteCadastro=" + instanteCadastro +
                '}';
    }

    //Verifica se o usuário está logado
    public static boolean logado(Optional<Usuario> usuarioLogado){
        if(usuarioLogado.isPresent()){
            if(usuarioLogado.get() != null){
                return true;
            }
        }
        return false;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }


    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    // Método da interface UserDetails que verifica se a conta não está expirada
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Método da interface UserDetails que verifica se a conta não está bloqueada
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Método da interface UserDetails que verifica se a senha não está expirada
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Método da interface UserDetails que verifica se o usuário está habilitado ou não
    @Override
    public boolean isEnabled() {
        return true;
    }

}
