package enterprise.elroi.services.servicesImplementation.userServiceImpl;

import enterprise.elroi.data.models.User;
import enterprise.elroi.data.repositories.UserRepository;
import enterprise.elroi.dto.requests.UserRequests;
import enterprise.elroi.dto.responses.UserResponse;
import enterprise.elroi.exceptions.userServiceException.DeleteUserByIdNotFoundException;
import enterprise.elroi.exceptions.userServiceException.GetUserByIdNotFoundException;
import enterprise.elroi.exceptions.userServiceException.UpdateUserByIdNotFoundException;
import enterprise.elroi.services.userServices.UserServicesInterface;
import enterprise.elroi.util.mapper.userMapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserServicesInterface {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public UserResponse getUserById(String userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new GetUserByIdNotFoundException("User not found"));
        return mapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = repository.findAll();
        List<UserResponse> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(mapper.toResponse(user));
        }
        return responses;
    }
    @Override
    public UserResponse updateUser(String userId, UserRequests request) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new UpdateUserByIdNotFoundException("User not found"));
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setRoles(request.getRoles());
        User updatedUser = repository.save(user);
        return mapper.toResponse(updatedUser);
    }

    @Override
    public void deleteUser(String userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new DeleteUserByIdNotFoundException("User not found"));
        repository.delete(user);
    }
}