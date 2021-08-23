package mercadolivre.mercadolivre.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploaderImage{
    String gerarURL(MultipartFile file);
    String  imgEmBase64(MultipartFile file) throws IOException;
}
