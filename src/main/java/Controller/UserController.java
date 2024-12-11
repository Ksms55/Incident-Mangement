package Controller;

import Entity.Incident;
import Request.CreateUserRequest;
import Request.UpdateUserRequest;
import Response.UserResponse;
import Service.UserService;
import Entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")  // Define base URL path for User-related operations
public class UserController {

    @Autowired
    UserService userService;

    //Method to Get All User
    @GetMapping("/getAll")
    public List<UserResponse> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        List<UserResponse> userResponseList = new ArrayList<>();
        userList.forEach(user -> {
            userResponseList.add(new UserResponse(user));
        });
        return userResponseList;
    }

    //Method to Create User
    @PostMapping("/create")
    public UserResponse createUser(@RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    //Method to Update User
    @PutMapping("/update")
    public UserResponse updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
        User user = userService.updateUser(updateUserRequest);
        return new UserResponse(user);
    }

    //Method to Delete User
    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Long id){
        userService.deleteUser(id);
        System.out.println("1 Incident with the id = " + id + " id deleted successfully");
    }
}
