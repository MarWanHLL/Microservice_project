package esprit.edu.KeycloakAdminService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class KeycloakAdminService {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.admin.username:admin}")
    private String keycloakAdminUsername;

    @Value("${keycloak.admin.password:admin}")
    private String keycloakAdminPassword;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAdminAccessToken() {
        String authUrl = keycloakServerUrl + "/realms/master/protocol/openid-connect/token";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", "admin-cli");
        formData.add("grant_type", "password");
        formData.add("username", keycloakAdminUsername);
        formData.add("password", keycloakAdminPassword);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(authUrl, request, Map.class);

        return (String) response.getBody().get("access_token");
    }
}
