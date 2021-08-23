package mercadolivre.mercadolivre.upload;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class UploadImages implements UploaderImage{



    @Override
    public String imgEmBase64(MultipartFile file) throws IOException {
        return new String(Base64.encodeBase64(file.getBytes()), "UTF-8");
    }

    @Override
    public String gerarURL(MultipartFile file)  {
        return "https://imgbb.com/"+file.getOriginalFilename();
    }
}

