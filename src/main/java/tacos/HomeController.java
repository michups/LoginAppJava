package tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller("/")
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class HomeController {

    @GetMapping("/asd")
    public String home() {
        return "home";
    }

    @Autowired
    private User user;

    @CrossOrigin
    @PostMapping(path = "/auth", consumes = "application/json", produces = "application/json")
    public ResponseEntity<StringResponse> addMember(@RequestBody LoginCredencials loginCredencials) {

        System.out.println(loginCredencials.getUsername() + " " + loginCredencials.getPassword());
        String success = "failure";
        StringResponse stringResponse = new StringResponse(success, "account does not exist");

        if (loginCredencials.getPassword().equals("admin")
                && loginCredencials.getUsername().equals("admin")) {
            success = "success";
            user.setName("admin");
            stringResponse = new StringResponse(success, "this is secret");
        }
        return ResponseEntity.ok(stringResponse);
    }

    @CrossOrigin
    @GetMapping(path = "/data", produces = "application/json")
    public ResponseEntity<DataResponse> getData() {

        DataResponse dataResponse = new DataResponse();
        if (user.getName()!=null && user.getName().equals("admin")) {
            dataResponse = new DataResponse("succes", "Top secret mesage only for admin");
        } else {
            dataResponse = new DataResponse("failure", "You are not admin!");
        }
        return ResponseEntity.ok(dataResponse);
    }

    @CrossOrigin
    @GetMapping(path = "/isloggedin", produces = "application/json")
    public ResponseEntity<StatusResponse> getStatus() {

        StatusResponse statusResponse = new StatusResponse();
        if (user.getName() != null && user.getName().equals("admin")) {
            statusResponse.setStatus(true);
        } else {
            statusResponse.setStatus(false);
        }
        return ResponseEntity.ok(statusResponse);
    }

    @CrossOrigin
    @GetMapping(path = "/logout", produces = "application/json")
    public ResponseEntity<SuccessResponse> logout() {
        user = new User();
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setSuccess(true);
        return ResponseEntity.ok(successResponse);
    }
}
