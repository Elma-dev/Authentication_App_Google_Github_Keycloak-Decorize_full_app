package dev.elma.thymleaf_app_secured.web;

import dev.elma.thymleaf_app_secured.entities.Customer;
import dev.elma.thymleaf_app_secured.models.Product;
import dev.elma.thymleaf_app_secured.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Controller @AllArgsConstructor
@EnableMethodSecurity
public class ControllerThymeleaf {
    private CustomerRepository customerRepository;
    private ClientRegistrationRepository clientRegistrationRepository; //object handle subscribe provider

    @GetMapping("/customers")

    public String allCustomers(Model model){
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers",customers);
        return "customers";
    }

    @GetMapping("/products")
    public String allProducts(Model model){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        OAuth2AuthenticationToken oAuth2AuthenticationToken= (OAuth2AuthenticationToken) authentication;
        DefaultOidcUser oidcUser = (DefaultOidcUser) oAuth2AuthenticationToken.getPrincipal();
        String jwtTokenValue=oidcUser.getIdToken().getTokenValue();
        RestClient restClient = RestClient.create("http://localhost:8083");
        List<Product> products = restClient.get()
                .uri("/products")
                .headers(httpHeaders -> httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer "+jwtTokenValue))
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
        model.addAttribute("products",products);
        return "products";
    }

    @GetMapping("/auth")
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public Authentication authInfo(Authentication authentication){
       return authentication;
    }

    @GetMapping("/")

    public String index(){
        return "index";
    }

    @GetMapping("/authorization")
    public String authorization(){
        return "authorization";
    }

    //todo: to extract all providers
    @GetMapping("/loginPage")
    public String loginPage(Model model){
        String authorizationRequestBaseUri="oauth2/authorization";
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        Iterable<ClientRegistration> clients = (Iterable<ClientRegistration>) clientRegistrationRepository;
        clients.forEach(clientRegistration->{
            objectObjectHashMap.put(clientRegistration.getClientName(),authorizationRequestBaseUri+"/"+clientRegistration.getRegistrationId());
        });
        model.addAttribute("urls",objectObjectHashMap);
        return "loginPage";
    }

}
