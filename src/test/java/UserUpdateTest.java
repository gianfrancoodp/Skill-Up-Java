
/*
import com.alkemy.wallet.controller.UserController;
import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@ExtendWith(SpringExtension.class)
    @WebMvcTest(value= UserController.class)
    @WithMockUser
    public class UserUpdateTest {

        @Autowired
        MockMvc mockMvc;
    @MockBean
    private IUserService userService;

UserEntity userEntity=new UserEntity(1,"jose","perez","perez@gmail.com","123fgd");

// String exampleUser = "{\"firstName\":\"Jose\",\"lastName\":\"perez\",\"password\":\"123gg5\"}";
        @Test
        public void updateUser() throws Exception {

            Mockito.when(userService.findById(1)).thenReturn(userEntity);
            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .patch("/users/1")
                    .accept(MediaType.APPLICATION_JSON).content()
                    .contentType(MediaType.APPLICATION_JSON);

            MvcResult result = mockMvc.perform(requestBuilder).andReturn();

            MockHttpServletResponse response = result.getResponse();

            UserEntity userEntityExpected=new UserEntity(1,"jose","Gomez","perez@gmail.com","123fgd");
            String expected = "{\"firstName\":\"Jose\",\"lastName\":\"gomez\",\"password\":\"12AAA5\"}";

            JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),true);
        }
    }
/*
        @Test
        public void givenAnUserThenTheUserShouldBeUpdatedTest(){

            UserMapper userMapper=new UserMapper();

            UserDto u=new UserDto();
            u.setFirstName("Juan");
            u.setLastName("perez");
            u.setPassword("1234");


            UserEntity u2= new UserEntity();
            u2.setFirstName("Andrés");
            u2.setLastName("perez");
            u2.setPassword("1456");


            UserEntity u3=userMapper.updateUserDTO2Entity(u,u2);

            Assertions.assertEquals(u.getFirstName(),u3.getFirstName());
            Assertions.assertEquals(u2.getLastName(),u3.getLastName());
            Assertions.assertEquals(u.getPassword(),u3.getPassword());



        }}
*/
