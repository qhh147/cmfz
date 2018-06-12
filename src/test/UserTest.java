import com.baizhi.App;
import com.baizhi.dao1.UserDAO;
import com.baizhi.entity.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Administrator on 2018/6/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UserTest {
    @Autowired
    private UserDAO userDAO;
    public void test1(){
        List<User> users = userDAO.selectUser(1, 10);
        for (User user : users) {
            System.out.println(user);
        }
    }
    public void test2(){
        User user = userDAO.queryById(1);
        System.out.println(user);
    }
}
