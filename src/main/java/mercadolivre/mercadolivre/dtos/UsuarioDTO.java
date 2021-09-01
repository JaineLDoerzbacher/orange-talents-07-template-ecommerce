package mercadolivre.mercadolivre.dtos;

import mercadolivre.mercadolivre.entities.Usuario;
import mercadolivre.mercadolivre.validators.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UsuarioDTO {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "login", message = "O email informado já existe")
    private String login;

    @NotBlank @Size(min = 6)
    private String senha;


    public UsuarioDTO() {
    }

    public UsuarioDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario converter(){
        return new Usuario(this.login, this.senha);
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
