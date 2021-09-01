package mercadolivre.mercadolivre.config.security;

import mercadolivre.mercadolivre.entities.Usuario;
import mercadolivre.mercadolivre.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Implementação do UsewrDetails que foi aplicado na classe de
@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Metódo para recuperar o usuário atravésdo nome do usuário.
     * @param username --> nome do usuário
     * @return --> retornará detalhes do usuário
     * @throws UsernameNotFoundException --> Caso o usuário não seja encontrado
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new UsernameNotFoundException("Dados inválidos!");
    }

}
