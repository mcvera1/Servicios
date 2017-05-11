package co.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.com.dto.User;
import co.com.interfaces.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
    @Autowired
    UserService userService;
 
    //@ResponseBodyno pide para ridireccionar a la pagina http://localhost:8080/service/user
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<User> getAllUsers() {
    	System.err.println("entre");
        return userService.findAll();
    }
 
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<User> getUser(@PathVariable long userId) {
        User user = userService.findById(userId);
        ResponseEntity<User> response;
        if (user == null) {
            response = new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return response;
    }
 
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody void insertUser(@RequestBody User user) {
        userService.save(user);
    }
 
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void updateUser(@PathVariable long userId, @RequestBody User user) {
        User userOld = userService.findById(userId);
        if (userOld != null) {
            userService.update(userOld.copyFrom(user));
        }
    }
 
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void deleteUser(@PathVariable long userId) {
        userService.delete(userId);
    }
	
}
